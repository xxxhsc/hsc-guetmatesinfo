package com.fcs.admin.user.controller;

import com.fcs.admin.user.entity.User;
import com.fcs.admin.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/5/13 16:55
 */
@Controller
public class RegisterController {
    @Autowired
    private IUserService userService;



    @RequestMapping("/register")
    public String register(User user, Model model){
        System.out.println(user);
        User user1=userService.findByName(user.getSnumber());
        if(user1!=null){
          if(user1.getState()==1){
              model.addAttribute("msg","用户已认证，可直接登录");
              return "index";
          }
          if (user.getUsername().equals(user1.getUsername())&&user.getScollege().equals(user1.getScollege())&&user.getSex()==user1.getSex()&&user.getSmajor().equals(user1.getSmajor()));{
          user1.setId(user.getId());
          user1.setState(1);
          userService.updateById(user1);
                model.addAttribute("msg","用户认证成功");
            return "403";
          }
        }else{
            model.addAttribute("msg","用户认证失败，请检查认证信息");
            return "403";
        }
    }


}
