package com.example.sms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactDTO {
    private Long UserId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
