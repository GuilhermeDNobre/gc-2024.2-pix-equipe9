package com.ufc.pix.controller;

import com.ufc.pix.service.LoginActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login-activity")
public class LoginActivityController {
    @Autowired
    private LoginActivityService loginActivityService;
}