package com.example.sms.mapper;

import com.example.sms.dto.AddressDTO;
import com.example.sms.dto.ContactDTO;
import com.example.sms.dto.UserDTO;
import com.example.sms.entity.Address;
import com.example.sms.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserMapper {

    public UserDTO toDTO(User user) {

        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setActive(user.isActive());
        userDTO.setCreateAt(user.getCreateAt());
        userDTO.setUpdateAt(user.getUpdateAt());

        List<AddressDTO> addressDTOList = new ArrayList<>();

        if (user.getAddress() != null) {
            user.getAddress().forEach(address -> {
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setStreet(address.getStreet());
                addressDTO.setCity(address.getCity());
                addressDTO.setState(address.getState());
                addressDTO.setCountry(address.getCountry());
                addressDTOList.add(addressDTO);
            });
        }
        userDTO.setAddress(addressDTOList);


        List<String> conList=new ArrayList<>();
        if(user.getContact()!=null)
        {
            user.getContact().forEach(contact->{
               StringBuilder s=new StringBuilder();
               s.append(contact.getFirstName()+" = ");
                s.append(contact.getPhoneNumber());
                conList.add(s.toString());
            });
        }
        userDTO.setContact(conList);

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        if ( userDTO.getAddress() != null) {
            List<Address> addressList = new ArrayList<>();
            for (AddressDTO addressDTO :  userDTO.getAddress()) {
                Address address = new Address();
                address.setStreet(addressDTO.getStreet());
                address.setCity(addressDTO.getCity());
                address.setState(addressDTO.getState());
                address.setCountry(addressDTO.getCountry());
                address.setUser(user);
                addressList.add(address);
            }
            user.setAddress(addressList);
        }





        return user;
    }
}