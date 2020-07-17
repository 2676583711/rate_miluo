var minuteObj = {
    allData: null,
    siteData: [],
    xTitleDates: []

};

var chartCount = 0;
function reInitMinuteObj() {
    minuteObj = {
        allData: null,
        siteData: [],
        xTitleDates: []
    };
}

function initMinuteContent() {
    bindMinuteSel();
    initMinuteHeight();
    initMinuteSearch();
}

function initMinuteHeight() {
    // var h1 = $(parent.document).find(".main-title-center").height();
    var h2 = $("#" + selectTabArr[selectTab]).outerHeight(true);
    var h3 = $(".layui-tab-title").outerHeight(true);
    // var h4 = $("#hourSelDiv").outerHeight(true);
    $("#minuteChartsContents").css({ "height": window.innerHeight - h2 - h3 + "px" });
}

function initMinuteSearch() {
    var minute = $("#minuteForm .dateRange").val();
    if (!minute) {
        setTimeout(function () {
            initMinuteSearch();
        }, 20);
    } else {
        minuteSearchCenter();
    }
}

function bindMinuteSel() {
    $("#minuteSearch").on("click", function (e) {
        minuteSearchCenter();
    });
    $("#minuteRefresh").on("click", function (e) {
        refreshObj('1');
    });
}

function minuteSearchCenter() {
    showLoad();
    searchMicroMinuteData();
};

function searchMicroMinuteData() {
    showLoad();
    var dateRange = $("#minuteForm .dateRange").val();
    var siteCodes = $.siteTree.siteCodes();
    if(siteCodes && siteCodes.length === 0){
        closeLoad();
    	layer.alert("未选择站点！");
    	return;
    }
    $.ajax({
        type: "get",
        url: "/graphic/pollute/minute/list",
        data: {
            dateRange: dateRange,
            siteCodes: siteCodes.join()
        },
        success: function success(res) {
            clearMinuteDiv();
            try {
                if ($.isEmptyObject(res.data)) {
                    layer.msg("无数据！");
                    return;
                }
                reInitMinuteObj();
                minuteObj.allData = res.data;
                minuteObj.xTitleDates = res.xTime;
                minuteLine();
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
let obj = {
    'press' : [],
    'flueVelocity' : [],
    'so2' : [],
    'nox' : [],
    'dust' : [],
    'temp' : [],
    'o2' : [],
    'exhaust' : []
}

let minuteChartArr = [];
function clearMinuteDiv() {
    for (let chart in minuteChartArr) {
        try {
            minuteChartArr[chart].clear();
        } catch (e) {}
    }
    minuteChartArr = [];
    $("#minuteChartsContents").html("");
}
function minuteLine() {
    if (!minuteObj.allData) {
        return;
    }
    let allDatas = Object.keys(minuteObj.allData);
    let pluField = Object.keys(obj);
    for (let index in allDatas) {
        $.each(Object.values(obj), function (i,item) {
            item.length = 0;
        });
        let equipmentId = allDatas[index];
        if (!minuteObj.allData.hasOwnProperty(equipmentId)) {
            continue;
        }
        let siteData = minuteObj.allData[equipmentId];
        let xItems = minuteObj.xTitleDates;
        for (let i=0; i<xItems.length; i++) {
            $.each(siteData, function (idx, site) {
                if (site.recordingTime == xItems[i]) {
                    $.each(Object.keys(obj), function (id, plu) {
                        if (pluField.indexOf(plu) > -1) {
                            obj[plu][i] = site[plu];
                        }
                    });
                    return false;
                }
            });
        }
        var lineOne = [{
            name: '烟气压力(kPa)',
            data: obj.press,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '烟气流速(m/s)',
            data: obj.flueVelocity,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '二氧化硫(mg/m³)',
            data: obj.so2,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '氮氧化物(mg/m³)',
            data: obj.nox,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '烟尘(mg/m³)',
            data: obj.dust,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '烟气温度(℃)',
            data: obj.temp,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '氧气含量(%)',
            data: obj.o2,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '排放量(m³/s)',
            data: obj.dust,
            type: 'line',
            yAxisIndex: 1
        }]
        var oneChart = {
            siteName: siteData[0].siteName,
            xTitle: xItems,
            data: lineOne
        };
        ++chartCount;
        var $chartMinute = '<div id="minuteChartsContent' + chartCount + '" class="chartsContent"></div><hr>';
        $("#minuteChartsContents").append($chartMinute);
        drawMinuteImage('minuteChartsContent' + chartCount, oneChart);
    }
}

function drawMinuteImage(element, indata) {
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
    minuteChartArr.push(myChart);
}
