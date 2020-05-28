$(document).ready(function(){
    $("#queryFormId").on("click","#send",doQueryObjects)
});

function getEditFormDate(){
    var params={
        realname:$("#people").val(),
        content:$("#content").val(),
        ntime:$("#ntime").val()
    }
    return params;
}

function doQueryObjects() {
debugger

    var params = getEditFormDate();
    var url = "addRepair.do";
    if (params.content.length<=0){
        alert("留言内容不能为空，请输入留言内容！！");
        return;
    }
    $.post(url,params,function(result){
        //console.log(result);
        if(result.success){
            alert(result.message);
        }else{
            alert(result.message);
        }
    });

}