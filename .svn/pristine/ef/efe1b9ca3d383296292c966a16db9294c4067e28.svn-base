$(function(){
	$("#formSwitch").click(function(){
		save();
	});
});


function save() {

	$.ajax({
		cache : true,
		type : "POST",
		url : "/smsIndex/saveSwitch",
		data : $('#addForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}