package com.example.clinic.model;

public class User {
    private Integer userId;
    private String login;
    private String password;
    private String fullName;
    private String role;

    public User(Integer userId, String login, String password, String fullName, String role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public User(String login, String password, String fullName) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
