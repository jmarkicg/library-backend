package hr.markic.library.mapper;

import hr.markic.library.domain.ContactType;
import hr.markic.library.dto.ContactTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactTypeMapper {

    ContactTypeDTO toDTO(ContactType contactType);

    ContactType toEntity(ContactTypeDTO contactType);
}
