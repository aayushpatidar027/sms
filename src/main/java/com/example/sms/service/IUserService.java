package com.example.sms.service;

import com.example.sms.dto.UserDTO;
import com.example.sms.entity.User;

import java.util.List;

public interface IUserService{
    UserDTO createUser(UserDTO user);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);

}
