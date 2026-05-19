package com.example.sms.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private List<AddressDTO> address;
}
