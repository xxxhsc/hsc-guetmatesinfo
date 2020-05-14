package com.fcs.admin.user.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.user.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    User findByName(String name);

    List<User> selectUserList(Page page);




}