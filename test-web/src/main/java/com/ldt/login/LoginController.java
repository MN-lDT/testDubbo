package com.ldt.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    /***
     * 登陆验证
     * @param request
     * @return
     */
    @RequestMapping(value = "/goLogin")
    public String goLogin(HttpServletRequest request) {
        return "";
    }

    /***
     * 登陆成功跳转的url
     * @param request
     * @return
     */
    @RequestMapping(value = "/success")
    public String loginSuccess(HttpServletRequest request){
        return "";
    }

    /***
     * 登陆失败跳转的url
     * @param request
     * @return
     */
    @RequestMapping(value = "/error")
    public String loginError (HttpServletRequest request){
        return "";
    }

    @RequestMapping(value = "/doadd")
    public String doadd(HttpServletRequest request){
        return "";
    }

}
