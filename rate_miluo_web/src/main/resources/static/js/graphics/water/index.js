var tabInitFlag = {
    1: true,
    2: false,
    3: false,
};
var selectTab = 1;
var selectTabArr = {
    1: 'minuteForm',
    2: 'hourForm',
    3: 'dayForm',
};

var waterPol = {
    "sw": "水温(℃)",
    "ddl": "电导率(μS/cm)",
    "codmn": "CODMn(≤6[mg/L])",
    "pb": "铅",
    "rjy": "溶解氧(≥5[mg/L])",
    "ph": "ph",
    "shen": "砷(mg/L)",
    "nh3N": "氨氮(≤1[mg/L])",
    "comprehensiveToxicity": "综合毒性(%)",
    "tp": "总磷(≤0.2[mg/L])",
    "cd": "镉(mg/L)",
    "kmn": "高锰酸盐指数",
    "temp": "室温(℃)",
    "humi": "湿度(%)",
    "yls": "叶绿素(ug/L)",
    "llz": "蓝绿藻(Kcells/mL)"
};
var laydate;
$(function () {
    $.siteTree.build(false)
    $.siteTree.tabChangeEvent = function (id) {
        // console.log(id);
    };
    $.siteTree.treeSingleLoadedEvent = function (e, data) {
        data.instance.select_node(e.target.firstChild.childNodes[0]);
    }
    $.siteTree.treeLoadedEvent = function () {
        $.siteTree.hideTab(["-2","-3","-4"]);
        initMinutePage();
    };
    $.siteTree.treeClickEvent = function (e, data) {
        // console.log($.siteTree.siteCodes());
    };
    layui.use('element', function () {
        var element = layui.element;
        element.on('tab(waterTab)', function () {
            changeTabContentInputId($(this).attr("lay-id"));
        });
    });
    layui.use('laydate', function () {
        laydate = layui.laydate;
    });

});

function changeTabContentInputId(titleId) {
    showLoad();
    selectTab = titleId;
    if (!tabInitFlag[selectTab]) {
        tabInitFlag[selectTab] = true;
        switch (selectTab) {
            case '1':
                initMinutePage();
            case '2':
                initHourPage();
                break;
            case '3':
                initDayPage();
                break;
        }
    }else{
        closeLoad();
    }
}

function initMinutePage() {
    $.ajax({
        type: 'get',
        url: '/graphic/water/minute',
        dataType: 'html',
        success: function success(html) {
            $("#minuteChartContent").html(html);
            initMinuteContent();
        }
    });
}

function initHourPage() {
    $.ajax({
        type: 'get',
        url: '/graphic/water/hour',
        dataType: 'html',
        success: function success(html) {
            $("#hourChartContent").html(html);
            // initSiteInput(undefined);
            // getSiteTreeData(initHourContent);
            initHourContent();
            // initPol();
            // initHourContent();
        }
    });
}
function initDayPage() {
    $.ajax({
        type: 'get',
        url: '/graphic/water/day',
        dataType: 'html',
        success: function success(html) {
            $("#dayChartContent").html(html);
            // initSiteInput(264);
            // initSiteInput(undefined);
            // getSiteTreeData(initDayContent);
            initDayContent();
            // initDayContent();
        }
    });
}
function refreshObj(selectTab) {
    showLoad();
    switch (selectTab) {
        case '1':
            try {
                initHourPage();
            } catch (e) {
                console.err(selectTab + '刷新失败！');
            }
            break;
        case '2':
            try {
                initDayPage();
            } catch (e) {
                console.err(selectTab + '刷新失败！');
            }
            break;
    }
};

function initDateInput(o) {
    if (laydate) {
        laydate.render(o);
    } else {
        setTimeout(function () {
            initDateInput(o);
        }, 10);
    }
}

var loadIndex = void 0;
function showLoad() {
    loadIndex = layer.msg('请稍后...', { icon: 16, shade: [0.5, '#f5f5f5'], scrollbar: false, offset: 'auto', time: 30000 });
}

function closeLoad() {
    layer.close(loadIndex);
}
function showError() {
    layer.msg('操作失败，请重新尝试！', { time: 1000, offset: 'auto' });
}