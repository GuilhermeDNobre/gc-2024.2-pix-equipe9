package com.ufc.pix.service.impl;

import com.ufc.pix.dto.ViewUserDto;
import com.ufc.pix.dto.UpdateUserDto;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.model.User;
import com.ufc.pix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public User create(CreateUserDto userDto) {

        if (this.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("There is already a user with this email");
        }

        if (this.userRepository.findByCpf(userDto.getCpf()).isPresent()){
            throw new BusinessException("There is already a user with this cpf");
        }

        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        var user = userDto.toModel();
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public UserDetails findByEmail(String email) {

        return this.userRepository.findByEmail(email).orElseThrow(()->
            new BusinessException("User not found",HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Optional<ViewUserDto> findById(UUID userId) {
        return this.userRepository.findById(userId)
                .map(ViewUserDto::fromUser);
    }


    @Override
    public void update(UUID userId, UpdateUserDto userDto) {
        var user = this.userRepository.findById(userId).orElseThrow(
                () -> new BusinessException("User not found", HttpStatus.NOT_FOUND)
        );


        if (userDto.name() != null) {
            user.setName(userDto.name());
        }

        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }

        if (userDto.cpf() != null) {
            user.setCpf(userDto.cpf());
        }

        if (userDto.birthDate() != null) {
            user.setBirthDate(userDto.birthDate());
        }

        userRepository.save(user);
    }

    @Override
    public void delete(UUID userId) {
        var user = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found", HttpStatus.NOT_FOUND));
        user.setActive(false);
    }
}
