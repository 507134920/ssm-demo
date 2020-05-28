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

    <script type="text/javascript" src="${basePath}/jquery/jquery-3.2.1.min.js"></script>

    <script type="text/javascript" src="${basePath}/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/hourse/hourse_list.js"></script>
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
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="queryFormId">
            <div class="layui-input-inline">
                <select name="contrller" id="option">
                    <option value="全部" >全部</option>
                    <option value="已租">已租</option>
                    <option value="未租">未租</option>
                </select>
            </div>
            <button type="button" class="layui-btn" id="btn-search"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>

    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>编号</th>
            <th>楼号</th>
            <th>层号</th>
            <th>房间号</th>
            <th>月租</th>
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
