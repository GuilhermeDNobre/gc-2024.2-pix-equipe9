package com.ufc.pix.repository;

import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.model.PixKey;
import com.ufc.pix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PixKeyRepository extends JpaRepository<PixKey, UUID> {
    Optional<PixKey> findByKey(String key);
    List<PixKey> findByUserId(UUID userId);
    boolean existsByUserAndType(User user, KeyType type);

}
