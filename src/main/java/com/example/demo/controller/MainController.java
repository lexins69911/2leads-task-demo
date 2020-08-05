package com.example.demo.controller;

import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getHomePage(Principal principal, Model model) {

        List<UserEntity> all = userRepository.findAllByActive(true);
        model.addAttribute("allUsers", all);
        model.addAttribute("countOfAllUsers", all.size());
        model.addAttribute("principal", principal);
        return "home";

    }
}
