package com.ufc.pix.repository;

import com.ufc.pix.enumeration.KeyType;
import com.ufc.pix.model.PixKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PixKeyRepository extends JpaRepository<PixKey, UUID> {

    Optional<PixKey> findByKeyValue(String key);

    Optional<PixKey> findByAccount_IdAndType(UUID id, KeyType type);


}
