package com.fcs.admin.role.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.role.entity.Role;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucare
 * @since 2017-03-06
 */
public interface IRoleService extends IService<Role> {

    Page<Role> selectRolePage(Page<Role> page);
	
}
