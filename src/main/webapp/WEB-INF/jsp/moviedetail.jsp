<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/15
  Time: 下午1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电影详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md6">
            <div class="layui-row">
                <h3>${randommovie.movieName}</h3>
            </div>
            <div class="layui-row">
                <div class="layui-col-md5">
                    movie picture
                    <br>
                    <img src="${randommovie.picture}"/>
                </div>
                <div class="layui-col-md7">
                    导演：${randommovie.director}<br>
                    主演：${randommovie.leadActors}<br>
                    电影类型：${randommovie.typeList}<br>
                    上映时间：${randommovie.releaseTime}<br>
                    电影简介：${randommovie.description}<br>
                </div>
            </div>
        </div>
        <div class="layui-col-md5">
            大众评分：${randommovie.averating}<br>
            参与评分人数：${randommovie.numrating}<br>
            你的评分：
            <form class="layui-form" target="nm_iframe"  action="/user/submitScores" method="post" modelAttribute="returnrating">
                <input type="text" style="visibility:hidden;width:0px;height:0px;" value="${userid}" name="userId"/>
                <input type="text" style="visibility:hidden;width:0px;height:0px;" value="${movieid}" name="movieId"/>
                <div class="layui-row">
                    <div class="layui-col-md6">
                        <div class="layui-input-block" style="float:left;">
                            <select name="rating">
                                <option value=""></option>
                                <option selected value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <button class="layui-btn layui-btn-sm" lay-submit lay-filter="formDemo">提交</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-col-md1">
            <button class="layui-btn layui-btn-radius layui-btn-warm" onclick="location='/movie/moviedetail?uid=${userid}'">下一部</button>
        </div>
    </div>




    <div class="layui-row">
        <div class="layui-col-md6">
            为这部电影写一个评论吧
            <form class="layui-form" target="nm_iframe" action="/movie/submitComment" method="post" modelAttribute="returncomment">
                <input type="text" style="visibility:hidden;width:0px;height:0px;" value="${userid}" name="userId"/>
                <input type="text" style="visibility:hidden;width:0px;height:0px;" value="${movieid}" name="movieId"/>
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
        <div class="layui-col-md6">

        </div>
    </div>
    <%--隐藏的框架，在form表单提交时指向它，防止表单提交时页面跳转 --%>
    <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
</div>
</div>

<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;
    });
</script>
</body>
</html>
