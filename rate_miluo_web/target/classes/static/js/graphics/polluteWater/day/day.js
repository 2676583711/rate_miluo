
let dayChartArr = [];
let dayObj = {
    allData: [],
    siteData: {}
};

function initDayContent(){
    initDayHeight();
    bindDayEvent();
    initDaySearch();
}
function initDaySearch(){
    let hour = $("#dayForm .dateRange").val();
    if(!hour){
        setTimeout(function () {
            initDaySearch();
        }, 20)
    }else{
        searchDayData();
    }
}

function initDayHeight(){
    var h2 = $("#" + selectTabArr[selectTab]).outerHeight(true);
    var h3 = $(".layui-tab-title").outerHeight(true);
    $("#dayChartsContents").css({"height" : (window.innerHeight - h2 - h3) + "px"})
}

function bindDayEvent() {
    $("#daySearch").on("click", function (e) {
        searchDayData();
    });
    $("#dayRefresh").on("click", function (e) {
        refreshObj('2');
    });
}

function searchDayData() {
    showLoad();
    let dateRange = $("#dayForm .dateRange").val();
    // let stationCodeMicro="";
    // let stationCodeStandard="";
    let siteCodes=$.siteTree.siteCodes();
    if(siteCodes.length === 0){
        closeLoad();
    	layer.alert("未选择站点！");
    	return;
    }
    $.ajax({
        type: "get",
        url: "/graphic/polluteWater/day/list",
        data: {
            dateRange: dateRange,
            // stationCodeMicro: stationCodeMicro,
            // stationCodeStandard: stationCodeStandard
            siteCodes: siteCodes.join()
        },
        dataType: "json",
        success: function (res) {
            let data = res.data;
            try {
                dayObj = {
                    allData: [],
                    siteData: {}
                };
                if(data){
                    $.each(data.data, function (siteName, siteData) {
                        if(siteData.length>0){
                            dayObj.siteData[siteName] = siteData;
                        }
                    });
                }
                drawDayImg();
            }catch (e) {
                console.error(e);
            }
            closeLoad();
        }
    })
}

function clearDayDiv(){
    $.each(dayChartArr, function (idx, chart) {
        try{
            chart.clear();
        }catch (e) {}
    });
    dayChartArr = [];
    $("#dayChartsContents").html("");
}


function drawDayImg() {
    clearDayDiv();
    let divIndex = 0;
    $.each(dayObj.siteData, function(siteName, siteData) {
        // let siteType = siteName.substr(0, 1);
        var standard = siteName.indexOf('(标准站)');
        // if (standard>=0) {
        //     standard = true;
        //     // siteName = siteName.substring(0, standard);
        // }
        let oneChart;
        let bo1 = [];
        let cd = [];
        let cu = [];
        let pb = [];
        let ph = [];
        let shen = [];
        let zn = [];
        let tp = [];
        let tn = [];
        let cod = [];
        let nh3 = [];
        $.each(siteData, function (idx, siteDataItem) {
            bo1.push(siteDataItem.bo1);
            cd.push(siteDataItem.cd);
            cu.push(siteDataItem.cu);
            pb.push(siteDataItem.pb);
            ph.push(siteDataItem.ph);
            shen.push(siteDataItem.shen);
            zn.push(siteDataItem.zn);
            tp.push(siteDataItem.tp);
            tn.push(siteDataItem.tn);
            cod.push(siteDataItem.cod);
            nh3.push(siteDataItem.nh3);
        });
        // if(standard){
        // item = ['AQI', 'NO2', 'PM2.5', 'PM10', 'SO2', 'CO', 'O3'];
        oneChart = {
            siteName: siteName,
            items: Object.values(polluteWaterPol),
            xTitle: siteData.map(function (d) {
                return d.recordingTime
            }),
            data: [{
                name: '排放量(L/s)',
                data: bo1,
                type: 'line',
                yAxisIndex: 1
            }, {
                name: '总镉(ug/L)',
                data: cd,
                type: 'line',
                yAxisIndex: 2
            }, {
                name: '总铜(mg/L)',
                data: cu,
                type: 'line',
                yAxisIndex: 0
            }, {
                name: '总铅(ug/L)',
                data: pb,
                type: 'line',
                yAxisIndex: 2
            }, {
                name: 'ph',
                data: ph,
                type: 'line',
                yAxisIndex: 3
            }, {
                name: '总砷(ug/L)',
                data: shen,
                type: 'line',
                yAxisIndex: 2
            }, {
                name: '总锌(mg/L)',
                data: zn,
                type: 'line',
                yAxisIndex: 0
            },{
                name: '总磷(mg/L)',
                data: tp,
                type: 'line',
                yAxisIndex: 0
            },{
                name: '总氮(mg/L)',
                data: tn,
                type: 'line',
                yAxisIndex: 0
            },{
                name: 'COD(mg/L)',
                data: cod,
                type: 'line',
                yAxisIndex: 0
            },{
                name: '氨氮(mg/L)',
                data: nh3,
                type: 'line',
                yAxisIndex: 0
            }]
        };
        ++divIndex;
        let $chartHour = '<div id="dayChartsContent'+divIndex+'" class="chartsContent"></div><hr>';
        $("#dayChartsContents").append($chartHour);
        initDayChartImg("dayChartsContent"+divIndex, oneChart);
    });

}

function initDayChartImg(element, inData) {
    var option = {
        title: {
            text: inData.siteName
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
            data: inData.items,
            bottom: 'bottom',
            textStyle: {
                fontSize: 12
            }
        },
        xAxis: {
            type: 'category',
            data: inData.xTitle
        },
        yAxis: [{
            type: 'value',
            name: "浓度 ：(mg/L)",
            position: "left",
            nameGap: 35,
            nameLocation: 'middle',
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        },{
            type: 'value',
            name: " 排放量 ：(L/s)",
            position: "right",
            // nameGap: 40,
            // nameLocation: 'middle',
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        },{
            type: 'value',
            name: "浓度 ：(ug/L)",
            position: "left",
            offset: 60,
            nameGap: 35,
            nameLocation: 'middle',
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        },{
            type: 'value',
            name: "ph",
            position: "right",
            offset: 70,
            // nameGap: 35,
            // nameLocation: 'middle',
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        }],
        series: inData.data
    };
    // 基于准备好的dom，初始化echarts图表
    var dayChart = echarts.init(document.getElementById(element));
    dayChart.setOption(option);
    dayChartArr.push(dayChart);
}