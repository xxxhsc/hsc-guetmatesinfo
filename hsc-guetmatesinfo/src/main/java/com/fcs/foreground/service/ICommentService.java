package com.fcs.foreground.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fcs.foreground.entity.Comment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2020-04-12
 */
public interface ICommentService extends IService<Comment> {

     boolean saveComment(Comment comment);



     List<Comment> commentList();

     Page<Comment> commentPage(Page<Comment> commentPage);

     boolean deleteComment(Integer cid);


}
