package org.transit.app.newspaperapp.model;

import java.util.HashSet;
import java.util.Set;

public class UserSession {
    private static UserSession instance;
    private User loggedInUser;
    private Set<Integer> roles;
    private int userId;
    private int roleId;

    private UserSession() {
        roles = new HashSet<>();
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Set<Integer> getRoles() {
        return roles;
    }

    public void setRoles(Set<Integer> roles) {
        this.roles = roles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void clearSession() {
        loggedInUser = null;
        roles.clear();
        userId = 0;
    }
}

