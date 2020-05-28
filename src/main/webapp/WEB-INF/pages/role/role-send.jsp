<%--
  Created by IntelliJ IDEA.
  User: 50713
  Date: 2020/3/2
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限分配</title>
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
    <script type="text/javascript" src="${basePath}/myhome/common/common.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="../../../js/html5.min.js"></script>
    <script src="../../../js/respond.min.js"></script>
    <![endif]-->

    <script>
        $(document).ready(function(){
            $("#queryFormId").on("click","#btn-search",doQueryObjects);
            $("#queryTenantFormId").on("click","#btn-search-tenant",doQueryTenantObjects);
            //1、查询已存在的角色 动态填入下拉框中
            //初始化所属分类
            doFindRoleName();
            //页面加载完成执行数据的获取操作
            doGetObjects();

        });
        function doFindRoleName() {
            var url="doFindRoleName.do";
            $.getJSON(url,function(result){
                if(result.state==1){
                    doInitRoleSelect(result.data);
                }else{
                    alert(result.message);
                }
            });
        }

        function doInitRoleSelect(list) {
            var select = $("#searchrole");
            select.append(
                "<option>==请选择角色==</option>")
            var option =
                "<option value=[roleName]>[roleValue]</option>"
            for (var i in list) {
                select.append(
                    option.replace("[roleName]", list[i].roleName)
                        .replace("[roleValue]", list[i].roleValue));
            }
        }

        function doQueryObjects() {
            var realname = $("#searchrealname").val();
            var url="FindUserByName.do";
            var params={realname:realname};
            $.getJSON(url,params,function (result) {
                if(result.state==1) {
                    if (result.data == null){
                        alert("该用户不存在");
                    }else{
                        if (result.data.valid == 0){
                            alert("该租客已被拉黑");
                        }
                        if (result.data.valid == 1){
                            //3、分配角色
                            var url1 = "insertUR.do";
                            var realname = $("#searchrealname").val();
                            var roleName= $("#searchrole").val();
                            var params1 = {realname:realname,roleName:roleName};
                            console.log(params1);
                            alert("开始分配");

                            $.getJSON(url1,params1,function (result) {
                                if(result.state==1) {
                                    alert(result.message);
                                }else {
                                    alert(result.message);
                                }
                            })
                        }
                    }
                }else{
                    alert(result.message);
                }

            });

        }

        function doGetObjects() {
        debugger
            var url="doGetPageObjects.do";
            //根据pageId绑定的值 动态确定当前页
            var pageCurrent=$("#pageId").data("pageCurrent");
            if(!pageCurrent)pageCurrent=1;
            var params={"pageCurrent":pageCurrent};
            //获得表单数据 并将其添加到params对象
            params.realname=$("#searchRealNames").val();
            params.phone=$("#searchPhone").val();
            //console.log("前端页面传来的查询值："+params);
            $.getJSON(url,params,function(result){
                console.log(result);
                if(result.state==1){
                    //将数据显示在table的tbody中
                    setTableBodyRows(result.data.list);
                    //设置分页信息
                    setPagination(result.data.pageObject);
                }else{
                    alert(result.message);
                }
            });
        }

        function doQueryTenantObjects() {
            //1.修改当前页的值为1
            $("#pageId").data("pageCurrent",1);
            //2.执行查询动作(重用doGetObjects方法)
            doGetObjects();
        }

        //将数据填充到table的body中
        function setTableBodyRows(result) {
        debugger
            //1、获得tbody对象
            var tBody = $("#tbodyId");
            tBody.empty();
            //2、迭代数据集result
            for(var i in result){
                //2.1、构建一个tr对象
                var tr = $("<tr></tr>");
                //2.1.1、绑定id 为修改操作提供id
                tr.data("id",result[i].id);
                //2.2、构建每行td对象
                //2.3、在td对象内填充具体数据
                var tds = "<td><input type='checkbox' name='checkId' value=' "+result[i].id+" '></td>"+
                    "<td>"+result[i].realname+"</td>"+
                    "<td>"+result[i].phone+"</td>"+
                    //layui-btn layui-btn-normal layui-btn-mini
                    "<td class='td-status'><span class='layui-btn "+(result[i].valid?" ":"layui-btn-disabled")+"'>"+(result[i].valid?"已启用":"已拉黑")+"</span></td>"+
                    "<td class='td-manage'>" +
                    "<a title='编辑' class='btn-update'><i class='layui-icon'>&#xe642;</i></a>" +
                    "<a title='删除' id='btn-delete'><i class='layui-icon'>&#xe640;</i></a>" +
                    "</td>";
                //2.4、将td添加到tr中
                tr.append(tds);
                //2.5、将tr追加到tbody中
                tBody.append(tr);
            }
        }

    </script>

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
            <input type="text" name="realname"  placeholder="请输入租客名字" autocomplete="off" class="layui-input" id="searchrealname">
            <div class="layui-input-inline">
                <select name="contrller" id="searchrole">

                </select>
            </div>
            <button type="button" class="layui-btn" id="btn-search"><i class="layui-icon">&#xe615;</i></button>
        </form>
        <form class="layui-form layui-col-md12 x-so" id="queryTenantFormId">
            <input type="text" name="realnames"  placeholder="请输入用户姓名" autocomplete="off" class="layui-input" id="searchRealNames">
            <input type="text" name="phone"  placeholder="请输入手机号" autocomplete="off" class="layui-input" id="searchPhone">
            <button type="button" class="layui-btn" id="btn-search-tenant"><i class="layui-icon">&#xe615;</i></button>
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
            <th>真实姓名</th>
            <th>手机号</th>
            <th>状态</th>
            <th>操作</th>
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