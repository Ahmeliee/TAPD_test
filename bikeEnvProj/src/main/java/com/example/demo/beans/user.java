package com.example.demo.beans;

public class user {
    private String username;
    private String password;
    private String type;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "user [username="+username+",password="+password+",type"+type+"]";
    }
}
