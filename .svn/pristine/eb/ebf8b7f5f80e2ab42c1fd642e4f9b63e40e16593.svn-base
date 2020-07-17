'use strict';

var prefix1 = "/statement/checkConcentration";
/*var siteCodes = [];
$.siteTree.treeClickEvent = function (e, data) {
	siteCodes = $.siteTree.siteCodes();
	siteCodes = siteCodes.join(",");
}*/
var tabInitFlag = {
	    1: true,
	    2: false,
	    3: false,
	    4: false
	};
//用于标记每个tab页中的时间下拉框
var timeStyleAir=0;
var timeStylePollute=0;
var timeStyleAuto=0;
var timeStylePlant=0;

$(function() {
	layui.use('laydate', function () {
	    var laydate = layui.laydate;
	    var nowTime = new Date().valueOf();
	    var nowDate = new Date();
	    var beginTime = new Date(nowDate.getTime()-24*3600*1000);
	    var endTime = nowDate;
	    laydate.render({
	        elem: '#beginTime1h',
	        type: 'datetime',
	        format: 'yyyy-MM-dd HH:mm',
	        value: beginTime,
	        btns: ['confirm'],
	        max: nowTime
	    });
	    laydate.render({
	        elem: '#endTime1h',
	        type: 'datetime',
	        format: 'yyyy-MM-dd HH:mm',
	        value: endTime,
	        max: nowTime
	    });
	});
});

//tab页改变执行函数
function changeTabContent(id){
	timeStyleAir = $("#timeTypeAir").val();
	timeStylePollute = $("#timeTypePollute").val();
	timeStyleAuto = $("#timeTypeAutoSite").val();
	timeStylePlant = $("#timeTypePlant").val();
    switch (id) {
        case '1':
        	if(!tabInitFlag[parseInt(id)]){
        		tabInitFlag[parseInt(id)] = true;
        		if(timeStyleAir == 1){
        			a();
        		}else if(timeStyleAir == 2){
        			b();
        		}else if(timeStyleAir == 3){
        			c();
        		}else if(timeStyleAir == 4){
        			d();
        		}
        	}
            $.siteTree.hideTab(["-1","-3","-4"]);
            $.siteTree.showTab("-2");
            break;
        case '2':
        	if(!tabInitFlag[parseInt(id)]){
        		tabInitFlag[parseInt(id)] = true;
                if(timeStylePollute == 1){
                    minutePollute();
                }else if(timeStylePollute == 2){
                    hourPollute();
                }else if(timeStylePollute == 3){
                    dailyPollute();
                }
        	}
            $.siteTree.hideTab(["-1","-2","-4"]);
            $.siteTree.showTab("-3");
            break;
        case '3':
        	if(!tabInitFlag[parseInt(id)]){
        		tabInitFlag[parseInt(id)] = true;
        		if(timeStyleAuto == 1){
        			minuteAuto();
        		}else if(timeStyleAuto == 2){
        			hourAuto();
        		}else if (timeStyleAuto == 3){
        		    dailyAuto();
                }
        	}
            $.siteTree.hideTab(["-2","-3","-4"]);
            $.siteTree.showTab("-1");
            break;
        case '4':
            if(!tabInitFlag[parseInt(id)]){
            	tabInitFlag[parseInt(id)] = true;
            	if(timeStylePlant == 1){
            		minutePlant();
        		}else if(timeStylePlant == 2){
        			hourPlant();
        		}else if(timeStylePlant == 3){
        			dailyPlant();
        		}
            }
            $.siteTree.hideTab(["-1","-2","-3"]);
            $.siteTree.showTab("-4");
            break;
    }
}
// 根据时间类型改变默认搜索时间
function changeTimeAir(){
	timeStyleAir = $("#timeTypeAir").val();
	switch(timeStyleAir){
	case '2':
		$("div.time-type2-air").removeClass("hidden");
		$("div.time-type3-air").addClass("hidden");
		hourTime();
		break;
	case '3':
		$("div.time-type2-air").addClass("hidden");
		$("div.time-type3-air").removeClass("hidden");
		dailyTime();
		break;
	}
}

//小时值
function hourTime() {
	layui.use('laydate', function () {
	    var laydate = layui.laydate;
	    var nowTime = new Date().valueOf();
	    var nowDate = new Date();
	    var beginTime = new Date(nowDate.getTime()-24*3600*1000);
	    var endTime = nowDate;
	    laydate.render({
	        elem: '#beginTime1h',
	        type: 'datetime',
	        format: 'yyyy-MM-dd HH:mm',
	        value: beginTime,
	        max: nowTime
	    });
	    laydate.render({
	        elem: '#endTime1h',
	        type: 'datetime',
	        format: 'yyyy-MM-dd HH:mm',
	        value: endTime,
	        max: nowTime
	    });
	});
	setTimeout(function () {
        b();
    }, 200)
}

function b(siteId) {
	var siteCodes = $.siteTree.siteCodes().join(",");
	if(siteId){
		siteCodes = siteId;
	}
	if(siteCodes==null || siteCodes.length==0){
	    layer.alert("请先选择站点！");
	    return;
    }
    $('#exampleTable1').bootstrapTable('destroy');
    $('#exampleTable1')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix1 + "/hourList",
                // showRefresh : true,
                // showToggle : true,
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
                pageSize : 50, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框(选择显示的列)
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                sortable : true,
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        /*pageSize : params.limit,
                        pageNumber : (params.offset / params.limit) + 1,*/
                    	//sortName,sortOrder 和上面两行不能同时出现
                    	siteCodes : siteCodes,
                        sortName : params.sort,
                        sortOrder : params.order,
                        beginTime : $("#beginTime1h").val(),
                        endTime : $("#endTime1h").val(),
                    };
                },
                onPostBody: function(){
                	resizeTable("#exampleTable1");
                },
                columns : [ {
                    field : 'siteName',
                    title : '站点',
                    align : "center",
                    width : '200',
                    valign: 'middle'
                }, {
                    field : 'queryTime',
                    title : '时间',
                    align : "center",
                    valign: 'middle',
                    width : '130',
                    sortable : true

                },{
                	field : 'pm25',
                    title : 'PM<sub>2.5</sub>(ug/m³)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'pm10',
                    title : 'PM<sub>10</sub>(ug/m³)',
                    align : "center",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'o3OneHour',
                    title : 'O<sub>3</sub>(ug/m³)',
                    align : "center",
                    width : '140',
                    titleTooltip : "臭氧(小时)",
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'no2',
                    title : 'NO<sub>2</sub>(ug/m³)',
                    align : "center",
                    width : '140',
                    titleTooltip : "二氧化氮",
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'so2',
                    title : 'SO<sub>2</sub>(ug/m³)',
                    align : "center",
                    titleTooltip : "二氧化硫",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'co',
                    title : 'CO(mg/m³)',
                    align : "center",
                    width : '140',
                    titleTooltip : "一氧化碳",
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return value.toFixed(1);
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'aqi',
                    title : 'AQI(ug/m³)',
                    align : "center",
                    width : '140',
                    titleTooltip : "空气污染指数",
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'primaryEp',
                    title : '首要污染物',
                    align : "center",
                    width : '90'
                },{
                	field : 'aqType',
                    title : '空气质量',
                    align : "center",
                    width : '80'
                },{
                	field : 'aqDegree',
                    title : '等级',
                    align : "center",
                    width : '60'
                },{
                	field : 'tsp',
                	title : 'TSP(ng/m³)',
                	align : 'center',
                	titleTooltip : "总悬浮颗粒物",
                	width : '150',
                    sortable : true,
                	formatter: function (value,row,index){
                		if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                	}
                },{
                	field : 'noise',
                    title : '噪声(dB)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return value.toFixed(1);
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'temp',
                    title : '温度(℃)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                        if(value != null && value != ""){
                            return Math.round(parseFloat(value))
                        } else {
                            return "-"
                        }
                    }
                },{
                	field : 'humi',
                    title : '湿度(%)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                        if(value != null && value != ""){
                            return value.toFixed(1);
                        } else {
                            return "-"
                        }
                    }
                },{
                	field : 'press',
                    title : '气压(HPa)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                        if(value != null && value != ""){
                            return Math.round(parseFloat(value))
                        } else {
                            return "-"
                        }
                    }
                },{
                	field : 'ws',
                    title : '风速(m/s)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return value.toFixed(1);
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'wd',
                	title : '风向(°)',
                	align : "center",
                	width : '150',
                	sortable : true,
                	formatter: function (value,row,index) {
                    	var text = ["北风","东北风","东风","东南风","南风","西南风","西风","西北风"];
                    	if(value != null && value != ""){
                    		if(value >= 333.5 || value <= 22.5){
                    			return text[0];
                    		}else if(value > 22.5 && value <= 67.5){
                    			return text[1];
                    		}else if(value > 67.5 && value <= 112.5){
                    			return text[2];
                    		}else if(value > 112.5 && value <= 157.5){
                    			return text[3];
                    		}else if(value > 157.5 && value <= 202.5){
                    			return text[4];
                    		}else if(value > 202.5 && value <= 247.5){
                    			return text[5];
                    		}else if(value > 247.5 && value <= 292.5){
                    			return text[6];
                    		}else if(value > 292.5 && value < 337.5){
                    			return text[7];
                    		}
                    	}else{
                    		return "-";
                    	}
                    }
                }]
            });
}

//日均值
function dailyTime() {
    $("#dc").show();

    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var date = new Date();
        var startDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);
        var endDefault = new Date(date.getTime()-24 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime1d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: startDefault,
            btns: ['confirm']
        });

        var end = laydate.render({
            elem: '#endTime1d',
            type: 'date',
            max: endDefault.valueOf(),
            format: 'yyyy-MM-dd',
            isInitValue: true,
            value: endDefault
        });
    });
    setTimeout(function () {
        c();
    }, 200)
}

function c() {
    $('#exampleTable1').bootstrapTable('destroy');
    $('#exampleTable1')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix1 + "/dailyList",
                // showRefresh : true,
                // showToggle : true,
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
                showColumns : true, // 是否显示内容下拉框(选择显示的列)
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                sortable : true,
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        /*pageSize : params.limit,
                        pageNumber : (params.offset / params.limit) + 1,*/
                    	sortName : params.sort,
                    	sortOrder : params.order,
                    	siteCodes : $.siteTree.siteCodes().join(","),
                        beginTime : $("#beginTime1d").val(),
                        endTime : $("#endTime1d").val(),
                    };
                },
                onPostBody: function(){
                	resizeTable("#exampleTable1");
                },
                columns : [ {
                    field : 'siteName',
                    title : '站点',
                    align : "center",
                    width : '200',
                    valign: 'middle'
                }, {
                    field : 'queryTime',
                    title : '时间',
                    align : "center",
                    valign: 'middle',
                    width : '130',
                    sortable : true

                },{
                	field : 'pm25',
                    title : 'PM<sub>2.5</sub>(ug/m³)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                    		return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'pm10',
                    title : 'PM<sub>10</sub>(ug/m³)',
                    align : "center",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'o3EightHour',
                    title : 'O<sub>3</sub>(ug/m³)',
                    align : "center",
                    titleTooltip : "臭氧(天)",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'no2',
                    title : 'NO<sub>2</sub>(ug/m³)',
                    align : "center",
                    titleTooltip : "二氧化氮",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'so2',
                    title : 'SO<sub>2</sub>(ug/m³)',
                    align : "center",
                    titleTooltip : "二氧化硫",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'co',
                    title : 'CO(mg/m³)',
                    align : "center",
                    titleTooltip : "一氧化碳",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return value.toFixed(1);
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'aqi',
                    title : 'AQI(ug/m³)',
                    align : "center",
                    titleTooltip : "空气污染指数",
                    width : '140',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'primaryEp',
                    title : '首要污染物',
                    align : "center",
                    width : '90'
                },{
                	field : 'aqType',
                    title : '空气质量',
                    align : "center",
                    width : '80'
                },{
                	field : 'aqDegree',
                    title : '等级',
                    align : "center",
                    width : '60'
                },{
                	field : 'tsp',
                	title : 'TSP(ng/m³)',
                	align : 'center',
                	titleTooltip : "总悬浮颗粒物",
                	width : '150',
                    sortable : true,
                	formatter: function (value,row,index){
                		if(value != null && value != ""){
                			return Math.round(parseFloat(value));
                		}else{
                			return "-";
                		}
                	}
                },{
                	field : 'noise',
                    title : '噪声(dB)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return value.toFixed(1);
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'temp',
                    title : '温度(℃)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                        if(value != null && value != ""){
                            return Math.round(parseFloat(value))
                        } else {
                            return "-"
                        }
                    }
                },{
                	field : 'humi',
                    title : '湿度(%)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                        if(value != null && value != ""){
                            return value.toFixed(1);
                        } else {
                            return "-"
                        }
                    }
                },{
                	field : 'press',
                    title : '气压(HPa)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                        if(value != null && value != ""){
                            return Math.round(parseFloat(value))
                        } else {
                            return "-"
                        }
                    }
                },{
                	field : 'ws',
                    title : '风速(m/s)',
                    align : "center",
                    width : '150',
                    sortable : true,
                    formatter: function (value,row,index) {
                    	if(value != null && value != ""){
                			return value.toFixed(1);
                		}else{
                			return "-";
                		}
                    }
                },{
                	field : 'wd',
                	title : '风向(°)',
                	align : "center",
                	width : '150',
                	sortable : true,
                	formatter: function (value,row,index) {
                		var text = ["北风","东北风","东风","东南风","南风","西南风","西风","西北风"];
                		if(value != null && value != ""){
                			if(value >= 333.5 || value <= 22.5){
                				return text[0];
                			}else if(value > 22.5 && value <= 67.5){
                				return text[1];
                			}else if(value > 67.5 && value <= 112.5){
                				return text[2];
                			}else if(value > 112.5 && value <= 157.5){
                				return text[3];
                			}else if(value > 157.5 && value <= 202.5){
                				return text[4];
                			}else if(value > 202.5 && value <= 247.5){
                				return text[5];
                			}else if(value > 247.5 && value <= 292.5){
                				return text[6];
                			}else if(value > 292.5 && value < 337.5){
                				return text[7];
                			}
                		}else{
                			return "-";
                		}
                    }
                }]
            });
}

//查询
function query1() {
	timeStyleAir = $("#timeTypeAir").val();   
    if(timeStyleAir == 2){
        b();
    }
    if(timeStyleAir == 3){
        c();
    }
}

function resizeTable(id){
	var ww = window.innerHeight;
	var headH = $(id + " thead").outerHeight(true);
	var bodyW = $(id + " tbody").outerHeight(true);
	var ulH = $("#listContent ul ").outerHeight(true);
	var ch = $(id).parents("div.layui-tab-item").find(".sp2").outerHeight(true);
	$(id).parents("div .fixed-table-body").height(ww - headH - ulH - ch - 31);
}

//导出
function exportExcel1(){
	timeStyleAir = $("#timeTypeAir").val();
    if(timeStyleAir == 2){
        exportExcelAirHour();
    }
    if(timeStyleAir == 3){
    	exportExcelAirDay();
    }
}

function exportExcelAirHour(){
    layer.confirm('确定要导出当前记录？', {
            btn : [ '确定', '取消' ]
        },
        function(index){
            layer.close(index);
            var ad = $('#advancedSer').html();
            var url = prefix1 + "/airHour/exportExcel?siteCode="+$.siteTree.siteCodes().join(",")
                 + "&beginTime="+$("#beginTime1h").val() + "&endTime="+$("#endTime1h").val()
            window.location.href = url
        })
}

function exportExcelAirDay(){
    layer.confirm('确定要导出当前记录？', {
            btn : [ '确定', '取消' ]
        },
        function(index){
            layer.close(index);
            var ad = $('#advancedSer').html();
            var url = prefix1 + "/airDay/exportExcel?siteCode="+$.siteTree.siteCodes().join(",")
                + "&beginTime="+$("#beginTime1d").val() + "&endTime="+$("#endTime1d").val()
            window.location.href = url
        })
}
