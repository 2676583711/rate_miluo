let dayObj = {
    allData: null,
    siteData: [],
    xTitleDates: []
};

function reInitDayObj() {
    dayObj = {
        allData: null,
        siteData: [],
        xTitleDates: []
    };
}

function initDayContent() {
    bindDaySel();
    initDayHeight();
    initDaySearch();
}

function initDayHeight() {
    // var h1 = $(parent.document).find(".main-title-center").height();
    var h2 = $("#" + selectTabArr[selectTab]).outerHeight(true);
    var h3 = $(".layui-tab-title").outerHeight(true);
    $("#dayChartsContents").css({ "height": window.innerHeight - h2 - h3 + "px" });
}

function initDaySearch() {
    var day = $("#dayForm .dateRange").val();
    if (!day) {
        setTimeout(function () {
            initDaySearch();
        }, 20);
    } else {
        daySearchCenter();
    }
}

function bindDaySel() {
    $("#daySearch").on("click", function (e) {
        daySearchCenter();
    });
    $("#dayRefresh").on("click", function (e) {
        refreshObj('1');
    });
}

function daySearchCenter() {
    showLoad();
    searchDayData();
};

function searchDayData() {
    showLoad();
    var dateRange = $("#dayForm .dateRange").val();
    var siteCodes = $.siteTree.siteCodes();
    if(siteCodes && siteCodes.length === 0){
        closeLoad();
        layer.alert("未选择站点！");
        return;
    }
    $.ajax({
        type: "get",
        url: "/graphic/pollute/day/list",
        data: {
            dateRange: dateRange,
            siteCodes: siteCodes.join()
        },
        success: function success(res) {
            clearDayDiv();
            try {
                if ($.isEmptyObject(res.data)) {
                    layer.msg("无数据！");
                    return;
                }
                reInitDayObj();
                dayObj.allData = res.data;
                dayObj.xTitleDates = res.xItem;
                dayLine();
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
let obj3 = {
    'press' : [],
    'flueVelocity' : [],
    'so2' : [],
    'nox' : [],
    'dust' : [],
    'temp' : [],
    'o2' : [],
    'exhaust' : []
}

let dayChartArr = [];
function clearDayDiv() {
    for (let chart in dayChartArr) {
        try {
            dayChartArr[chart].clear();
        } catch (e) {}
    }
    dayChartArr = [];
    $("#dayChartsContents").html("");
}
function dayLine() {
    if (!dayObj.allData) {
        return;
    }
    let allDatas = Object.keys(dayObj.allData);
    let pluField = Object.keys(obj3);
    for (let index in allDatas) {
        $.each(Object.values(obj3), function (i,item) {
            item.length = 0;
        });
        let siteName = allDatas[index];
        if (!dayObj.allData.hasOwnProperty(siteName)) {
            continue;
        }
        let siteData = dayObj.allData[siteName];
        let xItems = dayObj.xTitleDates;
        for (let i=0; i<xItems.length; i++) {
            $.each(siteData, function (idx, site) {
                if (site.recordingTime.substr(0,10) == xItems[i]) {
                    $.each(Object.keys(obj3), function (id, plu) {
                        if (pluField.indexOf(plu) > -1) {
                            obj3[plu][i] = site[plu];
                        }
                    });
                    return false;
                }
            });
        }
        var lineOne = [{
            name: '烟气压力(kPa)',
            data: obj3.press,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '烟气流速(m/s)',
            data: obj3.flueVelocity,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '二氧化硫(mg/m³)',
            data: obj3.so2,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '氮氧化物(mg/m³)',
            data: obj3.nox,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '烟尘(mg/m³)',
            data: obj3.dust,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '烟气温度(℃)',
            data: obj3.temp,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '氧气含量(%)',
            data: obj3.o2,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '排放量(m³/s)',
            data: obj3.dust,
            type: 'line',
            yAxisIndex: 1
        }]
        var oneChart = {
            siteName: siteName,
            xTitle: xItems,
            data: lineOne
        };
        ++chartCount;
        var $chartDay = '<div id="dayChartsContent' + chartCount + '" class="chartsContent"></div><hr>';
        $("#dayChartsContents").append($chartDay);
        drawDayImage('dayChartsContent' + chartCount, oneChart);
    }
}

function drawDayImage(element, indata) {
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
    dayChartArr.push(myChart);
}
