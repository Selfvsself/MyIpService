package ru.home.getmyip.getmyip.service;

import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
public class EnvironmentService {

    private final String baseUrl;

    private final UserDetails userDetails;

    public EnvironmentService(Environment environment) {
        baseUrl = environment.getProperty("base.url");
        String credential = new String(
                Base64.getDecoder().decode(environment.getProperty("credential")));
        userDetails = parseUserDetails(credential);
    }

    private UserDetails parseUserDetails(String credential) {
        UserDetails userDetails;
        if (credential.contains(":")) {
            String[] loginAndPassword = credential.split(":");
            userDetails = new User(loginAndPassword[0], loginAndPassword[1], new ArrayList<>());
        } else {
            throw new IllegalArgumentException("User credential is not provide");
        }
        return userDetails;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
