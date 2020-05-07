package com.fcs.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.entity.Role;
import com.fcs.admin.entity.User;
import com.baomidou.mybatisplus.service.IService;

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



    User findByName(String username);

    List<Role> findRolePermissions(long uid);

    Page<User> selectUserPage(Page<User> page);

}
