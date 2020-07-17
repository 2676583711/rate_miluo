$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

	$.ajax({
		cache : true,
		type : "POST",
		dataType : "json",
		url : "/vidicon/saveVidicon",
		data : $("#signupForm").serialize(),
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reFresh();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function validateRule() {
	$("#signupForm").validate({
		rules : {
			vidiconName : {
				required : true
			},
			supplier : {
				required : true
			},
			seriesNumber : {
				required : true
			},
			ownerId : {
				required : true
			},
			vidiconLocation : {
				required : true
			},
			rtmphdUrl : {
				required : true
			},
			rtmpUrl : {
				required : true
			},
			hlshdUrl : {
				required : true
			},
			hlsUrl : {
				required : true
			},
			ezopenhdUrl : {
				required : true
			},
			ezopenUrl : {
				required : true
			},
			ezopenPlaybackUrl : {
				required : true
			}

		}
	})
}
