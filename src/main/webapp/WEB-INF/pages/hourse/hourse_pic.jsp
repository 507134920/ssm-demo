<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/5/2
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公告页</title>
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
    <script type="text/javascript" src="${basePath}/myhome/common/page.js"></script>
    <script type="text/javascript" src="${basePath}/myhome/hourse/hourse_pic.js"></script>

    <script>
        var index = 0;

        function changeimg() {
            var img = document.getElementById("img1");
            var cuIndex = index % 3 + 1;
            img.src = "${pageContext.request.contextPath}\\images\\" + cuIndex + ".jpg";
            index++;
        }

        function init() {
            setInterval("changeimg()", 2000);
        }
    </script>
</head>
<body class="layui-anim layui-anim-up"  οnlοad="init()">

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
<div class="layui-row">
    <form class="layui-form layui-col-md12 x-so" id="queryFormId">
        <label for="id" class="layui-form-label">
            <span class="x-red">*</span>编号
        </label>
        <div class="layui-input-inline">
            <input type="text" placeholder="请输入要查询的房间编号" name="id" id="id">
        </div>
        <button type="button" class="layui-btn" id="btn-search" value="查询"><i class="layui-icon">&#xe615;</i></button>
    </form>
</div>
<%--<table class="layui-table">
    <thead>
        <tr>

        </tr>
    </thead>
    <!-- ajax异步获得,并将数据填充到tbody中 -->
    <tbody id="tbodyId">

    </tbody>
</table>--%>
<div>
    <img border="0" src="${pageContext.request.contextPath}/images/1.jpg" id="img1">
</div>
</body>
</html>
