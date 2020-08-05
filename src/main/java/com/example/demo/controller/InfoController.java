package com.example.demo.controller;


import com.example.demo.db.dto.UserDto;
import com.example.demo.db.entity.HistoryEntity;
import com.example.demo.db.entity.UserEntity;
import com.example.demo.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("info")
@RestController
public class InfoController {

    @Autowired
    UserRepository userRepository;


    @GetMapping()
    public List<UserDto> getAllUsers() {
        List<UserDto> all = new ArrayList<>();


        Collection<UserEntity> allFromDB =
                StreamSupport.stream(userRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());

        for (UserEntity userEntity: allFromDB) {
            all.add(convertToDto(userEntity));
        }
        return all;
    }

    @GetMapping("{id}")
    public UserDto getConcreteUser(@PathVariable long id) {
        UserEntity byId = userRepository.findById(id);
        return convertToDto(byId);
    }

    private UserDto convertToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        if (userEntity!=null) {
            userDto.setActive(userEntity.isActive());
            userDto.setEmail(userEntity.getEmail());
            userDto.setId(userEntity.getId());
            userDto.setName(userEntity.getName());
            userDto.setPassword(userEntity.getPassword());
            userDto.setRegistrationDate(userEntity.getRegistrationDate());
        }
        return userDto;
    }
}
