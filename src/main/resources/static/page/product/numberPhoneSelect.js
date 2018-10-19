var allCurrentPage = 1;
var allpageSize = 50;

layui.use(['form','layer','table','laytpl','laypage'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        laypage = layui.laypage,
        table = layui.table;

    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    var customerUrl = serverPath + "/product/phoneNumber/find";

    var pageInfo = {
        "currentPage": 1,
        "pageSize": 50
    };
    ajaxPost(customerUrl,pageInfo);


    function ajaxPost(url,data) {
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function(res){
                openTable(res);
            }

        })

    }



    function openTable(data) {
        var tableIns = table.render({
            elem: '#customerList',
            data: data.result.records,
            //cellMinWidth : 95,
            page : true,
            height : "full-40",
            limit : 30,
            //limits : [10,15,20,25],
            id : "customerListTable",
            cols : [[
                {field: 'phoneNumber', title: '号码',event: 'setSign', style:'cursor: pointer;'}
            ]]
        });





        //return tableIns;

    }


    //列表操作
    table.on('tool(customerList)', function(obj){
        var data = obj.data;
        alert(data.phoneNumber);
        //parent.addTrafficData(traffic);
        //parent.location.reload();
        //layer.close(layer.index);
        //parent.$("#numberPhoneSelectEnd").val(data.phoneNumber);
/*
        parent.$("#numberPhoneSelectEnd").css('display','block');

        layer.closeAll("iframe");
        //刷新父页面
        parent.location.reload();*/

        //显示被隐藏的元素；  content为input的id
        /*if(obj.event === 'setSign'){
            layer.prompt({
                formType: 2
                ,title: '修改 ID 为 ['+ data.id +'] 的用户签名'
                ,value: data.sign
            }, function(value, index){
                layer.close(index);

                //这里一般是发送修改的Ajax请求

                //同步更新表格和缓存对应的值
                obj.update({
                    sign: value
                });
            });
        }*/
    });

})
