package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.service.blogserviceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("Controller")
public class record {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("queryrecord")
    public String queryrecord(Model model, HttpServletRequest request){
        List<org.blog.entiy.record> allrecords = service.queryallrecord(request.getSession());
        //共多少页
        int size = allrecords.size();
        int pagecount = size / 8 == 0 ? size / 8 : size / 8 + 1;

        //获取sesion对象将总页数放入sesion中
        HttpSession session = request.getSession();
        session.setAttribute("pagecount",pagecount);
        List<org.blog.entiy.record> records = service.queryrecord(0,session);
        model.addAttribute("currentpage",1);

        model.addAttribute("record",records);
        return "person-record";

    }
    @RequestMapping("recordpage/{page}")
    public String recordpage(Model model, @PathVariable int page,HttpServletRequest request){
        List<org.blog.entiy.record> records = service.queryrecord((page-1)*7,request.getSession());
        model.addAttribute("record",records);
        model.addAttribute("currentpage",page);
        return "person-record";

    }
}
