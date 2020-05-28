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
            <label for="realname" class="layui-form-label">
                <span class="x-red">*</span>真实姓名
            </label>
            <div class="layui-input-inline">
                <input type="hidden" name="id" value="${tenant.id}">
                <input type="text" id="realname" name="realname" required="" lay-verify="realname"
                       autocomplete="off" class="layui-input" value="${tenant.realname}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>请输入您的真实姓名
            </div>
        </div>
        <div class="layui-form-item">
            <label for="phone" class="layui-form-label">
                <span class="x-red">*</span>手机号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="phone" name="phone" required="" lay-verify=""
                       autocomplete="off" class="layui-input" value="${tenant.phone}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="x-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="password" required="" lay-verify="password"
                       autocomplete="off" class="layui-input" value="${tenant.password}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="x-red">*</span>盐
            </label>
            <div class="layui-input-inline">
                <input type="password" id="salt" name="salt" required="" lay-verify="salt"
                       autocomplete="off" class="layui-input" value="${tenant.salt}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                加密字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="x-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass" name="password" required="" lay-verify="password"
                       autocomplete="off" class="layui-input" value="${tenant.password}">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <c:if test="${tenant==null}"><!-- 查看是没有添加按钮的 -->
                <button class="layui-btn" lay-submit="" lay-filter="addread">立即添加</button>
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
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        //自定义验证规则
        form.verify({
            phone: function(value){
                if(value.length !=11 ){
                    return '请输入正确的手机号码！';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
        });

    });
</script>

<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#bkDatePress' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#bkDateIn' //指定元素
        });
    });
</script>

<script type="text/javascript">
    layui.use(['form','layer','jquery'], function () {
        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;

        //添加ajax表单提交
        form.on('submit(addread)',function (data) {
            //console.log("页面数据"+JSON.stringify(data.field));
            $.ajax({
                url:'${basePath}/#',
                data:data.field,
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

            //修改ajax表单提交
            form.on('submit(updateread)',function (data) {
                console.log("页面数据"+JSON.stringify(data.field));
                $.ajax({
                    url:'${basePath}/user/updateUserObject.do',
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
                })
                return false;
            })


    });
</script>
</body>

</html>

