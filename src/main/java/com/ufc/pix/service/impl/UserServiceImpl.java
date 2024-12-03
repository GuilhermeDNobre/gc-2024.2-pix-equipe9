package com.ufc.pix.service.impl;

import com.ufc.pix.dto.GetUserInfoDto;
import com.ufc.pix.dto.UpdateUserDto;
import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.model.User;
import com.ufc.pix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<GetUserInfoDto> getUserById(UUID userId) {
        return this.userRepository.findById(userId)
                .map(GetUserInfoDto::fromUser);
    }


    @Override
    public void updateUser(UUID userId, UpdateUserDto userDto) {
        var userEntity = this.userRepository.findById(userId);
        if(userEntity.isPresent()){
            var user = userEntity.get();

            if (userDto.name() != null) {
                user.setName(userDto.name());
            }

            if (userDto.email() != null) {
                user.setEmail(userDto.email());
            }

            if(userDto.cpf() != null) {
                user.setCpf(userDto.cpf());
            }

            if (userDto.birthDate() != null) {
                user.setBirthDate(userDto.birthDate());
            }

            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(UUID userId) {
        var userExists = this.userRepository.existsById(userId);

        if(userExists){
            this.userRepository.deleteById(userId);
        }
    }
}
