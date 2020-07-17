var num1;
var num2;
var num3;
var num4;

(function () {
    var data = {};
    var treeData;
    var el = "#siteTree";
    var $trees = [];
    var $tabs = [];
    var $tabsNum = [];
    var trees = [];
    var allTab = {};
    var tabId;
    var $treeEl = {};
    var siteCodes = {
    };

    var $this = {
        build: build,
        tabChangeEvent: null,
        treeSingleLoadedEvent: null,
        treeLoadedEvent: null,
        treeClickEvent: null,
        $trees: $trees,
        trees: trees,
        tab: getTabId,
        hideTab: hideTab,
        showTab: showTab,
        setUrlData: setUrlData,
        siteCodes: getSiteCodes,
        allSiteCodes: getAllSiteCodes,
        siteIds: getSiteCodes,//同siteCodes
        allSiteIds: getAllSiteCodes,//同llSiteCodes
    };

    var option = {
        core:{"multiple" : false,}
    }
    var single = true;
    var many = {
        core:{},
        checkbox: {"keep_selected_style" : false},
        plugins: ["checkbox"]
    }

    function tabResize(){
        var ww = window.innerHeight - 20;
        $(el).css({
            'height': ww + "px",
        });
    }

    function buildTab(){
        $(el).removeClass("site-tree-content");
        $(el).addClass("site-tree-content");
        $(el).html("");
        $(el).html('<div class="layui-tab layui-tab-brief" lay-filter="siteTab">'+
                        '<ul class="layui-tab-title">'+
                        '</ul>'+
                        '<div class="layui-tab-content">'+
                        '</div>'+
                     '</div>');
        var tabLi = '';
        var tabContent = '';
        $.each(treeData, function (key, item) {
            if ("水质站"==key){
                // num1=item.children.length;
                return true;
            }
            if ("空气站"==key){
                num2=item.children.length;
            }
            if ("涉气污染源"==key){
                // num3=item.children.length;
                return true;
            }
            if ("涉水污染源"==key){
                // num4=item.children.length;
                return true;
            }
            allTab[key] = item.id;
            $tabsNum.push(item.id);
            tabLi += '<li lay-id="'+ item.id +'" >'+ key +"("+item.children.length+")"+'</li>';
            tabContent += '<div id="content'+ item.id +'" class="layui-tab-item"></div>';
        });
        $(el + " ul.layui-tab-title").html(tabLi);
        $(el +" div.layui-tab-content").html(tabContent);
        $.each($(el + " ul.layui-tab-title>li"), function (i, item) {
            $tabs[$tabsNum[i]] = $(this);
        });
        $(el +" ul.layui-tab-title>li:first-child").addClass("layui-this");
        tabId = $(el +" ul.layui-tab-title>li:first-child").attr("lay-id");
        $(el +" div.layui-tab-content>div:first-child").addClass("layui-show");
        useEle();
    }


    function useEle(){
        layui.use('element', function () {
            var element = layui.element;
            element.on('tab(siteTab)', function () {
                tabId = $(this).attr("lay-id");
                $this.tabChangeEvent && $this.tabChangeEvent($(this).attr("lay-id"));
            });
        });
    }

    function buildTree(){
        var types = Object.keys(treeData);
        var initCount = 0;
        for(var typeIndex in types){
            if(!treeData.hasOwnProperty(types[typeIndex])){
                continue;
            }
            var type = types[typeIndex];
            var item = treeData[type];
            (single?option:many).core["data"] = item;
            $('#content'+ item.id).jstree((single?option:many));
            var currTree = $('#content'+ item.id).jstree(true);
            $treeEl[allTab[type]] = currTree;
            $trees[type] = $('#content'+ item.id);
            trees[type] = currTree;
            $trees[type].unbind("loaded.jstree");
            $trees[type].on("loaded.jstree", function(e, data){
                initCount++;
                $this.treeSingleLoadedEvent && $this.treeSingleLoadedEvent(e, data);
                if(initCount == (types.length)){
                    types.length = 0;
                    treeData = undefined;
                    $this.treeLoadedEvent && $this.treeLoadedEvent();
                }
            });
            $trees[type].unbind("changed.jstree");
            $trees[type].on("changed.jstree", function (e, data) {
                var selCodes = $treeEl[tabId].get_bottom_selected();
                siteCodes[tabId] = findSiteCode(selCodes);
                $this.treeClickEvent && $this.treeClickEvent(e, data);
            });

        }
        tabResize();
    }

    function findSiteCode(selCodes){
        if(selCodes && selCodes.length>0){
            $.each(selCodes, function (idx, itm) {
                var r = parseInt(itm);
                if(!isNaN(r)){
                    if(r<0){
                        selCodes.splice(idx,1)
                    }
                }
            });
        }
        return selCodes;
    }

    function build(){
        var len = arguments.length;
        for(var i=0;i<len;i++){
            if(typeof arguments[i] === "string"){
                if(arguments[i]==="" || arguments[i].length === 0 || $(arguments[i]).length === 0){
                    throw new Error("元素不存在");
                }
                el = arguments[i];
            } else if (typeof arguments[i] === "boolean"){
                single = arguments[i];
            }
        }
        $.ajax({
            url: "/site/tree/item",
            type: "get",
            data: data,
            success: function (res) {
                if(res){

                    treeData = res.data;
                    buildTab();
                    // $("ul.layui-tab-title span").html("");
                    buildTree();
                }
            }
        });
    }

    function getSiteCodes() {
        var selCodes = $treeEl[tabId].get_bottom_selected();
        siteCodes[tabId] = findSiteCode(selCodes);
        return siteCodes[tabId];
    }
    function getAllSiteCodes(type) {
        var selCodesAll=[];
        $.each($treeEl, function(index, treeItem){
            var curr = treeItem.get_bottom_selected();
            curr = findSiteCode(curr);
            if(type){
                if(selCodesAll===[])
                    selCodesAll={};
                selCodesAll[index.substr(1)]=curr;
            }else{
                curr.map(function (e) {
                    selCodesAll.push(e)
                })
            }
        });

        return selCodesAll;
    }
    function getTabId() {
        return tabId.substr(1);
    }
    function setUrlData(aData){
        data = aData;
    }
    function hideTab(id){
        if(id instanceof Array){
            $.each(id, function (index, item) {
                if(parseInt(item)>=0){
                    item = ("-"+item);
                }else{
                    item+=""
                }
                var $tabEl = $("li[lay-id='"+item+"']");
                $tabEl.removeClass("layui-this");
                $tabEl.css({display:"none"});
                $(el +" div.layui-tab-content>div.layui-show").removeClass("layui-show");
            });
            var showEl = $(el +" ul.layui-tab-title>li:visible");
            if(showEl.length>0){
                showTab($(showEl[0]).attr("lay-id"));
            }else if(showEl.length === 0){
                showTab(showEl.attr("lay-id"));
            }
        }else{
            if(parseInt(id)>=0){
                id = ("-"+id);
            }else{
                id+=""
            }
            if (id == tabId){
                var $tabEl = $(el +" ul.layui-tab-title>li.layui-this");
                $tabEl.removeClass("layui-this");
                $tabEl.css({display:"none"});
                $(el +" div.layui-tab-content>div.layui-show").removeClass("layui-show");
                var pre = $tabEl.prev();
                if(pre.length>0){
                    showTab($tabEl.prev().attr("lay-id"));
                }else{
                    showTab($tabEl.next().attr("lay-id"));
                }
            }else{
                var $tabEl = $("li[lay-id='"+id+"']:visible");
                if($tabEl.length > 0){
                    $tabEl.css({display:"none"});
                    var preEl = $tabEl.prev();
                    if(preEl.length>0){
                        showTab(preEl.prev().attr("lay-id"));
                    }else{
                        showTab(preEl.next().attr("lay-id"));
                    }
                }
            }
        }
    }
    function showTab(id){
        if(parseInt(id)>=0){
            id = ("-"+id);
        }else{
            id+=""
        }
        var $el = $("li[lay-id='"+id+"']").css({display:"inline-block"});
        $el.click();
    }

    $.siteTree = $this;
})();