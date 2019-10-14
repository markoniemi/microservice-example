//package org.example.repository;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.example.repository.config.ApplicationConfig;
//import org.example.repository.user.User;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextHierarchy;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import lombok.Data;
//import lombok.extern.log4j.Log4j2;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserRepositoryApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
//@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
////@ActiveProfiles("local")
////@EnableFeignClients
//@Log4j2
//public class UserServiceIT {
//    private TestRestTemplate testRestTemplate = new TestRestTemplate();
//    private String url="http://localhost:8082/api/rest";
////    private String url="http://localhost:8082";
////    @Resource
////    UserRepository userRepository;
////    @Resource
////    UserService userService;
//    @Test
//    public void getUsersWithRestTemplate() throws JsonParseException, JsonMappingException, IOException {
//        ResponseEntity<String> responseString = testRestTemplate.getForEntity(url + "/users", String.class);
//        Assert.assertNotNull(responseString);
//        List<User> users = parseResponse(responseString);
////        List<User> users = testRestTemplate.getForObject(url + "/users", UserResponse.class).getUsers();
//        Assert.assertNotNull(users);
//        Assert.assertEquals(1, users.size());
//        Assert.assertEquals("admin", users.get(0).getUsername());
//    }
//    @Ignore
//    @Test
//    public void getUserWithRestTemplate() throws JsonParseException, JsonMappingException, IOException {
//        ResponseEntity<String> responseString = testRestTemplate.getForEntity(url + "/users/1", String.class);
//        Assert.assertNotNull(responseString);
//        log.debug("responseString: " + responseString);
//        User user = parseUser(responseString);
////        User user = testRestTemplate.getForObject(url + "/users/1", User.class);
////        Assert.assertNotNull(user);
//        Assert.assertEquals("admin", user.getUsername());
//    }
////    @Ignore
////    @Test
////    public void getUsersWithFeignClient() {
////        User[] users = userService.findAll();
////        Assert.assertEquals("admin", users[0].getUsername());
////    }
////    @Ignore
////    @Test
////    public void getUserWithFeignClient() {
////        User user = userService.findById(1L);
////        Assert.assertEquals("admin", user.getUsername());
////    }
//
//    private List<User> parseResponse(ResponseEntity<String> responseString)
//            throws IOException, JsonParseException, JsonMappingException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        return mapper.readValue(responseString.getBody(), new TypeReference<List<User>>() {
//        });
//    }
//    private User parseUser(ResponseEntity<String> responseString)
//            throws IOException, JsonParseException, JsonMappingException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        return mapper.readValue(responseString.getBody(), new TypeReference<User>() {
//        });
//    }
//    @Data
//    private class UserResponse{
//        private List<User> users;
//    }
//}
