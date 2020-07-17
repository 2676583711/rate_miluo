let map=null;
let mlAreaBorder;
// let map;
let siteData = [];
let siteOnlyInfo;
let currentSites = [];
let siteMarkers = [];
let markerLayer;

let player;
let accessToken;
let videoType;
let deviceSerial;
let channelNo;
let vidiconId;
let singleSiteData;
let typeOfSite;
let curVideo;

let popupIsShow = false;
let firstInitState=false;

function getMap(map) {
    this.map = map;
}(window);

$(function () {
    // setInterval(function () {
    //     initMarker();
    // }, 1000*45);
    // setInterval(function () {
    //     mlNewEcharts();713
    // }, 1000*60*10);
    setInterval(function () {
        checkSiteOnline();  // 每隔三分钟更新站点的在线状态
        initMarker();
    }, 1000*60*3);
    //
    initMarker();
    $(".legend-div>img").hover(function () {
        $(this).stop().animate({width: 90,height:90}, 200); // stop()防止重复动画
    }, function () {
        $(this).stop().animate({width: 75,height:75}, 200);
    });
    // 根据图例展示对应站点图标
    $("#siteLegend p").click(function () {
        $("#siteLegend p").css("background","none");
        $(this).css("background-color","#9D0300");
        typeOfSite = this.dataset.type;
        if(typeOfSite && typeOfSite != ''){
            singleSiteData = currentSites.filter(function (site) {
                return (site.siteType == typeOfSite) || (site.sitetype == typeOfSite);
            });
        }else{
            singleSiteData = currentSites;
        }
        putSiteMarkerToMap(singleSiteData);
    });
    //* $("#siteTreeContainer").css({'z-index':"-1"});
    $.siteTree.build("#siteTreeContainer");
    $.siteTree.treeLoadedEvent = function () {
        // checkSiteOnline()
        $("#siteTreeContainer a").attr("href", "");  // 使“所有站点”节点不可点击
        $.siteTree.hideTab(["1","2","3","4"]);
        $("#siteTreeContainer").width(0);
        $("#siteTreeContainer").css({"z-index":"402"});
        $("#leftLegend").width('initial');  // 保留原始宽度
        // initCheckSiteOnline();
        // obCheckSiteOnline('site', true);
        let i = setInterval(function () {
            if(state){
                clearInterval(i);
                initCheckSiteOnline();  // 初始化树中站点的在线状态
            }
        },10);
    }
    // 点击左侧树节点
    $.siteTree.treeClickEvent = function (e, data) {
        let siteId = data.node.id;
        let site = siteData.filter(function (site) {
            return site.siteId == siteId;
        })[0];
        // 站点有经纬度，则定位，否则提示坐标错误
        if(site.lat && site.lng){
            window.map.panTo([site.lat, site.lng]);
            for(let num in siteMarkers){
                let marker = siteMarkers[num];
                if(marker._latlng.lat==site.lat && marker._latlng.lng==site.lng){
                    $(marker._icon).click();
                    return;
                }
            }
        }else{
            layer.msg((site.siteName|| site.sitename) + "坐标数据错误！");
        }

    }
    // $("#hoverShowAqi,#leftAqi").hover(function () {
    //     $("#leftAqi").stop().fadeIn(350)
    // }, function () {
    //     $("#leftAqi").stop().fadeOut(350)
    // })
})
// let zoomStart=11;
let zoomEnd=11;
let p1w = 48;
let p1h = 48;
let p2w = 16;
let p2h = 22;
let p1t = 24;
let p1l = 16;
let p1Abs = {
            11:{
                top: -24,
                left: -16
            },
            9:{
                top: -19,
                left: -13
            },
            8:{
                top: -16,
                left: -12
            },

};
let onlineInitState={
  site: false,
  data: false
};
// function obCheckSiteOnline(key, value) {
//     onlineInitState[key] = value;
//     if(onlineInitState.site && onlineInitState.data){
//         initCheckSiteOnline();
//     }
// }
function initCheckSiteOnline() {
    changeSiteOnlineState(state);
}
function checkSiteOnline() {
    $.ajax({
        url: "/desktop/site/state",
        type: "get",
        success: function (res) {
            changeSiteOnlineState(res);
        },
    });
}
// 渲染左边树种站点的在线离线状态
function changeSiteOnlineState(state){
    var air=0;//报警
    var airOnline=0;//在线
    var water=0;
    var waterOnline=0;
    var pollutant=0;
    var pollutantOnline=0;
    var pollutantSite=0;
    var pollutantSiteOnline=0;
    $.each(state, function (index, site) {
        let treeI = $("#content-"+(site.siteType || site.sitetype) + " #" + site.siteId + " a i");
       var s=  site.siteType;
       if (s==1){

           if(site.pollutant){
               treeI.html("<div class='tree-state tree-li-warn'></div>")
               air++
               // }else if(site.dateTime && site.dateTime.startsWith("20")){
           }else if(site.dateTime && site.dateTime[0]=='2'&&site.dateTime[1]=='0'){
               treeI.html("<div class='tree-state tree-li-run'></div>")
               airOnline++
           } else{
               treeI.html("<div class='tree-state tree-li-offline'></div>")

           }
       } else if (s==2){

           if(site.pollutant){
               treeI.html("<div class='tree-state tree-li-warn'></div>")
               water++
               // }else if(site.dateTime && site.dateTime.startsWith("20")){
           }else if(site.dateTime && site.dateTime[0]=='2'&&site.dateTime[1]=='0'){
               treeI.html("<div class='tree-state tree-li-run'></div>")
               waterOnline++
           } else{
               treeI.html("<div class='tree-state tree-li-offline'></div>")

           }
       } else if (s==3){


           if(site.pollutant){
               treeI.html("<div class='tree-state tree-li-warn'></div>")
               pollutant++;
               // }else if(site.dateTime && site.dateTime.startsWith("20")){
           }else if(site.dateTime && site.dateTime[0]=='2'&&site.dateTime[1]=='0'){
               treeI.html("<div class='tree-state tree-li-run'></div>")
               pollutantOnline++;
           } else{
               treeI.html("<div class='tree-state tree-li-offline'></div>")

           }

       } else {


           if(site.pollutant){
               treeI.html("<div class='tree-state tree-li-warn'></div>")
               pollutantSite++;
               // }else if(site.dateTime && site.dateTime.startsWith("20")){
           }else if(site.dateTime && site.dateTime[0]=='2'&&site.dateTime[1]=='0'){
               treeI.html("<div class='tree-state tree-li-run'></div>")
               pollutantSiteOnline++
           } else{
               treeI.html("<div class='tree-state tree-li-offline'></div>")

           }
       }

    });
    $("li.jstree-node[aria-level=2]>a>i").each(function (index, item) {
        if($(item).find("div").length===0){
            $(item).html("<div class='tree-state tree-li-offline'></div>")
        }
    });
    var airSumline=air+airOnline;
    var waterSumline=water+waterOnline;
    var pollutantSumline=pollutant+pollutantOnline;
    var pollutantSiteSumline =pollutantSite+pollutantSiteOnline;


    $("li[lay-id='-1']").html("水质站("+airSumline+"/"+num1 +")")

    var ss=   $("li[lay-id='-2']").html()
    $("li[lay-id='-2']").html("空气站("+waterSumline+"/"+num2 +")")
    var ss=   $("li[lay-id='-3']").html()
    $("li[lay-id='-3']").html("涉气污染源("+pollutantSumline+"/"+num3 +")")
    var ss=   $("li[lay-id='-4']").html()
    $("li[lay-id='-4']").html("涉水污染源("+pollutantSiteSumline+"/"+num4 +")")
   /* $("li[lay-id='-1']").html("水质站("+air+"/"+airOnline+")")
    $("li[lay-id='-2']").html("空气站("+water+"/"+waterOnline+")")
    $("li[lay-id='-3']").html("涉气污染源("+pollutant+"/"+pollutantOnline+")")
    $("li[lay-id='-4']").html("涉水污染源("+pollutantSite+"/"+pollutantSiteOnline+")")*/


}
function mapZoomEvent(){
    // map.on("zoomstart", function () {
    //     zoomStart = map._zoom;
    // })
    // map.on("zoomend", function () {
    //     zoomEnd = map._zoom;
    //
    //     let c = zoomEnd/11;
    //     let p1NewW = p1w*c;
    //     let p1NewH = p1h*c;
    //     $(".p1 img").css({
    //         width: p1NewW,
    //         height: p1NewH
    //     });
    //     let p2NewW = p2w*c;
    //     let p2NewH = p2h*c;
    //     $(".p2 img").css({
    //         width: p2NewW,
    //         height: p2NewH
    //     });
    //     try{
    //         $(".p1").css({
    //             top: p1Abs[zoomEnd].top,
    //             left: p1Abs[zoomEnd].left
    //         });
    //     }catch (e) {
    //     }
    //     // putSiteMarkerToMap(currentSites);
    // })
}



function firstInitMarker(state) {
    firstInitState=true;
        //siteData = state;
        currentSites = state;
        putSiteMarkerToMap(currentSites);
}
function initOnlyMarker() {
    $.ajax({
        url: "/desktop/site/only/marker",
        type: "get",
        success: function (res) {
            debugger;
            siteOnlyInfo = res;
            siteData = res;
            // currentSites = res;
            if(!firstInitState){
                putSiteMarkerToMap(siteData, true);
            }
        }
    });
}
function initMarker() {
    $.ajax({
        url: "/desktop/site/marker",
        type: "get",
        success: function (res) {
            siteData = res;
            currentSites = res;
            putSiteMarkerToMap(siteData);
        }
    });
}

function changeSiteTreeTab(type, e){
    if($(e).hasClass("site-legend-active")){
        $.siteTree.hideTab([1,2,3,4]);
        $(e).removeClass("site-legend-active");
        $("#siteTreeContainer").stop().animate({width: 0}, 1000);
    }else{
        $(".legend-div").removeClass("site-legend-active");
        $(e).addClass("site-legend-active");
        $.siteTree.hideTab([1,2,3,4]);
        $.siteTree.showTab(type);
        $("#siteTreeContainer").stop().animate({width: 253}, 1000);
    }
}

function airSiteChoice(){
    if($("#air-site-container").is(":visible")){
        $("#air-site-container").addClass("hidden");
    }else{
        $("#air-site-container").removeClass("hidden");
    }
}

function markerClassify(e){
    return;
    if($(e).hasClass("site-legend-active")){
        $(e).removeClass("site-legend-active");
    }else{
        $(e).addClass("site-legend-active");
    }
    if($("#air-site-container>div.site-legend-active").length===0){
        $("#air-site-parent").removeClass("site-legend-active");
    }else{
        $("#air-site-parent").addClass("site-legend-active");
    }
    setTimeout(function () {
        $("#air-site-container").addClass("hidden");
    }, 100);
    currentSites = [];
    $(".site-legend-active").each(function (index, li) {
        let val = $(this).attr("site-type");
        if(val){
            let kv = val.split(",");
            let curr = siteData.filter(function (site) {
                return site[kv[0]] == kv[1];
            });
            currentSites.push.apply(currentSites, curr);
        }
    });
    putSiteMarkerToMap(currentSites);
}

let markerClick = null;
//init是否首次加载 -> initOnlyMarker
function putSiteMarkerToMap(markers, init) {
    if (markerLayer) {
        this.map.removeLayer(markerLayer);
    }
    if((!typeOfSite || typeOfSite =='') && siteOnlyInfo&&markers.length!==siteOnlyInfo.length){
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
    }
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
let siteVideos;
//空气
function showAirSiteInfo(siteId, siteCategory, siteType) {
    $.ajax({
        url: "/desktop/air/site",
        type: "get",
        data: {
            siteId: siteId,
            siteCategory: siteCategory,
            siteType: siteType
        },
        success: function (res) {
            let info = res.info;
            // let newData={};
            // if(res.hour){
            //     newData = res.hour[res.hour.length - 1];
            // }
            siteVideos = res.videos?res.videos:[];
            let popupEntity = buildPopup();
            popupEntity.setLatLng([info.lat, info.lng]);
            // if(siteCategory=='21'){
            //     popupEntity.setContent(buildAirPopupContent(siteType,siteCategory,info,res.newData,null,popupEntity));
            // }else{
            //     popupEntity.setContent(buildAirPopupContent(siteType,siteCategory,info,res.newData,res.hour,popupEntity));
            // }
            popupEntity.setContent(buildAirPopupContent(siteType,siteCategory,info,res.newData,res.hour,popupEntity));
            $("#parentLoadVideoDiv").addClass("hidden");
        },
        error: function (e) {
            $("#parentLoadVideoDiv").addClass("hidden");
        }
    });
}
//污染源站
function showPolluteWaterSiteInfo(siteId, siteCategory, siteType) {
    $.ajax({
        url: "/desktop/polluteWater/site",
        type: "get",
        data: {
            siteId: siteId,
            siteCategory: siteCategory,
            siteType: siteType
        },
        success: function (res) {
            let info = res.info;
            videoScreenPaths = [];
            siteVideos = res.videos?res.videos:[];
            // buildVideoContent(siteVideos);
            let popupEntity = buildPopup();
            popupEntity.setLatLng([info.lat, info.lng]);
            popupEntity.setContent(buildPolluteWaterPopupContent(siteType,siteCategory,info,res.newData, res.hour,popupEntity));
            $("#parentLoadVideoDiv").addClass("hidden");
        },
        error: function (e) {
            $("#parentLoadVideoDiv").addClass("hidden");
        }
    });
}
//污染源
function showPolluteSiteInfo(siteId, siteCategory, siteType) {
    $.ajax({
        url: "/desktop/pollute/site",
        type: "get",
        data: {
            siteId: siteId,
            siteCategory: siteCategory,
            siteType: siteType
        },
        success: function (res) {

            let info = res.info;
            videoScreenPaths = [];
            siteVideos = res.videos?res.videos:[];
            // buildVideoContent(siteVideos);
            let popupEntity = buildPopup();
            popupEntity.setLatLng([info.lat, info.lng]);
            popupEntity.setContent(buildPollutePopupContent(siteType,siteCategory,info,res.newData, res.hour,popupEntity));
            $("#parentLoadVideoDiv").addClass("hidden");
        },
        error: function (e) {
            $("#parentLoadVideoDiv").addClass("hidden");
        }
    });
}
//水
function showWaterSiteInfo(siteId, siteCategory, siteType) {
    $.ajax({
        url: "/desktop/water/site",
        type: "get",
        data: {
            siteId: siteId,
            siteCategory: siteCategory,
            siteType: siteType
        },
        success: function (res) {
            let info = res.info;
            videoScreenPaths = [];
            siteVideos = res.videos?res.videos:[];
            // buildVideoContent(siteVideos);
            let popupEntity = buildPopup();
            popupEntity.setLatLng([info.lat, info.lng]);
            popupEntity.setContent(buildWaterPopupContent(siteType,siteCategory,info,res.newData,res.hour,popupEntity));
            $("#parentLoadVideoDiv").addClass("hidden");
        },
        error: function (e) {
            $("#parentLoadVideoDiv").addClass("hidden");
        }
    });
}
let popupShowNum = 0;
function popupShowNumOperate(plus, reduce) {
    if(plus){
        popupShowNum+=plus;
    }
    if(reduce){
        popupShowNum-=reduce;
    }
    return popupShowNum;
}
function buildPopup() {
    gallery = undefined;
    let popupEntity = L.popup({
            className: "site-popup-content",
            closeOnClick: true,
            maxWidth: 560,
            minWidth: 560,
            maxHeight: 350,
        }
    );
    videoScreenPaths = [];
    setTimeout(function () {
        popupShowNumOperate(1);
        videoContainerShow();
        initVideoScreenImg(siteVideos);
    },  100);
    popupEntity.on("close", function () {
        if(popupShowNumOperate()){
            popupShowNumOperate(0,1);
            videoContainerHide();
        }
    });
    return popupEntity;
}
let videoScreenPaths = [];
function initVideoScreenImg(videos){
    if(videos && videos.length>0){
        curVideo = videos.shift();
        printScreen(curVideo, videos);
    }else if(videos.length===0){
        buildVideoContent(videoScreenPaths)
    }
}

// 图片大小
function buildVideoContent(){
    let size = videoScreenPaths.length;

    if(size > 0){
        // let LiHeight = Math.floor(110/size);
        $("#videoListMenu").html("");
        let html = "";

        $.each(videoScreenPaths, function (index, video) {
            let pathAndClass = video.path.split('?');
            html += '<li class="'+pathAndClass[1]+'"><img video-id="'+video.obj.id+'" title="'+video.obj.vidiconName+'" src="'+video.path+'" class="right-video-sc"/></li>';
        });
        $("#videoListMenu").html(html);
        // $("#videoListMenu").find("li").css('width', LiWidth);
        // $("#videoListMenu").find("img").css('height', LiWidth);
        // $("#videoListMenu").find("li").attr('width', LiWidth);
    }
    let w;
    let h;
    $("#videoListMenu>li").hover(function () {
        let $this = $(this).find("img");
        w = $this.width();
        h = $this.height();
            $this.stop().animate({width: 120,height: 120}, 200);
    }, function () {
        let $this = $(this).find("img");
            $this.stop().animate({width: w,height: h}, 200);
    })
    $("#videoListMenu>li").unbind("click");
    $("#videoListMenu>li").click(function (e) {
        let $this = $(e.target);
        if($this.is("li")){
            $this = $this.find("img");
        }
        if($this.hasClass("right-video-sc-active")){
            $this.removeClass("right-video-sc-active");
            $this.width(40);
            $this.height(40);
            clearVideo();
        }else{
            $("#videoListMenu>li>img").removeClass("right-video-sc-active");
            $("#videoListMenu>li>img").width(40);
            $("#videoListMenu>li>img").height(40);
            $this.addClass("right-video-sc-active")
            $this.width(60);
            $this.height(60);
            clearVideo();
            let id = $this.attr("video-id");
            let $video = videoScreenPaths.filter(function (video) {
                return video.obj?video.obj.id==id:false;
            });
            requestVidicon($video?$video[0].obj:null);
        }
    });
    $("#loadVideoDiv").addClass("hidden");
}

function clearVideo(){
    if(player){
        try{
            player.initCKPlayer()
        }catch (e) {
        }
    }
    vidiconId = null;
    $("#videoDiv object").remove();
    $("#videoDiv").html("");
    $("#videoDiv").html("<video autoplay='autoplay' id='myPlayer' poster='' controls width='400' height='300'>" +
        "                <source src='###' />" +
        "            </video>");
}
function initVideoList(){
    if(!siteVideos.length){
        $("#videoSelect").hide();
        $("#videoDiv").hide();
    }else{
        for(var v in siteVideos){
            $("#videoSelect").append("<option value='"+v+"'>"+siteVideos[v].vidiconName+"</option>");
        }
        requestVidicon(siteVideos[$("#videoSelect").val()]);
    }
}
function requestVidicon(video){
    if(!video){
        return;
    }
    vidiconId = video.id;
    deviceSerial = video.seriesNumber;
    channelNo = video.channelNo;
    videoType = video.supplier;
    if (video.supplier == "海康") {
        accessToken = video.accessToken;
        let d = video.defaultUrl;
        if (d.indexOf("ezopen") > -1) {
            let url = "https://open.ys7.com/ezopen/h5/iframe_se?url=" + video[d] + "&autoplay=1&audio=1&accessToken=" + accessToken;

            //let url = "https://open.ys7.com/ezopen/h5/iframe?url=" + video[d] + "&autoplay=1&accessToken=" + accessToken;
            let iframe = "<iframe id='ysOpenDevice' width=400 height=300 src='" + url + "' allowfullscreen></iframe>";
            $("#videoDiv").html(iframe);
        } else {
            try {
                $("#myPlayer").attr("src", d ? video[d] : video.rtmphdUrl);
                player = new EZUIPlayer('myPlayer');
                // player.play();  宽高为400 300时才支持自动播放
            } catch (e) {
                console.log(e);
            }
        }
    }
}


let isRec = false;
function clickRec() {

    if (!isRec) {       // 点击回放，先把之前的video或者iframe清了
        $("#videoDiv").empty();
        if (curVideo) {     // 第一次不需要时间
            let videoSrc="https://open.ys7.com/ezopen/h5/iframe_se?url="+curVideo.ezopenPlaybackUrl+"&autoplay=1&audio=1&accessToken="+ curVideo.accessToken;

           // let videoSrc = "https://open.ys7.com/ezopen/h5/iframe?url=" + curVideo.ezopenPlaybackUrl + "&autoplay=1&accessToken=" + curVideo.accessToken;
            let iframe = "<iframe id='ysOpenDevice' width=400 height=345 src='" + videoSrc + "' allowfullscreen></iframe>";
            $("#videoDiv").html(iframe);
            let str = "<div class='form-inline'>" +
                "<input class='form-control videoTime' id='begin' type='text' placeholder='开始时间' autocomplete='off'/>" +
                "<span> -- </span>" +
                "<input style='margin-right: 25px;' class='form-control videoTime' id='end' type='text' placeholder='结束时间' autocomplete='off' />" +
                "<button type='button' class='layui-btn layui-btn-sm layui-btn-primarys' onclick='changeRec()'>播放</button>"
                "</div>";
            $("#videoDiv").append(str);
            $("#switchPlay").removeClass('layui-icon-log');
            $("#switchPlay").addClass('layui-icon-play');
            $("#switchPlay").attr('title', '直播');
            // 正在回放
            isRec = true;
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                laydate.render({
                    type: 'datetime',
                    elem: '#begin',
                    trigger: 'click'
                });
            });
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                laydate.render({
                    type: 'datetime',
                    elem: '#end',
                    trigger: 'click'
                });
            });
        }
    } else {   // 退回到实时播放
        clearVideo();
        requestVidicon(curVideo);
        $("#switchPlay").addClass('layui-icon-log');
        $("#switchPlay").removeClass('layui-icon-play');
        $("#switchPlay").attr('title', '回放');
        isRec = false;
    }
}

function changeRec() {  // 选择时间后再播放
    if (curVideo) {
        let begin = format($("#begin").val());
        let end = format($("#end").val());
        let videoSrc = "https://open.ys7.com/ezopen/h5/iframe_se?url=" + curVideo.ezopenPlaybackUrl + (begin !== '' ? ("&begin=" + begin) : '') + (end !== '' ? ("&end=" + end) : '') + "&autoplay=1&audio=1&accessToken=" + curVideo.accessToken;

     //   let videoSrc = "https://open.ys7.com/ezopen/h5/iframe?url=" + curVideo.ezopenPlaybackUrl + (begin !== '' ? ("&begin=" + begin) : '') + (end !== '' ? ("&end=" + end) : '') + "&autoplay=1&accessToken=" + curVideo.accessToken;
        $("#ysOpenDevice").attr('src', videoSrc);
    }
}
function format(str) {
    if (str.indexOf("-") > 0) {
        str = str.replace("-", "").replace("-", "");
    }
    if (str.indexOf(":") > 0) {
        str = str.replace(":", "").replace(":", "");
    }
    if (str.indexOf(" ") > 0) {
        str = str.replace(" ", "");
    }
    return str;
}
function changeVideo(a) {
    if (videoType == "海康") {
        var direction = $(a).attr("direction");
        changeVideoDirection(direction);
    } else {
        //弹出窗
        $.alert({
            title : '提示',
            content : "目前该设备不能支持云台控制",
            boxWidth : '40px',
            buttons : {
                ok : {
                    text : '确定'
                }
            }
        });
    }
}


function changeVideoDirection(direction) {
    var obj = {
        accessToken : accessToken,
        deviceSerial : deviceSerial,
        channelNo : channelNo,
        direction : direction,
        speed : 1
    }
    $.ajax({
        url : 'https://open.ys7.com/api/lapp/device/ptz/start',
        type : 'post',
        data : obj,
        async : false,
        success : function(data) {
            console.log(data);
            $.ajax({
                url : 'https://open.ys7.com/api/lapp/device/ptz/stop',
                type : 'post',
                data : obj,
                async : false,
                success : function(data) {

                }
            });
        }
    });

}

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
            $("#leftAqi").css({
                right: 55,
                top: 5,
            });
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
    $("#issueDate").html(opt.queryTime + "发布");
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
//截图
var gallery;
function printScreen(video, videos) {
    $.ajax({
        cache : true,
        type : "POST",
        url : "https://open.ys7.com/api/lapp/device/capture",
        data : {
            accessToken : video.accessToken,
            deviceSerial : video.seriesNumber,
            channelNo : video.channelNo
        },
        success : function(data) {
            // console.log(data);
           // videoScreenPaths.push({path: data.data ? data.data.picUrl : "/image/desktop/video_offline.svg?right-video-sc-none", obj: video});
            videoScreenPaths.push({path: data.data ? data.data.picUrl : "/image/desktop/sheXiangTou.jpg?right-video-sc-none", obj: video});
            if(data.data && data.data.picUrl){
                $("#videoScreenListMenu").append('<li><img src="'+data.data.picUrl+'" class="right-video-sc"/></li>');
            }
            if(!gallery){
                gallery = new Viewer(document.getElementById('videoScreenListMenu'));
            }else{
                gallery.update();
            }
            initVideoScreenImg(videos)
        }
    });
}

//截图
function clickScreen() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "https://open.ys7.com/api/lapp/device/capture",
        data : {
            accessToken : accessToken,
            deviceSerial : deviceSerial,
            channelNo : channelNo
        },
        async : false,
        success : function(data) {
            if (data.code == "200") {
                //将图片上传至服务器
                $("#videoScreenListMenu").append('<li><img src="'+data.data.picUrl+'" class="right-video-sc"/></li>');
                if(!gallery){
                    gallery = new Viewer(document.getElementById('videoScreenListMenu'));
                }else{
                    gallery.update();
                }
                uploadPic(data.data.picUrl);
            } else {
                parent.layer.alert(data.msg);
            }
        }
    });
}
//上传截图
function uploadPic(picUrl) {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/vidicon/uploadPic",
        data : {
            picUrl : picUrl,
            vidiconId : vidiconId
        },
        success : function(data) {
            if (data.code == 0) {
                gallery.view($("#videoScreenListMenu>li").length-1)
                // parent.layer.alert("截图成功!!");
            } else {
                parent.layer.alert("截图失败!!");
            }
        }
    });
}