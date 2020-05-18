package hr.markic.library.config;

import hr.markic.library.audit.LibraryAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef="auditorProvider")
@Configuration
public class PersistenceConfig {

    @Bean
    AuditorAware<String> auditorProvider() {
        return new LibraryAuditorAware();
    }
}
