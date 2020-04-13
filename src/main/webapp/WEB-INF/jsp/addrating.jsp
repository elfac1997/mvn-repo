<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/14
  Time: 上午1:06
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
    <title>电影评分</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <style>
        body {background-image:url("/layui/images/purewhite.jpg");height:100%;width:100%;}
        #container{height:100%;width:100%;}
        input:-webkit-autofill {-webkit-box-shadow:inset 0 0 0 1000px #fff;background-color:transparent;}
        .admin-login-background {width:300px;height:300px;position:absolute;left:50%;top:40%;margin-left:-150px;margin-top:-100px;}
        .admin-header {text-align:center;margin-bottom:20px;color:#ffffff;font-weight:bold;font-size:40px}
        .admin-input {border-top-style:none;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;height:50px;width:300px;padding-bottom:0px;}
        .admin-input::-webkit-input-placeholder {color:#a78369}
        .layui-icon-username {color:#a78369 !important;}
        .layui-icon-username:hover {color:#9dadce !important;}
        .layui-icon-password {color:#a78369 !important;}
        .layui-icon-password:hover {color:#9dadce !important;}
        .admin-input-username {border-top-style:solid;border-radius:10px 10px 0 0;}
        .admin-input-verify {border-radius:0 0 10px 10px;}
        .admin-button {margin-top:20px;font-weight:bold;font-size:18px;width:300px;height:50px;border-radius:5px;background-color:#a78369;border:1px solid #d8b29f}
        .admin-icon {margin-left:260px;margin-top:10px;font-size:30px;}
        i {position:absolute;}
        .admin-captcha {position:absolute;margin-left:205px;margin-top:-40px;}
        .td2 {text-align: right;}
        .table1 {border: 1px solid #ddd;width: 900px;}
        thead {background-color: lightblue;}
    </style>

</head>
<body>

<div id="container">
    <div class="admin-login-background">
        <table class="layui-table" >
            <colgroup>
                <col width="200">
                <col width="200">
                <col width="200">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <td>电影名字</td>
                <td>电影年份</td>
                <td>电影导演</td>
                <td>电影poster</td>
                <td>评分</td>
            </tr>
            </thead>
            <form action="/user/submitScores?uid=${userid}" method="post" modelAttribute="returnRatinglist">
        <c:forEach items="${requestScope.randommovielist}" var="m">
                <tr>
                    <input type="text" style="visibility:hidden;width: 0px;height: 0px;" value="${userId}" name="userid">
                    <input type="text" style="visibility:hidden;width: 0px;height: 0px;" value="${m.movieId}" name="movieid">
                    <th>${m.movieName}</th>
                    <th>${m.releaseTime}</th>
                    <th>${m.director}</th>
                    <th><img src='${m.picture}'></th>
                    <th>
                        <select name="rating">
                            <option value=""></option>
                            <option selected value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select></th>
                </tr>
            </c:forEach>
            </form>
            <div class="layui-form-item">
                    <button class="layui-btn" type="button" lay-submit >提交</button>
            </div>
        </table>
    </div>
</div>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
</script>
</body>
</html>