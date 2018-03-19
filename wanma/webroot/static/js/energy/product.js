/**
 * Created by Aaron on 2015/3/24.
 */
(function () {
    var type = location.href.getQueryValue('type');
    if (!type) return;
    $('.title').text(type == '1' ? '新品区列表' : '折扣区列表');

    /**
     * 获取打折,新品列表
     */
    config.pageRequest = function(){
        Ajax.pageRequest({
            url: config.IEnergyNewProductAndDiscount,
            data: {
                type: type,
                pageSize: config.pageSize,
                begin: config.begin
            }
        });
    };
    config.pageRequest();

    /**
     * 详情查看
     */
    $('#data-list').on('click','li',function(){
        location.href = config.PEnergyProductDetail + '?id=' + $(this).attr('data-id')+'&sm='+ $(this).attr('stock-amount');
    });

})();