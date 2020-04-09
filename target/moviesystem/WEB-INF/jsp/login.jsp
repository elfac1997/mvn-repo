<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <font color="red">${errorMsg }</font>
</form>
<a href="/user/registerUser"> register</a><br>
<a href="logout">安全退出</a>
<shiro:hasRole name="admin">
    具备admin角色才能看到这句话<br>
</shiro:hasRole>

<shiro:hasRole name="user">
    具备user角色才能看到这句话<br>
</shiro:hasRole>
</body>
</html>
