package org.example.repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.example.repository.user.Role;
import org.example.repository.user.User;
import org.example.repository.user.UserRepository;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DatabaseInitializer {

    @Setter
    @Resource
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
