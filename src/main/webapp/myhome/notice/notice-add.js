$(document).ready(function(){
    $("#queryFormId").on("click",".layui-btn",doQueryObjects);
    $("#tbodyId").on("click","#btn-delete",doDeleteObject)
                .on("click",".btn-update",doLoadEditPage);
    doGetObjects();
});

function doGetObjects() {
    debugger
    var url="notice.do";
    //根据pageId绑定的值 动态确定当前页
    var pageCurrent=$("#pageId").data("pageCurrent");
    if(!pageCurrent)pageCurrent=1;
    var params={"pageCurrent":pageCurrent};
    $.getJSON(url,params,function(result){
        //console.log(result);
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

function doDeleteObject() {
    layer.confirm('确认要删除吗？',function(){
        //1、获得选中的checkbox的id值
        var id = "";
        $("#tbodyId input[name ='checkId']").each(function() {
            if($(this).prop("checked")){//判断此input对象是否是选中的
                id = $(this).val();
            }
        });
        //2、判断选择的id是否为空
        if(id == ""){
            layer.msg('请至少选择一个！', {icon: 1});
            return;
        }

        //3、发起异步请求，更新数据
        var url="doDeleteObjectById.do";
        var params={"id":id};
        $.post(url,params,function(result){
            if(result.state==1){
                layer.msg('删除成功', {icon: 1});
                //重新执行查询操作
                doGetObjects();
                location.replace(location.href);
            }else if(result.state==0){
                layer.msg('删除失败', {icon: 1});
            }
        });
    })
}

function doLoadEditPage() {
    var url;
    var title;
    if($(this).hasClass("btn-update")){
        var idValue = $(this).parent().parent().data("id");
        title= "修改公告信息,id="+idValue;
        url= "toEditNoticeById.do?id="+idValue;
    }
    x_admin_show(title,url,600,400);
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
            "<td>"+result[i].ntime+"</td>"+
            "<td>"+result[i].content+"</td>"+
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

function getEditFormDate(){
    var params={
        ntime:$("#ntime").val(),
        content:$("#content").val()
    };
    return params;
}

function doQueryObjects() {
    debugger
    var params = getEditFormDate();
    var url = "doSaveNotice.do";
    if (params.content!=null && params.ntime!=null){
        $.post(url,params,function(result){
            if(result.success){
                alert("添加公告信息成功");
                location.replace(location.href);
            }else{
                alert("添加公告信息失败");
            }
        });
    }

}