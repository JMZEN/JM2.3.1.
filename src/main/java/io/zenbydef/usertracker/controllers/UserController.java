package io.zenbydef.usertracker.controllers;

import io.zenbydef.usertracker.entities.User;
import io.zenbydef.usertracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "tablelist";
    }
}
