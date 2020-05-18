package hr.markic.library.service.impl;

import hr.markic.library.domain.Contact;
import hr.markic.library.repository.ContactRepository;
import hr.markic.library.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public Optional<Contact> findOne(Long contactId) {
        return contactRepository.findById(contactId);
    }
}
