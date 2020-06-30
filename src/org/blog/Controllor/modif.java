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
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

import static javax.swing.text.html.CSS.getAttribute;

@Controller
@RequestMapping("Controller")
//修改数据页面
public class modif {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("modifuser")
    //修改个人信息
    public String modifuser(HttpServletRequest request){
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        HttpSession session = request.getSession();
        user newu =(user)session.getAttribute("user");

        newu.setSex(sex);
        newu.setAddress(address);
        //调用修改方法
        int i = service.alteruser(newu);
        //更改sesion内的数据
        session.setAttribute("user",newu);
        //修改完之后跳回个人中心
        if(i==1) {
            return "redirect:center";
        }else{
            return "err";
        }
    }
    @RequestMapping("modifpass")
    //修改密码
    public String modifpass(HttpServletRequest request){
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String ensurepass = request.getParameter("ensurepass");
        HttpSession session = request.getSession();
        user user =(user)session.getAttribute("user");
        //判断源密码是否正确，然后判断两次输入的新密码是否正确
        if (!user.getPassword().equals(oldpass)&&newpass.equals(ensurepass)){
            return "err";
        }
        service.modifpass(newpass,session);


        return "redirect:Logout";

    }
    @RequestMapping("modifarticle")
    //修改文章
    public String modifarticle(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        //获得旧文章标题
        String oldtitle=(String)session.getAttribute("oldtitle");
//        session.removeAttribute("oldtitle");
        //获得文章新标题
        String newtitle = request.getParameter("title");
        //文章内容
        String content = request.getParameter("content");
        String name = request.getParameter("name");
        Map<String,String> userarticle = (Map<String,String>)session.getAttribute("checkuserarticle");
        //获得文章的地址
        String address = userarticle.get(oldtitle);
        boolean b=false;
        try {
             b= service.modifyarticle(session,content, newtitle, address,oldtitle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        if(b) {
            model.addAttribute("title",newtitle);
            model.addAttribute("content",content);
            model.addAttribute("name",name);
            int id =(int)session.getAttribute("id");
            int count = service.querycount(id, newtitle);
            model.addAttribute("count",count);

            return "showUserarticlePage";
        }else{
            return "err";
        }
    }
    @RequestMapping("modifarticleData")
    public String modifarticleData(HttpServletRequest request, Model model){
        String title = request.getParameter("title");
//        String content = request.getParameter("content");
//        System.out.println(content);
        HttpSession session = request.getSession();
        Map<String, String> map=(Map<String, String>)session.getAttribute("checkuserarticle");
        String content = null;
        try {
           content= service.open(map,title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(content);
        model.addAttribute("title",title);
        model.addAttribute("content",content);
        return "modifarticlePage";
    }
    @RequestMapping("modifuserdata")
    public String modifuserdata(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        vip vip = (vip)session.getAttribute("vip");
        user user = (user)session.getAttribute("user");
        model.addAttribute("vip",vip);
        model.addAttribute("user",user);
        return "person-modifuser";
    }
    @RequestMapping("modifpasswordData")
    public String modifpasswordData(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        vip vip = (vip)session.getAttribute("vip");
        model.addAttribute("vip",vip);

        return "MidfPasswordPage";

    }
}
