package com.ufc.pix.service;

import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    User createUser(CreateUserDto userDto);

    UserDetails findByEmail(String email);

}
