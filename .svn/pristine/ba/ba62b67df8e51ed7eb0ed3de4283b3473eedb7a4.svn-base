let prefix = "/site/param/configuration";

function reFresh() {
    window.location.href = prefix + "/list";
}
function reLoad() {
    $("#exampleTable").bootstrapTable("refresh");
}

function load(siteId) {
    $('#exampleTable').bootstrapTable("destroy")
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/getListBySiteId",
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 20, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams : function(params) {
                    return {
                        // pageSize : params.limit,
                        // pageNumber : (params.offset / params.limit) + 1,
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        // limit : params.limit,
                        // offset : params.offset,
                        pluName : $('#pluName').val(),
                        siteId : siteId,
                    };
                },
                onPostBody : function() {
                    resetHeight();
                },
                onLoadSuccess: function(res) {
                },
                columns: [[{
                    title: '序号',
                    align: 'center',
                    valign: 'middle',
                    width: '50',
                    colspan: 1,
                    rowspan: 2,
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                }, {
                    title: '设备名称',
                    field: 'videoName',
                    align: 'center',
                    valign: 'middle',
                    colspan: 1,
                    rowspan: 2
                }, {
                    title: '污染物名称',
                    field: 'name',
                    align: 'center',
                    valign: 'middle',
                    colspan: 1,
                    rowspan: 2,
                    width: 100
                }, {
                    title: '上限值',
                    align: 'center',
                    valign : 'middle',
                    colspan : 3,
                    rowspan : 1
                }, {
                    title: '下限值',
                    align: 'center',
                    valign : 'middle',
                    colspan : 3,
                    rowspan : 1
                }, {
                    title: '单位',
                    field: 'unit',
                    align: 'center',
                    valign: 'middle',
                    colspan: 1,
                    rowspan: 2
                }, {
                    title: '是否报警',
                    field: 'unit',
                    align: 'center',
                    valign: 'middle',
                    colspan: 1,
                    rowspan: 2,
                    width: 60,
                    formatter: function (value, row, index) {
                        return value == 1 ? '是':'否'
                    }
                }, {
                    title : '操作',
                    field : 'id',
                    align : 'center',
                    valign : 'middle',
                    colspan : 1,
                    rowspan : 2,
                    formatter : function(value, row, index) {
                        var e = '<a class="btn btn-primary btn-sm '
                            + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                            + row.id
                            + '\')"><i class="fa fa-edit"></i></a> ';
                        var d = '<a class="btn btn-warning btn-sm '
                            + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                            + row.id
                            + '\')"><i class="fa fa-remove"></i></a> ';
                        var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="detail(\''
                            + row.id
                            + '\')"><i class="glyphicon glyphicon-zoom-in"></i></a> ';
                        return e + d + f;
                    }
                }],[{
                    title: '一级',
                    field: 'limitTop1',
                    align: 'center',
                    valign : 'middle'
                }, {
                    title: '二级',
                    field: 'limitTop2',
                    align: 'center',
                    valign : 'middle'
                }, {
                    title: '三级',
                    field: 'limitTop3',
                    align: 'center',
                    valign : 'middle'
                }, {
                    title: '一级',
                    field: 'limitBottom1',
                    align: 'center',
                    valign : 'middle'
                }, {
                    title: '二级',
                    field: 'limitBottom2',
                    align: 'center',
                    valign : 'middle'
                }, {
                    title: '三级',
                    field: 'limitBottom3',
                    align: 'center',
                    valign : 'middle'
                }]]
            });
}


//详情
function detail(id) {
    layer.open({
        type: 2,
        title: '站点详情',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['100%', '100%'],
        content: prefix + '/detail/' + id // iframe的url
    });
}

function add() {

    var page = layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '480px' ],
        content : prefix + '/addParam/' + $("#siteId").val() // iframe的url
    });
    layer.full(page);
}

function edit(id) {
    var page = layer.open({
        type : 2,
        title : '编辑',
        // shift : -1,
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '480px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
    layer.full(page);
}


