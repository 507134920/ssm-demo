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
            <label for="hourse_pasword" class="layui-form-label">
                <span class="x-red">*</span>公共房间密码
            </label>
            <div class="layui-input-inline">
                <input type="hidden" name="id" value="${home.id}">
                <input type="text" id="hourse_pasword" name="hourse_pasword" required="" lay-verify="hourse"
                       autocomplete="off" class="layui-input" value="${home.hourse_pasword}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6个数字字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="room_password" class="layui-form-label">
                <span class="x-red">*</span>房间密码
            </label>
            <div class="layui-input-inline">
                <input type="text" id="room_password" name="room_password" required="" lay-verify=""
                       autocomplete="off" class="layui-input" value="${home.room_password}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6个数字字符
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="updateread">立即修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    //验证字符串是否是数字
    function checkNumber(theObj) {
        var reg = /^[0-9]+.?[0-9]*$/;
        if (reg.test(theObj)) {
            return true;
        }
        return false;
    }

    layui.use(['form','layer','jquery'], function () {
        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;

        $(".layui-btn").on("click", function () {
            hourse_pasword = $("#hourse_pasword").val();
            room_password = $("#room_password").val();
            if(/^[0-9]+$/.test(hourse_pasword) && /^[0-9]+$/.test(room_password) && hourse_pasword.length==6 && room_password.length==6){

                //修改ajax表单提交
                form.on('submit(updateread)',function (data) {
                    //console.log("页面数据"+JSON.stringify(data.field));
                    $.ajax({
                        url:'${basePath}/myhome/doUpdateObject.do',
                        data:data.field,
                        dataType:'json',
                        type:'post',
                        success:function (data) {
                            if (data.success){
                                layer.alert(data.message,{icon: 6},function(){
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
                })
            }else {
                alert("密码应为6位数字!!");
                return false;
            }
        });



    });
</script>

</body>

</html>

