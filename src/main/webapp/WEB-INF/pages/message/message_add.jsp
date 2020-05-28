<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/2/25
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>房间信息列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${basePath}/css/font.css">
    <link rel="stylesheet" href="${basePath}/css/xadmin.css">
    <link rel="stylesheet" href="${basePath}/css/welcome.css">

    <script type="text/javascript" src="${basePath}/jquery/jquery-3.2.1.min.js"></script>

    <script type="text/javascript" src="${basePath}/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/message/message_add.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/common/page.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/common/common.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="../../../js/html5.min.js"></script>
    <script src="../../../js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="layui-anim layui-anim-up">
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="#">首页</a>
        <a href="#">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <form class="layui-form" id="queryFormId">
        <div class="layui-form-item">
            <label for="realname" class="layui-form-label">
                <span class="x-red">*</span>留言人
            </label>
            <div class="layui-input-inline">
                <input type="text" disabled="disabled"id="realname" name="realname" value=${currentUser.realname} class="layui-input" placeholder=${currentUser.realname}>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label for="content" class="layui-form-label">
                <span class="x-red">*</span>留言内容
            </label>
            <div class="layui-input-block">
                <input type="text" id="content" name="content" class="layui-input" placeholder="请输入留言内容">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" id ="send">发布</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

    <xblock>

    </xblock>

    <div class="x-body layui-anim layui-anim-up">

        <fieldset class="layui-elem-field">
            <legend>本月租客留言</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <div class="messageList">
                        <!--异步加载-->

                    </div>
                </table>
            </div>
        </fieldset>

    </div>

</div>

</body>

</html>
