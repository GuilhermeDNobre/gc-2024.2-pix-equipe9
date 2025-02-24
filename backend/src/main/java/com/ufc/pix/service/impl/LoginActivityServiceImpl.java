package com.ufc.pix.service.impl;

import com.ufc.pix.model.LoginActivity;
import com.ufc.pix.model.User;
import com.ufc.pix.repository.LoginActivityRepository;
import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.service.LoginActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class LoginActivityServiceImpl implements LoginActivityService {

    @Autowired
    private IpService ipService;

    @Autowired
    private LoginActivityRepository loginActivityRepository;


    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerLogin(UUID userId) {
        String publicIp = ipService.getPublicIp();
        Map<String, Object> response = ipService.getLocationFromIp(publicIp);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        loginActivityRepository.save(new LoginActivity(user, publicIp, response));
        response.forEach((key, value) -> System.out.println(key + ": " + value));
    }

}
