package io.zenbydef.usertracker.controllers;

import io.zenbydef.usertracker.entities.User;
import io.zenbydef.usertracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView listUsers() {
        List<User> userList = userService.getUsers();

        ModelAndView modelAndView = new ModelAndView("users-table");
        modelAndView.addObject("usersForTable", userList);
        return modelAndView;
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

    @PostMapping("/updateuser")
    public String showFormForUpdate(
            @RequestParam("userId") int userId,
            Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/users/list";
    }

    @GetMapping("/searchuser")
    public String searchUser(
            @RequestParam("theSearchName") String theSearchName,
            Model model) {
        List<User> userList;
        if (theSearchName == null) {
            userList = new ArrayList<>();
        } else {
            userList = userService.searchUsers(theSearchName);
        }
        model.addAttribute("users", userList);
        return "user-search-table";
    }
}
