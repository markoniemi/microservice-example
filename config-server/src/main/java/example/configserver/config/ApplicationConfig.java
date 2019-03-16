package example.configserver.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "org.cloudconfig")
// @EntityScan(basePackages = "org.survey.model")
// @Import({ JpaConfig.class, TomcatConfig.class, WebMvcConfig.class,
// WebSecurityConfig.class })
@Import({ TomcatConfig.class })
public class ApplicationConfig {
    // @Bean
    // public DatabaseInitializer getDatabaseInitializer() {
    // return new DatabaseInitializer();
    // }
}