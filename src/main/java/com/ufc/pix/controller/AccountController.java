package com.ufc.pix.controller;

import com.ufc.pix.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
}
