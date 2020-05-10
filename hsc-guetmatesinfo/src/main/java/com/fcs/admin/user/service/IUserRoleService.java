package com.fcs.admin.user.service;

import com.fcs.admin.role.entity.Role;
import com.fcs.admin.user.entity.User;
import com.fcs.admin.user.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *

 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> findRoleidListByUserId(long uid);
	
}
