/**
 * Created by Kael on 2015/3/20.
 */
(function () {

    /**
     * 一键升级
     */
    $('#center_update').click(function () {
        Ajax.custom({
            url: config.IUpdateMyCenter,
            data: {userId: UserService.getUserId()},
            type: 'POST'
        }, function (res) {
            if(!res||res.code != 'OK')return;
            alert("提交成功,审核中...");
        });
    });

})();