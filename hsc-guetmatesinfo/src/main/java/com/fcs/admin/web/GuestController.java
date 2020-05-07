package com.fcs.admin.web;
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
public class GuestController {
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
