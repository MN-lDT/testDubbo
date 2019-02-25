package com.ldt.index;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final transient Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/")
    public String loadIndex(){
        logger.info("加载首页");
        return "/index/index";
    }
}
