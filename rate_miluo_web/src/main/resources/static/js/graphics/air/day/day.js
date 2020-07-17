
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
        url: "/graphic/air/day/list",
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
        if (standard>=0) {
            standard = true;
            // siteName = siteName.substring(0, standard);
        }
        let item;
        let oneChart;
        let aqi = [];
        let no2 = [];
        let pm25 = [];
        let pm10 = [];
        let so2 = [];
        let co = [];
        let o3 = [];
        $.each(siteData, function (idx, siteDataItem) {
            aqi.push(siteDataItem.aqi);
            no2.push(siteDataItem.no2);
            pm25.push(siteDataItem.pm25);
            pm10.push(siteDataItem.pm10);
            so2.push(siteDataItem.so2);
            co.push(siteDataItem.co);
            o3.push(siteDataItem.o3eightHour);
        });
        if(standard){
            item = ['AQI', 'NO2', 'PM2.5', 'PM10', 'SO2', 'CO', 'O3'];
            oneChart = {
                siteName: siteName,
                items: item,
                xTitle: siteData.map(function(d){return d.queryTime}),
                data: [{
                    name: 'AQI',
                    data: aqi,
                    type: 'line',
                    yAxisIndex: 2
                },{
                    name: 'NO2',
                    data: no2,
                    type: 'line',
                    yAxisIndex: 1
                },{
                    name: 'PM2.5',
                    data: pm25,
                    type: 'line',
                    yAxisIndex: 0
                },{
                    name: 'PM10',
                    data: pm10,
                    type: 'line',
                    yAxisIndex: 0
                },{
                    name: 'SO2',
                    data: so2,
                    type: 'line',
                    yAxisIndex: 0
                },{
                    name: 'CO',
                    data: co,
                    type: 'line',
                    yAxisIndex: 0
                },{
                    name: 'O3',
                    data: o3,
                    type: 'line',
                    yAxisIndex: 0
                }]
            };
        }else{
            item = ['AQI', 'NO2', 'PM2.5', 'PM10'];
            oneChart = {
                siteName: siteName,
                items: item,
                xTitle: siteData.map(function(d){return d.queryTime}),
                data: [{
                    name: 'AQI',
                    data: aqi,
                    type: 'line',
                    yAxisIndex: 2
                },{
                    name: 'NO2',
                    data: no2,
                    type: 'line',
                    yAxisIndex: 1
                },{
                    name: 'PM2.5',
                    data: pm25,
                    type: 'line',
                    yAxisIndex: 0
                },{
                    name: 'PM10',
                    data: pm10,
                    type: 'line',
                    yAxisIndex: 0
                }]
            };
        }

        ++divIndex;
        let $chartHour = '<div id="dayChartsContent'+divIndex+'" class="chartsContent"></div><hr>';
        if(standard){
            $("#dayChartsContents").prepend($chartHour);
        }else{
            $("#dayChartsContents").append($chartHour);
        }
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
                fontSize: 16
            }
        },
        xAxis: {
            type: 'category',
            data: inData.xTitle
        },
        yAxis: [{
            type: 'value',
            name: "浓度 ：μg/m³",
            position: "left",
            nameGap: 35,
            nameLocation: 'middle',
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        },{
            type: 'value',
            name: "CO 浓度 ：mg/m³",
            position: "right",
            nameGap: 40,
            nameLocation: 'middle',
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        },{
            type: 'value',
            name: "AQI",
            position: "left",
            offset: 70,
            nameGap: 35,
            nameLocation: 'middle',
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