var prefix = '/statistic/chainCompare';
var suffix = '/statistic/averageCompare';

var nowDate = new Date();
// 日期时间选择器
layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#startDate',
        format: 'yyyy-MM-dd',
        value: preDay(nowDate)
    });
});
layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#endDate',
        format: 'yyyy-MM-dd',
        value: preDay(nowDate)
    });
});

// 前一天
function preDay(nowDate) {
    if (nowDate != null) {
        return new Date(nowDate.getTime() - 24 * 60 * 60 * 1000);
    }
}

var siteCodes;
// $('#stationCode').on("changed.jstree", function (e, data) {
//     siteCodes = $('#stationCode').jstree().get_bottom_selected();
//     if (siteCodes) {
//         siteCodes = siteCodes.join(",");
//     }
// });
function getHeight() {
    let body = window.innerHeight;
    let se1 = $(".select-bg").outerHeight();
    return body - se1 - 30;
}
$(window).resize(function () {
    $('#exampleTable').bootstrapTable('resetView');
});

function load() {
    showLoad();
    $('#exampleTable').bootstrapTable('destroy')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list",
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
                fixedNumber: 2,
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client"
                // 或者 "server"
                queryParams: function (params) {
                    return {
                        startDate: $("#startDate").val(),
                        endDate: $("#endDate").val(),
                        siteCodes: $.siteTree.siteCodes()?$.siteTree.siteCodes().join():undefined,
                        type: $("input[name='recordType']:checked").val(),
                        examineType: $("input[name='examineType']:checked").val(),
                    };
                },onLoadSuccess: function(){  //加载成功时执行
                    closeLoad();
                },
                columns: [
                    [{
                        field: 'siteName',
                        title: '站点名称(类型)',
                        align: 'center',
                        width: 160,
                        valign: 'middle',
                        colspan: 1,
                        rowspan: 2
                    }, {
                        title: 'PM<sub>2.5</sub>（μg/m³）',
                        field: '',
                        align: 'center',
                        valign: 'middle',
                        width: 300,
                        colspan: 3,
                        rowsapn: 1
                    },{
                        title: 'PM<sub>10</sub>（μg/m³）',
                        field: '',
                        align: 'center',
                        valign: 'middle',
                        width: 300,
                        colspan: 3,
                        rowsapn: 1
                    }, {
                        title: 'O<sub>3</sub>-8h（μg/m³）',
                        field: '',
                        align: 'center',
                        valign: 'middle',
                        width: 300,
                        colspan: 3,
                        rowsapn: 1
                    }, {
                        title: 'NO<sub>2</sub>（μg/m³）',
                        field: '',
                        align: 'center',
                        valign: 'middle',
                        width: 300,
                        colspan: 3,
                        rowsapn: 1
                    }, {
                        title: 'SO<sub>2</sub>（μg/m³）',
                        field: '',
                        align: 'center',
                        valign: 'middle',
                        width: 300,
                        colspan: 3,
                        rowsapn: 1
                    },  {
                        title: 'CO（mg/m³）',
                        field: '',
                        align: 'center',
                        valign: 'middle',
                        width: 300,
                        colspan: 3,
                        rowsapn: 1
                    }],
                    [

                        {
                            field: 'pm25Avg',
                            title: '日均浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'pm25PreYearAvg',
                            title: '上月同期浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'pm25YearBasis',
                            title: '环比(%)',
                            align: 'center',
                            valign: 'middle',
                            width: 100,
                            formatter: function (value, row,
                                                 index) {
                                if (row.pm25Avg != null
                                    && row.pm25PreYearAvg != null) {
                                    if (row.pm25PreYearAvg=="0"){
                                        return "0";
                                    }
                                    var pm25YearBasis = row.pm25Avg
                                        / row.pm25PreYearAvg
                                        - 1;
                                    if (pm25YearBasis > 0) {
                                        return '+'
                                            + (pm25YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    } else {
                                        return (pm25YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    }
                                }
                            }
                        },{
                            field: 'pm10Avg',
                            title: '日均浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'pm10PreYearAvg',
                            title: '上月同期浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'pm10YearBasis',
                            title: '环比(%)',
                            align: 'center',
                            valign: 'middle',
                            width: 100,
                            formatter: function (value, row,
                                                 index) {
                                if (row.pm10Avg != null
                                    && row.pm10PreYearAvg != null) {
                                    if (row.pm10PreYearAvg=="0"){
                                        return "0";
                                    }
                                    var pm10YearBasis = row.pm10Avg
                                        / row.pm10PreYearAvg
                                        - 1;
                                    if (pm10YearBasis > 0) {
                                        return '+'
                                            + (pm10YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    } else {
                                        return (pm10YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    }
                                }
                            }
                        },
                        {
                            field: 'o3EightAvg',
                            title: '日均浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'o3EightPreYearAvg',
                            title: '上月同期浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'o3EightYearBasis',
                            title: '环比(%)',
                            align: 'center',
                            valign: 'middle',
                            width: 100,
                            formatter: function (value, row,
                                                 index) {
                                if (row.o3EightAvg != null
                                    && row.o3EightPreYearAvg != null) {
                                    if (row.o3EightPreYearAvg=="0"){
                                        return "0";
                                    }
                                    var o3EightYearBasis = row.o3EightAvg
                                        / row.o3EightPreYearAvg
                                        - 1;
                                    if (o3EightYearBasis > 0) {
                                        return '+'
                                            + (o3EightYearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    } else {
                                        return (o3EightYearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    }
                                }
                            }
                        },
                        {
                            field: 'no2Avg',
                            title: '日均浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'no2PreYearAvg',
                            title: '上月同期浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'no2YearBasis',
                            title: '环比(%)',
                            align: 'center',
                            valign: 'middle',
                            width: 100,
                            formatter: function (value, row,
                                                 index) {
                                if (row.no2Avg != null
                                    && row.no2PreYearAvg != null) {
                                    if (row.no2PreYearAvg=="0"){
                                        return "0";
                                    }
                                    var no2YearBasis = row.no2Avg
                                        / row.no2PreYearAvg
                                        - 1;
                                    if (no2YearBasis > 0) {
                                        return '+'
                                            + (no2YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    } else {
                                        return (no2YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    }
                                }
                            }
                        },
                        {
                            field: 'so2Avg',
                            title: '日均浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'so2PreYearAvg',
                            title: '上月同期浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'so2YearBasis',
                            title: '环比(%)',
                            align: 'center',
                            valign: 'middle',
                            width: 100,
                            formatter: function (value, row,
                                                 index) {
                                if (row.so2Avg != null
                                    && row.so2PreYearAvg != null) {
                                    if (row.so2PreYearAvg=="0"){
                                        return "0";
                                    }
                                    var so2YearBasis = row.so2Avg
                                        / row.so2PreYearAvg
                                        - 1;
                                    if (so2YearBasis > 0) {
                                        return '+'
                                            + (so2YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    } else {
                                        return (so2YearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    }
                                }
                            }
                        },
                        {
                            field: 'coAvg',
                            title: '日均浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'coPreYearAvg',
                            title: '上月同期浓度',
                            align: 'center',
                            valign: 'middle',
                            width: 100
                        },
                        {
                            field: 'coYearBasis',
                            title: '环比(%)',
                            align: 'center',
                            valign: 'middle',
                            width: 100,
                            formatter: function (value, row,
                                                 index) {
                                if (row.coAvg != null
                                    && row.coPreYearAvg != null) {
                                    if (row.coPreYearAvg=="0"){
                                        return "0";
                                    }
                                    var coYearBasis = row.coAvg
                                        / row.coPreYearAvg
                                        - 1;
                                    if (coYearBasis > 0) {
                                        return '+'
                                            + (coYearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    } else {
                                        return (coYearBasis * 100)
                                                .toFixed(1)
                                            + '%';
                                    }
                                }
                            }
                        }
                        ]]
            });
}
function reload() {
    siteCodes = $.siteTree.siteCodes();
    if (siteCodes.length == 0) {
        layer.msg('未选择站点');
        return;
    }
    // var a = getType(siteCodes);
    // if (a!="0"){
    //     $('#exampleTable').bootstrapTable('destroy');
    //     load2();
    // }else {
    //     $('#exampleTable').bootstrapTable('destroy');
    //     load();
    // }
    siteCodes = siteCodes.join();
    load();
    //$('#exampleTable').bootstrapTable('refresh');
}

function getType(stations) {
    var nodes = {"Nodes":stations};
    var str = 0;
    $.ajax({
        url: suffix+"/stationType",
        type: "POST",
        data: nodes,
        async:false,
        traditional:true,
        success: function (res) {
            for(var i in  res){
                if (res[i]==2){
                    str++;
                }
            }
        }
    })
    return str;
}

var loadIndex = void 0;
function showLoad() {
    loadIndex = layer.msg('请稍后...', {icon: 16, shade: [0.5, '#f5f5f5'], scrollbar: false, offset: 'auto', time: 30000});
}

function closeLoad() {
    layer.close(loadIndex);
}
// 导出Excel
function exportExcel() {
    layer.confirm('确定要导出当前记录？', {
        btn: ['确定', '取消']
    }, function (index) {
        layer.close(index);
        var url = "";
        url += prefix + "/exportExcel?startDate=" + $("#startDate").val()
            + "&endDate=" + $("#endDate").val()
            + "&siteCodes="+ $.siteTree.siteCodes().join()
            + "&type=" + ($("input[name='recordType']:checked").val())
            + "&examineType=" + $("input[name='examineType']:checked").val();
        window.location.href = url
    })
}