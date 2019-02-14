package com.ldt.login.domain;

import java.io.Serializable;

/***
 * 用户角色对应表
 */
public class ShiroUserRole implements Serializable {
    public String userName;
    public String roleName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
