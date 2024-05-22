package vn.hoidanit.laptopshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



// MVC
@Controller
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<User> data = this.userService.getAllUsers();
        model.addAttribute("data", data);
        return "home";
    }

    @GetMapping(value = "/admin/user")
    public String getListUser(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    @GetMapping(value = "/admin/user/{id}")
    public String getDetailUser(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "admin/user/show";
    }
    
    @GetMapping(value = "/admin/user/create")
    public String getCreateUser(Model model) {
        model.addAttribute("newData", new User());
        return "admin/user/create";
    }
    
    @PostMapping(value = "/admin/user/create")
    public String handleCreateUser(Model model, @ModelAttribute("newData") User newData) {
        this.userService.handleCreateUser(newData);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/update/{id}")
    public String getUpdateUser(Model model,  @PathVariable long id) {
        User data = this.userService.getUserById(id);
        model.addAttribute("data", data);
        return "/admin/user/update";
    }

    @PostMapping(value = "/admin/user/update/{id}")
    public String handleUpdateUser(Model model, @ModelAttribute("newData") User newData) {
        this.userService.handleUpdateUser(newData);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/delete/{id}")
    public String getDeleteUser(Model model, @PathVariable long id) {
        User user = new User();
        user.setId(id);
        model.addAttribute("newData", user);
        return "/admin/user/delete";
    }

    @PostMapping(value = "/admin/user/delete/{id}")
    public String handleDeleteUser(Model model, @ModelAttribute("newData") User newData) {
        this.userService.handleDeleteUser(newData.getId());
        return "redirect:/admin/user";
    }
}
