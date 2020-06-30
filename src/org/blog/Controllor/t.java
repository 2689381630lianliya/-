package org.blog.Controllor;

import org.blog.entiy.user;
import org.blog.service.blogserviceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

@Controller
@RequestMapping("Controller")
public class t {
    @Resource
    blogserviceImpl  ser= null;
    @RequestMapping("text1")
    public String text(HttpServletRequest request){
        System.out.println(request.getParameter("name"));



        return "index";
    }
//    @Test
//    public void t (){
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        blogserviceImpl bean = applicationContext.getBean("blogserviceImpl", blogserviceImpl.class);
//        user user = new user("liao", "lianliya");
//        int queryuser = bean.queryuser(user);
//        System.out.println(queryuser);
//    }
    @Test
    public void t (){
        File file = new File("D:\\Users\\a\\1.txt");
        file.mkdirs();
        System.out.println(file.exists());
//        String content ="  @Test\n" +
//                "//    public void t (){\n" +
//                "//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(\"applicationContext.xml\");\n" +
//                "//        blogserviceImpl bean = applicationContext.getBean(\"blogserviceImpl\", blogserviceImpl.class);\n" +
//                "//        user user = new user(\"liao\", \"lianliya\");\n" +
//                "//        int queryuser = bean.queryuser(user);\n" +
//                "//        System.out.println(quer";
//        try {
//            FileOutputStream out = new FileOutputStream(file);
//            out.write(content.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    @Test
    public void test() throws IOException {
//        Properties pro = new Properties();
//        pro.setProperty("liao","0");
//        FileOutputStream out = new FileOutputStream("a.properties");
//        pro.store(out,"Ab");
          File file = new File("t.txt");
          file.createNewFile();
          FileOutputStream out = new FileOutputStream(file,true);
          Properties p = new Properties();
          p.setProperty("zs","ww");
          p.store(out,null);
    }
    @RequestMapping("test3")
    public String Test3(Model model,HttpServletRequest request){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        request.setAttribute("data",list);
//        user user = new user();user.setId(4);
//        request.getSession().setAttribute("data",user);
//        request.getSession().setAttribute("data","hello");
//        model.addAttribute("data",new ArrayList<>().add("hello"));
        return "forward:../index.jsp";
    }

}
