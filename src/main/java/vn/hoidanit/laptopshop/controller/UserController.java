package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



// MVC
@Controller
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> data = this.userService.getAllUsers();
        model.addAttribute("data", data);
        return "home";
    }
    
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("hoidanit", "hoidanit value");
        return "admin/user/create";
    }
    
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public User createUserPage(Model model, @ModelAttribute("newUser") User newData) {
        return this.userService.handleSaveUser(newData);
    }
}
