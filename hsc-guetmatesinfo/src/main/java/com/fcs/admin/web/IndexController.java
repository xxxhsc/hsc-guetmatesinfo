package com.fcs.admin.web;


import com.fcs.admin.entity.User;
import com.fcs.common.util.StringUtils;
import com.fcs.common.util.bean.BeanUtils;
import org.apache.shiro.SecurityUtils;
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
        User user = null;
        // 取身份信息
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            user = new User();
            BeanUtils.copyBeanProp(user, obj);
        }
        System.out.println("在线信息"+user.toString());
        model.addAttribute("user",user);
        return "common/frame";
    }


    @RequestMapping("/index")
    public String list(Model mmap){
        System.out.println("this is index");


        return "adminindex";
    }


}
