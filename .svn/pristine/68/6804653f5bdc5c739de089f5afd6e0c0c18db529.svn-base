var prefix3 = "/statement/autoSite";
// 根据时间类型改变默认搜索时间
var timeStyleAuto=0;
function changeTimeAuto(){
    timeStyleAuto = $("#timeTypeAutoSite").val();
    switch(timeStyleAuto){
        case '1':
            $("div .time-type1-auto").removeClass("hidden");
            $("div .time-type2-auto").addClass("hidden");
            $("div .time-type3-auto").addClass("hidden");
            minuteAuto();
            break;
        case '2':
            $("div .time-type1-auto").addClass("hidden");
            $("div .time-type2-auto").removeClass("hidden");
            $("div .time-type3-auto").addClass("hidden");
            hourAuto();
            break;
        case '3':
            $("div .time-type1-auto").addClass("hidden");
            $("div .time-type2-auto").addClass("hidden");
            $("div .time-type3-auto").removeClass("hidden");
            dailyAuto();
            break;
    }
}
$(function () {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#beginTime3',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: preHourAuto(new Date())
        });
        laydate.render({
            elem: '#endTime3',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: new Date()
        });
    });
});
// 前一小时
function preHourAuto(nowDate) {
    if (nowDate != null) {
        return new Date(nowDate.getTime()-60*60*1000);
    }
}

function minuteAuto() {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#beginTime3',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: preHourAuto(new Date())
        });
        laydate.render({
            elem: '#endTime3',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: new Date()
        });
    });

    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    setTimeout(function () {
        loadMinuteAuto();
    }, 200)
}

function loadMinuteAuto(siteId) {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteId){
        siteCodes = siteId;
    }
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable3').bootstrapTable("destroy").bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix3 + "/minuteList",
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
                beginTime : $("#beginTime3").val(),
                endTime : $("#endTime3").val(),
                siteCodes : siteCodes
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable3");
        },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            width: 200,
            valign : 'middle'
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            width: 200,
            valign : 'middle'
        }, {
            field : 'dateTime',
            title : '时间',
            align : 'center',
            width: 200,
            valign : 'middle',
            sortable : true
        }, {
            field : 'sw',
            title : '水温(℃)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'ph',
            title : 'PH',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'zd',
            title : '浊度(NTU)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'ddl',
            title : '电导率(uS/cm)',
            align : 'center',
            valign : 'middle',
            width: 130,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'pb',
            title : '总铅(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'rjy',
            title : '溶解氧(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'shen',
            title : '总砷(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'nh3N',
            title : '氨氮(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'comprehensiveToxicity',
            title : '综合毒性',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'tp',
            title : '总磷(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'cd',
            title : '镉(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true
        }, {
            field : 'kmn',
            title : '高锰酸盐指数(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 160,
            sortable : true
        }, {
            field : 'temp',
            title : '室温(℃)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true
        }, {
            field : 'humi',
            title : '湿度(%)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true
        }, {
            field : 'yls',
            title : '叶绿素(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'llz',
            title : '蓝绿藻(Kcells/mL)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'waterSort',
            title : '水质等级',
            align : 'center',
            valign : 'middle',
            width: 100
        }]
    });
}

function hourAuto(){
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var date = new Date();
        // var endDefault = new Date(date.getTime() - date.getMinutes() * 60 * 1000);
        var startDefault = new Date(date.getTime() - 23 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime3h',
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
            elem: '#endTime3h',
            type: 'datetime',
            min: nowTime,
            format: 'yyyy-MM-dd HH:mm',
            isInitValue: true,
            value: date,
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
        loadHourAuto();
    }, 200);
}

function dailyAuto(){
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var date = new Date();
        var startDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);
        var endDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime3d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: startDefault,
            btns: ['confirm']
        });

        var end = laydate.render({
            elem: '#endTime3d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: endDefault
        });
    });

    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    setTimeout(function () {
        loadDailyAuto();
    }, 200)
}


function loadHourAuto(siteId) {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteId){
        siteCodes = siteId;
    }
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable3').bootstrapTable("destroy").bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix3 + "/hourList",
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
                beginTime : $("#beginTime3h").val(),
                endTime : $("#endTime3h").val(),
                siteCodes : siteCodes
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable3");
        },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            width: 200,
            valign : 'middle'
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            width: 200,
            valign : 'middle'
        }, {
            field : 'dateTime',
            title : '时间',
            align : 'center',
            width: 200,
            valign : 'middle',
            sortable : true
        }, {
            field : 'sw',
            title : '水温(℃)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'ph',
            title : 'PH',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'zd',
            title : '浊度(NTU)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'ddl',
            title : '电导率(uS/cm)',
            align : 'center',
            valign : 'middle',
            width: 130,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'pb',
            title : '总铅(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'rjy',
            title : '溶解氧(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'shen',
            title : '总砷(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'nh3N',
            title : '氨氮(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'comprehensiveToxicity',
            title : '综合毒性',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'tp',
            title : '总磷(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'cd',
            title : '镉(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true
        }, {
            field : 'kmn',
            title : '高锰酸盐指数(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 160,
            sortable : true
        }, {
            field : 'temp',
            title : '室温(℃)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true
        }, {
            field : 'humi',
            title : '湿度(%)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true
        }, {
            field : 'yls',
            title : '叶绿素(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'llz',
            title : '蓝绿藻(Kcells/mL)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'waterSort',
            title : '水质等级',
            align : 'center',
            valign : 'middle',
            width: 100
        }]
    });
}
function loadDailyAuto() {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable3').bootstrapTable("destroy").bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix3 + "/dailyList",
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
                beginTime : $("#beginTime3d").val(),
                endTime : $("#endTime3d").val(),
                siteCodes : $.siteTree.siteCodes().join(","),
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable3");
        },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            width: 200,
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
            align : 'center',
            width: 120,
            valign : 'middle',
            sortable : true
        }, {
            field : 'sw',
            title : '水温(℃)',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'ph',
            title : 'PH',
            align : 'center',
            valign : 'middle',
            width: 100,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'zd',
            title : '浊度(NTU)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'ddl',
            title : '电导率(uS/cm)',
            align : 'center',
            valign : 'middle',
            width: 130,
            sortable : true,
            formatter: function(value, row, index){
                if(value != null && value != ""){
                    return value.toFixed(1);
                }else{
                    return "-";
                }
            }
        }, {
            field : 'pb',
            title : '总铅(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'rjy',
            title : '溶解氧(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'shen',
            title : '总砷(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'nh3N',
            title : '氨氮(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'comprehensiveToxicity',
            title : '综合毒性',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'tp',
            title : '总磷(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'chromium',
            title : '总铬(mg/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'yls',
            title : '叶绿素(ug/L)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'llz',
            title : '蓝绿藻(Kcells/mL)',
            align : 'center',
            valign : 'middle',
            width: 120,
            sortable : true
        }, {
            field : 'waterSort',
            title : '水质等级',
            align : 'center',
            valign : 'middle',
            width: 100
        }]
    });
}


function query3() {
    timeStyleAuto = $("#timeTypeAutoSite").val();
    if (timeStyleAuto == 1) {
        loadMinuteAuto();
    }
    if(timeStyleAuto == 2){
        loadHourAuto();
    }
    if(timeStyleAuto == 3){
        loadDailyAuto();
    }
}
//导出Excel
function exportExcel3() {
    layer.confirm('确定要导出当前记录？', {
        btn : [ '确定', '取消' ]
    }, function(index) {
        layer.close(index);
        var startTime;
        var endTime;
        var tp =$("#timeTypeAutoSite").val();
        if (tp==1){
            startTime = $("#beginTime3").val();
            endTime = $("#endTime3").val();
        }else if (tp == 2) {
            startTime = $("#beginTime3h").val();
            endTime = $("#endTime3h").val();
        } else {
            startTime = $("#beginTime3d").val();
            endTime = $("#endTime3d").val();
        }
        var url = "";
        url += prefix3 + "/exportExcel?beginTime=" + startTime
            + "&endTime=" + endTime + "&siteCodes="
            + $.siteTree.siteCodes().join(",")
            +"&sort="+$("#timeTypeAutoSite").val();
        window.location.href = url
    })
}
