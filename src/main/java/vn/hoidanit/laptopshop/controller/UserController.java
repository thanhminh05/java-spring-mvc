package vn.hoidanit.laptopshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/admin/user")
    public String getUserListPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    @RequestMapping(value = "/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "admin/user/show";
    }
    
    @RequestMapping(value = "/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String handleCreateUser(Model model, @ModelAttribute("newData") User newData) {
        this.userService.handleCreateUser(newData);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/update/{id}", method = RequestMethod.GET)
    public String getUpdateUser(Model model,  @PathVariable long id) {
        User data = this.userService.getUserById(id);
        model.addAttribute("data", data);
        return "/admin/user/update";
    }

    @RequestMapping(value = "/admin/user/update/{id}", method = RequestMethod.POST)
    public String handleUpdateUser(Model model, @ModelAttribute("newData") User newData) {
        this.userService.handleUpdateUser(newData);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/delete/{id}")
    public String handleDeleteUser(@PathVariable long id) {
        this.userService.handleDeleteUser(id);
        return "redirect:/admin/user";
    }
}
