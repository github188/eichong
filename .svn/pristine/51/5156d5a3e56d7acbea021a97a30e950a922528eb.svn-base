(function () {
    /**
     * 获取设备维修类型
     */
    Ajax.pageRequest({url: config.IDictionary + '?' + dictionary.paraType.param + dictionary.paraType.repair});

    /**
     * checkbox
     */
    $('#data-list').on('click', 'button', function () {
        $(this).siblings().removeClass('current');
        $(this).addClass('current');
        $('textarea[name="eqreContent"]').val($(this).text());
    });

    /**
     * 提交设备报修信息
     */
    $('.submit-btn').click(function (e) {
        e.preventDefault();
        var v = $('textarea[name="eqreContent"]').val();
        if (!v || v.isEmpty()) {
            alert('信息不能为空!');
            return;
        }
        var data = {
            eqreUserid: UserService.getUserId(),
            eqreWarrantytypeid: $('#data-list button.current').attr('data-type'),
            eqreContent: v
        };
        Ajax.custom({
            url: config.IRepair, data: data
        }, function (res) {
            if (!res || res.code !== 'OK') {
                alert(res.msg);
                return;
            }
            $('textarea[name="eqreContent"]').val('');
            alert('提交成功!');
        })
    });

})();