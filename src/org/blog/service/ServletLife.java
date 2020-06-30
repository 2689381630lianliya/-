package org.blog.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

@Component
public class ServletLife implements HttpSessionListener , ServletContextListener {
    private ApplicationContext context = null;
    private int count =0;



    public int getCount(){
        return count;
    }
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//        int i = 0;
//        Enumeration<String> attributeNames = httpSessionEvent.getSession().getAttributeNames();
////         while(attributeNames.hasMoreElements()){
////             System.out.println(attributeNames.nextElement());
////         }
//        //确保创建sesion并有数据才增加
//
//        //确保第一次创建sesion才添加
//        if (attributeNames.hasMoreElements()) {
//            System.out.println(attributeNames.nextElement());
//            System.out.println(Thread.currentThread().getName());
//            if (i == 0) {
//                i++;
//                count++;
//                httpSessionEvent.getSession().getServletContext().setAttribute("onlineCount", count);
//            }
//        }
    }




    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        //确认在线人数不会0之下
        int Logincount = (int)servletContext.getAttribute("Logincount");
        if(Logincount>0) Logincount--;
        servletContext.setAttribute("Logincount",Logincount);
        String id1 = httpSessionEvent.getSession().getId();
        System.out.println(id1);
        if (context==null){
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
        }
        blogservice bean = context.getBean("blogserviceImpl", blogservice.class);
        Integer id =(int)servletContext.getAttribute(httpSessionEvent.getSession().getId());
//        Enumeration<String> attributeNames = servletContext.getAttributeNames();
//        while (attributeNames.hasMoreElements()){
//            System.out.println("当前线程session："+httpSessionEvent.getSession().getId());
//            System.out.println("contextsession："+attributeNames.hasMoreElements());
//        }
        if(id!=null)
        bean.modiflogin(0,id);


    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if(context==null){
            context= new ClassPathXmlApplicationContext("applicationContext.xml");
        }

        blogservice bean = context.getBean("blogserviceImpl", blogservice.class);
          bean.clearLogin();

    }
}