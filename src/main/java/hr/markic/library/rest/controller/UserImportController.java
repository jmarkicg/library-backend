package hr.markic.library.rest.controller;

import hr.markic.library.domain.User;
import hr.markic.library.dto.UserDTO;
import hr.markic.library.rest.exception.BlinkImportException;
import hr.markic.library.service.ImageParserService;
import hr.markic.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserImportController {

    private final Logger log = LoggerFactory.getLogger(UserImportController.class);

    private ImageParserService imageParserService;

    private UserService userService;

    @Autowired
    UserImportController(ImageParserService imageParserService,
                         UserService userService) {
        this.imageParserService = imageParserService;
        this.userService = userService;
    }

    @PostMapping("/user/import")
    public ResponseEntity<?> importUser(@RequestParam("file") MultipartFile file) throws BlinkImportException {
        try {
            Optional<UserDTO> user = imageParserService.importUserFromImageBytes(file.getBytes());

            if (user.isPresent() && user.get().getIsValid()) {
                Optional<User> userExisting = userService.findOneByIDNumber(user.get().getIdentityCardId());
                if (!userExisting.isPresent()){
                    userService.saveUser(user.get());
                    return ResponseEntity.ok().build();
                } else {
                    log.error("User with the same identification ID exists in the system.");
                }
            }
        } catch (IOException e) {
            log.error("Failed to read the attached file.", e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
