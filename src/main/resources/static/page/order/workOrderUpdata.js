layui.use(['form','layer'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    var url = serverPath + "/workOrder/update";

    form.on("submit(updataCustomer)",function(){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息

        // body.find("#id").val(edit.id);  //登录名
        // body.find("#appointmentDate").val(edit.appointmentDate);  //登录名
        // //body.find("#sex input[value="+edit.sex+"]").prop("checked","checked");  //性别
        // body.find("#userId").val(edit.userId);  //会员等级
        // body.find("#type").val(edit.type);  //会员等级
        // body.find("#maintenanceType").val(edit.maintenanceType);  //邮箱
        // body.find("#maintenanceReason").val(edit.maintenanceReason);    //用户状态
        // body.find("#installState").val(edit.installState);  //会员等级
        // body.find("#note").val(edit.note);    //用户状态
        // //body.find("#type input[value="+edit.type+"]").prop("checked","checked");  //性别
        // body.find("#mobile").val(edit.mobile);    //用户状态
        // body.find("#customerName").val(edit.customerName);    //用户状态
        //
        // "contactMobile": "string",
        // "contacts": "string",
        // "customerName": "string",
        // "id": 0,
        // "mobile": "string"

        var data = {
            "id": $('#id').val(),
            "type": $('#type').val(),
            "customerName": $('#customerName').val(),
            "mobile": $('#mobile').val(),
            "appointmentDate": $('#appointmentDate').val(),
            "userId": $('#userId').val(),
            "maintenanceReason": $('#maintenanceReason').val(),
            "note": $('#note').val()
        };

        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function(res){
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