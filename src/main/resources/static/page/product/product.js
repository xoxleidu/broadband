layui.use(['form','layer'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //alert(JSON.stringify(layer));

    ///product/product/findProductById
    ///product/expenses/find
    ///product/equipment/findEquipment
    ///product/model/find
    ///product/gift/findByName

    //页面初始化
    var serverPath = "http://localhost:8080/broadband";
    $("#sysIdcard").val(parent.$('#idcard').val());
    //parent.parent.$("#hideCollectionId").val();//取得父页面之父页面的非动态生成的元素
    var findProductBase = {
        "currentPage": 1,
        "pageSize": 50
    };

    var productData ="";
    var expensesData = "";
    var equipmentData = "";
    var equipmentModelData = "";
    var giftData = "";

    $.ajax({
        type: "post",
        url: serverPath + "/product/product/findProductBase",
        data: JSON.stringify(findProductBase),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            productData = res.result.records;
            //var productData = res.result;
            productList(productData);
        },
        error: function (e) {
            alertConsole(e);
        }
    })

    $.ajax({
        type: "post",
        url: serverPath + "/product/expenses/find",
        data: JSON.stringify(findProductBase),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            expensesData = res.result.records;
            //var productData = res.result;
            expensesList(expensesData);
        },
        error: function (e) {
            alertConsole(e);
        }
    })

    $.ajax({
        type: "post",
        url: serverPath + "/product/model/find",
        data: JSON.stringify(findProductBase),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            equipmentData = res.result.records;
            //var productData = res.result;
            equipmentList(equipmentData);
        },
        error: function (e) {
            alertConsole(e);
        }
    })

    $.ajax({
        type: "post",
        url: serverPath + "/product/gift/findByName",
        data: JSON.stringify(findProductBase),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            giftData = res.result.records;
            //var productData = res.result;
            giftList(giftData);
        },
        error: function (e) {
            alertConsole(e);
        }
    })

    function productList(data){

        var dataHtml = '';
        for(var i=0;i<data.length;i++){
            dataHtml += '<input name="product" value="'
                + data[i].id
                + '" title="'
                + data[i].name
                + '"type="radio">';
        }
        $(".product_content").html(dataHtml);
        form.render();
    }

    function expensesList(data){

        var dataHtml = '';
        for(var i=0;i<data.length;i++){
            dataHtml += '<input name="expenses" value="'
                + data[i].id
                + '" title="'
                + data[i].name
                + '"type="radio">';
        }
        $(".expenses_content").html(dataHtml);
        form.render();
    }

    function equipmentList(data){

        var dataHtml = '';
        for(var i=0;i<data.length;i++){
            dataHtml += '<input name="equipment" value="'
                + data[i].id
                + '" title="'
                + data[i].name
                + '"type="checkbox">';
        }
        $(".equipment_content").html(dataHtml);
        form.render();
    }

    function giftList(data){

        var dataHtml = '';
        for(var i=0;i<data.length;i++){
            dataHtml += '<input name="gift" value="'
                + data[i].id
                + '" title="'
                + data[i].name
                + '"type="checkbox">';
        }
        $(".gift_content").html(dataHtml);
        form.render();
    }

    //提交个人资料
    form.on("submit(addProduct)",function(data){
        var productIndex = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});

        $('#productId').val($("input[name='product']:checked").val());
        $('#expensesId').val($("input[name='expenses']:checked").val());

        var checkboxEquipment = [];
        $.each($('input:checkbox[name="equipment"]:checked'),function(){
            checkboxEquipment.push($(this).val());

        })
        $('#equipmentIds').val(checkboxEquipment);

        var checkboxGift = [];
        $.each($('input:checkbox[name="gift"]:checked'),function(){
            checkboxGift.push($(this).val());

        })
        $('#giftIds').val(checkboxGift);


        var productInfo = '';
        productInfo = {
            'product' : $("input[name='product']:checked").val(),
            'expenses' : $("input[name='expenses']:checked").val(),
            'equipment' : checkboxEquipment,
            'gift' : checkboxGift
        };

        $('#productInfo').val(JSON.stringify(productInfo));


        //将填写的用户信息存到session以便下次调取
        //window.sessionStorage.setItem("productInfoJson",JSON.stringify(productInfoJson));

        setTimeout(function(){
            layer.close(productIndex);
            layer.msg("提交成功！");
            OpenFrame();
        },500);


        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })


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

        /*layer.open({
            type: 2,
            skin: 'layui-layer-lan',
            title: '产品订单',
            fix: false,
            shadeClose: false,
            maxmin: true,
            id:'productConfirm',
            move: false,
            closeBtn:2,
            //以下代码为打开窗口添加按钮
            /!* btn: ['确定', '取消'],
            btnAlign: 'c',
            yes: function(index, layero){
              /!* //layer.closeAll();//关闭所有弹出层
              //var parentWin = layero.find('iframe')[0];
              var parentWin = layer.getChildFrame('body', index);
              alert(parentWin);
              parentWin.contentWindow.doOk();
              //layer.close(index);//这块是点击确定关闭这个弹出层
            }, *!/
            area: ['750px', '450px'],
            content: "../product/productConfirm.html",
            success: function(layero, index){
                /!*var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                console.log(body.html()) //得到iframe页的body内容
                body.find("input[name='productInfo']").val(JSON.stringify(productInfoJson))*!/
            }
        });*/

    }


    /*$.ajax({
            type: "post",
            url: "http://localhost:8080/broadband/customer/customerMessage/add",
            data: JSON.stringify(userInfoHtml),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (data) {
                /!*if (data.flag === 1) {
                    kenWindowClose(true);
                    var callback = eval("window.parent.LoadData1");
                    callback("操作成功！");
                }
                else {
                    GysAlert({
                        content: data.message
                    });
                }*!/

                setTimeout(function(){
                    layer.close(index);
                    layer.msg("提交成功！");
                },2000);
            },
            error: function (e) {
                alertConsole(e);
            }
        });*/


})
