package com.example.demo.db.dto;

import java.util.Date;

public class HistoryDto {

    private long id;
    private String userIp;
    private Date actionDate;
    private UserDto user;
    private boolean login;

    public HistoryDto() {
    }

    public HistoryDto(long id, String userIp, Date actionDate, UserDto user, boolean login) {
        this.id = id;
        this.userIp = userIp;
        this.actionDate = actionDate;
        this.user = user;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
