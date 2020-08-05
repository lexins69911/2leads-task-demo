package com.example.demo.db.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name="REGISTRATION_DATE")
    private Date registrationDate;

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<HistoryEntity> HistoryEntitys = new HashSet<>();
//
//    public Set<HistoryEntity> getHistoryEntitys() {
//        return HistoryEntitys;
//    }
//
//    public void setHistoryEntitys(Set<HistoryEntity> histories) {
//        this.HistoryEntitys = histories;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserEntity() {
    }

    public UserEntity(long id, String email, String name, String password, boolean active, Date registrationDate, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.active = active;
        this.registrationDate = registrationDate;
        this.roles = roles;
//        this.HistoryEntitys = HistoryEntitys;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User: [id: %d; name: %s; email: %s]",
                id, name, email
        );
    }
}
