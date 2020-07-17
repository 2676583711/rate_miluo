$(function(){	
	validateRule();
    initMgr();
    // initCompany();
    $("input:radio[name='role']:first").attr('checked', 'checked');//选中第一个单选框
    $("#siteTree").addClass('hidden');
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

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function initMgr(){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: '/sys/user/getMgr',
		data: {},
		success: function(data){
			var str = '<option value="">-请选择-</option>';
			for(var d of data){
				str += '<option value="'+d.userId+'">'+d.name+'</option>'
			}
			$("#mgrId").html(str);
            $('#mgrId').selectpicker('refresh');
		}
	});
}

function initCompany(){
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/sys/user/getCompany",
		data : {},
		success : function(data) {
			var str ='';
			for(var d of data){
				str += '<option value="'+d.id+'">'+d.name+'</option>';
			}
			$("#pwId").html(str);
			$('#pwId').selectpicker('refresh');
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

function save() {
    $("#roleIds").val($("input:radio[name='role']:checked").val());
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
		url : "/sys/user/save/"+siteIds,
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
        ignore: [],
		rules : {
			name : {
				required : true
			},
			username : {
				required : true,
				minlength : 2,
				remote : {
					url : "/sys/user/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						username : function() {
							return $("#username").val();
						}
					}
				}
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
			agree : "required",
			deptId : {
				required : true
			}
		},
		messages : {

			name : {
				required : icon + "请输入姓名"
			},
			username : {
				required : icon + "请输入您的用户名",
				minlength : icon + "用户名必须两个字符以上",
				remote : icon + "用户名已经存在"
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
			sex :  icon + "请选择性别",
            deptId : icon + "请选择部门"
		}
	})
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


