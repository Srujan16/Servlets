package com.wavemaker.tutorial.searchContext.domain;


/**
 * Created by srujant on 19/7/16.
 */
public class User {

    private String emailId;
    private String password;
    private String username;

    public User() {
    }

    public User(String emailId, String password, String username) {
        this.emailId = emailId;
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
