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
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>欢迎页面</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
	<link rel="stylesheet" href="${basePath}/static/login/css/style.css">

	<link rel="stylesheet" href="${basePath}/static/login/js/jquery/1.11.1/jquery.min.js">
	<script type="text/javascript" src="${basePath}/static/login/js/common.js"></script>

	<script type="text/javascript" src="${basePath}/static/login/js/supersized.3.2.7.min.js"></script>
	<script type="text/javascript" src="${basePath}/static/login/js/supersized-init.js"></script>

	<script src="${basePath}/static/login/js/jquery.validate.min.js"></script>

	<script>
		function showTips(spanID, msg) {
			var span = document.getElementById(spanID);
			span.innerHTML = msg;
		}

		function cheackUser() {
			var uValue = document.getElementById("realname").value;
			var span = document.getElementById("span_realname");

			if (uValue.length < 2) {
				span.innerHTML = "对不起，太短了！";
				return false;
			} else {
				span.innerHTML = "恭喜您，够用！";
				return true;
			}
		}

		function cheack(ID1, ID2) {
			var id = document.getElementById(ID1).value;
			var span = document.getElementById(ID2);

			if (id.length < 6) {
				span.innerHTML = "对不起，太短了！";
				return false;
			} else {
				span.innerHTML = "恭喜您，够用！";
				return true;
			}
		}

		function cheackForm() {
			var flag = (cheackUser() && check(ID1, ID2));

			return flag;

		}
	</script>
</head>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>注册页</title>

<body>
	<div class="register-container">
	<form action="reg.do" method="post" onsubmit="return cheackForm()">
		<div>
			<input type="text" name="realname" id="realname" placeholder="您的姓名" autocomplete="off" onblur="cheackUser()" onkeyup="cheackUser()"
				   onfocus="showTips('span_realname','姓名长度不能小于2位')"><span id="span_realname"></span><br/>
		</div>
		<div>
			<input type="password" name="password" id="password" placeholder="输入密码" onblur="cheack('password','span_password')"
				   onkeyup="cheack('password','span_password')"
				   onfocus="showTips('span_password','密码长度不能小于6位')"><span id="span_password"></span><br/>
		</div>
		<div>
			<input type="password" name="salt" id="salt" placeholder="加密盐值" autocomplete="off">
		</div>
		<div>
			<input type="tel" name="phone" id="phone" placeholder="输入手机号码" maxlength="11" autocomplete="off" id="phone">
		</div>
		<button id="submit" type="submit">注 册</button>
	</form>
</div>
</body>


</html>