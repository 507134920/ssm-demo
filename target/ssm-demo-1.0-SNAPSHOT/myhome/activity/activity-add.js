$(document).ready(function(){
    $("#editFormId").on("click","#add",doAdd);
});

function doAdd() {
    var params={
        time:$("#time").val(),
        place:$("#place").val(),
        content:$("#content").val()
    };
    console.log(params);
    var url = "doSaveActivity.do";
    if (params.content.length<=0){
        alert("活动内容不能为空，请输入活动内容！！");
        return;
    }
    $.post(url,params,function(result){
        if(result.success){
            alert("添加成功");
        }else{
            alert("添加失败");
        }
    });
}