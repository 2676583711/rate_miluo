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
        type : "POST",
        url : "/airSite/saveSite",
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

// 改变类型
function typeChange(){
    var type = $("#siteType").val();
    if(type == '3'){
        $('#airD').addClass('hidden');
        $('#pollutantD').removeClass('hidden');
    }else{
        $('#airD').removeClass('hidden');
        $('#pollutantD').addClass('hidden');
    }
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
                required : true,
                maxlength:32
            },
            // siteCode : {
            //     required : true,
            //     maxlength:32
            // },
            areaType : {
                required : true
            },
            longitude : {
                required : true,
                maxlength:32,
                number: true
            },
            latitude : {
                required : true,
                maxlength:32,
                number: true
            },
        },
        messages : {
            name : {
                required : icon + "请输入站点名称",
                maxlength: icon + "长度不能超过32位"
            },
            // siteCode : {
            //     required : icon + "请输入站点编码",
            //     maxlength: icon + "长度不能超过32位"
            // },
            areaType : {
                required : icon + "请选择行政区划"
            },
            longitude : {
                required : icon + "请输入经度",
                maxlength: icon + "长度不能超过32位"
            },
            latitude : {
                required : icon + "请输入纬度",
                maxlength: icon + "长度不能超过32位"
            },

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