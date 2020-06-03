<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/2/25
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${basePath}/css/font.css">
    <link rel="stylesheet" href="${basePath}/css/xadmin.css">
    <script type="text/javascript" src="${basePath}/jquery/jquery-3.2.1.min.js"></script>
    <script src="${basePath}/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/common/index.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container" >
    <div class="logo"><a href="#">社区住户管理系统</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav left fast-add" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">+生活服务</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('维修报备','${basePath}/welcome/toRepair.do')"><i class="iconfont">&#xe6a2;</i>维修报备</a></dd>
                <dd><a onclick="x_admin_show('缴费','${basePath}/money/money_editUI.do')"><i class="iconfont">&#xe6a8;</i>缴费</a></dd>
                <dd><a class="updatePassword"  id="${currentUser.realname} ${currentUser.phone}"><i class="iconfont">&#xe6b8;</i>修改房间密码</a></dd>
            </dl>
        </li>

        <li class="layui-nav-item">
            <a href="javascript:;">+房租缴费</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('查看房间租赁信息','${basePath}/findPersonHome.do?phone='+${currentUser.phone})"><i class="iconfont">&#xe6a8;</i>缴费</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${currentUser.realname}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <%--<dd><a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a></dd>--%>
                <dd><a onclick="x_admin_show('切换帐号','${basePath}/unlogin.do')">切换帐号</a></dd>
                <dd><a onclick="x_admin_show('修改个人信息','${basePath}/user/updateMsg.do?id='+${currentUser.id})">修改个人信息</a></dd>
                <dd><a href="${basePath}/logout.do">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index"><a onclick="x_admin_show('前往注册','${basePath}/registUI.do')">前往注册</a></li>
    </ul>

</div>
<!-- 顶部结束 -->

<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>租客管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="user/tenant_list.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>租客列表</cite>

                        </a>
                    </li>
                    <li>
                        <a _href="user/block_list.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>租客黑名单</cite>

                        </a>
                    </li>
                    <li>
                        <a _href="role/roleUI.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>角色信息</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="role/roleSendUI.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>权限分配</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>物业服务管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="money/money_list.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>物业缴费管理</cite>

                        </a>
                    </li>
                    <li>
                        <a _href="myhome/toHomeUI.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>门锁密码更换</cite>

                        </a>
                    </li>
                    <li>
                        <a _href="repair/toRepair.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>维修报备管理</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>社区服务管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="activity/toActivity.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>社区活动管理</cite>

                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>公示信息管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="notice/toNotice.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>公告</cite>

                        </a>
                    </li >
                </ul>
                <ul class="sub-menu">
                    <li>
                        <a _href="message/toMessage.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>留言墙</cite>

                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>房源信息管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="hourse/tohourse.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>房源信息列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="hourse/toPicUI.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>详细房源信息</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="hourse/toNewPicUI.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>详细房源信息2</cite>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->

<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${basePath}/welcome/welcome.do' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->

<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2020 Rights Reserved  ：<a href="www.baidu.com" target="_blank">社区住户管理系统</a></div>
</div>
<!-- 底部结束 -->

</body>
</html>
