var prefix4 = "/statement/waterPlant";
// 根据时间类型改变默认搜索时间
var timeStylePlant=0;
function changeTimePlant(){
    timeStylePlant = $("#timeTypePlant").val();
    switch(timeStylePlant){
        case '1':
            $("div .time-type1-plant").removeClass("hidden");
            $("div .time-type2-plant").addClass("hidden");
            $("div .time-type3-plant").addClass("hidden");
            minutePlant();
            break;
        case '2':
            $("div .time-type1-plant").addClass("hidden");
            $("div .time-type2-plant").removeClass("hidden");
            $("div .time-type3-plant").addClass("hidden");
            hourPlant();
            break;
        case '3':
            $("div .time-type1-plant").addClass("hidden");
            $("div .time-type2-plant").addClass("hidden");
            $("div .time-type3-plant").removeClass("hidden");
            dailyPlant();
            break;
    }
}
$(function () {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var endMax = nowTime;
        var nowDate = new Date();
        var beginTime = new Date(nowDate.getTime()-3600*1000);
        var endTime = nowDate;
        laydate.render({
            elem: '#beginTime4',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: beginTime,
            max: nowTime
        });
        laydate.render({
            elem: '#endTime4',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: endTime,
            max: endMax
        });
    });
});
//实时数据时间输入框格式化
function minutePlant(){
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var endMax = nowTime;
        var nowDate = new Date();
        var beginTime = new Date(nowDate.getTime()-3600*1000);
        var endTime = nowDate;
        laydate.render({
            elem: '#beginTime4',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: beginTime,
            max: nowTime
        });
        laydate.render({
            elem: '#endTime4',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: endTime,
            max: endMax
        });
    });

    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    setTimeout(function () {
        loadMinutePlant();
    }, 200);
}
//小时数据时间输入框格式化
function hourPlant(){
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var date = new Date();
        var endDefault = new Date(date.getTime() - date.getMinutes() * 60 * 1000);
        var startDefault = new Date(endDefault.getTime() - 23 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime4h',
            type: 'datetime',
            max: nowTime,
            format: 'yyyy-MM-dd HH:mm',
            isInitValue: true,
            value: startDefault,
            btns: ['clear', 'now', 'confirm'],
            done: function (value, date) {
                endMax = end.config.max;
                end.config.min = date;
                end.config.min.month = date.month - 1;
            }
        });

        var end = laydate.render({
            elem: '#endTime4h',
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

    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    setTimeout(function () {
        loadHourPlant();
    }, 200);
}
//日数据时间输入框格式化
function dailyPlant(){
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var date = new Date();
        var startDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);
        var endDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime4d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: startDefault
        });

        var end = laydate.render({
            elem: '#endTime4d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: endDefault
        });
    });

    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    setTimeout(function () {
        loadDailyPlant();
    }, 200);
}
//实时数据
function loadMinutePlant(siteId) {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteId){
        siteCodes = siteId;
    }
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    var beginTime = new Date($("#beginTime4").val()).getTime();
    var endTime = new Date($("#endTime4").val()).getTime();
    if((endTime-beginTime) > 24*3600*1000){
        layer.open({
            type:0,
            icon:2,
            title: '提示窗',
            content: '请查询一天内的数据',
        });
        return;
    }
    $('#exampleTable4').bootstrapTable("destroy").bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix4 + "/minuteList",
        // showRefresh : true,
        // showToggle : true,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        // showHeader : false, 不显示表头
        pageSize : 50, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        // pageList : 'All',//每页固定50条，不能修改
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect : false, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        // search : true, // 是否显示搜索框
        showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        sortable: true,
        queryParams : function(params) {
            return {
//				pageSize : params.limit,
//				pageNumber : (params.offset / params.limit) + 1,
                sortName : params.sort, // 排序列名
                sortOrder : params.order, // 排序命令（desc，asc）
                beginTime : $("#beginTime4").val(),
                endTime : $("#endTime4").val(),
                siteCodes : siteCodes,
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable4");
        },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            width: 160,
            valign : 'middle'
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            width: 200,
            valign : 'middle'
        }, {
            field : 'recordingTime',
            title : '时间',
            width : 180,
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'bo1',
            title : '排放量(L/s)',
            width : 120,
            titleTooltip : '排放量值',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'ph',
            title : 'PH',
            width : 100,
            align : 'center',
            valign : 'middle',
            sortable : true,
            formatter: function (value,row,index) {
                if(value != null && value != ""){
                    return parseFloat(value).toFixed(1);
                } else {
                    return "-"
                }
            }
        }, {
            field : 'pb',
            title : 'Pb(mg/L)',
            width : 120,
            titleTooltip: '铅',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cd',
            title : 'Cd(ug/L)',
            width : 120,
            titleTooltip: '镉',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'shen',
            title : 'As(mg/L)',
            width : 120,
            titleTooltip: '砷',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'zn',
            title : 'Zn(mg/L)',
            width : 120,
            titleTooltip: '锌',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cu',
            title : 'Cu(mg/L)',
            width : 120,
            titleTooltip: '铜',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'tp',
            title : 'TP(mg/L)',
            width : 120,
            titleTooltip: '总磷',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'tn',
            title : 'TN(mg/L)',
            width : 120,
            titleTooltip: '总氮',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cod',
            title : 'COD(mg/L)',
            width : 120,
            titleTooltip: 'COD',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'nh3',
            title : 'NH3(mg/L)',
            width : 120,
            titleTooltip: '氨氮',
            align : 'center',
            valign : 'middle',
            sortable : true
        }]
    });
}
//小时数据
function loadHourPlant() {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable4').bootstrapTable("destroy").bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix4 + "/hourList",
        // showRefresh : true,
        // showToggle : true,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        // showHeader : false, 不显示表头
        pageSize : 50, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        // pageList : 'All',//每页固定50条，不能修改
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect : false, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        // search : true, // 是否显示搜索框
        showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        sortable: true,
        queryParams : function(params) {
            return {
//				pageSize : params.limit,
//				pageNumber : (params.offset / params.limit) + 1,
                sortName : params.sort, // 排序列名
                sortOrder : params.order, // 排序命令（desc，asc）
                beginTime : $("#beginTime4h").val(),
                endTime : $("#endTime4h").val(),
                siteCodes : $.siteTree.siteCodes().join(","),
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable4");
        },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            width: 160,
            valign : 'middle'
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            width: 200,
            valign : 'middle'
        }, {
            field : 'hourTime',
            title : '时间',
            width : 180,
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'bo1',
            title : '排放量(L/s)',
            width : 120,
            titleTooltip : '排放量值',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'ph',
            title : 'PH',
            width : 100,
            align : 'center',
            valign : 'middle',
            sortable : true,
            formatter: function (value,row,index) {
                if(value != null && value != ""){
                    return parseFloat(value).toFixed(1);
                } else {
                    return "-"
                }
            }
        }, {
            field : 'pb',
            title : 'Pb(mg/L)',
            width : 120,
            titleTooltip: '铅',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cd',
            title : 'Cd(ug/L)',
            width : 120,
            titleTooltip: '镉',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'shen',
            title : 'As(mg/L)',
            width : 120,
            titleTooltip: '砷',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'zn',
            title : 'Zn(mg/L)',
            width : 120,
            titleTooltip: '锌',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cu',
            title : 'Cu(mg/L)',
            width : 120,
            titleTooltip: '铜',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'tp',
            title : 'TP(mg/L)',
            width : 120,
            titleTooltip: '总磷',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'tn',
            title : 'TN(mg/L)',
            width : 120,
            titleTooltip: '总氮',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cod',
            title : 'COD(mg/L)',
            width : 120,
            titleTooltip: 'COD',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'nh3',
            title : 'NH3(mg/L)',
            width : 120,
            titleTooltip: '氨氮',
            align : 'center',
            valign : 'middle',
            sortable : true
        }]
    });
}
//日数据
function loadDailyPlant() {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable4').bootstrapTable("destroy").bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix4 + "/dailyList",
        // showRefresh : true,
        // showToggle : true,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        // showHeader : false, 不显示表头
        pageSize : 50, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        // pageList : 'All',//每页固定50条，不能修改
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect : false, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        // search : true, // 是否显示搜索框
        showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        sortable: true,
        queryParams : function(params) {
            return {
//				pageSize : params.limit,
//				pageNumber : (params.offset / params.limit) + 1,
                sortName : params.sort, // 排序列名
                sortOrder : params.order, // 排序命令（desc，asc）
                beginTime : $("#beginTime4d").val(),
                endTime : $("#endTime4d").val(),
                siteCodes : $.siteTree.siteCodes().join(","),
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable4");
        },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            width: 160,
            valign : 'middle'
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            width: 200,
            valign : 'middle'
        }, {
            field : 'queryTime',
            title : '时间',
            width : 180,
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'bo1',
            title : '排放量(L/s)',
            width : 120,
            titleTooltip : '排放量值',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'ph',
            title : 'PH',
            width : 100,
            align : 'center',
            valign : 'middle',
            sortable : true,
            formatter: function (value,row,index) {
                if(value != null && value != ""){
                    return parseFloat(value).toFixed(1);
                } else {
                    return "-"
                }
            }
        }, {
            field : 'pb',
            title : 'Pb(mg/L)',
            width : 120,
            titleTooltip: '铅',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cd',
            title : 'Cd(ug/L)',
            width : 120,
            titleTooltip: '镉',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'shen',
            title : 'As(mg/L)',
            width : 120,
            titleTooltip: '砷',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'zn',
            title : 'Zn(mg/L)',
            width : 120,
            titleTooltip: '锌',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cu',
            title : 'Cu(mg/L)',
            width : 120,
            titleTooltip: '铜',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'tp',
            title : 'TP(mg/L)',
            width : 120,
            titleTooltip: '总磷',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'tn',
            title : 'TN(mg/L)',
            width : 120,
            titleTooltip: '总氮',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'cod',
            title : 'COD(mg/L)',
            width : 120,
            titleTooltip: 'COD',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'nh3',
            title : 'NH3(mg/L)',
            width : 120,
            titleTooltip: '氨氮',
            align : 'center',
            valign : 'middle',
            sortable : true
        }]
    });
}

function query4() {
    timeStylePlant = $("#timeTypePlant").val();
    if(timeStylePlant == 1){
        loadMinutePlant();
    }
    if(timeStylePlant == 2){
        loadHourPlant();
    }
    if(timeStylePlant == 3){
        loadDailyPlant();
    }
}
//导出Excel
function exportExcel4() {
    layer.confirm('确定要导出当前记录？', {
        btn : [ '确定', '取消' ]
    }, function(index) {
        layer.close(index);
        var startTime;
        var endTime;
        var tp =$("#timeTypeAutoSite").val();
        if (tp==1){
            startTime = $("#beginTime4").val();
            endTime = $("#endTime4").val();
        }else if (tp == 2) {
            startTime = $("#beginTime4h").val();
            endTime = $("#endTime4h").val();
        } else {
            startTime = $("#beginTime4d").val();
            endTime = $("#endTime4d").val();
        }
        var url = "";
        url += prefix4 + "/exportExcel?beginTime=" + startTime
            + "&endTime=" + endTime + "&siteCodes="
            + $.siteTree.siteCodes().join(",")
            +"&sort="+$("#timeTypePlant").val();
        window.location.href = url
    })
}
