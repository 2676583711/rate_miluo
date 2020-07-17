let map;
let markerLayer;
let typeOfSite;
let siteOnlyInfo;
let markers;

function getMap(map) {
    this.map = map;
}(window);

$(function () {
    layui.use('laydate',function() {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#dateTime'
            ,type: 'month'
            ,range: false,
            value :preMonth(new Date())
        });
    });

    initWaterWarnMark();
})
function preMonth(nowDate) {
    if (nowDate != null) {
        return new Date(nowDate.setMonth((nowDate.getMonth() - 1)));
    }
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
function initWaterWarnMark() {
    $.ajax({
        url: "/water/warn/getMarker",
        type: "get",
        success: function (res) {
            markers = res;
            putSiteWaterMarkerToMap(markers);
        }
    });
}
let markerClick = null;
//init是否首次加载 -> initOnlyMarker
function putSiteWaterMarkerToMap(markers, init) {
    debugger;
    if (markerLayer) {
        this.map.removeLayer(markerLayer);
    }
        $.each(siteOnlyInfo, function (idx, item) {
            let siteId = item.siteId;
            let isPush = true;
            for(let i in markers){
                let siteMk = markers[i];
                if(siteId==(siteMk.siteId || siteMk.siteid)){
                    isPush = false;
                    break;
                }
            }
            if(isPush){
                markers.push(item);
            }
        });
    siteMarkers = [];
    $.each(markers, function (idx, siteItem) {
        let icon = "";
        let bg = "";
        if(siteItem.pollutant){
            icon = "scan-warn";
            bg = "sensor";
            if((siteItem.siteType || siteItem.sitetype) == '1'){
                bg = "autosite-warn";
            }else if((siteItem.siteType || siteItem.sitetype) == '3'){
                bg = "pollute-warn";
            }else if((siteItem.siteType || siteItem.sitetype) == '4'){
                bg = "water-plant";
            }
        }else if(siteItem.queryTime || siteItem.querytime || siteItem.dateTime){
            icon = "scan";
            bg = "sensor";
            if((siteItem.siteType || siteItem.sitetype) == '1'){
                bg = "autosite-online";
            }else if((siteItem.siteType || siteItem.sitetype) == '3'){
                bg = "pollute-online";
            }else if((siteItem.siteType || siteItem.sitetype) == '4'){
                bg = "water-plant";
            }
        } else if(init && siteItem.tails && siteItem.tails.type === 'only'){
            icon = "transparent";
            bg = "offline";
            if((siteItem.siteType || siteItem.sitetype) == '1'){
                bg = "autosite-offline";
            }else if((siteItem.siteType || siteItem.sitetype) == '3'){
                bg = "pollute-offline";
            }else if((siteItem.siteType || siteItem.sitetype) == '4'){
                bg = "water-plant";
            }
        } else{
            icon = "scan-offline";
            bg = "offline";
            if((siteItem.siteType || siteItem.sitetype) == '1'){
                bg = "autosite-offline";
            }else if((siteItem.siteType || siteItem.sitetype) == '3'){
                bg = "pollute-offline";
            }else if((siteItem.siteType || siteItem.sitetype) == '4'){
                bg = "water-plant";
            }
        }

        let str = '<div title="' + (siteItem.siteName || siteItem.sitename) + '"><ul class="test">' +
            '<li class="p1">' +
            '<img src="/image/desktop/'+icon+'.png"></li>' +
            '<li class="p2"><img src="/image/desktop/'+bg+'.png"></li>' +
            '</ul></div>';
        if (siteItem.lat && siteItem.lng) {
            let markerItem = L.marker([siteItem.lat, siteItem.lng], {
                icon: L.divIcon({
                    className: 'ship-div-icon',
                    // iconAnchor: [-5, -20],
                    // popupAnchor: [0, -36],
                    html: str
                }),
                riseOnHover: true
            });
            let mkClick = function (e) {
                $("#parentLoadVideoDiv").removeClass("hidden");
                let siteId = siteItem.siteId;
                let siteCategory = siteItem.siteCategory || siteItem.sitecategory;
                let siteType = siteItem.siteType || siteItem.sitetype;
                if(markerClick){
                    return;
                }
                markerClick = setTimeout(function () {
                    if(siteType==2){
                        showAirSiteInfo(siteId, siteCategory, siteType);
                    }else if(siteType==3){
                        showPolluteSiteInfo(siteId, siteCategory, siteType);
                    }else if(siteType==1){
                        showWaterSiteInfo(siteId, siteCategory, siteType);
                    }else if(siteType==4){
                        showPolluteWaterSiteInfo(siteId, siteCategory, siteType);
                    }
                    markerClick = null;
                },100);
            };
            markerItem.on('click', mkClick);
            let mouseOverFn = function(){
                $(this._icon).find(".p1>img").css({
                    width: 48,
                    height: 48,
                });
                $(this._icon).find(".p1").css({
                    top: -24,
                    left: -16,
                });
                $(this._icon).find(".p2>img").css({
                    width: 16,
                    height: 22,
                });
            };
            let mouseOutFn = function(){
                $(this._icon).find(".p1>img").css({
                    width: 36,
                    height: 36,
                });
                $(this._icon).find(".p1").css({
                    top: -15,
                    left: -13,
                });
                $(this._icon).find(".p2>img").css({
                    width: 10,
                    height: 14,
                });
            };
            markerItem.on('mouseover', mouseOverFn);
            markerItem.on('mouseout', mouseOutFn);
            siteMarkers.push(markerItem);
        }else{
            markers.slice(idx,1);
        }
    });
    markerLayer = L.layerGroup(siteMarkers);
    this.map.addLayer(markerLayer);
}