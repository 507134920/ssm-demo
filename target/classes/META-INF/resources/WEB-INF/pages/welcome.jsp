<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/2/25
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面</title>
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

</head>
<body class="welcome-bg>
<div class="x-body layui-anim layui-anim-up">
    <blockquote class="layui-elem-quote">欢迎读者：
        <span class="x-red">${currentUser.realname}</span>
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
            <i class="layui-icon" style="line-height:30px">ဂ</i>
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>社区本月活动</legend>
        <div class="layui-field-box">
            <table class="layui-table">
                <tbody>
                    <td bgcolor="#ffd700">时间</td>
                    <td bgcolor="#ffd700">地点</td>
                    <td bgcolor="#ffd700">内容</td>
                </tbody>
                <tbody>
                    <c:forEach var="activityList" items="${activityList}">
                        <tr>
                            <td >
                                <a class="x-a" href="#" target="_blank">${activityList.time}</a>
                            </td>
                            <td >
                                <a class="x-a" href="#" target="_blank">${activityList.place}</a>
                            </td>
                            <td >
                                <a class="x-a" href="#" target="_blank">${activityList.content}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>社区公告栏</legend>
        <div class="layui-field-box">
            <table class="layui-table">
                <tbody>
                    <td bgcolor="#ffd700">时间</td>
                    <td bgcolor="#ffd700">内容</td>
                </tbody>
                <tbody>
                <c:forEach var="noticeList" items="${noticeList}">
                    <tr>
                        <td >
                            <a class="x-a" href="#" target="_blank">${noticeList.ntime}</a>
                        </td>
                        <td >
                            <a class="x-a" href="#" target="_blank">${noticeList.content}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>

</body>
</html>