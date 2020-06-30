package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.service.blogserviceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Controller")
//由其他页面跳转到主页中间的数据处理
public class Homepage {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("homepage")
    public String homepage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<article> qianshititle  = (List< article >)session.getAttribute("link");

//        if (qianshititle.isEmpty()){
//            try {
//                service.queryqianshi();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        model.addAttribute("link",qianshititle);
       return "index";

    }
}
