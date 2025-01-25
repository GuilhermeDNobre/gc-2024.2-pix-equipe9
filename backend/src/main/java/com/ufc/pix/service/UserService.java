package com.ufc.pix.service;

import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.dto.SearchUserDto;
import com.ufc.pix.dto.ViewUserDto;
import com.ufc.pix.dto.UpdateUserDto;
import com.ufc.pix.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;
import java.util.UUID;
import java.util.List;


public interface UserService {

    User create(CreateUserDto userDto);

    UserDetails findByEmail(String email);

    Optional<ViewUserDto> findById(UUID userId);

    void update(UUID userId, UpdateUserDto userDto);

    void delete(UUID userId);

    void block(UUID id);

    List<User> list(SearchUserDto dto);
}
