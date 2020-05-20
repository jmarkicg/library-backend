package hr.markic.library.rest.error;

import hr.markic.library.constants.ErrorCodes;
import hr.markic.library.rest.exception.BlinkImportException;
import hr.markic.library.rest.exception.InvalidRequestDataException;
import hr.markic.library.rest.exception.UnknownUserException;
import hr.markic.library.rest.exception.UserExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonErrorHandler {

    private final Logger log = LoggerFactory.getLogger(CommonErrorHandler.class);

    @ExceptionHandler(InvalidRequestDataException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRequestData(InvalidRequestDataException ex) {
        String msg = (ex.getCustomMsg() != null)? ex.getCustomMsg(): "Request body not readable / not fully populated.";
        ApiErrorResponse response = new ApiErrorResponse(ErrorCodes.ERR_BAD_REQUEST, msg);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownUserException.class)
    public ResponseEntity<ApiErrorResponse> handleUnknownUser(UnknownUserException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ErrorCodes.ERR_UNKNOWN_USER,
                ex.getId().toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUserExists(UserExistsException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ErrorCodes.ERR_UNKNOWN_USER,
                ex.getId().toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlinkImportException.class)
    public ResponseEntity<ApiErrorResponse> handleUBlinkException(BlinkImportException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ErrorCodes.ERR_BLINK_IMPORT,
                "Blink import image failure.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse(ErrorCodes.ERR_INTERNAL_SERVER_ERROR,
                ex.toString());
        log.error("Failed to handle request.", ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
