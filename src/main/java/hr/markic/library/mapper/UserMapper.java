package hr.markic.library.mapper;

import hr.markic.library.domain.User;
import hr.markic.library.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ContactMapper.class, ContactTypeMapper.class})
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO user);
}
