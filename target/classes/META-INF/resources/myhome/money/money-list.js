$(document).ready(function(){
    $("#queryFormId").on("click","#btn-search",doQueryObjects);
    $(".x-body").on("click",".btn-update",doLoadEditPage);
    //页面加载完成执行数据的获取操作
    doGetObjects();
});

function doLoadEditPage() {
    if($(this).hasClass("btn-update")){
        var idValue = $(this).parent().parent().data("id");
        title= "修改缴费信息,id="+idValue;
        url= "updateObjectById.do?id="+idValue;
    }
    x_admin_show(title,url,600,400);
}

//查询按钮
function doQueryObjects() {
    //1.修改当前页的值为1
    $("#pageId").data("pageCurrent",1);
    //2.执行查询动作(重用doGetObjects方法)
    doGetObjects();
}

//获取缴费信息
function doGetObjects(){
debugger
    var url="doGetPageObjects.do";
    //根据pageId绑定的值 动态确定当前页
    var pageCurrent=$("#pageId").data("pageCurrent");
    if(!pageCurrent)pageCurrent=1;
    var params={"pageCurrent":pageCurrent};
    //获得表单数据 并将其添加到params对象
    params.realname=$("#searchRealname").val();
    params.phone=$("#searchPhone").val();
    params.hourse=$("#searchHourse").val();
    params.floor=$("#searchFloor").val();
    params.room_number=$("#search_room_number").val();
    $.getJSON(url,params,function(result){
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
            "<td>"+result[i].hourse+"</td>"+
            "<td>"+result[i].floor+"</td>"+
            "<td>"+result[i].room_number+"</td>"+
            "<td>"+result[i].waterprice+"</td>"+
            "<td>"+result[i].electricprice+"</td>"+
            "<td class='td-manage'>" +
            "<a title='编辑' class='btn-update'><i class='layui-icon'>&#xe642;</i></a>" +
            "</td>";
        //2.4、将td添加到tr中
        tr.append(tds);
        //2.5、将tr追加到tbody中
        tBody.append(tr);
    }
}