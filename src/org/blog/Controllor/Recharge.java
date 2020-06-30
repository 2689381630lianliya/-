package org.blog.Controllor;

import org.blog.entiy.article;
import org.blog.entiy.user;
import org.blog.entiy.vip;
import org.blog.service.blogserviceImpl;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Controller")
//充值页面
public class Recharge {
    @Resource
    blogserviceImpl service = null;
    //充值页面 数据显示 用户名 用户vip等级 直接用sesion获取数据
    @RequestMapping("recharge")
    public String recharge(){


        return "rechargePage";
    }

    @RequestMapping("rechargeaddmoney")
    public String addmoney(@RequestParam("money") int money, @RequestParam("name")String name){
        int i = service.addmoney(money,name);
        //此处有待完善
        return "redirect:recharge";
    }


}
