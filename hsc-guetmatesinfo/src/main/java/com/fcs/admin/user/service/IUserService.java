package com.fcs.admin.user.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.role.entity.Role;
import com.fcs.admin.user.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.fcs.admin.user.entity.UserRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucare
 * @since 2017-02-24
 */
public interface IUserService extends IService<User> {


    /**
     *
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 找到id对应的所有角色集合，并给集合set 所有角色对应的权限
     * @param uid
     * @return
     */
    List<Role> findRolePermissions(long uid);

    Page<User> selectUserPage(Page<User> page);

    /**
     * 查找用户的角色列表
     * @param uid
     * @return
     */
    List<UserRole> findRole(long uid);



}
