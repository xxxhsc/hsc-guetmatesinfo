package com.fcs.foreground.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fcs.admin.entity.User;
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
    private Integer cid;

    private Integer id;

    @TableField("createTime")
    private Date createTime;

    private String content;

    @TableField("parentCommentId")
    private Integer parentCommentId;


    @TableField("replyCommentId")
    private Integer replyCommentId;

    private String email;

    @TableField("userName")
    private String userName;

    private boolean remind;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    List<Comment> replyComments;

    @TableField(exist = false)
    private Comment replyToComment;


}
