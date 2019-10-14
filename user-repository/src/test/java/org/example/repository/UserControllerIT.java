//package org.example.repository;
//
//import javax.annotation.Resource;
//
//import org.example.repository.config.ApplicationConfig;
//import org.example.repository.user.Role;
//import org.example.repository.user.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextHierarchy;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import lombok.extern.log4j.Log4j2;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserRepositoryApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
//@ContextHierarchy(@ContextConfiguration(classes = ApplicationConfig.class))
//@EnableFeignClients(basePackages = "org.example.repository")
////@ActiveProfiles("local")
//@Log4j2
//public class UserControllerIT {
//    @Resource
//    UserClient userClient;
//    @Test
//    public void users() {
//        User[] users = userClient.findAll();
//        Assert.assertNotNull(users);
//        Assert.assertEquals("admin", users[0].getUsername());
//    }
//    @Test
//    public void userByUsername() {
//        User user = userClient.findByUsername("admin");
//        Assert.assertNotNull(user);
//        Assert.assertEquals("admin", user.getUsername());
//    }
//    @Test
//    public void findById() {
//        User user = userClient.findById(1L);
//        Assert.assertNotNull(user);
//        Assert.assertEquals("admin", user.getUsername());
//    }
//    @Test
//    public void create() {
//        User user = userClient.update(new User("user","user","email", Role.ROLE_USER));
//        Assert.assertNotNull(user);
//        Assert.assertEquals("user", user.getUsername());
//        user = userClient.findById(user.getId());
//        Assert.assertNotNull(user);
//        userClient.delete(user.getId());
//        user = userClient.findById(user.getId());
//        Assert.assertNull(user);
//    }
//    @Test
//    public void count() {
//        Assert.assertEquals(1L, userClient.count());
//    }
//}
