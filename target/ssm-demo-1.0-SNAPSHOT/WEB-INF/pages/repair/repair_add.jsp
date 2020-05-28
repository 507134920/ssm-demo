<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/4/29
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${basePath}/css/font.css">
    <link rel="stylesheet" href="${basePath}/css/xadmin.css">
    <script type="text/javascript" src="${basePath}/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${basePath}/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/repair/repair_add.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="../../../js/html5.min.js"></script>
    <script src="../../../js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-body">
    <form class="layui-form" id="queryFormId">
        <div class="layui-form-item">
            <label for="people" class="layui-form-label">
                <span class="x-red">*</span>维修报备者姓名
            </label>
            <div class="layui-input-inline">
                <input disabled = "disabled" type="text" id="people" name="people" value=${currentUser.realname} class="layui-input" placeholder=${currentUser.realname}>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="ntime" class="layui-form-label">
                <span class="x-red">*</span>时间
            </label>
            <div class="layui-input-inline">
                <input type="date" name="ntime" lay-verify="required"autocomplete="off" class="layui-input" id="ntime">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label for="content" class="layui-form-label">
                <span class="x-red">*</span>维修报备内容
            </label>
            <div class="layui-input-block">
                    <textarea style="width:400px; height:100px" placeholder="请输入内容" name="content" id="content" class="layui-textarea" >

                    </textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id ="send">报备</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

</body>

</html>