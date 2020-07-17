
//利用高德接口   获取行政区边界坐标GeoJson标准数据
//市区划编码
var adcode = 430600;
let center = [28.80858, 113.080902];
let map;
map = new AMap.Map('container', {
    resizeEnable: true,
    center: center
});
map.setMapStyle("blue_night");
setTimeout(function () {
    initPage()
},50)
function initPage() {
    //加载DistrictExplorer，loadUI的路径参数为模块名中 'ui/' 之后的部分
    AMapUI.loadUI(['geo/DistrictExplorer'], function (DistrictExplorer) {
        //启动页面
        //创建一个实例
        var districtExplorer = new DistrictExplorer({
            map: map //关联的地图实例
        });
        districtExplorer.loadAreaNode(adcode, function (error, areaNode) {
            if (error) {
                console.error(error);
                return;
            }
            //绘制载入的区划节点
            //地级市 GeoJSON数据
            //console.log(JSON.stringify(areaNode._data.geoData))
            renderAreaNode(districtExplorer, areaNode);
            //areaNode
            //指定地区数据  GeoJson
        });
    });
}

function renderAreaNode(districtExplorer, areaNode) {
    //绘制子级区划
    districtExplorer.renderSubFeatures(areaNode, function (feature, i) {
        if(feature.properties.name == "汨罗市"){
            console.log({"汨罗市" : JSON.stringify(feature)})
            return {
                cursor: 'default',
                bubble: true,
                strokeColor: "#3366cc", //线颜色
                strokeOpacity: 1, //线透明度
                strokeWeight: 1, //线宽
                fillColor: "#dc3912", //填充色
                fillOpacity: 0.35, //填充透明度
            };
        }
    });

    //绘制父级区划，仅用黑色描边
    districtExplorer.renderParentFeature(areaNode, {
        cursor: 'default',
        bubble: true,
        strokeColor: 'black', //线颜色
        fillColor: null,
        strokeWeight: 3, //线宽
    });

}