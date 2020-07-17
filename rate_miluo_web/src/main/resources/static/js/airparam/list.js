var prefix = "/module/airparam"
$(function() {
	load();
});

//刷新页面
function reLoad() {
	var opt = {
		query : {
			pluName : $("#searchName").val().trim()
		}
	}
	$('#exampleTable').bootstrapTable("refresh", opt);
}

// bootstrap table 事件都是以.bs.table为后缀作为标识的
$('#exampleTable').on('load-success.bs.table', function (e, data) {
    if (data.total && !data.rows.length) {
        $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
    }
});

function reFresh() {
	location.href = prefix;
}

//展示表格数据
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						// showRefresh : true,
						// showToggle : true,
						// showColumns : true,
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
						pageSize : 15, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分页，首页页码
						// search : true, // 是否显示搜索框
						// showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"
						queryParams : function(params) {
							return {
								// limit: params.limit,
								// offset: params.offset,
								pageSize : params.limit,
								pageNumber : (params.offset / params.limit) + 1,
								pluName : $("#searchName").val().trim()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求

						columns : [ [ 
							{
							checkbox : true,
							align : 'center',
							valign : 'middle',
							colspan : 1,
							rowspan : 2,
							},
						    {
							field : '',
							title : '序号',
							align : 'center',
							valign : 'middle',
							colspan : 1,
							rowspan : 2,
							width : '80',
							formatter : function(value, row, index) {
								return index + 1;
							}
						},  {
							field : 'pluName',
							title : '污染物项目',
							align : 'center',
							valign : 'middle',
							colspan : 1,
							rowspan : 2
						}, {
							field : 'avgTime',
							title : '平均时间',
							align : 'center',
							valign : 'middle',
							width : '400',
							colspan : 1,
							rowspan : 2
						}, {
							field : '',
							title : '浓度限值',
							align : 'center',
							valign : 'middle',
							colspan : 5,
							rowspan : 1
						},
						   {
							field : 'unit',
							title : '单位',
							align : 'center',
							valign : 'middle',
							colspan : 1,
							rowspan : 2
						},{
							field : 'isAlarm',
							title : '是否需要报警',
							align : 'center',
							valign : 'middle',
							colspan : 1,
							rowspan : 2,
							 formatter:function (value) {
		                            if(value==1){
		                                return "一级";
		                            }else if(value==2){
		                                return "二级";
		                            }else if(value==3){
		                                return "三级";
		                            }else if(value==4){
		                        	  return "四级";
		                            }else if(value==5){
		                        	return "五级";
		                            }else if(value==6){
		                        	return "六级";
		                            }else if(value==7){
		                        	return "七级";
		                            }else{
		                        	return "无";
		                        }
						}},{
							title : '操作',
							field : 'id',
							align : 'center',
							valign : 'middle',
							colspan : 1,
							rowspan : 2,
							formatter : function(value, row, index) {
								var e = '<a  class="btn btn-primary btn-sm'
										+ s_edit_h
										+ '" href="#" mce_href="#" title="编辑" onclick="edit(\''
										+ row.id
										+ '\')"><i class="fa fa-edit "></i></a> ';
								var d = '<a class="btn btn-warning btn-sm '
										+ s_remove_h
										+ '" href="#" title="删除"  mce_href="#" onclick="remove(\''
										+ row.id
										+ '\')"><i class="fa fa-remove"></i></a> ';
								return e + d;
							}
						} ],
						[ {
							field : 'conLimit1',
							title : '一级',
							align : 'center',
							valign : 'middle'
						}, {
							field : 'conLimit2',
							title : '二级',
							align : 'center',
							valign : 'middle'
						},{
							field : 'conLimit3',
							title : '三级',
							align : 'center',
							valign : 'middle'
						} ,{
							field : 'conLimit4',
							title : '四级',
							align : 'center',
							valign : 'middle'
						},{
							field : 'conLimit5',
							title : '五级',
							align : 'center',
							valign : 'middle'
						}  ] ],
						onLoadSuccess : function(data) {
							var fieldList = [ "pluName" ];
							mergeCells(data, "pluName", 1, $('#exampleTable'), fieldList);//行合并
							//mergeCells(data, "riverName", 1, $('#exampleTable'));//行合并
						},
					});
}

/**
 * 合并行  按单个条件合并
 * @param data  原始数据（在服务端完成排序）
 * @param fieldName 合并属性名称数组
 * @param colspan 列数
 * @param target 目标表格对象
 */
function mergeCells(data, fieldName, colspan, target) {
	//声明一个map计算相同属性值在data对象出现的次数和
	var sortMap = {};
	for (var i = 0; i < data.rows.length; i++) {
		for ( var prop in data.rows[i]) {
			if (prop == fieldName) {
				var key = data.rows[i][prop]
				if (sortMap.hasOwnProperty(key)) {
					sortMap[key] = sortMap[key] * 1 + 1;
				} else {
					sortMap[key] = 1;
				}
				break;
			}
		}
	}
	var index = 0;
	for ( var prop in sortMap) {
		var count = sortMap[prop];
		$(target).bootstrapTable('mergeCells', {
			index : index,
			field : fieldName,
			colspan : colspan,
			rowspan : count
		});
		index += count;
	}
}

/**
 * 合并行  按多个条件合并
 * @param data  原始数据（在服务端完成排序）
 * @param fieldName 合并属性名称数组
 * @param colspan 列数
 * @param target 目标表格对象
 */
function mergeCells(data, fieldName, colspan, target, fieldList) {
	// 声明一个map计算相同属性值在data对象出现的次数和
	var sortMap = {};
	var index = 0;
	var begini = 0;
	var endi = 0;
	// 统计fieldName长度
	getCount(target, data, 0, data.rows.length, fieldName, index, sortMap);
	for ( var prop in sortMap) {
		endi = index + sortMap[prop];
		if (sortMap[prop] > 1) {
			// console.log(fieldName + ":" + prop,sortMap[prop]);
			for (var i = 0; i < fieldList.length; i++) {
				getCount(target, data, begini, endi, fieldList[i], index, null);
			}
		}
		index = begini = endi;
	}
}


//得到数量
function getCount(target, data, begini, endi, fieldName, index, sortMap) {
	if (sortMap == null) {
		sortMap = {};
	}
	for (var i = begini; i < endi; i++) {
		for ( var prop in data.rows[i]) {
			if (prop == fieldName) {
				var key = data.rows[i][prop];
				if (sortMap.hasOwnProperty(key)) {
					sortMap[key] = sortMap[key] + 1;
				} else {
					sortMap[key] = 1;
				}
				break;
			}
		}
	}
	for ( var p in sortMap) {
		var count = sortMap[p];
		$(target).bootstrapTable('mergeCells', {
			index : index,
			field : fieldName,
			colspan : 1,
			rowspan : count
		});
		index += count;
	}
}

function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '新增项目',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '700px', '800px' ],
		content : prefix + '/add'
	});
}

function edit(id) {
	layer.open({
		type : 2,
		title : '修改参数',
		maxmin : true,
		shadeClose : false,
		area : [ '700px', '800px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}


//单项删除
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			beforeSend : function(request) {
				index = layer.load();
			},
			success : function(r) {
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
//批量删除
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {
	});
}