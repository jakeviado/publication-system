package org.transit.app.newspaperapp.model;

import org.transit.app.newspaperapp.interfaces.register;

public class Signup implements register {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    private static Signup registeredUserInfo;

    public static void setLoggedInUser(Signup user) {
        registeredUserInfo = user;
    }

    public static Signup getLoggedInUser() {
        return registeredUserInfo;
    }

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
        return getFirstName();
    }

    @Override
    public String last_name() {
        return getLastName();
    }

    public Signup(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Signup() {}

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
