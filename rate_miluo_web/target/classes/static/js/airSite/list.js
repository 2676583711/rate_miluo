var prefix = "/airSite";
$(function() {
	load();
});

function reFresh() {
	window.location.href = prefix + "/list";
}
function reLoad() {
	$("#exampleTable").bootstrapTable("refresh");
}

function load(siteIds) {
	$('#exampleTable').bootstrapTable("destroy").bootstrapTable(
		{
			method : 'get', // 服务器数据的请求方式 get or post
			url : prefix + "/index",
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
			pageSize : 20, // 如果设置了分页，每页数据条数
			// search : true, // 是否显示搜索框
			showColumns : false, // 是否显示内容下拉框（选择显示的列）
			sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
			queryParams : function(params) {
				return {
					pageSize : params.limit,
					pageNumber : (params.offset / params.limit) + 1,
					// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
					limit : params.limit,
					offset : params.offset,
					positionName : $('#positionName').val().trim(),
                    siteIds : siteIds,
				};
			},
			onPostBody : function() {
				resetHeight();
			},
			columns : [
					// {
					// 	checkbox : true
					// },
					{
						title : '序号',
						align : "center",
						width : '50',
						formatter : function(value, row, index) {
							return index + 1;
						}
					},
					{
						field : 'siteCode',
						title : '站点编码'
					},
					{
						field : 'name',
						title : '站点名称'
					},
					{
						field : 'areaType',
						title : '行政区划',
						formatter : function(value) {
							if (value == "1") {
								return "城区";
							} else if (value == "2") {
								return "乡镇";
							} else {
								return "未知";
							}
						}
					},
					{
						field : 'longitude',
						title : '经度'
					},
					{
						field : 'latitude',
						title : '纬度'
					},
					{
						field : 'siteType',
						title : '站点类型',
						formatter : function(value) {
							if (value == "1") {
								return "水质";
							} else if (value == "2") {
								return "空气";
							} else if (value == "3") {
								return "涉气污染源";
							} else if (value == "4") {
								return "涉水污染源";
							} else {
								return "未知";
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						class : 'noprint',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm '
									+ '" href="#" mce_href="#" title="编辑" onclick="edit(\''
									+ row.id
									+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm '
									+ '" href="#" title="删除"  mce_href="#" onclick="remove(\''
									+ row.id
									+ '\')"><i class="fa fa-remove"></i></a> ';
							var x = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="detail(\''
									+ row.id
									+ '\')"><i class="glyphicon glyphicon-zoom-in"></i></a> ';
							var f = '<a class="btn btn-success btn-sm" href="#" title="查看设备"  mce_href="#" onclick="showEqu(\''
									+ row.id
									+ '\')"><i class="glyphicon glyphicon-search"></i></a> ';
							var g = '<a class="btn btn-success btn-sm" href="#" title="查看摄像机"  mce_href="#" onclick="showVidicon(\''
									+ row.id
									+ '\')"><i class="fa fa-file-video-o"></i></a> ';
							return e + d +x+ f + g;
						}
					} ]
		});
}


//详情
function detail(id) {
    layer.open({
        type: 2,
        title: '站点详情',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['100%', '100%'],
        content: prefix + '/detail/' + id // iframe的url
    });
}

function add() {
	var page = layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/addSite' // iframe的url
	});
	layer.full(page);
}

function batchRemove2() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	// console.log(rows);
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
					reFresh();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}

function edit(id) {
	var page = layer.open({
		type : 2,
		title : '编辑',
		// shift : -1,
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	layer.full(page);
}

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
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reFresh();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}
// 点击查看站点设备
function showEqu(id) {
    var page = layer.open({
        type : 2,
        title : '设备管理',
        // shift : -1,
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '480px' ],
        content : prefix + '/showEqu/' + id
    });
    layer.full(page);


	// location.href = prefix + '/show/' + id + '/' + curPage;
}
// 点击查看摄像机设备
function showVidicon(siteId) {
	var curPage = ($('#exampleTable').bootstrapTable('getOptions').pageNumber);
    var page = layer.open({
        type : 2,
        title : '详细信息',
        // shift : -1,
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '480px' ],
        content : '/vidicon/showVidicon/' + siteId + '/' + curPage
    });
    layer.full(page);
}