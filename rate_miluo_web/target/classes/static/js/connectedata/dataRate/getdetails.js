$(function() {
    let type = $("#type").val();
    if (type == "air") {
        load1();
    } else if (type == "pollute") {
        load2();
    } else if (type == "waterPlant") {
        load3();
    } else if (type == "water") {
        load4();
    }
});
//
function load1() {
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/dataDetail/airDetailList",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        pageSize: 25, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        //queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        //$('#exampleTable').bootstrapTable('prepend', data);
        showHeader: true,//默认为true显示表头，设为false隐藏
        // showFooter:true,
        //singleSelect : false, // 设置为true将禁止多选
        //contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        //search : true, // 是否显示搜索框
        //showColumns : true, // 是否显示内容下拉框（选择显示的列）
        // clickToSelect:true,
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        // onClickRow: function (row) {
        //     aFormatter(row,index)
        // },
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return {
                pageSize: params.limit, // 每页要显示的数据条数
                pageNumber: (params.offset / params.limit) + 1,
                siteCode:$("#siteCode").val()
            };
        },
        columns: [{
            title: '站点名称',
            field: 'name',
            width: 200,
            align: 'center'
        }, {
            title: "时间",
            field: 'reportTime',
            width: 140,
            align: 'center'
        }, {
            title: 'PM<sub>2.5</sub>',
            field: 'pm25',
            align: 'center'
        },  {
            title: 'PM<sub>10</sub>',
            field: 'pm10',
            align: 'center'
        },{
            title: 'O<sub>3</sub>',
            field: 'o3oneHour',
            align: 'center'
        }, {
            title: 'NO<sub>2</sub>',
            field: 'no2',
            align: 'center'
        }, {
            title: 'SO<sub>2</sub>',
            field: 'so2',
            align: 'center'
        }, {
            title: 'CO',
            field: 'co',
            align: 'center'
        }, {
            title: '湿度',
            field: 'humi',
            align: 'center'
        }, {
            title: '风速',
            field: 'ws',
            align: 'center'
        }, {
            title: '风向',
            field: 'wd',
            align: 'center',
            formatter: function (value) {
                var text = ['北风', '东北风', '东风', '东南风', '南风' ,'西南风', '西风', '西北风'];
                if(value<=337.5 && value<=22.5){
                    return text[0];
                }else if(value<22.5 && value<=67.5){
                    return text[1];
                }else if(value<67.5 && value<=112.5){
                    return text[2];
                }else if(value<112.5 && value<=157.5){
                    return text[3];
                }else if(value<157.5 && value<=202.5){
                    return text[4];
                }else if(value<202.5 && value<=247.5){
                    return text[5];
                }else if(value<247.5 && value<=292.5){
                    return text[6];
                }else if(value<292.5 && value<=337.5){
                    return text[7];
                }
            }
        }, {
            title: '气温',
            field: 'temp',
            align: 'center'
        }, {
            title: '气压',
            field: 'press',
            align: 'center'
        }, {
            title: '噪声',
            field: 'noise',
            align: 'center'
        }, {
            title: '总悬浮颗粒物',
            field: 'tsp',
            width: 110,
            align: 'center'
        }]
    });
}

function load2() {
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/dataDetail/polluteDetailList",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        pageSize: 25, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        //queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        //$('#exampleTable').bootstrapTable('prepend', data);
        showHeader: true,//默认为true显示表头，设为false隐藏
        // showFooter:true,
        //singleSelect : false, // 设置为true将禁止多选
        //contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        //search : true, // 是否显示搜索框
        //showColumns : true, // 是否显示内容下拉框（选择显示的列）
        // clickToSelect:true,
        sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        // onClickRow: function (row) {
        //     aFormatter(row,index)
        // },
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return {
                // pageSize: params.limit, // 每页要显示的数据条数
                // pageNumber: (params.offset / params.limit) + 1,
                siteCode:$("#siteCode").val()
            };
        },
        columns: [{
            title: '站点名称',
            field: 'siteName',
            width: 200,
            align: 'center'
        }, {
            title: "时间",
            field: 'recordingTime',
            align: 'center'
        }, {
            title: '二氧化硫',
            field: 'so2',
            align: 'center'
        }, {
            title: '氮氧化物',
            field: 'nox',
            align: 'center'
        }, {
            title: '氧气含量',
            field: 'o2',
            align: 'center'
        }, {
            title: '烟气流速',
            field: 'flowVelocity',
            align: 'center'
        }, {
            title: '温度',
            field: 'temp',
            align: 'center'
        }, {
            title: '压力',
            field: 'press',
            align: 'center'
        }, {
            title: '烟尘',
            field: 'dust',
            align: 'center'
        }, {
            title: '排放量',
            field: 'exhaust',
            align: 'center'
        }]
    });
}

// 污水厂
function load3() {
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/dataDetail/waterPlantDetailList",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        pageSize: 25, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        //queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        //$('#exampleTable').bootstrapTable('prepend', data);
        showHeader: true,//默认为true显示表头，设为false隐藏
        // showFooter:true,
        //singleSelect : false, // 设置为true将禁止多选
        //contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        //search : true, // 是否显示搜索框
        //showColumns : true, // 是否显示内容下拉框（选择显示的列）
        // clickToSelect:true,
        sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        // onClickRow: function (row) {
        //     aFormatter(row,index)
        // },
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return {
                // pageSize: params.limit, // 每页要显示的数据条数
                // pageNumber: (params.offset / params.limit) + 1,
                siteCode:$("#siteCode").val()
            };
        },
        columns: [{
            title: '站点名称',
            field: 'name',
            align: 'center',
            width: 120
        },{
            title: '设备名称',
            field: 'videoName',
            width: 220,
            align: 'center'
        }, {
            title: "时间",
            field: 'recordingTime',
            width: 140,
            align: 'center'
        }, {
            title: '排放量',
            field: 'bo1',
            align: 'center'
        }, {
            title: 'PH值',
            field: 'ph',
            align: 'center'
        }, {
            title: '总铅',
            field: 'pb',
            align: 'center'
        }, {
            title: '总镉',
            field: 'cd',
            align: 'center'
        }, {
            title: '总砷',
            field: 'shen',
            align: 'center'
        }, {
            title: '总铜',
            field: 'cu',
            align: 'center'
        }, {
            title: '总磷',
            field: 'tp',
            align: 'center'
        }, {
            title: '总氮',
            field: 'tn',
            align: 'center'
        }, {
            title: 'COD',
            field: 'cod',
            align: 'center'
        }, {
            title: '氨氮',
            field: 'nh3',
            align: 'center'
        }]
    });
}

// 水质站
function load4() {
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/dataDetail/waterDetailList",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        pageSize: 25, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        //queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        //$('#exampleTable').bootstrapTable('prepend', data);
        showHeader: true,//默认为true显示表头，设为false隐藏
        // showFooter:true,
        //singleSelect : false, // 设置为true将禁止多选
        //contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        //search : true, // 是否显示搜索框
        //showColumns : true, // 是否显示内容下拉框（选择显示的列）
        // clickToSelect:true,
        sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        // onClickRow: function (row) {
        //     aFormatter(row,index)
        // },
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return {
                // pageSize: params.limit, // 每页要显示的数据条数
                // pageNumber: (params.offset / params.limit) + 1,
                siteCode:$("#siteCode").val()
            };
        },
        columns: [{
            title: '站点名称',
            field: 'siteName',
            align: 'center'
        }, {
            title: "时间",
            field: 'dateTime',
            align: 'center'
        }, {
            title: '水温',
            field: 'sw',
            align: 'center'
        }, {
            title: 'PLC',
            field: 'plc',
            align: 'center'
        }, {
            title: '电导率',
            field: 'ddl',
            align: 'center'
        }, {
            title: 'CODmn',
            field: 'cd',
            align: 'center'
        }, {
            title: 'codmn',
            field: 'shen',
            align: 'center'
        }, {
            title: '总铅',
            field: 'pb',
            align: 'center'
        }, {
            title: '溶解氧',
            field: 'rjy',
            align: 'center'
        }, {
            title: 'PH值',
            field: 'ph',
            align: 'center'
        }, {
            title: '砷',
            field: 'shen',
            align: 'center'
        }, {
            title: '氨氮',
            field: 'nh3N',
            align: 'center'
        }, {
            title: '毒性',
            field: 'comprehensiveToxicity',
            align: 'center'
        }, {
            title: '总磷',
            field: 'tp',
            align: 'center'
        }, {
            title: '总铬',
            field: 'chromium',
            align: 'center'
        }, {
            title: '水质等级',
            field: 'waterSort',
            align: 'center'
        }, {
            title: '高锰酸盐指数',
            field: 'kmn',
            align: 'center'
        }, {
            title: '镉',
            field: 'cd',
            align: 'center'
        }, {
            title: '室温',
            field: 'temp',
            align: 'center'
        }, {
            title: '湿度',
            field: 'humi',
            align: 'center'
        }]
    });
}



