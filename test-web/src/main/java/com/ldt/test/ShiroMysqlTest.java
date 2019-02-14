package com.ldt.test;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroMysqlTest {

    private static transient final Logger logger = Logger.getLogger(ShiroMysqlTest.class);

    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-mysql.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("test@shiro.com","123456");

        try {
            subject.login(token);

            logger.info("用户"+subject.getPrincipal()+"登陆成功！");

            //查看用户是否有角色
            if (subject.hasRole("admin")) {
                logger.info("您有admin角色");
            } else {
                logger.info("您没有admin角色");
            }
            if (subject.hasRole("test")) {
                logger.info("您有test角色");
            } else {
                logger.info("您没有test角色");
            }
            // 查看用户是否有某个权限
            if (subject.isPermitted("perm1")) {
                logger.info("您有perm1权限");
            } else {
                logger.info("您没有perm1权限");
            }
            if (subject.isPermitted("guest")) {
                logger.info("您有guest权限");
            } else {
                logger.info("您没有guest权限");
            }
            //登出
            subject.logout();
        } catch (UnknownAccountException uae) {
            logger.info(token.getPrincipal() + "账户不存在");
        }catch (IncorrectCredentialsException ice) {
            logger.info(token.getPrincipal() + "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info(token.getPrincipal() + "用户被锁定了 ");
        } catch (AuthenticationException ae) {
            //无法判断是什么错了
            logger.info(ae.getMessage());
        }
    }
}
