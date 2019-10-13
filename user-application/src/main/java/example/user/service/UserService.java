package example.user.service;

import javax.jws.WebParam;
import javax.websocket.server.PathParam;

import org.example.repository.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Service
// TODO get url from discovery service
@FeignClient(name = "User", url = "http://localhost:8082/api/rest")
public interface UserService extends org.example.repository.user.UserService {

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    User[] findAll();

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    User create(@WebParam(name = "user") User user);

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    User update(@WebParam(name = "user") User user);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/username/{username}")
    User findByUsername(@PathParam("username") @WebParam(name = "username") String username);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User findById(@PathParam("id") @WebParam(name = "id") Long id);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/email/{email}")
    User findByEmail(@PathParam("email") @WebParam(name = "email") String email);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/exists/{id}")
    boolean exists(@PathParam("id") @WebParam(name = "id") Long id);

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    void delete(@PathParam("id") @WebParam(name = "id") Long id);

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/users/count")
    long count();
}
