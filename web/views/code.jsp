<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/5
  Time: 22:35
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
    <style type="text/css">
        body{
            background-color: #ffffff;
        }
        a{
            text-decoration: none;
            color: #222222;
        }
    </style>
    <title></title>
</head>
<body>
<a href="/Controller/withincode">${code}</a>
</body>
</html>
