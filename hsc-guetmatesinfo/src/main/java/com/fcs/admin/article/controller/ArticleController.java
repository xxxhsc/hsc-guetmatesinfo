package com.fcs.admin.article.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.article.entity.Article;
import com.fcs.admin.article.entity.Type;
import com.fcs.admin.article.service.IArticleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: hsc
 * @Description:前端控制器
 * @Date: 2020/5/9 3:40
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    IArticleService articleService;






    /**
     * summernote 富文本编辑器
     */

    @RequiresPermissions("admin:article:view")
    @RequestMapping("/index")
    public String index(Model model) {
        List<Type> FirstTypelist = articleService.FirstTypesList();
//        List<Type> SencondTypelist=articleService.SencondTypesList();
        model.addAttribute("FirstTypelist",FirstTypelist);
//        model.addAttribute("SencondTypeList",SencondTypelist);
        return "admin/article/summernote";
    }

    /**
     * summernote 文章列表
     */
    @RequiresPermissions("admin:article:view")
    @RequestMapping("/index2")
    public String articlelist(Model model) {
        List<Type> FirstTypelist = articleService.FirstTypesList();
        model.addAttribute("FirstTypelist",FirstTypelist);
        return "admin/article/articlelist";
    }

    @RequiresPermissions("admin:article:view")
    @RequestMapping("/articlelist")
    @ResponseBody
    public Page<Article> list() {

        Page<Article> page = articleService.selectArticlePage(new Page<Article>(0, 12));
        return page;
    }

    @RequestMapping("/getArticleTypeById")
    @ResponseBody
    public String getArticleTypeById(long aid) {
        return  articleService.getArticleTypeById(aid).getLabel();
    }

    @RequestMapping("/updatestatus")
    @ResponseBody
    public String updatestatus(long aid, int status) {
        System.out.println("id为"+aid+"状态为"+status);
        articleService.updatestatus(aid,status);
        return "success";
    }





    @RequestMapping("/changeState")
    @ResponseBody
    public String changeState(long aid, int status) {
        Article article = new Article();
        article.setAid(aid);
        article.setStatus(status);
        articleService.updateById(article);
        return "success";
    }

    @RequestMapping("/getArticleById")
    @ResponseBody
    public Article getArticleById(long aid) {
        Article article = articleService.selectById(aid);
        return article;
    }

    @RequestMapping("/update")
    public String update(Article article) {
        boolean isSuccc = articleService.updateById(article);
        if (isSuccc) {
            return "redirect:index";
        }
        return "error";
    }

    @RequestMapping("/deleteOne")
    @ResponseBody
    public int deleteOne(long aid) {
        boolean isSucc = articleService.deleteById(aid);
        return isSucc ? 1 : 0;
    }

    @RequestMapping("/selectByParentId")
    @ResponseBody
    public  String  selectByParentId(Integer id){

        List<Type> type =articleService.selectByParentId(id);
        String jsonObject = JSONObject.toJSONString(type);
        System.out.println(jsonObject);
        return jsonObject;
    }

}
