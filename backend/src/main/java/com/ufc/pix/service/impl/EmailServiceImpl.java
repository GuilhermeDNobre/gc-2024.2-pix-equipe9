package com.ufc.pix.service.impl;

import com.ufc.pix.Observer.EmailObserver;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService, EmailObserver {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String message) {
        if (username.isBlank()) return;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom(username);
        mailSender.send(email);
    }

    @Override
    public void update(String to, String subject, String message) {
        sendEmail(to, subject, message);
    }
}
