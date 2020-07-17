var index=0;
let roleId = $("#roleId").val();
$(function() {
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var max = null;
        var endDefault = new Date();
        var startDefault = new Date(endDefault - 24 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime',
            type: 'datetime',
            max: nowTime,
            format: 'yyyy-MM-dd HH:mm',
            isInitValue: true,
            value: startDefault,
            btns: ['clear', 'confirm'],
            done: function (value, date) {
                endMax = end.config.max;
                end.config.min = date;
                end.config.min.month = date.month - 1;
            }
        });

        var end = laydate.render({
            elem: '#endTime',
            type: 'datetime',
            min: nowTime,
            format: 'yyyy-MM-dd HH:mm',
            isInitValue: true,
            value: endDefault,
            done: function (value, date) {
                if ($.trim(value) == '') {
                    var curDate = new Date();
                    date = {
                        'date': curDate.getDate(),
                        'month': curDate.getMonth() + 1,
                        'year': curDate.getFullYear()
                    };
                }
                start.config.max = date;
                start.config.max.month = date.month - 1;
            }
        });
    });

});

function a() {
    index = 0;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable(
        {
            method : 'get', // 服务器数据的请求方式 get or post
            url :"/offline/data/airList",
            // showRefresh : true,
            // showToggle : true,
            showColumns : true,
            iconSize : 'outline',
            toolbar : '#exampleToolbar',
            striped : true, // 设置为true会有隔行变色效果
            dataType : "json", // 服务器返回的数据类型
            pagination : true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect : false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageSize : 20, // 如果设置了分页，每页数据条数
            pageNumber : 1, // 如果设置了分布，首页页码
            // search : true, // 是否显示搜索框
            showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"
            queryParams : function(params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    // pageSize :    params.limit,
                    // pageNumber : (params.offset / params.limit) + 1,
                    siteCodes :   $.siteTree.siteCodes,
                    beginTime : $("#beginTime").val() + ":00",
                    endTime :   $("#endTime").val() + ":00",
                };
            },
            columns :[{
                title : '状态',
                field : 'status',
                formatter: function(data, row, index){
                    if (data == 0) {
                        return '在线';
                    } else if (data == 1) {
                        return '离线';
                    } else {
                        return '-';
                    }
                }
            },{
                title : '站点名称',
                field : 'name',
            }, {
                title : '离线次数',
                field : 'offCount',
            }, {
                title : '总离线时间',
                field : 'offTotalTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }, {
                title : '最大离线时长',
                field : 'offMaxTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }]
        });
}

function b() {
    index = 2;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable(
        {
            method : 'get', // 服务器数据的请求方式 get or post
            url :"/offline/data/polluteList",
            // showRefresh : true,
            // showToggle : true,
            showColumns : true,
            iconSize : 'outline',
            toolbar : '#exampleToolbar',
            striped : true, // 设置为true会有隔行变色效果
            dataType : "json", // 服务器返回的数据类型
            pagination : true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect : false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageSize : 20, // 如果设置了分页，每页数据条数
            pageNumber : 1, // 如果设置了分布，首页页码
            // search : true, // 是否显示搜索框
            showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"
            queryParams : function(params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    // pageSize :    params.limit,
                    // pageNumber : (params.offset / params.limit) + 1,
                    siteCodes :   $.siteTree.siteCodes,
                    beginTime : $("#beginTime").val() + ":00",
                    endTime :   $("#endTime").val() + ":00",
                };
            },
            columns :[{
                title : '状态',
                field : 'status',
                formatter: function(data, row, index){
                    if (data == 0) {
                        return '在线';
                    } else if (data == 1) {
                        return '离线';
                    } else {
                        return '-';
                    }
                }
            },{
                title : '站点名称',
                field : 'name',
            }, {
                title : '离线次数',
                field : 'offCount',
            }, {
                title : '总离线时间',
                field : 'offTotalTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }, {
                title : '最大离线时长',
                field : 'offMaxTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }]
        });
}

function c() {
    index = 3;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable(
        {
            method : 'get', // 服务器数据的请求方式 get or post
            url :"/offline/data/waterPlantList",
            // showRefresh : true,
            // showToggle : true,
            showColumns : true,
            iconSize : 'outline',
            toolbar : '#exampleToolbar',
            striped : true, // 设置为true会有隔行变色效果
            dataType : "json", // 服务器返回的数据类型
            pagination : true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect : false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageSize : 20, // 如果设置了分页，每页数据条数
            pageNumber : 1, // 如果设置了分布，首页页码
            // search : true, // 是否显示搜索框
            showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"
            queryParams : function(params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    // pageSize :    params.limit,
                    // pageNumber : (params.offset / params.limit) + 1,
                    siteCodes :   $.siteTree.siteCodes,
                    beginTime : $("#beginTime").val() + ":00",
                    endTime :   $("#endTime").val() + ":00",
                };
            },
            columns :[{
                title : '状态',
                field : 'status',
                formatter: function(data, row, index){
                    if (data == 0) {
                        return '在线';
                    } else if (data == 1) {
                        return '离线';
                    } else {
                        return '-';
                    }
                }
            },{
                title : '站点名称',
                field : 'name',
            }, {
                title : '设备名称',
                field : 'videoName',
            }, {
                title : '离线次数',
                field : 'offCount',
            }, {
                title : '总离线时间',
                field : 'offTotalTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }, {
                title : '最大离线时长',
                field : 'offMaxTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }]
        });
}

function d() {
    index = 4;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable(
        {
            method : 'get', // 服务器数据的请求方式 get or post
            url :"/offline/data/waterAuto",
            // showRefresh : true,
            // showToggle : true,
            showColumns : true,
            iconSize : 'outline',
            toolbar : '#exampleToolbar',
            striped : true, // 设置为true会有隔行变色效果
            dataType : "json", // 服务器返回的数据类型
            pagination : true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect : false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageSize : 20, // 如果设置了分页，每页数据条数
            pageNumber : 1, // 如果设置了分布，首页页码
            // search : true, // 是否显示搜索框
            showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"
            queryParams : function(params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    // pageSize :    params.limit,
                    // pageNumber : (params.offset / params.limit) + 1,
                    siteCodes :   $.siteTree.siteCodes,
                    beginTime : $("#beginTime").val() + ":00",
                    endTime :   $("#endTime").val() + ":00",
                };
            },
            columns :[{
                title : '状态',
                field : 'status',
                formatter: function(data, row, index){
                    if (data == 0) {
                        return '在线';
                    } else if (data == 1) {
                        return '离线';
                    } else {
                        return '-';
                    }
                }
            },{
                title : '站点名称',
                field : 'name',
            },{
                title : '离线次数',
                field : 'offCount',
            }, {
                title : '总离线时间',
                field : 'offTotalTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }, {
                title : '最大离线时长',
                field : 'offMaxTime',
                formatter: function (data, row, index) {
                    return formatTimeCeil(data);
                }
            }]
        });
}

//查询
function query1() {
    if(roleId == 81){
        a();
    }
    if(roleId == 79){
        b();
    }
    if(roleId == 82){
        c();
    }
    if(roleId == 78){
        d();
    }
}


function formatTimeCeil(data) {
    let str = null;
    if (data != null) {
        if (data == 0) {
            str = 0;
        } else if (data < 60000) {
            str = parseInt(data / 1000) + "秒";
        } else if (data <= 3600000) {
            let minute = parseInt(data / 60000);
            str = minute + "分钟";
        } else {
            let hour = parseInt(data / 3600000);
            let minute = parseInt((data - hour * 3600000) / 60000);
            str = hour + "小时" + (minute==0?'':(' '+minute) + "分钟");
        }
    }
    return str;
}
