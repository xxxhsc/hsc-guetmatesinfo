package com.fcs.admin.user.controller;


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

}
