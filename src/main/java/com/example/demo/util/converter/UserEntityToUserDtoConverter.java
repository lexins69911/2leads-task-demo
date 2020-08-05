package com.example.demo.util.converter;

import com.example.demo.db.dto.UserDto;
import com.example.demo.db.entity.UserEntity;

public class UserEntityToUserDtoConverter {

    private UserEntity userEntity;

    public UserEntityToUserDtoConverter(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserDto convert() {
        UserDto userDto = new UserDto();
        if (getUserEntity()!=null) {
            userDto.setRegistrationDate(getUserEntity().getRegistrationDate());
            userDto.setPassword(getUserEntity().getPassword());
            userDto.setName(getUserEntity().getName());
            userDto.setId(getUserEntity().getId());
            userDto.setEmail(getUserEntity().getEmail());
            userDto.setActive(getUserEntity().isActive());
        }
        return userDto;
    }
}
