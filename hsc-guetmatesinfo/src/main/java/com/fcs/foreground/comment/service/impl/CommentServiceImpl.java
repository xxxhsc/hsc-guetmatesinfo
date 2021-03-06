package com.fcs.foreground.comment.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fcs.foreground.comment.entity.Comment;
import com.fcs.foreground.comment.mapper.CommentMapper;
import com.fcs.foreground.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *服务实现类
 * </p>
 *
 * @author hsc
 * @since 2020-04-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    CommentMapper commentMapper;


    @Override
    public Page<Comment> selectCommentPage(Page<Comment> page) {
        System.out.println("页面信息"+commentMapper.selectCommentList(page));
        page.setRecords(commentMapper.selectCommentList(page));
        return page;
    }

    @Override
    public Page<Comment> commentPage(Page<Comment> commentPage) {
//        Page<Comment> page=new Page<>(currentPage,12);
//        Page<Comment> commentIPage = commentMapper.selectAllParentCommentNullPage(page);
        commentPage.setRecords(commentMapper.selectAllParentCommentNullPage(commentPage));

        return commentPage;
    }

    @Override
    public boolean deleteComment(long cid) {
        int i = commentMapper.deleteById(cid);
        return true;
    }



    @Transactional
    @Override
    public boolean saveComment(Comment comment) {
        comment.setCreateTime(new Date());

        Integer parentComment = comment.getParentCommentId();
        if (parentComment.equals(-1)) {
            comment.setParentCommentId(null);

        }else{
            Comment comment1 = commentMapper.selectById(comment.getParentCommentId());
            }
        Integer replyComment = comment.getReplyCommentId();
        if (replyComment.equals(-1)) {
            comment.setReplyCommentId(null);
        }
        int cnt = commentMapper.insert(comment);




        if (cnt == 1)
            return true;
        else
            return false;
    }

    @Override
    public List<Comment> commentList() {
        List<Comment> comments = commentMapper.selectAllParentCommentNull();
        return comments;
    }


}
