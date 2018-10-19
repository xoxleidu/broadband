layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    var customerUrl = serverPath + "/product/product/update";

    form.on("submit(changeUser)",function(){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        var data = {
            "equipmentList": [
                {
                    "modelId": 1,
                    "number": 1,
                    "price": 10
                }
            ],
            "expensesList": [
                {
                    "id": 1,
                    "price": 10
                }
            ],
            "giftList": [
                {
                    "id": 1,
                    "name": $('#name').val(),
                    "outNumber": 10
                }
            ],
            "id": $('#id').val(),
            "name": $('#name').val(),
            "price": $('#price').val(),
            "status": $('#status').val()
        };

        alert(JSON.stringify(data))

        $.ajax({
            type: "post",
            url: customerUrl,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function(res){
                alert(JSON.stringify(res))
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