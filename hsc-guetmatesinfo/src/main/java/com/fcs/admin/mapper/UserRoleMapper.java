package com.fcs.admin.mapper;

import com.fcs.admin.entity.Role;
import com.fcs.admin.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Lucare
 * @since 2017-03-08
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Role> findRoleListByUserId(long uid);

}