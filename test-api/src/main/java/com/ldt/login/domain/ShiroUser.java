package com.ldt.login.domain;

import java.io.Serializable;

/***
 * 基本用户表
 */
public class ShiroUser implements Serializable {
    public String userName;
    public String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
