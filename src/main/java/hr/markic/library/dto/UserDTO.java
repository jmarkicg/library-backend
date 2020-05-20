package hr.markic.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO extends PersonDTO {

    private String userName;

    private Set<ContactDTO> contacts;

    private Boolean isValid;

    private String identityCardId;
}
