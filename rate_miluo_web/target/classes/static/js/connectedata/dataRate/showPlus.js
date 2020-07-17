let roleId = $("#roleId").val();
$(function() {
    if(roleId){
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
    }
});

function countHours(first, second) {
    let times = new Date(second).getTime() - new Date(first).getTime();
    let hours = Math.round(times / 1000 / 60 / 60);
    return hours;
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
                }, {
                    title: 'PM10',
                    field: 'pm10',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'PM2.5',
                    field: 'pm25',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'SO2',
                    field: 'so2',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'CO',
                    field: 'co',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }

                }, {
                    title: 'NO2',
                    field: 'no2',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'O3',
                    field: 'o3oneHour',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '湿度',
                    field: 'humi',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '风速',
                    field: 'ws',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '风向',
                    field: 'wd',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '气温',
                    field: 'temp',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '气压',
                    field: 'press',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '噪声',
                    field: 'noise',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总悬浮颗粒物',
                    field: 'tsp',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                },{
                    title: '查看详情',
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
                }, {
                    title: '二氧化硫(mg/m³)',
                    field: 'so2',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '氮氧化物(mg/m³)',
                    field: 'nox',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '氧气含量(%)',
                    field: 'o2',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '烟气流速(m/s)',
                    field: 'flowVelocity',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }

                }, {
                    title: '温度(℃)',
                    field: 'temp',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '压力(kPa)',
                    field: 'press',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '烟尘(mg/m³)',
                    field: 'dust',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                }, {
                    title: '排放量(m³/s)',
                    field: 'exhaust',
                    formatter: function (data, row, index) {
                        return formatCeil(Math.round(data));
                    }
                },{
                    title: '查看详情',
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
                },{
                    title: '设备名称',
                    field: 'videoName',
                }, {
                    title: '排放量值(L/s)',
                    field: 'bo1',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'PH值',
                    field: 'ph',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总铅值(mg/L)',
                    field: 'pb',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总镉值(ug/L)',
                    field: 'cd',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }

                }, {
                    title: '总砷值(mg/L)',
                    field: 'shen',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总锌值(mg/L)',
                    field: 'zn',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总铜值(mg/L)',
                    field: 'cu',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总磷(mg/L)',
                    field: 'tp',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '总氮(mg/L)',
                    field: 'tn',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: 'cod(mg/L)',
                    field: 'cod',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '氨氮(mg/L)',
                    field: 'nh3',
                    formatter: function (data, row, index) {
                        return formatCeil(parseInt(data));
                    }
                }, {
                    title: '查看详情',
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
        }, {
            title: '水温',
            field: 'sw',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: 'PLC',
            field: 'plc',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '浊度',
            field: 'zd',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '电导率',
            field: 'ddl',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }

        }, {
            title: 'CODMN',
            field: 'codmn',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '总铅',
            field: 'pb',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '溶解氧',
            field: 'rjy',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: 'PH值',
            field: 'ph',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '总砷',
            field: 'shen',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '氨氮',
            field: 'nh3N',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '综合毒性',
            field: 'comprehensiveToxicity',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        },{
            title: '总磷',
            field: 'tp',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '总铬',
            field: 'chromium',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '高猛酸盐指数',
            field: 'kmn',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '镉',
            field: 'cd',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '室温',
            field: 'temp',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '湿度',
            field: 'humi',
            formatter: function (data, row, index) {
                return formatCeil(parseInt(data));
            }
        }, {
            title: '查看详情',
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
    if(roleId == 81){
        a();
    }
    if(roleId == 79){
        b();
    }
    if(roleId == 82){
        c();
    }
    if(roleId == 78){
        d();
    }
}
