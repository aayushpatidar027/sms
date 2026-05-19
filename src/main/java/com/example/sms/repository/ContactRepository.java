package com.example.sms.repository;

import com.example.sms.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
     boolean existsByPhoneNumber(String phoneNumber);
}
