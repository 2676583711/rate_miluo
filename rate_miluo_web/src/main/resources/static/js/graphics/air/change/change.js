let changeChartArr = [];
let changeObj1 = {
    allData: []
};

function initChangeContent() {
    initChangeHeight();
    bindChangeEvent();
    searchChangeMicroData();
}


let initChangeHeight = function () {
    let h2 = $("#" + selectTabArr[selectTab]).outerHeight(true);
    let h3 = $(".layui-tab-title").outerHeight(true);
    $("#changeChartsContents").css({"height": (window.innerHeight - h2 - h3) + "px"})
};

let bindChangeEvent = function () {
    $("#changeType").on("change", function (e) {
        let selValue = $(this).val();
        clearChangeDiv();
        if (selValue == "changeOp1") {
            changeOP1Content();
        } else if (selValue == "changeOp2") {
            changeOP2Content();
        }
    });
    $("#changeSearch").on("click", function (e) {
        searchChangeMicroData();
    });
    $("#changeRefresh").on("click", function (e) {
        refreshObj('4');
    });

};

function changeOP1Content() {
    $("#changeForm .dateRange").removeClass("hidden");
    $("#changeForm .year").addClass("hidden");
    searchChangeMicroData();
}

function changeOP2Content() {
    $("#changeForm .year").removeClass("hidden");
    $("#changeForm .dateRange").addClass("hidden");
    searchChangeMicroData();
}

let changeErrorStatus = [false,false];
function searchChangeMicroData() {
    changeErrorStatus = [false,false];
    showLoad();
    clearChangeDiv();
    let dateRange = $("#changeForm .dateRange").val();
    let siteCodes=$.siteTree.siteCodes();
    if(siteCodes.length === 0){
    	layer.alert("未选择站点！");
        closeLoad();
    	return;
    }
    $.ajax({
        type: "get",
        url: "/graphic/air/change/list",
        data: {
            day: dateRange,
            siteCodes: siteCodes.join()
        },
        dataType: "json",
        success: function (res) {
            changeObj1.allData = res.data;
            try {
                initChangeMicroChartData();
            } catch (e) {
                console.error(e)
            }
            closeLoad();
        },
        error: function (e) {
            closeLoad();
        }
    })
}
function clearChangeDiv() {
    $.each(changeChartArr, function (idx, chart) {
        try {
            chart.clear();
        } catch (e) {
        }
    });
    changeChartArr = [];
    $("#changeChartsContents").html("");
}
let changeIndex=0;
function initChangeMicroChartData() {
    let xItems = [];
    for (let i = 0; i < 24; i++) {
        xItems.push(i + '时');
    }
    $.each(changeObj1.allData, function (siteName, siteData) {
        let no2 = [];
        let pm10 = [];
        let pm25 = [];
        let so2 = [];
        let co = [];
        let o3 = [];
        $.each(siteData, function (idx, site) {
            for (let idxx=0;idxx < xItems.length; idxx++) {
                let hour = parseInt(site.queryTime.substr(11, 2));
                let date = xItems[idxx];
                if (date == (hour + '时')) {
                    let i = parseInt(hour);
                    no2[i] = site.no2;
                    pm10[i] = site.pm10;
                    pm25[i] = site.pm25;
                    so2[i] = site.so2;
                    co[i] = site.co;
                    o3[i] = site.o3oneHour;
                    break;
                }
            }
        });
        let lineOne = [{
            name: 'PM2.5',
            type: 'line',
            yAxisIndex: 0,
            data: pm25
        },
            {
                name: 'PM10',
                type: 'line',
                yAxisIndex: 0,
                data: pm10
            },
            {
                name: 'NO2',
                yAxisIndex: 0,
                type: 'line',
                data: no2
            }];
        let oneChart = {
            siteName: siteName,
            items: ['NO2','PM2.5','PM10'],
            xTitle: xItems,
            data: lineOne,
        };
        var standard = siteName.indexOf('(标准站)');
        if (standard>=0) {
            standard = true;
            // siteName = siteName.substring(0, standard);
        }
        if(siteName){
            oneChart.siteName = siteName;
            oneChart.items = ['NO2','PM2.5','PM10','SO','SO2','O3'];
            lineOne.push({
                name: 'CO',
                type: 'line',
                yAxisIndex: 1,
                data: co
            });
            lineOne.push({
                name: 'SO2',
                type: 'line',
                yAxisIndex: 0,
                data: so2
            });
            lineOne.push({
                name: 'O3',
                type: 'line',
                yAxisIndex: 0,
                data: o3
            });
            oneChart.data = lineOne;
        }
        ++changeIndex;
        let $chartChange = '<div id="changeChartsContent'+changeIndex+'" class="chartsContent"></div><hr>';
        if(standard){
            $("#changeChartsContents").prepend($chartChange);
        }else{
            $("#changeChartsContents").append($chartChange);
        }
        drawChangeChart("changeChartsContent"+changeIndex, oneChart);
    });
}


let drawChangeChart = function (element, inData) {
    let
        option = {
            title: {text: inData.siteName},
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
                data: inData.items
            },
            xAxis: [
                {
                    type: 'category',
                    data: inData.xTitle,
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
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
            }, {
                type: 'value',
                name: "CO 浓度 ：mg/m³",
                position: "right",
                nameGap: 40,
                nameLocation: 'middle',
                nameTextStyle: {
                    fontWeight: "bold",
                    fontSize: 15
                }
            }
            ],
            series: inData.data
        };

    // 基于准备好的dom，初始化echarts图表
    let changeChart = echarts.init(document.getElementById(element));
    changeChart.setOption(option);
    changeChartArr.push(changeChart);
};


