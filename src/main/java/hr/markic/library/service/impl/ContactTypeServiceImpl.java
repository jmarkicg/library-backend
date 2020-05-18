package hr.markic.library.service.impl;

import hr.markic.library.domain.ContactType;
import hr.markic.library.repository.ContactTypeRepository;
import hr.markic.library.service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactTypeServiceImpl implements ContactTypeService {

    private ContactTypeRepository contactTypeRepository;

    @Autowired
    public ContactTypeServiceImpl(ContactTypeRepository contactTypeRepository){
        this.contactTypeRepository = contactTypeRepository;
    }

    @Override
    public List<ContactType> findAll() {
        return contactTypeRepository.findAll();
    }
}
