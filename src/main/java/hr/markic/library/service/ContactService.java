package hr.markic.library.service;

import hr.markic.library.domain.Contact;

import java.util.Optional;

/**
 * Contact service interface.
 */
public interface ContactService {

    /**
     * Retrieves one {@link hr.markic.library.domain.Contact} instance by id.
     * @param contactId
     * @return
     */
    Optional<Contact> findOne(Long contactId);
}
