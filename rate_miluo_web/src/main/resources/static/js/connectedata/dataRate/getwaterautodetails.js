// $(function () {
//     // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
//     setTimeout(function () {
//         load();
//     }, 130)
// });
// window.close()  ; 关闭当前页面
// <a onClick="JavaScript:history.back(1);" href="#">返回</a>
$(function() {
    load();
});
function load() {
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/internet/waterAutoList",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        pageSize: 20, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        //queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        //$('#exampleTable').bootstrapTable('prepend', data);
        showHeader: true,//默认为true显示表头，设为false隐藏
        // showFooter:true,
        //singleSelect : false, // 设置为true将禁止多选
        //contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        //search : true, // 是否显示搜索框
        //showColumns : true, // 是否显示内容下拉框（选择显示的列）
        // clickToSelect:true,
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        // onClickRow: function (row) {
        //     aFormatter(row,index)
        // },
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return {
                pageSize: params.limit, // 每页要显示的数据条数
                pageNumber: (params.offset / params.limit) + 1,
                siteCode:$("#siteCode").val()
                // startDate: $("#startDate").val(),
                // endDate: $("#endDate").val()
                //offset: params.offset// 每页显示数据的开始行号
                //sort: params.sort// 要排序的字段
                //sortOrder: params.order, // 排序规则
            };
        },
        columns: [{
            title: '站点名称',
            field: 'name',
        }, {
            title: '时间',
            field: 'dateTime',
            width: '84',
        },{
            title: '设备位置',
            field: 'videoName',
        }, {
            title: '水温',
            field: 'sw',
        }, {
            title: 'PLC',
            field: 'plc',

        }, {
            title: '浊度',
            field: 'zd',

        }, {
            title: '电导率',
            field: 'ddl',
        }, {
            title: 'CODMN',
            field: 'codmn',

        }, {
            title: '总铅',
            field: 'pb',
        }, {
            title: '溶解氧',
            field: 'rjy',

        }, {
            title: 'PH值',
            field: 'ph',
        }, {
            title: '总砷',
            field: 'shen',

        }, {
            title: '氨氮',
            field: 'nh3N',

        }, {
            title: '综合毒性',
            field: 'comprehensiveToxicity',

        },
            {
                title: '总磷',
                field: 'tp',

            },{
                title: '总铬',
                field: 'chromium',

            }, {
                title: '水质等级',
                field: 'waterSort',

            },{
                title: 'K03',
                field: 'k03',

            },{
                title: 'K04',
                field: 'k04',

            }]
    });
}


