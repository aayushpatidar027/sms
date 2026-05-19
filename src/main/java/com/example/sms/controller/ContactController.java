package com.example.sms.controller;

import com.example.sms.dto.ContactDTO;
import com.example.sms.service.impl.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ContactDTO>> getAllContacts()
        {
            return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
        }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) {
            return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {

        return new ResponseEntity<>(contactService.createContact(contactDTO), HttpStatus.CREATED);

        }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id,@RequestBody ContactDTO contactDTO) {
        return new ResponseEntity<>(contactService.updateContact(id, contactDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
