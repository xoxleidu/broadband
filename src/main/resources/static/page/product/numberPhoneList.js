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
            height : "full-125",
            limit : 10,
            //limits : [10,15,20,25],
            id : "customerListTable",
            cols : [[
                {field: 'phoneNumber', title: '号码', width:200},
                {field: 'status', title: '状态', width:60},
                {title: '操作',toolbar: '#customer_order',fixed:"right"},
            ]]
        });





        //return tableIns;

    }



    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){


                var searchInfo = {
                    "currentPage": 1,
                    "phoneNumber": $(".searchVal").val(),
                    "pageSize": 50
                };
            //alert(JSON.stringify(searchInfo));
            ajaxPost(customerUrl,searchInfo);
            tableIns.reload();

        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
        var title = '添加号码';
        var content = 'numberPhoneAdd.html';
        if(edit){
            title = '修改号码';
            content = 'numberPhoneUpdata.html';
        }
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : content,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                        body.find("#id").val(edit.id);  //登录名
                        body.find("#phoneNumber").val(edit.phoneNumber);  //登录名
                        //body.find("#sex input[value="+edit.sex+"]").prop("checked","checked");  //性别
                        body.find("#status").val(edit.status);  //会员等级
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


    $('.testbtnbtn').click(function () {

        $('.testbtnbtn').data().url = "123.html";
        //$(this).attr("customerId",'123.html?customerId=1');
        alert(JSON.stringify($(this).data().url));
        //$(this).href = "dis.html";

        /*var dlgStr = '<div class="dialog"><div class="dialog-head"><div class="close-btn" onclick="closeDlg()">关闭</div></div><div class="dialog-content"><a href="'
            + addr + '" class="dialog-img"/></div></div>';
        $('body').append(dlgStr)
        $('.dialog-img').media({
            width : 700,
            height : 700
        });*/

        parent.addTab($(this));

    })

    //列表操作
    table.on('tool(customerList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            //alert(JSON.stringify(data));
            addUser(data);
        }else if (layEvent === 'info'){

            window.location.href='../order/customerorderList.html?customerId=' + data.id;

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
                    /*var delData = {
                        "id": data.id
                    };
                    $.ajax({
                        type: "post",
                        url: serverPath + '/customer/customerMessage/delete',
                        data: JSON.stringify(delData),
                        contentType: "application/json;charset=utf-8",
                        dataType: "json",
                        success: function(res){
                            layer.close(index);
                            window.location.reload();
                        }
                    })*/
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
