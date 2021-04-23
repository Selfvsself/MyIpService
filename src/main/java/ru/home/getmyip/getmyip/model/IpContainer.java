package ru.home.getmyip.getmyip.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class IpContainer {

    private String ip;

    public IpContainer(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
