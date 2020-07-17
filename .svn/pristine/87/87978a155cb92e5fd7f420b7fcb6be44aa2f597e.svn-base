var index=0;

$(function() {
    // a();
    treeHeight();
    $(window).resize(function () {
        resetHeight();
        treeHeight();
    })
    
});
function treeHeight() {
    var ww=window.innerHeight;
    $("#siteTree").height(ww-30);
}

function resetHeight() {
    var ww=window.innerHeight;
    var headH=$(".sp2").outerHeight(true);
    var headH2=$("#top").outerHeight(true);
    var footH=$(".pagination-detail").outerHeight(true);
    $(".fixed-table-body").height(ww-headH-footH-headH2-50);
    // $(".fixed-table-container").css("border","none");
}

window.onload=function(){         //一进该页面就加载以下方法
    /*tick();   */                          //显示右下角日期的方法
    setInterval('countDown()',1000);    //一般秒设置为参数为1000
}
var sec =300;                  //设置倒计时时间为30秒

function countDown() {        //倒计时的方法
    if(sec-- > 0) {
        num.innerHTML =	((sec/60).toString()).substring(0,(sec/60).toString().indexOf('.')==-1?(sec/60).toString().length:(sec/60).toString().indexOf('.'))+'分'+sec%60+'秒';

    } else {
        reload();
    }
}
function reload(){
    //重新加载整个页面
    location=window.location.href;
}
function a() {
    index = 0;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url :"/internet/getAir",
                // showRefresh : true,
                // showToggle : true,
                showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 20, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        pageSize : params.limit,
                        pageNumber : (params.offset / params.limit) + 1,
                        siteIds : $.siteTree.siteCodes,
                    };
                },
                onPostBody:function(){
                    resetHeight();
                },
                columns : [ [ {
                    field : 'name',
                    title : '站点',
                    align : "center",
                    width : '200',
                    valign: 'middle',
                    colspan : 1,
                    rowspan : 2
                }, {
                    field : 'reportTime',
                    title : '时间',
                    align : "center",
                    valign: 'middle',
                    width : '200',
                    colspan : 1,
                    rowspan : 2

                },{
                    title : 'PM2.5',
                    align : "center",
                    width : '80'
                },{
                    title : 'PM10',
                    align : "center",
                    width : '80'
                },{
                    title : 'O3',
                    align : "center",
                    width : '80'
                },{
                    title : 'NO2',
                    align : "center",
                    width : '80',
                },{
                    title : 'SO2',
                    align : "center",
                    width : '80'
                },{
                    title : 'CO',
                    align : "center",
                    width : '80'
                }, {
                    title : '湿度',
                    // field : 'humi',
                    align : "center",
                    width : '73'
                }, {
                    title : '风速',
                    // field : 'ws',
                    align : "center",
                    width : '74'
                }, {
                    title : '风向',
                    // field : 'wd',
                    align : "center",
                    width : '74'
                }, {
                    title : '气温',
                    // field : 'temp',
                    align : "center",
                    width : '74'
                },  {
                    title : '气压',
                    // field : 'press',
                    align : "center",
                    width : '74'
                },  {
                    title : '噪声',
                    // field : 'press',
                    align : "center",
                    width : '74'
                },{
                    title : '总悬浮颗粒物',
                    // field : 'temp',
                    align : "center",
                    width : '94'
                }],[
                    {
                        field : 'pm25',
                        title : 'μg/m3',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){

                                return  Math.round(parseFloat(value));
                            } else {
                                return "-"
                            }
                        }
                    },{
                        field : 'pm10',
                        title : 'μg/m3',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){

                                return  Math.round(parseFloat(value));
                            } else {
                                return "-"
                            }
                        }

                    },{
                        field : 'o3',
                        title : 'μg/m3',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){

                                return  Math.round(parseFloat(value));
                            } else {
                                return "-"
                            }
                        }
                    },{
                        field : 'no2',
                        title : 'μg/m3',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){

                                return  Math.round(parseFloat(value));
                            } else {
                                return "-"
                            }
                        }
                    },{
                        field : 'so2',
                        title : 'μg/m3',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){

                                return  Math.round(parseFloat(value));
                            } else {
                                return "-"
                            }
                        }
                    },{
                        field : 'co',
                        title : 'mg/m3',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){
                                return parseFloat(value).toFixed(1);
                            } else {
                                return "-"
                            }
                        }
                    },{
                        field : 'humi',
                        title : '%',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){
                                return Math.round(parseFloat(value));
                            } else {
                                return "-"
                            }
                        }
                    },{
                        field : 'ws',
                        title : 'm/s',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){
                                return parseFloat(value).toFixed(1);
                            } else {
                                return "-"
                            }
                        }
                    },{
                        field : 'wd',
                        title : '°',
                        align : "center",
                        width : '115.5',
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
                    },{
                        field : 'temp',
                        title : '°C',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){
                                return parseFloat(value).toFixed(1);
                            } else {
                                return "-"
                            }
                        }
                    },{

                        field : 'press',
                        title : 'pa',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){
                                return parseFloat(value).toFixed(2);
                            } else {
                                return "-"
                            }
                        }
                    },{

                        field : 'noise',
                        title : 'dB',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){
                                return parseFloat(value).toFixed(2);
                            } else {
                                return "-"
                            }
                        }
                    },{

                        field : 'tsp',
                        title : 'ng/m³',
                        align : "center",
                        width : '115.5',
                        formatter: function (value,row,index) {
                            if(value != null && value != ""){
                                return parseFloat(value).toFixed(2);
                            } else {
                                return "-"
                            }
                        }
                    }
                ]]
            });
}
function getWdStr(wd) {
    var text = ['北风', '东北风', '东风', '东南风', '南风' ,'西南风', '西风', '西北风'];
    if(wd<=337.5 || wd<=22.5 && wd > 0){
        return text[0];
    }else if(wd<22.5 && wd<=67.5){
        return text[1];
    }else if(wd<67.5 && wd<=112.5){
        return text[2];
    }else if(wd<112.5 && wd<=157.5){
        return text[3];
    }else if(wd<157.5 && wd<=202.5){
        return text[4];
    }else if(wd<202.5 && wd<=247.5){
        return text[5];
    }else if(wd<247.5 && wd<=292.5){
        return text[6];
    }else if(wd<292.5 && wd<=337.5){
        return text[7];
    }
}

function b() {
    index = 2;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/internet/getPollutantList",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 20, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        /*pageSize : params.limit,
                        pageNumber : (params.offset / params.limit) + 1,*/
                        siteIds : $.siteTree.siteCodes,
                    };
                },
                onPostBody:function(){
                    resetHeight();
                },
                columns : [ [ {
                    field : 'siteName',
                    title : '站点',
                    align : "center",
                    width : '200',
                    valign: 'middle',
                    colspan : 1,
                    rowspan : 2
                }, {
                    field : 'recordingTime',
                    title : '时间',
                    align : "center",
                    valign: 'middle',
                    width : '200',
                    colspan : 1,
                    rowspan : 2
                },{
                    title : '二氧化硫',
                    align : "center"
                },{
                    title : '氧气含量',
                    align : "center"
                },{
                    title : '氮氧化物',
                    align : "center"
                },{
                    title : '烟气流速',
                    align : "center"
                },{
                    title : '烟气温度',
                    align : "center"
                },{
                    title : '烟气压力',
                    align : "center"
                },{
                    title : '烟尘',
                    align : "center"
                },{
                    title : '排放量',
                    align : "center"
                }],[
                    {
                        field : 'so2',
                        title : 'mg/m³',
                        align : "center"
                    },
                    {
                        field : 'o2',
                        title : '%',
                        align : "center"
                    },
                    {
                        field : 'nox',
                        title : 'mg/m³',
                        align : "center"
                    },{
                        field : 'flowVelocity',
                        title : 'm/s',
                        align : "center"
                    },{
                        field : 'temp',
                        title : '℃',
                        align : "center"
                    },{
                        field : 'press',
                        title : 'kPa',
                        align : "center"
                    },{
                        field : 'dust',
                        title : 'mg/m³',
                        align : "center"
                    },{
                        field : 'exhaust',
                        title : 'm³/s',
                        align : "center"
                    }]]
            });
}

function c() {
    index = 3;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url :"/internet/getPolluteWaterMinutes",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 20, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        pageSize : params.limit,
                        pageNumber : (params.offset / params.limit) + 1,
                        siteIds : $.siteTree.siteCodes,
                    };
                },
                onPostBody:function(){
                    resetHeight();
                },
                columns : [ [ {
                    field : 'name',
                    title : '站点',
                    align : "center",
                    width : '150',
                    valign: 'middle',
                    colspan : 1,
                    rowspan : 2
                }, {
                    field : 'recordingTime',
                    title : '时间',
                    align : "center",
                    valign: 'middle',
                    width : '150',
                    colspan : 1,
                    rowspan : 2

                }, {
                    field : 'videoName',
                    title : '设备名称',
                    align : "center",
                    valign: 'middle',
                    width : '220',
                    colspan : 1,
                    rowspan : 2

                },{
                    title : '排放量值',
                    align : "center",
                    width : '80'
                },{
                    title : 'PH值',
                    align : "center",
                    width : '80'
                },{
                    title : '总铅值',
                    align : "center",
                    width : '80'
                },{
                    title : '总镉值',
                    align : "center",
                    width : '80'
                },{
                    title : '总砷值',
                    align : "center",
                    width : '80',
                },{
                    title : '总锌值',
                    align : "center",
                    width : '80'
                }, {
                    title : '总铜值',
                    // field : 'humi',
                    align : "center",
                    width : '73'
                }, {
                    title : '总磷',
                    // field : 'humi',
                    align : "center",
                    width : '73'
                },{
                    title : '总氮',
                    // field : 'humi',
                    align : "center",
                    width : '73'
                }, {
                    title : 'cod0',
                    // field : 'humi',
                    align : "center",
                    width : '73'
                }, {
                    title : '氨氮',
                    // field : 'humi',
                    align : "center",
                    width : '73'
                }],[{
                    field : 'bo1',
                    title : 'L/s',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'ph',
                    title : '',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'pb',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'cd',
                    title : 'μg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'shen',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'zn',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'cu',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'tp',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'tn',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'cod',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                },{
                    field : 'nh3',
                    title : 'mg/L',
                    align : "center",
                    width : '115.5',
                }
                ]]
            });
}

function d() {
    index = 4;
    var sites = $.siteTree.siteCodes();
    if(sites == null || sites.length == 0){
        layer.alert("请先选择站点！");
        return;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/internet/getWaterAuto",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 20, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        pageSize : params.limit,
                        pageNumber : (params.offset / params.limit) + 1,
                        siteIds : $.siteTree.siteCodes,

                    };
                },
        onPostBody:function(){
            resetHeight();
        },
        columns : [ [ {
            field : 'name',
            title : '站点',
            align : "center",
            width : '200',
            valign: 'middle',
            colspan : 1,
            rowspan : 2
        }, {
            field : 'dateTime',
            title : '时间',
            align : "center",
            valign: 'middle',
            width : '150',
            colspan : 1,
            rowspan : 2

        },{
            field : 'videoName',
            title : '设备名称',
            align : "center",
            valign: 'middle',
            width : '150',
            colspan : 1,
            rowspan : 2

        },{
            title : '水温',
            align : "center",
            width : '80'
        },{
            title : '浊度',
            align : "center",
            width : '80'
        },{
            title : '电导率',
            align : "center",
            width : '80'
        },{
            title : 'CODMN',
            align : "center",
            width : '80',
        },{
            title : '总铅',
            align : "center",
            width : '80'
        }, {
            title : '溶解氧',
            // field : 'humi',
            align : "center",
            width : '73'
        }, {
            title : 'PH值',
            // field : 'ws',
            align : "center",
            width : '74'
        }, {
            title : '总砷',
            // field : 'wd',
            align : "center",
            width : '74'
        }, {
            title : '氨氮',
            // field : 'temp',
            align : "center",
            width : '74'
        },  {
            title : '综合毒性',
            // field : 'press',
            align : "center",
            width : '74'
        }, {
            title : '总磷',
            // field : 'humi',
            align : "center",
            width : '73'
        }, {
            title : '水质等级',
            // field : 'wd',
            align : "center",
            width : '74'
        }, {
            title : '镉',
            // field : 'temp',
            align : "center",
            width : '74'
        }, {
            title : '高猛酸盐指数',
            // field : 'press',
            align : "center",
            width : '90'
        }, {
            title : '室温',
            // field : 'temp',
            align : "center",
            width : '74'
        }, {
            title : '湿度',
            // field : 'press',
            align : "center",
            width : '74'
        }, {
            title : '叶绿素',
            align : 'center',
            width : '80'
        }, {
            title : '蓝绿藻',
            align : 'center',
            width : '80'
        } ],[{
            field : 'sw',
            title : '°C',
            align : "center",
            width : '115.5',


        },{
            field : 'zd',
            title : 'NTU',
            align : "center",
            width : '115.5',

        },{
            field : 'ddl',
            title : 'μS/cm',
            align : "center",
            width : '115.5',

        },{
            field : 'codmn',
            title : 'mg/L',
            align : "center",
            width : '115.5',

        },{
            field : 'pb',
            title : 'mg/L',
            align : "center",
            width : '115.5',

        },{
            field : 'rjy',
            title : 'mg/L',
            align : "center",
            width : '115.5',

        },{
            field : 'ph',
            title : '',
            align : "center",
            width : '115.5',


        },{
            field : 'shen',
            title : 'μg/L',
            align : "center",
            width : '115.5',

        },{
            field : 'nh3N',
            title : 'mg/L',
            align : "center",
            width : '115.5',

        },{

            field : 'comprehensiveToxicity',
            title : '',
            align : "center",
            width : '115.5',

        },{
            field : 'tp',
            title : 'mg/L',
            align : "center",
            width : '115.5',


        },{
            field : 'waterSort',
            title : '级别',
            align : "center",
            width : '115.5',
        },{
            field : 'cd',
            title : 'mg/L',
            align : "center",
            width : '115.5',

        },{
            field : 'kmn',
            title : 'mg/L',
            align : "center",
            width : '115.5',

        },{
            field : 'temp',
            title : '°C',
            align : "center",
            width : '115.5',

        },{
            field : 'humi',
            title : '%',
            align : "center",
            width : '115.5',

        },{
            field : 'yls',
            title : 'ug/L',
            align : "center",
            width : '115.5',
        },{
            field : 'llz',
            title : 'Kcells/mL',
            align : "center",
            width : '115.5',
        }
        ]]
            });
}

//查询
function query1() {
    if(index == 0){
        a();
    }
    if(index == 2){
        b();
    }
    if(index == 3){
        c();
    }
    if(index == 4){
        d();
    }
}



