layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    var serverPath = "http://localhost:8080/broadband";
    //提交个人资料
    form.on("submit(changeUser)",function(data){

        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //将填写的用户信息存到session以便下次调取
        var userInfoHtml = '';
        userInfoHtml = {
            'address' : $("#address").val(),
            'sex' : data.field.sex,
            'contactMobile' : $("#contactMobile").val(),
            'contacts' : $("#contacts").val(),
            'idcard' : $("#idcard").val(),
            'mobile' : $("#mobile").val(),
            'name' : $("#name").val(),
            'tel' : $("#address").val(),
            'type': 0
        };

        $.ajax({
            type: "post",
            url: serverPath + "/customer/customerMessage/add",
            data: JSON.stringify(userInfoHtml),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (data) {
                setTimeout(function(){
                    layer.close(index);
                    layer.msg("提交成功！");
                    //window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
                    OpenFrame();
                    //window.location.href='../product/product.html';
                },500);
            },
            error: function (e) {
                alertConsole(e);
            }
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    function OpenFrame() {
        var productIndex = layui.layer.open({
            title : "产品选择",
            type : 2,
            id:'product',
            content: "../product/product.html",
            success: function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },1000)
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function(){
            layui.layer.full(productIndex);
        })
        layui.layer.full(productIndex);

    }

    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        // $.post("上传路径",{
        //     userName : $(".userName").val(),  //登录名
        //     userEmail : $(".userEmail").val(),  //邮箱
        //     userSex : data.field.sex,  //性别
        //     userGrade : data.field.userGrade,  //会员等级
        //     userStatus : data.field.userStatus,    //用户状态
        //     newsTime : submitTime,    //添加时间
        //     userDesc : $(".userDesc").text(),    //用户简介
        // },function(res){
        //
        // })
        setTimeout(function(){
            top.layer.close(index);
            top.layer.msg("用户添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },2000);
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