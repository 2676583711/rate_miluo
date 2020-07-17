let maps;
function oldMapInit(map){
    this.maps=map
    map.remove();
    let center = [28.783909, 113.107681];
    let layerControl;
    let pollClick = false;
    let siteVideos = [];
         initMap();
    function initMap() {
        let normalm = L.tileLayer.chinaProvider('GaoDe.Normal.Map', {
            maxZoom: 18,
            minZoom: 3
        });
        let blue = L.tileLayer.chinaProvider('Geoq.Normal.PurplishBlue', {
            maxZoom: 16,
            minZoom: 2
        });
        let imgm = L.tileLayer.chinaProvider('GaoDe.Satellite.Map', {
            maxZoom: 18,
            minZoom: 3
        });
        let imga = L.tileLayer.chinaProvider('GaoDe.Satellite.Annotion', {
            maxZoom: 18,
            minZoom: 3
        });

        baseMapLayer = L.layerGroup([blue]);
        let normal = L.layerGroup([normalm]),
            image = L.layerGroup([imgm, imga]);
        baseLayers = {
            "蓝底": baseMapLayer,
            "地图": normal,
            "影像": image
        }
        this.map=map;
        map.on("zoomend", function (ev) {
            if(map.getZoom()<11){
                getNewMap();
            }
            changeAqiSiteStyle(map.getZoom());
        });
        map.setView(center, 12);
        layerControl = L.control.layers(baseLayers);
        layerControl.addTo(map);
        baseMapLayer.add(map)
        initOnlyMarker();
        addShapeFile(map)
    }

    function changeTab(e) {
        if (e === 1) {
            $("#rightRank li:eq(0)").removeClass("right-expand2");
            $("#rightRank li:eq(0)").addClass("right-active");
            $("#rightRank li:eq(1)").removeClass("right-active");
            $("#rightRank li:eq(1)").addClass("right-expand2");
            $("#rankContent").show();
            $("#rightInfoContent").hide();
            $("#rightInfoContent1").hide();
        } else {
            $("#rightRank li:eq(1)").removeClass("right-expand2");
            $("#rightRank li:eq(1)").addClass("right-active");
            $("#rightRank li:eq(0)").removeClass("right-active");
            $("#rightRank li:eq(0)").addClass("right-expand2");
            $("#rankContent").hide();
            if (pollClick) {
                $("#rightInfoContent").hide();
                $("#rightInfoContent1").show();
                pollClick = undefined;
            } else {
                $("#rightInfoContent1").hide();
                $("#rightInfoContent").show();
            }

        }
    }

    function initTableRow(stationType, eqName){
        if(!eqName){
            // eqName = eqName.toLowerCase().replace(".","");
            // }else{
            return [];
        }

        let list = [];
        rankDataList.forEach(function(item) {
            if (item.stationType == stationType) {
                list.push({siteName: item['stationName'], value: item[eqName] + ""});
            }
        });
        list.sort(function(a,b){return a.value-b.value});
        let i = 0;
        list.forEach(function (item) {
            item['ranking'] = ++i;
        });
        return list;
    }
    let rankDataList=[];

    function rankTable(stationType, eqName, data) {
        $('#exampleTable').bootstrapTable('destroy');
        $('#exampleTable')
            .bootstrapTable(
                {
                    method: 'get', // 服务器数据的请求方式 get or post
                    //url: "/desktop/rank/list",
                    // showRefresh : true,
                    // showToggle : true,
                    // showColumns : true,
                    iconSize: 'outline',
                    toolbar: '#exampleToolbar',
                    //striped : true, // 设置为true会有隔行变色效果
                    dataType: "json", // 服务器返回的数据类型
                    //pagination : true, // 设置为true会在底部显示分页条
                    // queryParamsType : "limit",
                    // //设置为limit则会发送符合RESTFull格式的参数
                    singleSelect: false, // 设置为true将禁止多选
                    // contentType : "application/x-www-form-urlencoded",
                    // //发送到服务器的数据编码类型
                    // pageSize : 10, // 如果设置了分页，每页数据条数
                    //pageNumber : 1, // 如果设置了分布，首页页码
                    // search : true, // 是否显示搜索框
                    //showColumns: false, // 是否显示内容下拉框（选择显示的列）
                    //sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                    // "server"
                    queryParams: function (params) {
                        return {
                            // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                            stationType: stationType,
                            eqName: eqName
                        };
                    },
                    onClickRow: function (row, tr, field) {
                        let siteName = row.siteName;
                        let currentSite = siteData.filter(function (d) {
                            return d.name === siteName
                        });
                        if (currentSite.length === 1) {
                            let lgtd = currentSite[0].longitude;
                            let lttd = currentSite[0].latitude;
                            map.setView([lttd, lgtd], {animate: true});
                            map.setZoom(15, {animate: true});
                        }
                    },
                    onLoadSuccess: function (res) {

                        if (res && res.length > 0) {
                            if ($("#station7").val() != 7) {
                                let time = res[0].queryTime;
                                time && $("#rightRankTopTime").text("硚口区空气质量排名(" + time.substring(0, time.length - 3) + "时发布)");
                                //console.log(res[0]);
                            }
                        }
                    },
                    columns: [
                        {
                            field: 'siteName',
                            title: '站点',
                            align: "center",
                            width: '200',
                        }, {
                            field: 'value',
                            title: '监测值',
                            align: "center",
                            width: '200',
                            // formatter: function (value, row, index) {
                            //     if (value != null && value != "") {
                            //
                            //         return Math.round(parseFloat(value));
                            //     }
                            //     if (value == null) {
                            //         return "-"
                            //     }
                            // }
                        }, {
                            field: 'ranking',
                            title: '排名',
                            align: "center",
                            width: '80',
                        }
                    ]
                });
        $('#exampleTable').bootstrapTable('load', data);
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

}
