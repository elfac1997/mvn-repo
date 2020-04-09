<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/3
  Time: 下午6:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>编辑rating页面</title>
</head>
<body>
欢迎你：${currentUser}
<hr>
<a href="logout">安全退出</a>
<form:form action="/user/saveRating" method="post" modelAttribute="returnRating">
    用户名:<form:input path="userId"/><br>
    movieid:<form:input path="movieId"/><br>
    <shiro:hasRole name="user">
        具备user角色才能看到这句话
    </shiro:hasRole>
    <shiro:hasRole name="admin">
    具备admin角色才能看到这句话
    </shiro:hasRole><br>
    rating:
        <form:select path="rating">
            <form:option value="">请选择rating</form:option>
            <form:option value="1">2</form:option>
            <form:option value="2">2</form:option>
            <form:option value="3">3</form:option>
            <form:option value="4">4</form:option>
            <form:option value="5">5</form:option>
        </form:select><br>
    <input type="submit" value="提交"/>
</form:form>
</body>
</html>