package org.transit.app.newspaperapp.model;

import org.transit.app.newspaperapp.interfaces.login;

public class Login implements login {
    private String username;
    private String password;

    @Override
    public String username() {
        return getUsername();
    }

    @Override
    public String password() {
        return getPassword();
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userID) {
        this.username = userID;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

