package com.ufc.pix.service.impl;

import com.ufc.pix.dto.LoginActivityDto;
import com.ufc.pix.dto.ViewGenerateReportsDto;
import com.ufc.pix.dto.ViewLoginActivityDto;
import com.ufc.pix.model.LoginActivity;
import com.ufc.pix.model.User;
import com.ufc.pix.repository.LoginActivityRepository;
import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.service.LoginActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @Override
    public Optional<ViewLoginActivityDto> listAllByUserId(UUID userId) {
        List<LoginActivity> loginActivities = loginActivityRepository.findAllByUserId(userId);

        if (loginActivities.isEmpty()) {
            return Optional.empty();
        }

        List<LoginActivityDto> loginActivityDtos = loginActivities.stream()
                .map(activity -> new LoginActivityDto(
                        activity.getId(),
                        activity.getUser().getId(),
                        activity.getIp_address(),
                        activity.getCity(),
                        activity.getRegion(),
                        activity.getCountry(),
                        activity.getOrg(),
                        activity.getPostal(),
                        activity.getTimezone(),
                        activity.getLogin_at()
                ))
                .toList();

        return Optional.of(new ViewLoginActivityDto(loginActivityDtos));
    }
}
