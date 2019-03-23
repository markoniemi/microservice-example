package org.example.repository.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.example")
@Import({ TomcatConfig.class, DiscoveryClientConfig.class, UserRepositoryConfig.class, SpringRestConfiguration.class })
public class ApplicationConfig {
}