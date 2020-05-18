package hr.markic.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {

    private Long id;

    private Long userId;

    private ContactTypeDTO contactType;

    private String value;
}
