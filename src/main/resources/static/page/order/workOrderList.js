var allCurrentPage = 1;
var allpageSize = 50;

layui.use(['form','layer','table','laytpl','element'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        element = layui.element,
        table = layui.table;

    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    var url = serverPath + "/workOrder/query";

    var pageInfo = {
        "currentPage": 1,
        "pageSize": 50
    };

    ajaxPost(url,pageInfo);


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
            elem: '#dataList',
            data: data.result.records,
            //cellMinWidth : 95,
            page : true,
            height : "full-125",
            limit : 10,
            //limits : [10,15,20,25],
            id : "workOrderListTable",
            cols : [[
                {field: 'customerName', title: '客户姓名', width:100},
                {field: 'theRepairOrderId', title: '工单编号', width:100},
                {field: 'mobile', title: '联系电话', width:140},
                {field: 'appointmentDate', title: '预约时间', width:200},
                {field: 'userId', title: '安装维修人员及其联系电话', width:200},
                {field: 'type', title: '类型', width:100},//类型（1 维修，2 安装）
                {field: 'maintenanceType', title: '维修类型', width:100},//维修类型（1 宽带，2 电视，3 固话）
                {field: 'maintenanceReason', title: '维修原因', width:160},
                {field: 'installState', title: '完成状态', width:100},//完成状态（1 未完成，2 完成，3 确认完成）
                {field: 'orderNumber', title: '订单编号', width:100},//（ 关联安装地址，产品名称，产品信息 ）
                {field: 'note', title: '备注', width:100},
                {title: '操作',toolbar: '#customer_order'},
            ]]
        });

        return tableIns;
    }



    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){

            var i = $(".searchSelect").val();
            var searchInfo = '';

            if (i == 1) {
                searchInfo = {
                    "currentPage": 1,
                    "customerName": $(".searchVal").val(),
                    "pageSize": 50
                };
            } else if (i == 2) {
                searchInfo = {
                    "currentPage": 1,
                    "mobile": $(".searchVal").val(),
                    "pageSize": 50
                };
            } /*else if (i == 3) {
                searchInfo = {
                    "currentPage": 1,
                    "type": $(".searchVal").val(),
                    "pageSize": 50
                };
            }
            else if (i == 4) {
                searchInfo = {
                    "currentPage": 1,
                    "installState": $(".searchVal").val(),
                    "pageSize": 50
                };
            }
            else if (i == 5) {
                searchInfo = {
                    "currentPage": 1,
                    "maintenanceType": $(".searchVal").val(),
                    "pageSize": 50
                };
            }*/
            //alert(JSON.stringify(searchInfo));
            ajaxPost(url,searchInfo);
            tableIns.reload();

        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
        var title = '修改工单';
        var content = 'workOrderUpdata.html';

        var index = layui.layer.open({
            title : title,
            type : 2,
            content : content,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                        body.find("#id").val(edit.id);  //登录名
                        body.find("#appointmentDate").val(edit.appointmentDate);  //登录名
                        //body.find("#sex input[value="+edit.sex+"]").prop("checked","checked");  //性别
                        body.find("#userId").val(edit.userId);  //会员等级
                        body.find("#type").val(edit.type);  //会员等级
                        body.find("#maintenanceType").val(edit.maintenanceType);  //邮箱
                        body.find("#maintenanceReason").val(edit.maintenanceReason);    //用户状态
                        body.find("#installState").val(edit.installState);  //会员等级
                        body.find("#note").val(edit.note);    //用户状态
                        //body.find("#type input[value="+edit.type+"]").prop("checked","checked");  //性别
                        body.find("#mobile").val(edit.mobile);    //用户状态
                        body.find("#customerName").val(edit.customerName);    //用户状态
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })


    function openCustomerOrder(data) {
        alert("openCustomerOrder");
    }

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })



    //列表操作
    table.on('tool(dataList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            alert(JSON.stringify(data));
            addUser(data);
        }else if (layEvent === 'info'){

            window.location.href='../order/orderList.html?customerId=' + data.id;

        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                //     tableIns.reload();
                //     layer.close(index);
                // })
            });
        }
    });

})
