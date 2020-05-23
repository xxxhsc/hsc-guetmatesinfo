package com.fcs.foreground.comment.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fcs.admin.user.entity.User;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * <p>
 * 
 * </p>
 *
 * @author hsc
 * @since 2020-04-12
 */

@Data
@TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    private Long uid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("createTime")
    private Date createTime;

    private String content;

    @TableField("parentCommentId")
    private Integer parentCommentId;


    @TableField("replyCommentId")
    private Integer replyCommentId;


    @TableField("userName")
    private String userName;


    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    List<Comment> replyComments;

    @TableField(exist = false)
    private Comment replyToComment;

    private int status;


}
