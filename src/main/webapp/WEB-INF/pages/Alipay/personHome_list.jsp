<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/2/26
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${basePath}/css/font.css">
    <link rel="stylesheet" href="${basePath}/css/xadmin.css">

    <script type="text/javascript" src="${basePath}/jquery/jquery-3.2.1.min.js"></script>

    <script type="text/javascript" src="${basePath}/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="../../../js/html5.min.js"></script>
    <script src="../../../js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-body layui-anim layui-anim-up">
    <form class="layui-form" id="editFormId">
        <div class="layui-form-item">
            <label for="startleasetime" class="layui-form-label">
                <span class="x-red">*</span>租借时间
            </label>
            <div class="layui-input-inline">
                <input type="hidden" name="phone" value="${homeMessage.get("phone")}" id="phone">
                <input type="text" id="startleasetime" name="startleasetime" required="" lay-verify="hourse" disabled = "disabled"
                       autocomplete="off" class="layui-input" value="${homeMessage.get("startleasetime")}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="endleasetime" class="layui-form-label">
                <span class="x-red">*</span>到期时间
            </label>
            <div class="layui-input-inline">
                <input type="text" id="endleasetime" name="endleasetime" required="" lay-verify="" disabled = "disabled"
                       autocomplete="off" class="layui-input" value="${homeMessage.get("endleasetime")}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="daytime" class="layui-form-label">
                <span class="x-red">*</span>剩余时间
            </label>
            <div class="layui-input-inline">
                <input type="text" id="daytime" name="daytime" required="" lay-verify="" disabled = "disabled"
                       autocomplete="off" class="layui-input" value="${homeMessage.get("day")}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="num" class="layui-form-label">
                <span class="x-red">*</span>请输入需要续期的时间（月）
            </label>
            <div class="layui-input-inline">
                <input type="text" id="num" name="num" required="" lay-verify=""
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="updatereadP">普通支付</button>
                <button class="layui-btn" lay-submit="" lay-filter="updateread">扫码支付</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">

    layui.use(['form','layer','jquery'], function () {
        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;

        //添加ajax表单提交
        form.on('submit(updatereadP)',function () {
            var params={
                phone:$("#phone").val(),
                num:$("#num").val()
            };
            var daytime = $("#daytime").val();
            var url;
            if (daytime>=0){
                url="${basePath}/goAlipayP1.do";
            }else {
                url="${basePath}/goAlipayP2.do";
            }
            $.ajax({
                url:url,
                data:params,
                //dataType:'json',
                type:'post',
                success:function (data) {
                    if (data.success){
                        layer.msg(data.message);
                        layer.alert(data.message, {icon: 6},function(){
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            window.parent.location.reload();//刷新父页面
                            //关闭当前frame
                            parent.layer.close(index);

                        });
                    }else{
                        layer.msg(data.message);
                    }
                }
            });
            return false;
        });

            //扫码表单提交
        form.on('submit(updateread)',function (data) {
            var params={
                phone:$("#phone").val(),
                num:$("#num").val()
            };
           /* var phone  = $("#phone").val();
            var num  = $("#num").val();*/
            //location.href="${basePath}/goAlipay?phone="+phone+"&num"+num;
            $.ajax({
                url:"${basePath}/goAlipay.do",
                data:params,
                //dataType:'json',
                type:'post',
                success:function (data) {

                }
            });
        });

    });
</script>

</body>

</html>

