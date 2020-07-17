let siteVideos = [];
$(function () {
    showSiteInfo();
    $("#videoSelect").change(function (e) {
        clearVideo()
        requestVidicon(siteVideos[$("#videoSelect").val()])
    });
});
function showSiteInfo() {

    $.ajax({
        url:"/index/polluteDataVideo",
        type:"get",
        data: {
            id: $("#id").val(),},
        success: function (res) {
            try {
                initVideoHtml(res);
            }catch (e) {
                console.error("video Error");
            }
        }
    });
}
var player;
let accessToken;
let videoType;
let deviceSerial;
let ChannelNumber;
function initVideoHtml(res) {
    siteVideos = [];
    if(res.videos && res.videos.length>0){
        $("#videoSelect, #videoDiv").removeClass("hidden");
        clearVideo();
        $("#videoSelect").html("");
        siteVideos = res.videos;
        initVideoList();
    }else{
        $("#videoSelect, #videoDiv").addClass("hidden");
    }
}
function clearVideo(){
    if(player){
        player.initCKPlayer()
    }
    $("#videoDiv object").remove();
    $("#videoDiv").html("");
    $("#videoDiv").html("<video autoplay='autoplay' id='myPlayer'>" +
        "                <source src='###' />" +
        "            </video>");
}
function initVideoList(){
    for(var v in siteVideos){
        $("#videoSelect").append("<option value='"+v+"'>"+siteVideos[v].vidiconName+"</option>");
    }
    requestVidicon(siteVideos[$("#videoSelect").val()]);
}
function requestVidicon(video){
    //src /vidicon/vidiconVideoData
    deviceSerial = video.seriesNumber;//"124312157"
    ChannelNumber = '1';
    videoType = video.supplier;//"海康"
    if (video.supplier == "海康") {
        accessToken = video.accessToken;
        $("#myPlayer").attr("src", video.rtmpUrl);
        player = new EZUIPlayer('myPlayer');
        player.play();
    }
}