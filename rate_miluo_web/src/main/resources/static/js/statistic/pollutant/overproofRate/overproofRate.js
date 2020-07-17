function initOverproofRateContent() {
    loadOverproofRate();
}
$(window).resize(function () {
    $('#overproofRateTable').bootstrapTable('resetView');
});
function loadOverproofRate() {

    let startTime = $('.time-type-start').not('.hidden').val();
    let endTime = $('.time-type-end').not('.hidden').val();
    let stationType = $("input[name='stationType']:checked").val();  // 站点类型
    let timeType = $("input[name='time']:checked").val();  // 时间类型

    $('#overproofRateTable').bootstrapTable('destroy').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: prefix + "/overproofRate/list",
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
                timeType: $("input[name='time']:checked").val(),  // 时间类型
                siteCodes: siteCodes.join(),
                stationType: stationType,
                dataType: $("input[name='dataType']:checked").val(),  // 标况，实况
                examineType: $("input[name='examineType']:checked").val(),  // 原始，审核
            };
        },
        onLoadSuccess: function() {
            if (stationType == '4') {
                $('#overproofRateTable').bootstrapTable('hideColumn', 'siteName')
            }
        },
        columns: [{
            field: 'areaName',
            title: '区域',
            align: 'center',
            width: 100,
            valign: 'middle'
        }, {
            field: 'siteName',
            title: stationType=='' ? siteColName['0'] : siteColName[stationType],
            align: 'center',
            width: 160,
            valign: 'middle',
        }, {
            field: 'dataTime',
            title: '时间段',
            width: 180,
            align: 'center',
            valign: 'middle',
            formatter: function (value) {
                if (timeType == '2' && value != null) {
                    let year = value.substr(0,4);
                    let season = value.substr(4,5);
                    value = year + '年' + seasonF[season];
                }
                return value;
            }
        }, {
            field: 'pm25OverDays',
            title: 'PM<sub>2.5</sub>超标天数',
            width: 110,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'pm10OverDays',
            title: 'PM<sub>10</sub>超标天数',
            width: 110,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'o3OverDays',
            title: 'O<sub>3</sub>超标天数',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'no2OverDays',
            title: 'NO<sub>2</sub>超标天数',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'so2OverDays',
            title: 'SO<sub>2</sub>超标天数',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'coOverDays',
            title: 'CO超标天数',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'pm25Range',
            title: 'PM<sub>2.5</sub>范围',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'pm10Range',
            title: 'PM<sub>10</sub>范围',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'o3Range',
            title: 'O<sub>3</sub>范围',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'no2Range',
            title: 'NO<sub>2</sub>范围',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'so2Range',
            title: 'SO<sub>2</sub>范围',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'coRange',
            title: 'CO范围',
            width: 100,
            align: 'center',
            valign: 'middle',
        }, {
            field: 'pm25OverRate',
            title: 'PM<sub>2.5</sub>超标率（%）',
            width: 140,
            align: 'center',
            valign: 'middle'
        }, {
            field: 'pm10OverRate',
            title: 'PM<sub>10</sub>超标率（%）',
            width: 140,
            align: 'center',
            valign: 'middle'
        }, {
            field: 'o3OverRate',
            title: 'O<sub>3</sub>超标率（%）',
            width: 130,
            align: 'center',
            valign: 'middle'
        }, {
            field: 'no2OverRate',
            title: 'NO<sub>2</sub>超标率（%）',
            width: 130,
            align: 'center',
            valign: 'middle'
        }, {
            field: 'so2OverRate',
            title: 'SO<sub>2</sub>超标率（%）',
            width: 130,
            align: 'center',
            valign: 'middle'
        }, {
            field: 'coOverRate',
            title: 'CO超标率（%）',
            width: 130,
            align: 'center',
            valign: 'middle'
        }]
    });
}

//导出Excel
function exportExcelRate() {

    let startTime = $('.time-type-start').not('.hidden').val();
    let endTime = $('.time-type-end').not('.hidden').val();
    let stationType = $("input[name='stationType']:checked").val();  // 站点类型
    let timeType = $("input[name='time']:checked").val();  // 时间类型

    layer.confirm('确定要导出当前记录？', {
        btn: ['确定', '取消']
    }, function (index) {
        layer.close(index);
        var url = "";
        url += prefix + "/exportExcelExceedRate?"
            + "startDate=" + startTime
            + "&endDate=" + endTime
            + "&timeType=" + timeType
            + "&siteCodes=" + siteCodes.join()
            + "&stationType=" + stationType
            + "&dataType=" + $("input[name='dataType']:checked").val()
            + "&examineType=" + $("input[name='examineType']:checked").val();
        window.location.href = url
    });
}