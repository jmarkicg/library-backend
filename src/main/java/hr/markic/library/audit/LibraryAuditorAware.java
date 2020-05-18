package hr.markic.library.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Dummy {@link AuditorAware} implementation.
 * Always returns ADMIN user.
 * If Spring Security was integrated properly, current user here would be retrieved.
 * @return
 */
@Service
public class LibraryAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("admin");
    }

}
