
let pmChartArr = [];
let pmObj1 = {
    allData: []
};
let pmObj2 = {
    allData: []
};

function initPmContent(){
    initDateInput({
        elem: '#pmForm .dateRange'
        , type: 'datetime'
        , format: 'yyyy-MM-dd HH:mm'
        , range: '~'
        , value: defaultDateTime(1)
    })
    initPmHeight();
    bindPmEvent();
    initPmSearch();
}

function initPmSearch(){
    let hour = $("#pmForm .dateRange").val();
    if(!hour){
        setTimeout(function () {
            initPmSearch();
        }, 20)
    }else{
        searchPmData();
    }
}


let initPmHeight = function (){
    let h2 = $("#" + selectTabArr[selectTab]).outerHeight(true);
    let h3 = $(".layui-tab-title").outerHeight(true);
    $("#pmChartsContents").css({"height" : (window.innerHeight - h2 - h3) + "px"})
}

let bindPmEvent = function () {
    $("#pmSearch").on("click", function (e) {
        searchPmData();
    });
    $("#pmRefresh").on("click", function (e) {
        refreshObj('5');
    });

}

function searchPmData() {
    showLoad();
    clearPmDiv();
    let dateRange = $("#pmForm .dateRange").val();
    let siteCodes=$.siteTree.siteCodes();
    if(siteCodes.length === 0){
        closeLoad();
    	layer.alert("未选择站点！");
    	return;
    }
    $.ajax({
        type: "get",
        url: "/graphic/air/pm/list",
        data: {
            dateRange: dateRange,
            siteCodes: siteCodes.join()
        },
        dataType: "json",
        success: function (res) {
            pmObj2 = {
                allData: {}
            };
            pmObj2.allData = res.data;
            try{
                initPmChartData();
            }catch (e) {
                console.error(e);
            }
            closeLoad();
        },
        error: function (e) {
            closeLoad();
        }
    })
}
function clearPmDiv(){
    $.each(pmChartArr, function (idx, chart) {
        try{
            chart.clear();
        }catch (e) {}
    });
    pmChartArr = [];
    $("#pmChartsContents").html("");
}
let pmChartIndex=0;

function initPmChartData(){
    $.each(pmObj2.allData, function (siteName, siteData) {
        let pm25Line = [];
        let pm10Line = [];
        let mkLine = [];
        let xItems = [];
        $.each(siteData, function (idxx, siteItem) {
            pm10Line.push(siteItem.pm10);
            xItems.push(siteItem.queryTime.substr(0, 13));
            pm25Line.push(siteItem.pm25);
            try{
                mkLine.push((siteItem.pm25/siteItem.pm10).toFixed(2));
            }catch (e) {
                mkLine.push("-");
            }
        });
        let oneChart = {
            siteName: siteName,
            xItems: xItems,
            data: [
                {
                    name:'PM2.5',
                    type:'bar',
                    data:pm25Line
                },
                {
                    name:'PM10',
                    type:'bar',
                    data:pm10Line
                },
                {
                    name:'PM2.5/PM10',
                    type:'line',
                    yAxisIndex: 1,
                    data:mkLine
                }
            ],
        };
        ++pmChartIndex;
        let $chartPm = '<div id="pmChartsContent'+pmChartIndex+'" class="chartsContent"></div><hr>'
        if(siteName.indexOf('标准站')>-1){
            $("#pmChartsContents").prepend($chartPm);
        }else{
            $("#pmChartsContents").append($chartPm);
        }
        drawPmChart("pmChartsContent"+pmChartIndex, oneChart);
    });


}

let drawPmChart = function(element, inData) {
    let option = {
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
            data:['PM2.5','PM10','PM2.5/PM10']
        },
        xAxis: [
            {
                type: 'category',
                data: inData.xItems,
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                min: 0,
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: 'PM2.5/PM10',
                min: 0,
            }
        ],
        series: inData.data
    };
    // 基于准备好的dom，初始化echarts图表
    let pmChart = echarts.init(document.getElementById(element));
    pmChart.setOption(option);
    pmChartArr.push(pmChart);
}


