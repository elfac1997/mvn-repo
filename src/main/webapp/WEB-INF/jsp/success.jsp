<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/1
  Time: 下午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>


欢迎你,<shiro:principal/>!<br>

<shiro:hasRole name="admin">
具备admin角色才能看到这句话<br>
</shiro:hasRole>


<shiro:hasRole name="user">
具备user角色才能看到这句话<br>
</shiro:hasRole>


<%--<shiro:hasPermission name="/user/allUser">--%>
<%--具备admin权限才能看到这句话<br>--%>
<%--</shiro:hasPermission>--%>


<%--<shiro:hasPermission name="student:update">--%>
<%--具备student:update权限才能看到这句话<br>--%>
<%--</shiro:hasPermission>--%>
<%--<br>--%>


<%--<shiro:hasPermission name="{student:update,user:*}">--%>
<%--具备student:update,user:*权限才能看到这句话<br>--%>
<%--</shiro:hasPermission>--%>
admin permissions:<br>
<a href="/user/allUser">alluser,需要admin权限才能访问</a><br>
<a href="/movie/allMovie">allmovie权,需要admin限才能访问</a><br>
<a href="/user/allRating">view all movie ratings from user,需要admin限才能访问</a><br>
<%--<a href="/user/deleteRating">delete movie ratings from user,需要admin限才能访问</a><br>--%>
<br><br>
user permissions:<br>
<a href="/user/edit">change password or username</a><br>
<a href="/user/userRating">view movie ratings from user</a><br>
<%--<a href="/user/deleteuserRating">delete movie ratings from user,需要admin限才能访问</a><br>--%>
<br><br>
<a href="logout">安全退出</a>
</body>
</html>
