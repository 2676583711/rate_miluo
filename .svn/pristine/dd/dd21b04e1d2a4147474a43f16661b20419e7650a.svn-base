let overChartArr = [];
function initOverproofChartContent(){
    clearOverDiv();
    initOverHeight();
    loadOverChart();
}

let initOverHeight = function () {
    var h3 = $("#myTab").outerHeight(true);
    var h4 = $("#selDiv").outerHeight(true);
    $("#overproofChartsContents").css({"height": (window.innerHeight - h3 - h4 - 45) + "px"});
};

function loadOverChart() {
    let startTime = $('.time-type-start').not('.hidden').val();
    let endTime = $('.time-type-end').not('.hidden').val();
    let stationType = $("input[name='stationType']:checked").val();  // 站点类型
    let timeType = $("input[name='time']:checked").val();  // 时间类型
    let dataType = $("input[name='dataType']:checked").val();  // 标况，实况
    let examineType = $("input[name='examineType']:checked").val();  // 原始，审核

    $.ajax({
        type: 'get',
        url: prefix + '/overproof/graphic',
        data: {
            startDate: startTime,
            endDate: endTime,
            stationType: stationType,
            timeType: timeType,
            dataType: dataType,
            examineType: examineType,
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

                // xItem [2019-11, 2019-12, 2020-01]
                for (let i = 0; i < xItem.length; i++) {
                    for (let j = 0; j < siteData.length; j++) {
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'PM2.5' && siteData[j].badDays > 0) {
                            pm25[i] = siteData[j].badDays;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'PM10' && siteData[j].badDays > 0) {
                            pm10[i] = siteData[j].badDays;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'O3' && siteData[j].badDays > 0) {
                            o3[i] = siteData[j].badDays;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'NO2' && siteData[j].badDays > 0) {
                            no2[i] = siteData[j].badDays;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'SO2' && siteData[j].badDays > 0) {
                            so2[i] = siteData[j].badDays;
                        }
                        if (siteData[j].dataTime == xItem[i] && siteData[j].primaryEp == 'CO' && siteData[j].badDays > 0) {
                            co[i] = siteData[j].badDays;
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
                let $chart = '<div id="overproofChartsContent' + divIndex + '" style="width: 50%;display: inline-block" class="chartsContent"></div>'
                $("#overproofChartsContents").append($chart);
                drawOverImg("overproofChartsContent" + divIndex, data);
            });
        },
        error: function (e) {
            closeLoad();
        }
    });
}

function clearOverDiv() {
    $.each(overChartArr, function (idx, chart) {
        try {
            chart.clear();
        } catch (e) {}
    });
    overChartArr = [];
    $("#overproofChartsContents").html("");
}

function drawOverImg(element, inData) {
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
    overChartArr.push(chart);
}