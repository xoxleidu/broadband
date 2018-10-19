layui.use(['form','layer'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    var customerUrl = serverPath + "/product/phoneNumber/update";

    form.on("submit(updataCustomer)",function(){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息

        // body.find("#id").val(edit.id);  //登录名
        // body.find("#customerName").val(edit.customerName);  //登录名
        // //body.find("#sex input[value="+edit.sex+"]").prop("checked","checked");  //性别
        // body.find("#idcard").val(edit.idcard);  //会员等级
        // body.find("#tel").val(edit.tel);  //会员等级
        // body.find("#mobile").val(edit.mobile);  //邮箱
        // body.find("#address").val(edit.address);    //用户状态
        // body.find("#contacts").val(edit.contacts);  //会员等级
        // body.find("#contactMobile").val(edit.contactMobile);    //用户状态
        // //body.find("#type input[value="+edit.type+"]").prop("checked","checked");  //性别
        // body.find("#creationTime").val(edit.creationTime);    //用户状态
        // body.find("#status").val(edit.status);    //用户状态
        // body.find("#sysUserId").val(edit.sysUserId);    //用户状态
        // body.find("#name").text(edit.name);    //用户简介
        //
        // "contactMobile": "string",
        // "contacts": "string",
        // "customerName": "string",
        // "id": 0,
        // "mobile": "string"

        var data = {
            "id": $('#id').val(),
            "phoneNumber": $('#phoneNumber').val(),
            "status": $('#status').val()
        };

        $.ajax({
            type: "post",
            url: customerUrl,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function(res){
                alert(JSON.stringify(res));
                setTimeout(function(){
                    top.layer.close(index);
                    top.layer.msg("用户修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                },2000);
            }
        })
        return false;
    })

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})