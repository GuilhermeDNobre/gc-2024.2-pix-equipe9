package com.ufc.pix.service;

import com.ufc.pix.dto.*;
import com.ufc.pix.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;
import java.util.UUID;
import java.util.List;


public interface UserService {

    User create(CreateUserDto userDto);

    Optional<ViewUserDto> findById(UUID userId);

    void update(UUID userId, UpdateUserDto userDto);

    void delete(UUID userId);

    void block(UUID id, String authorizationHeader);

    void unblock(UUID id);

    List<User> list(SearchUserDto dto);

    Token login(LoginDto loginDto);
}
