
function loadDyna(pageSize,currentPage)
{
    pageSize=10;
    if(currentPage==""){
        currentPage=1
    }else{
        currentPage=currentPage
    }
    $.ajax({
        type: "POST",
        url: "url地址",
        data: {"rerId":rerId,"pageinfo.pageSize":pageSize,"pageinfo.currentPage":currentPage},//和后台交互传递所需参数
        success: function(data){
            //console.log(currentPage);
            var data_list=data.releaseStatisticsList//列表内容
            var allpages=data.pageinfo.totalRecords
            reloadTable(data_list,pageSize,allpages,currentPage);//渲染表格并传递参数
        }

    });
}

loadDyna(); //运行定义函数

function reloadTable(data,pageSize,allpages,currentPage){
    layer.load(2);
    if(data!==""){
        layer.closeAll('loading'); //
        //var data = eval('('+data+')');
        var mydata= data;
        var datalength=mydata.length; //
        //console.log(datalength) exp_tableObj.reload({data:mydata,limit:datalength}); //
        exp_tableObj.reload({data:mydata,limit:datalength});
        pageslist(allpages,pageSize,currentPage)
    } else{
        layer.closeAll('loading');
        layer.msg("请稍后再试");
    }
}
//data 为表格数据，pageSize为每页显示数量，allpages为总条数，currentPage为当前选择页码

function pageslist(pages,pageSize,currentPage){
    laypage.render({
        elem: 'pagers'
        ,count: pages //后台返回分页条数的总和
        ,limit: pageSize //每页显示的数据的条数,layui会根据count，limit进行分页的计算
        ,skip: true
        ,curr: location.hash.replace('#!currentPage=', '') //获取起始页
        ,hash: 'currentPage' //自定义hash值
        ,jump: function(obj, first){
            if(!first){
                //layer.msg('第 '+ obj.curr +' 页');
                loadDyna(pageSize,obj.curr)

            }
        }
    });

}



function InitDataByPage(curr) {
    var pageSize = 5;
    $.getJSON("InitDataByPage", { "CFG_Table": cfg_table, "pageindex": curr, "pagesize": pageSize }, function (result) {
        //BindDataToTableByPage(result);//将获取到的数据动态加载到table
        var coun = result[0]._pagenum;//总数据条数
        var pagecount = coun % pageSize == 0 ? coun / pageSize : coun / pageSize + 1;//计算多少页
        laypage({
            cont: 'lay_page', //容器。值支持id名、原生dom对象，jquery对象。
            skin: '#fb771f',
            pages: pagecount, //通过后台拿到的总页数
            curr: curr, //初始化当前页
            groups: 10,//连续显示页数
            skip:true,//是否跳页
            first: '|<', //将首页显示为数字1,。若不显示，设置false即可
            last: '>|', //将尾页显示为总页数。若不显示，设置false即可
            prev: '<', //若不显示，设置false即可
            next: '>', //若不显示，设置false即可
            jump: function (obj, first) { //触发分页后的回调
                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                    var pageindex = obj.curr - 1;
                    $.getJSON("InitDataByPage", { "CFG_Table": cfg_table, "pageindex": pageindex, "pagesize": pageSize }, function (result) {
                        BindDataToTableByPage(result);
                    })
                }
            }
        });
    })
}
