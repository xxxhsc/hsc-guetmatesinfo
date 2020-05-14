package com.fcs.admin.comment.controller;



import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.common.util.ShiroUtils.ShiroUtils;
import com.fcs.foreground.comment.entity.Comment;
import com.fcs.foreground.comment.service.ICommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/admin/comment")
public class BCommentController {

    @Autowired
    ICommentService commentService;


    @RequiresPermissions("admin:comment:view")
    @RequestMapping("/index")
    public String index() {
        return "admin/comment/list";
    }

    @RequiresPermissions("admin:comment:view")
    @RequestMapping("/list")
    @ResponseBody
    public Page<Comment> list() {
        Page<Comment> page = commentService.selectCommentPage(new Page<Comment>(0, 12));

        return page;
    }






    @RequestMapping("/changeState")
    @ResponseBody
    public String changeState(long cid, int state) {
        Comment comment = new Comment();
        comment.setCid(cid);
        comment.setState(state);
        commentService.updateById(comment);
        return "success";
    }

    @RequestMapping("/getCommentById")
    @ResponseBody
    public Comment getCommentById(long cid) {
        Comment Comment = commentService.selectById(cid);
        return Comment;
    }

    @RequestMapping("/deleteOne")
    @ResponseBody
    public int deleteOne(long cid) {
        boolean isSucc = commentService.deleteComment(cid);
        return isSucc ? 1 : 0;
    }



}
