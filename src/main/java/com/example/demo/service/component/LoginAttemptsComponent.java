package com.example.demo.service.component;

public interface LoginAttemptsComponent {

    Integer commitNewAttempt(String userIp);

    boolean isExtraAttempt(String userIp);

    void ban(String userIp);

}
