var tabInitFlag = {
    1: true,
    2: false,
    3: false,
    4: false,
};
var selectTab = 1;
var selectTabArr = {
    1: 'minuteForm',
    2: 'hourForm',
    3: 'dayForm',
    4: 'changeForm'
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
        $.siteTree.hideTab(["-1","-2","-4"]);
        initMinutePage();
    };
    $.siteTree.treeClickEvent = function (e, data) {
        // console.log($.siteTree.siteCodes());
    };
    layui.use('element', function () {
        var element = layui.element;
        element.on('tab(polluteTab)', function () {
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
            case '4':
                initChangePage();
                break;
        }
    }else{
        closeLoad();
    }
}

function initMinutePage() {
    $.ajax({
        type: 'get',
        url: '/graphic/pollute/minute',
        dataType: 'html',
        success: function success(html) {
            $("#minuteChartContent").html(html);
            // initSiteInput(undefined);
            // getSiteTreeData(initHourContent);
            initMinuteContent();
            // initPol();
            // initHourContent();
        }
    });
}

function initHourPage() {
    $.ajax({
        type: 'get',
        url: '/graphic/pollute/hour',
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
        url: '/graphic/pollute/day',
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
        url: '/graphic/pollute/change',
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
                initMinutePage();
            } catch (e) {
                console.err(selectTab + '刷新失败！');
            }
            break;
        case '2':
            try {
                initHourPage();
            } catch (e) {
                console.err(selectTab + '刷新失败！');
            }
            break;
        case '3':
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