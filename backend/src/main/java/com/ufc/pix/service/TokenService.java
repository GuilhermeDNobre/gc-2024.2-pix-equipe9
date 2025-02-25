package com.ufc.pix.service;

import com.ufc.pix.model.User;

public interface TokenService {

    public String generateToken(User user);

    String validateToken(String token);

    User getUserFromToken(String token);

    void invalidateToken(String token);

}
