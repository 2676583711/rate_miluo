let windChartArr = [];
let windObj = {
    allData: []
};

let windChartIndex = 0;

function initWindContent() {
    initWindHeight();
    bindWindEvent();
    searchWindData();
}


let initWindHeight = function () {
    let h2 = $("#" + selectTabArr[selectTab]).outerHeight(true);
    let h3 = $(".layui-tab-title").outerHeight(true);
    $("#windChartsContents").css({"height": (window.innerHeight - h2 - h3) + "px"})
}

let bindWindEvent = function () {
    $("#windType").on("change", function (e) {
        let selValue = $(this).val();
        if (selValue == "windOp1") {
            windOP1Content();
        } else if (selValue == "windOp2") {
            windOP2Content();
        }
    });
    $("#windSearch").on("click", function (e) {
        searchWindData();
    });
    $("#windRefresh").on("click", function (e) {
        refreshObj('3');
    });

}

function windOP1Content() {
    $("#windForm .dateRange").removeClass("hidden")
    $("#windForm .year").addClass("hidden")
    searchWindData();
}

function windOP2Content() {
    $("#windForm .year").removeClass("hidden")
    $("#windForm .dateRange").addClass("hidden")
    searchWindData();
}

function searchWindData() {
    showLoad();
    let dateRange = $("#windForm .dateRange").val();
    let siteCodes = $.siteTree.siteCodes();
    if(siteCodes.length === 0){
        closeLoad();
    	layer.alert("未选择站点！");
    	return;
    }
    $.ajax({
        type: "get",
        url: "/graphic/air/wind/list",
        data: {
            dateRange: dateRange,
            siteCodes: siteCodes.join()
        },
        success: function (res) {
            windObj = {
                allData: []
            };
            windObj.allData = res.wds;
            initWindChartData();
            closeLoad();
        },
        error: function (e) {
            closeLoad();
        }
    })
}

function clearWindDiv() {
    $.each(windChartArr, function (idx, chart) {
        try {
            chart.clear();
        } catch (e) {
        }
    });
    windChartArr = [];
    $("#windChartsContents").html("");
}

function initWindChartData() {
    clearWindDiv()
    $.each(windObj.allData, function(siteName, siteData){
        let oneChart = {
            siteName: siteName,
            wds: siteData.wds,
            wss: siteData.wss,
        };
        ++windChartIndex;
        let $chartWind = '<div id="windChartsContent'+windChartIndex+'" style="width: 50%;display: inline-block" class="chartsContent"></div>'
        $("#windChartsContents").append($chartWind);
        drawWindChart("windChartsContent"+windChartIndex, oneChart);
    });
}

let drawWindChart = function (element, inData) {
    let option = {
        title: {
            text: inData.siteName
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            },
        },
        angleAxis: {
            type: 'category',
            // data: ['N', 'NNE', 'NE', 'ENE', 'E', 'ESE', 'SE', 'SSE', 'S', 'SSW', 'SW', 'WSW', 'W', 'WNW', 'NW', 'NNW'],
            data: ['北', '北东北', '东北', '东东北', '东', '东东南' ,'南', '南东南', '南' ,'南西南', '西南', '西西南' ,'西', '西西北', '西北', '北西北'],
            z: 0,
            boundaryGap: false,
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#ddd',
                    type: 'solid'
                }
            },
            axisLine: {
                show: false
            }
        },
        radiusAxis: {},
        polar: {},
        series: [{
            type: 'bar',
            data: inData.wds,
            coordinateSystem: 'polar',
            symbolSize: function (val) {
                return val[2] * 2;
            },
            name: '风频 %',
            animationDelay: function (idx) {
                return idx * 5;
            },
            stack: 'a',
            itemStyle: {
                normal: {
                    color: 'rgb(124, 181, 236)'
                }
            }
        }, {
            type: 'bar',
            data: inData.wss,
            coordinateSystem: 'polar',
            name: '风速 m/s',
            stack: 'a',
            symbolSize: function (val) {
                return val[2] * 2;
            },
            animationDelay: function (idx) {
                return idx * 5;
            },
            itemStyle: {
                normal: {
                    color: 'rgb(67, 67, 72)'
                }
            }
        }],
        legend: {
            show: true,
            right: 'right',
            bottom: 'middle',
            data: ['风频 %', '风速 m/s'],
        },
        color: []
    };

    // 基于准备好的dom，初始化echarts图表
    var windChart = echarts.init(document.getElementById(element));
    windChart.setOption(option);
    windChartArr.push(windChart);
}


