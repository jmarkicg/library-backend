package hr.markic.library.rest.controller;

import hr.markic.library.domain.ContactType;
import hr.markic.library.dto.ContactTypeDTO;
import hr.markic.library.mapper.ContactTypeMapper;
import hr.markic.library.service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class ContactTypeController {

    private ContactTypeService contactTypeService;

    private ContactTypeMapper contactTypeMapper;

    @Autowired
    ContactTypeController(ContactTypeService contactTypeService,
                          ContactTypeMapper contactTypeMapper){
        this.contactTypeService = contactTypeService;
        this.contactTypeMapper = contactTypeMapper;
    }

    @GetMapping("/contact-types")
    public ResponseEntity<List<ContactTypeDTO>> getAllContactTypes() {
        List<ContactType> contactTypes = contactTypeService.findAll();

        List<ContactTypeDTO> contactTypesDTO = contactTypes.stream().
                map(contactType -> contactTypeMapper.toDTO(contactType)).collect(Collectors.toList());

        return ResponseEntity.ok().body(contactTypesDTO);
    }
}

