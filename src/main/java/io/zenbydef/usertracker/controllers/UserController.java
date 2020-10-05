package io.zenbydef.usertracker.controllers;

import io.zenbydef.usertracker.entities.User;
import io.zenbydef.usertracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> userList = userService.getUsers();

        model.addAttribute("usersForTable", userList);
        return "users-table";
    }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/updateuser")
    public String showFormForUpdate(
            @RequestParam("userId") int userId,
            Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("userForUpdate", user);
        return "user-form";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/users/list";
    }

    @GetMapping("/searchuser")
    public String searchUser(
            @RequestParam("theSearchName") String theSearchName,
            Model model) {
        List<User> userList = userService.searchUsers(theSearchName);
        model.addAttribute("users", userList);
        return "users-table";
    }
}
