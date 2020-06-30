<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/5
  Time: 22:47
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
    <title>个人中心-我的文章</title>
    <link rel="stylesheet" href="<%=basePath%>/css/index.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>/css/person.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>/css/person-userarticle.css" type="text/css" />
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
        <div class="title">我的文章</div>
        <table  >
          <c:forEach items="${userarticle}"  var="list" >
              <%--<tr>--%>
                  <%--<td><a href="#">${list.title}</a>　　　　　　　　　　　　${list.name}<a href="#"><img src="<%=basePath%>img/delete.png" /></a></td>--%>
              <%--</tr>--%>
              <tr>

                  <td width="500px"><a href="/Controller/Showuser?title=${list.title}&name=${list.name}">${list.title}</a></td>
                  <td>　　　　　</td>
                  <td>${list.name}</td>

                  <td ><a href="/Controller/deletearticle/${list.title}"><img src="<%=basePath%>img/delete.png" /></a></td>
              </tr>

          </c:forEach>
        </table>

        <div class="linkpage">
            <a href="/Controller/userarticlepage/1">首页</a>
            <a href="/Controller/userarticlepage/${currentpage-1}">上一页</a>
            <label>-${currentpage}-</label>
            <a href="/Controller/userarticlepage/${currentpage+1}">下一页</a>
            <a href="/Controller/userarticlepage/${pagecount}">末页</a>
            <label>共${sessionScope.get("pagecount")}页</label>
        </div>
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