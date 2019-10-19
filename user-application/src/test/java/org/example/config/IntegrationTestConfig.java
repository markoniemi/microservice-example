package org.example.config;

import org.example.selenium.SeleniumConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.user.config.ApplicationConfig;

@Configuration
@Import({ ApplicationConfig.class, SeleniumConfig.class })
public class IntegrationTestConfig {
}
