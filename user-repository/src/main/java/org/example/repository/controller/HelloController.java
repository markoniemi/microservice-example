package org.example.repository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloController {
    @Value("${user.role}")
    private String role;
    @Autowired
    private Environment environment;

    @GetMapping(value = "/hello/{username}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(@PathVariable("username") String username) {
        return String.format("Hello %s, %s", username, role);
    }

    @GetMapping(value = "/env", produces = MediaType.TEXT_PLAIN_VALUE)
    public String env() {
        return environment.toString();
    }
}
