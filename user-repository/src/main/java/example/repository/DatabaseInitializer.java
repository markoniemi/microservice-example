package example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import example.repository.user.Role;
import example.repository.user.User;
import example.repository.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DatabaseInitializer {
	@Autowired
	private UserRepository userRepository;

    @PostConstruct
    public void initialize() {
        log.debug("DatabaseInitializer.initialize");
        try {
            userRepository.save(new User("admin", "admin", "admin@test.com", Role.ROLE_ADMIN));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
