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
    <script type="text/javascript" src="${basePath}/myhome/activity/activity-add.js"></script>
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
            <label for="time" class="layui-form-label">
                <span class="x-red">*</span>活动开始时间
            </label>
            <div class="layui-input-inline">
                <input type="hidden" name="id" value="${activity.id}">
                <input type="date" id="time" name="time" required="" lay-verify="time"
                       autocomplete="off" class="layui-input" value="${activity.time}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="place" class="layui-form-label">
                <span class="x-red">*</span>举办地点
            </label>
            <div class="layui-input-inline">
                <input type="text" id="place" name="place" required="" lay-verify="place"
                       autocomplete="off" class="layui-input" value="${activity.place}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="content" class="layui-form-label">
                <span class="x-red">*</span>内容
            </label>
            <div class="layui-input-inline">
                <input type="text" id="content" name="content" required="" lay-verify="content"
                       autocomplete="off" class="layui-input" value="${activity.content}">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <c:if test="${activity==null}"><!-- 查看是没有添加按钮的 -->
                <button class="layui-btn" lay-submit="" lay-filter="addread" id="add">立即添加</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </c:if>
                <!-- 修改需要修改按钮是没有添加按钮的 -->
                <c:if test="${code==1}">
                    <button class="layui-btn" lay-submit="" lay-filter="updateread">立即修改</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </c:if>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    layui.use(['form','layer','jquery'], function () {
        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;

        //修改ajax表单提交
        form.on('submit(updateread)',function (data) {
                console.log("页面数据"+JSON.stringify(data.field));
                $.ajax({
                    url:'${basePath}/activity/doUpdateObject.do',
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


    });
</script>
</body>

</html>

