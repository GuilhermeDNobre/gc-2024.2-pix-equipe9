package com.ufc.pix.repository;

import com.ufc.pix.model.LoginActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LoginActivityRepository extends JpaRepository<LoginActivity, UUID> {
    List<LoginActivity> findAllByUserId(UUID userId);
}
