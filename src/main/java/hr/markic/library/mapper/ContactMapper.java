package hr.markic.library.mapper;

import hr.markic.library.domain.Contact;
import hr.markic.library.dto.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ContactTypeMapper.class})
public interface ContactMapper {

    @Mapping(source = "user.id", target = "userId")
    ContactDTO toDTO(Contact contact);

    @Mapping(source = "userId", target = "user.id")
    Contact toEntity(ContactDTO contactDTO);
}
