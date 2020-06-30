<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/5/29
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>

       <a href="/Controller/test3">搜索</a>
       ${data[1]}
       <form action="Controller/testbody" method="post">
         <input type="text" name="id"/><br/>
         <input type="text" name="adres" /><br/>
         <input type="submit" />
       </form>
  </body>
</html>
