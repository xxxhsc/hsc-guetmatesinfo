package com.fcs.common.util.ShiroUtils;

import com.fcs.admin.user.entity.User;
import com.fcs.common.util.StringUtils;
import com.fcs.common.util.bean.BeanUtils;
import org.apache.shiro.SecurityUtils;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/5/6 22:59
 */
public class ShiroUtils {
    public static User getuserinfo(){
        User user = null;
        // 取身份信息
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            user = new User();
            BeanUtils.copyBeanProp(user, obj);
            return user;
        }else {
            return null;
        }
    }
}
