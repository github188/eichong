/**
 * Created by Kael on 2015/3/20.
 */
$(document).ready(function () {

    /**
     * 手机号回填
     */
    try {
        $('#phone').val(UserService.getUser().userTel);
    } catch (e) {
    }

    /**
     * 手动触发表单提交
     */
    $('#submitBtn').click(function () {
        $('#update-info-form').trigger('submit')
    });

    /**
     * 表单提交
     */
    $('#update-info-form').on('valid.form', function (e, f) {
        var data = formJson('#update-info-form');
        //default user icon
        if(!data.usinHeadimage){
            data.usinHeadimage = config.defaultUserImg;
        }
        Ajax.submitForm({
            url: config.IUpdateUserInfo,
            data: $(f)
        }, function (res) {
            if (res.code != 'OK') {
                alert(res.msg);
                return;
            }
            location.href = config.PMyCenter;
        });
    });

    /**
     * 上传文件
     */
    new AjaxUpload($('#file'), {
        action: config.IUpdateUserInfo,
        data: {
            pkUserinfo: UserService.getUserId(),
            isUpload: true
        },
        onSubmit: function (file, e) {
            $('#loadimg').empty().append(createLoadImg());
        },
        onComplete: function (file, res) {
            if (!res || res.code !== 'OK') {
                alert('上传失败!');
                return;
            }
            var icon = $('<img />');
            icon.attr('src', config.imageServer + res.msg);
            icon.css({width: '100%', height: '100%'});
            $('#loadimg').empty().append(icon);
            $('input[name="usinHeadimage"]').val(res.msg);
        }
    });

    /**
     * loading
     * @returns {*|jQuery}
     */
    function createLoadImg() {
        return $('<img />')
            .attr('src', config.defaultLoadImg)
            .addClass('load-img')
            .css({
                position: 'absolute',
                left: '50%',
                top: '50%',
                marginLeft: '-8px',
                marginTop: '-8px'
            });
    }

    /**
     * 获取汽车品牌
     */
    Ajax.pageRequest({url: config.IDictionary + '?' + dictionary.paraType.param + dictionary.paraType.carCard}, function () {
        $('#data-list').trigger('change');
    });

    /**
     * 获取车型
     */
    $('#data-list').change(function () {
        if (!$(this).val())return;
        Ajax.pageRequest({
            url: config.ICarModel,
            renderFor: 'car-model-tmpl',
            renderEle: '#car-model',
            type:'POST',
            data:{
                carinfoBrandname:encodeURIComponent($('#data-list option:selected').text())
            }
        })
    });
});