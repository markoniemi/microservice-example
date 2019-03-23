package org.example.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class UserRepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserRepositoryApplication.class, args);
    }
}
