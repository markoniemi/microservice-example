package example.user.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@ComponentScan(basePackages = { "example.user" })
@EnableEurekaClient
@EnableFeignClients(basePackages = { "example.user.service" })
public class ApplicationConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }
}