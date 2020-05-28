$(document).ready(function(){
    $(".updatePassword").on("click", function () {
        homes = $(".updatePassword").attr("id");
        url = "myhome/updatePassword.do?homes="+homes;
        x_admin_show("修改与查看房间密码",url);
    });
});