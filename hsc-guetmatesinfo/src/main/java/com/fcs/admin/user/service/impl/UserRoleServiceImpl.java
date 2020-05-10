package com.fcs.admin.user.service.impl;

import com.fcs.admin.role.entity.Role;
import com.fcs.admin.user.entity.UserRole;
import com.fcs.admin.user.mapper.UserRoleMapper;
import com.fcs.admin.user.service.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucare
 * @since 2017-03-08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> findRoleidListByUserId(long uid) {

        return userRoleMapper.findRoleidListByUserId(uid);
    }
}
