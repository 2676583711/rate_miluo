var tabInitFlag = {
    1: true,
    2: false,
    3: false,
};
var selectTab = 1;
var selectTabArr = {
    1: 'hourForm',
    2: 'dayForm',
    3: 'changeForm',
};
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
        $.siteTree.hideTab(["-1","-2","-3"]);
        initHourPage();
    };
    $.siteTree.treeClickEvent = function (e, data) {
        // console.log($.siteTree.siteCodes());
    };
    layui.use('element', function () {
        var element = layui.element;
        element.on('tab(polluteWaterTab)', function () {
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
                initHourPage();
                break;
            case '2':
                initDayPage();
                break;
            case '3':
                initChangePage();
                break;
        }
    }else{
        closeLoad();
    }
}

function initHourPage() {
    $.ajax({
        type: 'get',
        url: '/graphic/polluteWater/hour',
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
        url: '/graphic/polluteWater/day',
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
function initChangePage() {
    $.ajax({
        type: 'get',
        url: '/graphic/polluteWater/change',
        dataType: 'html',
        success: function success(html) {
            $("#changeChartContent").html(html);
            // initSiteInput(undefined);
            // getSiteTreeData(initChangeContent);
            initChangeContent();
            // initChangeContent();
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
        case '3':
            try {
                initChangePage();
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