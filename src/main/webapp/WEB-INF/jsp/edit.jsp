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
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>edituser</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        body {background-image:url("/layui/images/purewhite.jpg");background-repeat:no-repeat ;
            background-size:100% 100%;
            background-attachment: fixed;}
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
    </style>
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <div class="admin-header">
            <c:if test="${requestScope.register eq 1}">
                <span>注册新用户</span>
            </c:if>
            <c:if test="${requestScope.register eq 2}">
                <span>修改用户信息</span>
            </c:if>
        </div>

        <form class="layui-form"  action="/user/save" method="post" modelAttribute="returnUser" >
            <input type="text" style="visibility:hidden;width:0px;height:0px;" value="${returnUser.id}" name="id"/>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" value="${returnUser.username}" name="username" lay-verify="title" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="text" value="${returnUser.password}" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <shiro:hasRole name="admin">
                <div class="layui-form-item">
                    <label class="layui-form-label">选择角色</label>
                    <div class="layui-input-block">
                        <select name="roleId" >
                            <option value=""></option>
                            <option value="1">管理员</option>
                            <option value="2">普通用户</option>
                        </select>
                    </div>
                </div>
            </shiro:hasRole>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" lay-submit="">立即提交</button>
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