package org.example.repository;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.example.repository.config.ApplicationConfig;
import org.example.repository.user.User;
import org.example.repository.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRepositoryApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
//@ActiveProfiles("local")
@Log4j2
public class UserServiceIT {
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private String url="http://localhost:8082";
    @Resource
    UserRepository userRepository;

    @Test
    public void getUsersWithRestTemplate() throws JsonParseException, JsonMappingException, IOException {
        ResponseEntity<String> responseString = testRestTemplate.getForEntity(url + "/api/rest/users", String.class);
        Assert.assertNotNull(responseString);
        List<User> users = parseResponse(responseString);
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals("admin", users.get(0).getUsername());
    }

    private List<User> parseResponse(ResponseEntity<String> responseString)
            throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return mapper.readValue(responseString.getBody(), new TypeReference<List<User>>() {
        });
    }
}
