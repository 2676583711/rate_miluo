let prefix = "/monitor/video"
$(function () {
    load();
});

// 刷新
function reFresh() {
    window.location.href = prefix;
}
// 查询
function reLoad() {
    var opt = {
        query : {
            name : $("#name").val().trim()
        }
    };
    $('#exampleTable').bootstrapTable("refresh", opt);
}

// bootstrap table 事件都是以.bs.table为后缀作为标识的
// $('#exampleTable').on('load-success.bs.table', function (e, data) {
//     if (data.total && !data.rows.length) {
//         $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
//     }
// });

// $('#exampleTable').on('load-success.bs.table', function (e, data) {
//     if (data.total && !data.rows.length) {
//         $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
//     }
// });

function load(ids) {
    $('#exampleTable').bootstrapTable('destroy').bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                // showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        name: $("#name").val(),
                        ids: ids ? ids.join() : ''
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    }, {
                        title : '序号',
                        align : "center",
                        width : '50',
                        formatter : function(value, row, index) {
                            return index + 1;
                        }
                    }, {
                        field: 'siteName',
                        title: '站点名称'
                    }, {
                        field: 'vidiconName',
                        title: '设备名称'
                    }, {
                        field: 'seriesNumber',
                        title: '设备序列号'
                    }, {
                        field: 'supplier',
                        title: '设备供应商'
                    }, {
                        field: 'vidiconLocation',
                        title: '设备位置',
                        formatter: function (value) {
                            if (value == '1') {
                                return '监测点位置';
                            } else if (value == '2') {
                                return '水排放口';
                            } else if (value == '3') {
                                return '进水口';
                            } else if (value == '4') {
                                return '气排放口';
                            } else if (value == '5') {
                                return '站房内';
                            } else if (value == '6') {
                                return '站房外';
                            } else if (value == '7') {
                                return '生产车间';
                            } else {
                                return '-';
                            }
                        }
                    }, {
                        field: 'ownerName',
                        title: '设备所有人'
                    }, {
                        field: 'defaultUrl',
                        title: '默认播放地址'
                    }, {
                        title: '播放方式',
                        field: '',
                        align: 'center',
                        width: 200,
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-info btn-small" href="#" mce_href="#" onclick="showPlay(\''
                                + row.id
                                + '\')">直播地址</a> ';
                            var r = '<a class="btn btn-info btn-small" href="#" mce_href="#" onclick="showMonitor(\''
                                + row.id
                                + '\')">监控地址</a> ';
                            return e + r;
                        }
                    }, {
                        title: '操作',
                        field: '',
                        align: 'center',
                        width: 100,
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-warning btn-sm" href="#" title="编辑" mce_href="#" onclick="editVideo(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var r = '<a class="btn btn-danger btn-sm" href="#" title="删除" mce_href="#" onclick="removeVideo(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';

                            return e + r;
                        }
                    }]
            });
}

// 直播地址
function showPlay(id) {
    openVideoPage(id, 1);
}

// 监控地址
function showMonitor(id) {
    openVideoPage(id, 2);
}

function openVideoPage(id, type) {
    var page = layer.open({
        type : 2,
        title : '视频',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '100%', '100%' ],
        content : prefix + '/openVideoPage/' + type + "?id=" + id // iframe的url
    });
}

// 新增
function add() {
    var page = layer.open({
        type : 2,
        title : '增加',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '100%', '100%' ],
        content : prefix + '/add' // iframe的url
    });
}

function editVideo(id) {
    var page = layer.open({
        type : 2,
        title : '编辑',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '100%', '100%' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}

function removeVideo(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: "/monitor/video/remove",
            type: "post",
            data: {
                'id': id
            },
            beforeSend: function (request) {
                index = layer.load();
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.close(index);
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}
//下载播放器
function downHikPlayer() {
    	 location.href = "http://218.75.181.68:8306/hikPlayer/VSPlayer.exe";
}