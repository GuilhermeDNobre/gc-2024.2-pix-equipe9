package com.ufc.pix.service;

import com.ufc.pix.model.LoginActivity;

import java.util.List;
import java.util.UUID;

public interface LoginActivityService {
    void registerLogin(UUID userId);
    //List<LoginActivity> listAllByUserId(UUID userId);
}
