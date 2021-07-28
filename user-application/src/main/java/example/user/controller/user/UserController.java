package example.user.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import example.repository.user.Role;
import example.repository.user.User;
import example.user.controller.UserValidator;
import example.user.service.UserClient;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {
    @Autowired
    private MessageSource messageSource;
    @Resource
    private UserClient userService;
    @Resource
    private UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @PostMapping(value = "/user/save")
    public ModelAndView saveUser(@ModelAttribute @Validated User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        model.addObject("roles", getRolesAsMap());
        model.setViewName("/user/user");
        if (bindingResult.hasErrors()) {
            return model;
        }
        try {
            if (user.getId() != null) {
                userService.update(user);
            } else {
                userService.create(user);
            }
        } catch (Exception e) {
            bindingResult.reject(e.getMessage());
            return model;
        }
        model.setViewName("redirect:/user/users");
        return model;
    }

    @GetMapping(value = "/user/new")
    public ModelAndView newUser() {
        return editUser(null);
    }

    @GetMapping(value = "/user/{username}")
    public ModelAndView editUser(@PathVariable String username) {
        User user = null;
        if (StringUtils.isNotBlank(username)) {
            user = userService.findByUsername(username);
            log.debug("editUser() - found user: " + user);
        }
        if (user == null) {
            user = new User();
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("/user/user");
        model.addObject("user", user);
        model.addObject("roles", getRolesAsMap());
        return model;
    }

    @PostMapping(value = "/user/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.delete(userService.findByUsername(username).getId());
        return "redirect:/user/users";
    }

    private Map<String, String> getRolesAsMap() {
        Map<String, String> roleMap = new HashMap<>();
        Role[] roles = Role.values();
        for (Role role : roles) {
            roleMap.put(role.name(), messageSource.getMessage(role.name(), null, null));
        }
        return roleMap;
    }
}
