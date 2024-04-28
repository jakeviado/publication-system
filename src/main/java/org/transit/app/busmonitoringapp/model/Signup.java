package org.transit.app.busmonitoringapp.model;

import org.transit.app.busmonitoringapp.interfaces.SignupInterface;

public class Signup implements SignupInterface {
    private String username;
    private String password;
    private String name;

    @Override
    public String username() {
        return getUsername();
    }

    @Override
    public String password() {
        return getPassword();
    }

    @Override
    public String name() {
        return getPersonName();
    }

    public Signup(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Signup() {
    }

    public String getPersonName() { return name; }
    public void setPersonName(String name) { this.name = name; }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
