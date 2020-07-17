$(function () {
    validateRule();
});
var prefix = "/index";
$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});


function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/alarm/save",
        data: $('#dbForm').serialize(),// 你的formid
        async: false, // 是否异步?
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg);
            }
        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#dbForm").validate({
        rules: {
            taskName: {
                required: true
            },
            taskType: {
                required: true
            },
            fzrName: {
                required: true
            },
            startTime: {
                required: true
            },
            finishTime: {
                required: true
            }
        },
        messages: {
            taskName: {
                required: icon + "请输入任务名称"
            },
            taskType: {
                required: icon + "请输入任务类型"
            },
            fzrName: {
                required: icon + "该站点无负责人，请先添加"
            },
            startTime: {
                required: icon + "请输入开始时间"
            },
            finishTime: {
                required: icon + "请输入预计结束时间"
            }
        }
    })
}

function deptChange() {
    // 选中部门
    var deptId = $("#deptId").val();
    $("#dutyPerson").find("option:not(:first)").remove(); //删除除第一项外的全部
    $.ajax({
        url: "/index/getPolluteDataUser", //获取数据的请求
        async: false,
        cache: false,
        method: "post",
        data: {
            "deptId": deptId
        },
        success: function (data) {
            if (!data) {
                return;
            }
            var users = data;
            var options = "";
            for (var i = 0; i < users.length; i++) {
                options += "<option value='" + users[i].userId + "'>" + users[i].name + "</option>";
            }
            $("#dutyPerson").append(options);
        }
    })
}