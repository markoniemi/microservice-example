package example.user.service;

import org.example.repository.user.User;
import org.example.repository.user.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "User", url = "http://localhost:8082/api/rest")
public interface UserClient extends UserService {

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    User[] findAll();

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    User create(@RequestBody User user);

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    User update(@RequestBody User user);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/username/{username}")
    User findByUsername(@PathVariable("username") String username);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User findById(@PathVariable("id") Long id);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/email/{email}")
    User findByEmail(@PathVariable("email") String email);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/exists/{id}")
    boolean exists(@PathVariable("id") Long id);

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    void delete(@PathVariable("id") Long id);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/count")
    long count();
}
