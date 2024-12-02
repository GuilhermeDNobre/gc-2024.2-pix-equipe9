package com.ufc.pix.repository;

import com.ufc.pix.dto.GetUserInfoDto;
import com.ufc.pix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

    GetUserInfoDto findUserById(UUID id);
}
