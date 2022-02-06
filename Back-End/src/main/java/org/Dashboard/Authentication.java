package org.Dashboard;

public class Authentication {
    private String userEmail;
    private String token;

    public Authentication(){
        this.userEmail="@gmail.com";
        this.token="qf1U398F44";
    }

    public String getUserEmail(){
        return userEmail;
    }
    public String getToken(){
        return token;
    }
}
