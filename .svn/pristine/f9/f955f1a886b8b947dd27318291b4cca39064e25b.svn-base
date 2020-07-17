$(function () {
    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    // setTimeout(function () {
    //     load();
    // }, 200)
});
// var warnNum;

function load(roleId) {
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/index/polluteFactory",
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
        // showFooter: true,//这个会显示在底部多一行空白
        //singleSelect : false, // 设置为true将禁止多选
        //contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        //search : true, // 是否显示搜索框
        //showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            var ids = $.siteTree.siteCodes();
            ids = ids.length===0?undefined:ids.join();
            if(ids==null || ids.length==0){
                layer.alert("请先选择站点！");
                return;
            }
            return {
                pageSize: params.limit, // 每页要显示的数据条数
                pageNumber: (params.offset / params.limit) + 1,
                siteIds: ids,
                startDate: $("#startDate").val(),
                endDate: $("#endDate").val(),
                status : $("#status").val(),
            };
        },
        onPostBody: function(){
            if(roleId != 1 && roleId != 77){
                $("#exampleTable tbody tr").find('td:eq(8) a:lt(3)').removeAttrs('onclick');
                $("#exampleTable tbody tr").find('td:eq(8) a:lt(3)').click(function () {
                   layer.msg("权限不足，请勿重复操作！");
                });
            }
        },
        columns: [{
            title: '站点',
            field: 'siteName',
            align: 'center'
        }, {
            title: '报警因子',
            field: 'pollutant',
            align: 'center'
        }, {
            title: '报警时间',
            align: 'center',
            field: 'dataTime'
        }, {
            title: '检测值',
            align: 'center',
            field: 'value', formatter: function (data, row, index) {
                return '<span style="color:#FF0000">'+data + '</span>'
            }
        }, {
            title: '指标值',
            align: 'center',
            field: 'indexValue',
        }, {
            title: '报警类型',
            align: 'center',
            field: 'text',
            formatter: function (value, row, index) {
                if (row.seriousExceed === 1) {
                    return '<span style="color: #ff0000;">严重超标</span>'
                } else {
                    return value;
                }
            }
        }, {
            title: '报警等级',
            align: 'center',
            field: 'degreeText',
        }, {
            title: '状态',
            width : '120px',
            field: 'status',
            align: 'center',
            formatter : function(value, row, index) {
                if(value==0){
                    return '<span style="color: #ff0000;">未处理</span>'
                }else  if(value==1){
                    return  "已处理"
                }else if(value==2){
                    return "任务已添加"
                }
            }
        },{
            title : '操作',
            field : 'id',
            width : '192px',
            align : 'center',
            formatter : function(value, row, index) {
                var e = '<a class="btn btn-primary btn-sm" style="width: 59px;\n' +
                    '        padding-left: 0px;\n' +
                    '        margin-left: -4px;" href="#" mce_href="#" title="" onclick="add(\''
                    + row.id+ '\')"><i class="fa fa-plus">添加任务</i></a> ';
                var d = '<a class="btn btn-warning btn-sm" href="#" title=""  mce_href="#" onclick="updateIgnore(\''
                    + row.id
                    + '\')"><i class="fa fa-remove"></i>忽略</a> ';
                var c = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="" onclick="updateReturn(\''
                    + row.id
                    + '\')"><i class="fa fa-plus">恢复</i></a> ';
                return e + d + c;
            }
        }, {
            title: '查看',
            field: 'id',
            width : '120px',
            align : 'center',
            formatter : function(value, row, index) {
                var  s= row.vId;

                    var d = '<a class="btn btn-success btn-sm" style="    width: 58px;\n' +
                        '    padding-left: 3px;" href="#" title=""  mce_href="#" onclick="lookVideo(\''
                        + row.siteId+","+row.dataTime
                        + '\')"><i class="glyphicon glyphicon-zoom-in">监控</i></a> ';
                    return d;

            }

        }]
    });
}

function lookVideo(id) {
    var page = layer.open({
        type : 2,
        title : '监控视频查看',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '100%', '100%' ],
        content : "/index/openVideoPage?id=" + id // iframe的url
    });


}

function updateReturn(id) {
     layer.confirm('确定要把状态改为未处理吗？', {
         btn: ['确定', '取消']
     }, function () {
    $.ajax({
        url: "/index/polluteDataUpdateReturn",
        type: "post",
        data: {
            'id': id
        },
        beforeSend: function (request) {
            // index = layer.load();
        },
        success: function (r) {
            if (r.code == 0) {
                // layer.close(index);
                layer.msg(r.msg);
                reload();
            } else {
                layer.msg("操作失败");
            }

        }
    });
     })
}
function updateIgnore(id) {
     layer.confirm('确定要把状态改为已处理吗？', {
         btn: ['确定', '取消']
     }, function () {
    $.ajax({
        url: "/index/polluteDataUpdateIgnore",
        type: "post",
        data: {
            'id': id
        },
        beforeSend: function (request) {
            // index = layer.load();
        },
        success: function (r) {
            if (r.code == 0) {
                // layer.close(index);
                layer.msg(r.msg);
                reload();
            } else {
                layer.msg("操作失败");
            }
        }
    });
    })
}

function add(id) {
    layer.open({
        type : 2,
        title : '代办任务查看',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '480px' ],
        content : '/index/polluteDataAdd?id=' + id
    });
}
function reload() {
    $('#exampleTable').bootstrapTable('refresh', {});
}

//导出Excel
function exportExcel() {
    layer.confirm('确定要导出当前记录？', {
        btn : [ '确定', '取消' ]
    }, function(index) {
        layer.close(index);
        var url = "";
        url += "/index/exportExcel?type=pollute&startDate=" + $("#startDate").val()
            + "&endDate=" + $("#endDate").val()
            + "&siteIds=" + $.siteTree.siteCodes().join(",")
            +"&status="+$("#status").val();
        window.location.href = url
    });
}