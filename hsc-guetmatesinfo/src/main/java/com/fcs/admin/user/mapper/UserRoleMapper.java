package com.fcs.admin.user.mapper;

import com.fcs.admin.role.entity.Role;
import com.fcs.admin.user.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hsc
 * @since 2020-03-08
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Role> findRoleListByUserId(long uid);

}