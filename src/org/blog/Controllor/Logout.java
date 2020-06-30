package org.blog.Controllor;


import org.blog.service.blogserviceImpl;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;

@Controller
@RequestMapping("Controller")
public class Logout {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("Logout")

    public String logoutuser(HttpServletRequest request){
        HttpSession session = request.getSession();
        //如果sesion已经失效直接退出
        if (session.getAttribute("user")==null) return  "redirect:../views/login.jsp";
        Enumeration<String> names = session.getAttributeNames();

        //删除全部sesion
        while (names.hasMoreElements()){
            String s = names.nextElement();
            session.removeAttribute(s);
        }
        session.invalidate();


        //最终跳转到登录页
        return "redirect:../views/login.jsp";
    }

}
