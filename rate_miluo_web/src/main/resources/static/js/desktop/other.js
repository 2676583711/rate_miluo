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
function videoContainerShow() {
    let containerHtml = '<div class="right-aqi right-show" id="rightVideoContainer">\n' +
        '    <div id="videoListMenuContainer">\n' +
        '        <div>\n' +
        '        <div class="video-screen-title">摄像头列表\n' +
    '            </div>\n' +
        '        <div class="video-screen-title">截图列表\n' +
    '            </div>\n' +
    '            </div>\n' +
        '        <div style="height: 100%;">\n' +
        '            <ul id="videoListMenu" class="list-ul">\n' +
        '            </ul>\n' +
        '            <ul id="videoScreenListMenu" class="list-ul">\n' +
        '            </ul>\n' +
        '        </div>\n' +
        '    </div>\n' +
            '<div id="videoDiv" class="videoDiv">' +
                '<video id="myPlayer" autoplay="autoplay">' +
                    '<source src="###" />' +
                '</video>' +
            '</div>'+
            '<div id="vido_constrl" class="vido_constrl">\n' +
        '        <div id="one-line" class="ctrl-line">\n' +
        '            <div><span direction="9" class="one-reduce" onclick="changeVideo(this)"></span></div>\n' +
        '            <div class="ctrl-text">变倍</div>\n' +
        '            <div><span direction="8" class="one-plus" onclick="changeVideo(this)"></span></div>\n' +
        '        </div>\n' +
        '        <div id="two-line" class="ctrl-line">\n' +
        '            <div><span direction="10" class="two-reduce" onclick="changeVideo(this)"></span></div>\n' +
        '            <div class="ctrl-text">变焦</div>\n' +
        '            <div><span direction="11" class="two-plus" onclick="changeVideo(this)"></span></div>\n' +
        '        </div>\n' +
        '        <div id="three-line" class="ctrl-line">\n' +
                    '<div class="camera-operate">' +
        '<i id="switchPlay" class=" layui-icon layui-icon-log" title="回放" onclick="clickRec()"></i></div>\n' +
                    '<div class="camera-operate">' +
        '<i class=" layui-icon layui-icon-camera" title="截图" onclick="clickScreen()"></i></div>\n' +
                    '<div class="camera-operate">' +
        '<i class=" layui-icon layui-icon-video" title="录像" onclick="vidVideo()"></i></div>' +
        '        </div>\n' +
        '        <div id="four-line">\n' +
        '            <div direction="0" class="video-ctrl-dire top-ctrl" onclick="changeVideo(this)"></div>\n' +
        '            <div direction="2" class="video-ctrl-dire left-ctrl" onclick="changeVideo(this)"></div>\n' +
        '            <div direction="3" class="video-ctrl-dire right-ctrl" onclick="changeVideo(this)"></div>\n' +
        '            <div direction="1" class="video-ctrl-dire bottom-ctrl" onclick="changeVideo(this)"></div>\n' +
        '        </div>\n' +
        '    </div>'+
        '    <div id="loadVideoDiv" class="">\n' +
        '        <div class="spinner center">\n' +
        '            <div class="spinner-container container1">\n' +
        '                <div class="circle1"></div>\n' +
        '                <div class="circle2"></div>\n' +
        '                <div class="circle3"></div>\n' +
        '                <div class="circle4"></div>\n' +
        '            </div>\n' +
        '            <div class="spinner-container container2">\n' +
        '                <div class="circle1"></div>\n' +
        '                <div class="circle2"></div>\n' +
        '                <div class="circle3"></div>\n' +
        '                <div class="circle4"></div>\n' +
        '            </div>\n' +
        '            <div class="spinner-container container3">\n' +
        '                <div class="circle1"></div>\n' +
        '                <div class="circle2"></div>\n' +
        '                <div class="circle3"></div>\n' +
        '                <div class="circle4"></div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <!--折叠按钮-->\n' +
        '    <a class="right-expand" href="javascript:void(0);" onclick="videoContentChange(this)">&nbsp;</a>\n' +
        '</div>';
    $("#rightVideoContainer").remove();
    $("body").append(containerHtml);
    let isShow = $("#leftAqi").hasClass("right-show");
    let el = $("#rightVideoContainer");
    let ela = $("#rightVideoContainer>a");
    setTimeout(function () {
        el.show();
    }, 950)
    let e = $("#leftAqi>a.right-expand");
    if(isShow){
        $(e).click();
    }
    el.animate({
        'right': '5px'
    }, 1000);
    el.addClass("right-show");
    ela.css({"background": "#18324a url(/plugins/themes/default/images/slide-right.png) no-repeat center"})
}

function videoContainerHide() {
    // let isShow = $("#leftAqi").hasClass("right-show");
    // let el = $("#rightVideoContainer");
    // el.hide();
    // let e = $("#leftAqi>a.right-expand");
    // if(!isShow){
    //     $(e).click();
    // }
    // el.animate({
    //     'right': '-600px'
    // }, 1000);
    // el.removeClass("right-show");
    // $(e).css({"background": "#18324a url(/plugins/themes/default/images/slide-left.png) no-repeat center"})
    $("#rightVideoContainer").remove();
}
function leftShow(e) {
    let el = $(e).parent();
    // let elLegend = el.find("#legendContainer");
    let width = el.width();
    if (el.hasClass("left-show")) {
        el.animate({
            'left': '-'+width+'px'
        }, 1000);
        el.removeClass("left-show");
        $(e).css({"background": "#18324a url(/plugins/themes/default/images/slide-right.png) no-repeat center"})
    } else {
        el.animate({
            'left': '0px'
        }, 1000);
        el.addClass("left-show");
        $(e).css({"background": "#18324a url(/plugins/themes/default/images/slide-left.png) no-repeat center"})
    }
}
function rightShow(e) {
    let el = $(e).parent();
    let width = el.width();
    if (el.hasClass("right-show")) {
        el.animate({
            'right': '-'+width+'px'
        }, 1000);
        el.removeClass("right-show");
        $(e).css({"background": "#18324a url(/plugins/themes/default/images/slide-left.png) no-repeat center"})
    } else {
        el.animate({
            'right': '5px'
        }, 1000);
        el.addClass("right-show");
        $(e).css({"background": "#18324a url(/plugins/themes/default/images/slide-right.png) no-repeat center"})
    }
}
function videoContentChange(e) {
    let el = $(e).parent();
    let width = el.width();
    if (el.hasClass("right-show")) {
        el.animate({
            'right': '-'+width+'px'
        }, 1000);
        el.removeClass("right-show");
        $(e).css({"background": "#18324a url(/plugins/themes/default/images/slide-left.png) no-repeat center"})
    } else {
        el.animate({
            'right': '5px'
        }, 1000);
        el.addClass("right-show");
        $(e).css({"background": "#18324a url(/plugins/themes/default/images/slide-right.png) no-repeat center"})
    }
}
function mapClickEvent() {
    map.on('click', function (e) {
        let latlng = e.latlng;
        let opt = {
            name: '',
            lat: parseFloat(latlng.lat).toFixed(6),
            lgt: parseFloat(latlng.lng).toFixed(6),
            type: 2,
        };
        console.log(opt);
    });
}
function buildAirPopupContent(siteType, siteCategory, info, newData, hour, popup){
    if(!newData){
        newData = hour[hour.length-1];
    }
    if(siteType==2){
        if(siteCategory==22 || siteCategory==23){
            let content =
                "<div class='site-popup-p' style='font-size: 20px;font-weight: bold;'>" + info.siteName + "</div>" +
                "<div class='site-popup-p'>" + (newData.queryTime?newData.queryTime:"-") + "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('AQI', newData.aqi)+"'>AQI</div>" +
                "<div class='text-center'>"+(newData.aqi?newData.aqi:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('AQI', newData.aqi)+"'>首要污染物</div>" +
                "<div class='text-center'>"+(newData.primaryEp?newData.primaryEp:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', newData.pm25)+"'>PM2.5</div>" +
                "<div class='text-center'>"+(newData.pm25?newData.pm25:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM10', newData.pm10)+"'>PM10</div>" +
                "<div class='text-center'>"+(newData.pm10?newData.pm10:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('SO2', newData.so2)+"'>SO2</div>" +
                "<div class='text-center'>"+(newData.so2?newData.so2:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('NO2', newData.no2)+"'>NO2</div>" +
                "<div class='text-center'>"+(newData.no2?newData.no2:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('CO', newData.co)+"'>CO</div>" +
                "<div class='text-center'>"+(newData.co?newData.co:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('O3', newData.o3oneHour)+"'>O3</div>" +
                "<div class='text-center'>"+(newData.o3oneHour?newData.o3oneHour:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p'>" +
                // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
                // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
                "<button onclick=\'openFrame(\"/statement/checkConcentration?siteType=2&siteId="+info.siteId+"\")\' " +
                    "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
                "<button onclick=\'openFrame(\"/index/air?siteId="+info.siteId+"\")\' " +
                    "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
                "<button onclick='openFrame(\"/airSite/detail/"+info.siteId+"\")' " +
                    "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>站点信息</button>" +
                "</div>" +
                "<div style='width: 100%;height: 350px' id='airHourChart'>" +
                "</div>" +
                "<div style='width: 100%;height: 400px' id='airHourChangeChart'>" +
                "</div>";
            popup.setContent(content);
            popup.openOn(this.map);
            if(hour){
                let aqi24Arr = [];
                let xItem = [];
                let no2 = [];
                let pm10 = [];
                let pm25 = [];
                let so2 = [];
                let co = [];
                let o3 = [];
                $.each(hour, function (idx, item) {
                    xItem.push((item.queryTime?item.queryTime.substr(0, item.queryTime.length-3):"-") + "时");
                    aqi24Arr.push(item.aqi ? item.aqi : '-');
                    no2.push(item.no2 ? item.no2 : '-');
                    pm10.push(item.pm10 ? item.pm10 : '-');
                    pm25.push(item.pm25 ? item.pm25 : '-');
                    so2.push(item.so2 ? item.so2 : '-');
                    co.push(item.co ? item.co : '-');
                    o3.push(item.o3oneHour ? item.o3oneHour : '-');
                });
                //24小时aqi变化
                setAqiImg({
                    aqi24Arr:aqi24Arr,
                    xItem:xItem});

                //24小时浓度变化
                setAirChangeImg({
                    xItem:xItem,
                    no2:no2,
                    pm10:pm10,
                    pm25:pm25,
                    so2:so2,
                    co:co,
                    o3:o3,
                    siteCategory:siteCategory});
            }else{
                $("#airHourChart").remove();
            }
        }else{
            let content =
                "<div class='site-popup-p' style='font-size: 20px;font-weight: bold;'>" + info.siteName + "</div>" +
                "<div class='site-popup-p'>" + ((newData.reportTime||newData.queryTime)?(newData.reportTime||newData.queryTime):"-") + "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', -1)+"'>TSP</div>" +
                "<div class='text-center'>"+(newData.tsp?newData.tsp:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', newData.pm25)+"'>PM2.5</div>" +
                "<div class='text-center'>"+(newData.pm25?newData.pm25:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM10', newData.pm10)+"'>PM10</div>" +
                "<div class='text-center'>"+(newData.pm10?newData.pm10:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', -1)+"'>噪声</div>" +
                "<div class='text-center'>"+(newData.noise?newData.noise:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', -1)+"'>温度</div>" +
                "<div class='text-center'>"+(newData.temp?newData.temp:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', -1)+"'>湿度</div>" +
                "<div class='text-center'>"+(newData.humi?newData.humi:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', -1)+"'>气压</div>" +
                "<div class='text-center'>"+(newData.press?newData.press:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', -1)+"'>风速</div>" +
                "<div class='text-center'>"+(newData.ws?newData.ws:"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p air-attr'>" +
                "<div style='background: "+getColor('PM2.5', -1)+"'>风向</div>" +
                "<div class='text-center'>"+(newData.wd?getWdStr(newData.wd):"-")+"</div>" +
                "</div>" +
                "<div class='site-popup-p'>" +
                // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
                // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
                "<button onclick=\'openFrame(\"/statement/checkConcentration?siteType=2&siteId="+info.siteId+"\")\' " +
                    "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
                "<button onclick=\'openFrame(\"/index/air?siteId="+info.siteId+"\")\' " +
                    "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
                "<button onclick='openFrame(\"/airSite/detail/"+info.siteId+"\")' " +
                    "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>站点信息</button>" +
                "</div>" +
                "<div style='width: 100%;height: 400px' id='airHourChangeChart'>" +
                "</div>";
            popup.setContent(content);
            popup.openOn(this.map);
            if(hour){
                let aqi24Arr = [];
                let xItem = [];
                let tsp = [];
                let pm10 = [];
                let pm25 = [];
                let noise = [];
                let temp = [];
                let humi = [];
                let press = [];
                let ws = [];
                $.each(hour, function (idx, item) {
                    xItem.push((item.queryTime?item.queryTime.substr(0, item.queryTime.length-3):"-") + "时");
                    aqi24Arr.push(item.aqi ? item.aqi : '-');
                    tsp.push(item.tsp ? item.tsp : '-');
                    pm10.push(item.pm10 ? item.pm10 : '-');
                    pm25.push(item.pm25 ? item.pm25 : '-');
                    noise.push(item.noise ? item.noise : '-');
                    temp.push(item.temp ? item.temp : '-');
                    humi.push(item.humi ? item.humi : '-');
                    press.push(item.press ? item.press : '-');
                    ws.push(item.ws ? item.ws : '-');
                });
                setAirChangeImg({
                    xItem:xItem,
                    tsp:tsp,
                    pm10:pm10,
                    pm25:pm25,
                    noise:noise,
                    temp:temp,
                    humi:humi,
                    press:press,
                    ws:ws,
                    siteCategory:siteCategory});
            }else{
                $("#airHourChangeChart").remove();
            }
        }
    }
}

function setAqiImg(inData) {
    let aqi24Arr = inData.aqi24Arr,
        xItem = inData.xItem;
    let data = [];
    $.each(aqi24Arr, function (idx, aqi) {
        data.push({
            value: aqi,
            itemStyle: {
                color: getColor("AQI", aqi)
            }
        })
    });
    let option = {
        title: {text: '最近24小时AQI变化趋势',textStyle: {color: 'white'}},
        grid: {
            containLabel: true,
            bottom: 20,
            right: 100
        },
        color: ['#fff'],
        xAxis: {
            type: 'category',
            data: xItem,
            axisLabel: {
                rotate: 60,
                color: "#fff"
            },
            axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
            },
        },
        tooltip: {
            trigger: 'axis',

        },
        yAxis: [{
            type: 'value',
            axisLabel: {
                color: "#fff"
            },

        }],
        series: [{
            data: data,
            type: 'bar',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                    color: '#fff'
                }
            },
        }]
    };


    let aqiImg = echarts.init(document.getElementById("airHourChart"));
    aqiImg.setOption(option);
}

var airMicroPol = {
    "pm10" : "PM10(ug/m³)",
    "pm25" : "PM2.5(ug/m³)",
    "tsp" : "TSP(ng/m3)",
    "noise" : "噪声(dB)",
    "temp" : "温度(℃)",
    "humi" : "湿度(%)",
    "press" : "压力(kPa)",
    "ws" : "风速"
};
function setAirChangeImg(inData){
    let xItem = inData.xItem,
        pm10 = inData.pm10,
        pm25 = inData.pm25;
    let lineOne = [];
    let item = [];
    let yAxisName0 = '';
    let yAxisName1 = '';
    let siteCategory = inData.siteCategory;
    if(siteCategory == 21){
        yAxisName1 = "风速 : m/s";
        let tsp = inData.tsp,
            noise = inData.noise,
            temp = inData.temp,
            humi = inData.humi,
            press = inData.press,
            ws = inData.ws;
        item = Object.values(airMicroPol);
        lineOne = [{
            'name': airMicroPol['pm10'],
            'type': 'line',
            'yAxisIndex': 0,
            'data': pm10
        },{
            'name': airMicroPol['pm25'],
            'type': 'line',
            'yAxisIndex': 0,
            'data': pm25
        },{
            'name': airMicroPol['tsp'],
            'type': 'line',
            'yAxisIndex': 0,
            'data': tsp
        },{
            'name': airMicroPol['noise'],
            'type': 'line',
            'yAxisIndex': 0,
            'data': noise
        },{
            'name': airMicroPol['temp'],
            'type': 'line',
            'yAxisIndex': 0,
            'data': temp
        },{
            'name': airMicroPol['humi'],
            'type': 'line',
            'yAxisIndex': 0,
            'data': humi
        },{
            'name': airMicroPol['press'],
            'type': 'line',
            'yAxisIndex': 0,
            'data': press
        },{
            'name': airMicroPol['ws'],
            'type': 'line',
            'yAxisIndex': 1,
            'data': ws
        }]
    }else{
        yAxisName0 = "浓度 ：ug/m³";
        yAxisName1 = "CO 浓度 ：mg/m³";
        let no2 = inData.no2,
            so2 = inData.so2,
            co = inData.co,
            o3 = inData.o3;
        item = ['NO2','SO2','PM10','PM2.5','CO','O3'];
        lineOne = [{
            'name': 'PM10',
            'type': 'line',
            'yAxisIndex': 0,
            'data': pm10
        },{
            'name': 'PM2.5',
            'type': 'line',
            'yAxisIndex': 0,
            'data': pm25
        },{
            'name': 'NO2',
            'type': 'line',
            'yAxisIndex': 0,
            'data': no2
        },{
            'name': 'SO2',
            'type': 'line',
            'yAxisIndex': 0,
            'data': so2
        },{
            'name': 'O3',
            'type': 'line',
            'yAxisIndex': 0,
            'data': o3
        },{
            'name': 'CO',
            'type': 'line',
            'yAxisIndex': 1,
            'data': co
        }];
    }
    let option = {
        title: {
            text: "最近24小时浓度变化趋势",
            textStyle: {
                color: 'white'
            }
        },
        grid: {
            containLabel: true,
            bottom: 20,
            right: 100
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: item,
            type: 'scroll',
            bottom: -5,
            textStyle: {
                fontSize: 12,
                color: 'white'
            }
        },
        xAxis: {
            type: 'category',
            data: xItem,
            axisLine: {
                lineStyle: {
                    color: 'white'
                }
            },
            axisLabel: {
                rotate: 60
            }
        },
        yAxis: [{
            type: 'value',
            name: yAxisName0,
            position: "left",
            yAxisIndex: 0,
            nameTextStyle: {
                fontSize: 12
            },
            axisLine: {
                lineStyle: {
                    color: 'white'
                }
            }
        },{
            type: 'value',
            name: yAxisName1,
            position: "right",
            yAxisIndex: 1,
            nameTextStyle: {
                fontSize: 12
            },
            axisLine: {
                lineStyle: {
                    color: 'white'
                }
            }
        }],
        textStyle: {
            color: 'white'
        },
        series: lineOne
    };
    let aqiImg = echarts.init(document.getElementById("airHourChangeChart"));
    aqiImg.setOption(option);
}

var polluteWaterPol = {
    "bo1": "排放量(L/s)",
    "cd": "总镉(ug/L)",
    "cu": "总铜(mg/L)",
    "pb": "总铅(ug/L)",
    "ph": "ph",
    "shen": "总砷(ug/L)",
    "zn": "总锌(mg/L)",
    "tp": "总磷(mg/L)",
    "tn": "总氮(mg/L)",
    "cod": "COD(mg/L)",
    "nh3": "氨氮(mg/L)"
}
function buildPolluteWaterPopupContent(siteType, siteCategory, info, newDatas, hour, popup){
    let content = "<div class=''>" +
                    "<div class='site-popup-p' style='font-size: 20px;font-weight: bold;'>" + info.siteName + "</div>";

    let newDatasValue = Object.values(newDatas)[0];
    for(let newDataIndex in newDatasValue){
        let newData = newDatasValue[newDataIndex];
        // let siteName = newData.siteName;
        // let siteHour = hour[siteName];
        let deviceName = newData.equName;

        content += ("<div class='site-popup-p site-popup-content-item'>" +
                        "<div style='text-align: center'>" +
                            "<div>"+ deviceName +"</div>" +
                            "<div>"+(newData.recordingTime?newData.recordingTime:"-")+"</div>" +
                        "</div>" +
                        (newData.bo1?(
                            "<div class='site-popup-p pollute-attr'>" +
                                "<div>排放量(L/s)</div>" +
                                "<div class='text-center'>" + (newData.bo1) + "</div>" +
                            "</div>"):'') +
                        (newData.ph?(
                                newData.ph2?(
                            "<div class='site-popup-p pollute-attr2'>" +
                                "<div>ph</div>" +
                                "<div class='text-center'>"+(newData.ph)+"</div>" +
                            "</div>"):(
                                    "<div class='site-popup-p pollute-attr'>" +
                                    "<div>ph</div>" +
                                    "<div class='text-center'>"+(newData.ph)+"</div>" +
                                    "</div>"
                                )
                                ):'') +
                        (newData.pb?(
                            "<div class='site-popup-p pollute-attr'>" +
                                "<div>总铅(ug/L)</div>" +
                                "<div class='text-center'>"+(newData.pb)+"</div>" +
                            "</div>"):'') +
                        (newData.cd?(
                                newData.cd2?(
                            "<div class='site-popup-p pollute-attr2'>" +
                                "<div>总镉(ug/L)</div>" +
                                "<div class='text-center'>"+(newData.cd)+"</div>" +
                            "</div>"):(
                                    "<div class='site-popup-p pollute-attr'>" +
                                    "<div>总镉(ug/L)</div>" +
                                    "<div class='text-center'>"+(newData.cd)+"</div>" +
                                    "</div>"
                                )
                                ):'') +
                        (newData.cu?(
                            "<div class='site-popup-p pollute-attr'>" +
                                "<div>总铜(mg/L)</div>" +
                                "<div class='text-center'>"+(newData.cu)+"</div>" +
                            "</div>"):'') +
                        (newData.shen?(
                            "<div class='site-popup-p pollute-attr'>" +
                                "<div>总砷(ug/L)</div>" +
                                "<div class='text-center'>"+(newData.shen)+"</div>" +
                            "</div>"):'') +
                        (newData.zn?(
                            "<div class='site-popup-p pollute-attr'>" +
                                "<div>总锌(mg/L)</div>" +
                                "<div class='text-center'>"+(newData.zn)+"</div>" +
                            "</div>"):'') +
                        (newData.tp?(
                                newData.tp2?(
                            "<div class='site-popup-p pollute-attr2'>" +
                                "<div>总磷(mg/L)</div>" +
                                "<div class='text-center'>"+(newData.tp)+"</div>" +
                            "</div>"):(
                                    "<div class='site-popup-p pollute-attr'>" +
                                    "<div>总磷(mg/L)</div>" +
                                    "<div class='text-center'>"+(newData.tp)+"</div>" +
                                    "</div>"
                                )
                                ):'') +
                        (newData.tn?(
                                newData.tn2?(
                            "<div class='site-popup-p pollute-attr2'>" +
                                "<div>总氮(mg/L)</div>" +
                                "<div class='text-center'>"+(newData.tn)+"</div>" +
                            "</div>"):(
                                    "<div class='site-popup-p pollute-attr'>" +
                                    "<div>总氮(mg/L)</div>" +
                                    "<div class='text-center'>"+(newData.tn)+"</div>" +
                                    "</div>"
                                )
                                ):'') +
                        (newData.cod?(
                            "<div class='site-popup-p pollute-attr'>" +
                                "<div>COD(mg/L)</div>" +
                                "<div class='text-center'>"+(newData.cod)+"</div>" +
                            "</div>"):'') +
                        (newData.nh3?(
                            "<div class='site-popup-p pollute-attr'>" +
                                "<div>氨氮(mg/L)</div>" +
                                "<div class='text-center'>"+(newData.nh3)+"</div>" +
                            "</div>"):'') +
                    "</div>");
        }
        content += ("<div class='site-popup-p'>" +
        // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
        // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
        "<button onclick=\'openFrame(\"/statement/checkConcentration?siteType=4&siteId="+info.siteId+"\")\' " +
            "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
        "<button onclick=\'openFrame(\"/index/pollutewater?siteId="+info.siteId+"\")\' " +
            "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
        "<button onclick=\'openFrame(\"/airSite/detail/"+info.siteId+"\")\' " +
            "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>站点信息</button>" +
        "</div>" +
        "</div>" +
        "<div style='width: 100%;' id='hourCharts'>" +
        "</div>");
    popup.setContent(content);
    popup.openOn(this.map);
    setPolluteImg(hour);
}
function setPolluteImg(hour) {
    let count = 0;
    $.each(hour, function (index, item) {
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
        $.each(item, function (idx, site) {
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
            xItems.push((site.recordingTime?site.recordingTime.substr(0, site.recordingTime.length-3):"-") + "时");
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
            yAxisIndex: 0
        }, {
            name: '总铜(mg/L)',
            data: cu,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '总铅(ug/L)',
            data: pb,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: 'ph',
            data: ph,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '总砷(ug/L)',
            data: shen,
            type: 'line',
            yAxisIndex: 0
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
        var option = {
            title: {
                text: index.substring(index.indexOf("(")+1,index.indexOf(")")) + "最近24小时数据趋势"
                ,textStyle: {color: 'white'}
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
            legend: {
                data: Object.values(polluteWaterPol),
                type: 'scroll',
                bottom: 'bottom',
                textStyle: {
                    fontSize: 12
                }
            },
            xAxis: {
                type: 'category',
                data: xItems,
                axisLine: {
                    lineStyle: {
                        color: 'while'
                    }
                },
            },
            yAxis: [{
                type: 'value'
            },{
                type: 'value',
                name: " (排放量、PH)",
                position: "right",
            }],
            series: lineOne
        };
        count++;
        $("#hourCharts").append( "<div id='hourChart"+count+"' class='hour-chart'></div>");
        let aqiImg = echarts.init(document.getElementById("hourChart"+count));
        aqiImg.setOption(option);
    });
}
// "<div style='background: "+getColor('PM2.5', -1)+"'>风向</div>" +
function buildPollutePopupContent(siteType, siteCategory, info, newDatas, hour, popup){
    let content = "<div class=''>" +
        "<div class='site-popup-p' style='font-size: 20px;font-weight: bold;'>" + info.siteName + "</div>";
    for(let i = 0; i < newDatas.length; i++){
        let newData = newDatas[i];
        let deviceName = newData.equipmentName;
        content += ("<div class='site-popup-p site-popup-content-item'>" +
            "<div style='text-align: center'>" +
                "<div>" + deviceName + "</div>" +
                "<div>" + (newData.recordingTime?newData.recordingTime:"-") + "</div>" +
            "</div>" +
            ((newData.so2 || new Data.so2 === 0)?(
                newData.so22?(
                "<div class='site-popup-p pollutant-attr2'>" +
                "<div >二氧化硫(mg/m³)</div>" +
                "<div class='text-center'>" + newData.so2 + "</div>" +
                "</div>"):(
                    "<div class='site-popup-p pollutant-attr'>" +
                    "<div>二氧化硫(mg/m³)</div>" +
                    "<div class='text-center'>" + newData.so2 + "</div>" +
                    "</div>")
            ):'') +
            ((newData.nox || newData.nox === 0)?(
                newData.nox2?(
                "<div class='site-popup-p pollutant-attr2'>" +
                "<div>氮氧化物(mg/m³)</div>" +
                "<div class='text-center'>" + newData.nox + "</div>" +
                "</div>"):(
                    "<div class='site-popup-p pollutant-attr'>" +
                    "<div>氮氧化物(mg/m³)</div>" +
                    "<div class='text-center'>" + newData.nox + "</div>" +
                    "</div>")
                ): '') +
            ((newData.o2 || newData.o2 === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>氧气含量(%)</div>" +
                "<div class='text-center'>" + newData.o2 + "</div>" +
                "</div>"):'') +
            ((newData.flowVelocity || newData.flowVelocity === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>烟气流速(m/s)</div>" +
                "<div class='text-center'>" + newData.flowVelocity + "</div>" +
                "</div>"):'') +
            ((newData.temp || newData.temp === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>烟气温度(℃)</div>" +
                "<div class='text-center'>" + newData.temp + "</div>" +
                "</div>"):'') +
            ((newData.press || newData.temp === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>烟气压力(kPa)</div>" +
                "<div class='text-center'>" + newData.press + "</div>" +
                "</div>"):'') +
            ((newData.dust || newData.dust === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>烟尘(mg/m³)</div>" +
                "<div class='text-center'>" + newData.dust + "</div>" +
                "</div>"):'') +
            ((newData.exhaust || newData.exhaust === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>排放量(m³/s)</div>" +
                "<div class='text-center'>" + newData.exhaust + "</div>" +
                "</div>"):'') +
            ((newData.co || newData.co === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>一氧化碳(mg/m³)</div>" +
                "<div class='text-center'>" + newData.co + "</div>" +
                "</div>"):'') +
            ((newData.co2 || newData.co2 === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>二氧化碳(mg/m³)</div>" +
                "<div class='text-center'>" + newData.co2 + "</div>" +
                "</div>"):'') +
            ((newData.hcl || newData.hcl === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>氯化氢(mg/m³)</div>" +
                "<div class='text-center'>" + newData.hcl + "</div>" +
                "</div>"):'') +
            ((newData.no || newData.no === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>一氧化氮(mg/m³)</div>" +
                "<div class='text-center'>" + newData.no + "</div>" +
                "</div>"):'') +
            ((newData.no2 || newData.no2 === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>二氧化氮(mg/m³)</div>" +
                "<div class='text-center'>" + newData.no2 + "</div>" +
                "</div>"):'') +
            ((newData.humidity || newData.humidity === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>烟气湿度(%)</div>" +
                "<div class='text-center'>" + newData.humidity + "</div>" +
                "</div>"):'') +
            ((newData.avgTempInChamber || newData.avgTempInChamber === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>炉膛内平均温度(℃)</div>" +
                "<div class='text-center'>" + newData.avgTempInChamber + "</div>" +
                "</div>"):'') +
            ((newData.dcsTempInChamber || newData.dcsTempInChamber === 0)?(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>炉膛内DCS温度(℃)</div>" +
                "<div class='text-center'>" + newData.dcsTempInChamber + "</div>" +
                "</div>"):'') +
            "</div>");
    }
    content += ("<div class='site-popup-p'>" +
        // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
        // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
        "<button onclick=\'openFrame(\"/statement/checkConcentration?siteType=3&siteId="+info.siteId+"\")\' " +
        "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
        "<button onclick=\'openFrame(\"/index/pollute?siteId="+info.siteId+"\")\' " +
        "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
        "<button onclick=\'openFrame(\"/airSite/detail/"+info.siteId+"\")\' " +
        "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>站点信息</button>" +
        "</div>" +
        "</div>" +
        "<div style='width: 100%;' id='hourCharts'>" +
        "</div>");
    popup.setContent(content);
    popup.openOn(this.map);
    setbuildPollutePopupImg(hour);
}

function setbuildPollutePopupImg(hour) {
    var buildPollutePopups = {
        "exhaust": "排放量(m³/s)",
        "dust": "烟尘(mg/m³)",
        "o2": "氧含量(%)",
        "so2": "二氧化硫(mg/m³)",
        "nox": "氮氧化物(mg/m³)",
        "flowVelocity": "烟气流速(m/s)"
    }
    let count = 0;
    $.each(hour, function (index, item) {
        let exhaust = [];
        let dust = [];
        let o2 = [];
        let so2 = [];
        let nox = [];
        let flowVelocity = [];
       /* let zn = [];
        let tp = [];
        let tn = [];
        let cod = [];
        let nh3 = [];*/
        let xItems = [];
        $.each(item, function (idx, site) {
            exhaust.push(site.exhaust);
            dust.push(site.dust);
            o2.push(site.o2);
            so2.push(site.so2);
            nox.push(site.nox);
            flowVelocity.push(site.flowVelocity);
           /* zn.push(site.zn);
            tp.push(site.tp);
            tn.push(site.tn);
            cod.push(site.cod);
            nh3.push(site.nh3);*/
            xItems.push((site.recordingTime?site.recordingTime.substr(0, site.recordingTime.length-3):"-") + "时");
        });
        var lineOne = [{
            name: '排放量(m³/s)',
            data: exhaust,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '烟尘(mg/m³)',
            data: dust,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '氧含量(%)',
            data: o2,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '二氧化硫(mg/m³)',
            data: so2,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: '氮氧化物(mg/m³)',
            data: nox,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: '烟气流速(m/s)',
            data: flowVelocity,
            type: 'line',
            yAxisIndex: 0
        }]
        var option = {
            title: {
                text: index+ "最近24小时数据趋势"
                ,textStyle: {color: 'white'}
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
            legend: {
                data: Object.values(buildPollutePopups),
                type: 'scroll',
                bottom: 'bottom',
                textStyle: {
                    fontSize: 12
                }
            },
            xAxis: {
                type: 'category',
                data: xItems,
                axisLine: {
                    lineStyle: {
                        color: 'while'
                    }
                },
            },
            yAxis: [{
                type: 'value'
            },{
                type: 'value',
                name: " 氮氧化物(mg/m³)",
                position: "right",
            }],
            series: lineOne
        };
        count++;
        $("#hourCharts").append( "<div id='hourChart"+count+"' class='hour-chart'></div>");
        let aqiImg = echarts.init(document.getElementById("hourChart"+count));
        aqiImg.setOption(option);
    });
}

var waterPol = {
    "sw": "水温(℃)",
    "zd": "浊度(NTU)",
    "ddl": "电导率(μS/cm)",
    "codmn": "CODMn(≤6[mg/L])",
    "pb": "铅",
    "rjy": "溶解氧(≥5[mg/L])",
    "ph": "ph",
    "shen": "砷(mg/L)",
    "nh3N": "氨氮(≤1[mg/L])",
    "comprehensiveToxicity": "综合毒性(%)",
    "tp": "总磷(≤0.2[mg/L])",
    "chromium": "总铬",
    "cd": "镉(mg/L)",
    "kmn": "高锰酸盐指数(mg/L)",
    "temp": "室温(℃)",
    "humi": "湿度(%)",
    "yls": "叶绿素(ug/L)",
    "llz": "蓝绿藻(Kcells/mL)",
};
function buildWaterPopupContent(siteType, siteCategory, info, newDatas, hour, popup){
    let content = "<div class='' style='width: auto'>" +
                    "<div class='site-popup-p' style='font-size: 20px;font-weight: bold;'>" + info.siteName + "</div>" +
                "</div>";
    let names = Object.keys(hour);
    for(let nameIndex in names){
        let siteName = names[nameIndex];
        let siteHour = hour[siteName];
        let deviceName = siteName.substring(siteName.indexOf("(")+1,siteName.indexOf(")"));
        let newData = siteHour[siteHour.length-1];
        content += ("<div class='site-popup-p site-popup-content-item'>" +
                        "<div style='text-align: center'>" +
                            "<div>"+ deviceName +"</div>" +
                            "<div>"+(newData.dateTime?newData.dateTime:"-")+"</div>" +
                        "</div>" +

                        "<div class='site-popup-p water-attr'>" +
                            "<div>水温(℃)</div>" +
                            "<div class='text-center'>"+(newData.sw?newData.sw:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                            "<div>浊度(NTU)</div>" +
                            "<div class='text-center'>"+(newData.zd?newData.zd:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                            "<div>电导率(μS/cm)</div>" +
                            "<div class='text-center'>"+(newData.ddl?newData.ddl:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                            "<div>CODMn(≤6[mg/L])</div>" +
                            "<div class='text-center'>"+(newData.codmn?newData.codmn:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                            "<div>铅</div>" +
                            "<div class='text-center'>"+(newData.pb?newData.pb:"-")+"</div>" +
                        "</div>" +
                       (newData.ph2?(
                       "<div class='site-popup-p pollutant-attr2'>" +
                       "<div>溶解氧(≥5[mg/L])</div>" +
                      "<div class='text-center'>" + newData.rjy + "</div>" +
                     "</div>"):(
                     "<div class='site-popup-p pollutant-attr'>" +
                      "<div>溶解氧(≥5[mg/L])</div>" +
                      "<div class='text-center'>"+(newData.rjy?newData.rjy:"-")+"</div>" +
                     "</div>")) +

                         (newData.ph2?(
                         "<div class='site-popup-p pollutant-attr2'>" +
                         "<div>ph</div>" +
                         "<div class='text-center'>" + newData.ph + "</div>" +
                           "</div>"):(
                             "<div class='site-popup-p pollutant-attr'>" +
                             "<div>ph</div>" +
                             "<div class='text-center'>"+(newData.ph?newData.ph:"-")+"</div>" +
                             "</div>")) +

                        "<div class='site-popup-p water-attr'>" +
                            "<div>砷(mg/L)</div>" +
                            "<div class='text-center'>"+(newData.shen?newData.shen:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                            "<div>氨氮(≤1[mg/L])</div>" +
                            "<div class='text-center'>"+(newData.nh3N?newData.nh3N:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                            "<div>综合毒性(%)</div>" +
                            "<div class='text-center'>"+(newData.comprehensiveToxicity?newData.comprehensiveToxicity:"-")+"</div>" +
                        "</div>" +
                          (newData.tp2?(
                "<div class='site-popup-p pollutant-attr2'>" +
                "<div>总磷(≤0.2[mg/L])</div>" +
                "<div class='text-center'>" + newData.tp + "</div>" +
                "</div>"):(
                "<div class='site-popup-p pollutant-attr'>" +
                "<div>总磷(≤0.2[mg/L])</div>" +
                "<div class='text-center'>"+(newData.tp?newData.tp:"-")+"</div>" +
                "</div>")) +

                        "<div class='site-popup-p water-attr'>" +
                            "<div>总铬</div>" +
                            "<div class='text-center'>"+(newData.chromium?newData.chromium:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                            "<div>镉</div>" +
                            "<div class='text-center'>"+(newData.cd?newData.cd:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                        "<div>高锰酸盐指数</div>" +
                        "<div class='text-center'>"+(newData.kmn?newData.kmn:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                        "<div>室温</div>" +
                        "<div class='text-center'>"+(newData.temp?newData.temp:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                        "<div>湿度</div>" +
                        "<div class='text-center'>"+(newData.humi?newData.humi:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                        "<div>叶绿素(ug/l)</div>" +
                        "<div class='text-center'>"+(newData.yls?newData.yls:"-")+"</div>" +
                        "</div>" +
                        "<div class='site-popup-p water-attr'>" +
                        "<div>蓝绿藻(Kcells/mL)</div>" +
                        "<div class='text-center'>"+(newData.llz?newData.llz:"-")+"</div>" +
                        "</div>")
    }
        content += ("<div class='site-popup-p'>" +
        // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
        // "<button onclick='' class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
        "<button onclick=\'openFrame(\"/statement/checkConcentration?siteType=1&siteId="+info.siteId+"\")\' " +
            "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>历史数据</button>" +
        "<button onclick=\'openFrame(\"/index/water?siteId="+info.siteId+"\")\' " +
            "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>报警数据</button>" +
        "<button onclick=\'openFrame(\"/airSite/detail/"+info.siteId+"\")\' " +
            "class='btn btn-info' style='font-size: 12px;margin: 2px 5px;'>站点信息</button>" +
        "</div>" +
        "</div>" +
        "<div style='width: 100%;' id='hourCharts'>" +
        "</div>");
    popup.setContent(content);
    popup.openOn(this.map);
    setWaterImg(hour);
}
function setWaterImg(hour) {
    let count = 0;
    $.each(hour, function (index, siteData) {
        let sw = [];
        let zd = [];
        let ddl = [];
        let codmn = [];
        let pb = [];
        let rjy = [];
        let ph = [];
        let shen = [];
        let nh3N = [];
        let comprehensiveToxicity = [];
        let tp = [];
        let chromium = [];
        let cd = [];
        let kmn = [];
        let temp = [];
        let humi = [];
        let yls = [];
        let llz = [];

        let xItems = [];
        $.each(siteData, function (idx, site) {
            sw.push(site.sw?site.sw:'-');
            zd.push(site.zd?site.zd:'-');
            ddl.push(site.ddl?site.ddl:'-');
            codmn.push(site.codmn?site.codmn:'-');
            pb.push(site.pb?site.pb:'-');
            rjy.push(site.rjy?site.rjy:'-');
            ph.push(site.ph?site.ph:'-');
            shen.push(site.shen?site.shen:'-');
            nh3N.push(site.nh3N?site.nh3N:'-');
            comprehensiveToxicity.push(site.comprehensiveToxicity?site.comprehensiveToxicity:'-');
            tp.push(site.tp?site.tp:'-');
            chromium.push(site.chromium?site.chromium:'-');
            cd.push(site.cd?site.cd:'-');
            kmn.push(site.kmn?site.kmn:'-');
            temp.push(site.temp?site.temp:'-');
            humi.push(site.humi?site.humi:'-');
            yls.push((site.yls?site.yls:'-'));
            llz.push((site.llz?site.llz:'-'));
            xItems.push(site.dateTime);
        });
        var lineOne = [{
            name: waterPol['sw'],
            data: sw,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['zd'],
            data: zd,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['ddl'],
            data: ddl,
            type: 'line',
            yAxisIndex: 1
        }, {
            name: waterPol['codmn'],
            data: codmn,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['pb'],
            data: pb,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['rjy'],
            data: rjy,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['ph'],
            data: ph,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['shen'],
            data: shen,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['nh3N'],
            data: nh3N,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['comprehensiveToxicity'],
            data: comprehensiveToxicity,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['tp'],
            data: tp,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['chromium'],
            data: chromium,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['cd'],
            data: cd,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['kmn'],
            data: kmn,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['temp'],
            data: temp,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['humi'],
            data: humi,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['yls'],
            data: yls,
            type: 'line',
            yAxisIndex: 0
        }, {
            name: waterPol['llz'],
            data: llz,
            type: 'line',
            yAxisIndex: 0
        }]
        var option = {
            title: {
                text: index.substring(index.indexOf("(")+1,index.indexOf(")")) + "最近24小时数据趋势",
                textStyle: {color: 'white'}
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
            legend: {
                data: Object.values(waterPol),
                type: 'scroll',
                bottom: 'bottom',
                textStyle: {
                    fontSize: 12
                }
            },
            xAxis: {
                type: 'category',
                data: xItems,
                axisLine: {
                    lineStyle: {
                        color: 'while'
                    }
                },
            },
            yAxis: [{
                type: 'value'
            },{
                type: 'value',
                name: " 电导率",
                position: "right",
            }],
            series: lineOne
        };
        count++;
        $("#hourCharts").append( "<div id='hourChart"+count+"' class='hour-chart'></div>");
        let aqiImg = echarts.init(document.getElementById("hourChart"+count));
        aqiImg.setOption(option);
    });
}

function openFrame(url) {
    var page = layer.open({
        type : 2,
        title : '详细信息',
        // shift : -1,
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '480px' ],
        content : url
    });
    layer.full(page);
}
function getWdStr(wd) {
    var text = ['北风', '东北风', '东风', '东南风', '南风' ,'西南风', '西风', '西北风'];
    if(wd>337.5 || wd<=22.5 && wd > 0){
        return text[0];
    }else if(wd>22.5 && wd<=67.5){
        return text[1];
    }else if(wd>67.5 && wd<=112.5){
        return text[2];
    }else if(wd>112.5 && wd<=157.5){
        return text[3];
    }else if(wd>157.5 && wd<=202.5){
        return text[4];
    }else if(wd>202.5 && wd<=247.5){
        return text[5];
    }else if(wd>247.5 && wd<=292.5){
        return text[6];
    }else if(wd>292.5 && wd<=337.5){
        return text[7];
    }
}