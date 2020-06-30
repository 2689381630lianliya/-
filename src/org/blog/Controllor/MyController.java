package org.blog.Controllor;



import org.blog.entiy.article;
import org.blog.entiy.login;
import org.blog.entiy.user;
import org.blog.service.ServletLife;
import org.blog.service.blogserviceImpl;
import org.blog.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("Controller")
//登陆界面
public class MyController  {
       @Resource
       blogserviceImpl  service = null;
       @Resource
       ServletLife life = null;

   // private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int id =0;
    @RequestMapping("loginuser")
    public String login(HttpServletRequest request, HttpServletResponse response,Map map) throws IOException {
        //创建线程池，连接池中有多个线程，每一个线程都可以处理一次连接

        String username = request.getParameter("username");
        //session
        HttpSession session = request.getSession();
        String password=request.getParameter("password");
        //判断用户是否要记住账号密码
        String flag = request.getParameter("flag");


        user u = new user(username,password);
        //获取用户信息
        map.put("user",u);
        user user = service.querybyname(username);
        id=user.getId();
        //查询登录状态
        login login = service.querylogin(user.getId());
//        FileOperateUtil.
        if (login.isStatus()){
            return "login";
        }



        int i = service.queryuser(u);
        //如果登录成功
        if (i==1){
            ServletContext context = request.getServletContext();
            Integer Logincount = (Integer)context.getAttribute("Logincount");
             if (Logincount==null) Logincount=0;
            System.out.println(Logincount);
            //在线人数+1
            //创建一个标识确保当前用户刷新页面不会重复添加
            boolean flag2 =true;
            if(flag2) {
                context.setAttribute("Logincount", ++Logincount);
            flag2=false;
            }

            //修改登录状态
            service.modiflogin(1,user.getId());

//        System.out.println("当前用户的线程"+Thread.currentThread().getName());
            //将当前用户Id放入Applocationcontext域 key为session ID
            request.getServletContext().setAttribute(session.getId(),user.getId());
            session.setAttribute("id",user.getId());
            user.setAddress(service.queryaddress(user.getAddress_id()));
            session.setAttribute("user",user);
        //获取cookies
        Cookie[] cookies = request.getCookies();


        //获取前十文章数据
        List<article> articles = service.queryqianshi();

        //为 文章类添加作者姓名
        List<article> newlist = getnewlist(articles);


            session.setAttribute("uname",username);
            //将标题放入index页面
            session.setAttribute("link",newlist);

//
            map.put("link",newlist);
                for (Cookie cookie : cookies) {
                    if (!cookie.getValue().equals(username)) {
                        if (flag != null) {

                            //创建cookie
                            CookieUtil.getCookie(username, password, request, response);
                        }


                }
            }
            if (flag==null)
                {
                    //删除cookie
                    CookieUtil.deleteCookie(cookies,response);
                }
            //
            return "redirect:../views/index.jsp";
        }else{
            return "redirect:../views/login.jsp";
        }

    }

    private List<article> getnewlist(List<article> articles) {
     List<article>  list= new ArrayList<>();
        for (article article : articles) {
            String name = service.querynamebyId(article.getId());
            article.setName(name);
            list.add(article);

        }
        return list;
    }




}
