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
        refreshObj('3');
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
        url: "/graphic/polluteWater/change/list",
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
        let bo1 = [];
        let cd = [];
        let cu = [];
        let pb = [];
        let ph = [];
        let shen = [];
        let zn = [];
        $.each(siteData, function (idx, site) {
            for (let idxx=0;idxx < xItems.length; idxx++) {
                let hour = parseInt(site.recordingTime.substr(11, 2));
                let date = xItems[idxx];
                if (date == (hour + '时')) {
                    let i = parseInt(hour);
                    bo1[i] = site.bo1;
                    cd[i] = site.cd;
                    cu[i] = site.cu;
                    pb[i] = site.pb;
                    shen[i] = site.shen;
                    ph[i] = site.ph;
                    zn[i] = site.zn;
                    break;
                }
            }
        });
        let lineOne = [{
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
            }]
        let oneChart = {
            siteName: siteName,
            items: Object.values(polluteWaterPol),
            xTitle: xItems,
            data: lineOne,
        };
        ++changeIndex;
        let $chartChange = '<div id="changeChartsContent'+changeIndex+'" class="chartsContent"></div><hr>';
        $("#changeChartsContents").append($chartChange);
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
                data: inData.items,
                bottom: 'bottom',
                textStyle: {
                    fontSize: 12
                }
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
                offset: 70,
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
            series: inData.data
        };

    // 基于准备好的dom，初始化echarts图表
    let changeChart = echarts.init(document.getElementById(element));
    changeChart.setOption(option);
    changeChartArr.push(changeChart);
};


