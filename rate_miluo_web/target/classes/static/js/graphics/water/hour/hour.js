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
    var h4 = $("#hourSelDiv").outerHeight(true);
    $("#hourChartsContents").css({ "height": window.innerHeight - h2 - h3 - h4 + "px" });
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
        refreshObj('2');
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
            url: "/graphic/water/hour/list",
            data: {
                dateRange: dateRange,
                // pollutantNames: pollutantNames,
                // stationCodeMicro: stationCodeMicro,
                siteCodes: siteCodes.join(',')
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
                    searchSelectMicro();
                    // hourLoadStatus(0);
                    // microLine1();
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

        let xItems = [];
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
            xItems.push(site.dateTime?site.dateTime:'-');
        });
        var lineOne = [{
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
        var oneChart = {
            siteName: siteName,
            items: Object.values(waterPol),
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
        let swLineChild = [];
        let ddlLineChild = [];
        let codmnLineChild = [];
        let pbLineChild = [];
        let rjyLineChild = [];
        let phLineChild = [];
        let shenLineChild = [];
        let nh3NLineChild = [];
        let comprehensiveToxicityLineChild = [];
        let tpLineChild = [];
        let cdLineChild = [];
        let kmnLineChild = [];
        let tempLineChild = [];
        let humiLineChild = [];
        let ylsLineChild = [];
        let llzLineChild = [];

        for (var i in hourObj.xTitleDates) {
            swLineChild.push('-');
            ddlLineChild.push('-');
            codmnLineChild.push('-');
            pbLineChild.push('-');
            rjyLineChild.push('-');
            phLineChild.push('-');
            shenLineChild.push('-');
            nh3NLineChild.push('-');
            comprehensiveToxicityLineChild.push('-');
            tpLineChild.push('-');
            cdLineChild.push('-');
            kmnLineChild.push('-');
            tempLineChild.push('-');
            humiLineChild.push('-');
            ylsLineChild.push('-');
            llzLineChild.push('-');
        }
        var siteDataItem = hourObj.allData[siteName];
        // if(siteArr.indexOf(siteName)<0){
        //     siteArr.push(siteName);
        // }
        for (var siteDatatimeIdx in hourObj.xTitleDates) {
            var siteDatatime = hourObj.xTitleDates[siteDatatimeIdx];
            for (var siteDataI in siteDataItem) {
                var siteData = siteDataItem[siteDataI];
                if (siteData.dateTime == siteDatatime) {
                    swLineChild[siteDatatimeIdx] = siteData.sw;
                    ddlLineChild[siteDatatimeIdx] = siteData.ddl;
                    codmnLineChild[siteDatatimeIdx] = siteData.codmn;
                    pbLineChild[siteDatatimeIdx] = siteData.pb;
                    rjyLineChild[siteDatatimeIdx] = siteData.rjy;
                    phLineChild[siteDatatimeIdx] = siteData.ph;
                    shenLineChild[siteDatatimeIdx] = siteData.shen;
                    nh3NLineChild[siteDatatimeIdx] = siteData.nh3N;
                    comprehensiveToxicityLineChild[siteDatatimeIdx] = siteData.comprehensiveToxicity;
                    tpLineChild[siteDatatimeIdx] = siteData.tp;
                    cdLineChild[siteDatatimeIdx] = siteData.cd;
                    kmnLineChild[siteDatatimeIdx] = siteData.kmn;
                    tempLineChild[siteDatatimeIdx] = siteData.temp;
                    humiLineChild[siteDatatimeIdx] = siteData.humi;
                    ylsLineChild[siteDatatimeIdx] = siteData.yls;
                    llzLineChild[siteDatatimeIdx] = siteData.llz;
                }
            }
            lineArr[siteName] = {};
            lineArr[siteName][waterPol['sw']] = swLineChild;
            lineArr[siteName][waterPol['ddl']] = ddlLineChild;
            lineArr[siteName][waterPol['codmn']] = codmnLineChild;
            lineArr[siteName][waterPol['pb']] = pbLineChild;
            lineArr[siteName][waterPol['rjy']] = rjyLineChild;
            lineArr[siteName][waterPol['ph']] = phLineChild;
            lineArr[siteName][waterPol['shen']] = shenLineChild;
            lineArr[siteName][waterPol['nh3N']] = nh3NLineChild;
            lineArr[siteName][waterPol['comprehensiveToxicity']] = comprehensiveToxicityLineChild;
            lineArr[siteName][waterPol['tp']] = tpLineChild;
            lineArr[siteName][waterPol['cd']] = cdLineChild;
            lineArr[siteName][waterPol['kmn']] = kmnLineChild;
            lineArr[siteName][waterPol['temp']] = tempLineChild;
            lineArr[siteName][waterPol['humi']] = humiLineChild;
            lineArr[siteName][waterPol['yls']] = ylsLineChild;
            lineArr[siteName][waterPol['llz']] = llzLineChild;
        }
    }
    var realXI = [];
    $.each(hourObj.xTitleDates, function (idx, item) {
        realXI.push(item);
    });
    var siteNameArr = Object.keys(lineArr);
    $.each(waterPol, function (idx, polName) {
        var s = [];
        for (var _siteNameI in siteNameArr) {
            var _siteName = siteNameArr[_siteNameI];
            if (!lineArr.hasOwnProperty(_siteName)) {
                continue;
            }
            if (!lineArr[_siteName][polName]) {
                continue;
            }
            var t = {
                name: _siteName,
                data: lineArr[_siteName][polName],
                type: 'line'
            };
            s.push(t);
        }
        var oneChart = {
            siteName: polName,
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
            position: "left",
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