package vn.hoidanit.laptopshop.controller;

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
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("eric", test);
        model.addAttribute("hoidanit", "hoidanit value");
        return "hello";
    }
    
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("hoidanit", "hoidanit value");
        return "admin/user/create";
    }
    
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User newUser) {
        System.out.println("Debug: " + newUser);
        return "hello";
    }
}
