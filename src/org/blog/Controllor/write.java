package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.entiy.user;
import org.blog.service.blogserviceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("Controller")
public class write {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("writearticle")
    public String wirtearticle(HttpServletRequest request){
        HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String name=(String)session.getAttribute("uname");

        int i = service.createarticle(title, name, content,session);

        if (1!=0){
            return "writearticlePage";
        }else {
            return "err";
        }

    }
}
