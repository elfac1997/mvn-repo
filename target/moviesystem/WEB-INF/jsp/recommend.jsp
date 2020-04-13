<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/13
  Time: 下午3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>recommend result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
guess you might like
<table class="layui-table">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>电影海报</th>
        <th>电影名称</th>
        <th>上映时间</th>
        <th>导演</th>
        <th>主演</th>
        <th>简介</th>
        <th>平均分</th>
        <th>类型</th>
        <th>收藏</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.list}" var="m">
        <tr>
            <td><img src='${m.picture}'></td>
            <td>${m.movieName}</td>
            <td>${m.releaseTime}</td>
            <td>${m.director}</td>
            <td>${m.leadActors}</td>
            <td>${m.description}</td>
            <td>${m.averating}</td>
            <td>${m.typeList}</td>
            <td><a href="/movie/like?id=${m.movieId}">like!</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
