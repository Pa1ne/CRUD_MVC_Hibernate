package web.controller;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import web.services.UserService;

import web.models.User;


@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("userId") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if (!user.getFirstName().isEmpty()
                && !user.getLastName().isEmpty()
                && !user.getCitizenship().isEmpty()) {
            userService.addOrEditUser(user);
            return "redirect:/";
        }
        return "user-info";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }
}
