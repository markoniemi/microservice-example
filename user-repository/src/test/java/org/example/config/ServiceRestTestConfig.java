package org.example.config;

import java.util.Arrays;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.example.repository.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

//@Configuration
//@PropertySource("classpath:server.properties")
public class ServiceRestTestConfig {
    @Value("http://localhost:8082/user-repository/api/rest/")
//    @Value("${http.protocol}://localhost:${http.port}/user-repository/api/rest/")
    private String baseAddress;
    @Bean
    public List<?> getJsonProviders() {
        return Arrays.asList(new JacksonJaxbJsonProvider());
    }

    @Bean(name = "userService")
    public Object getUserService() {
        return JAXRSClientFactory.create(baseAddress, UserService.class, getJsonProviders());
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer
//    propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}