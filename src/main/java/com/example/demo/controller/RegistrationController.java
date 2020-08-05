package com.example.demo.controller;

import com.example.demo.db.dto.UserDto;
import com.example.demo.db.entity.Role;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(userDto);
        return "register";
    }


    @PostMapping(value = "/registration")
    public String addNewUser(@ModelAttribute UserDto userDto, Map<String,Object> model) {


        UserEntity userFromDB = userRepository.findByEmail(userDto.getEmail());

        if (userFromDB!=null) {
            model.put("message", "Такой пользователь уже существует");
            return "register";
        }

        UserEntity user = new UserEntity();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setRegistrationDate(new Date());
        userRepository.save(user);

        return "redirect:/login";
    }
}
