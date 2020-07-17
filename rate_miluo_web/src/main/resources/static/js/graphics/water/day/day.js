
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
    let day = $("#dayForm .dateRange").val();
    if(!day){
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
        refreshObj('3');
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
        url: "/graphic/water/day/list",
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
        let sw = [];
        let ddl = [];
        let codmn = [];
        let pb = [];
        let rjy = [];
        let ph = [];
        let shen = [];
        let nh3N = [];
        let comprehensiveToxicity = [];
        let tp = [];
        let cd = [];
        let kmn = [];
        let temp = [];
        let humi = [];
        let yls = [];
        let llz = [];
        $.each(siteData, function (idx, site) {
            sw.push(site.sw?site.sw:'-');
            ddl.push(site.ddl?site.ddl:'-');
            codmn.push(site.codmn?site.codmn:'-');
            pb.push(site.pb?site.pb:'-');
            rjy.push(site.rjy?site.rjy:'-');
            ph.push(site.ph?site.ph:'-');
            shen.push(site.shen?site.shen:'-');
            nh3N.push(site.nh3N?site.nh3N:'-');
            comprehensiveToxicity.push(site.comprehensiveToxicity?site.comprehensiveToxicity:'-');
            tp.push(site.tp?site.tp:'-');
            cd.push(site.cd?site.cd:'-');
            kmn.push(site.kmn?site.kmn:'-');
            temp.push(site.temp?site.temp:'-');
            humi.push(site.humi?site.humi:'-');
            yls.push(site.yls?site.yls:'-');
            llz.push(site.llz?site.llz:'-');
        });
        // if(standard){
        // item = ['AQI', 'NO2', 'PM2.5', 'PM10', 'SO2', 'CO', 'O3'];
        oneChart = {
            siteName: siteName,
            items: Object.values(waterPol),
            xTitle: siteData.map(function (d) {
                return d.dateTime.substr(0, 10);
            }),
            data: [{
                name: waterPol['sw'],
                data: sw,
                type: 'line',
            }, {
                name: waterPol['ddl'],
                data: ddl,
                type: 'line',
            }, {
                name: waterPol['codmn'],
                data: codmn,
                type: 'line',
            }, {
                name: waterPol['pb'],
                data: pb,
                type: 'line',
            }, {
                name: waterPol['rjy'],
                data: rjy,
                type: 'line',
            }, {
                name: waterPol['ph'],
                data: ph,
                type: 'line',
            }, {
                name: waterPol['shen'],
                data: shen,
                type: 'line',
            }, {
                name: waterPol['nh3N'],
                data: nh3N,
                type: 'line',
            }, {
                name: waterPol['comprehensiveToxicity'],
                data: comprehensiveToxicity,
                type: 'line',
            }, {
                name: waterPol['tp'],
                data: tp,
                type: 'line',
            }, {
                name: waterPol['cd'],
                data: cd,
                type: 'line',
            }, {
                name: waterPol['kmn'],
                data: kmn,
                type: 'line',
            }, {
                name: waterPol['temp'],
                data: temp,
                type: 'line',
            }, {
                name: waterPol['humi'],
                data: humi,
                type: 'line',
            }, {
                name: waterPol['yls'],
                data: yls,
                type: 'line',
            }, {
                name: waterPol['llz'],
                data: llz,
                type: 'line',
            }]
        };
        ++divIndex;
        let $chartDay = '<div id="dayChartsContent'+divIndex+'" class="chartsContent"></div><hr>';
        $("#dayChartsContents").append($chartDay);
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
            type: 'scroll',
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
            position: "left",
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