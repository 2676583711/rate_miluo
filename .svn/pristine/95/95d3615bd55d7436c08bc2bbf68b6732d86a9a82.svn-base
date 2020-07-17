$().ready(function() {
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
        dataType : "json",
        url : "/monitor/video/update",
        data : $("#signupForm").serialize(),
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
            siteId : {
                required : true,
            },
            vidiconName : {
                required : true,
            },
            supplier : {
                required : true
            },
            seriesNumber : {
                required : true,
            },
            ownerId : {
                required : true,
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
        },
        messages : {
            siteId : {
                required : icon + "请选择站点",
            },
            vidiconName : {
                required : icon + "请填写摄像机名称",
            },
            supplier : {
                required : icon + "请填写供应商"
            },
            seriesNumber : {
                required : icon + "请填写设备序列号",
            },
            ownerId : {
                required : icon + "请选择设备所有者",
            },
            rtmphdUrl : {
                required : icon + "地址不能为空",
            },
            rtmpUrl : {
                required : icon + "地址不能为空",
            },
            hlshdUrl : {
                required : icon + "地址不能为空",
            },
            hlsUrl : {
                required : icon + "地址不能为空",
            },
            ezopenhdUrl : {
                required : icon + "地址不能为空",
            },
            ezopenUrl : {
                required : icon + "地址不能为空",
            },
            ezopenPlaybackUrl : {
                required : icon + "地址不能为空",
            }
        }
    })
}
