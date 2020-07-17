var image3 = $("#imgId3").val();
$().ready(function() {
    if ($.validator) {
        $.validator.prototype.elements = function () {
            var validator = this,
                rulesCache = {};
            return $(this.currentForm)
                .find("input, select, textarea")
                .not(":submit, :reset, :image, [disabled]")
                .not(this.settings.ignore)
                .filter(function () {
                    if (!this.name && validator.settings.debug && window.console) {
                        console.error("%o has no name assigned", this);
                    }
                    //注释这行代码
                    // select only the first element for each name, and only those with rules specified
                    //if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
                    //    return false;
                    //}
                    rulesCache[this.name] = true;
                    return true;
                });
        }
    }
    $.extend($.validator.messages, {
        required: "必选项"
    });
    validateRule();
    $("#workerId").change(function () {
        var name = $("#workerId option:selected").text();
        var idCode = $("#workerId option:selected").attr("idCode");
        $("#idCode").val(idCode);
        $("#name").val(name);
    });
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});

var control = $("#input-b3");
control.fileinput({
    language: 'zh', //设置语言
    uploadUrl:"/task/agents/upload",  //上传地址
    uploadAsync: true, //默认异步上传
    showUpload: true, //是否显示上传按钮
    showPreview: true, //是否显示预览
    showCaption: false,//是否显示标题
    showRemove: true,
    dropZoneEnabled: false,
    allowedPreviewTypes: ['image'],
    allowedFileTypes: ['image'],
    allowedFileExtensions: ['jpg', 'png', 'bmp', 'jpeg'],
    //uploadExtraData:{pwId : ""},
    maxFileSize: 1024 * 1024 * 1024,
    autoReplace: false,
    layoutTemplates: {
        actionDelete: '', //去除上传预览的缩略图中的删除图标
        actionUpload: ''//去除上传预览缩略图中的上传图片；
        //actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
    }
}).on("fileuploaded", function (event, data) {
//    image += data.response;
    if(undefined != data.response.image3 && data.response.image3 != null){
        image3 += data.response.image3;
        $("#imgId3").val(image3);
    }
}).on('fileerror', function (event, data, msg) {
    alert("文件上传失败");
})

var saveFlag = true;
function save() {
    if(saveFlag) {
        saveFlag = false;
        var tempImg3 = $("#input-b3").val();
        if(tempImg3.length > 0) {
            layer.alert("您有图片未上传！");
            saveFlag = true;
        } else {
            $.ajax({
                type: "POST",
                url: "/task/agents/update",
                data: $('#signupForm').serialize(),// 你的formid
                error: function (request) {
                    parent.layer.alert("Connection error");
                    saveFlag = true;
                },
                success: function (data) {
                    parent.layer.msg("操作成功");
                    parent.reLoad();
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                }
            });
        }
    }
}

function removeWorkerPic(id, type){
    var idx1 = layer.confirm('是否删除此图片？（会直接删除不可恢复！）', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type : "get",
            url : "/task/agents/deleteImg?imgId="+ id +"&miluoTaskInfoId=" + $("#miluoTaskInfoId").val()+"&type=" + type,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                if(data == "error"){
                    layer.msg("删除失败");
                }else {
                    parent.layer.msg("操作成功");
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.window['layui-layer-iframe' + index].location.reload();
                }

            }
        });
        layer.close(idx1);
    });
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            timeJobType : {
                required : true
            },
            assistManagement : {
                required : true
            },
            activist : {
                required : true
            },
            volunteer : {
                required : true
            },
            instructor : {
                required : true
            },
            legalWorker : {
                required : true
            }
        }
    });
}