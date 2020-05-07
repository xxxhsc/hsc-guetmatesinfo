package com.fcs.admin.web;

import com.fcs.common.util.ShiroUtils.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/5/6 16:32
 */
@Controller
public class MateController {
    @RequestMapping("/profile")
    public String profile(Model model) {
        System.out.println("个人资料");
        model.addAttribute("user",ShiroUtils.getuserinfo());
        return "profile";
    }
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("首页");
        model.addAttribute("user",ShiroUtils.getuserinfo());
        return "index";
    }




}
