var windtyMap = null;//存储winftyd的map对象
var productPopup; // 产品窗口全局变量
var isAqiOpened = false; // AQI窗口打开标识
var allCityAqiInfo; // 全国AQI站点及AQI信息
var aqiSearchSites; // 下方AQI显示列表站点选择框
var isAqiShow = true; // 全国AQI站点显示标识
var isPlayFlg = false;//播放按钮切换图片的判断值
var selectedAqiItem = null;
var interval = 0;//播放按钮停止函数的参数
var isSimpleDetailShow = false; // false,显示详细窗口，首页使用；true，显示简易aqi窗口，其他页面使用
// 计算IAQI数值
var iaqi = [ 0, 50, 100, 150, 200, 300, 400, 500 ];
var so2Hours = [ 0, 150, 500, 650, 800, 1600, 2100, 2620 ];
var no2Hours = [ 0, 100, 200, 700, 1200, 2340, 3090, 3840 ];
var coHours = [ 0, 5, 10, 35, 60, 90, 120, 150 ];
var pm25 = [ 0, 35, 75, 115, 150, 250, 350, 500 ];
var pm10 = [ 0, 50, 150, 250, 350, 420, 500, 600 ];
var o3Hours = [ 0, 160, 200, 300, 400, 800, 1000, 1200 ];

//添加进度条控件
function addAqiRangeControl(map) {

	var AqiRangeControl = L.Control.extend({
		options: {
			position: 'bottomleft'
		},
		onAdd: function(map) {
			var opDiv = L.DomUtil.create('div');
			opDiv.id = "rangeDiv";
			opDiv.style['margin-left'] = "10px";
			opDiv.style['margin-bottom'] = "25px";
			opDiv.style['height'] = "26px";
			opDiv.innerHTML = '<div id="rangeAndScale"><div style="height: 10px; width: 50px; background-color: #8B0000; float: left; margin-top: 4px"></div>'
							+ '<div id="rangeDiv" style="float: right"><input id="rangeId" type="range" onchange="timeChange()"/>'
							+ '<table class="rangeTooltipTbl"><tr></tr></table></div>';
			return opDiv;
		}
	});
	map.addControl(new AqiRangeControl());

	//设置进度条初始化宽度
	var width = document.getElementById("windyty").offsetWidth - 370;
	$("#rangeId").css("width",width);
	$(window).resize(function(){
		var changeWidth = document.getElementById("windyty").offsetWidth-370;
		//进度条宽度随页面宽度变化
		$("#rangeId").css("width",changeWidth);
		$("table.rangeTooltipTbl tr").html("");
		setAqiScale(W.timeline.end,W.timeline.start,changeWidth);
	});

	//设置进度条最大最小值
	$("#rangeId").attr("max",W.timeline.end);
	$("#rangeId").attr("min",W.timeline.start);
	//添加刻度尺
	setAqiScale(W.timeline.end,W.timeline.start,width);
	//鼠标在进度条上时函数
	$('#rangeId').mousemove(function(e) { 
		//进度条高度坐标
		var rangetop = $('#rangeId').offset().top;
		//进度条左边距坐标
		var rangeleft = $('#rangeId').offset().left;
		//进度条长度值
		var rangewidth = $('#rangeId').css("width");
		//鼠标x轴位置
		var xposition = e.originalEvent.x || e.originalEvent.layerX || 0;
		//鼠标当前坐标在进度条长度百分比
		var ratio = (xposition-rangeleft)/parseInt(rangewidth);
		//鼠标当前坐标时间戳
		var xtimes = (W.timeline.start)+((W.timeline.end-W.timeline.start)*ratio);
		//判断是否为火狐浏览器
		//火狐浏览器鼠标像素抓取位置与谷歌不同，所以计算出的时间戳有误差，需补差
		var userAgent = navigator.userAgent; 
		if (userAgent.indexOf("Firefox") > -1) {  
			xtimes = 5496516+xtimes;
			xposition = 8+xposition;
		}
		//鼠标当前时间对象
		var xtime = new Date(parseInt(xtimes));
		//显示鼠标停留时时间提示框
		$("#prompt").show(50);
		$("#prompt").text(xtime.getHours()+":00");
		//设置提示框x轴位置
		$("#prompt").css("left",xposition-25);
		//设置提示框y轴位置
		$("#prompt").css("top",rangetop-30);
	});
	//鼠标移开进度条时函数
	$('#rangeId').mouseleave(function(e) { 
		$("#prompt").hide(50);
	});
}

// 添加进度条下刻度尺
function setAqiScale(max,min,width){
	// 存储周信息数组
	var weekday = ["周日","周一","周二","周三","周四","周五","周六"];
	var maxDate = new Date(parseInt(max.toString()));
	// 取得进度条变化值的整小时时间字符串
	var maxtime = formatDate(maxDate);
	var minDate = new Date(parseInt(min.toString()));
	// 取得最小日期的整小时时间字符串
	var mintime = formatDate(minDate);
	//计算出刻度条显示天数（间隔毫秒数/一天毫秒数）
	var days = (max-min)/(3600000*24);
	//刻度条生成语句字符串
	var scaleString = "<th style='height: 30px;width: 50px'><img id='playBtn' style='vertical-align:middle;;margin-left: -5px' src='" + startbuttonImgUrl + "' onclick='clickPlayBtn()'></th>";
	// 每天的宽度
	var dayWidth = width / days - 2;
	//按照天数拼接生成语句
	for(var num = 0;num < days - 1;num++){
		//以最小日期每次加一天的方式添加
		var weekDate = new Date(min+((3600000*24)*num));
		//得到周信息
		var week = weekDate.getDay();
		//得到日信息
		var day = weekDate.getDate();
		//取得th标签字符串
		var str = "<th style='width: "+ dayWidth +"px'>"+weekday[week]+day+"</th>";
		scaleString += str;
	}
	$("table.rangeTooltipTbl tr").append(scaleString);
}

//进度条值发生变化时函数
function timeChange() {
	//取得当前进度条时间戳
	var now = $("#rangeId").val();
	// 如果正在播放，停止播放
	stopRange();
	var nowDate = new Date(parseInt(now.toString()));
	//取得进度条变化值的整小时时间字符串
	var timestamp = formatDate(nowDate);
	//进度条值随变化
	changeRangeValue(timestamp);
	//进度条刻度随变化
	changeRangeSize(timestamp);
	//随进度条变化修改地图时间
	W.setTimestamp(parseInt(now.toString())); 
	//随刻度变化重新设置站点标识
	getAqiByTime(formatDate(nowDate));
}

//获取指定时间的全国AQI数据并设置站点
function getAqiByTime(time){
	//$.ajax({
	//	url : getAqiByTimeUrl,
	//	type : "post",
	//	async: true,
	//	dataType:"json",
	//	data: {"latestTime": time},
	//	success : function(data) {
	//		if(null!=data[defaultAqiSiteCode]){
	//			//当前进度条时间查询数据区中有数据时，设置成当前数据
	//			toggleSite(data);
	//		}else{
	//			getNewestTimeAqi();
	//		}
	//	},
	//	error:function(XMLResponse){
	//		console.log(JSON.stringify(XMLResponse));
	//	}
	//});
}

//进度条改变时切换站点方法
function toggleSite(data){
	for(var siteCode in allCityAqiInfo) {
		for(var proItem in data[siteCode]) {
			allCityAqiInfo[siteCode][proItem] = data[siteCode][proItem];
		}
	}
	// 改变左上角图层控件
	var selectedCityCode = $("#aqiCityCode").text();
	changeAqiContent(allCityAqiInfo[selectedCityCode], selectedCityCode);
	// 改变全国站点数据
	changeCityAqiItem(selectedAqiItem || "aqi", windtyMap);
}

//获取数据库最新时间的全国AQI数据并重新设置站点
function getNewestTimeAqi(){
	$.ajax({
		url : getNewestTimeAqiUrl,
		type : "post",
		async: true,
		dataType:"json",
		data: {},
		success : function(data) {
			if(data.length > 0) {
				toggleSite(data);
			}
		},
		error:function(XMLResponse){
			console.log(JSON.stringify(XMLResponse));
		}
	});
}
//设置进度条value
function changeRangeValue(time){
	var changetime = Date.parse(GetTimeByTimeStr(time));
	$("#rangeId").attr("value",changetime);
}
//设置进度条刻度
function changeRangeSize(time){
	var now = Date.parse(GetTimeByTimeStr(time));
	var max = $("#rangeId").attr("max");
	var min = $("#rangeId").attr("min");
	//计算出进度条时间位置
	var rangeChange = (now-min)/(max-min)*100;
	//设置进度条时间位置
	$("#rangeId").css("background-size", rangeChange + "% 100%");
}


//将字符串转换为时间格式,适用各种浏览器,格式如2011-08-03 09:15:11
function GetTimeByTimeStr(dateStr) {
	var timeArr=dateStr.split(" ");
	var d=timeArr[0].split("-");
	var t=timeArr[1].split(":");
	return new Date(d[0],(d[1]-1),d[2],t[0],t[1],t[2]);
}

// 格式化时间（yyyy-MM-dd hh:00:00）
function formatDate(now) {
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	month = formatTime(month);
	var date=now.getDate();
	date = formatTime(date);
	var hour=now.getHours();
	hour = formatTime(hour);
	return year + "-" + month + "-" + date + " " + hour + ":00:00";
}

//当时间数小于两位数时，在前面加0
function formatTime(time){
	if(0 <= time && 10 > time){
		time = "0"+time;
	}
	return time;
}

//播放按钮点击函数
function clickPlayBtn() {
	if(!isPlayFlg){
		playRange();
	}else{
		stopRange();
	} 
}

// 播放操作
function playRange() {
	$("#playBtn").attr("src", stopbuttonImgUrl);
	isPlayFlg = true;
	interval = window.setInterval(function() {
		var now = $("#rangeId").attr("value");
		if((W.timeline.start)>now){
			now = (W.timeline.start);
		}
		if((W.timeline.end)<now){
			now = (W.timeline.end-(60*60*100))
		}
		var newnow = parseInt(now)+(60*60*1000);
		var nowDate = new Date(newnow);
		//取得进度条变化值的整小时时间字符串
		var timestamp = formatDate(nowDate);
		//进度条值随变化
		changeRangeValue(timestamp);
		//进度条刻度随变化
		changeRangeSize(timestamp);
		//随进度条变化修改地图时间
		W.setTimestamp(newnow); 
		//随刻度变化重新设置站点标识
		getAqiByTime(formatDate(nowDate));
	}, 5000);
}

// 停止操作
function stopRange() {
	$("#playBtn").attr("src", startbuttonImgUrl);
	isPlayFlg = false;
	if(interval > 0) {
		interval = window.clearInterval(interval);
	}
}

//添加右上角AQI选项控件
function addAqiItemControl(map) {
	
	var AqiItemControl = L.Control.extend({
		options: {
			position: 'topright'
		},
		onAdd: function(map) {
			var div = L.DomUtil.create('div', 'leaflet-control-clegend');
			div.id = "aqiItemOverlaysDiv";
			div.innerHTML = '<div id="aqiItemDiv" class="aqiItemDiv"><ul class="aqiItemOverlays">'
                + '<li style="background-color:#00E400;color:dark;display:inline-block;width:50px;height:24px;text-align:center;" title="各类人群可正常活动">优</li>'
                + '<li style="background-color: #FFFF00;color:dark; display: inline-block; width: 50px; height: 24px; text-align: center;" title="极少数异常敏感人群应减少户外活动">良</li>'
                + '<li style="background-color: #FF7E00;color:dark; display: inline-block; width: 50px; height: 24px; text-align: center;" title="儿童、老年人及心脏病、呼吸系统疾病患者应减少长时间、高强度的户外锻炼">轻度污染</li>'
                + '<li style="background-color: #FF0000;color:dark; display: inline-block; width: 50px; height: 24px; text-align: center;" title="儿童、老年人及心脏病、呼吸系统疾病患者避免长时间、高强度的户外锻炼，一般人群适量减少户外活动">中度污染</li>'
                + '<li style="background-color: #99004C; color: white; display: inline-block; width: 50px; height: 24px; text-align: center;" title="儿童、老年人和心脏病、肺病患者应停留在室内，停止户外活动，一般人群减少户外运动">重度污染</li>'
                + '<li style="background-color: #7E0023; color: white; display: inline-block; width: 50px; height: 24px; text-align: center;" title="儿童、老年人和病人应当停留在室内，避免体力消耗，一般人群应避免户外活动">严重污染</li>'
                + '<li style="background-color: gray; color: white; display: inline-block; width: 50px; height: 24px; text-align: center;">离线</li>'
							+ '</ul></div>';
			return div;
		}
	});
	
	map.addControl(new AqiItemControl());
	
	// 控件方法添加
	$("#aqiItemDiv input[name='aqiItemRadio']").change(function() {
		var valueSelected = $(this).val();
		$("#aqiItemDiv input[name='aqiItemRadio']").each(function() {
			if(valueSelected == $(this).val()) {
				$(this).parent().attr('class','checked');
			} else {
				$(this).parent().attr('class','unchecked');
			}
		});
		selectedAqiItem = valueSelected;
		changeCityAqiItem(valueSelected, map);
	});

	$("#aqiItemDiv input[name='aqiItemRadio']:eq(1)").attr('checked', 'checked');
	$("#aqiItemDiv input[name='aqiItemRadio']:eq(1)").parent().attr('class','checked');
}

// 查看AQI项目改变事件
function changeCityAqiItem(aqiItem, map) {
	if(aqiItem == "none") {
		for(var siteCode in allCityAqiInfo) {
			var cityAqiInfo = allCityAqiInfo[siteCode];
			cityAqiInfo.marker.setStyle({fillOpacity: 0, opacity: 0, clickable: false});
		}
		isAqiShow = false;
	} else {
		for(var siteCode in allCityAqiInfo) {
			var cityAqiInfo = allCityAqiInfo[siteCode];
			var color = pickColor(getIaqi(aqiItem, +cityAqiInfo[aqiItem]));
			cityAqiInfo.marker.setStyle({color: color, fillColor: color});
			if(!isAqiShow) {
				cityAqiInfo.marker.setStyle({fillOpacity: 1, opacity: 0, clickable: true});
			}
		}
		isAqiShow = true;
	}
}

// 地图大小改变时，改变AQI站点大小
function changeAqiSiteStyle(zoom) {

	var radius = 10;
	if(zoom > 7) {
		radius = 10;
	} else {
		radius = 6;
	}

	for(var siteCode in allCityAqiInfo) {
		var cityAqiInfo = allCityAqiInfo[siteCode];
		cityAqiInfo.marker.setStyle({radius: radius});
	}
}

//// 添加AQI站点标记
//function addAqiMarker(map, siteCode, cityAqiInfo) {

//	var color = pickColor(cityAqiInfo.aqi);
//	var marker = L.circleMarker([cityAqiInfo.latitude, cityAqiInfo.longitude], {
//		color: color,
//		fillColor: color,
//		fillOpacity: 1,
//		radius: 10
//	}).addTo(map);
//	marker.siteCode = siteCode;
//	cityAqiInfo.marker = marker;

//	marker.on("click", onAqiSiteClick);
//}



// 通过查询内容，改变AQI窗口内容，且根据不同内容，改变不同窗口
function changeAqiContent(cityAqiInfo, siteCode) {
	if(isSimpleDetailShow) {
		changeSimpleAqiDialog && changeSimpleAqiDialog(cityAqiInfo,siteCode);
	} else {
		changeAqiDialog(cityAqiInfo, siteCode);
	}
}




function getIaqiFun(iaqiArr, itemArr, bpValue) {

	var result = 0;
	for (var i = 0; i < iaqiArr.length - 1; i++) {
		if (bpValue > itemArr[i] && bpValue < itemArr[i + 1]) {
			result = (iaqiArr[i + 1] - iaqiArr[i]) * (bpValue - itemArr[i]) / (itemArr[i + 1] - itemArr[i]) + iaqiArr[i];
			break;
		}
	}
	if (bpValue > itemArr[iaqiArr.length - 1]) {
		result = iaqiArr[iaqiArr.length - 1];
	}
	return result;
}



//左下角AQI控件
function addAqiControl(map) {

	var AqiControl = L.Control.extend({
		options: {
			position: 'bottomleft'
		},
		onAdd: function(map) {
			var opDiv = L.DomUtil.create('div');
			opDiv.id = "aqiContainer";
			opDiv.class = "aqiContainer";
			opDiv.style['width'] = ($("#windyty").width()-50) + "px";
			opDiv.style['margin-right'] = "20px";
			opDiv.style['margin-bottom'] = "0px";
			//opDiv.innerHTML = '<div id="aqiSlideBtn" class="aqiSlideBtn"><span class="iconfont arrow-font icon-arrow-up"></span></div>';
			return opDiv;
		}
	});

	map.addControl(new AqiControl());

}



// 隐藏AQI列表
function hideAqiArea() {
	$("#aqiContext").html();
}

// 浓度值改变背景颜色
function pickColor(aqi) {
	var color = "rgba(0,228,0, 1)";
	if (!aqi || aqi == 0) {
		color = " grey";
	} else if (aqi<=50 && aqi>0) {
		color = "rgba(0,228,0, 1)";
	} else if (aqi<=100 && aqi>50) {
		color = "rgba(255,255,0, 1)";
	} else if (aqi<=150 && aqi>100) {
		color = "rgba(255,126,0, 1)";
	} else if (aqi<=200 && aqi>150) {
		color = "rgba(255,0,0, 1)";
	} else if (aqi<=300 && aqi>200) {
		color = "rgba(153,0,76, 1)";
	} else if (aqi > 300) {
		color = "rgba(126,0,35, 1)";
	}
	return color;
}

// AQI列表分页按钮
function pagerFilter(data){
	if ($.isArray(data)){	// is array
		data = {
			total: data.length,
			rows: data
		}
	}
	var target = this;
	var dg = $(target);
	var state = dg.data('datagrid');
	var opts = dg.datagrid('options');
	state.allRows = (data.rows);
	if (!opts.remoteSort && opts.sortName){
		var names = opts.sortName.split(',');
		var orders = opts.sortOrder.split(',');
		state.allRows.sort(function(r1,r2){
			var r = 0;
			for(var i=0; i<names.length; i++){
				var sn = names[i];
				var so = orders[i];
				var col = $(target).datagrid('getColumnOption', sn);
				var sortFunc = col.sorter || function(a,b){
					return a==b ? 0 : (a>b?1:-1);
				};
				r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
				if (r != 0){
					return r;
				}
			}
			return r;
		});
	}
	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = state.allRows.slice(start, end);
	return data;
}

// 页面大小改变触发事件
window.onresize = function() {
	var mapWidth = $("#windyty").width();
	$("#aqiContainer").width(mapWidth - 50);
};

//加入数据来源
function addSourceControl(map, W) {
	var SourceControl = L.Control.extend({
		options: {
			position: 'bottomright'
		},
		onAdd: function(map) {
			var opDiv = L.DomUtil.create('div');
			opDiv.id = "sourceDiv";
			opDiv.style['margin-right'] = "50px";
			opDiv.style['margin-bottom'] = "0px";
			opDiv.style['height'] = "20px";
//			opDiv.innerHTML = '<div id="source" style="color: white;font-size: 14px">预报数据来源：<a target="_blank" style="color: white;" href="http://www.windy.com">www.windy.com</a></div>'
			opDiv.innerHTML = '<div id="source" style="color: white;font-size: 14px">预报数据来源：www.windy.com</div>'
			return opDiv;
		}
	});
	map.addControl(new SourceControl());
}

//加入右下角图层及高度控件
function addOptionControl(map, W) {

	var OptionControl = L.Control.extend({
		options: {
			position: 'bottomright'
		},
		onAdd: function(map) {
			var opDiv = L.DomUtil.create('div');
			opDiv.id = "optionDiv";
			opDiv.style['margin-right'] = "50px";
			opDiv.style['margin-bottom'] = "-16px";
			opDiv.style['height'] = "272px";
			opDiv.innerHTML = '<div id="overlaysDiv"><ul class="overlays">'
							+ '<li><input name="overlayRadio" type="radio" value="wind" id="windRadio" style="display: none"><label for="windRadio"><span class="iconfont icon-wind">&nbsp;风&nbsp;&nbsp;</span></label></li>'
							+ '<li><input name="overlayRadio" type="radio" value="temp" id="tempRadio" style="display: none"><label for="tempRadio"><span class="iconfont icon-temp">&nbsp;温度&nbsp;</span></label></li>'
							+ '<li><input name="overlayRadio" type="radio" value="rh" id="rhRadio" style="display: none"><label for="rhRadio"><span class="iconfont icon-rh">&nbsp;湿度&nbsp;</span></label></li>'
							+ '<li><input name="overlayRadio" type="radio" value="pressure" id="pressureRadio" style="display: none"><label for="pressureRadio"><span class="iconfont icon-pressure">&nbsp;气压&nbsp;</span></label></li>'
							+ '<li><input name="overlayRadio" type="radio" value="clouds" id="cloundRadio" style="display: none"><label for="cloundRadio"><span class="iconfont icon-clouds">&nbsp;云&nbsp;&nbsp;</span></label></li></ul></div>'
							+ '<div id="levelDiv"><ul class="level">'
							+ '<li><input name="levelRadio" type="radio" value="150h" id="150hRadio" style="display: none"><label for="150hRadio"><span>150h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="200h" id="200hRadio" style="display: none"><label for="200hRadio"><span>200h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="250h" id="250hRadio" style="display: none"><label for="250hRadio"><span>250h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="300h" id="300hRadio" style="display: none"><label for="300hRadio"><span>300h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="350h" id="350hRadio" style="display: none"><label for="350hRadio"><span>350h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="450h" id="450hRadio" style="display: none"><label for="450hRadio"><span>450h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="550h" id="550hRadio" style="display: none"><label for="550hRadio"><span>550h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="700h" id="700hRadio" style="display: none"><label for="700hRadio"><span>700h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="750h" id="750hRadio" style="display: none"><label for="750hRadio"><span>750h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="850h" id="850hRadio" style="display: none"><label for="850hRadio"><span>850h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="900h" id="900hRadio" style="display: none"><label for="900hRadio"><span>900h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="925h" id="925hRadio" style="display: none"><label for="925hRadio"><span>925h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="950h" id="950hRadio" style="display: none"><label for="950hRadio"><span>950h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="975h" id="975hRadio" style="display: none"><label for="975hRadio"><span>975h</span></label></li>'
							+ '<li><input name="levelRadio" type="radio" value="surface" id="surfaceRadio" style="display: none"><label for="surfaceRadio"><span>近地面</span></label></li></ul></div>';
			return opDiv;
		}
	});
	map.addControl(new OptionControl());

	// 图层控件方法添加
	$("#overlaysDiv input[name='overlayRadio']").change(function() {
		var valueSelected = $(this).val();
		W.setOverlay(valueSelected);
		$("#overlaysDiv input[name='overlayRadio']").each(function() {
			if(valueSelected == $(this).val()) {
				$(this).parent().attr('class','checked');
			} else {
				$(this).parent().attr('class','unchecked');
			}
		});
	});

	$("#overlaysDiv input[name='overlayRadio']:eq(0)").attr('checked', 'checked');
	$("#overlaysDiv input[name='overlayRadio']:eq(0)").parent().attr('class','checked');

	// 高度控件方法添加
	$("#levelDiv input[name='levelRadio']").change(function() {
		var valueSelected = $(this).val();
		W.setLevel(valueSelected);
		$("#levelDiv input[name='levelRadio']").each(function() {
			if(valueSelected == $(this).val()) {
				$(this).parent().attr('class','checked');
			} else {
				$(this).parent().attr('class','unchecked');
			}
		});
	});

	$("#levelDiv input[name='levelRadio']:eq(14)").attr('checked', 'checked');
	$("#levelDiv input[name='levelRadio']:eq(14)").parent().attr('class','checked');
}
