package com.fcs.foreground.web;



import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.entity.User;
import com.fcs.common.util.StringUtils;
import com.fcs.common.util.bean.BeanUtils;
import com.fcs.foreground.entity.Comment;
import com.fcs.foreground.service.ICommentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
@Controller

public class CommentController {

    @Autowired
    ICommentService commentService;


    @RequestMapping("/comment/postcomment")

    public String postcomment(Comment comment) {
        System.out.println(comment);
        commentService.saveComment(comment);
        return "redirect:/comment/commentlist";
    }

    @RequestMapping("/comment/commentlist")
    public String commentlist(Integer page, Model model){
        if(page==null){
            page=1;
        }
        Page<Comment> commentIPage = commentService.commentPage(new Page<Comment> (page,1));
        model.addAttribute("page",commentIPage);
        List<Comment> comments = commentIPage.getRecords();
        model.addAttribute("comments",comments);
        System.out.println("page "+page);
        return "note::commentslist";
    }

    @RequestMapping("/comment/delete")
    public String deleteComment(Integer cid) {
        boolean b = commentService.deleteComment(cid);
        return "redirect:/comment/commentlist";
    }

    @RequestMapping("/note")
    public String getcomment(Model model, HttpSession session){

        System.out.println("this is note");
        User user = null;
        // 取身份信息
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            user = new User();
            BeanUtils.copyBeanProp(user, obj);
            System.out.println("在线信息"+user.toString());
            Page<Comment> commentIPage = commentService.commentPage(new Page<Comment> (1,1));
            List<Comment> comments = commentIPage.getRecords();
            System.out.println("page "+commentIPage);
            model.addAttribute("user",user);
            model.addAttribute("page",commentIPage);
            model.addAttribute("comments",comments);
            return "note";
        }else{
            return "index";
        }

    }

}
