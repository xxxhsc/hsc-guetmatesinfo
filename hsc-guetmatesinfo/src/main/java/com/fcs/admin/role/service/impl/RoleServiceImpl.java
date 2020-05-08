package com.fcs.admin.role.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.role.entity.Role;
import com.fcs.admin.role.mapper.RoleMapper;
import com.fcs.admin.role.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucare
 * @since 2017-03-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<Role> selectRolePage(Page<Role> page) {
        page.setRecords(roleMapper.selectRoleList(page));
        return page;
    }
}
