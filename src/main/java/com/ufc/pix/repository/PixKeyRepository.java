package com.ufc.pix.repository;

import com.ufc.pix.model.PixKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PixKeyRepository extends JpaRepository<PixKey, UUID> {
    Optional<PixKey> findByKey(String key);
}
