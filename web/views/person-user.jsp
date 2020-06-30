<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/5
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <%
        String path = request.getContextPath();
        System.out.println(path);
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"views/";
    %>
    <meta charset="utf-8">
    <title>个人中心</title>
    <link rel="stylesheet" href="<%=basePath%>/css/index.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>/css/person-user.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>/css/person.css" type="text/css" />
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
        <a href="/Controller/modifpasswordData">修改密码</a>
        <a href="/Controller/Logout">退出登录</a>
    </div>
</header>
<div class="mui-mid">
    <div class="left-box">
        <div class="userinformation">
            <ul>
                <li>　　用户名:${uname}</li>
                <li>　　vip等级:${vip.grade}</li>
                <li>　　成长值:${vip.course}</li>
            </ul>
        </div>
        <div class="mui-btn-link">

            <div class="one"><a href="/Controller/center">个人信息</a><br/></div>
            <br />
            <div class="two"><a href="/Controller/Myarticle">我的文章</a><br /></div>
            <br />
            <div class="three">	<a href="/Controller/queryrecord">浏览历史</a><br /></div>
        </div>
    </div>

    <div class="right-box">
        <div class="title">用户基本信息</div>
        <label>用户名:${user.username}</label><br />
        <label>性　别:${user.sex}</label><br />
        <label>地　址:${user.address}</label><br />
        <div id="modif"><a href="/Controller/modifuserdata">修改个人信息</a></div>
    </div>

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