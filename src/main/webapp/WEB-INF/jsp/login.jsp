<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/3/28
  Time: 下午2:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面login.jsp</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user/login" method="post">
    用户名:<input type="text" name="username" value="${user.username }"/><br/>
    密码:<input type="password" name="password" value="${user.password }"><br/>
    <input type="submit" value="login"/><br>
<%--    <font color="red">${errorMsg }</font>--%>
</form>
</body>
</html>
