package hr.markic.library.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String encodeFileToBase64(File file) throws IOException;

    String encodeByteStreamToBase64(byte[] bytes);

    byte[] fileToBytes(File file) throws IOException;
}

