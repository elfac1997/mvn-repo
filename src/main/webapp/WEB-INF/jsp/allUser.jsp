<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/3/24
  Time: 下午1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <style type="text/css">

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
<!--<table border="1" cellpadding="10" cellspacing="0" class="table1">-->
<table class="layui-table">

    <thead>
    <tr>
        <td>用户ID</td>
        <td>用户名</td>
        <td>密码</td>
        <td>身份</td>
        <td>编辑</td>
        <td>删除</td>
    </tr>
    </thead>
    <c:forEach items="${requestScope.pagemsg.lists}" var="u">
        <tr>
            <th>${u.id }</th>
            <th>${u.username }</th>
            <th>${u.password }</th>
            <th>${u.roleId }</th>
            <th><a href="/user/edit?uid=${u.id}">编辑</a></th>
            <th><a href="/user/delete?uid=${u.id}" onclick="return confirm('确定要删除吗')">删除</a></th>
        </tr>
    </c:forEach>
</table>

<table border="0" cellspacing="0" cellpadding="0" width="900px">
    <tr>
        <td class="td2">
            <span>第${requestScope.pagemsg.currPage }/ ${requestScope.pagemsg.totalPage}页</span>&nbsp;&nbsp;
            <span>总记录数：${requestScope.pagemsg.totalCount }&nbsp;&nbsp;每页显示:${requestScope.pagemsg.pageSize}</span>&nbsp;&nbsp;
            <span>
       <c:if test="${requestScope.pagemsg.currPage != 1}">
           <a href="${pageContext.request.contextPath }/user/allUser?currentPage=1">[首页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath }/user/allUser?currentPage=${requestScope.pagemsg.currPage-1}">[上一页]</a>&nbsp;&nbsp;
       </c:if>

       <c:if test="${requestScope.pagemsg.currPage != requestScope.pagemsg.totalPage}">
           <a href="${pageContext.request.contextPath }/user/allUser?currentPage=${requestScope.pagemsg.currPage+1}">[下一页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath }/user/allUser?currentPage=${requestScope.pagemsg.totalPage}">[尾页]</a>&nbsp;&nbsp;
       </c:if>
   </span>
        </td>
    </tr>
</table>
</body>
</html>
