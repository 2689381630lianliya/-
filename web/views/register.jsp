<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/5
  Time: 22:39
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
    <title>注册</title>
    <link rel="stylesheet" href="<%=basePath%>css/rigister.css" type="text/css" />
</head>
<body>
<header class="mui-card-header"><h3 class="mui-title">欢迎注册</h3>

    <span class="righttop">已有账号?<a href="login.jsp">请登录</a></span>
</header>
<div class="mui-center">
    <div class="mui-text-center">
        <form action="/Controller/registeruser" method="post">

            <label class="" >用　户　名:</label>
            <label for="username" class="login-lable name-lable"></label>
            <input class="text name-lable" type="text" name="username" required pattern="^[\w]{3,10}$" /><br>
            <label>密　　　码:</label>
            <label for="password" class="login-lable pswd-lable"></label>
            <input  class="text pswd-lable" type="password" name="password" required  pattern="^[\w]{5,16}$"/><br>
            <label>性　　　别:</label>
            <label for="sex1" class="mui-sex"><input id="sex1" type="radio" name="sex" value="男">男生</label>
            <label for="sex2" class="mui-sex"><input id="sex2" type="radio" name="sex" value="女">女生</label><br>
            <label>地　　　址:</label>
            <select name="address">
                <option value="北京">北京</option>
                <option value="四川">四川</option>
                <option value="广东">广东</option>
                <option value="江西">江西</option>
                <option value="重庆">重庆</option>
                <option value="内蒙古">内蒙古</option>
                <option value="浙江">浙江</option>
                <option value="新疆">新疆</option>
                <option value="黑龙江">黑龙江</option>
                <option value="山东">山东</option>
                <option value="福建">福建</option>
                <option value="湖北">湖北</option>
                <option value="湖南">湖南</option>

            </select><br>

            <label class="code">验　证　码:</label>


            <input  class="text code-lable" type="text" name="code" required/><br>

            <iframe class="getcode" src="/Controller/withincode" name="my_iframe" width="65px" height="38px"></iframe> <br/>
            <input id="rigister" type="submit" value="注册" />
        </form>
        <!-- <label class="securitycode"></label><br> -->
    </div>
</div>
<HR  style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="80%" color=#aaaa7f SIZE=3>
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
