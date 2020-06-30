<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/5
  Time: 22:44
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
    <title>${title}</title>
    <link rel="stylesheet" href="<%=basePath%>css/index.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>css/showarticlePage.css" type="text/css" />
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
<div id="showarticle">

    <h2>${title}</h2>
    <dl>
        <dt>${name}</dt>
        <dd>${content}</dd>
    </dl>
    <div class="showbottom">
        <span>当前文章访问人数:${count}人</span>
        <form action="/Controller/bookmoney">
				 <span>支持作者<input class="text" type="text" name="award"  placeholder="输入打赏金额"/>
                     <input name="name" value="${name}" style="display: none"/>
                      <input name="title" value="${title}" style="display: none"/>
                      <input name="content" value="${content}" style="display: none"/>
                     <input name="count" value="${count}" style="display: none"/>

				 <input class="submit" type="submit"value="打赏" />
				 </span>
        </form>

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