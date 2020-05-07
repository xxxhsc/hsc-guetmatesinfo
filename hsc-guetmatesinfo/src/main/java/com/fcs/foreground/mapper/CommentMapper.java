package com.fcs.foreground.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.foreground.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from t_comment where parentCommentId is null")
    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "user",column = "id",one = @One(select = "com.fcs.admin.mapper.UserMapper.selectById")),
            @Result(property = "replyComments",column = "cid",many = @Many(select = "com.fcs.foreground.mapper.CommentMapper.selectByParentCommentId"))
    }
    )
    List<Comment> selectAllParentCommentNull();

    @Select("select * from t_comment where parentCommentId is null")
    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "user",column = "id",one = @One(select = "com.fcs.admin.mapper.UserMapper.selectById")),
            @Result(property = "replyComments",column = "cid",many = @Many(select = "com.fcs.foreground.mapper.CommentMapper.selectByParentCommentId"))
    }
    )
    List<Comment> selectAllParentCommentNullPage(Page  page);


    @Select("select * from t_comment where parentCommentId=#{cid}")
    @Results(value = {
            @Result(property = "replyCommentId",column = "replyCommentId"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "id",column = "id"),
            @Result(property = "user",column = "id",one = @One(select = "com.fcs.admin.mapper.UserMapper.selectById")),
        @Result(property = "replyToComment",column = "replyCommentId",one = @One(select = "com.fcs.foreground.mapper.CommentMapper.selectById"))
    })
    List<Comment> selectByParentCommentId(Integer cid);
}
