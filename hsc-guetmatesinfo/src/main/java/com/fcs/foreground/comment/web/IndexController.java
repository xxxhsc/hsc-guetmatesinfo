package com.fcs.foreground.comment.web;


import com.fcs.admin.user.entity.User;
import com.fcs.common.util.ShiroUtils.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/4/30 16:22
 */

@Controller
public class IndexController {

    @RequestMapping("/admin")
    public String index(Model model) {
        System.out.println("this is frame");
        User user = ShiroUtils.getuserinfo();
        System.out.println("在线信息"+user.toString());
        model.addAttribute("user",user);
        return "common/frame";
    }


    @RequestMapping("/index")
    public String list(){
        System.out.println("this is index");
        return "adminindex";
    }

    @RequestMapping("/toregister")
    public String register(){
        return "register";
    }

    @RequestMapping("/xyhd")
    public String xyhd(Model model){

        model.addAttribute("user", ShiroUtils.getuserinfo());
        return "xyhd";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        System.out.println("个人资料");
        model.addAttribute("user",ShiroUtils.getuserinfo());
        return "profile";
    }
    @RequestMapping("/")
    public String foregroundindex(Model model) {
        System.out.println("首页");
        model.addAttribute("user",ShiroUtils.getuserinfo());

        System.out.println("首页用户信息"+ShiroUtils.getuserinfo());
        return "index";
    }


    @RequestMapping("/details")
    public String details(Model model){
        System.out.println("详情页");

        return "details";
    }

}
