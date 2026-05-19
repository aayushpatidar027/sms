package com.example.sms.mapper;

import com.example.sms.dto.ContactDTO;
import com.example.sms.entity.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactMapper {

    public Contact toEntity(ContactDTO contactDTO) {
      if(contactDTO == null) {
          return null;
      }

        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setEmail(contactDTO.getEmail());
        return contact;
    }


    public ContactDTO toDTO(Contact contact) {
        if(contact == null) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setPhoneNumber(contact.getPhoneNumber());
        contactDTO.setCreateAt(contact.getCreateAt());
        contactDTO.setUpdateAt(contact.getUpdateAt());
        contactDTO.setEmail(contact.getEmail());
        if(contact.getUser() != null) {
            contactDTO.setUserId(contact.getUser().getId());
        }
        return contactDTO;
    }
}
