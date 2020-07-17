var prefix2 = "/statement/pollute";
var timeStylePollute=0;
function changeTimePollute(){
    timeStylePollute = $("#timeTypePollute").val();
    switch(timeStylePollute){
        case '1':
            $("div .time-type1-pollute").removeClass("hidden");
            $("div .time-type2-pollute").addClass("hidden");
            $("div .time-type3-pollute").addClass("hidden");
            minutePollute();
            break;
        case '2':
            $("div .time-type1-pollute").addClass("hidden");
            $("div .time-type2-pollute").removeClass("hidden");
            $("div .time-type3-pollute").addClass("hidden");
            hourPollute();
            break;
        case '3':
            $("div .time-type1-pollute").addClass("hidden");
            $("div .time-type2-pollute").addClass("hidden");
            $("div .time-type3-pollute").removeClass("hidden");
            dailyPollute();
            break;
    }
}
$(function () {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#beginTime2',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: preHourPollute(new Date())
        });
        laydate.render({
            elem: '#endTime2',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: new Date()
        });
    });
});
// 前一小时
function preHourPollute(nowDate) {
    if (nowDate != null) {
        return new Date(nowDate.getTime()-60*60*1000);
    }
}

function minutePollute(){
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#beginTime2',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: preHourPollute(new Date())
        });
        laydate.render({
            elem: '#endTime2',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            value: new Date()
        });
    });

    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    setTimeout(function () {
        loadMinutePollute();
    }, 200)
}

function loadMinutePollute(siteId) {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteId){
        siteCodes = siteId;
    }
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    var dataType = $("#dataTypeDiv input[type='radio']:checked").val();

    let beginTime = $("#beginTime2").val();
    let endTime = $("#endTime2").val();
    // let message = getDateDiffMsg(beginTime, endTime);
    // if (message) {
    //     layer.alert(message);
    //     return;
    // }
    $('#exampleTable2').bootstrapTable("destroy");
    $('#exampleTable2').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix2 + "/minuteList",
        // showRefresh : true,
        // 		// showToggle : true,
        // 		// showColumns : true,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        pageSize : 50, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect : true, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        // search : true, // 是否显示搜索框
        // showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        sortable : true,
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                // sortName : params.sort,
                // sortOrder : params.order,
                dataType: dataType,
                beginTime : beginTime,
                endTime : endTime,
                siteCodes : siteCodes
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable2");
        },
        // onLoadSuccess: function(data){
        // 	var fieldList = ["siteName"];
        // 	mergeCells(data, "siteName", 1, $("#exampleTable2"), fieldList);//行合并
        // },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            valign : 'middle',
            width : 220
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            valign : 'middle',
            width : 220
        }, {
            field : 'recordingTime',
            title : '时间',
            align : 'center',
            valign : 'middle',
            width : 140
        }, {
            field : 'exhaust',
            title : '排放量(m³/s)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'dust',
            title : '烟尘(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'o2',
            title : '氧含量(%)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'so2',
            title : '二氧化硫(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'nox',
            title : '氮氧化物(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'flowVelocity',
            title : '烟气流速(m/s)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'temp',
            title : '温度(℃)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'press',
            title : '压力(kPa)',
            align : 'center',
            valign : 'middle'
        }]
    });
}

function hourPollute(){
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var date = new Date();
        var endDefault = new Date(date.getTime() - date.getMinutes() * 60 * 1000);
        var startDefault = new Date(endDefault.getTime() - 23 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime2h',
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
            elem: '#endTime2h',
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
        loadHourPollute();
    }, 200);
}

function loadHourPollute() {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    var dataType = $("#dataTypeDiv input[type='radio']:checked").val();

    let beginTime = $("#beginTime2h").val() + ":00";
    let endTime = $("#endTime2h").val() + ":00";
    $('#exampleTable2').bootstrapTable("destroy");
    $('#exampleTable2').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix2 + "/hourList",
        // showRefresh : true,
        // 		// showToggle : true,
        // 		// showColumns : true,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        pageSize : 50, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect : true, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        // search : true, // 是否显示搜索框
        // showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        sortable : true,
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                // sortName : params.sort,
                // sortOrder : params.order,
                dataType: dataType,
                beginTime : beginTime,
                endTime : endTime,
                siteCodes : siteCodes
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable2");
        },
        // onLoadSuccess: function(data){
        // 	var fieldList = ["siteName"];
        // 	mergeCells(data, "siteName", 1, $("#exampleTable2"), fieldList);//行合并
        // },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            valign : 'middle',
            width : 220
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            valign : 'middle',
            width : 220
        }, {
            field : 'recordingTime',
            title : '时间',
            align : 'center',
            valign : 'middle',
            width : 140
        }, {
            field : 'exhaust',
            title : '排放量(m³/s)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'dust',
            title : '烟尘(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'o2',
            title : '氧含量(%)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'so2',
            title : '二氧化硫(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'nox',
            title : '氮氧化物(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'flowVelocity',
            title : '烟气流速(m/s)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'temp',
            title : '温度(℃)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'press',
            title : '压力(kPa)',
            align : 'center',
            valign : 'middle'
        }]
    });
}

function dailyPollute() {
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var date = new Date();
        var startDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);
        var endDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime2d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: startDefault
        });

        var end = laydate.render({
            elem: '#endTime2d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: endDefault
        });
    });

    // setTimeout的用法是：放在script之后执行的，等待时间的格式加载完之后，执行里面的方法
    setTimeout(function () {
        loadDailyPollute();
    }, 200)
}

function loadDailyPollute() {
    var siteCodes = $.siteTree.siteCodes().join(",");
    if(siteCodes==null || siteCodes.length==0){
        layer.alert("请先选择站点！");
        return;
    }
    var dataType = $("#dataTypeDiv input[type='radio']:checked").val();

    let beginTime = $("#beginTime2d").val() + " :00:00:00";
    let endTime = $("#endTime2d").val() + " :00:00:00";
    $('#exampleTable2').bootstrapTable("destroy");
    $('#exampleTable2').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : prefix2 + "/dayList",
        // showRefresh : true,
        // 		// showToggle : true,
        // 		// showColumns : true,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        pageSize : 50, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect : true, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        // search : true, // 是否显示搜索框
        // showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        sortable : true,
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                // sortName : params.sort,
                // sortOrder : params.order,
                dataType: dataType,
                beginTime : beginTime,
                endTime : endTime,
                siteCodes : siteCodes
            };
        },
        onPostBody: function(){
            resizeTable("#exampleTable2");
        },
        // onLoadSuccess: function(data){
        // 	var fieldList = ["siteName"];
        // 	mergeCells(data, "siteName", 1, $("#exampleTable2"), fieldList);//行合并
        // },
        columns : [ {
            field : 'siteName',
            title : '站点',
            align : 'center',
            valign : 'middle',
            width : 220
        }, {
            field : 'equipmentName',
            title : '设备',
            align : 'center',
            valign : 'middle',
            width : 220
        }, {
            field : 'recordingTime',
            title : '时间',
            align : 'center',
            valign : 'middle',
            width : 140
        }, {
            field : 'exhaust',
            title : '排放量(m³/s)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'dust',
            title : '烟尘(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'o2',
            title : '氧含量(%)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'so2',
            title : '二氧化硫(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'nox',
            title : '氮氧化物(mg/m³)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'flowVelocity',
            title : '烟气流速(m/s)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'temp',
            title : '温度(℃)',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'press',
            title : '压力(kPa)',
            align : 'center',
            valign : 'middle'
        }]
    });
}

function query2() {
    timeStylePollute = $("#timeTypePollute").val();
    if(timeStylePollute == 1){
        loadMinutePollute();
    }
    if(timeStylePollute == 2){
        loadHourPollute();
    }
    if(timeStylePollute == 3){
        loadDailyPollute();
    }
}
//导出Excel
function exportExcel2() {
    layer.confirm('确定要导出当前记录？', {
        btn : [ '确定', '取消' ]
    }, function(index) {
        layer.close(index);
        var url = "";
        url += prefix + "/exportExcel?beginTime=" + $("#beginTime2").val()
            + "&endTime=" + $("#endTime2").val() + "&siteCodes="
            + $.siteTree.siteCodes().join(",");
        window.location.href = url
    })
}