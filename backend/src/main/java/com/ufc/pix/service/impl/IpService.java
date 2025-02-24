package com.ufc.pix.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class IpService {

    private static final String IPIFY_URL = "https://api.ipify.org?format=json";

    public String getPublicIp() {
        RestTemplate restTemplate = new RestTemplate();
        IpResponse response = restTemplate.getForObject(IPIFY_URL, IpResponse.class);
        return response != null ? response.getIp() : "Unknown IP";
    }

    public Map<String, Object> getLocationFromIp(String ipAddress) {
        RestTemplate restTemplate = new RestTemplate();
        String token = System.getenv("IPINFO_API_TOKEN");

        String url = "https://ipinfo.io/" + ipAddress + "/json?token=" + token; // Substitua pelo seu token real

        return restTemplate.getForObject(url, Map.class);
    }

    private static class IpResponse {
        private String ip;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
