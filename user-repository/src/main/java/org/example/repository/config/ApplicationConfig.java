package org.example.repository.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.example")
@Import({ DiscoveryClientConfig.class, UserRepositoryConfig.class })
public class ApplicationConfig {
}