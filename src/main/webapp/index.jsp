<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>movie recommend system</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div class="container">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>电影推荐系统</legend>
    </fieldset>
    <h3>
        <a href="/user/toLogin">登录</a>
    </h3>
    <div class="layui-carousel" id="test1" lay-filter="test1" style="text-align: center;margin: 0 auto;border: green solid 1px;">
        <div carousel-item="">
            <div><img src="${pageContext.request.contextPath}/layui/images/哪吒.png"></div>
            <div><img src="${pageContext.request.contextPath}/layui/images/姜子牙.jpg"></div>
            <div><img src="${pageContext.request.contextPath}/layui/images/肖申克的救赎.jpg"></div>
            <div><img src="${pageContext.request.contextPath}/layui/images/这个杀手不太冷.jpg"></div>
            <div><img src="${pageContext.request.contextPath}/layui/images/蜘蛛侠.jpg"></div>
        </div>
    </div>

    <script type="text/javascript">
        layui.use('carousel', function(){
            var carousel = layui.carousel;
            //建造实例
            carousel.render({
                elem: '#test1'
                ,width: '1200px'
                ,height: '800px'
                ,interval: 5000 //每2秒，自动轮播
                ,arrow: 'always'
            });

            //监听轮播切换事件
            carousel.on('change(test1)', function(obj){ //test1来源于对应HTML容器的 lay-filter="test1" 属性值
                console.log(obj.index); //当前条目的索引
                console.log(obj.prevIndex); //上一个条目的索引
                console.log(obj.item); //当前条目的元素对象
            });

        });
    </script>

</div>

</body>
</html>