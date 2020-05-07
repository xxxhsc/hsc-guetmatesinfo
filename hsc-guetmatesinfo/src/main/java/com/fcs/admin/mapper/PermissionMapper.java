package com.fcs.admin.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.entity.Permission;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectPermList(Page page);

}