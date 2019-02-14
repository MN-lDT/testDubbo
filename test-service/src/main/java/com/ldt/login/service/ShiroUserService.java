package com.ldt.login.service;

import com.ldt.login.dao.ShiroUserMapper;
import com.ldt.login.domain.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroUserService implements IShiroUser {

    @Autowired
    private ShiroUserMapper shiroUserMapper;

    @Override
    public ShiroUser findInfo(String username) {
        return shiroUserMapper.findInfo(username);
    }
}
