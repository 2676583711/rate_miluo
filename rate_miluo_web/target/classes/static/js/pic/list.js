$(function () {
    menulists('/module/pic/list', 10, 1);
});

function menulists(url, pageSize, pageNumber) {
    // var page = curr || 1; //向服务端传的参数
    $.ajax({
        type: "Get",
        url: url,
        data: {
            pageSize: pageSize,
            pageNumber: pageNumber,
            siteCodes: siteCodes,
            dateCondition: $("#dateCondition").val()
        },
        success: function (json) {
            if (!json || json.total==0) {
                $("#pics").empty();
                // $("#pageTool").empty();
                // return;
            }
            var rows = json.rows;
            var pages = json.total / 10;
            //拼接数据
            integratingData(rows);
            //显示分页
            layui.use(['laypage', 'layer'], function () {
                var laypage = layui.laypage;
                //完整功能
                laypage.render({
                    elem: 'pageTool',
                    curr: pageNumber || 1,
                    count: json.total,
                    layout: ['count', 'prev', 'page', 'next', 'skip'],
                    limit: 10,
                    jump: function (obj, first) { //触发分页后的回调
                        if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                            menulists(url, 10, obj.curr);
                        }
                    }
                });
            });
        }
    });

};

function reFresh() {
    location.href = "/module/pic";
}

function reLoad() {
    menulists('/module/pic/list', 10, 1);
}

function integratingData(data) {
    if (data.length < 1) {
        return;
    }
    $("#pics").empty();
    var html = '';
    for (var i = 0; i < data.length; i++) {
        var pic = data[i];
        // <span> <input type="checkbox">
        //     <img src="${pic.url}" style="width:24.8%">
        // </span>
        html += "<div style='display:inline-block;width:24%;padding: 3px;position: relative'>" +
            "<span style='display: block; text-align: center;'>"+pic.siteName + " "+ pic.printscreenDate+"</span>"+
            "<img src='" + pic.url + "' style='width:100%'>" +
            "<input type='checkbox' style='z-index: 999;position: absolute;left:95%;top: 91%;'>" +
            "<input type='hidden' value='" + pic.fileId + "'></div>";
    }
    $("#pics").append(html);
    //给复选框设置宽高
    // var checks = $("#pics input[type=checkbox]");
    // var img = $("#pics img").eq(0);
    // //位置偏移量
    // var w = img.width() - 3;
    // var h = img.height() - 3;
    // //设置
    // for (var i = 0; i < checks.length; i++) {
    //     var check = checks.eq(i);
    //     check.offset({top: h, left: w});
    // }

    if ($("#pics div").length > 0) {
        var gallery = new Viewer(document.getElementById('pics'));
    }
}

function batchRemove() {
    var checks = $("#pics input:checkbox:checked+input");
    if (checks.length == 0) {
        layer.msg("请选择要删除的图片");
        return;
    }
    layer.confirm("确认要删除选中的'" + checks.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(checks, function (i, row) {
            ids[i] = checks.eq(i).val();
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: '/module/pic/remove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    });
}

function selectAll() {
    //如果是全选  就全部取消  如果不是全选  就全部选中
    var checks = $("#pics input[type=checkbox]");
    var isSelectedAll = true;
    for (var i = 0; i < checks.length; i++) {
        var check = checks.eq(i);
        if (check.is(":checked") == false) {
            isSelectedAll = false;
            break;
        }
    }
    //如果没有全选
    if (isSelectedAll == false) {
        for (var i = 0; i < checks.length; i++) {
            var check = checks.eq(i);
            check.prop("checked", true);
        }
    } else {
        for (var i = 0; i < checks.length; i++) {
            var check = checks.eq(i);
            check.prop("checked", false);
        }
    }
}