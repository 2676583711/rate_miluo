function initGradeChangeContent() {
    loadGradeChange();
}
$(window).resize(function () {
    $('#gradeChangeTable').bootstrapTable('resetView');
});
function loadGradeChange() {
    $('#gradeChangeTable').bootstrapTable('destroy');
    $('#gradeChangeTable').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url : "/statistic/excellent/list4",
        iconSize : 'outline',
        height: getHeight(),
        // toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        singleSelect : false, // 设置为true将禁止多选
        pageSize : 25, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        sortable : true, // 是否启用排序
        showColumns : false, // 是否显示内容下拉框（选择显示的列）
        fixedColumns: true,
        fixedNumber: 3,
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"Server"
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                dateType: $("input[type='radio'][name='time']:checked").val(), //获取日期类型
                startMonth: $("#monthStartTime").val(), // 开始月份
                endMonth: $("#monthEndTime").val(),     // 结束月份
                startYear: '',   // 开始年份
                endYear: '',       // 结束年份
                startDay: $("#dayStartTime").val(),     // 开始日期
                endDay: $("#dayEndTime").val(),         // 结束日期
                jd1: '',               // 开始季度
                jd2: '',               // 结束季度

                siteCodes:siteCodes.join()
            };
        },
        onLoadSuccess:function(data){

            //drawEchars(station,excellentDays,goodDays,mildDays,moderateDays,severeDays,seriousnessDays);
        },
        columns: [{
            field: 'siteName',
            title: '站点名称',
            align: 'center',
            width: '160px',
            valign: 'middle'

        },{
            field: 'times',
            title: '时间',
            width: '160px',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var s= $("input[type='radio'][name='time']:checked").val();
                if (value) {
                    if (s=='1'){
                        return value.substr(0,7);;
                    } else if (s=='2'){
                        var as = value.substr(0,4);
                        var as2 = value.substr(5,7);
                        if (1<=as2<4){
                            return as+"年第一季度"
                        }else if (4<=as2<7){
                            return as+"年第二季度"
                        } else if (7<=as2<10){
                            return as+"年第三季度"
                        }else {
                            return as+"年第四季度"
                        }

                    } else if (s=='3'){

                        return value.substr(0,4)+"年";

                    } else {
                        return $("#dayStartTime").val()+"至"+$("#dayEndTime").val() ;
                    }
                }
            }
        },{
            field: 'excellentAndGood',
            title: '优良天数(天) ',
            align: 'center',
            width: '160px',
            valign: 'middle'
        },{
            field: 'excellnotentAndGood',
            title: '污染天数(天) ',
            align: 'center',
            width: '160px',
            valign: 'middle'
        },{
            field: 'validDays',
            title: '有效天数(天)',
            align: 'center',
            width: '160px',
            valign: 'middle'
        },{
            field: 'sumDays',
            title: '总天数(天) ',
            align: 'center',
            width: '150px',
            valign: 'middle'
        },{
            field: 'excellentAndGoodRatio',
            title: '本区间优良天数比例(%)',
            align: 'center',
            width: '200px',
            valign: 'middle'
        },{
            field: 'oldRatio',
            title: '上年同期优良天数比例(%)',
            align: 'center',
            width: '200px',
            valign: 'middle'
        },{
            field: 'newoldRatio',
            title: '本区间较上年同期(%)',
            align: 'center',
            width: '160px',
            valign: 'middle'
        }]
    });
}

function exportExcelGradeChange() {
    layer.confirm('确定要导出当前记录？', {
        btn: ['确定', '取消']
    }, function (index) {
        layer.close(index);
        var url = "";
        var a = "/statistic/excellent/exportExcel4?dateType=" + $("input[type='radio'][name='time']:checked").val();
        var d = "&startMonth=" + $("#monthStartTime").val() ;
        var e = "&endMonth=" + $("#monthEndTime").val() ;
        var f = "&startDay=" + $("#dayStartTime").val() ;
        var g = "&endDay=" +$("#dayEndTime").val();
        var h = "&siteCodes=" + siteCodes.join();
        window.location.href = url+a+d+e+f+g+h;
    });
}
