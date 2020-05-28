$(function(){
    //页面加载完成执行数据的获取操作
    doGetObjects();
    //2.监听顶点击
    $(".messageList").on("click",".infoTop",updateMessageAgreeNum);
    $(".messageList").on("click",".infoDown",updateMessageDisAgreeNum);
    //3.监听删除点击
    $(".messageList").on("click",".infoDel",delMessage);
    //添加留言
    $("#queryFormId").on("click","#send",doQueryObjects);
});

function doGetObjects(){
    var url="showMessage.do";
    $.post(url,function(result){
        //console.log(result);
        if(result.state==1){
            // 根据内容创建节点
            createEle(result.data);
        }else {
            alert(result.message);
        }
    });
}

// 创建节点方法
function createEle(result) {
    console.log(result);
    for(var i in result){//循环一次取一行
        var $weibo = $("<div class=\"info\">\n" +
            "            <p class=\"infoText\">"+result[i].content+"</p>\n" +
            "            <p class=\"infoOperation\">\n" +
            "                <span class=\"infoTime\">"+result[i].committime+"</span>\n" +
            "                <span class=\"infoHandle\">\n" +
            "                    <a href=\"javascript:;\" class='infoTop' id="+result[i].id+">"+result[i].agree+"</a>\n" +
            "                    <a href=\"javascript:;\" class='infoDown' id="+result[i].id+">"+result[i].disagree+"</a>\n" +
            "                    <a href=\"javascript:;\" class='infoDel' id="+result[i].id+">删除</a>\n" +
            "                </span>\n" +
            "            </p>\n" +
            "        </div>");
        // 插入留言
        $(".messageList").prepend($weibo);
    }
}

function updateMessageAgreeNum() {
    var url="updateMessageAgreeNum.do";
    var id =$(".infoTop").attr("id");
    var params= {id:id};
    console.log("id为"+id);
    $.post(url,params,function(result){
        if(result.state==1){
            alert("点赞成功");
            location.replace(location.href)
        }else {
            alert("点赞失败");
        }
    });
}
function updateMessageDisAgreeNum() {
    var url="updateMessageDisAgreeNum.do";
    var id =$(".infoDown").attr("id");
    var params= {id:id};
    console.log("id为"+id);
    $.post(url,params,function(result){
        if(result.state==1){
            alert("反对成功");
            location.replace(location.href)
        }else {
            alert("反对失败");
        }
    });
}

function delMessage() {
    var url="delMessage.do";
    var id =$(".infoDel").attr("id");
    //console.log("id为"+id);
    var params= {id:id};
    $.post(url,params,function(result){
        if(result.state==1){
            alert("删除成功");
            location.replace(location.href)
        }else {
            alert("删除失败");
        }
    });
}

function getEditFormDate(){
    var params={
        realname:$("#realname").val(),
        content:$("#content").val()
    };
    return params;
}

function doQueryObjects() {
    debugger
    var params = getEditFormDate();
    var url = "addMessage.do";
    if (params.content.length<=0){
        alert("留言内容不能为空，请输入留言内容！！");
        return;
    }
    $.post(url,params,function(result){
        if(result.success){
            alert(result.message);
            location.replace(location.href)
        }else{
            alert(result.message);
        }
    });

}