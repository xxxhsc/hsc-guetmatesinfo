package com.fcs.admin.article.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fcs.admin.article.entity.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/5/11 0:09
 */
@Repository
public interface TypeMapper extends BaseMapper<Type> {


    @Select("select * from t_type where parentid is null")
    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "label",column = "label"),
            @Result(property = "parentid",column = "parentid"),
            @Result(property = "childrenType",column = "id",many = @Many(select = "com.fcs.admin.article.mapper.TypeMapper.selectByParentId"))
    }
    )
    List<Type> selectAllParentidNull();

    @Select("select * from t_type where parentid is not null")
    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "label",column = "label"),
            @Result(property = "parentid",column = "parentid"
            )
    }
    )
    List<Type> selectAllParentidNotNull();

    @Select("select * from t_type where parentid =#{id}")
    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "label",column = "label"),
            @Result(property = "parentid",column = "parentid"),
    }
    )
    List<Type> selectByParentId(Integer id);
}


