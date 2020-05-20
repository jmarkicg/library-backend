package hr.markic.library.dto.microblink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlinkIDRequestDTO {

    private String recognizerType;

    private String imageBase64;
}
