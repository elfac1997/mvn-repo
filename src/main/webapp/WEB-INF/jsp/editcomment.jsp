<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/16
  Time: 上午9:59
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
    <title>重新评论</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <style>
        body {background-image:url("/layui/images/purewhite.jpg");background-repeat:no-repeat ;
            background-size:100% 100%;
            background-attachment: fixed;}
        #container{height:100%;width:100%;}
        input:-webkit-autofill {-webkit-box-shadow:inset 0 0 0 1000px #fff;background-color:transparent;}
        .admin-login-background {width:600px;height:600px;position:absolute;left:50%;top:40%;margin-left:-150px;margin-top:-100px;}
        .admin-header {text-align:center;margin-bottom:20px;color:#ffffff;font-weight:bold;font-size:40px}
        i {position:absolute;}
    </style>
</head>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <div class="admin-header">
            <span>修改电影评论</span>
        </div>
            为这部电影写一个评论吧
            <form class="layui-form"  action="/movie/submitComment" method="post" modelAttribute="returnComment">
                <input type="text" style="visibility:hidden;width:0px;height:0px;" value="${returnComment.userId}" name="userId"/>
                <input type="text" style="visibility:hidden;width:0px;height:0px;" value="${returnComment.movieId}" name="movieId"/>
                <div class="layui-form-item layui-form-text">
                    <div class="layui-input-block">
                        <textarea name="comment" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    </div>
                </div>
            </form>
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
