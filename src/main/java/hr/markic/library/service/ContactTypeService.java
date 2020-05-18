package hr.markic.library.service;

import hr.markic.library.domain.ContactType;

import java.util.List;

/**
 * Contact type service interface.
 */
public interface ContactTypeService {

    List<ContactType> findAll();
}
