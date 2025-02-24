package com.ufc.pix.repository;



import com.ufc.pix.model.UnlockRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnlockRequestRepository extends JpaRepository<UnlockRequest, UUID> {

}
