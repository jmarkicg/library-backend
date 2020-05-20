package hr.markic.library.dto.microblink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlinkIDResponseDTO {

    private String code;

    private String summary;

    private String executionId;

    private BlinkIDData data;
}
