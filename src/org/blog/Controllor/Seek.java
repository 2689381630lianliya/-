package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.service.blogserviceImpl;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//搜索页面
@Controller
@RequestMapping("Controller")
public class Seek {
    @Resource
    blogserviceImpl service = null;

    @RequestMapping("seek")
    public String seek(HttpServletRequest request, Model model) {
        String title = request.getParameter("title");
        HttpSession session = request.getSession();

        //将标题放入sesion中
        session.setAttribute("title",title);
        //查询搜索到的书籍信息
        List<article> articles = service.queryalltitle(title);
        //共多少页
        int size = articles.size();
        int pagecount = size / 10 == 0 ? size / 10 : size / 10 + 1;

        session.setAttribute("pagecount",pagecount);

        //将搜索到的全部信息封装为  map对象 key 标题 value 地址
        Map<String, String> seekarticle = service.checkarticle(articles);
        //将搜到的文章标题信息放入sision域中
        session.setAttribute("allarticle", seekarticle);
        //分页搜索第一页

        List<article> querytitle = service.querytitle(title, 0);
        List<article> articles1 = new ArrayList<>();
        for (article article : querytitle) {
            int id = article.getId();
            String name = service.querynamebyId(id);
            article.setName(name);
            articles1.add(article);
        }


        model.addAttribute("allarticle", articles1);
        model.addAttribute("currentpage",0+1);
        return "seekpage";
    }
    @RequestMapping("tiaozhuan/{pagecount}")
    public String  tiaozhuan(@PathVariable int pagecount,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        String title=(String)session.getAttribute("title");
        //前端发过来的页码
        List<article> list = service.querytitle(title, pagecount);
        List<article> articles1 = new ArrayList<>();
        for (article article : list) {
            int id = article.getId();
            String name = service.querynamebyId(id);
            article.setName(name);
            articles1.add(article);
        }
        model.addAttribute("currentpage",pagecount);
        model.addAttribute("allarticle", articles1);
        //用于占用搜索框
        model.addAttribute("title",title);
        return "seekpage";
    }
}
