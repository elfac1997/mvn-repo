<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/4/1
  Time: 下午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>推荐系统管理平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">推荐系统管理平台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                欢迎光临，${user.username}
            </li>
            <li class="layui-nav-item"><a href="logout">安全退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧垂直导航区域-->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <shiro:hasRole name="admin">
                    <li class="layui-nav-item">
                        <a href="javascript:;">管理员选项</a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" data-id="1" data-title="用户列表" data-url="/user/allUser"
                                   class="site-demo-active" data-type="tabAdd">用户列表</a></dd>
                            <dd>
                                <a href="javascript:;" data-id="8" data-title="用户注册" data-url="/user/registerUser"
                                   class="site-demo-active" data-type="tabAdd">用户注册</a></dd>
                            <dd><a href="javascript:;" data-id="2" data-title="电影管理" data-url="/movie/allMovie"
                                   class="site-demo-active" data-type="tabAdd">电影管理</a></dd>
                            <dd><a href="javascript:;" data-id="3" data-title="评分管理" data-url="/user/allRating"
                                   class="site-demo-active" data-type="tabAdd">评分管理</a></dd>
                        </dl>
                    </li>
                </shiro:hasRole>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户选项</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;"  data-id="4" data-title="评分列表" data-url="/user/userRating?uid=${userid }"
                               class="site-demo-active" data-type="tabAdd">评分列表</a></dd>
                        <dd>
                            <a href="javascript:;"  data-id="5" data-title="推荐列表" data-url="/user/recommend?uid=${userid }"
                               class="site-demo-active" data-type="tabAdd">推荐列表</a></dd>
                        <dd>
                            <a href="javascript:;"  data-id="6" data-title="用户信息管理" data-url="/user/edit?uid=${userid }"
                               class="site-demo-active" data-type="tabAdd">用户信息管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">电影推荐</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-id="7" data-title="电影评分" data-url="/user/addRating?uid=${userid }"
                               class="site-demo-active" data-type="tabAdd">电影评分</a></dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-id="9" data-title="电影推荐" data-url="/user/submitToSparkandLoad?uid=${userid }"
                               class="site-demo-active" data-type="tabAdd">电影推荐</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!--tab标签-->
    <div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin-left: 200px;">
        <ul class="layui-tab-title"></ul>
        <div class="layui-tab-content"></div>
    </div>

<%--    <div class="layui-footer" style="text-align:center;">--%>
<%--        <!-- 底部固定区域 -->--%>
<%--        © 电影推荐系统--%>
<%--    </div>--%>
</div>
<script>
    layui.use(['element', 'layer', 'jquery'], function () {
        var element = layui.element;
        // var layer = layui.layer;
        var $ = layui.$;
        // 配置tab实践在下面无法获取到菜单元素
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在
                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('demo', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("demo", id);//删除
            }
        };
        function FrameWH() {
            // var h = $(window).height();
            // $("iframe").css("height",h+"px");scrolling="yes" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes"
            $("iframe").css("height","90%");
            $("iframe").css("marginwidth","0");
            $("iframe").css("marginheight","0");
            $("iframe").css("allowtransparency","yes");
            $("iframe").css("height","80%");
            $("iframe").css("scrolling","yes");
            $("iframe").css("frameborder","no");
            $("iframe").css("border","0");
        }
    });
</script>
</body>
</html>