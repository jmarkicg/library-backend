package hr.markic.library.service.impl;

import hr.markic.library.constants.BlinkConstants;
import hr.markic.library.dto.UserDTO;
import hr.markic.library.dto.microblink.BlinkIDRequestDTO;
import hr.markic.library.dto.microblink.BlinkIDResponseDTO;
import hr.markic.library.dto.microblink.BlinkIDResult;
import hr.markic.library.rest.client.BlinkWebClient;
import hr.markic.library.rest.exception.BlinkImportException;
import hr.markic.library.service.FileService;
import hr.markic.library.service.ImageParserService;
import hr.markic.library.util.DateUtil;
import hr.markic.library.util.DocumentType1Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
public class IDParserServiceImpl implements ImageParserService {

    private final Logger log = LoggerFactory.getLogger(IDParserServiceImpl.class);

    BlinkWebClient blinkWebClient;

    FileService fileService;

    @Value("${microblink.api.blink-id-path}")
    private String blinkIDPath;


    @Autowired
    public IDParserServiceImpl(BlinkWebClient blinkWebClient,
                               FileService fileService){
        this.blinkWebClient = blinkWebClient;
        this.fileService = fileService;
    }

    @Override
    public Optional<UserDTO> importUserFromImageFile(File file) throws BlinkImportException {
            try {
                return importUserFromImageBytes(fileService.fileToBytes(file));
            } catch (Exception e) {
                log.error("Failed to import user from image file.", e);
                throw new BlinkImportException();
            }
    }

    @Override
    public Optional<UserDTO> importUserFromImageBytes(byte[] bytes) throws BlinkImportException {
        try {
            BlinkIDRequestDTO blinkIDRequestDTO = new BlinkIDRequestDTO();
            blinkIDRequestDTO.setRecognizerType(BlinkConstants.RECOGNIZE_TYPE_MRTD);
            blinkIDRequestDTO.setImageBase64(fileService.encodeByteStreamToBase64(bytes));

            Optional<Object> responseOpt = blinkWebClient.performPOSTRequest(blinkIDRequestDTO, BlinkIDResponseDTO.class, blinkIDPath);
            if (responseOpt.isPresent()) {
                BlinkIDResponseDTO response = (BlinkIDResponseDTO) responseOpt.get();
                UserDTO user = mapResponseToUser(response);
                return Optional.of(user);
            }

        } catch (Exception e) {
            log.error("Failed to import user from image.", e);
            throw new BlinkImportException();
        }
        return Optional.empty();
    }

    private UserDTO mapResponseToUser(BlinkIDResponseDTO response) {
        UserDTO user = new UserDTO();
        user.setIsValid(false);
        if (response.getCode().equals(BlinkConstants.RESPONSE_STATUS_OK)){
            BlinkIDResult result = response.getData().getResult();
            user.setFirstName(result.getSecondaryID());
            user.setLastName(result.getPrimaryID());
            user.setIdentityCardId(result.getDocumentNumber());
            user.setDateOfBirth(DateUtil.getLocalDateFromDayMonthYear(result.getDateOfBirth().getYear(),
                                result.getDateOfBirth().getMonth(), result.getDateOfBirth().getDay()));
            user.setIsValid(DocumentType1Validator.checkDataValidity(result));
        }
        return user;
    }
}
