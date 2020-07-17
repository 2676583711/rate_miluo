$(function () {
    $("input.datepicker").each(function (_i, _c) {
        var _that = $(_c);
        var _format = _that.data("date-format");
        if (_format == undefined || _format == "")
            _format = "yyyy-mm-dd hh:ii:ss";
        var _minView = 2;
        if (_format.indexOf("ii") > 0) _minView = 0;
        else if (_format.indexOf("hh") > 0) _minView = 1;
        else if (_format.indexOf("dd") > 0) _minView = 2;
        _that.datetimepicker({ language: 'zh-CN', autoclose: true, minView: _minView, todayHighlight: true, fontAwesome: true });
    });
});

var CommonVariable = {
    RegionCodeOfCity: '440300000000'//城市代码
};

function serializeObject(serArray) {
    var o = {};
    $.each(serArray, function (_i, _o) {
        o[_o.name] = _o.value;
    });
    return o;
}

/**
 * 打开一个弹出窗口(在顶部窗口)
 * @method
 * @param {string} title 弹框标题
 * @param {string} url 弹框中要显示的页面路径
 * @param {number} width 弹框宽度(数值,传null则根据窗口自动计算)
 * @param {number} height 弹框高度(数值,传null则根据窗口自动计算)
 * @param {function} callback 关闭弹框时的回调
 */
function openTopDialog(title, url, width, height, callback) {
    var _width = (width == undefined || width == null) ? ($(top.window).width() - 240) : width;
    var _height = (height == undefined || height == null) ? ($(top.window).height() - 140) : height;
    var win = top.$.dialog({
        icon: 'fa fa-clipboard',
        title: title,
        boxWidth: _width + "px",
        theme: 'bootstrap',
        type: 'blue',
        animation: 'scale',
        closeIcon: true,
        useBootstrap: false,
        escapeKey: true,
        backgroundDismiss: false,
        smoothContent:false,
        content: '<iframe src="' + url + '" style="border-width:0px; overflow:auto; width:' + (_width - 50) + 'px; height:' + _height + 'px;"></iframe>',
        onContentReady: function () {
            console.log('onContentReady');
        },
        contentLoaded: function (data, status, xhr) {
            //console.log('contentLoaded: ' + status);
        }
        ,onOpenBefore: function () {
            //console.log('onOpenBefore');
        },
        onOpen: function () {
            //console.log('onOpen');
        },
        onClose: function () {
            callback();//窗口关闭后调用回调函数
        },
        onDestroy: function () {
            //console.log('onDestroy');
        }
    });
    top.openedWindows.push(win);
}

/**
 * 打开一个弹出窗口（在当前窗口）,返回窗口对象
 * @method
 * @param {string} title 弹框标题
 * @param {string} url 弹框中要显示的页面路径
 * @param {number} width 弹框宽度(数值,传null则根据窗口自动计算)
 * @param {number} height 弹框高度(数值,传null则根据窗口自动计算)
 * @param {function} callback 关闭弹框时的回调
 */
function openDialog(title, url, width, height, callback) {
    var _width = (width == undefined || width == null) ? ($(window).width() - 240) : width;
    var _height = (height == undefined || height == null) ? ($(window).height() - 140) : height;
    var win = $.dialog({
        icon: 'fa fa-clipboard',
        title: title,
        boxWidth: _width + "px",
        theme: 'bootstrap',
        type: 'blue',
        animation: 'scale',
        closeIcon: true,
        useBootstrap: false,
        escapeKey: true,
        backgroundDismiss: false,
        content: '<iframe src="' + url + '" style="border-width:0px; overflow:auto; width:' + (_width - 50) + 'px; height:' + _height + 'px;"></iframe>',
        onContentReady: function () {
            console.log('onContentReady');
        },
        contentLoaded: function (data, status, xhr) {
            alerconsole.log('contentLoaded: ' + status);
        },
        onOpenBefore: function () {
            console.log('onOpenBefore');
        },
        onOpen: function () {
            console.log('onOpen');
        },
        onClose: function () {
            callback();
        },
        onDestroy: function () {
            console.log('onDestroy');
        }
    });
    return win;
}


/**
 * 填充表单数据
 * @method
 * @param {object} $form 表单的jquery对象
 * @param {object} jsonData 用于填充表单的json数据
 */
function fillFormData($form, jsonData) {
    var controls = $form.find("input,textarea,select");
    if (jsonData != null && jsonData != undefined) {
        controls.each(function (i, c) {
            var $c = $(c);
            var _key = $c.attr("name");
            var _val = jsonData[_key];
            if (c.tagName == "INPUT") {
                if ($c.is(":checkbox")) {
                    if (_val != null && _val != undefined) {
                        var values = _val.split(',');
                        if (values.indexOf($c.val()) >= 0)
                            $c.attr("checked", checked);
                    }
                } else if ($c.is(":radio")) {
                    if (_val === $c.val() || (_val === true ? "1" : "0") == $c.val() || (_val === true ? "true" : "false") == $c.val().toLowerCase())
                        $c.attr("checked", "checked");
                } else {
                    $c.val(_val);
                }
            } else if (c.tagName == "TEXTAREA") {
                $c.val(_val);
            } else if (c.tagName == "SELECT") {
                if (_val === true || _val === false) {
                    $c.children("option[value='" + (_val === true ? 1 : 0) + "']").attr("selected", true);
                    $c.children("option[value='" + (_val === true ? "true" : "false") + "']").attr("selected", true);
                }
                else
                    $c.children("option[value='" + _val + "']").attr("selected", true);
                $c.val(_val);
            }
        });
    }
}

/**
 * 加载表单数据
 * @method
 * @param {object} $form 表单的jquery对象
 * @param {string} url 请求路径
 * @param {object} data 请求参数（json）
 * @param {function} callback 回调，参数为返回的json格式表单数据
 */
function loadFormData($form, url, data, callback) {
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        data: data,
        success: function (res) {
            if (res.status == 200) {
                fillFormData($form, res.data);
                if (callback != undefined && typeof (callback) == "function")
                    callback(res.data);
            }
            else
                bootbox.alert(res.message);
        }
    });
}

/**
 * 新增一条数据
 * @method
 * @param {any} $form 表单的jquery对象
 * @param {any} url 请求路径
 */
function Add($form, url) {
    $form.ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        timeout: 6000,
        beforeSubmit: function () { return $form.valid(); },
        beforeSerialize: function (form, options) { console.log(JSON.stringify(options) + "----"); },
        success: function (res) {
            if (res.status == 200) {
                top.Messenger().post({
                    message: '新增成功',
                    type: 'success',
                    showCloseButton: true,
                    hideAfter: 2
                });
                //关闭弹窗
                top.closeChildWindow();
            } else {
                top.Messenger().post({
                    message: '新增失败',
                    type: 'error',
                    showCloseButton: true,
                    hideAfter: 2
                });
            }
        }
    });
}

/**
 * 编辑一条数据
 * @method
 * @param {any} $form 表单的jquery对象
 * @param {any} url 请求路径
 * @param {any} id 编辑数据的主键(ID)值
 */
function Edit($form, url, id) {
    console.log(url);
    console.log($form);
    $form.ajaxSubmit({
        url: url,
        type: 'put',
        dataType: 'json',
        timeout: 6000,
        beforeSubmit: function () { return $form.valid(); },
        beforeSerialize: function (form, options) { form.append($("<input>").attr({ type: "hidden", name: "ID" }).val(id)); },
        success: function (res) {
            if (res.status == 200) {
                top.Messenger().post({
                    message: '修改成功', type: 'success', showCloseButton: true, hideAfter: 2
                });
            } else {
                top.Messenger().post({
                    message: '修改失败', type: 'error', showCloseButton: true, hideAfter: 2
                });
            }
        }
    });
}

/**
 * 删除数据
 * @method
 * @param {string} url 请求路径
 * @param {object} rows 从datagrid中获取到的选中行
 * @param {function} callback 删除成功后的回调
 */
function Delete(url, rows, callback) {
    if (rows.length > 0) {
        $.confirm({
            title: '提示',
            content: '确定要删除选中的数据吗？',
            buttons: {
                confirm: {
                    text: '确定',
                    action: function () {
                        var _count = 0;//计数器
                        $.each(rows, function (_i, _row) {
                            $.ajax({
                                url: url + "?id=" + _row.ID,
                                type: 'delete',
                                dataType: 'json',
                                timeout: 3000,
                                success: function (res) {
                                    if (res.status == 200) {
                                        _count++;
                                        top.Messenger().post({
                                            message: '删除了一条数据', type: 'success', showCloseButton: true, hideAfter: 2
                                        });
                                        //删除完成后刷新表格
                                        if (rows.length == _count)
                                            callback();
                                    }
                                    else {
                                        top.Messenger().post({
                                            message: '删除失败', type: 'error', showCloseButton: true
                                        });
                                    }
                                }
                            });
                        });
                    }
                },
                cancel: {
                    text: '取消',
                    action: function () {

                    }
                }
            }
        });

    } else {
        bootbox.alert("请先选择要删除的行");
    }
}