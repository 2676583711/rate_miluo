let prefix = "/statistic/pollutant";
let tabIndex = "1";
var tabInitFlag = {
    1: true,
    2: false,
    3: false,
    4: false,
    5: false
};
let seasonF = {
    "1": "第一季度",
    "2": "第二季度",
    "3": "第三季度",
    "4": "第四季度"
};
let siteColName = {
    "0": "站点（类型）",
    "1": "站点",
    "2": "站点",
    "3": "站点",
    "4": "区域",
};

$(function () {
    //年月选择器
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var now = new Date();
        laydate.render({
            elem: '#monthStartTime',
            type: 'month',
            btns: ['now', 'confirm'],
            value: now
        });laydate.render({
            elem: '#monthEndTime',
            type: 'month',
            btns: ['now', 'confirm'],
            value: now
        });
        renderSeasonDate("#seasonStartTime");
        renderSeasonDate("#seasonEndTime");
        laydate.render({
            elem: '#yearStartTime',
            type: 'year',
            btns: ['now', 'confirm'],
            value: now
        });laydate.render({
            elem: '#yearEndTime',
            type: 'year',
            btns: ['now', 'confirm'],
            value: now
        });
        laydate.render({
            elem: '#dayStartTime',
            type: 'date',
            btns: ['now', 'confirm'],
            value: new Date(new Date().getTime() - 24 * 60 * 60 * 1000)
        });laydate.render({
            elem: '#dayEndTime',
            type: 'date',
            btns: ['now', 'confirm'],
            value: now
        });
        function renderSeasonDate(ohd) {
            var ele = $(ohd);
            let ch = parseInt((new Date().getMonth() + 3) /3 );

            laydate.render({
                elem: ohd,
                type: 'month',
                format: 'yyyy年第M季度',
                btns: ['clear', 'confirm'],
                value: new Date().getFullYear() + "年第"+ch+"季度",
                //range: sgl?null:'~',
                //min:"1900-1-1",
                ready: function(value, date, endDate) {
                    var hd = $("#layui-laydate" + ele.attr("lay-key"));
                    // console.log("hd=" + hd);
                    if (hd.length > 0) {
                        hd.click(function() {
                            ren($(this));
                        });
                    }
                    ren(hd);
                },
                done: function(value, date, endDate) {
                    if (!isNull(date) && date.month > 0 && date.month < 5) {
                        ele.attr("startDate", date.year + "-" + date.month);
                    } else {
                        ele.attr("startDate", "");
                    }
                    if (!isNull(endDate) && endDate.month > 0 && endDate.month < 5) {
                        ele.attr("endDate", endDate.year + "-" + endDate.month)
                    } else {
                        ele.attr("endDate", "");
                    }
                }
            });
        }
        var ren = function(thiz) {
            var mls = thiz.find(".laydate-month-list");
            mls.each(function(i, e) {
                $(this).find("li").each(function(inx, ele) {
                    var cx = ele.innerHTML;
                    // console.log('inx'+inx);
                    if (inx < 4) {
                        ele.innerHTML = cx.replace(/月/g, "季度");
                    } else {
                        ele.style.display = "none";
                    }
                });
            });
        }
        function isNull(s) {
            if (s == null || typeof(s) == "undefined" || s == "") return true;
            return false;
        }
    });
    let i = setInterval(function () {
        if ($("#monthStartTime").val() != '' && $("#monthEndTime").val() != '' && siteCodes != undefined) {
            initPrimaryContent();
            clearInterval(i);
        }
    }, 10);
    $("#myTab a").click(function (e) {
        e.preventDefault();
        $("#myTab li:first-child").removeClass('active')
        $(this).tab('show');
        let dataValue = this.dataset.value;
        tabIndex = dataValue;
        changeTabContentInputId(dataValue);
    });
});

function getHeight() {
    let ul = $("#myTab").outerHeight();
    let body = window.innerHeight;
    let se1 = $(".select-bg").outerHeight();
    let se2 = $(".select-bg1").outerHeight();
    return body - ul - se1 - se2 - 30;
}

function changeTabContentInputId(dataValue) {
    showExp(dataValue);
    if (!tabInitFlag[dataValue]) {
        tabInitFlag[dataValue] = true;
        showLoad();
        switch (dataValue) {
            case '1':
                initPrimaryContent();
                break;
            case '2':
                initPrimaryChartContent();
                break;
            case '3':
                initOverproofContent();
                break;
            case '4':
                initOverproofChartContent();
                break;
            case '5':
                initOverproofRateContent();
                break;
        }
        closeLoad();
    }
}

function showExp(dataValue) {
    switch (dataValue) {
        case '1':case '3':case '5':
            $("#myExp").show();
            break;
        case '2':case '4':
            $("#myExp").hide();
            break;
    }
}

function reload() {
    if (siteCodes.length == 0) {
        layer.alert("请先选择站点");
        return;
    }
    showLoad();
    switch (tabIndex) {
        case "1":
            initPrimaryContent();
            break;
        case "2":
            initPrimaryChartContent();
            break;
        case "3":
            initOverproofContent();
            break;
        case "4":
            initOverproofChartContent();
            break;
        case "5":
            initOverproofRateContent();
            break;
    }
    closeLoad();
}

function exportExcel() {
    if (siteCodes.length == 0) {
        layer.alert("请先选择站点");
        return;
    }
    if (tabIndex == '1') {
        exportExcelPrimary();
    } else if (tabIndex == '3') {
        exportExcelExceed();
    } else if (tabIndex == '5') {
        exportExcelRate();
    }
}

var loadIndex = void 0;

function showLoad() {
    loadIndex = layer.msg('请稍后...', {icon: 16, shade: [0.5, '#f5f5f5'], scrollbar: false, offset: 'auto', time: 30000});
}

function closeLoad() {
    layer.close(loadIndex);
}