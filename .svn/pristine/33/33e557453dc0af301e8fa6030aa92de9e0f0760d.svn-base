let apiCss = "/plugin/windy/windytv.css";
let apiJs = "/plugin/windy/windytv.js";
// img
let startbuttonImgUrl = "/plugin/windy/startbutton.png";
let stopbuttonImgUrl = "/plugin/windy/stopbutton.png";

var windytyInit = {
    // Required: API key
    key: 'PsL-At-XpsPTZexBwUkO7Mx5I',
    apiCss: apiCss,
    apiJs: apiJs,
    // Optional: Initial state of the map
    lat: 28.783909,
    lon: 113.107681,
    zoom: 11
}
// windyty回调函数
function windytyMain(initmap) {
    map = initmap;
    // 地图初始设置
    //map.setZoom(10);
    addShapeFile(map);
    // mapClickEvent();
    // 添加左上角AQI详细窗口控件
    //addAqiDialogControl(map);

    // 添加左上角下拉框
    //addAqiSelect2(map)

    // 添加右上角AQI选项控件
    // addAqiItemControl(map);

    // 添加左下角AQI控件
    // addAqiControl(map);

    // addSourceControl(map, W);

    // 添加右下角图层及高度控件
    addOptionControl(map, W);
    // map.on("zoomend", function (ev) {
    //     console.log(map.getZoom());
    // });
    // 添加进度条
    // addAqiRangeControl(map);
    // initMarker();
    // firstInitMarker();
    // initOnlyMarker();
    mapZoomEvent();

    // 默认加载风的内容(否则，会出现滑动不显示周边区域的问题)
    // setTimeout(function () {
    //     W.setOverlay('temp');
    //     W.setOverlay('wind');
    // }, 2000);
}

function addShapeFile(map) {
        mlAreaBorder = L.geoJson(mlGeo, {
            style: {
                color: "#1E9FFF",
                fillColor: "#E8E8E8",
                weight: 2,
                opacity: 1,
                fillOpacity: 0.2
            }
        });
        mlAreaBorder.addTo(map);
}
