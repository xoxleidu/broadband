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
        url: serverPath + "/product/equipment/findEquipment",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            //var productData = res.result;
            var tableIns2 = table.render({
                elem: '#equipmentList',
                page: true,
                data: res.result.records,
                limit: 10,
                id:'equipmentListTable',
                cols: [[ //标题栏
                    {field: 'equipmentNumber', title: '设备编号', width: 120, align: "center"}
                    , {field: 'outTime', title: '出库时间', minWidth: 150, align: "center"}
                    , {field: 'addTime', title: '入库时间', minWidth: 150, align: "center"},
                    {field: 'status', title: '设备状态:0.可用,1.待出库,2.已出库', minWidth: 150, align: "center"},
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
                "equipmentNumber":$(".searchVal").val(),
            }
            $.ajax({
                type: "post",
                url: serverPath + "/product/equipment/findEquipment",
                data: JSON.stringify(datas),
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function(res){
                    //var productData = res.result;
                    var tableIns2 = table.render({
                        elem: '#equipmentList',
                        page:true,
                        data: res.result.records,
                        limit:10,
                        cols: [[ //标题栏
                            {field: 'equipmentNumber', title: '设备编号', width: 120, align: "center"}
                            , {field: 'outTime', title: '出库时间', minWidth: 150, align: "center"}
                            , {field: 'addTime', title: '入库时间', minWidth: 150, align: "center"},
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
            content : "equipmentAdd.html",
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
    $(".equipmentAdd").click(function(){
        addUser();
    })




})