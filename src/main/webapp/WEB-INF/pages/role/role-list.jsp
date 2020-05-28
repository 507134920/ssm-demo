<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/3/1
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>界面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${basePath}/css/font.css">
    <link rel="stylesheet" href="${basePath}/css/xadmin.css">

    <script type="text/javascript" src="${basePath}/jquery/jquery-3.2.1.min.js"></script>

    <script type="text/javascript" src="${basePath}/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/common/page.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/role/role-list.js"></script>
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
          <cite>系统已存在用户的权限信息</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="queryFormId">
            <input type="text" name="realname"  placeholder="请输入租客姓名" autocomplete="off" class="layui-input" id="searchrealname">
            <button type="button" class="layui-btn" id="btn-search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>

    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <td>用户</td>
            <td>角色类型</td>
            <td>角色名</td>
        </tr>
        </thead>
        <!-- ajax异步获得,并将数据填充到tbody中 -->
        <tbody id="tbodyId">

        </tbody>
    </table>
    <%@include file="../common/page.jsp" %>

</div>

</body>
</html>
