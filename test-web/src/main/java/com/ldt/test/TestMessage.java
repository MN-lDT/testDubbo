package com.ldt.test;

import com.ldt.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestMessage {

    private static final Logger logger= LoggerFactory.getLogger(TestMessage.class);

    @Autowired
    private IMessageService messageService;

    @RequestMapping("/test")
    @ResponseBody
    public void getTestMessage(String messageStr) {
        messageService.getStr(messageStr);

    }


}
