package com.fcs.foreground.comment.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.foreground.comment.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hsc
 * @since 2020-04-12
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> selectCommentList(Page page);



    @Select("select * from t_comment where parentCommentId is null  and status = 1")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "user",column = "uid",one = @One(select = "com.fcs.admin.user.mapper.UserMapper.selectById")),
            @Result(property = "replyComments",column = "cid",many = @Many(select = "com.fcs.foreground.comment.mapper.CommentMapper.selectByParentCommentId"))
    }
    )
    List<Comment> selectAllParentCommentNull();




    /*one一对一，many一对多*/
    @Select("select * from t_comment where parentCommentId is null and status = 1")
    @Results(value = {
            @Result(property = "uid",column = "uid"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "user",column = "uid",one = @One(select = "com.fcs.admin.user.mapper.UserMapper.selectById")),
            @Result(property = "replyComments",column = "cid",many = @Many(select = "com.fcs.foreground.comment.mapper.CommentMapper.selectByParentCommentId"))
    }
    )
    List<Comment> selectAllParentCommentNullPage(Page  page);


    @Select("select * from t_comment where parentCommentId=#{cid} and status = 1")
    @Results(value = {
            @Result(property = "replyCommentId",column = "replyCommentId"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "user",column = "uid",one = @One(select = "com.fcs.admin.user.mapper.UserMapper.selectById")),
        @Result(property = "replyToComment",column = "replyCommentId",one = @One(select = "com.fcs.foreground.comment.mapper.CommentMapper.selectById"))
    })
    List<Comment> selectByParentCommentId(Integer cid);
}
