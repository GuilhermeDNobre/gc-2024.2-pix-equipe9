package com.ufc.pix.service;

public interface EmailService {
    void sendEmail(String forEmail, String subject, String message);
}
