package com.ufc.pix.service.impl;

import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.model.User;
import com.ufc.pix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(CreateUserDto userDto) {
        if(this.findByEmail(userDto.getEmail()) != null){
            throw new RuntimeException("User already has a registered account");
        }


        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return userRepository.save(userDto.toModel());
    }

    @Override
    public UserDetails findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
