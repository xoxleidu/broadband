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

    /*$.ajax({
        type: "post",
        url: serverPath + "/product/product/findProductById",
        data: parent.$("#productId").val(),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function(res){
            alert(JSON.stringify(res.result));
            //用户列表
            table.render({
                elem: '#productList',
                cols: [[ //标题栏
                    {field: 'name', width: 120}
                    ,{field: 'price', minWidth: 150}
                ]]
                ,data: res.result,
                id:"productListTable"
            });

        }
    })*/

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
                    {field: 'name', width: 120}
                    ,{field: 'price', minWidth: 150}
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
                    {field: 'name', width: 120}
                    ,{field: 'price', minWidth: 150}
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
                cols: [[ //标题栏
                    {field: 'name', width: 120}
                ]]
                ,data: res.result,
                id:"giftListTable"
            });

        }
    })


    form.on("submit(productConfirmPrint)",function(data){

        $("input").attr('readonly', true);
        $("input").addClass('readonlyDIV');

        $("textarea").attr('readonly', true);

        $(':radio').attr('disabled', true);
        $(':checkbox').attr('disabled', true);
        $(':button').attr('disabled', true);
        $('a').removeAttr('onclick');
        $('select').attr('disabled', true);

        $(".button-productConfirm").addClass('div-button-none');
        $(".button-productConfirmPrint").addClass('div-button-block');

    })

    form.on("submit(productConfirmPrint)",function(data){

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

    })


    $('.addProductConfirm').on('click', function(){
        /*var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';*/

        alert("打印");
    });



})


