$(function(){
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

$(function(){
    $("input:radio[name='role']").change(function () {
        var _value = this.value;
        if(_value == 79){
            $("#siteTree").removeClass('hidden');
            $.siteTree.hideTab(["-1","-2","-4"]);
            $.siteTree.showTab("-3");
        }else if(_value == 82){
            $("#siteTree").removeClass('hidden');
            $.siteTree.hideTab(["-1","-2","-3"]);
            $.siteTree.showTab("-4");
		}else if(_value == 78){
            $("#siteTree").removeClass('hidden');
            $.siteTree.hideTab(["-2","-3","-4"]);
            $.siteTree.showTab("-1");
        }else if(_value == 81){
            $("#siteTree").removeClass('hidden');
            $.siteTree.hideTab(["-1","-3","-4"]);
            $.siteTree.showTab("-2");
        }else{
            $("#siteTree").addClass('hidden');
        }
    });
});

function update() {
	// $("#roleIds").val(getCheckedRoles());
    $("#roleIds").val($("input:radio[name='role']:checked").val());
    var roleId = $("#roleIds").val();
    var siteIds;
    if(!$("#siteTree").hasClass('hidden')){
        siteIds = $.siteTree.siteCodes().join(",");
        if(siteIds.trim().length==0){
            layer.alert("请选中站点权限");
            return;
        }
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/user/update/"+siteIds,
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function getCheckedRoles() {
	var adIds = "";
	$("input:radio[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function setCheckedRoles() {
	var roleIds = $("#roleIds").val();
	alert(roleIds);
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			username : {
				required : true,
				minlength : 2
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			email : {
				required : true,
				email : true
			},
			sex : {
				required : true
			},
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
			agree : "required"
		},
		messages : {

			name : {
				required : icon + "请输入姓名"
			},
			username : {
				required : icon + "请输入您的用户名",
				minlength : icon + "用户名必须两个字符以上"
			},
			password : {
				required : icon + "请输入您的密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirm_password : {
				required : icon + "请再次输入密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
			},
			email : icon + "请输入您的E-mail",
			sex :  icon + "请选择性别"
		}
	});
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}