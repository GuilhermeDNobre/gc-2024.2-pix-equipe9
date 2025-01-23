package com.ufc.pix.service;

import com.ufc.pix.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatementService {
    @Autowired
    private StatementRepository statementRepository;

}
