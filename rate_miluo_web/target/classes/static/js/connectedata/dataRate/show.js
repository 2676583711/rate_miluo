var index = 0;

$(function () {
    layui.use(['laydate'], function () {
        var $ = layui.$;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        var max = null;
        var date = new Date();
        var endDefault = 0;
        var endDefault = new Date(date.getTime() - date.getMinutes() * 60 * 1000);
        var startDefault = new Date(endDefault.getTime() - 24 * 60 * 60 * 1000);

        var start = laydate.render({
            elem: '#beginTime',
            type: 'datetime',
            max: nowTime,
            format: 'yyyy-MM-dd HH:mm',
            isInitValue: true,
            value: startDefault,
            btns: ['clear', 'confirm'],
            done: function (value, date) {
                endMax = end.config.max;
                end.config.min = date;
                end.config.min.month = date.month - 1;
            }
        });

        var end = laydate.render({
            elem: '#endTime',
            type: 'datetime',
            min: nowTime,
            format: 'yyyy-MM-dd HH:mm',
            isInitValue: true,
            value: endDefault,
            done: function (value, date) {
                if ($.trim(value) == '') {
                    var curDate = new Date();
                    date = {
                        'date': curDate.getDate(),
                        'month': curDate.getMonth() + 1,
                        'year': curDate.getFullYear()
                    };
                }
                start.config.max = date;
                start.config.max.month = date.month - 1;
            }
        });
    });
});

function countHours(first, second) {
    let times = new Date(second).getTime() - new Date(first).getTime();
    let hours = Math.round(times / 1000 / 60 / 60);
    return hours;
}

//5分钟值
function minute() {
    index = 0;
    $.siteTree.hideTab(["-1", "-3", "-4"])
    $.siteTree.showTab("-2")
    a();
}

function a() {
    var sites = $.siteTree.siteCodes();
    if (sites == null || sites.length == 0) {
        layer.alert("请先选择站点！");
        return;
    }
    let beginTime = $("#beginTime").val() + ":00";
    let endTime = $("#endTime").val() + ":00";
    let hours = countHours(beginTime, endTime);
    if (hours > 24) {
        layer.alert("时间不能超过24小时！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/dataRate/getAirList",
                // showRefresh : true,
                // showToggle : true,
                showColumns: true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 20, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        // pageSize : params.limit,
                        // pageNumber : (params.offset / params.limit) + 1,
                        siteIds: $.siteTree.siteCodes,
                        beginTime: beginTime,
                        endTime: endTime
                    };
                },
                columns: [{
                    title: '站点名称',
                    field: 'name',
                    width: 200,
                    align: 'center'
                }, {
                    title: 'PM<sub>2.5</sub>',
                    field: 'pm25',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'PM<sub>10</sub>',
                    field: 'pm10',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'O<sub>3</sub>',
                    field: 'o3oneHour',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                },  {
                    title: 'NO<sub>2</sub>',
                    field: 'no2',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'SO<sub>2</sub>',
                    field: 'so2',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'CO',
                    field: 'co',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }

                },{
                    title: '湿度',
                    field: 'humi',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '风速',
                    field: 'ws',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '风向',
                    field: 'wd',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '气温',
                    field: 'temp',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '气压',
                    field: 'press',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '噪声',
                    field: 'noise',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总悬浮颗粒物',
                    field: 'tsp',
                    width: 100,
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                },{
                    title: '查看详情',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="openAirDetail(\''
                            + row.siteCode
                            + '\')"><i class="glyphicon glyphicon-search"></i></a> ';
                        return f;
                    }

                }]
            });

    var pollutantNames = $("#pollutantNames").val();
    if (pollutantNames == null || pollutantNames == "") {
        //console.info(str);
    } else {
        var names = pollutantNames.split(",");
        var str = $('#exampleTable').bootstrapTable.columns[1];
        // console.info(str)
        // console.info(names);
        // console.info(pollutantNames)
    }
}

function openAirDetail(equmentId) {
    location.href = "/dataDetail/details/air?siteCode=" + equmentId;
}
function openPolluteDetail(equmentId) {
    location.href = "/dataDetail/details/pollute?siteCode=" + equmentId;
}
function openWaterPlantDetail(equmentId) {
    location.href = "/dataDetail/details/waterPlant?siteCode=" + equmentId;
}
function openWaterDetail(equmentId) {
    location.href = "/dataDetail/details/water?siteCode=" + equmentId;
}

//小时值
function hourTime() {
    index = 2;
    $.siteTree.hideTab(["-1", "-2", "-4"])
    $.siteTree.showTab("-3")
    b();
}

// 涉气污染源
function b() {
    var sites = $.siteTree.siteCodes();
    if (sites == null || sites.length == 0) {
        layer.alert("请先选择站点！");
        return;
    }
    let beginTime = $("#beginTime").val() + ":00";
    let endTime = $("#endTime").val() + ":00";
    let hours = countHours(beginTime, endTime);
    if (hours > 24) {
        layer.alert("时间不能超过24小时！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/dataRate/getPollutantList",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 20, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        /*pageSize : params.limit,
                        pageNumber : (params.offset / params.limit) + 1,*/
                        siteIds: $.siteTree.siteCodes(),
                        beginTime: beginTime,
                        endTime: endTime,
                    };
                },
                columns: [{
                    title: '站点名称',
                    field: 'siteName',
                    align: 'center'
                }, {
                    title: '二氧化硫(mg/m³)',
                    field: 'so2',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '氮氧化物(mg/m³)',
                    field: 'nox',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '氧气含量(%)',
                    field: 'o2',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '烟气流速(m/s)',
                    field: 'flowVelocity',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }

                }, {
                    title: '温度(℃)',
                    field: 'temp',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '压力(kPa)',
                    field: 'press',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '烟尘(mg/m³)',
                    field: 'dust',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '排放量(m³/s)',
                    field: 'exhaust',
                    align: 'center',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                },{
                    title: '查看详情',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="openPolluteDetail(\''
                            + row.equmentId
                            + '\')"><i class="glyphicon glyphicon-search"></i></a> ';
                        return f;
                    }

                }]
            });
}

function formatCeil(data) {
    if (data >= 0 && data < 19) {
        return '<span style="color: #ff0000;">' + data + '/24</span>'
    } else if (data >= 19 && data <= 24) {
        return '<span style="color: orange;">' + data + '/24</span>'
    } else {
        return '<span style="color: #3f5dc8">-----</span>'
    }
}

//日均值
function dayTime() {
    index = 3;
    $.siteTree.hideTab(["-1", "-2", "-3"])
    $.siteTree.showTab("-4")
    c();
}

// 污水厂
function c() {
    var sites = $.siteTree.siteCodes();
    if (sites == null || sites.length == 0) {
        layer.alert("请先选择站点！");
        return;
    }
    let beginTime = $("#beginTime").val() + ":00";
    let endTime = $("#endTime").val() + ":00";
    let hours = countHours(beginTime, endTime);
    if (hours > 24) {
        layer.alert("时间不能超过24小时！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/dataRate/getWaterPlantList",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 20, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        // pageSize: params.limit,
                        // pageNumber: (params.offset / params.limit) + 1,
                        siteIds: $.siteTree.siteCodes,
                        beginTime: beginTime,
                        endTime: endTime,
                    };
                },
                columns: [{
                    title: '站点名称',
                    field: 'name',
                    align: 'center',
                    width : '120'
                },{
                    title: '设备名称',
                    field: 'videoName',
                    align: 'center',
                    width : '220'
                }, {
                    title: '排放量值(L/s)',
                    field: 'bo1',
                    align: 'center',
                    width : '90',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'PH值',
                    field: 'ph',
                    align: 'center',
                    width : '90',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总铅值(mg/L)',
                    field: 'pb',
                    align: 'center',
                    width : '100',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总镉值(ug/L)',
                    field: 'cd',
                    align: 'center',
                    width : '100',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }

                }, {
                    title: '总砷值(mg/L)',
                    field: 'shen',
                    align: 'center',
                    width : '100',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总锌值(mg/L)',
                    field: 'zn',
                    align: 'center',
                    width : '100',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总铜值(mg/L)',
                    field: 'cu',
                    align: 'center',
                    width : '100',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总磷(mg/L)',
                    field: 'tp',
                    align: 'center',
                    width : '90',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总氮(mg/L)',
                    field: 'tn',
                    align: 'center',
                    width : '90',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'cod(mg/L)',
                    field: 'cod',
                    align: 'center',
                    width : '90',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '氨氮(mg/L)',
                    field: 'nh3',
                    align: 'center',
                    width : '90',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                   }
                }, {
                    title: '查看详情',
                    align: 'center',
                    width : '90',
                    formatter: function (value, row, index) {
                        var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="openWaterPlantDetail(\''
                            + row.equmentId
                            + '\')"><i class="glyphicon glyphicon-search"></i></a> ';
                        return f;
                    }

                }
                ]
            });
}

//月均值
function monthTime() {
    index = 4;
    $.siteTree.hideTab(["-2", "-3", "-4"])
    $.siteTree.showTab("-1")
    d();
}

// 水质站
function d() {
    var sites = $.siteTree.siteCodes();
    if (sites == null || sites.length == 0) {
        layer.alert("请先选择站点！");
        return;
    }
    let beginTime = $("#beginTime").val() + ":00";
    let endTime = $("#endTime").val() + ":00";
    let hours = countHours(beginTime, endTime);
    if (hours > 24) {
        layer.alert("时间不能超过24小时！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/dataRate/getWaterList",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect: false, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        pageSize: 20, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        // search : true, // 是否显示搜索框
        showColumns: false, // 是否显示内容下拉框（选择显示的列）
        sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者
        // "server"
        queryParams: function (params) {
            return {
                // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                // pageSize: params.limit,
                // pageNumber: (params.offset / params.limit) + 1,
                siteIds: $.siteTree.siteCodes,
                beginTime: beginTime,
                endTime: endTime
            };
        },
        columns: [{
            title: '站点名称',
            field: 'siteName',
            align: 'center',
            width : '120',
        }, {
            title: '水温',
            field: 'sw',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: 'PLC',
            field: 'plc',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '浊度',
            field: 'zd',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '电导率',
            field: 'ddl',
            align: 'center',
            width : '80',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }

        }, {
            title: 'CODMN',
            field: 'codmn',
            align: 'center',
            width : '80',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '总铅',
            field: 'pb',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '溶解氧',
            field: 'rjy',
            align: 'center',
            width : '80',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: 'PH值',
            field: 'ph',
            align: 'center',
            width : '80',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '总砷',
            field: 'shen',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '氨氮',
            field: 'nh3N',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '综合毒性',
            field: 'comprehensiveToxicity',
            align: 'center',
            width : '100',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        },{
            title: '总磷',
            field: 'tp',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '总铬',
            field: 'chromium',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '高猛酸盐指数',
            field: 'kmn',
            align: 'center',
            width : '100',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '镉',
            field: 'cd',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '室温',
            field: 'temp',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '湿度',
            field: 'humi',
            align: 'center',
            width : '60',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '查看详情',
            align: 'center',
            width : '100',
            formatter: function (value, row, index) {
                var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="openWaterDetail(\''
                    + row.stationCode
                    + '\')"><i class="glyphicon glyphicon-search"></i></a> ';
                return f;
            }
        }]
    });
}

//查询
function query1() {
    if (index == 0) {
        a();
    }
    if (index == 2) {
        b();
    }
    if (index == 3) {
        c();
    }
    if (index == 4) {
        d();
    }
}
