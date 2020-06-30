package org.blog.Controllor;

import org.blog.entiy.user;
import org.blog.entiy.vip;
import org.blog.service.blogserviceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("Controller")
//会员中心页面
public class Vipcenter {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("vipcenter")
    public String vipcenter(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("uname");
        //如果sesion中vip为空则创建
        vip vip = (vip)session.getAttribute("vip");
        if (vip==null){
            vip = service.queryvip(username);
            session.setAttribute("vip",vip);
        }
        //将vip信息放入域中
        model.addAttribute("vip",vip);
        //跳转如vip中心页面
        return "vipcenterpage";
    }
}
