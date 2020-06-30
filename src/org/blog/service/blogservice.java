package org.blog.service;

import org.blog.entiy.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface blogservice {

    //用于登录查询用户
    int queryuser(user user);
    //注册用户
    int rigisteruser(user user);
    //根据姓名查询所有信息用作个人资料显示
    user querybyname(String username);
    //查询主页前十文章地址
    List<article> queryqianshi() throws IOException;
    //查询当前账户vip
    vip queryvip(String username);
    //更改vip等级 自动
    int updatevip(int grade, int sid);
    //充值后增加书币 然后增加会员成长值
    int addmoney(int  money,String name);
    //增加打赏
    int addaward(int award ,int id,user user);
    //更改个人信息
    int alteruser (user user);
    //搜索标题获得文章
    List<article> querytitle(String title,int count);
    //查询全部文章
    List<article> queryalltitle(String title);
    //增加访问次数
    void altercount(int count ,int id);
    //查询文章访问人数
    int querycount(int id,String title);

    //分页查询用户的文章
    List<article> queryuserarticle2(int page, HttpSession s);
    //用户查看自己的文章
    List<article> queryuserarticle(HttpSession s);
    //用户点进标题 查看文章
    Map<String,String> checkauserrticle(String title) throws IOException;
    //查看文章所有标题
    Map<String,String> checkarticle(List<article> list);
    //查看搜索的文章
    String open(Map<String,String> map,String title) throws IOException;
    //删除文章
    boolean deletearticle(String title,List<article> list);
    //修改文章
    boolean modifyarticle(HttpSession s,String content,String title,String address,String oldtitle) throws IOException;
    //修改密码
    void modifpass (String password,HttpSession s);
    //根据id查用户名
    String querynamebyId(int id);
    //添加浏览历史
    int addrecord (String address,String name,String title,HttpSession s);
    //查询用户的浏览历史(分页)
    List<record> queryrecord(int page,HttpSession s);
    //根据id查地址
    String queryaddress(int id);
    //查询用户所有记录
    List<record> queryallrecord(HttpSession s);
    //查询某文章的打赏数目
    int queryaward(int id);
    //扣除用户的钱
    int reducemoney(int money,int id);
    //用户添加文章
    int createarticle(String title,String name,String content,HttpSession s);
    //查询当前用户对某一文章的访问情况
    boolean querysign (String name,String title,HttpSession s);
    //记录当前文章被那些人访问过
    void addsign(String name,String title,HttpSession s);
    //修改登录信息
    int modiflogin(int status,int id);
    //查询登录信息
    login querylogin(int id);
    //退出服务器时清空所有用户登录状态
      void clearLogin();
    }

