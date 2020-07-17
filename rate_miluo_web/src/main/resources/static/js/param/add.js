let prefix = "/site/param/configuration";

function videoChange(e) {
    let videoId = e.value;
    if (!videoId || videoId == '') {
        return;
    }
    summerFresh.dataService(prefix + "/inputFactorList", {videoId:videoId}, function (res) {
        let str = '<option value="">--选择因子--</option>';
        let data = res;
        let videoType = res[0].type;
        let typeName = '';
        switch (videoType) {
            case '1': typeName = '水质'; break;
            case '2': typeName = '空气'; break;
            case '3': typeName = '污水厂'; break;
            case '4': typeName = '污染源（气）'; break;
        }
        $("#videoType").val(typeName);
        for (let i=0; i<data.length; i++) {
            str += '<option value="'+data[i][id]+'">'+data[i][name]+'</option>'
        }
        $("#name").html(str);
    }, 'get', true)
}