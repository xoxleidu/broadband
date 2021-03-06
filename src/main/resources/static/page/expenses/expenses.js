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
        url: serverPath + "/product/expenses/find",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            //var productData = res.result;
            var tableIns2 = table.render({
                elem: '#expensesList',
                page: true,
                data: res.result.records,
                limit: 10,
                id:'expensesListTable',
                cols: [[ //标题栏
                    {field: 'name', title: '资费名称', width: 120, align: "center"}
                    , {field: 'type',title:'资费类型', minWidth: 150, align: "center"}
                    , {field: 'ipName',title:'资费ip名称', minWidth: 150, align: "center"}
                    , {field: 'cycle', title: '资费周期', minWidth: 150, align: "right", rowspan:"2"}
                    , {field: 'cycleType', minWidth: 150, align: "left"}
                    , {field: 'status', title: '资费状态:0.可用,1.不可用', minWidth: 150, align: "center"},
                    {title: '操作', minWidth: 175,toolbar:'#expenses_update', fixed: "right", align: "center"}
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
                "name":$(".searchVal").val()
            }
            $.ajax({
                type: "post",
                url: serverPath + "/product/expenses/find",
                data: JSON.stringify(datas),
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function(res){
                    //var productData = res.result;
                    var tableIns2 = table.render({
                        elem: '#expensesList',
                        page:true,
                        data: res.result.records,
                        limit:10,
                        cols: [[ //标题栏
                            {field: 'name', title: '资费名称', width: 120, align: "center"}
                            , {field: 'type',title:'资费类型', minWidth: 150, align: "center"}
                            , {field: 'ipName',title:'资费ip名称', minWidth: 150, align: "center"}
                            , {field: 'cycle', title: '资费周期', minWidth: 150, align: "right"}
                            , {field: 'cycleType', minWidth: 150, align: "left"}
                            , {field: 'price', title: '定价', minWidth: 150, align: "center"},
                            {field: 'status', title: '资费状态:0.可用,1.不可用', minWidth: 150, align: "center"},
                            {title: '操作', minWidth: 175,toolbar:'#expenses_update', fixed: "right", align: "center"}
                        ]],

                    });
                },
                error: function (e) {
                    alertConsole(e);
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });


    function addUser(){
        var index = layui.layer.open({
            title : "添加资费",
            type : 2,
            content : "expensesAdd.html",
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
    $(".expensesAdd").click(function(){
        addUser();
    })


    table.on('tool(expensesList)', function(obj) {
        var layEvent = obj.event,
            data = obj.data;
        giftUpdate(data);
    })

    // function   giftUpdate(data){
    //     var index1 = layui.layer.open({
    //         title : "修改赠品数量",
    //         type : 2,
    //         content : "giftUpdate.html",
    //         success : function(){
    //             setTimeout(function(){
    //                 layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
    //                     tips: 3
    //                 });
    //             },500)
    //         }
    //     })
    // }













})