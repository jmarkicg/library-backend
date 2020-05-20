package hr.markic.library.service.impl;

import hr.markic.library.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = fileToBytes(file);
        return encodeByteStreamToBase64(fileContent);
    }

    @Override
    public String encodeByteStreamToBase64(byte[] bytes){
        String encodedString = Base64.getEncoder().encodeToString(bytes);
        return encodedString;
    }

    @Override
    public byte[] fileToBytes(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }
}
