package com.example.demo.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOGIN_ATTEMPT")
public class LoginAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_ip")
    private String userIp;

    @Column(name = "CURRENT_ATTEMPTS_COUNT")
    private Integer currentAttemptsCount;

    @Column(name = "LAST_ATTEMPT_DATE")
    private Date lastAttemptDate;

    @Column(name = "BAN_DATE")
    private Date banDate;

    public LoginAttemptEntity() {}

    public LoginAttemptEntity(String userIp) {
        this.userIp = userIp;
        currentAttemptsCount = 1;
        lastAttemptDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Integer getCurrentAttemptsCount() {
        return currentAttemptsCount;
    }

    public void setCurrentAttemptsCount(Integer currentAttemptsCount) {
        this.currentAttemptsCount = currentAttemptsCount;
    }

    public Date getLastAttemptDate() {
        return lastAttemptDate;
    }

    public void setLastAttemptDate(Date lastAttemptDate) {
        this.lastAttemptDate = lastAttemptDate;
    }

    public Date getBanDate() {
        return banDate;
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate;
    }
}
