layui.use(['form','layer','table','laytpl','element'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        element = layui.element,
        table = layui.table;

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }
    var customerId = getUrlParam('customerId');
    alert(customerId);

    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    //var customerOrderUrl = serverPath + "/order/findSelectAll";
    var customerOrderUrl = serverPath + "/order/orderQuery";
    var allOrderUrl = serverPath + "/order/orders";

    var findData = '';


    if (customerId == '' || customerId == undefined || customerId == null) {
        findData = {
            "currentPage": 1,
            "pageSize": 50
        };
        ajaxPost(allOrderUrl,findData);
    } else {
        findData = {
            "currentPage": 1,
            "id": Number(customerId),
            "pageSize": 50
        };
        ajaxPost(customerOrderUrl,findData);
    }
    //alert(customerId);



    //alert(JSON.stringify(findData));


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
            elem: '#orderList',
            data: data.result.records,
            cellMinWidth : 95,
            page : true,
            height : "full-125",
            limit : 10,
            //limits : [10,15,20,25],
            id : "orderListTable",

            
            cols : [[
                {field: 'productId', title: 'ID', width:10},
                {field: 'orderNumber', title: '订单号', width:350},
                /*{field: 'money', title: '订单金额', width:350},
                {field: 'createTime', title: '订单时间', width:350},
                {field: 'projectType', title: '产品类型', width:350},//产品类型 1:套餐2资费3设备4赠品
                {field: 'type', title: '状态', width:350}//状态(0.在线/激活,1.退费/未激活,2.过期)*/
                {toolbar: '#order_bar', title: '操作', width:80},
            ]]
        });
    }



    //新闻列表
    /*var tableIns = table.render({
        elem: '#newsList',
        //type: 'POST',
        //contentType: 'application/json',
        url : serverPath + '/workOrder/queryAll',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits : [10,15,20,25],
        id : "newsListTable",
        request: {
            pageName: 'currentPage' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        },
        //where: {currentPage: 1, pageSize: 1},
        cols : [[


            {field: 'message', title: '文章标题', width:350}
        ]]
    });*/

    //是否置顶
    form.on('switch(newsTop)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            if(data.elem.checked){
                layer.msg("置顶成功！");
            }else{
                layer.msg("取消置顶成功！");
            }
        },500);
    })

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

    //添加文章
    function addNews(edit){
        var index = layui.layer.open({
            title : "添加文章",
            type : 2,
            content : "newsAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".newsName").val(edit.newsName);
                    body.find(".abstract").val(edit.abstract);
                    body.find(".thumbImg").attr("src",edit.newsImg);
                    body.find("#news_content").val(edit.content);
                    body.find(".newsStatus select").val(edit.newsStatus);
                    body.find(".openness input[name='openness'][title='"+edit.newsLook+"']").prop("checked","checked");
                    body.find(".newsTop input[name='newsTop']").prop("checked",edit.newsTop);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    $(".addNews_btn").click(function(){
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(orderList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            alert(JSON.stringify(data));
            $("#productId").val(data.productId);
            OpenFrame();

        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此文章？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                    tableIns.reload();
                    layer.close(index);
                // })
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });


    function OpenFrame() {
        var productConfirmIndex = layui.layer.open({
            title : "产品选择",
            type : 2,
            id:'productConfirm',
            content: "../product/productConfirm.html",
            success: function(layero, index){
                //var body = layui.layer.getChildFrame('body', index);
                //body.find("#equipmentIds").val("12333");  //登录名
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },1000)
            }
        })
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function(){
            layui.layer.full(productConfirmIndex);
        })
        layui.layer.full(productConfirmIndex);
    }

})