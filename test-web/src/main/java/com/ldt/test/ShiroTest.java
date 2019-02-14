package com.ldt.test;


import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroTest {

    private static final transient Logger logger = Logger.getLogger(ShiroTest.class);

    public static void main(String[] args) {
        // 1.读取ini，生成SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2.解析工厂获取实例
        SecurityManager securityManager = factory.getInstance();
        // 3.通过SecurityUtils，绑定securityManager
        SecurityUtils.setSecurityManager(securityManager);
        // 4.安全操作Subject
        Subject subject = SecurityUtils.getSubject();

        // 测试在应用的当前会话中设置属性
        Session session = subject.getSession();
        //放进key
        session.setAttribute("someKey","aValue");
        //取出value
        String value = (String) session.getAttribute("someKey");

        if ("aValue".equals(value)) {//比较拿到的值和原来的值是否一致
            logger.info("检索到正确的值[" + value + "]");
        }

        //尝试进行登录用户，如果登录失败了，我们进行一些处理
        if (!subject.isAuthenticated()) {//如果用户没有登录过(验证)
            UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
            token.setRememberMe(true);//是否记住用户

            try {
                // 登录
                subject.login(token);
                logger.info("用户 [" + subject.getPrincipal() + "] 登陆成功");

                // 查看用户有没有指定角色
                if (subject.hasRole("admin")) {
                    logger.info("有admin角色");
                } else {
                    logger.info("无admin角色");
                }
                if (subject.hasRole("role1")) {
                    logger.info("有role1角色");
                } else {
                    logger.info("无role1角色");
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
                // 退出登录  会话失效
                subject.logout();


            } catch (UnknownAccountException uae) {
                logger.info(token.getPrincipal() + "账户不存在");
            } catch (IncorrectCredentialsException ice) {
                logger.info(token.getPrincipal() + "密码不正确");
            } catch (LockedAccountException lae) {
                logger.info(token.getPrincipal() + "用户被锁定了 ");
            } catch (AuthenticationException ae) {
                //无法判断是什么错了
                logger.info(ae.getMessage());
            }
        }

    }
}
