package com.fcs.common.util.ShiroUtils;

import com.fcs.admin.user.entity.User;
import com.fcs.common.util.StringUtils;
import com.fcs.common.util.bean.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/5/6 22:59
 */
public class ShiroUtils {
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }
    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }


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

    public static void setSysUser(User user)
    {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

}
