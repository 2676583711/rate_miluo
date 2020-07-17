let hourObj = {
    allData: null,
    siteData: [],
    xTitleDates: []
};

function reInitHourObj() {
    hourObj = {
        allData: null,
        siteData: [],
        xTitleDates: []
    };
}

function initHourContent() {
    bindHourSel();
    initHourHeight();
    initHourSearch();
}

function initHourHeight() {
    // var h1 = $(parent.document).find(".main-title-center").height();
    var h2 = $("#" + selectTabArr[selectTab]).outerHeight(true);
    var h3 = $(".layui-tab-title").outerHeight(true);
    // var h4 = $("#hourSelDiv").outerHeight(true);
    $("#hourChartsContents").css({ "height": window.innerHeight - h2 - h3 + "px" });
}

function initHourSearch() {
    var hour = $("#hourForm .dateRange").val();
    if (!hour) {
        setTimeout(function () {
            initHourSearch();
        }, 20);
    } else {
        hourSearchCenter();
    }
}

function bindHourSel() {
    $("#hourSearch").on("click", function (e) {
        hourSearchCenter();
    });
    $("#hourRefresh").on("click", function (e) {
        refreshObj('1');
    });
}

function hourSearchCenter() {
    showLoad();
    searchHourData();
};

function searchHourData() {
    showLoad();
    var dateRange = $("#hourForm .dateRange").val();
    var siteCodes = $.siteTree.siteCodes();
    if(siteCodes && siteCodes.length === 0){
        closeLoad();
        layer.alert("未选择站点！");
        return;
    }
    $.ajax({
        type: "get",
        url: "/graphic/pollute/hour/list",
        data: {
            dateRange: dateRange,
            siteCodes: siteCodes.join()
        },
        success: function success(res) {
            clearHourDiv();
            try {
                if ($.isEmptyObject(res.data)) {
                    layer.msg("无数据！");
                    return;
                }
                reInitHourObj();
                hourObj.allData = res.data;
                hourObj.xTitleDates = res.xItem;
                hourLine();
                closeLoad();
            } catch (e) {
                closeLoad();
                showError();
                console.error(e);
            }
        },
        error: function error(e) {
            console.error(e);
            closeLoad();
        }

    });
}
let obj2 = {
    'press' : [],
    'flueVelocity' : [],
    'so2' : [],
    'nox' : [],
    'dust' : [],
    'temp' : [],
    'o2' : [],
    'exhaust' : []
}

let hourChartArr = [];
function clearHourDiv() {
    for (let chart in hourChartArr) {
        try {
            hourChartArr[chart].clear();
        } catch (e) {}
    }
    hourChartArr = [];
    $("#hourChartsContents").html("");
}
function hourLine() {
    if (!hourObj.allData) {
        return;
    }
    let allDatas = Object.keys(hourObj.allData);
    let pluField = Object.keys(obj2);
    for (let index in allDatas) {
        $.each(Object.values(obj2), function (i,item) {
            item.length = 0;
        });
        let siteName = allDatas[index];
        if (!hourObj.allData.hasOwnProperty(siteName)) {
            continue;
        }
        let siteData = hourObj.allData[siteName];
        let xItems = hourObj.xTitleDates;
        for (let i=0; i<xItems.length; i++) {
            $.each(siteData, function (idx, site) {
                if (site.recordingTime.substr(0,16) == xItems[i]) {
                    $.each(Object.keys(obj2), function (id, plu) {
                        if (pluField.indexOf(plu) > -1) {
                            obj2[plu][i] = site[plu];
                        }
                    });
                    return false;
                }
            });
        }
        var lineOne = [{
            name: '烟气压力(kPa)',
            data: obj2.press,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '烟气流速(m/s)',
            data: obj2.flueVelocity,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '二氧化硫(mg/m³)',
            data: obj2.so2,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '氮氧化物(mg/m³)',
            data: obj2.nox,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '烟尘(mg/m³)',
            data: obj2.dust,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '烟气温度(℃)',
            data: obj2.temp,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '氧气含量(%)',
            data: obj2.o2,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '排放量(m³/s)',
            data: obj2.dust,
            type: 'line',
            yAxisIndex: 1
        }]
        var oneChart = {
            siteName: siteName,
            xTitle: xItems,
            data: lineOne
        };
        ++chartCount;
        var $chartHour = '<div id="hourChartsContent' + chartCount + '" class="chartsContent"></div><hr>';
        $("#hourChartsContents").append($chartHour);
        drawHourImage('hourChartsContent' + chartCount, oneChart);
    }
}

function drawHourImage(element, indata) {
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
            type: 'scroll',
            data: ['烟气压力(kPa)','烟气流速(m/s)','二氧化硫(mg/m³)','氮氧化物(mg/m³)','烟尘(mg/m³)','烟气温度(℃)','氧气含量(%)','排放量(m³/s)'],
            bottom: 'bottom',
            textStyle: {
                fontSize: 12
            }
        },
        xAxis: {
            type: 'category',
            data: indata.xTitle
        },
        yAxis: [{
            type: 'value',
            name: "浓度 ：(mg/m³)",
            position: "left",
            nameGap: 35,
            nameLocation: 'middle',
            yAxisIndex: 0,
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        }, {
            type: 'value',
            name: "",
            position: "right",
            yAxisIndex: 1,
            offset: 35,
            // nameGap: 35,
            // nameLocation: 'middle',
            nameTextStyle: {
                fontWeight: "bold",
                fontSize: 15
            }
        }],
        series: indata.data
    };

    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById(element));
    myChart.setOption(option);
    hourChartArr.push(myChart);
}
