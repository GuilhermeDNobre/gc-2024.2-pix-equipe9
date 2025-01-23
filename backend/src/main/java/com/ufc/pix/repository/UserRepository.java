package com.ufc.pix.repository;

import com.ufc.pix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    Optional<UserDetails> findByEmailAndActiveTrue(String email);
    Optional<UserDetails> findByEmail(String email);
    Optional<User> findByIdAndActiveTrue(UUID id);
    Optional<User> findByCpfAndActiveTrue(String cpf);
    Optional<User> findByCpf(String cpf);
}
