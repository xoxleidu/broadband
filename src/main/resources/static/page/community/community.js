

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
        url: serverPath + "/community/query",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            //var productData = res.result;
            var tableIns2 = table.render({
                elem: '#communityList',
                page: true,
                data: res.result.records,
                limit: 10,
                id:'communityListTable',
                cols: [[ //标题栏
                    {field: 'communityName', title: '小区名称', minwidth: 190, align: "center"},
                    {title: '操作', minWidth: 175,toolbar:'#community_update', fixed: "right", align: "center"}
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
                "communityName":$(".searchVal").val()
            }
            $.ajax({
                type: "post",
                url: serverPath + "/community/query",
                data: JSON.stringify(datas),
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function(res){
                    //var productData = res.result;
                    var tableIns2 = table.render({
                        elem: '#communityList',
                        page:true,
                        data: res.result,
                        limit:10,
                        cols: [[ //标题栏
                            {field: 'communityName', title: '小区名称', minwidth: 190, align: "center"},
                            {title: '操作', minWidth: 175,toolbar:'#community_update', fixed: "right", align: "center"}
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
            title : "添加小区",
            type : 2,
            content : "communityAdd.html",
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
    $(".communityAdd").click(function(){
        addUser();
    })


    function   communityUpdate(data){
        var index1 = layui.layer.open({
            title : "修改小区",
            type : 2,
            content : "communityUpdate.html",
            width:"300px",
            success : function(){

                var body = layui.layer.getChildFrame('body', index1);
                body.find("#id").val(JSON.stringify(data.id));
                body.find("#communityName").val(JSON.stringify(data.communityName));
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





    table.on('tool(communityList)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            communityUpdate(JSON.stringify(data.id))
        } else if(obj.event === 'del'){
            layer.confirm('确认删除吗?', function(index){
                $.ajax({
                    type: "post",
                    url: serverPath + "/community/delete",
                    data:  JSON.stringify(data.id),
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    success: function(res){
                    },
                    error: function (e) {
                        alertConsole(e);
                    }
                })
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            communityUpdate(data)
        }
    });















})