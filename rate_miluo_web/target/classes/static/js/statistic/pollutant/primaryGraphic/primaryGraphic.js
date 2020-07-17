let priChartArr = [];
function initPrimaryChartContent() {
    clearPriDiv();
    initPriHeight();
    loadPrimaryChart();
}
let initPriHeight = function () {
    var h3 = $("#myTab").outerHeight(true);
    var h4 = $("#selDiv").outerHeight(true);
    $("#primaryChartsContents").css({"height": (window.innerHeight - h3 - h4 - 15) + "px"})
};

function loadPrimaryChart() {
    let startTime = $('.time-type-start').not('.hidden').val();
    let endTime = $('.time-type-end').not('.hidden').val();
    let timeType = $("input[name='time']:checked").val();  // 时间类型

    $.ajax({
        type: 'get',
        url: prefix + '/primary/graphic',
        data: {
            startDate: startTime,
            endDate: endTime,
            timeType: timeType,
            siteCodes: siteCodes.join()
        },
        success: function (res) {
            let divIndex = 0;
            let allData = res.data;
            let xItem = res.xItem;
            $.each(allData, function (idx, siteData) {
                let siteName = idx;
                let legend = ["PM2.5", "PM10", "O3", "NO2", "SO2", "CO"];
                let series = [];
                let pm25 = [];
                let pm10 = [];
                let o3 = [];
                let no2 = [];
                let so2 = [];
                let co = [];
                // xItem [2019-09, 2019-10, 2019-11, 2019-12]
                for (let i = 0; i < xItem.length; i++) {
                    // pm25[i] = "-";
                    // pm10[i] = "-";
                    // o3[i] = "-";
                    // no2[i] = "-";
                    // so2[i] = "-";
                    // co[i] = "-";
                    for (let j = 0; j < siteData.length; j++) {
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'PM2.5') {
                            pm25[i] = siteData[j].pm25Count;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'PM10') {
                            pm10[i] = siteData[j].pm10Count;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'O3') {
                            o3[i] = siteData[j].o3Count;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'NO2') {
                            no2[i] = siteData[j].no2Count;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'SO2') {
                            so2[i] = siteData[j].so2Count;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'CO') {
                            co[i] = siteData[j].coCount;
                        }
                    }
                }
                series.push({
                    name: 'PM2.5',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: pm25
                }, {
                    name: 'PM10',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: pm10
                }, {
                    name: 'O3',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: o3
                }, {
                    name: 'NO2',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: no2
                }, {
                    name: 'SO2',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: so2
                }, {
                    name: 'CO',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'insideRight'
                        }
                    },
                    data: co
                });
                let data = {
                    siteName: siteName,
                    legend: legend,
                    xItem: xItem,
                    series: series
                };
                ++divIndex;
                let $chart = '<div id="primaryChartsContent' + divIndex + '" style="width: 50%;display: inline-block" class="chartsContent"></div>'
                $("#primaryChartsContents").append($chart);
                drawPrimaryImg("primaryChartsContent" + divIndex, data);
            });
        },
        error: function (e) {
            closeLoad();
        }
    });
}

function clearPriDiv() {
    $.each(priChartArr, function (idx, chart) {
        try {
            chart.clear();
        } catch (e) {}
    });
    priChartArr = [];
    $("#primaryChartsContents").html("");
}

function drawPrimaryImg(element, inData) {
    let option = {
        title: {
            text: inData.siteName
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            left: 170,
            textStyle: {
                fontSize: 10
            },
            data: inData.legend
        },
        grid: {
            containLabel: true
        },
        xAxis: {
            data: inData.xItem,
            type: 'category'
        },
        yAxis: {
            type: 'value'
        },
        series: inData.series
    };
    // 基于准备好的dom，初始化echarts图表
    var chart = echarts.init(document.getElementById(element));
    chart.setOption(option);
    priChartArr.push(chart);
}