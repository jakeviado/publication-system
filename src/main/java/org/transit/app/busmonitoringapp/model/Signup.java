package org.transit.app.busmonitoringapp.model;

public class Signup {

    private String username;
    private String password;
    private String name;

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
