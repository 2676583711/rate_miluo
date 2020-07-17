// let col = [];
function initGradeContent() {
    // initCol();
    loadGrade();
}

// 重置浏览器大小，保证表格列不偏移
$(window).resize(function () {
    $('#gradeTable').bootstrapTable('resetView');
});

function loadGrade() {
    let startTime = $('.time-type-start').not('.hidden').val();
    let endTime = $('.time-type-end').not('.hidden').val();
    let timeType = $("input[name='time']:checked").val();  // 时间类型
    $('#gradeTable').bootstrapTable('destroy').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: prefix + "/grade/list",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        height: getHeight(),
        // toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        pageSize: 25, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        // showHeader: false,
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect: true, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        // search : true, // 是否显示搜索框
        // showColumns : true, // 是否显示内容下拉框（选择显示的列）
        fixedColumns: true,
        fixedNumber: 3,
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNumber: (params.offset / params.limit) + 1,
                startDate: startTime,
                endDate: endTime,
                timeType: timeType,
                siteCodes: siteCodes.join(),
            };
        },
        // responseHandler: function(res) {
        //     return {
        //         "rows" : res.rows,
        //         "total" : res.total
        //     }
        // },
        columns: [[{
            field: 'siteName',
            title: '站点名称',
            align: 'center',
            width: 160,
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'dataTime',
            title: '时间段',
            align: 'center',
            valign: 'middle',
            width: 180,
            colspan: 1,
            rowspan: 2,
            formatter: function (value) {
                if (timeType == '2' && value != null) {
                    let year = value.substr(0,4);
                    let season = value.substr(4,5);
                    value = year + '年' + seasonF[season];
                }
                return value;
            }
        }, {
            title: '空气质量类别分布（天数）',
            align: 'center',
            valign: 'middle',
            colspan: 6,
            rowspan: 1,
            width: 600
        }, {
            title: '首要污染物分布（天数）',
            align: 'center',
            valign: 'middle',
            colspan: 6,
            rowspan: 1,
            width: 480
        },{
            field: 'okRate',
            title: '优良率（%）',
            align: 'center',
            valign: 'middle',
            width: 120,
            colspan: 1,
            rowspan: 2,
            formatter: function (value) {
                return (value * 100).toFixed(1);
            }
        }, {
            field: 'okDays',
            title: '优良天数',
            align: 'center',
            valign: 'middle',
            width: 100,
            colspan: 1,
            rowspan: 2
        }, {
            field: 'validDays',
            title: '有效天数',
            align: 'center',
            valign: 'middle',
            width: 100,
            colspan: 1,
            rowspan: 2
        }, {
            field: 'totalDays',
            title: '总天数',
            align: 'center',
            valign: 'middle',
            width: 80,
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25OkDays',
            title: 'PM<sub>2.5</sub>优良天数',
            align: 'center',
            valign: 'middle',
            width: 120,
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25ValidDays',
            title: 'PM<sub>2.5</sub>有效天数',
            align: 'center',
            valign: 'middle',
            width: 120,
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25OkDaysRate',
            title: 'PM<sub>2.5</sub>优良天数比例（%）',
            align: 'center',
            valign: 'middle',
            width: 200,
            colspan: 1,
            rowspan: 2,
            formatter: function (value) {
                return (value * 100).toFixed(1);
            }
        }],[{
            field: 'greatDays',
            title: '优',
            align: 'center',
            valign: 'middle',
            width: 100
        }, {
            field: 'goodDays',
            title: '良',
            align: 'center',
            valign: 'middle',
            width: 100
        }, {
            field: 'mildDays',
            title: '轻度污染',
            align: 'center',
            valign: 'middle',
            width: 100
        }, {
            field: 'middleDays',
            title: '中度污染',
            align: 'center',
            valign: 'middle',
            width: 100
        }, {
            field: 'severeDays',
            title: '中度污染',
            align: 'center',
            valign: 'middle',
            width: 100
        }, {
            field: 'worstDays',
            title: '严重污染',
            align: 'center',
            valign: 'middle',
            width: 100
        }, {
            field: 'pm25FirstDays',
            title: 'PM<sub>2.5</sub>',
            align: 'center',
            valign: 'middle',
            width: 80
        }, {
            field: 'pm10FirstDays',
            title: 'PM<sub>10</sub>',
            align: 'center',
            valign: 'middle',
            width: 80
        }, {
            field: 'o3FirstDays',
            title: 'O<sub>3</sub>',
            align: 'center',
            valign: 'middle',
            width: 80
        }, {
            field: 'no2FirstDays',
            title: 'NO<sub>2</sub>',
            align: 'center',
            valign: 'middle',
            width: 80
        }, {
            field: 'so2FirstDays',
            title: 'SO<sub>2</sub>',
            align: 'center',
            valign: 'middle',
            width: 80
        }, {
            field: 'coFirstDays',
            title: 'CO',
            align: 'center',
            valign: 'middle',
            width: 80
        }]]
    });
}

//导出Excel
function exportExcelGrade() {

    let startTime = $('.time-type-start').not('.hidden').val();
    let endTime = $('.time-type-end').not('.hidden').val();
    let timeType = $("input[name='time']:checked").val();  // 时间类型

    layer.confirm('确定要导出当前记录？', {
        btn: ['确定', '取消']
    }, function (index) {
        layer.close(index);
        var url = "";
        url += prefix + "/exportExcelGrade?"
            + "startDate=" + startTime
            + "&endDate=" + endTime
            + "&timeType=" + timeType
            + "&siteCodes=" + siteCodes.join()
        window.location.href = url
    });
}