var tabInitFlag = {
    1: true,
    2: false,
    3: false,
    4: false,
    5: false
};
var selectTab = 1;
var selectTabArr = {
    1: 'hourForm',
    2: 'dayForm',
    3: 'windForm',
    4: 'changeForm',
    5: 'pmForm'
};
var laydate;
$(function () {
    // setTimeout(function () {
    $.siteTree.build(false)
    $.siteTree.tabChangeEvent = function (id) {
        // console.log(id);
    };
    $.siteTree.treeSingleLoadedEvent = function (e, data) {
        data.instance.select_node(e.target.firstChild.childNodes[0]);
    }
    $.siteTree.treeLoadedEvent = function () {
        $.siteTree.hideTab(["-1","-3","-4"]);
        initHourPage();
        // $.siteTree.trees["-2"].select_node($.siteTree.$trees["-2"]);
    };
    $.siteTree.treeClickEvent = function (e, data) {
        // console.log($.siteTree.siteCodes());
    };
    layui.use('element', function () {
        var element = layui.element;
        element.on('tab(airTab)', function () {
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
    if (selectTab == '3') {
        $('#wind-left-menu').css({ "left": "-300px" });
        $('.toggler').click();
    } else {
        $('#wind-left-menu').attr('data-status', "closed");
        $('#wind-left-menu').css({ "left": "-550px" });
    }
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
                initWindPage();
                break;
            case '4':
                initChangePage();
                break;
            case '5':
                initPmPage();
                break;
        }
    }else{
        closeLoad();
    }
}

function initHourPage() {
    $.ajax({
        type: 'get',
        url: '/graphic/air/hour',
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
        url: '/graphic/air/day',
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
function initWindPage() {
    $.ajax({
        type: 'get',
        url: '/graphic/air/wind',
        dataType: 'html',
        success: function success(html) {
            $("#windChartContent").html(html);
            // initSiteInput(undefined);
            // getSiteTreeData(initWindContent, 'micro');
            initWindContent();
            // initWindContent();
        }
    });
}
function initChangePage() {
    $.ajax({
        type: 'get',
        url: '/graphic/air/change',
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
function initPmPage() {
    $.ajax({
        type: 'get',
        url: '/graphic/air/pm',
        dataType: 'html',
        success: function success(html) {
            $("#pmChartContent").html(html);
            // initSiteInput(undefined);
            // getSiteTreeData(initPmContent);
            initPmContent();
            // initPmContent();
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
                initWindPage();
            } catch (e) {
                console.err(selectTab + '刷新失败！');
            }
            break;
        case '4':
            try {
                initChangePage();
            } catch (e) {
                console.err(selectTab + '刷新失败！');
            }
            break;
        case '5':
            try {
                initPmPage();
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