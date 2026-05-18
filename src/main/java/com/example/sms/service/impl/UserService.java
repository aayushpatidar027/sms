package com.example.sms.service.impl;

import com.example.sms.dto.UserDTO;
import com.example.sms.entity.User;
import com.example.sms.exception.AlreadyExistException;
import com.example.sms.exception.NotFoundException;
import com.example.sms.mapper.UserMapper;
import com.example.sms.repository.UserRepository;
import com.example.sms.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new AlreadyExistException("User with email " + userDTO.getEmail() + " already exists");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new AlreadyExistException("User with username " + userDTO.getUsername() + " already exists");
        }

        User entity = userMapper.toEntity(userDTO);

            entity.setCreateAt(LocalDateTime.now());
            entity.setUpdateAt(LocalDateTime.now());
            entity.setActive(true);

        User savedUser = userRepository.save(entity);
           return userMapper.toDTO(savedUser);

    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));



//        if(!user.isActive()) {
//            throw new NotFoundException("User with id " + id + " is inactive");
//        }

        UserDTO dto = userMapper.toDTO(user);
        return dto;
    }

    @Override
    public List<UserDTO> getAllUsers() {
            List<User> users = userRepository.findAll();
//            List<UserDTO> userDTOs = users.stream()
//                    .filter(User::isActive) // Filter only active users
//                    .map(userMapper::toDTO)
//                    .toList();

        List<UserDTO> userDTOs = users.stream()
                  // .filter(u-> u.isActive()) // Filter only active users
                    .map(u-> userMapper.toDTO(u))
                    .toList();


//        List<UserDTO> userDTOs = new ArrayList<>();
//        for (User user : users) {
//            if (user.isActive()) {
//                UserDTO dto = userMapper.toDTO(user);
//                userDTOs.add(dto);
//            }
//        }
        return userDTOs;


    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.getIsActive());
        user.setUpdateAt(LocalDateTime.now());
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }
}
