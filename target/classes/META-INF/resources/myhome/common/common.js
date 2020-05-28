/*用户-停用*/
function member_stop(obj,id){
    alert(id);
    if($(obj).attr('title')=='启用'){
        alert(id);
        //alert($(obj).attr('title'));
        //发异步把用户状态进行更改
        $(obj).attr('title','禁用')
        $(obj).find('i').html('&#xe62f;');

        $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
        layer.msg('已启用!',{icon: 5,time:1000});
    }else if ($(obj).attr('title')=='禁用'){
        alert(id);
        $(obj).attr('title','启用')
        $(obj).find('i').html('&#xe601;');

        $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已禁用');
        layer.msg('已禁用!',{icon: 5,time:1000});
    }
}
