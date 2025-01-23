package com.ufc.pix.repository;

import com.ufc.pix.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatementRepository extends JpaRepository<Statement, UUID> {
    Statement findStatementById(UUID id);
}
