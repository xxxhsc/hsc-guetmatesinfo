package com.fcs.common.shiro;

import com.fcs.admin.role.entity.Role;
import com.fcs.admin.user.entity.User;
import com.fcs.admin.user.entity.UserRole;
import com.fcs.admin.user.service.IUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 *
 */
public class AuthSubjectUtil {


    /**
     * 系统管理员账号
     */
    public static final String SUPPLIER_ADMIN_USERNAME = "admin";
    public static final String SUPPLIER_GUETMATES_USERNAME = "guetmates";
    public static final int SUPPLIER_ADMIN_ID = 1;
    public static final int SUPPLIER_GUETMATES_ID = 2;

    public static boolean isPatformAdmin(String principal) {
        if (StringUtils.isEmpty(principal))
            return false;
        return SUPPLIER_ADMIN_USERNAME.equalsIgnoreCase(principal);
    }

    /**
     * 判断是否平台管理员账号登录
     */
    public static boolean isPatformAdmin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null)
            return false;
        User user = (User) subject.getPrincipal();
        return SUPPLIER_ADMIN_USERNAME.equalsIgnoreCase(user.getUsername().trim());
    }
    /**
     * 判断是否平台校友账号登录
     */
    public static boolean isPatformMates(Role role) {

        Subject subject = SecurityUtils.getSubject();

        if (subject == null)
            return false;
        User user = (User) subject.getPrincipal();
       System.out.println("用户的角色为"+user.getRoleList());
        return SUPPLIER_GUETMATES_USERNAME.equalsIgnoreCase(user.getUsername().trim());
    }


    /**
     * 判断是否登录账号是否匹配
     */
    public static boolean checklogintype (List<UserRole> userRole, int type) {
        for(UserRole userRolelist :userRole){
           if(userRolelist.getRoleid()==SUPPLIER_ADMIN_ID&type==SUPPLIER_ADMIN_ID){
               return  true;
           }
            if(userRolelist.getRoleid()==SUPPLIER_GUETMATES_ID&type==SUPPLIER_GUETMATES_ID){
                return  true;
            }
        }

        return  false;
    }



}
