package com.example.sms.service.impl;

import com.example.sms.dto.ContactDTO;
import com.example.sms.entity.Contact;
import com.example.sms.entity.User;
import com.example.sms.exception.AlreadyExistException;
import com.example.sms.exception.NotFoundException;
import com.example.sms.mapper.ContactMapper;
import com.example.sms.repository.ContactRepository;
import com.example.sms.repository.UserRepository;
import com.example.sms.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService implements IContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository  userRepository;
    @Autowired
    ContactMapper contactMapper;

    @Override
    public ContactDTO createContact(ContactDTO contactDTO) {
        User user = userRepository.findById(contactDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + contactDTO.getUserId()));


        Contact entity = contactMapper.toEntity(contactDTO);
        entity.setCreateAt(LocalDateTime.now());
        entity.setUpdateAt(LocalDateTime.now());
        entity.setUser(user);
        Contact savedContact = contactRepository.save(entity);
        return contactMapper.toDTO(savedContact);

        //return contactMapper.toDTO(contactRepository.save(contactMapper.toEntity(contactDTO)));
    }

    @Override
    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contact not found with id: " + id));
        return contactMapper.toDTO(contact);
    }

    @Override
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contact not found with id: " + id));

        if (!existingContact.getPhoneNumber().equals(contactDTO.getPhoneNumber()) &&
                contactRepository.existsByPhoneNumber(contactDTO.getPhoneNumber())) {
            throw new AlreadyExistException("Phone number already exists");
        }

        existingContact.setFirstName(contactDTO.getFirstName());
        existingContact.setLastName(contactDTO.getLastName());
        existingContact.setPhoneNumber(contactDTO.getPhoneNumber());
        existingContact.setEmail(contactDTO.getEmail());
        existingContact.setUpdateAt(LocalDateTime.now());

        Contact updatedContact = contactRepository.save(existingContact);
        return contactMapper.toDTO(updatedContact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contact not found with id: " + id));
        contactRepository.delete(contact);
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactDTO> contactDTOs = new ArrayList<>();
        for (Contact contact : contacts) {
            contactDTOs.add(contactMapper.toDTO(contact));
        }
    return  contactDTOs;
    }

}
