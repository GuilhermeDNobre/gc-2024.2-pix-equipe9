package com.ufc.pix.service;

import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.dto.GetUserInfoDto;
import com.ufc.pix.dto.UpdateUserDto;
import com.ufc.pix.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser(CreateUserDto userDto);

    UserDetails findByEmail(String email);

    Optional<GetUserInfoDto> getUserById(UUID userId);

    void updateUser(UUID userId, UpdateUserDto userDto);

    void deleteUser(UUID userId);
}
