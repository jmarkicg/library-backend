package hr.markic.library.rest.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiErrorResponse {

    @JsonProperty("error-code")
    private String errorCode;

    @JsonProperty("error-arg")
    private String errorArg;

}
