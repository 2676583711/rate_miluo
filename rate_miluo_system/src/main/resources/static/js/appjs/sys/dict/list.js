var prefix="/sys/dict"
	
$(function() {
	load();
});

function reFresh(){
	location.href=prefix;
}

function reLoad(){
	$('#dictTable').bootstrapTable('refresh');
}

function load(){
	$('#dictTable').bootstrapTable({
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
		pageSize : 10, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		// search : true, // 是否显示搜索框
		showColumns : false, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
		// "server"
		queryParams : function(params) {
			return {
				// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
				pageSize : params.limit,
				pageNumber : (params.offset / params.limit) + 1,
				description :$('#description').val(),
				name : $('#name').val()
			};
		},
		columns : [
			{
				checkbox : true
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
				field : 'name',
				align : "center",
				title : '名称',
				width :'15%'
			},
			{
				field : 'value',
				align : "center",
				title : '数据值',
				width :'15%'
			},{
				field : 'type',
				align : "center",
				title : '类型',
				width :'15%'
			},
			{
				field : 'description',
				align : "center",
				title : '类型描述',
				width :'15%'
			},{
				field : 'createName',
				align : "center",
				title : '创建人',
				width :'15%'
			},{
				field : 'updateName',
				align : "center",
				title : '修改人',
				width :'15%'
			},{
				title : '操作',
				field : 'id',
				align : 'center',
				formatter : function(value, row, index) {
					var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
							+ row.id
							+ '\')"><i class="fa fa-edit"></i></a> ';
					var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
							+ row.id
							+ '\')"><i class="fa fa-remove"></i></a> ';
					return e + d;
				}
			}
			]
	});
}

function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '添加字典',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add' // iframe的url
	});
}

function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url :  prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function edit(id) {
	layer.open({
		type : 2,
		title : '字典修改',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}

function batchRemove() {
	var rows = $('#dictTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
	}, function() {});
}
