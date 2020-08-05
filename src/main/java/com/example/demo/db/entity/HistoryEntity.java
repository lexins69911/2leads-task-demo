package com.example.demo.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HISTORY")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_ip")
    private String userIp;

    @Column(name = "ACTION_DATE")
    private Date actionDate;

    @Column(name = "IS_LOGIN")
    private boolean isLogin;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public HistoryEntity() {
    }

    public HistoryEntity(long id, String userIp, Date actionDate, boolean isLogin, UserEntity user) {
        this.id = id;
        this.userIp = userIp;
        this.actionDate = actionDate;
        this.isLogin = isLogin;
        this.user = user;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
