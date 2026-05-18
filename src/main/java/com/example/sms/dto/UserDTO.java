package com.example.sms.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
