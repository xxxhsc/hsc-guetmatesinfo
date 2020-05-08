package com.fcs.admin.role.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.role.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Lucare
 * @since 2017-03-06
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Long> findRoleIdListByUserId(long uid);

    List<Role> selectRoleList(Page page);

}