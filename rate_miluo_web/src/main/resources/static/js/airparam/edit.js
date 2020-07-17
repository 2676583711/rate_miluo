var prefix = "/module/airparam"
$(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			laryer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				setTimeout("back()",50);
			} else {
				layer.alert(data.msg)
			}
		}
	});
}

//返回
function back(){
	 parent.reLoad();
	parent.layer.msg("操作成功");
    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
   
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			pluName : {
				required : true
			},
			avgTime : {
				required : true
			},
			conLimit1 : {
				required : true,
				number : true
			},
			conLimit2 : {
				required : true,
				number : true
			},
			conLimit3 : {
				required : true,
				number : true
			},
			conLimit4 : {
				required : true,
				number : true
			},
			unit : {
				required : true
			}
		},
		messages : {
			pluName :  icon + "请输入名称",
			conLimit1 : {
				number : icon + "请输入一个数值"
			},
			conLimit2 : {
				number : icon + "请输入一个数值"
			},
			conLimit3 : {
				number : icon + "请输入一个数值"
			},
			conLimit4 : {
				number : icon + "请输入一个数值"
			},
			conLimit5 : {
				number : icon + "请输入一个数值"
			}
		}
	})
}