package com.ldt.login.service;

import com.ldt.login.domain.ShiroUser;

public interface IShiroUser {

    /***
     * 根据姓名查找
     */
    ShiroUser findInfo(String username);
}
