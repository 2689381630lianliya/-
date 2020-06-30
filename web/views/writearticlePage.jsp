<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/5
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <%
        String path = request.getContextPath();
        System.out.println(path);
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"views/";
    %>
    <meta charset="utf-8">
    <title>主页</title>
    <link rel="stylesheet" href="<%=basePath%>css/index.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>css/writearticle.css" type="text/css" />
</head>
<body>
<header class="mui-header">

    <div class="top-left">
        <a href="/Controller/homepage">首页</a>
        <a href="/Controller/recharge">充值</a>
        <a href="#">下载</a>
        <a href="#">问答</a>
    </div>
    <div class="top-center">
        <form action="/Controller/seek">
            <input class="text" type="text" name="title"  placeholder="输入标题进行搜索"/>
            <input class="submit"type="submit" value="　" />
        </form>
    </div>
    <div class="top-right">
        <label>欢迎您,${sessionScope.get("uname")}</label>
        <a href="/Controller/center">个人中心</a>
        <a href="/Controller/Logout">退出登录</a>
    </div>
</header>
<div class="mui-center">
    <form  class="write" action="/Controller/writearticle">
        <div class="text">
            <input type="text" name="title" style="font-size: 18px"  placeholder="请输入标题" />
            <div class="write-content">

                <textarea  name="content" style="font-size: 18px" cols="140" rows="40"></textarea></div>
        </div>
        <div id="modif"><input class="submit" type="submit" value="提　　　交"></div>

    </form>


</div>
<!-- <HR  style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="80%" color=#aaaa7f SIZE=3> -->
<br>

<div class="mui-bottom">
    <a  href="#">联系我们</a>|
    <a href="#">人才招聘</a>|
    <a  href="#">商家入驻</a>|
    <a  href="#/">手机</a>|
    <a  href="#">友情链接</a>|
    <a href="#">销售联盟</a>|
    <a target="_blank" href="//en.jd.com/" clstag="pageclick|keycount|20150112ABD|9">English Site</a>
</div>

<div class="copyright">Copyright&copy;2004-2020&nbsp;&nbsp;&nbsp;我的版权所有</div>

</body>


</html>
