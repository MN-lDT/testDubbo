package com.ldt;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService{
    @Override
    public String getStr(String str) {
        return "ECHO:"+str;
    }
}
