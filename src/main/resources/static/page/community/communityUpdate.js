

layui.use(['form','layer'],function() {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var serverPath = "http://localhost:8080/broadband";
    //提交个人资料
    form.on("submit(changeCommunity)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //将填写的用户信息存到session以便下次调取
        var userInfoHtml = '';
        userInfoHtml = {
            "currentPage": 1,
            "pageSize": 50,
            "id":$("#id").val(),
            "communityName":$("#communityName").val()
        };


        $.ajax({
            type: "post",
            url: serverPath + "/community/update",
            data: JSON.stringify(userInfoHtml),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (data) {
                setTimeout(function () {
                    layer.close(index);
                    layer.msg("提交成功！");
                    //window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
                    OpenFrame();
                    //window.location.href='../product/product.html';
                }, 500);
            },
            error: function (e) {
                alertConsole(e);
            }
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })


    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    $(window).resize(function(){
        layui.layer.full(productIndex);
    })









})

