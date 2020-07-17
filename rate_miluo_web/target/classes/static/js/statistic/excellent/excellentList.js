var prefix = "/statistic/excellent";
var flag = true;
var qualityList= [];
var siteCodes="";
var index = 0;

$(function () {
    setTimeout(function () {
        tree1()
    }, 300)
});

function getHeight() {
    let body = window.innerHeight;
    let ul = $("#myTab").outerHeight();
    let se1 = $(".select-bg").outerHeight();
    return body - ul - se1 - 30;
}
$(window).resize(function () {
    $('#exampleTable').bootstrapTable('resetView');
});

//改变事件
function resetHeight(){
    let ww = window.innerHeight;
    let forH = $(".form-inline").outerHeight(true);
    let footH = $(".pagination-detail").outerHeight(true);
    $(".fixed-table-body").height(ww-footH-forH-80);
}

function minute() {
    $("#main").show();
    $("#table2").attr("style","display:none;");
    $("#main2").hide();
    index=0;
    reLoad()
}

function initTime() {
    $.ajax({
        url: prefix + "/initTime",
        type: "post",
        data: {
            "dateType": $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
            "startMonth": $("#startMonth").val(), // 开始月份
            "endMonth": $("#endMonth").val(),     // 结束月份
            "startYear": $("#startYear").val(),   // 开始年份
            "endYear": $("#endYear").val(),       // 结束年份
            "startDay": $("#startDay").val(),     // 开始日期
            "endDay": $("#endDay").val(),         // 结束日期
            "jd1": $("#jd1").val(),               // 开始季度
            "jd2": $("#jd2").val()               // 结束季度
        },
        success: function (res) {
            let time = res.data;
            var stationType =$("input[type='radio'][name='stationType']:checked").val();
            init1(time);
        },
        error: function (error) {

        }
    })

}

function init1(time) {

    let cols = new Array();
    let xItems =time;

    cols.push({
        field: 'siteName',
        title: '站点名称',
        align: 'center',
        fixed:'left',
        width: 100
    });
    var timesa= $("input[type='radio'][name='dataStatus']:checked").val();
    if (timesa=='1' ){
        if (time && time.length > 0) {
            for (let i = 0; i < time.length; i++) {
                var time1 = time[i].substr(0,4)+"_"+time[i].substr(5,7);
                var time2 =  time[i];
                cols.push({
                    field: time1,
                    title: time2,
                    align: 'center',
                    width: 150
                })
            }
        }
    } else if (timesa=='2'){
        if (time && time.length > 0) {
            for (let i = 0; i < time.length; i++) {
                var time1 = time[i];
                var time2 =  time[i].substr(0,4)+time[i].slice(6,7);
                cols.push({
                    field: time2,
                    title: time1,
                    align: 'center',
                    width: 150
                })
            }
        }
    }else if (timesa=='3'){
        if (time && time.length > 0) {
            for (let i = 0; i < time.length; i++) {
                var time1 = time[i];
                var time2 =  time[i];
                cols.push({
                    field: time2,
                    title: time1,
                    align: 'center',
                    width: 150
                })
            }
        }
    }else if (timesa=='4'){
        if (time && time.length > 0) {
            for (let i = 0; i < time.length; i++) {
                var time1 = time[i];
                var time2 =  time[i].substr(0,4);
                cols.push({
                    field: time2,
                    title: time1,
                    align: 'center',
                    width: 150
                })
            }
        }
    }

    layui.use('table', function () {
       var stp=  $("input[type='radio'][name='stationType']:checked").val();
       if (stp==""){
           stp="0"
       }
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#exampleTable2'
            , height: getHeight()
            , method: 'post'
            , url: prefix + "/list2" //数据接口
            , where: {
                dateType: $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
                dbValue: $("input[type='radio'][name='db']:checked").val(), // 获取数据源选项值
                examineType: $("input[type='radio'][name='examineType']:checked").val(), // 获取数据源选项值
                siteType: stp,  // 站点类型
                startMonth: $("#startMonth").val(), // 开始月份
                endMonth: $("#endMonth").val(),     // 结束月份
                startYear: $("#startYear").val(),   // 开始年份
                endYear: $("#endYear").val(),       // 结束年份
                startDay: $("#startDay").val(),     // 开始日期
                endDay: $("#endDay").val(),         // 结束日期
                jd1: $("#jd1").val(),               // 开始季度
                jd2: $("#jd2").val(),               // 结束季度
                siteCodes:getSiteCodes().join(",")
            }
            , done: function (res, curr, count) {
                setPolImg(res,xItems)
            }
            , even: true
            , cols: [cols]
        });

    });
}

//图
function setPolImg(res,xItems) {
    let xItem = xItems,
        siteArr = [];

    var timesa= $("input[type='radio'][name='dataStatus']:checked").val();
    var s = [];
    $.each(res.data, function (idx, item) {
        siteArr.push(item.siteName)
        if (timesa==1){
            let sdata=[];
            $.each(xItem, function (idx2, item2) {
                sdata.push(item[item2]==undefined?"-":item[item2])
            });
            var t = {
                name: item.siteName,
                type: 'line',
                data: sdata,
                connectNulls: true
            };
            s.push(t)
        }
        if (timesa==2){
            let sdata=[];
            $.each(xItem, function (idx2, item2) {
                var time2 =  item2.substr(0,4)+item2.slice(6,7);
                sdata.push(item[time2]==undefined?"-":item[time2])
            });
            var t = {
                name: item.siteName,
                type: 'line',
                data: sdata,
                connectNulls: true
            };
            s.push(t)
        }
        if (timesa==3){
            let sdata=[];
            $.each(xItem, function (idx2, item2) {
                sdata.push(item[item2]==undefined?"-":item[item2])
            });
            var t = {
                name: item.siteName,
                type: 'line',
                data: sdata,
                connectNulls: true
            };
            s.push(t)
        }
        if (timesa==4){
            let sdata=[];
            $.each(xItem, function (idx2, item2) {
                var time2 =  item2.substr(0,4);
                sdata.push(item[time2]==undefined?"-":item[time2])
            });
            var t = {
                name: item.siteName,
                type: 'line',
                data: sdata,
                connectNulls: true
            };
            s.push(t)
        }

    });
    var oneChart = {
        siteName: "值",
        items: siteArr,
        xTitle: xItem,
        data: s
    };
    drawHourImage2( oneChart);
}
function drawHourImage2( indata) {
    var option = {
        title: {
            text: indata.siteName
        },

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },

        legend: {
            data: indata.items,
            bottom: 'bottom'
            // textStyle: {
            //     fontSize: 16
            // }
        },
        xAxis: {
            type: 'category',
            data: indata.xTitle
        },
        yAxis: [{
            type: 'value',
            name: indata.unit,
            position: "left",
            yAxisIndex: 0,
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        }],
        series: indata.data
    };
    let hourImg = echarts.init(document.getElementById("main2"));
    hourImg.setOption(option);

    /*  // 基于准备好的dom，初始化echarts图表
      var myChart = echarts.init(document.getElementById(element));
      myChart.setOption(option);
      hourChartArr.push(myChart);*/
}
// 加载list方法
function load2() {
    $('#exampleTable').bootstrapTable('destroy');
    initTime();
}



// 加载list方法
function load() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url :  prefix + "/list",
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
        // showColumns : false, // 是否显示内容下拉框（选择显示的列）
        // fixedColumns: true,
        // fixedNumber: 3,
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"Server"
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                dateType: $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
                startMonth: $("#startMonth").val(), // 开始月份
                endMonth: $("#endMonth").val(),     // 结束月份
                startYear: $("#startYear").val(),   // 开始年份
                endYear: $("#endYear").val(),       // 结束年份
                startDay: $("#startDay").val(),     // 开始日期
                endDay: $("#endDay").val(),         // 结束日期
                jd1: $("#jd1").val(),               // 开始季度
                jd2: $("#jd2").val(),               // 结束季度

                siteCodes:getSiteCodes().join(",")
            };
        },
        onLoadSuccess:function(data){
            //通过对data判断
            qualityList = data.rows;
            let station = [];
            let excellentDays =[];
            let goodDays =[];
            let mildDays =[];
            let moderateDays =[];
            let severeDays =[];
            let seriousnessDays =[];
            for(let i = 0; i < data.rows.length; i++) {
                station.push(data.rows[i].siteName);
                excellentDays.push(data.rows[i].excellentDays);
                goodDays.push(data.rows[i].goodDays);
                mildDays.push(data.rows[i].mildDays);
                moderateDays.push(data.rows[i].moderateDays);
                severeDays.push(data.rows[i].severeDays);
                seriousnessDays.push(data.rows[i].seriousnessDays);
            }
            drawEchars(station,excellentDays,goodDays,mildDays,moderateDays,severeDays,seriousnessDays);
        },
        columns: [[{
            field: 'siteName',
            title: '站点名称',
            align: 'center',
            width: '160',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        },{
            field: 'times',
            title: '时间',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2,
            formatter: function (value, row, index) {
               var s= $("input[type='radio'][name='dataStatus']:checked").val();
            if (s=='1'){
                return value.substr(0,7);;
            } else if (s=='2'){
                var as = value.substr(0,4);
                var as2 = value.substr(5,2);
                if (1<=as2 && as2<4){
                    return as+"年第一季度"
                }else if (4<=as2 && as2<7){
                    return as+"年第二季度"
                } else if (7<=as2 && as2<10){
                    return as+"年第三季度"
                }else {
                    return as+"年第四季度"
                }

            } else if (s=='3'){

                return value.substr(0,4)+"年";

            } else {
                return $("#startDay").val()+"至"+$("#endDay").val() ;
            }
            }
        }, {
            title: '优',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '良',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '轻度污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '中度污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '重度污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '严重污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            field: 'sumDays',
            title: '总天数',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'validDays',
            title: '有效天数',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'excellentAndGood',
            title: '优良天数',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'excellentAndGoodRatio',
            title: '优良比例',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2,
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }], [{
            field: 'excellentDays',
            title: '优天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'excellentRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'goodDays',
            title: '良天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'goodRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'mildDays',
            title: '轻度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'mildRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'moderateDays',
            title: '中度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'moderateRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'severeDays',
            title: '重度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'severeRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'seriousnessDays',
            title: '严重污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'seriousnessRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }]]
    }).bootstrapTable('hideLoading');
}
// 加载list方法
function loadarea() {
    var stationType =$("input[type='radio'][name='stationType']:checked").val();
    if (stationType==""){
        stationType="0"
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url :  prefix + "/list",
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
        fixedNumber: 2,
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"Server"
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                dateType: $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
                dbValue: $("input[type='radio'][name='db']:checked").val(), // 获取数据源选项值
                examineType: $("input[type='radio'][name='examineType']:checked").val(), // 获取数据源选项值
                siteType: stationType,  // 站点类型
                startMonth: $("#startMonth").val(), // 开始月份
                endMonth: $("#endMonth").val(),     // 结束月份
                startYear: $("#startYear").val(),   // 开始年份
                endYear: $("#endYear").val(),       // 结束年份
                startDay: $("#startDay").val(),     // 开始日期
                endDay: $("#endDay").val(),         // 结束日期
                jd1: $("#jd1").val(),               // 开始季度
                jd2: $("#jd2").val(),               // 结束季度

                siteCodes:getSiteCodes().join(",")
            };
        },
        onLoadSuccess:function(data){
            //通过对data判断
            qualityList = data.rows;
            let station = [];
            let excellentDays =[];
            let goodDays =[];
            let mildDays =[];
            let moderateDays =[];
            let severeDays =[];
            let seriousnessDays =[];
            for(let i = 0; i < data.rows.length; i++) {
                station.push(data.rows[i].siteName);
                excellentDays.push(data.rows[i].excellentDays);
                goodDays.push(data.rows[i].goodDays);
                mildDays.push(data.rows[i].mildDays);
                moderateDays.push(data.rows[i].moderateDays);
                severeDays.push(data.rows[i].severeDays);
                seriousnessDays.push(data.rows[i].seriousnessDays);
            }
            drawEchars(station,excellentDays,goodDays,mildDays,moderateDays,severeDays,seriousnessDays);
        },
        columns: [[{
            field: 'areaName',
            title: '区域',
            align: 'center',
            width: '120',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        },{
            field: 'times',
            title: '时间',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2,
            formatter: function (value, row, index) {
                var s= $("input[type='radio'][name='dataStatus']:checked").val();
                if (s=='1'){
                    return value.substr(0,7);;
                } else if (s=='2'){
                    var as = value.substr(0,4);
                    var as2 = value.substr(5,2);
                    if (1<=as2 && as2<4){
                        return as+"年第一季度"
                    }else if (4<=as2 && as2<7){
                        return as+"年第二季度"
                    } else if (7<=as2 && as2<10){
                        return as+"年第三季度"
                    }else {
                        return as+"年第四季度"
                    }

                } else if (s=='3'){

                    return value.substr(0,4)+"年";

                } else {
                    return $("#startDay").val()+"至"+$("#endDay").val() ;
                }
            }
        }, {
            title: '优',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '良',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '轻度污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '中度污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '重度污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            title: '严重污染',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 2,
            rowsapn: 1
        }, {
            field: 'sumDays',
            title: '总天数',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'validDays',
            title: '有效天数',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'excellentAndGood',
            title: '优良天数',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'excellentAndGoodRatio',
            title: '优良比例',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2,
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }], [{
            field: 'excellentDays',
            title: '优天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'excellentRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'goodDays',
            title: '良天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'goodRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'mildDays',
            title: '轻度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'mildRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'moderateDays',
            title: '中度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'moderateRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'severeDays',
            title: '重度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'severeRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }, {
            field: 'seriousnessDays',
            title: '严重污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'seriousnessRatio',
            title: '比率',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value + "%";
                }
            }
        }]]
    }).bootstrapTable('hideLoading');
}

function hourTime() {
    $("#ds2").attr("style","display:show();");
    $("#ds3").attr("style","display:show();");
    $("#dss2").attr("style","display:show();");
    $("#dss3").attr("style","display:show();");
    $("#main2").show();
    $("#table2").attr("style","display:show();");
    $("#main").hide();
    index=1;
    var startMonth=$("#startMonth").val();
    var endMonth=$("#endMonth").val();
    var startYear=$("#startYear").val();
    var endYear=$("#endYear").val();
    var startDay=$("#startDay").val();
    var endDay=$("#endDay").val();
    var jd1=$("#jd1").val();
    var jd2=$("#jd2").val();

    siteCodes = getSiteCodes();
    if(siteCodes.length==0){
        layer.alert("请选择站点");
        return;
    }

    var rq = $("input[type='radio'][name='dataStatus']:checked").val();
    if (rq==1) {  // 按月份
        if (startMonth != "" && startMonth > endMonth && endMonth != "") {
            var errorMsg = "开始日期不能为空或大于结束日期！";
            layer.msg(errorMsg);
        } else if (startMonth == "" || endMonth == "") {
            var errorEndMsg = "日期不能为空！";
            layer.msg(errorEndMsg);
        }
    }else if (rq==2){  // 按季度
        if (startYear == endYear && jd1 > jd2) {
            var errorMsg = "开始季度不能大于结束季度！";
            layer.msg(errorMsg);
        }
        if (startYear != "" && startYear > endYear && endYear != "") {
            var errorMsg = "开始年份不能为空或大于结束年份！";
            layer.msg(errorMsg);
        } else if (startYear == "" || endYear == "") {
            var errorEndMsg = "年份不能为空！";
            layer.msg(errorEndMsg);
        }
    } else if (rq==3){  // 按年份
        if (startYear != "" && startYear > endYear && endYear != "") {
            var errorMsg = "开始年份不能为空或大于结束年份！";
            layer.msg(errorMsg);
        } else if (startYear == "" || endYear == "") {
            var errorEndMsg = "年份不能为空！";
            layer.msg(errorEndMsg);
        }
    }else if (rq==4) {
        if (startDay != "" && startDay > endDay && endDay != "") {
            var errorMsg = "开始日期不能为空或大于结束日期！";
            layer.msg(errorMsg);
        } else if (startDay == "" || endDay == "") {
            var errorEndMsg = "日期不能为空！";
            layer.msg(errorEndMsg);
        }
    }
    load2();
}
function dayTime() {
    $("#main").hide();
    $("#table2").attr("style","display:none;");
    $("#main2").hide();
    index=2;
    reLoad()
}
function monthTime() {
    $("#main").hide();
    $("#ds1").click();
    $("#startYear").hide();
    $("#endYear").hide();
    $("#jd1").hide();
    $("#jd2").hide();
    $("#startDay").hide();
    $("#endDay").hide();
    $("#startMonth").show();
    $("#endMonth").show()
    $("#jd1").hide();
    $("#jd2").hide();
    $("#table2").attr("style","display:none;");
    $("#main2").hide();
    index=3;
    reLoad()
}
// 加载list方法
function load30() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url :  prefix + "/list",
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
                dateType: $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
                startMonth: $("#startMonth").val(), // 开始月份
                endMonth: $("#endMonth").val(),     // 结束月份
                startYear: $("#startYear").val(),   // 开始年份
                endYear: $("#endYear").val(),       // 结束年份
                startDay: $("#startDay").val(),     // 开始日期
                endDay: $("#endDay").val(),         // 结束日期
                jd1: $("#jd1").val(),               // 开始季度
                jd2: $("#jd2").val(),               // 结束季度

                siteCodes:getSiteCodes().join(",")
            };
        },
        columns: [[{
            field: 'siteName',
            title: '站点名称',
            align: 'center',
            width: '160',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        },{
            field: 'times',
            title: '时间',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2,
            formatter: function (value, row, index) {
                var s= $("input[type='radio'][name='dataStatus']:checked").val();
                if (value) {
                    if (s=='1'){
                        return value.substr(0,7);;
                    } else if (s=='2'){
                        var as = value.substr(0,4);
                        var as2 = value.substr(5,2);
                        if (1<=as2 && as2<4){
                            return as+"年第一季度"
                        }else if (4<=as2 && as2<7){
                            return as+"年第二季度"
                        } else if (7<=as2 && as2<10){
                            return as+"年第三季度"
                        }else {
                            return as+"年第四季度"
                        }

                    } else if (s=='3'){

                        return value.substr(0,4)+"年";

                    } else {
                        return $("#startDay").val()+"至"+$("#endDay").val() ;
                    }
                }
            }
        }, {
            title: '空气质量类别分布(天数)',
            width: '600',
            align: 'center',
            valign: 'middle',
            colspan: 5,
            rowsapn: 1
        }, {
            title: '首要污染物分布（天数）',
            width: '380',
            align: 'center',
            valign: 'middle',
            colspan: 6,
            rowsapn: 1
        }, {
            field: 'excellentAndGoodRatio',
            title: '优良率(%) ',
            width: '100px',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'excellentAndGood',
            title: '优良天数 ',
            width: '100',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'validDays',
            title: '有效天数',
            width: '100',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        },{
            field: 'sumDays',
            title: '总天数',
            width: '100',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25GoodDays',
            title: 'PM2.5优良天数 ',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25sumDays',
            title: 'PM2.5有效天数 ',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25Ratio',
            title: 'PM2.5优良天数比例（%）',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }], [{
            field: 'excellentDays',
            title: '优天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'goodDays',
            title: '良天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'mildDays',
            title: '轻度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'moderateDays',
            title: '中度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'severeDays',
            title: '重度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'pm25days',
            title: 'PM2.5',
            align: 'center',
            valign: 'middle'
        },  {
            field: 'pm10days',
            title: 'PM10',
            align: 'center',
            valign: 'middle'
        },{
            field: 'o3days',
            title: 'O3',
            align: 'center',
            valign: 'middle'
        },{
            field: 'no2days',
            title: 'NO2',
            align: 'center',
            valign: 'middle'
        },{
            field: 'so2days',
            title: 'SO2',
            align: 'center',
            valign: 'middle'
        },{
            field: 'codays',
            title: 'CO',
            align: 'center',
            valign: 'middle',
        }]]
    }).bootstrapTable('hideLoading');
}

// 加载list方法
function load30area() {
    var stationType =$("input[type='radio'][name='stationType']:checked").val();
    if (stationType==""){
        stationType="0"
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url :  prefix + "/list",
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
        fixedNumber: 2,
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"Server"
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                dateType: $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
                dbValue: $("input[type='radio'][name='db']:checked").val(), // 获取数据源选项值
                examineType: $("input[type='radio'][name='examineType']:checked").val(), // 获取数据源选项值
                siteType: stationType,  // 站点类型
                startMonth: $("#startMonth").val(), // 开始月份
                endMonth: $("#endMonth").val(),     // 结束月份
                startYear: $("#startYear").val(),   // 开始年份
                endYear: $("#endYear").val(),       // 结束年份
                startDay: $("#startDay").val(),     // 开始日期
                endDay: $("#endDay").val(),         // 结束日期
                jd1: $("#jd1").val(),               // 开始季度
                jd2: $("#jd2").val(),               // 结束季度

                siteCodes:getSiteCodes().join(",")
            };
        },
        columns: [[{
            field: 'areaName',
            title: '区域',
            align: 'center',
            width: '100',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'times',
            title: '时间',
            width: '160',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2,
            formatter: function (value, row, index) {
                var s= $("input[type='radio'][name='dataStatus']:checked").val();
                if (s=='1'){
                    return value.substr(0,7);;
                } else if (s=='2'){
                    var as = value.substr(0,4);
                    var as2 = value.substr(5,2);
                    if (1<=as2 && as2<4){
                        return as+"年第一季度"
                    }else if (4<=as2 && as2<7){
                        return as+"年第二季度"
                    } else if (7<=as2 && as2<10){
                        return as+"年第三季度"
                    }else {
                        return as+"年第四季度"
                    }

                } else if (s=='3'){

                    return value.substr(0,4)+"年";

                } else {
                    return $("#startDay").val()+"至"+$("#endDay").val() ;
                }
            }
        }, {
            title: '空气质量类别分布(天数)',
            width: '600',
            align: 'center',
            valign: 'middle',
            colspan: 5,
            rowsapn: 1
        }, {
            title: '首要污染物分布（天数）',
            width: '380',
            align: 'center',
            valign: 'middle',
            colspan: 6,
            rowsapn: 1
        }, {
            field: 'excellentAndGoodRatio',
            title: '优良率(%) ',
            width: '100px',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'excellentAndGood',
            title: '优良天数 ',
            width: '100',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'validDays',
            title: '有效天数',
            width: '100',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        },{
            field: 'sumDays',
            title: '总天数',
            width: '100',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25GoodDays',
            title: 'PM2.5优良天数 ',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25sumDays',
            title: 'PM2.5有效天数 ',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }, {
            field: 'pm25Ratio',
            title: 'PM2.5优良天数比例（%）',
            width: '208',
            align: 'center',
            valign: 'middle',
            colspan: 1,
            rowspan: 2
        }], [{
            field: 'excellentDays',
            title: '优天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'goodDays',
            title: '良天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'mildDays',
            title: '轻度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'moderateDays',
            title: '中度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'severeDays',
            title: '重度污染天数',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'pm25days',
            title: 'PM2.5',
            align: 'center',
            valign: 'middle'
        },  {
            field: 'pm10days',
            title: 'PM10',
            align: 'center',
            valign: 'middle'
        },{
            field: 'o3days',
            title: 'O3',
            align: 'center',
            valign: 'middle'
        },{
            field: 'no2days',
            title: 'NO2',
            align: 'center',
            valign: 'middle'
        },{
            field: 'so2days',
            title: 'SO2',
            align: 'center',
            valign: 'middle'
        },{
            field: 'codays',
            title: 'CO',
            align: 'center',
            valign: 'middle',
        }]]
    }).bootstrapTable('hideLoading');
}
//查询
function reLoad(){
    var startMonth=$("#startMonth").val();
    var endMonth=$("#endMonth").val();
    var startYear=$("#startYear").val();
    var endYear=$("#endYear").val();
    var startDay=$("#startDay").val();
    var endDay=$("#endDay").val();
    var jd1=$("#jd1").val();
    var jd2=$("#jd2").val();

    siteCodes = getSiteCodes();
    if(siteCodes.length==0){
        layer.alert("请选择站点");
        return;
    }

    var rq = $("input[type='radio'][name='dataStatus']:checked").val();
    if (rq==1) {  // 按月份
        if (startMonth != "" && startMonth > endMonth && endMonth != "") {
            var errorMsg = "开始日期不能为空或大于结束日期！";
            layer.msg(errorMsg);
        } else if (startMonth == "" || endMonth == "") {
            var errorEndMsg = "日期不能为空！";
            layer.msg(errorEndMsg);
        }
    }else if (rq==2){  // 按季度
        if (startYear == endYear && jd1 > jd2) {
            var errorMsg = "开始季度不能大于结束季度！";
            layer.msg(errorMsg);
        }
        if (startYear != "" && startYear > endYear && endYear != "") {
            var errorMsg = "开始年份不能为空或大于结束年份！";
            layer.msg(errorMsg);
        } else if (startYear == "" || endYear == "") {
            var errorEndMsg = "年份不能为空！";
            layer.msg(errorEndMsg);
        }
    } else if (rq==3){  // 按年份
        if (startYear != "" && startYear > endYear && endYear != "") {
            var errorMsg = "开始年份不能为空或大于结束年份！";
            layer.msg(errorMsg);
        } else if (startYear == "" || endYear == "") {
            var errorEndMsg = "年份不能为空！";
            layer.msg(errorEndMsg);
        }
    }else if (rq==4) {
        if (startDay != "" && startDay > endDay && endDay != "") {
            var errorMsg = "开始日期不能为空或大于结束日期！";
            layer.msg(errorMsg);
        } else if (startDay == "" || endDay == "") {
            var errorEndMsg = "日期不能为空！";
            layer.msg(errorEndMsg);
        }
    }
    if (index==0){
        $("#ds2").attr("style","display:show();");
        $("#ds3").attr("style","display:show();");
        $("#dss2").attr("style","display:show();");
        $("#dss3").attr("style","display:show();");
        load();
    } else if (index == 1) {
        $("#ds2").attr("style","display:show();");
        $("#ds3").attr("style","display:show();");
        $("#dss2").attr("style","display:show();");
        $("#dss3").attr("style","display:show();");
        initTime();
    }else if (index == 2) {
        $("#ds2").attr("style","display:show();");
        $("#ds3").attr("style","display:show();");
        $("#dss2").attr("style","display:show();");
        $("#dss3").attr("style","display:show();");
        load30();

    }else if (index == 3) {
        $("#ds2").attr("style","display:none;");
        $("#ds3").attr("style","display:none;");
        $("#dss2").attr("style","display:none;");
        $("#dss3").attr("style","display:none;");
        load4();
    }


}
// 加载list方法
function load4() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url :  prefix + "/list4",
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
                dateType: $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
                startMonth: $("#startMonth").val(), // 开始月份
                endMonth: $("#endMonth").val(),     // 结束月份
                startYear: $("#startYear").val(),   // 开始年份
                endYear: $("#endYear").val(),       // 结束年份
                startDay: $("#startDay").val(),     // 开始日期
                endDay: $("#endDay").val(),         // 结束日期
                jd1: $("#jd1").val(),               // 开始季度
                jd2: $("#jd2").val(),               // 结束季度
                siteCodes:getSiteCodes().join(",")
            };
        },
        onLoadSuccess:function(data){
            //通过对data判断
            qualityList = data.rows;
            let station = [];
            let excellentDays =[];
            let goodDays =[];
            let mildDays =[];
            let moderateDays =[];
            let severeDays =[];
            let seriousnessDays =[];
            for(let i = 0; i < data.rows.length; i++) {
                station.push(data.rows[i].siteName);
                excellentDays.push(data.rows[i].excellentDays);
                goodDays.push(data.rows[i].goodDays);
                mildDays.push(data.rows[i].mildDays);
                moderateDays.push(data.rows[i].moderateDays);
                severeDays.push(data.rows[i].severeDays);
                seriousnessDays.push(data.rows[i].seriousnessDays);
            }
            drawEchars(station,excellentDays,goodDays,mildDays,moderateDays,severeDays,seriousnessDays);
        },
        columns: [{
            field: 'siteName',
            title: '站点名称(类型)',
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
                var s= $("input[type='radio'][name='dataStatus']:checked").val();
                if (value) {
                    if (s=='1'){
                        return value.substr(0,7);;
                    } else if (s=='2'){
                        var as = value.substr(0,4);
                        var as2 = value.substr(5,2);
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
                        return $("#startDay").val()+"至"+$("#endDay").val() ;
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
    }).bootstrapTable('hideLoading');
}

// 加载list方法
function load4area() {
    var stationType =$("input[type='radio'][name='stationType']:checked").val();
    if (stationType==""){
        stationType="0"
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method : 'get', // 服务器数据的请求方式 get or post
        url :  prefix + "/list4",
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
        fixedNumber: 2,
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"Server"
        queryParams : function(params) {
            return {
                pageSize : params.limit,
                pageNumber : (params.offset / params.limit) + 1,
                dateType: $("input[type='radio'][name='dataStatus']:checked").val(), //获取日期类型
                dbValue: $("input[type='radio'][name='db']:checked").val(), // 获取数据源选项值
                examineType: $("input[type='radio'][name='examineType']:checked").val(), // 获取数据源选项值
                siteType: stationType,  // 站点类型
                startMonth: $("#startMonth").val(), // 开始月份
                endMonth: $("#endMonth").val(),     // 结束月份
                startYear: $("#startYear").val(),   // 开始年份
                endYear: $("#endYear").val(),       // 结束年份
                startDay: $("#startDay").val(),     // 开始日期
                endDay: $("#endDay").val(),         // 结束日期
                jd1: $("#jd1").val(),               // 开始季度
                jd2: $("#jd2").val(),               // 结束季度
                siteCodes:getSiteCodes().join(",")
            };
        },
        onLoadSuccess:function(data){
            //通过对data判断
            qualityList = data.rows;
            let station = [];
            let excellentDays =[];
            let goodDays =[];
            let mildDays =[];
            let moderateDays =[];
            let severeDays =[];
            let seriousnessDays =[];
            for(let i = 0; i < data.rows.length; i++) {
                station.push(data.rows[i].siteName);
                excellentDays.push(data.rows[i].excellentDays);
                goodDays.push(data.rows[i].goodDays);
                mildDays.push(data.rows[i].mildDays);
                moderateDays.push(data.rows[i].moderateDays);
                severeDays.push(data.rows[i].severeDays);
                seriousnessDays.push(data.rows[i].seriousnessDays);
            }
            drawEchars(station,excellentDays,goodDays,mildDays,moderateDays,severeDays,seriousnessDays);
        },
        columns: [ {
            field: 'times',
            title: '时间',
            width: '160px',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var s= $("input[type='radio'][name='dataStatus']:checked").val();
                if (s=='1'){
                    return value.substr(0,7);;
                } else if (s=='2'){
                    var as = value.substr(0,4);
                    var as2 = value.substr(5,2);
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
                    return $("#startDay").val()+"至"+$("#endDay").val() ;
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
    }).bootstrapTable('hideLoading');
}

//导出
function exportExcel() {
    var startMonth=$("#startMonth").val();
    var endMonth=$("#endMonth").val();
    var startYear=$("#startYear").val();
    var endYear=$("#endYear").val();
    var startDay=$("#startDay").val();
    var endDay=$("#endDay").val();
    var jd1=$("#jd1").val();
    var jd2=$("#jd2").val();

    siteCodes = getSiteCodes();
    if(siteCodes.length==0){
        layer.alert("请选择站点");
        return;
    }

    var rq = $("input[type='radio'][name='dataStatus']:checked").val();
    if (rq==1) {  // 按月份
        if (startMonth != "" && startMonth > endMonth && endMonth != "") {
            var errorMsg = "开始日期不能为空或大于结束日期！";
            layer.msg(errorMsg);
        } else if (startMonth == "" || endMonth == "") {
            var errorEndMsg = "日期不能为空！";
            layer.msg(errorEndMsg);
        }
    }else if (rq==2){  // 按季度
        if (startYear == endYear && jd1 > jd2) {
            var errorMsg = "开始季度不能大于结束季度！";
            layer.msg(errorMsg);
        }
        if (startYear != "" && startYear > endYear && endYear != "") {
            var errorMsg = "开始年份不能为空或大于结束年份！";
            layer.msg(errorMsg);
        } else if (startYear == "" || endYear == "") {
            var errorEndMsg = "年份不能为空！";
            layer.msg(errorEndMsg);
        }
    } else if (rq==3){  // 按年份
        if (startYear != "" && startYear > endYear && endYear != "") {
            var errorMsg = "开始年份不能为空或大于结束年份！";
            layer.msg(errorMsg);
        } else if (startYear == "" || endYear == "") {
            var errorEndMsg = "年份不能为空！";
            layer.msg(errorEndMsg);
        }
    }else if (rq==4) {
        if (startDay != "" && startDay > endDay && endDay != "") {
            var errorMsg = "开始日期不能为空或大于结束日期！";
            layer.msg(errorMsg);
        } else if (startDay == "" || endDay == "") {
            var errorEndMsg = "日期不能为空！";
            layer.msg(errorEndMsg);
        }
    }
    if(index == 0){
        layer.confirm('确定要导出当前记录？', {
            btn: ['确定', '取消']
        }, function (index) {

            layer.close(index);
            var url = "";
            var a = prefix + "/exportExcel1?dateType=" + $("input[type='radio'][name='dataStatus']:checked").val();

            var d = "&startMonth=" + $("#startMonth").val() ;
            var e = "&endMonth=" + $("#endMonth").val() ;
            var f = "&startYear=" +$("#startYear").val()+"&endYear=" + $("#endYear").val()+"&startDay=" + $("#startDay").val() ;
            var g = "&endDay=" +$("#endDay").val()+"&jd1=" + $("#jd1").val()+"&jd2=" +$("#jd2").val() ;
            var h = "&siteCodes=" + getSiteCodes().join(",");
            window.location.href = url+a+d+e+f+g+h;
        })
    }else if (index ==1){
        layer.confirm('确定要导出当前记录？', {
            btn: ['确定', '取消']
        }, function (index) {

        layer.close(index);
        var url = "";
        var a = prefix + "/exportExcel2?dateType=" + $("input[type='radio'][name='dataStatus']:checked").val();

        var d = "&startMonth=" + $("#startMonth").val() ;
        var e = "&endMonth=" + $("#endMonth").val() ;
        var f = "&startYear=" +$("#startYear").val()+"&endYear=" + $("#endYear").val()+"&startDay=" + $("#startDay").val() ;
        var g = "&endDay=" +$("#endDay").val()+"&jd1=" + $("#jd1").val()+"&jd2=" +$("#jd2").val() ;
        var h = "&siteCodes=" + getSiteCodes().join(",");
        window.location.href = url+a+d+e+f+g+h;
    })
    } else if (index ==2){

        layer.confirm('确定要导出当前记录？', {
            btn: ['确定', '取消']
        }, function (index) {

            layer.close(index);
            var url = "";
            var a = prefix + "/exportExcel3?dateType=" + $("input[type='radio'][name='dataStatus']:checked").val();
            var d = "&startMonth=" + $("#startMonth").val() ;
            var e = "&endMonth=" + $("#endMonth").val() ;
            var f = "&startYear=" +$("#startYear").val()+"&endYear=" + $("#endYear").val()+"&startDay=" + $("#startDay").val() ;
            var g = "&endDay=" +$("#endDay").val()+"&jd1=" + $("#jd1").val()+"&jd2=" +$("#jd2").val() ;
            var h = "&siteCodes=" + getSiteCodes().join(",");
            window.location.href = url+a+d+e+f+g+h;
        })

    } else if (index==3){
        layer.confirm('确定要导出当前记录？', {
            btn: ['确定', '取消']
        }, function (index) {

            layer.close(index);
            var url = "";
            var a = prefix + "/exportExcel4?dateType=" + $("input[type='radio'][name='dataStatus']:checked").val();
            var d = "&startMonth=" + $("#startMonth").val() ;
            var e = "&endMonth=" + $("#endMonth").val() ;
            var f = "&startYear=" +$("#startYear").val()+"&endYear=" + $("#endYear").val()+"&startDay=" + $("#startDay").val() ;
            var g = "&endDay=" +$("#endDay").val()+"&jd1=" + $("#jd1").val()+"&jd2=" +$("#jd2").val() ;
            var h = "&siteCodes=" + getSiteCodes().join(",");
            window.location.href = url+a+d+e+f+g+h;
        })

    }


}


function drawEchars(station,excellentDays,goodDays,mildDays,moderateDays,severeDays,seriousnessDays) {
var myChart = echarts.init(document.getElementById('main'));
    myChart.setOption({
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['优','良','轻度污染','中度污染','重度污染','严重污染']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox : {
        show : true,
        feature : {
            magicType : {
                show : true,
                type : [ 'line', 'bar']
            },
            restore : {
                show : true
            },
            dataView:{
                show : true,
                readOnly:true
            },
            saveAsImage : {
                show : true,
                title : '保存为图片',
                type : 'png',
                lang : [ '点击保存' ]
            }
        }
    },
    xAxis : [
        {
            type : 'category',
            data :station
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'优',
            type:'bar',
            color:'#00FF3C',
            data:excellentDays
        },
        {
            name:'良',
            type:'bar',
            color:'#DBFF41',
            data:goodDays
        },
        {
            name:'轻度污染',
            type:'bar',
            color:'#FF8127',
            data:mildDays
        },
        {
            name:'中度污染',
            type:'bar',
            color:'#FF001A',
            data:moderateDays
        },
        {
            name:'重度污染',
            type:'bar',
            color:'#89004C',
            data:severeDays
        },
        {
            name:'严重污染',
            type:'bar',
            color:'#800025',
            data:seriousnessDays
        }
    ]
});
}

function getSiteCodes() {
    return $("#siteTree").jstree().get_bottom_selected();
}

function tree1() {
    $.ajax({
        type : "GET",
        url : "/site/tree/single/" + "2",
        data : {},
        success : function(data) {
            $('#siteTree').jstree({
                'core' : {
                    'data' : data.data
                },
                "checkbox" : {
                    "keep_selected_style" : false
                },
                "plugins" : ["search","checkbox"]
            });
        }
    });
    $('#siteTree').on("ready.jstree", function(e, data){
        var inst = data.instance;
        inst.open_node("#000");
        // inst.select_node($('li[aria-level="2"]:eq(0)'));
        var obj=inst.get_node(e.target.firstChild.firstChild.lastChild);
        inst.select_node(obj);
        siteCodes = $('#siteTree').jstree().get_bottom_selected();
        reLoad();
    });

    $('#siteTree').on("changed.jstree", function (e, data) {
        siteCodes = $('#siteTree').jstree().get_bottom_selected();
    });
}