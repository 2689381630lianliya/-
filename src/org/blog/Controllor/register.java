package org.blog.Controllor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.blog.entiy.user;
import org.blog.service.blogserviceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Controller
@RequestMapping("Controller")
//注册页面
public class register {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("registeruser")
    public String Rigister(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        //验证码
        String code1=(String)request.getSession().getAttribute("code");
        String code = request.getParameter("code");

        if (!code.equals(code1)){
            System.out.println(123);
            return "err";
        }
        String username = request.getParameter("username");
        String psssword = request.getParameter("password");
        String address = request.getParameter("address");

        String sex = request.getParameter("sex");


        user user =new user(username,psssword,sex,address);

        int rigisteruser = service.rigisteruser(user);

        if (rigisteruser==1){
        return "redirect:../views/login.jsp";
        }else{
            System.out.println(123);
            return "err";
        }
    }
//@RequestMapping("code")
////@Test
//    public String  createcode(Model model) {
//    String code = this.getcode();
//    model.addAttribute("code",code);
//    return "register";
//
//}
@RequestMapping("withincode")
public String flushcode(Model model,HttpServletRequest request){
    String code = this.getcode();
    model.addAttribute("code",code);
    request.getSession().setAttribute("code",code);
    return "code";
}

public String getcode(){
    Random random = new Random();
    String code="qwertyuiopasdfghjklzxcvbnm123456789QWERTYUIOPASDFGHJKLZXCVBNM";
    StringBuilder sb = new StringBuilder();
    char[] chars = code.toCharArray();
    for (int i = 0; i < 4; i++) {
        int ii = random.nextInt(61);
        sb.append(String.valueOf(chars[ii]));
    }
    code=sb.toString();
    return code;
}

}
