package com.ufc.pix.service;

import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.dto.ViewUserDto;
import com.ufc.pix.dto.UpdateUserDto;
import com.ufc.pix.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User create(CreateUserDto userDto);

    UserDetails findByEmail(String email);

    Optional<ViewUserDto> findById(UUID userId);

    void update(UUID userId, UpdateUserDto userDto);

    void delete(UUID userId);
}
