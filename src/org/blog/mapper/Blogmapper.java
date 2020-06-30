package org.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.blog.entiy.*;

import java.util.List;

public interface Blogmapper {
  //用于登录查询用户
  int queryuser(@Param("user") user user);
  //注册用户
  int rigisteruser(@Param("user")user user);
  int registerlogintable(int id);
  int addvip(@Param("user")user user);
  //  void article(user user);
  //根据姓名查询所有信息用作个人资料显示
  user querybyname(String username);
  //查询主页前十文章地址
  List<article> queryqianshi();
  //查询当前账户vip
  vip queryvip(String username);
  //更改vip等级
  int updatevip(int [] arr);
  //充值后增加书币 然后增加会员成长值
  int addmoney(int [] arr);
  int addcourse(int [] arr);
  //增加打赏
  int addaward(int [] arr);
  //更改个人信息
  int alteruser (@Param("user")user user);
  //搜索标题获得文章分页
  List<article> querytitle(Object [] o);
  //查询全部文章
  List<article> queryalltitle(String title);
  //增加访问次数
  void altercount(int array[]);
  //用户查看自己的文章
  List<article> queryuserarticle(int id);
  //分页查询自己的文章
  List<article> queryuserarticle2(int []array);
  //查看文章地址
  List<article> queryarticleaddress(Object[]o);
  //删除文章
  int deletearticle(Object[] o);
  //根据id查询用户信息
  user queryuserbyid(int id);
  //查询文章访问人数
  int querycount(Object[] o);
  //修改文章地址和标题
  int updatearticleaddress(Object [] o);
  //修改密码
  void alterpass(@Param("user")user u);
  //根据id查用户名
  String querynamebyId(int id);
  //添加浏览历史
  int addrecord(@Param("record") record record);
  //查询用户的浏览记录
  List<record> queryrecord(int array[]);
  //用户注册时执行
  void createrecordtable(int id);
  //根据id查地址
  String queryaddress(int id);
  //查询用户的全部浏览记录
  List<record> queryallrecord(int id);
  //查询某文章的打赏数目
  int queryaward(int id);
  //添加文章
  int createarticle(@Param("article") article article);
  //文章数加1
  int addamount(int array[]);
  //修改登录信息
  int modiflogin(int arr[]);
  //查询登录信息
  login querylogin(int id);
  //退出服务器时清空所有用户登录状态
  void clearLogin();

}
