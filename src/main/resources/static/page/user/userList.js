layui.config({
    base: '../../js/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    setter: 'config' //如果 config.js 是在根目录，也可以不用设定别名!!!!! layui.use引用 setter
    //,mod1: 'admin/mod1' //相对于上述 base 目录的子目录
});
layui.use(['form','layer','table','laytpl','laypage','setter'],function(){
    var form = layui.form
        ,layer = parent.layer === undefined ? layui.layer : top.layer
        ,$ = layui.jquery
        ,laytpl = layui.laytpl
        ,table = layui.table
        ,laypage = layui.laypage
    ;

    //页面初始化
    var ajaxUrl = layui.setter.serverPath + "/customer/customerMessage/queryAllCustomer";
    var ajaxData = layui.setter.ajaxData_pageSize;
    //debugger;

    //ajax调用后台
    ajaxPost(ajaxUrl,ajaxData);


    //渲染列表 更新后刷新 1 : tableIns.reload(); 2 : table.reload("id",{})
    var tableIns = table.render({
        elem: '#tableList',//前台容器ID
        //data: data.result.records,
        //cellMinWidth : 95,
        page : false,
        //height : "full-20",//距离下方距离 padding-bottom
        limit : ajaxData.pageSize,
        //limits : [10,15,20,25],
        id : "tableListTable",//reload引用
        cols : [[
            {field: 'customerName', title: '姓名', width:100},
            {field: 'sex', title: '性别', align:'center',width:60},
            {field: 'contact_mobile', title: '联系人电话', width:140},
            {field: 'type', title: '客户类型', width:100},
            {field: 'creation_time', title: '创建时间', width:140},
            {field: 'status', title: '状态', width:60},
            {field: 'sys_user_id', title: '操作者', width:100},

        ]]
    });

    //ajax调用后台方法
    function ajaxPost(url,data,currentPage) {
        if(currentPage == "" || currentPage == undefined || currentPage == null){
            data.currentPage = 1;
        }else{
            data.currentPage = currentPage;
        }
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function(res){
                if (res.code == 000) {
                    layer.msg(res.message);
                    tableIns.reload({
                        data : res.result.records,
                        //可以对数据进行处理
                        //table.数据处理 cols [[ { * } ]]
                        /*{field: 'newsTop', title: '是否置顶', align:'center', templet:function(d){
                            return '<input type="checkbox" name="newsTop" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" '+d.newsTop+'>'
                        }},
                        {field: 'newsTime', title: '发布时间', align:'center', minWidth:110, templet:function(d){
                            return d.newsTime.substring(0,10);
                        }},*/
                        cols : [[
                            {field: 'idcard', title: '身份证号', width:200},
                            {field: 'tel', title: '家庭电话', width:140},
                            {field: 'mobile', title: '手机号码', width:140},
                            {field: 'address', title: '证件地址', width:200},
                            {field: 'contacts', title: '联系人', width:100},
                            {title: '操作',toolbar: '#tableListBar'},//前台toolbar的ID
                        ]]
                    });
                    //分页方法
                    pageslist(res.result.total,ajaxData.pageSize,data.currentPage)
                }else {
                    layer.msg(res.message);
                }
            }
        })
    }

    //分页方法
    function pageslist(pages,pageSize,currentPage){
        //debugger;
        laypage.render({
            elem: 'pagers'//前台容器ID
            ,count: pages //后台返回分页条数的总和
            ,limit: pageSize //每页显示的数据的条数,layui会根据count，limit进行分页的计算
            ,skip: true
            ,curr: location.hash.replace('#!currentPage=', '') //获取起始页
            ,hash: 'currentPage' //自定义hash值
            ,jump: function(obj, first){
                if(!first){
                    //layer.msg('第 '+ obj.curr +' 页');
                    //loadDyna(pageSize,obj.curr)
                    //获取下一页数据
                    ajaxPost(ajaxUrl,ajaxData,obj.curr);
                }
            }
        });
    }


    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".top_toolbar_search_btn").on("click",function(){
        if($(".top_toolbar_search_val").val() != ''){

            var i = $(".top_toolbar_Select_val").val();
            var j = $(".top_toolbar_search_val").val();

            //ajaxData.reload();//缓存问题

            if (i == 1) {
                alert(1);
                ajaxData.idcard = j;
            } else if (i == 2) {
                alert(2);
                ajaxData.customerName = j;
            } else if (i == 3) {
                alert(3);
                ajaxData.mobile = j;
            }
            debugger;
            ajaxPost(ajaxUrl,ajaxData);
            tableIns.reload();

        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "userAdd.html",
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
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
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
