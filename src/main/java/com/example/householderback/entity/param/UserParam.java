package com.example.householderback.entity.param;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public class UserParam {
    String username;

    String password;

    String type;

    public UserParam() {
    }

    public UserParam(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
