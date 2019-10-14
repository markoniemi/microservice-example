package org.example.repository.controller;

import javax.annotation.Resource;

import org.example.repository.user.User;
import org.example.repository.user.UserService;
import org.example.repository.user.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest")
public class UserController extends UserServiceImpl {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User[] findAll() {
        return super.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
        return super.create(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {
        return super.create(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Long id) {
        super.delete(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable("id") Long id) {
        return super.findById(id);
    }

    @RequestMapping(value = "/users/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findByUsername(@PathVariable("username") String username) {
        return super.findByUsername(username);
    }

    @RequestMapping(value = "/users/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findByEmail(@PathVariable("email") String email) {
        return super.findByEmail(email);
    }

    @RequestMapping(value = "/users/exists/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean exists(@PathVariable("id") Long id) {
        return super.exists(id);
    }

    @RequestMapping(value = "/users/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public long count() {
        return super.count();
    }
}
