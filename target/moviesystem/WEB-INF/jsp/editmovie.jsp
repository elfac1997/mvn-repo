<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/3
  Time: 下午2:22
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
    <title>编辑movie页面</title>
</head>
<body>
<form:form action="/movie/save" method="post" modelAttribute="returnMovie">
    <form:hidden path="movieId"/>
    movieName:<form:input path="movieName"/><br>
    releasetime:<form:input path="releaseTime"/><br>
    director:<form:input path="director"/><br>
    leadactors:<form:input path="leadActors"/><br>
    picture:<form:input path="picture"/><br>
    averating:<form:input path="averating"/><br>
    numrating:<form:input path="numrating"/><br>
    description:<form:input path="description"/><br>
    typelist:<form:input path="typeList"/><br>
    <input type="submit" value="提交"/>
</form:form>
</body>
</html>