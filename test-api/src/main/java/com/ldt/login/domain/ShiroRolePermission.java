package com.ldt.login.domain;

import java.io.Serializable;

/***
 * 角色权限对应表
 */
public class ShiroRolePermission implements Serializable {
    public String roleName;
    public String permName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }
}
