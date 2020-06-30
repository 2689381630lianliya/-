package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.entiy.user;
import org.blog.service.blogserviceImpl;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Controller")

public class ShowArticle {
    @Resource
    blogserviceImpl service = null;
    @RequestMapping("Showuser")
    public String Showuser(HttpServletRequest request, Model model) throws IOException {
        //获取文章存储信息的map
        HttpSession session = request.getSession();
        Map<String, String> checkuserarticle=(Map<String, String>)session.getAttribute("checkuserarticle");
        util(request,model,checkuserarticle);
        return "showUserarticlePage";
    }
    @RequestMapping("Showall")
    public String Showall(HttpServletRequest request, Model model) throws IOException {
        //获取文章存储信息的map
        HttpSession session = request.getSession();
        Map<String, String> allarticle=(Map<String, String>)session.getAttribute("allarticle");
        util(request,model,allarticle);
        return "showarticlePage";
}
    @RequestMapping("Showqs")
    public String Showqs(HttpServletRequest request, Model model) throws IOException {
        //获取文章存储信息的map
        HttpSession session = request.getSession();
        List<article> link = (List<article>)session.getAttribute("link");
        Map<String, String> qsarticle = service.checkarticle(link);
        //添加访问记录


        util(request,model,qsarticle);
        return "forward:../views/showarticlePage.jsp";
    }
    //对文章进行打赏
    @RequestMapping("bookmoney")
        public String bookmoney(HttpServletRequest request,Model model){
        //在显示页面设置request域值
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String  count = request.getParameter("count");
        //获取前台传回的数据，打赏的金额
        int award = Integer.parseInt(request.getParameter("award"));
        Integer id = service.querybyname(name).getId();

        //将前页面的数据存起来然后跳回去
        model.addAttribute("count",count);
        model.addAttribute("name",name);
        model.addAttribute("title",title);
        model.addAttribute("content",content);
        //获取源数据
        int oldaward = service.queryaward(id);

        //当前账户因扣除相应的金钱
        int ii=100/0;
        String uname=(String)request.getSession().getAttribute("uname");
        user user = service.querybyname(uname);
        //在原数据上加上新增数据
        int i = service.addaward(award+oldaward, id,user);
//        service.reducemoney(user.getMoney()-award,user.getId());

        if (i==1) {
            return "showarticlePage";
        }else {
            return "err";
        }
        }

    public void util(HttpServletRequest request, Model model, Map<String, String> map){
        HttpSession session = request.getSession();
        //获取前端传来的文章标题与作者名
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        //为当前用户添加访问记录
        String address = map.get(title);
        service.addrecord(address,name,title,session);
        //传入一个文章标题供给修改
        session.setAttribute("oldtitle",title);
        int id = service.querybyname(name).getId();

        int count = service.querycount(id, title);

        user user =(user)session.getAttribute("user");
        if (id!=user.getId()){

            //排除用户自己查看自己文章  查看其他人文章访问数+1

            boolean b = service.querysign(name, title,session);
            if (b){

                service.altercount(count+1,id);
                service.addsign(name,title,session);
            }

        }
        //获取文章内容

        String content = null;
        try {
            content = service.open(map, title);

        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("count",count);
        model.addAttribute("name",name);
        model.addAttribute("title",title);
        model.addAttribute("content",content);

    }


}