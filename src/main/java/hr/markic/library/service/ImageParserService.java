package hr.markic.library.service;

import hr.markic.library.dto.UserDTO;
import hr.markic.library.rest.exception.BlinkImportException;

import java.io.File;
import java.util.Optional;

public interface ImageParserService {

    Optional<UserDTO> importUserFromImageFile(File file) throws BlinkImportException;

    Optional<UserDTO> importUserFromImageBytes(byte[] bytes) throws BlinkImportException;
}
