package com.ufc.pix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginActivityDto {
    private UUID id;
    private UUID userId;
    private String ipAddress;
    private String city;
    private String region;
    private String country;
    private String org;
    private String postal;
    private String timezone;
    private LocalDateTime loginAt;
}