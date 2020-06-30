package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.entiy.user;
import org.blog.entiy.vip;
import org.blog.service.blogserviceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Request;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Controller")
//个人中心页面
public class personCenter {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("center")
    public String center( HttpServletRequest request, Model model){

       // service.querycount()
        HttpSession session = request.getSession();
        user user=(user)session.getAttribute("user");
        model.addAttribute("user",user);
        //如果sesion中vip为空则创建
        vip vip = (vip)session.getAttribute("vip");
        if (vip==null){
            vip = service.queryvip(user.getUsername());
            session.setAttribute("vip",vip);
        }


        //将用户信息放入页面
        model.addAttribute("vip",vip);


        return "person-user";
    }
    @RequestMapping("Myarticle")
    public String Myartice( HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(123);
            System.out.println(cookie.getName());
        }
        //自己所写的所有文章（标题 超链接)
        List<article> list = service.queryuserarticle(session);
        //共多少页
        int size = list.size();

        int pagecount = size / 7 == 0 ? size / 7 : size / 7 + 1;

        //获取sesion对象将总页数放入sesion中
        session.setAttribute("pagecount",pagecount);

        session.setAttribute("userarticleList",list);
        //将文章放入页面
        List<article> articles = service.queryuserarticle2(0,session);
        //为文章类添加作者名
        List<article> articles1 = new ArrayList<>();
        for (article article : articles) {
            int id = article.getId();
            String name1 = service.querynamebyId(id);
            article.setName(name1);
            articles1.add(article);
        }
        model.addAttribute("userarticle",articles1);
        //获取文章标题对于地址的map
        Map<String, String> checkarticle = service.checkarticle(list);
        session.setAttribute("checkuserarticle",checkarticle);
        //获取vipsesion
        vip vip=(vip)session.getAttribute("vip");

        model.addAttribute("vip",vip);
        model.addAttribute("currentpage",1);
        return "person-userarticle";
    }
    @RequestMapping("userarticlepage/{page}")
    public String userarticlepage(@PathVariable int page,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        List<article> articles = service.queryuserarticle2((page-1)*7,session);
        //为文章类添加作者名
        List<article> articles1 = new ArrayList<>();
        for (article article : articles) {
            int id = article.getId();
            String name1 = service.querynamebyId(id);
            article.setName(name1);
            articles1.add(article);
        }
        model.addAttribute("userarticle",articles1);
        model.addAttribute("currentpage",page);
        return "person-userarticle";
    }


}
