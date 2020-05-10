package com.fcs.foreground.comment.web;



import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.common.util.ShiroUtils.ShiroUtils;
import com.fcs.foreground.comment.entity.Comment;
import com.fcs.foreground.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 *控制器
 * </p>
 *
 * @author hsc
 * @since 2020-04-12
 */
@Controller

public class CommentController {

    @Autowired
    ICommentService commentService;


    @RequestMapping("/comment/postcomment")
    public String postcomment(Comment comment) {
        System.out.println("后台收到1comment数据"+comment);
        if(commentService.saveComment(comment)){
            System.out.println("发送失败"+comment);
        }else {
            System.out.println("发送成功"+comment);
        }

        return "redirect:/comment/commentlist";
    }

    @RequestMapping("/comment/commentlist")
    public String commentlist(Integer page, Model model){
        if(page==null){
            page=1;
        }
        Page<Comment> commentIPage = commentService.commentPage(new Page<Comment> (page,5));
        model.addAttribute("page",commentIPage);
        List<Comment> comments = commentIPage.getRecords();
        model.addAttribute("comments",comments);
        model.addAttribute("user",ShiroUtils.getuserinfo());
        System.out.println("page "+page);
        return "note::commentslist";
    }

    @RequestMapping("/comment/delete")
    public String deleteComment(Integer cid) {
        boolean b = commentService.deleteComment(cid);
        return "redirect:/comment/commentlist";
    }

    @RequestMapping("/note")
    public String getcomment(Model model){


        Page<Comment> commentIPage = commentService.commentPage(new Page<Comment> (1,5));
        List<Comment> comments = commentIPage.getRecords();
        System.out.println("page "+commentIPage);
        model.addAttribute("user", ShiroUtils.getuserinfo());
        model.addAttribute("page",commentIPage);
        model.addAttribute("comments",comments);
        return "note";
    }

}
