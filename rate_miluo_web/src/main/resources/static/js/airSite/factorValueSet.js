$(function () {
    $("input[name='needAlarm']").on('click',function (e) {
        let formId = e.target.form.attributes.id.value;
        let rv = e.target.value;
        // console.log(formId,rv);
        if (rv == '0') {
            $("#"+formId + " div.input-limit").addClass('hidden');
        } else if (rv == '1') {
            $("#"+formId + " div.input-limit").removeClass('hidden');
        }
    });
});

function factorValueSetForm() {
    layer.confirm('确定修改报警参数？', {
       btn : ['确定', '取消']
    }, function () {
        var paramList = new Array();
        var size = parseInt($('#size').val());
        var equId = $('#equId').val();
        for(var i=1; i<=size; i++){
            let arr = $('#factorValueSetForm-'+i).serializeArray();
            var jsonStr = "";
            jsonStr += '{';
            for (var j=0; j<arr.length; j++) {
                jsonStr += '"' + arr[j].name + '":"' + arr[j].value + '",';
            }
            jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
            jsonStr += '}';
            var jsonObj = JSON.parse(jsonStr); //将拿到的键值对转换位json对象
            paramList[i-1] = jsonObj;
        }
        // console.log(paramList);
        $.ajax({
            type : "POST",
            url : "/airSite/save/factorValueSet",
            data : {"paramList":JSON.stringify(paramList), "equId":equId},
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                parent.layer.msg("操作成功");
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                // parent.btnSearchSubmit();
                parent.layer.close(index);
            }
        });
    });
}

function clearNoNum(obj) {
    obj.value = obj.value.replace(/[\u4e00-\u9fa5]+/g, ""); //验证非汉字
    obj.value = obj.value.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", "."); //只保留第一个小数点, 清除多余的
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d{6}).*$/, '$1$2.$3'); //只能输入6个小数
}