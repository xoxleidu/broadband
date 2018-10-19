layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;



    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    $("#sysIdcard").val(parent.$("#sysIdcard").val());
    $("#productInfo").val(parent.$("#productInfo").val());
    $("#productId").val(parent.$("#productId").val());
    $("#expensesId").val(parent.$("#expensesId").val());
    $("#equipmentIds").val(parent.$("#equipmentIds").val());
    $("#giftIds").val(parent.$("#giftIds").val());

    var productData ="";
    var expensesData = "";
    var equipmentData = "";
    var equipmentModelData = "";
    var giftData = "";

    var community = {
        "currentPage" : 1,
        "pageSize" : 50
    };


    $.ajax({
        type: "post",
        url: serverPath + "/community/query",
        data: JSON.stringify(community),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            //alert(JSON.stringify(res));
            /*var res = {
                "total": 17,
                "result": [
                    {
                        "id":1,
                        "communityName":"111"
                    },
                    {
                        "id":2,
                        "communityName":"222"
                    }
                ]
            }*/
            //<option value='' selected = 'selected'>默认选择</option>
            for(var i=0; i<res.result.records.length; i++) {
                var opt="<option value='" + res.result.records[i].id + "'";
                opt += ">" + res.result.records[i].communityName + "</option>"; //动态添加数据
                //opt += "<option value='" + testRes.result[i].id + "' selected = 'selected'>" + testRes.result[i].communityName + "</option>";
                $("select[name=communityName]").append(opt);
            }
            form.render('select','selFilter');
        }
    });


    //alert(parent.$("#productId").val() + "77");
    $.ajax({
        type: "post",
        url: serverPath + "/product/product/findProductById",
        data: parent.$("#productId").val(),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            //alert(JSON.stringify(res));
            //用户列表
            table.render({
                elem: '#productList',
                cols: [[ //标题栏
                    {title: '套餐', width: 120},
                    {title: '套餐名称',field: 'name', width: 120},
                    {title: '价格',field: 'price', width: 120},
                    {title: '小计',field: 'price', fixed:"right",width: 200,
                        templet: function(d){
                            return '<input type="text" value="' + d.price + '" class="layui-input product-money"><input type="hidden" value="' + d.id + '">'
                        }
                    }
                ]]
                ,data: res.result,
                id:"productListTable"
            });

        }
    })

    var ProductExpenses = {
        "id" : parent.$("#expensesId").val(),
        "currentPage" : 1,
        "pageSize" : 1
    };


    $.ajax({
        type: "post",
        url: serverPath + "/product/expenses/find",
        data: JSON.stringify(ProductExpenses),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            //用户列表
            table.render({
                elem: '#expensesList',
                cols: [[ //标题栏
                    {title: '资费', width: 120},
                    {title: '资费名称',field: 'name', width: 200},
                    {title: '电话号码',toolbar: '#numberPhoneAdd',width: 250},
                    {title: '价格',field: 'price', width: 120},
                    {title: '小计',field: 'price', fixed:"right",width: 200,
                        templet: function(d){
                            return '<input type="text" value="' + d.price + '" class="layui-input expenses-money"><input type="hidden" value="' + d.id + '">'
                        }
                    }
                ]],
                data: res.result.records,
                id:"expensesListTable"
            });
        }
    })


    $.ajax({
        type: "post",
        url: serverPath + "/product/model/findById",
        data: JSON.stringify(parent.$("#equipmentIds").val().split(",")),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            //用户列表
            table.render({
                elem: '#equipmentList',
                cols: [[ //标题栏
                    {title: '设备', width: 120},
                    {title: '设备名称',field: 'name', width: 200},
                    {title: '价格',field: 'price', width: 120},
                    {title: '数量',type: 'space', width: 120,
                        templet: function(d){
                            return '<input type="text" value="1" class="layui-input equipment-num"><input type="hidden" value="' + d.id + '">'
                        }
                    },
                    {title: '小计',type: 'space',fixed:"right", width: 200,
                        templet: function(d){
                            return '<input type="text" value="' + d.price + '" class="layui-input equipment-money"><input type="hidden" value="' + d.id + '">'
                        }
                    }
                ]]
                ,data: res.result,
                id:"equipmentListTable"
            });

        }
    })


    $.ajax({
        type: "post",
        url: serverPath + "/product/gift/findById",
        data: JSON.stringify(parent.$("#giftIds").val().split(",")),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            //用户列表
            table.render({
                elem: '#giftList',
                cols: [[
                    {title: '赠品', width: 120},
                    {title: '赠品',field: 'name', width: 200},
                    {title: '数量',type: 'space', width: 120,
                        templet: function(d){

                            /*$(".layui-giftNum").on("input propertychange",function(){
                                alert("变化了");
                            });*/

                            //var equipmentMoney = d.price * $('.equipment-num').val();

                            return '<input type="text" value="1" class="layui-input gift-num"><input type="hidden" value="' + d.id + '">'
                        }
                    },
                    {title: '小计',fixed:"right",width: 200}
                ]]
                ,data: res.result,
                id:"giftListTable"
            });

        }
    })




    //提交订单
    $(".layui-btn-productAdd").click(function(){

        var money =Number($('.expenses-money').val())
            + (Number($('.equipment-money').val()) * Number($('.equipment-num').val()))
            + Number($('.product-money').val());

        $("#money").val(money);

        $(".layui-btn-productAdd").text('你真的确定要购买么？'); // 只支持修改文本
        $(".layui-btn-productAdd").addClass('layui-btn-productAddEnd');
        $(".layui-btn-productAdd").removeClass("layui-btn-productAdd");
        InputCloseAll()

        var checkStatus = table.checkStatus('giftListTable'),
            data = checkStatus.data,
            newsId = [];


        var orderAddInfo = {
            //"currentPage": 0,
            "customerId": $("#customerId").val(),
            "installAddress": $("#installAddress").val(),
            "installDate": $("#installDate").val(),
            "money": $("#money").val(),
            //"pageSize": 0,
            "product": [
                {
                    "discountMoney": $('.product-money').val(),
                    "num": 1,
                    "productId": $("#productId").val(),
                    "productType": 1
                },{
                    "discountMoney": $('.expenses-money').val(),
                    "num": 1,
                    "productId": $("#expensesId").val(),
                    "productType": 2
                },{
                    "discountMoney": $('.equipment-money').val(),
                    "num": $('.equipment-num').val(),
                    "productId": $("#equipmentIds").val(),
                    "productType": 3
                },{
                    "discountMoney": 0,
                    "num": $('.gift-num').val(),
                    "productId": $("#giftIds").val(),
                    "productType": 4
                }
            ],
            "telephone": $("#telephone").val()
        };

        alert(JSON.stringify(orderAddInfo));




        /*if(data.length > 0) {
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
        }*/

        return false;

    });

    $(".layui-btn-productAddEnd").click(function(){
        alert("打印");
    });

    /*form.on("submit(productConfirmPrint)",function(data){

        $("#money").val(100);

        $("input").attr('readonly', true);
        $("input").addClass('readonlyDIV');

        $("textarea").attr('readonly', true);

        $(':radio').attr('disabled', true);
        $(':checkbox').attr('disabled', true);
        $(':button').attr('disabled', true);
        $('a').removeAttr('onclick');
        $('select').attr('disabled', true);

        $(".button-productConfirm").addClass('div-button-none');
        $(".button-productConfirmPrint").removeClass('button-productConfirmPrint');


    })*/

    function InputCloseAll() {
        $("input").attr('readonly', true);
        $("input").addClass('readonlyDIV');

        $("textarea").attr('readonly', true);

        $(':radio').attr('disabled', true);
        $(':checkbox').attr('disabled', true);
        $(':button').attr('disabled', true);
        $('a').removeAttr('onclick');
        $('select').attr('disabled', true);
    }


    $('.addProductConfirm').on('click', function(){
        /*var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';*/
        //alert($("select[name=communityName]").val());
    });


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
    table.on('tool(expensesList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        var index = layui.layer.open({
            title : '选择号码',
            type : 2,
            area: ['300px', '500px'],
            //btn: ['选择'],
            //btnAlign: 'c',
            content : 'numberPhoneSelect.html',
            success : function(layero, index){
                //var body = layui.layer.getChildFrame('body', index);
                //body.find("#customerId").val(data.customerId);  //登录名

                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        /*layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })*/
    });


    function addTrafficData(obj) {
        $('#numberPhoneSelectEnd').val(1);
        form.render('input','selNumber');
    }

})


