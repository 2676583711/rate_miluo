

var leftStandardLatestData = {};
var leftStandardChart;
function mlNewEcharts() {
    $.get("/desktop/standard/latestData", {}, function (result) {
        if (result && result.length > 0) {
            $("#standardSelect").html("");
            for (var idx in result) {
                if (result.hasOwnProperty(idx)) {
                    var item = result[idx];
                    leftStandardLatestData[item.siteCode] = item;
                    if (item.stationType == 22) {
                        $("#standardSelect").prepend("<option selected value='" + item.siteCode + "'>" + item.stationName + "</option>");
                    } else {
                        $("#standardSelect").append("<option value='" + item.siteCode + "'>" + item.stationName + "</option>");
                    }
                }
            }
            $("#standardSelect").unbind("change");
            $("#standardSelect").on("change", function (e) {
                drawStandardChart(leftStandardLatestData[e.target.value]);
            });
            drawStandardChart(result[0]);
            $("#leftAqi").stop().fadeOut(100);
            setTimeout(function () {
                $("#leftAqi").css({
                    right: 30,
                    top: 115,
                    transform: 'scale(1.2)'
                });
            }, 50)
        }
    });
}

function drawStandardChart(opt) {
    var option = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}"
        },
        /*
         * toolbox : { feature : { restore : {}, saveAsImage : {} } },
         */
        series: [{
            name: 'AQI值',
            type: 'gauge',
            min: 0,
            max: 300,
            width: 50,
            splitNumber: 0,
            axisLabel: {
                formatter: function (e) {

                }
            },
            splitLine: {
                show: false
            },
            radius: '100%',
            detail: {
                formatter: '{value}'
            },
            title: {
                fontSize: 20,
                color: '#FFFFFF'
            },
            axisLine: { // 坐标轴线
                lineStyle: { // 属性lineStyle控制线条样式
                    color: [[1 / 6, '#00E400'], [2 / 6, '#FFFF00'],
                        [3 / 6, '#FF7E00'], [4 / 6, '#FF0000'],
                        [5 / 6, '#99004C'], [1, '#7E0023']],
                    width: 10,
                    shadowColor: '#000', // 默认透明
                }
            },
            axisTick: {
                show: false
            },
            data: [{
                value: opt.aqi,
                name: 'AQI值'
            }]
        }]
    };
    // 基于准备好的dom，初始化echarts图表
    if (!leftStandardChart) {
        leftStandardChart = echarts.init(document.getElementById("mlNewSingle"));
    }
    leftStandardChart.clear();
    leftStandardChart.setOption(option);
    $("#issueDate").html(opt.queryTime + "");
    $("#primary").html("首要污染物: " + (opt.primaryEp ? opt.primaryEp : '无'));
    $("#so2Value").attr("style", "color:" + getColor("SO2", opt.so2))
    $("#so2Value").html(opt.so2);
    $("#no2Value").attr("style", "color:" + getColor("NO2", opt.no2))
    $("#no2Value").html(opt.no2);
    $("#pm10Value").attr("style",
        "color:" + getColor("PM10", opt.pm10))
    $("#pm10Value").html(opt.pm10);
    $("#pm25Value").attr("style",
        "color:" + getColor("PM2.5", opt.pm25))
    $("#pm25Value").html(opt.pm25);
    $("#coValue").attr("style", "color:" + getColor("CO", opt.co))
    $("#coValue").html(opt.co);
    $("#o3Value").attr("style",
        "color:" + getColor("O3", opt.o3oneHour))
    $("#o3Value").html(opt.o3oneHour);
}
function initWeather() {
    //动态的查天气
    AMap.plugin('AMap.Weather', function () {
        var weather = new AMap.Weather();
        //查询实时天气信息, 查询的城市到行政级别的城市，如朝阳区、杭州市
        weather.getLive('汨罗市', function (err, data) {
            if (!err) {
                let weatherInfo = '当前天气情况：' + data.weather + ', 气温' + data.temperature + '℃， ' +
                    data.windDirection + '风，风力' + data.windPower + '级，空气湿度为' + data.humidity + '% <br/>更新时间:' + data.reportTime;
                // let weatherInfo = `当前天气情况：${data.weather}, 气温${data.temperature}℃，
                // ${data.windDirection}风，风力${data.windPower}级，空气湿度为${data.humidity}% <br/>更新时间:${data.reportTime}`;
                $("#weatherInfo").html(weatherInfo);
            }
        });
    });
}
function getColor(attr, value) {
    if(value<0){
        return "#b1b1b1";
    }
    if(!value){
        return "#b1b1b1";
    }
    if (attr == 'AQI') {
        if (value >= 0 && value <= 50) {
            return "#00E400";
            // return '<div class="spot" style="background-color:' + colors +
            // ';"><div>' + value + '</div></div>';
        } else if (value >= 51 && value <= 100) {
            return "#FFFF00";
        } else if (value >= 101 && value <= 150) {
            return "#FF7E00";
        } else if (value >= 151 && value <= 200) {
            return "#FF0000";
        } else if (value >= 201 && value <= 300) {
            return "#99004C";
        } else if (value > 300) {
            return "#7E0023";
        }
    } else if (attr == 'PM2.5') {
        if (value >= 0 && value <= 35) {
            return "#00E400";
        } else if (value >= 36 && value <= 75) {
            return "#FFFF00";
        } else if (value >= 76 && value <= 115) {
            return "#FF7E00";
        } else if (value >= 116 && value <= 150) {
            return "#FF0000";
        } else if (value >= 151 && value <= 250) {
            return "#99004C";
        } else if (value > 250) {
            return "#7E0023";
        }
    } else if (attr == 'PM10') {
        if (value >= 0 && value <= 50) {
            return "#00E400";
        } else if (value >= 51 && value <= 150) {
            return "#FFFF00";
        } else if (value >= 151 && value <= 250) {
            return "#FF7E00";
        } else if (value >= 251 && value <= 350) {
            return "#FF0000";
        } else if (value >= 351 && value <= 420) {
            return "#99004C";
        } else if (value > 420) {
            return "#7E0023";
        }
    } else if (attr == 'SO2') {
        if (value >= 0 && value <= 150) {
            return "#00E400";
        } else if (value >= 151 && value <= 500) {
            return "#FFFF00";
        } else if (value >= 501 && value <= 650) {
            return "#FF7E00";
        } else if (value >= 651 && value <= 800) {
            return "#FF0000";
        } else if (value > 800) {
            return "#7E0023";
        }
    } else if (attr == 'NO2') {
        if (value >= 0 && value <= 100) {
            return "#00E400";
        } else if (value >= 101 && value <= 200) {
            return "#FFFF00";
        } else if (value >= 201 && value <= 700) {
            return "#FF7E00";
        } else if (value >= 701 && value <= 1200) {
            return "#FF0000";
        } else if (value >= 1201 && value <= 2340) {
            return "#99004C";
        } else if (value > 2340) {
            return "#7E0023";
        }
    } else if (attr == 'CO') {
        if (value >= 0 && value <= 5) {
            return "#00E400";
        } else if (value >= 5.1 && value <= 10) {
            return "#FFFF00";
        } else if (value >= 10.1 && value <= 35) {
            return "#FF7E00";
        } else if (value >= 35.1 && value <= 60) {
            return "#FF0000";
        } else if (value >= 60.1 && value <= 90) {
            return "#99004C";
        } else if (value > 90) {
            return "#7E0023";
        }
    } else if (attr == 'O3') {
        if (value >= 0 && value <= 160) {
            return "#00E400";
        } else if (value >= 161 && value <= 200) {
            return "#FFFF00";
        } else if (value >= 201 && value <= 300) {
            return "#FF7E00";
        } else if (value >= 301 && value <= 400) {
            return "#FF0000";
        } else if (value >= 401 && value <= 800) {
            return "#99004C";
        } else if (value > 800) {
            return "#7E0023";
        }
    }
}