var dataId;
//获得5分钟或者一小时的value值,dataId，在controller中params接收
function dasas(e) {
	dataId=$(e).val()
	var opt = {
		query : {
			dataId : dataId,
		}
	}

	// $("input[name='sourceType'][checked=\"checked\"]").removeAttr('checked');
	$('#exampleTable').bootstrapTable("refresh", opt);

}
function reload(){
	//重新加载整个页面
	location=window.location.href;
}
window.onload=function(){         //一进该页面就加载以下方法
	/*tick();   */                          //显示右下角日期的方法
	setInterval('countDown()',1000);    //一般秒设置为参数为1000
}
var sec = 300;                  //设置倒计时时间为30秒

function countDown() {        //倒计时的方法
	if(sec-- > 0) {
        num.innerHTML =	((sec/60).toString()).substring(0,(sec/60).toString().indexOf('.')==-1?(sec/60).toString().length:(sec/60).toString().indexOf('.'))+'分'+sec%60+'秒';

    } else {
		reload();
	}
}

var  types = null;
function xz(e){
	var opt = {
		query : {
			dataId : dataId,
		}
	}
    if (e==-1){
        types = null;
    }else if(e == 0){
        types = 0;
    }else{
        types = 1;
    }
    $("#exampleTable").bootstrapTable('refresh',opt);
}


function reloadSelect() {
    // var date = $("#searchDate").val();

    $('#exampleTable').bootstrapTable('refresh', {
    });
}

$(function() {
	load();
});

var siteCodes;
$('#stationCode').on("changed.jstree", function (e, data) {
    siteCodes = $('#stationCode').jstree().get_bottom_selected();
    if(siteCodes){
        siteCodes = siteCodes.join(",");
    }
});
function load() {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url :  "/internet/latestDataShow",
		// showRefresh : true,
		// showToggle : true,
		// showColumns : true,
		iconSize : 'outline',
		toolbar : '#exampleToolbar',
		striped : true, // 设置为true会有隔行变色效果
		dataType : "json", // 服务器返回的数据类型
		pagination : true, // 设置为true会在底部显示分页条
		pageSize : 20, // 如果设置了分页，每页数据条数
	    pageNumber : 1, // 如果设置了分布，首页页码
		// queryParamsType : "limit",
		// //设置为limit则会发送符合RESTFull格式的参数
		//$('#exampleTable').bootstrapTable('prepend', data);
		showHeader:true,//默认为true显示表头，设为false隐藏
		// singleSelect : true, // 设置为true将禁止多选
		// contentType : "application/x-www-form-urlencoded",
		// //发送到服务器的数据编码类型
		// search : true, // 是否显示搜索框
		// showColumns : true, // 是否显示内容下拉框（选择显示的列）
		 sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"

		queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			return {
				pageSize: params.limit, // 每页要显示的数据条数
				pageNumber : (params.offset / params.limit) + 1,
                siteCodes : siteCodes,
                types : types,
				//offset: params.offset// 每页显示数据的开始行号
				//sort: params.sort// 要排序的字段
				//sortOrder: params.order, // 排序规则
			};
		},
		columns :[[{
			title : '状态',
			field : 'siteType',
			formatter: function(data, row, index){
             if( data==0 ){
				 return "在线";

			 }else{
             	return"离线";
			 }},
			align : "center",
			width : '129',
			valign: 'middle',
			colspan : 1,
			rowspan : 2
		},{
			title : '站点名称',
			field : 'siteName',
			align : "center",
			width : '129',
			valign: 'middle',
			colspan : 1,
			rowspan : 2
		}, {
			title : '最新时间',
			field : 'reportTime',
			align : "center",
			width : '129',
			valign: 'middle',
			colspan : 1,
			rowspan : 2
		},{
            title : 'PM10',
            // field : 'pm10',
            align : "center",
            width : '74'
        }, {
            title : 'PM2.5',
            // field : 'pm25',
            align : "center",
            width : '74'
        },{
			title : 'SO2',
			// field : 'so2',
			align : "center",
			width : '74'

		},{
			title : 'CO',
			// field : 'co',
			align : "center",
			width : '74'
		},{
			title : 'NO2',
			// field : 'no2',
			align : "center",
			width : '74'
		},{
			title : 'O3',
			// field : 'o3',
			align : "center",
			width : '74'
		}, {
            title : '湿度',
            // field : 'humi',
            align : "center",
            width : '73'
        }, {
			title : '风速',
			// field : 'ws',
			align : "center",
			width : '74'
		}, {
			title : '风向',
			// field : 'wd',
			align : "center",
			width : '74'
		}, {
			title : '气温',
			// field : 'temp',
			align : "center",
			width : '74'
		},  {
			title : '气压',
			// field : 'press',
			align : "center",
			width : '74'
		}
		],[{
            field : 'pm10',
            title : 'μg/m3',
            align : "center",
            width : '115.5',
            formatter: function (value,row,index) {
                if(value != null && value != ""){

                    return  Math.round(parseFloat(value));
                } else {
                    return "-"
                }
            }

        },{
            field : 'pm25',
            title : 'μg/m3',
            align : "center",
            width : '115.5',
            formatter: function (value,row,index) {
                if(value != null && value != ""){

                    return  Math.round(parseFloat(value));
                } else {
                    return "-"
                }
            }
        },{
			field : 'so2',
			title : 'μg/m3',
			align : "center",
			width : '115.5',
			formatter: function (value,row,index) {
				if(value != null && value != ""){

					return  Math.round(parseFloat(value));
				} else {
					return "-"
				}
			}
		},{
			field : 'co',
			title : 'mg/m3',
			align : "center",
			width : '115.5',
			formatter: function (value,row,index) {
				if(value != null && value != ""){
					return parseFloat(value).toFixed(1);
				} else {
					return "-"
				}
			}
		},{
			field : 'no2',
			title : 'μg/m3',
			align : "center",
			width : '115.5',
			formatter: function (value,row,index) {
				if(value != null && value != ""){

					return  Math.round(parseFloat(value));
				} else {
					return "-"
				}
			}
		},{
			field : 'o3',
			title : 'μg/m3',
			align : "center",
			width : '115.5',
			formatter: function (value,row,index) {
				if(value != null && value != ""){

					return  Math.round(parseFloat(value));
				} else {
					return "-"
				}
			}
		},{
            field : 'humi',
            title : '%',
            align : "center",
            width : '115.5',
            formatter: function (value,row,index) {
                if(value != null && value != ""){
                    return Math.round(parseFloat(value));
                } else {
                    return "-"
                }
            }
        },{
			field : 'ws',
			title : 'm/s',
			align : "center",
			width : '115.5',
			formatter: function (value,row,index) {
				if(value != null && value != ""){
					return parseFloat(value).toFixed(1);
				} else {
					return "-"
				}
			}
		},{
			field : 'wd',
			title : '°',
			align : "center",
			width : '115.5',
			formatter: function (value,row,index) {
				if(value != null && value != ""){
					return parseFloat(value).toFixed(1);
				} else {
					return "-"
				}
			}
		},{
			field : 'temp',
			title : '°C',
			align : "center",
			width : '115.5',
			formatter: function (value,row,index) {
				if(value != null && value != ""){
					return parseFloat(value).toFixed(1);
				} else {
					return "-"
				}
			}
		},{

			field : 'press',
			title : 'pa',
			align : "center",
			width : '115.5',
            formatter: function (value,row,index) {
                if(value != null && value != ""){
                    return parseFloat(value).toFixed(2);
                } else {
                    return "-"
                }
            }
		}
		]]
	});
}
