<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/5/31
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <%
        String path = request.getContextPath();
        System.out.println(path);
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"views/";
    %>
    <%
        String uname ="";
        String upswd="";
        Cookie[] cookies = request.getCookies();

        if (cookies!=null) {
            for (Cookie cookie : cookies) {

                if ("uname".equals(cookie.getName())) {
                    uname = cookie.getValue();


                } else if ("upswd".equals(cookie.getName())) {
                    upswd = cookie.getValue();

                }
            }
        }
    %>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="<%=basePath%>css/login.css" type="text/css" />
    <title>登录</title>
</head>
<body>
<header>欢迎登录</header>
<div id="back">
    <div class="ui-page-login">
        <form action="/Controller/loginuser" method="post">
            <div class="name">
                <div class="mui-content">
                    <label for="username" class="login-lable name-lable"></label>
                    <input class="text" id="username" type="text" value="<%=uname%>" placeholder="用户名/邮箱/手机号" value="" name="username" required pattern="^[\w]{3,10}$" />
                    <label for="password" class="login-lable pswd-lable"></label>
                    <input class="text" id="password" type="password" value="<%=upswd%>" placeholder="密码"  name="password" required pattern="^[\w]{5,16}$"  /><br/>
                    <div class="jizhumma">
                        <input type="checkbox" id="flag" name="flag" checked />
                        <label for="flag">记住密码</label>
                    </div>
                </div>

                <div class="zclu">
                    <div class="box">
                        <a class="zc" href="register.jsp">注&nbsp;册</a>
                    </div>
                    <div class="box2">
                        <input class="dlu" type="submit" value="登&nbsp;录" />
                    </div>
                </div>

            </div>
        </form>

    </div>
</div>
<!-- <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="80%" color=#aaaa7f SIZE=3> -->
<!-- <hr /> -->
<br>
<div class="mui-bottom">
    <a href="#"> 关于我们</a>|
    <a  href="#">联系我们</a>|
    <a href="#">人才招聘</a>|
    <a  href="#">商家入驻</a>|
    <a  href="#/">手机</a>|
    <a  href="#">友情链接</a>|
    <a href="#">销售联盟</a>|

    <a href="#" clstag="pageclick|keycount|20150112ABD|9">English Site</a>
</div>
<div class="copyright">Copyright&copy;2004-2020&nbsp;&nbsp;&nbsp;我的版权所有</div>




</body>
</html>

