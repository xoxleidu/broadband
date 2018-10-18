layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    var customerUrl = serverPath + "/customer/customerMessage/queryAllCustomer";

    var pageInfo = {
        "currentPage": 1,
        "pageSize": 50
    };
    ajaxPost(customerUrl,pageInfo)

    function ajaxPost(url,data) {
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function(res){
                openTable(res);
                //return res.result;
            }
        })
    }

    function openTable(data) {
        var tableIns = table.render({
            elem: '#customerList',
            data: data.result.records,
            cellMinWidth : 95,
            page : true,
            height : "full-125",
            limit : 10,
            //limits : [10,15,20,25],
            id : "customerListTable",
            cols : [[
                {field: 'customerName', title: '姓名', width:100},
                {field: 'sex', title: '性别', align:'center',width:60},
                {field: 'idcard', title: '身份证号', width:200},
                {field: 'tel', title: '家庭电话', width:140},
                {field: 'mobile', title: '手机号码', width:140},
                {field: 'address', title: '证件地址', width:200},
                {field: 'contacts', title: '联系人', width:100},
                {field: 'contact_mobile', title: '联系人电话', width:140},
                {field: 'type', title: '客户类型', width:100},
                {field: 'creation_time', title: '创建时间', width:140},
                {field: 'status', title: '状态', width:60},
                {field: 'sys_user_id', title: '操作者', width:100},
                {title: '操作',toolbar: '#customer_order', width:100},
            ]]
        });
    }

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("newsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "customerAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".userName").val(edit.userName);  //登录名
                    body.find(".userEmail").val(edit.userEmail);  //邮箱
                    body.find(".userSex input[value="+edit.userSex+"]").prop("checked","checked");  //性别
                    body.find(".userGrade").val(edit.userGrade);  //会员等级
                    body.find(".userStatus").val(edit.userStatus);    //用户状态
                    body.find(".userDesc").text(edit.userDesc);    //用户简介
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
    table.on('tool(customerList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        //alert(JSON.stringify(layEvent));

        if(layEvent === 'edit'){ //编辑
            //addUser(data);

            //openCustomerOrder();
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
                    tableIns.reload();
                    layer.close(index);
                // })
            });
        }
    });

})
