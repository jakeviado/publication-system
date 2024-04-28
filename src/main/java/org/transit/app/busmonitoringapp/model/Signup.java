package org.transit.app.busmonitoringapp.model;

import org.transit.app.busmonitoringapp.interfaces.SignupInterface;

public class Signup implements SignupInterface {
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;

    @Override
    public String username() {
        return getUsername();
    }

    @Override
    public String password() {
        return getPassword();
    }

    @Override
    public String email() {
        return getEmail();
    }

    @Override
    public String first_name() {
        return getFirst_name();
    }

    @Override
    public String last_name() {
        return getLast_name();
    }


    public Signup(String username, String password, String email, String first_name, String last_name ){
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Signup() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
