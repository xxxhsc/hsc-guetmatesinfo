package com.fcs.admin.permission.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.permission.entity.Permission;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucare
 * @since 2017-03-06
 */
public interface IPermissionService extends IService<Permission> {

    Page<Permission> selectPermPage(Page<Permission> page);
	
}
