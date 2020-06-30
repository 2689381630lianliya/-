<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/5
  Time: 22:42
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
            <input class="text" type="text" name="title" value="${title}" placeholder="输入标题进行搜索"/>
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

    <table  class="mui-table">
        <c:forEach items="${allarticle}" var="list">
        <tr >
            <td class="td_lable" width="500" >
                <a href="/Controller/Showall?title=${list.title}&name=${list.name}">
                    ${list.title}</a></td>
            <td>　　　　　</td>
            <td>${list.name}</td>

        </tr>
        </c:forEach>
    </table>
    <div class="linkpage">
        <a href="/Controller/tiaozhuan//0">首页</a>
        <a href="/Controller/tiaozhuan/${currentpage}-1">上一页</a>
        <label>-${currentpage}-</label>
        <a href="/Controller/tiaozhuan/${currentpage}+1">下一页</a>
        <a href="/Controller/tiaozhuan/${sessionScope.get("pagecount")}">末页</a>
        <label>共${sessionScope.get("pagecount")}页</label>
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
