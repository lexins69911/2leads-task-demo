package com.example.demo.controller;

import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("message", "Такая комбинация логина и пароля не найдена");
        }

        return "login";
    }

//    @PutMapping("/login")
//    public String tryLogin(UserEntity user, Map<String, Object> model) {
//
//        UserEntity userFromDB = userRepository.findByEmail(user.getEmail());
//
//        if (userFromDB == null || userFromDB.getPassword()!=user.getPassword()) {
//            model.put("message", "Такая комбинация логина и пароля не найдена");
//            return "login";
//        }
//        return "redirect:/home";
//    }
}
