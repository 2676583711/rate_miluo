$().ready(function() {
    $(".selectpicker").selectpicker({
        noneSelectedText: '  ' //默认显示内容
    });
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
        url : "/airSite/saveDevice",
        data :$("#signupForm").serialize(),
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");


                // parent.reFresh();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引

                parent.btnSearchSubmit();
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function finishTo() {
    var  curPage=$("#curPage").val();
    var  id=$("#siteId").val();
    location.href ='/airSite/show/' + id +'/'+ curPage;
}

var image = '';
function selectImage(file) {
    if (!file.files || !file.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function(evt) {
        document.getElementById('image').src = evt.target.result;
        image = evt.target.result;
    }
    reader.readAsDataURL(file.files[0]);
}

//打开文件
function inputImage() {
    $("#file").click();
}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
                required : true
            },
            equmentId : {
                required : true,
                remote: {
                    url: '/airSite/validateEquId',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        equmentId: function () {
                            return $("#equmentId").val()
                        }
                    }
                },
            },
            type : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入设备名称"
            },
            equmentId : {
                required : icon + "请输入设备编号",
                remote: icon + "设备编码已存在"
            },
            type : {
                required : icon + "请选择设备类型"
            }
        },
        errorPlacement: function (error, element) { //指定错误信息位置
            if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                var eid = element.attr('name'); //获取元素的name属性
                /*error.appendTo(element.parent().parent());*/ //将错误信息添加当前元素的父结点后面
                $(element).parent().attr("data-toggle",  "popover");
                $(element).parent().attr("data-placement",  "top");
                $(element).parent().popover({
                    html: true,
                    content: error
                });
                $(element).parent().popover("show");
            } else {
                $(element).attr("data-toggle",  "popover");
                $(element).attr("data-placement",  "top");
                //$(element).attr("data-content",  $(error).text());
                $(element).popover({
                    html: true,
                    content: error
                });
                $(element).popover("show");
                // error.insertAfter(element);
                /* $(element).attr("title",  $(error).text()).tooltip("show");*/
            }
        },
        success: function (error , element){
            $(element).parent().popover("hide");{
                $(element).popover("hide");
            }
        }
    })
}