$(document).ready(function(){
    $("#queryFormId").on("click","#btn-search",doQueryObjects);
    doFindAllpic();
});

//查询按钮
function doQueryObjects() {
    //1.修改当前页的值为1
    $("#pageId").data("pageCurrent",1);
    //2.执行查询动作(重用doGetObjects方法)
    doGetObjects();
}
//获取房间信息
function doGetObjects(){
debugger
    var url = "toPic.do";
    //根据pageId绑定的值 动态确定当前页
    var pageCurrent=$("#pageId").data("pageCurrent");
    if(!pageCurrent)pageCurrent=1;
    var id=$("#id").val();
    var params={"id":id};
    $.getJSON(url,params,function(result){
        if(result.state==1){
            //将数据显示在table的tbody中
            setTableBodyRows(result.data);
        }else{
            alert(result.message);
        }
    });
}

//将数据填充到table的body中
//将数据填充到route的ul中
function setTableBodyRows(result) {
debugger
    var route_lis = "";
    //2、迭代数据集result
    for (var i = 0; i < result.length; i++) {
        //获取{rid:1,rname:"xxx"}

        var li = '<li>\n' +
            '                        <div class="img"><img src="../'+result[i].pic+'" style="width: 299px;"></div>\n' +
            '                    </li>';
        route_lis += li;
    }
    $("#tbodyId").html(route_lis);
    //定位到页面顶部
    //window.scrollTo(0,0);
}

function doFindAllpic() {
    var url = "findAllPic.do";
    $.getJSON(url,function(result){
        if(result.state==1){
            //将数据显示在table的tbody中
            setPicTableBodyRows(result.data);
        }else{
            alert(result.message);
        }
    });
}

function setPicTableBodyRows() {
    var route_lis = "";
    //2、迭代数据集result
    for (var i = 0; i < result.length; i++) {
        //获取{rid:1,rname:"xxx"}

        var li = '<li>\n' +
            '                        <div class="img"><img src="../'+result[i].pic+'" style="width: 299px;"></div>\n' +
            '                    </li>';
        route_lis += li;
    }
    $("#tbodyId").html(route_lis);
}
