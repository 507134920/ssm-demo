$(function () {
    $("#hourse_pasword_update").on("click", function () {
        var number = getNumber();
        $("#hourse_pasword").attr("value", number);
    });

    $("#room_password_update").on("click", function () {
        var number = getNumber();
        $("#room_password").attr("value", number);
    });

});

function getNumber() {
    url = "dogetNumber.do";
    $.getJSON(url,function(result){
        console.log(result);
        if (result.state==1){
            number = result.message;
        }
    });
    return number;
}
