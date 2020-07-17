//微型
var hourObj = {
    allData: null,
    siteData: [],
    polData: [],
    xTitleDates: []
    //标准
};
var chartCount = 0;

function reInitHourObj() {
    hourObj = {
        allData: null,
        siteData: [],
        polData: [],
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

function searchSelectMicro() {
    var selValue = $("#hourType").val();
    if (selValue == "hourOp1") {
        microLine1();
    } else if (selValue == "hourOp2") {
        microLine2();
    } else if (selValue == "hourOp3") {
        microLine3();
    }
}

function changeChart3Status(tab) {
    if (tab == 'hourOp3') {
        $("#chart3").removeClass("hidden");
    } else {
        if (!$("#chart3").hasClass("hidden")) {
            $("#chart3").addClass("hidden");
        }
    }
}

function bindHourSel() {
    $("#hourType").on("change", function (e) {
        var selValue = $(this).val();
        changeCenter(selValue);
    });
    $("#chart3X, #chart3Y").on("change", function (e) {
        var selValue = $("#hourType").val();
        changeCenter(selValue);
    });
    $("#hourSearch").on("click", function (e) {
        hourSearchCenter();
    });
    $("#hourRefresh").on("click", function (e) {
        refreshObj('1');
    });
}

function hourSearchCenter(type) {
    showLoad();
    searchMicroHourData();
};

function changeCenter(tab) {
    showLoad();
    try {
        changeChart3Status(tab);
        initHourHeight();
        clearHourDiv();
        setTimeout(function () {
            if (tab == 'hourOp1') {
                microLine1();
            } else if (tab == 'hourOp2') {
                microLine2();
            } else {
                var X = $("#chart3X").val();
                var Y = $("#chart3Y").val();
                if (X == Y) {
                    layer.alert("请重新选择参数项！");
                    return;
                }
                microLine3();
            }
            closeLoad();
        }, 10);
    } catch (e) {
        console.err(e);
        // closeLoad();
    }
};

// var hourPol = [];
// var hourMicroPol = [];
function searchMicroHourData() {
    // var polNames = $("#hourForm .pollutantNames").val();
    // hourPol = [];
    // hourMicroPol = [];
    // if (polNames && polNames.length > 0) {
    //     hourPol = polNames.split(",");
    //     if (hourPol.includes('PM2.5')) {
    //         hourMicroPol.push('PM2.5');
    //     }
    //     if (hourPol.includes('PM10')) {
    //         hourMicroPol.push('PM10');
    //     }
    //     if (hourPol.includes('NO2')) {
    //         hourMicroPol.push('NO2');
    //     }
    // }
    // if (hourPol.length === 0) {
    //     return;
    // }
    showLoad();
    var dateRange = $("#hourForm .dateRange").val();
    // var pollutantNames = $("#hourForm .pollutantNames").val();
    // var stationCodeMicro = "";
    // var stationCodeStandard = "";
    var siteCodes = $.siteTree.siteCodes();
    if(siteCodes && siteCodes.length === 0){
        closeLoad();
    	layer.alert("未选择站点！");
    	return;
    }
        $.ajax({
            type: "get",
            url: "/graphic/polluteWater/hour/list",
            data: {
                dateRange: dateRange,
                // pollutantNames: pollutantNames,
                // stationCodeMicro: stationCodeMicro,
                siteCode: siteCodes.join()
            },
            success: function success(res) {
                clearHourDiv();
                try {
                    if (!res || res == {}) {
                        layer.alert("无数据！");
                        return;
                    }
                    reInitHourObj();
                    hourObj.allData = res.data.data;
                    // if (res.standard) {
                    //     $.each(Object.keys(res.standard), function (idx, item) {
                    //         hourObj.allData['S' + item] = res.standard[item];
                    //     });
                    // }
                    hourObj.xTitleDates = res.data.xDate;
                    // searchSelectMicro();
                    // hourLoadStatus(0);
                    microLine1();
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


var hourChartArr = [];

function clearHourDiv() {
    for (var chart in hourChartArr) {
        try {
            hourChartArr[chart].clear();
        } catch (e) {}
    }
    hourChartArr = [];
    $("#hourChartsContents").html("");
}


var station = [];

function microLine1() {
    if (!hourObj.allData) {
        return;
    }
    var allDatas = Object.keys(hourObj.allData);
    for (var siteNameI in allDatas) {
        var siteName = allDatas[siteNameI];
        if (!hourObj.allData.hasOwnProperty(siteName)) {
            continue;
        }
        var siteData = hourObj.allData[siteName];
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

        let xItems = [];
        $.each(siteData, function (idx, site) {
            bo1.push(site.bo1);
            cd.push(site.cd);
            cu.push(site.cu);
            pb.push(site.pb);
            ph.push(site.ph);
            shen.push(site.shen);
            zn.push(site.zn);

            tp.push(site.tp);
            tn.push(site.tn);
            cod.push(site.cod);
            nh3.push(site.nh3);
            xItems.push(site.recordingTime);
        });
        var lineOne = [{
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
        var oneChart = {
            siteName: siteName,
            items: Object.values(polluteWaterPol),
            xTitle: xItems,
            data: lineOne
        };
        ++chartCount;
        var $chartHour = '<div id="hourChartsContent' + chartCount + '" class="chartsContent"></div><hr>';
        $("#hourChartsContents").append($chartHour);
        drawHourImage1('hourChartsContent' + chartCount, oneChart);
    }
}
function microLine2() {
    var lineArr = {};
    var siteArr = Object.keys(hourObj.allData);
    //siteName 每个站点所有数据 集合
    for (var siteNameI in siteArr) {
        var siteName = siteArr[siteNameI];
        if (!hourObj.allData.hasOwnProperty(siteName)) {
            continue;
        }
        var standard = siteName.indexOf('(标准站)');
        if (standard>=0) {
            standard = true;
            // siteName = siteName.substring(0, standard);
        }
        var pm25LineChild = [];
        var pm10LineChild = [];
        var no2LineChild = [];
        var so2LineChild = [];
        var coLineChild = [];
        var o3LineChild = [];
        for (var i in hourObj.xTitleDates) {
            no2LineChild.push('-');
            pm25LineChild.push('-');
            pm10LineChild.push('-');
            so2LineChild.push('-');
            coLineChild.push('-');
            o3LineChild.push('-');
        }
        var siteDataItem = hourObj.allData[siteName];
        // if(siteArr.indexOf(siteName)<0){
        //     siteArr.push(siteName);
        // }
        for (var siteDatatimeIdx in hourObj.xTitleDates) {
            var siteDatatime = hourObj.xTitleDates[siteDatatimeIdx];
            for (var siteDataI in siteDataItem) {
                var siteData = siteDataItem[siteDataI];
                if (siteData.queryTime == siteDatatime) {
                    no2LineChild[siteDatatimeIdx] = siteData.no2;
                    pm10LineChild[siteDatatimeIdx] = siteData.pm10;
                    pm25LineChild[siteDatatimeIdx] = siteData.pm25;
                    if (standard) {
                        so2LineChild[siteDatatimeIdx] = siteData.so2;
                        coLineChild[siteDatatimeIdx] = siteData.co;
                        o3LineChild[siteDatatimeIdx] = siteData.o3OneHour;
                    }
                }
            }
            if (standard) {
                lineArr[siteName] = { 'PM2.5': pm25LineChild, PM10: pm10LineChild, NO2: no2LineChild,
                    SO2: so2LineChild, CO: coLineChild, O3: o3LineChild };
            } else {
                lineArr[siteName] = { 'PM2.5': pm25LineChild, PM10: pm10LineChild, NO2: no2LineChild };
            }
        }
    }
    var realXI = [];
    $.each(hourObj.xTitleDates, function (idx, item) {
        realXI.push(item);
    });
    var siteNameArr = Object.keys(lineArr);
    $.each(polluteWaterPol, function (idx, polName) {
        var s = [];
        for (var _siteNameI in siteNameArr) {
            var _siteName = siteNameArr[_siteNameI];
            if (!lineArr.hasOwnProperty(_siteName)) {
                continue;
            }
            if (!lineArr[_siteName][idx]) {
                continue;
            }
            var t = {
                name: _siteName,
                data: lineArr[_siteName][idx],
                type: 'line'
            };
            s.push(t);
        }
        var oneChart = {
            siteName: idx,
            items: siteArr,
            xTitle: realXI,
            data: s
        };
        // if (!hourMicroPol.includes(oneChart.siteName)) {
        //     oneChart.items = siteNameArr.filter(function (d) {
        //         d.startsWith('S');
        //     }).map(function (d) {
        //         return d.substr(1);
        //     });
        // } else {
        //     oneChart.items = siteNameArr.map(function (d) {
        //         return d.startsWith('S') ? d.substr(1) : d;
        //     });
        // }
        // if (polName == 'CO') {
        //     oneChart.unit = '浓度 ：mg/m³';
        // } else {
        //     oneChart.unit = '浓度 ：μg/m³';
        // }
        ++chartCount;
        var $chartHour = '<div id="hourChartsContent' + chartCount + '" class="chartsContent"></div><hr>';
        $("#hourChartsContents").append($chartHour);
        drawHourImage2('hourChartsContent' + chartCount, oneChart);
    });
}
function microLine3() {
    var X = $("#chart3X").val();
    var Y = $("#chart3Y").val();
    $.each(hourObj.allData, function (siteName, siteData) {
        var lineData = [];
        for (var siteItemI in siteData) {
            var siteItem = siteData[siteItemI];
            lineData.push([siteItem[X], siteItem[Y]]);
        }
        // let standard = false;
        // if(siteName.startsWith("S")){
        //     standard = true;
        //     siteName = siteName.substr(1);
        // }
        var standard = siteName.indexOf('(标准站)');
        if (standard>=0) {
            standard = true;
            // siteName = siteName.substring(0, standard);
        }
        var inData = {
            siteName: siteName,
            data: lineData,
            fittingType: $("#chart3 input:checked").val(),
            x: X.toUpperCase().replace("25", "2.5"),
            y: Y.toUpperCase().replace("25", "2.5")
        };
        ++chartCount;
        var $chartHour = '<div id="hourChartsContent' + chartCount + '" class="chartsContent">';
        if(standard){
            $("#hourChartsContents").prepend($chartHour);
        }else{
            $("#hourChartsContents").append($chartHour);
        }
        drawHourImage3('hourChartsContent' + chartCount, inData);
    });
}

function drawHourImage1(element, indata) {
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
            data: indata.items,
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
        series: indata.data
    };

    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById(element));
    myChart.setOption(option);
    hourChartArr.push(myChart);
}

function drawHourImage2(element, indata) {
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

    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById(element));
    myChart.setOption(option);
    hourChartArr.push(myChart);
}
// 0:
// SStation: "东湖梨园"
// data: Array(43)
// 0: (2) [58, 2]
// 1: (2) [60, 2]
// 2: (2) [53, 2]
// 3: (2) [48, 2]
// 4: (2) [42, 2]
// 5: (2) [47, 2]
// 6: (2) [53, 2]
//        PM10 NO
function drawHourImage3(element, indata) {
    var myRegression;
    if (indata.fittingType == 0) {
        myRegression = ecStat.regression('linear', indata.data);
    } else {
        myRegression = ecStat.regression('exponential', indata.data);
    }
    myRegression.points.sort(function (a, b) {
        return a[0] - b[0];
    });

    var option = {
        title: {
            text: indata.siteName + '相关性分析',
            subtext: 'X:' + indata.x + ' - ' + 'Y:' + indata.y,
            left: 'center'
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
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
        xAxis: {
            type: 'value',
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        yAxis: {
            type: 'value',
            splitLine: {
                lineStyle: {
                    type: 'dashed'
                }
            }
        },
        series: [{
            name: 'scatter',
            type: 'scatter',
            label: {
                emphasis: {
                    show: true,
                    position: 'left',
                    textStyle: {
                        color: 'blue',
                        fontSize: 16
                    }
                }
            },
            data: indata.data
        }, {
            name: 'line',
            type: 'line',
            showSymbol: false,
            data: myRegression.points,
            markPoint: {
                itemStyle: {
                    normal: {
                        color: 'transparent'
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'left',
                        formatter: myRegression.expression,
                        textStyle: {
                            color: '#333',
                            fontSize: 14
                        }
                    }
                },
                data: [{
                    coord: myRegression.points[myRegression.points.length - 1]
                }]
            }
        }]
    };

    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById(element));
    myChart.setOption(option);
    hourChartArr.push(myChart);
}