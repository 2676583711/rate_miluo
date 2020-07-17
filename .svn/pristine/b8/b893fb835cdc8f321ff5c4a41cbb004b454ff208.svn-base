var tableData = null;
$(function() {
	$("#from").hide();
	//0.初始化fileinput
	var oFileInput = new FileInput();
	oFileInput.Init("file", "/sys/user/inputFile");
});
//初始化fileinput
var FileInput = function() {
	var oFile = new Object();

	//初始化fileinput控件（第一次初始化）
	oFile.Init = function(ctrlName, uploadUrl) {
		var control = $('#' + ctrlName);
		//初始化上传控件的样式
		control.fileinput({
			language : 'zh', //设置语言
			uploadUrl : uploadUrl, //上传的地址
			allowedFileExtensions : [ 'xls', 'xlsx' ],//接收的文件后缀
			showUpload : true, //是否显示上传按钮
			showCaption : false,//是否显示标题
			showCancel : false,//是否显示取消按钮
			showClose : false,//是否显示预览框的关闭图标
			browseClass : "btn btn-success", //按钮样式   
			showPreview : true,//是否显示预览
			dropZoneTitle : "拖拽文件到这里 ，或者点击左下方的选择&hellip;",
			previewFileIconSettings : {
				'docx' : '<i ass="fa fa-file-word-o text-primary"></i>',
				'xlsx' : '<i class="fa fa-file-excel-o text-success"></i>',
				'xls' : '<i class="fa fa-file-excel-o text-success"></i>',
				'pptx' : '<i class="fa fa-file-powerpoint-o text-danger"></i>',
				'jpg' : '<i class="fa fa-file-photo-o text-warning"></i>',
				'pdf' : '<i class="fa fa-file-archive-o text-muted"></i>',
				'zip' : '<i class="fa fa-file-archive-o text-muted"></i>',
			},
			//dropZoneEnabled: false,//是否显示拖拽区域
			//minImageWidth: 50, //图片的最小宽度
			//minImageHeight: 50,//图片的最小高度
			//maxImageWidth: 1000,//图片的最大宽度
			//maxImageHeight: 1000,//图片的最大高度
			//maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
			//minFileCount: 0,
			maxFileCount : 10, //表示允许同时上传的最大文件个数
			enctype : 'multipart/form-data',
			validateInitialCount : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
		});
		//导入文件上传完成之后的事件
		$("#file").on("fileuploaded", function(event, data, previewId, index) {
			tableData = data.response;
			if (data == undefined) {
				layer.alert("文件格式类型不正确或没有数据");
				return;
			}
			$("#myModal").hide();
			$("#from").show();
			//1.初始化表格
			$('#exampleTable').bootstrapTable({
				data : tableData, // 数据
				showRefresh : false,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				singleSelect : false, // 设置为true将禁止多选
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
					};
				},
				columns : [ {

					title : '序号',//标题  可不加  
					align : "center",
					width : '50',
					formatter : function(value, row, index) {
						return index + 1;
					}
				}, {
					width : '130',
					align : "center",
					sortable : true,//启用排序
					field : 'name',
					title : '(*必填)姓名'
				}, {
					width : '95',
					align : "center",
					field : 'username',
					title : '(*必填)用户名'
				}, {
					width : '200',
					align : "center",
					field : 'password',
					title : '(*必填)密码'
				},  {
					width : '170',
					align : "center",
					field : 'mobile',
					title : '(*必填)联系方式'
				}, {
					width : '100',
					align : "center",
					field : 'workName',
					title : '(*必填)职位'
				}, {
					width : '100',
					align : "center",
					field : 'username2',
					title : '上级领导'
				}, {
					width : '200',
					align : "center",
					field : 'statusExp',
					title : '状态'
				}, {
					width : '100',
					align : "center",
					field : 'roleName',
					title : '角色'
				}

				]
			});
		});

	}
	return oFile;
};

/**
 * 点击取消删除数据
 */
function saveTable(){
    showLoad()
	$.ajax({
		type : "POST",
		url : "/sys/user/importData",
		data : {'tableData':JSON.stringify(tableData)},
		dataType: "json",
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
                closeLoad()
				parent.layer.alert(data.successCount+"["+data.error+"]");

				closeTable();//关闭弹出层
			} else {
                closeLoad()
				parent.layer.alert(data.msg)
			}


		}
	});
}
var loadIndex = void 0;
function showLoad() {
    loadIndex = layer.msg('请稍后...', {
        icon : 16,
        shade : [ 0.5, '#f5f5f5' ],
        scrollbar : false,
        offset : 'auto',
        time : 30000
    });
}

function closeLoad() {
    layer.close(loadIndex);
}

/**
 *  关闭弹出层
 */
function closeTable(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
	parent.reFresh();
}