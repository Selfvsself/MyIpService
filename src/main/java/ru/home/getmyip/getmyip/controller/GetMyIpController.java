package ru.home.getmyip.getmyip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.home.getmyip.getmyip.model.IpContainer;
import ru.home.getmyip.getmyip.service.EnvironmentService;

@RestController
public class GetMyIpController {

    private final EnvironmentService envService;

    public GetMyIpController(EnvironmentService envService) {
        this.envService = envService;
    }

    @GetMapping
    public IpContainer getIp() {
        String ip = getMyIp(envService.getBaseUrl());
        return new IpContainer(ip);
    }

    private String getMyIp(String baseUrl) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(baseUrl, String.class);
        if (response != null) {
            response = response.replace("\n", "");
        } else {
            response = "0.0.0.0";
        }
        return response;
    }
}
