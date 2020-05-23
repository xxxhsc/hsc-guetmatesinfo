package com.fcs.admin.user.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.role.entity.Role;
import com.fcs.admin.role.mapper.RolePermissionMapper;
import com.fcs.admin.user.entity.User;
import com.fcs.admin.user.entity.UserRole;
import com.fcs.admin.user.mapper.UserRoleMapper;
import com.fcs.admin.user.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fcs.admin.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }






    @Override
    public List<Role> findRolePermissions(long uid) {
        List<Role> roleIdList = userRoleMapper.findRoleListByUserId(uid);/*找到userid对应的角色*/

        for (Role role : roleIdList) {
            Set<String> everyRolePer = rolePermissionMapper.findPermissions(role.getId());/*找到每一个角色对应的权限*/
            role.setPerNameSet(everyRolePer);//给每个角色设置对应的权限
        }
        return roleIdList;/*返回userid对应的所有角色类信息集合*/
    }

    @Override
    public Page<User> selectUserPage(Page<User> page) {
        page.setRecords(userMapper.selectUserList(page));
        return page;
    }


    @Override
    public List<UserRole> findRole(long uid) {
        return userRoleMapper.findRoleidListByUserId(uid);
    }

}
