var prefix = "/task/agents"
$(function () {
    load();

});
$('#exampleTable').on('load-success.bs.table', function (e, data) {
    if (data.total && !data.rows.length) {
        $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
    }
});

var myColumns;
function reFresh(){
	//getColumns();
	location.href = prefix+"/historyIndex";
	//点击左侧树后重新加载表格
	/*$table.bootstrapTable(
	              "refreshOptions",  
	              {
	            	url : prefix + "/historyLists", // 获取数据的地址
	            	columns : myColumns,
	              }  
	      	);*/
}

//导出Excel
function exportExcel() {
    layer.confirm('确定要导出当前记录？', {
        btn : [ '确定', '取消' ]
    }, function(index) {
        layer.close(index);
        var url = "";
        url += "/task/agents/hisExp?beginTime=" + $("#beginTime").val()
            + "&endTime=" + $("#endTime").val()
            + "&fzr=" + $("#searchUsername").val()
            +"&taskName="+$("#searchOperation").val();
        window.location.href = url
    })
}

var peopleOptions;
function load() {
	peopleOptions = {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/historyLists", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                // showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                	 return {
                         // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                         pageSize : params.limit,
                         pageNumber : (params.offset / params.limit) + 1,
                         sortName : params.sort, // 排序列名
                         sortOrder : params.order, // 排序命令（desc，asc）
                         beginTime : $("#beginTime1").val(),
                         endTime : $("#endTime1").val(),
                         fzr:$("#searchUsername").val(),
                         taskName:$("#searchOperation").val(),
                     };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    {
            			title : '序号',
            			align : "center",
            			width : '50',
            			formatter : function(value, row, index) {
            				return index + 1;
            			}
                    },
                    {
                        field: 'name',
                        align : "center",
                        title: '任务名称'
                    },
                    {
                        field: 'siteName',
                        align : "center",
                        title: '站点名称'
                    },
                    {
                        field: 'fzr',
                        align : "center",
                        title: '负责人'
                    },
                    {
                        field: 'degreeEmergency',
                        title: '紧急程度',
                        align : "center",
                        formatter:function (value) {
                            if(value==0){
                                return "普通不紧急";
                            }else if(value==1){
                                return "重要紧急";
                            }else if(value==2){
                                return "最高级别";
                            }else{
                                return "其他";
                            }
                        }
                    },
                    {
                        field: 'startTime',
                        align : "center",
                        title: '开始时间'
                    },
                    {
                        field: 'endTime',
                        align : "center",
                        title: '结束时间'
                    },
                    {
                        field: 'status',
                        align : "center",
                        title: '处理状态',
                        formatter:function (value) {
                            if(value==0){
                                return "未处理";
                            }else if(value==1){
                                return "已处理";
                            }else{
                                return "其他";
                            }
                        }
                    },
                    {
                        field: 'taskType',
                        align : "center",
                        title: '任务类型',
                        formatter:function (value) {
                            if(value==0){
                                return "报警任务";
                            }else if(value==1){
                                return "巡检";
                            }else if(value==2){
                                return "维护";
                            }else if(value==3){
                                return "易耗品更换";
                            }else if(value==4){
                                return "仪器校准";
                            }else if(value==5){
                                return "维修";
                            }else if(value==6){
                                return "试剂更换";
                            }else if(value==7){
                                return "废液回收";
                            }else if(value==8){
                                return "其他";
                            }else if(value==9){
                                return "因子超标";
                            }else{
                            	return "未知";
                            }
                        }
                    }, {
                        field: 'stationCode',
                        align : "center",
                        title: '设备编号'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="查看" onclick="edit(\''
                                + row.id+"\',\'"+row.status
                                + '\')"><i class="fa fa-search"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d;
                        }
                    }]
	}
    $table = $("#exampleTable").bootstrapTable(peopleOptions);
}

function getColumns() {
	// 加载动态表格
/*	$.ajax({
		url : prefix + "/minuteList",
		type : 'get',
		dataType : "json",
		async : false,
		success : function(returnValue) {
			// 未查询到相应的列，展示默认列
			if (returnValue.retCode == "0") {
				//没查到列的时候把之前的列再给它
				myColumns = $table.bootstrapTable('getOptions').columns[0];
			} else {
				// 异步获取要动态生成的列
				var arr = returnValue.data;
				$.each(arr, function(i, item) {
					myColumns.push({
						"field" : item.labelColumnCode,
						"title" : item.labelColumnName,
						"hide" : true,
						"align" : 'center',
						"valign" : 'middle'
					});
				});
			}
			
			
			
			
			console.log(myColumns);
			return myColumns;
		}
	});*/
	
	myColumns=[
	 {
         field: 'fzr',
         title: '负责人'
     },
     {
         field: 'degreeEmergency',
         title: '紧急程度',
         formatter:function (value) {
             if(value==0){
                 return "普通不紧急";
             }else if(value==1){
                 return "重要紧急";
             }else if(value==2){
                 return "最高级别";
             }else{
                 return "未知";
             }
         }
     },
    /* {
         field: 'dpname',
         title: '所属部门'
     },*/
     {
         field: 'startTime',
         title: '开始时间'
     },
     {
         field: 'endTime',
         title: '结束时间'
     }]
}



function edit(id,status) {
    layer.open({
        type : 2,
        title : '历史任务查看',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '700px' ],
        content : prefix + '/edit/' + id+"/"+status // iframe的url
    });
}

function reLoad() {
   $('#exampleTable').bootstrapTable('refresh');
}



function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            beforeSend: function (request) {
                index = layer.load();
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.close(index);
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}


$(function(){
    resetHeight();
    $(window).resize(function () {
        resetHeight();
    })
});

function resetHeight(){
    var ww=window.innerHeight;
    var headH=$(".columns .pull-left").outerHeight(true);
    var footH=$(".pagination-detail").outerHeight(true);
    $(".fixed-table-body").height(ww-headH-footH-110);
    $(".fixed-table-container").css("border","none");
}