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
    public ModelAndView addUser() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("user-form");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @PostMapping("/updateuser")
    public ModelAndView showFormForUpdate(@RequestParam("userId") int userId) {
        User user = userService.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView("user-form");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/users/list";
    }

    @GetMapping("/searchuser")
    public ModelAndView searchUser(@RequestParam("theSearchName") String theSearchName) {
        List<User> userList;
        if (theSearchName == null) {
            userList = new ArrayList<>();
        } else {
            userList = userService.searchUsers(theSearchName);
        }
        ModelAndView modelAndView = new ModelAndView("user-search-table");
        modelAndView.addObject("users", userList);
        return modelAndView;
    }
}
