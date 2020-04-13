<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/3/23
  Time: 下午2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Movie列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        td {
            text-align: center;
        }

        .td2 {
            text-align: right;
        }

        .table1 {
            border: 1px solid #ddd;
            width: 900px;
        }

        thead {
            background-color: lightblue;
        }
    </style>
</head>
<body>
欢迎你：${currentUser}
<br>
<a href="logout">安全退出</a>
<br><a href="/user/menu?uid=${uid}">return to menu</a><br>
<hr>
<a href="/movie/addMovie"> 添加movie</a><br>
<c:if test="${empty requestScope.pagemsg}">
    没有任何用户信息！
</c:if>
<c:if test="${!empty requestScope.pagemsg}">
    <table border="1" cellpadding="10" cellspacing="0" class="table1">
        <thead>
        <tr>
            <td>电影编号</td>
            <td>电影名字</td>
            <td>电影年份</td>
            <td>电影导演</td>
            <td>电影poster</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        </thead>
        <c:forEach items="${requestScope.pagemsg.lists}" var="m">
            <tr>
                <th>${m.movieId}</th>
                <th>${m.movieName}</th>
                <th>${m.releaseTime}</th>
                <th>${m.director}</th>
                <th><img src='${m.picture}'></th>
                <th><a href="/movie/edit?id=${m.movieId}">Edit</a></th>
                <th><a href="/movie/delete?id=${m.movieId}" onclick="return confirm('确定要删除吗')">Delete</a></th>
            </tr>
        </c:forEach>
    </table>
</c:if>

<table border="0" cellspacing="0" cellpadding="0" width="900px">
    <tr>
        <td class="td2">
            <span>第${requestScope.pagemsg.currPage }/ ${requestScope.pagemsg.totalPage}页</span>&nbsp;&nbsp;
            <span>总记录数：${requestScope.pagemsg.totalCount }&nbsp;&nbsp;每页显示:${requestScope.pagemsg.pageSize}</span>&nbsp;&nbsp;
            <span>
       <c:if test="${requestScope.pagemsg.currPage != 1}">
           <a href="${pageContext.request.contextPath }/movie/allMovie?currentPage=1">[首页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath }/movie/allMovie?currentPage=${requestScope.pagemsg.currPage-1}">[上一页]</a>&nbsp;&nbsp;
       </c:if>

       <c:if test="${requestScope.pagemsg.currPage != requestScope.pagemsg.totalPage}">
           <a href="${pageContext.request.contextPath }/movie/allMovie?currentPage=${requestScope.pagemsg.currPage+1}">[下一页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath }/movie/allMovie?currentPage=${requestScope.pagemsg.totalPage}">[尾页]</a>&nbsp;&nbsp;
       </c:if>
   </span>
        </td>
    </tr>
</table>
</body>
</html>
