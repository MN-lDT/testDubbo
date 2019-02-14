package com.ldt.login.dao;

import com.ldt.login.domain.ShiroUser;

public interface ShiroUserMapper {

    ShiroUser findInfo(String username);
}
