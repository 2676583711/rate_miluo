function load(id) {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: "/index/eventList",
        // showRefresh : true,
        // showToggle : true,
        // showColumns : true,
        iconSize: 'outline',
        showColumns: true,
        toolbar: '#exampleTable',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        pageSize: 25, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        //queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        //$('#exampleTable').bootstrapTable('prepend', data);
        showHeader: true,//默认为true显示表头，设为false隐藏
        // showFooter: true,//这个会显示在底部多一行空白
        //singleSelect : false, // 设置为true将禁止多选
        //contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        //search : true, // 是否显示搜索框
        //showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求

            return {
                pageSize: params.limit, // 每页要显示的数据条数
                pageNumber: (params.offset / params.limit) + 1,
                id:$("#id").val(),
                pollutant: $("#pollutant").val(),
                time: $("#time").val()
            };
        },
        //(1)是一个回调函数，可以获取查询数据的总条数
        /*  onLoadSuccess: function (res) {
              // alert(res.total)
              warnNum = res.total;
              //改变#count的文本内容
              $("#count").text(res.total);
          },*/
        columns: [{
            title: '站点',
            field: 'siteName',
            align: 'center'
        }, {
            title: '时间',
            field: 'recordingTime',
            align: 'center'
        },{
            title: '因子',
            field: 'pollutant',
            align: 'center'
        },{
            title: '因子值',
            field: 'so2',
            align: 'center'
        }


            //     {
            //     title: '查看',
            //     field: 'id',
            //     width : '120px',
            //     align : 'center',
            //     formatter : function(value, row, index) {
            //         var d = '<a class="btn btn-success btn-sm" style="    width: 43px;\n' +
            //             '    padding-left: 3px;" href="#" title=""  mce_href="#" onclick="lookVideo(\''
            //             + row.siteId
            //             + '\')"><i class="glyphicon glyphicon-zoom-in">监控</i></a> ';
            //
            //         return d;
            //     }
            //
            // }
        ]
    });
}
//导出Excel
function exportExcel() {
    /* id:$("#id").val(),
                pollutant: $("#pollutant").val(),
                time: $("#time").val()*/
    layer.confirm('确定要导出当前记录？', {
        btn : [ '确定', '取消' ]
    }, function(index) {
        layer.close(index);
        var url = "";
        url += "/index/eventDetailExp?id=" + $("#id").val()
            + "&pollutant=" + $("#pollutant").val()
            +"&time="+$("#time").val();
        window.location.href = url
    });
}