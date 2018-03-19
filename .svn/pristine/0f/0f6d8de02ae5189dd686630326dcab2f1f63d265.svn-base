/**
 * Created by Administrator on 2017/12/11 0011.
 */
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        divideListSearch();
    }
});
//去加载表格的数据
function divideListSearch() {
    initTable("divideListForm", "divideListPage", doChargingOrderForBatchUrl, divideListCallback);
}

//表格数据
function divideListCallback(req) {
    layer.closeAll();
    if(req.success){
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: '操作成功',
            time: 0,
            btn: ["确定"],
            btn1: function(index, layero) {
                layer.closeAll();
            },
            cancel: function(index, layero) {
                layer.closeAll();
            }
        });
    }
}