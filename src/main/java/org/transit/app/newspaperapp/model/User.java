package org.transit.app.newspaperapp.model;

import org.transit.app.newspaperapp.interfaces.UserInterface;

public class User implements UserInterface {
    private String username;
    private String password;
    private static User loggedInUser;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
