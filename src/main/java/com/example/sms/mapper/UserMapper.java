package com.example.sms.mapper;

import com.example.sms.dto.UserDTO;
import com.example.sms.entity.Users;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMapper {

    public UserDTO toDTO(Users user) {

        log.info("toDTO method");
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setActive(user.isActive());
        return userDTO;
    }

    public Users toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        Users user = new Users();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.isActive());
        return user;
    }
}