package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.service.blogserviceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("Controller")
public class Delete {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("deletearticle/{title}")
    public String deletearticle(HttpServletRequest request,@PathVariable String title){
        //获取sesion对象
        HttpSession session = request.getSession();
        //获取集合对象
        List<article> list =(List<article>)session.getAttribute("userarticleList");
        boolean deletearticle = service.deletearticle(title,list);

            return "redirect:../Myarticle";



    }
}
