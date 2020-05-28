$(document).ready(function(){
    $("#queryFormId").on("click","#btn-search",doQueryObjects);
    //页面加载完成执行数据的获取操作
    doGetObjects();
});

//查询按钮
function doQueryObjects() {
    //1.修改当前页的值为1
    $("#pageId").data("pageCurrent",1);
    //2.执行查询动作(重用doGetObjects方法)
    doGetObjects();
}

//获取表单数据
function getQueryFormData(){
    //根据id获得具体对象的值,然后封装到JSON对象
    var params={
        option:$("#option").val()
    };
    return params;
}

//获取房间信息
function doGetObjects(){
debugger
    var url;
    var option = getQueryFormData();
    if (option.option === "已租"){
        url="doGetRentAlready.do";
    }
    if (option.option === "未租"){
        url="doGetNoRent.do";
    }
    if (option.option === "全部"){
        url="doGetAllObject.do";
    }
    //根据pageId绑定的值 动态确定当前页
    var pageCurrent=$("#pageId").data("pageCurrent");
    if(!pageCurrent)pageCurrent=1;
    var params={"pageCurrent":pageCurrent};

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
        var tds = "<td>"+result[i].id+"</td>"+
            "<td>"+result[i].hourse+"</td>"+
            "<td>"+result[i].floor+"</td>"+
            "<td>"+result[i].room_number+"</td>"+
            "<td>"+result[i].price+"</td>";
        //2.4、将td添加到tr中
        tr.append(tds);
        //2.5、将tr追加到tbody中
        tBody.append(tr);
    }
}