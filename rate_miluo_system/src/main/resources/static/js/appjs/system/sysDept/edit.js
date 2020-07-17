$().ready(function() {
	initDelFlag();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function initDelFlag(){
	var del = $("#del").val();
	var delFlag = $("#delFlag").find("option");
	for (var j = 1; j < delFlag.length; j++) {
		if ($(delFlag[j]).val() == del) {
			$(delFlag[j]).attr("selected", "selected");
		}
	}
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/sysDept/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}