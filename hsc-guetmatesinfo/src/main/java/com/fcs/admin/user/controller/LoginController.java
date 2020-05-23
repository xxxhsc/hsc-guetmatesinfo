package com.fcs.admin.user.controller;

import com.fcs.admin.user.entity.User;
import com.fcs.admin.user.service.IUserService;
import com.fcs.common.shiro.AuthSubjectUtil;
import com.fcs.common.util.StringUtils;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/4/30 16:22
 */
@Controller
public class LoginController {
    @Autowired
    private IUserService userService;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
//            String md5Pwd = new Md5Hash(password, AuthConstant.salt).toString();
//            String md5Pwd = new Md5Hash(password).toString();


    @RequestMapping("/login")
    public String index(User iuser , @RequestParam("type") Integer type, @RequestParam("validateCode") String validateCode, Model model) {
        String userName = iuser.getUsername();
        Object obj=SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String code = String.valueOf(obj != null ? obj : "");
        if (validateCode.equals(code))
        {
            System.out.println("验证码通过");
            if(AuthSubjectUtil.checklogintype(userService.findRole(userService.findByName(iuser.getUsername()).getId()),type)){
            UsernamePasswordToken token = new UsernamePasswordToken(iuser.getUsername(), iuser.getPassword(), "login");
            Subject currentUser = SecurityUtils.getSubject();

            logger.info("表单信息"+iuser.toString());
            logger.info("对用户[" + userName + "]进行登录验证..验证开始");

            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");

                   if(type==2){
                       return "redirect:/";/*校友*/
                   }else if (type==1){
                       return "redirect:/admin";/*系统管理员*/
           }
                } else {
                    token.clear();
                    logger.info("重定向login");
                    return "redirect:/login";
                }
            } catch ( UnknownAccountException uae ) {
                logger.info("对用户[" + userName + "]进行登录验证..验证失败-username wasn't in the system");
            } catch ( IncorrectCredentialsException ice ) {
                logger.info("对用户[" + userName + "]进行登录验证..验证失败-password didn't match");
            } catch ( LockedAccountException lae ) {
                logger.info("对用户[" + userName + "]进行登录验证..验证失败-account is locked in the system");
            } catch ( AuthenticationException ae ) {
                logger.error(ae.getMessage());
            }
        }
            model.addAttribute("msg","用户名或密码错误");
            logger.info("用户名或密码错误，重定向index");
            return "redirect:/";
        }else {
            model.addAttribute("msg","验证码错误");
            logger.info("验证码错误，重定向index");
            return "redirect:/";
        }
    }


    @RequestMapping("/logout")
    public String logout(User user) {

        SecurityUtils.getSubject().logout();
        return "index";
    }



    @RequestMapping("/checkUserName")
    public int checkUsername(String userName) {
        if(userService.findByName(userName)!=null){
            return 1;
        }else{
            return 0;
        }

    }

    @RequestMapping(value = {"/403"})
    public String noAuth() {
        return "403";
    }


}
