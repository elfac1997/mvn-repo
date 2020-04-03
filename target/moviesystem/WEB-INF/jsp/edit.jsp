<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/2
  Time: 上午11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>编辑user页面</title>
</head>
<body>
欢迎你：${currentUser}
<hr>
<form:form action="/user/save" method="post" modelAttribute="returnUser">
    <form:hidden path="id"/>
    用户名:<form:input path="username"/><br>
    密码:<form:input path="password"/><br>
    roleid:
    <form:select path="roleId">
        <form:option value="">请选择role</form:option>
        <form:option value="1">admin</form:option>
        <form:option value="2">user</form:option>
    </form:select><br>
    <input type="submit" value="提交"/>
</form:form>
</body>
</html>