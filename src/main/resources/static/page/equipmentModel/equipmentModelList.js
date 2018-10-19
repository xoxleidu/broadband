layui.use(['form','layer','table','laytpl'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    var data = {
        "currentPage": 1,
        "pageSize": 50
    }
    var serverPath = "http://localhost:8080/broadband";
    $.ajax({
        type: "post",
        url: serverPath + "/product/model/find",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            //var productData = res.result;
            var tableIns2 = table.render({
                elem: '#equipmentModelList',
                page: true,
                data: res.result.records,
                limit: 10,
                id:'equipmentModelListTable',
                cols: [[ //标题栏
                    {field: 'name', title: '设备名称', width: 120, align: "center"}
                    , {field: 'id', title: '设备id', minWidth: 150, align: "center"}
                    , {field: 'model', title: '设备型号', minWidth: 150, align: "center"}
                    , {field: 'price', title: '设备价格', minWidth: 150, align: "center"},
                    {field: 'status', title: '设备状态:0.可用,1.不可用', minWidth: 150, align: "center"},
                    {title: '操作', minWidth: 175,toolbar:'#equipment_update', fixed: "right", align: "center"}
                ]],
            });
        },
        error: function (e) {
            alertConsole(e);
        }
    })



    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            var datas={
                "currentPage": 1,
                "pageSize": 50,
                "name":$(".searchVal").val(),
            }
            $.ajax({
                type: "post",
                url: serverPath + "/product/model/find",
                data: JSON.stringify(datas),
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function(res){
                    //var productData = res.result;
                    var tableIns2 = table.render({
                        elem: '#equipmentModelList',
                        page:true,
                        data: res.result.records,
                        limit:10,
                        cols: [[ //标题栏
                            {field: 'name', title: '设备名称', width: 120, align: "center"}
                            , {field: 'id', title: '设备id', minWidth: 150, align: "center"}
                            , {field: 'model', title: '设备型号', minWidth: 150, align: "center"}
                            , {field: 'price', title: '设备价格', minWidth: 150, align: "center"},
                            {field: 'status', title: '设备状态:0.可用,1.不可用', minWidth: 150, align: "center"},
                            {title: '操作', minWidth: 175,toolbar:'#equipment_update', fixed: "right", align: "center"}
                        ]],
                    });
                },
                error: function (e) {
                    alertConsole(e);
                }
            })
        }

    });


    function addUser(){
        var index = layui.layer.open({
            title : "添加设备",
            type : 2,
            content : "equipmentModelAdd.html",
            success : function(){
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
    $(".equipmentModelAdd").click(function(){
        addUser();
    })


    table.on('tool(equipmentModelList)', function(obj) {
        var layEvent = obj.event,
            data = obj.data;
        equipmentUpdate(data);
    })






    function   equipmentUpdate(data){
        var index1 = layui.layer.open({
            title : "修改设备型号管理",
            type : 2,
            content : "equipmentModelUpdate.html",
            success : function(){
                var body = layui.layer.getChildFrame('body', index1);
                body.find("#id").val(JSON.stringify(data.id));
                body.find("#name").val(JSON.stringify(data.name));
                body.find("#model").val(JSON.stringify(data.model));
                body.find("#price").val(JSON.stringify(data.price));
                body.find("#status").val(JSON.stringify(data.status));
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index1);
        window.sessionStorage.setItem("index",index1);
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function(){
            layui.layer.full(productIndex);
        })
    }
















})