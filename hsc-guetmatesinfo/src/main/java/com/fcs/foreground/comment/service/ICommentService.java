package com.fcs.foreground.comment.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fcs.foreground.comment.entity.Comment;

import java.util.List;

/**
 * <p>
 *服务类
 * </p>
 *
 * @author hsc
 * @since 2020-04-12
 */
public interface ICommentService extends IService<Comment> {

     boolean saveComment(Comment comment);


     Page<Comment> selectCommentPage(Page<Comment> page);

     List<Comment> commentList();

     Page<Comment> commentPage(Page<Comment> commentPage);

     boolean deleteComment(long cid);


}
