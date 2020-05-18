package hr.markic.library.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UnknownUserException extends Exception {

    private final Long id;
}
