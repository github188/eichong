/**
 * Created by Aaron on 2015/4/7.
 */
(function () {
    /**
     * 获取省份列表
     */
    Ajax.custom({
        url: config.IProvinceList
    }, function (res) {
        if (!res || res.code != 'OK')return;
        $('#province').html(template.render('province-tmpl', {list: res.data || []}));
    });

    /**
     * 省份选择
     */
    $('#province').change(function () {
        getCity($(this).val());
    });

    /**
     * 根据省份筛选城市
     */
    function getCity(provinceCode) {
        if (!provinceCode) {
            $('#city').empty();
            return
        }
        Ajax.custom({
            url: config.ICityList + '?cityProvcode=' + provinceCode
        }, function (res) {
            $('#city').html(template.render('city-tmpl', {list: res.data || []}));
        });
    }

    /**
     * 获取城市字母排序列表
     */
    var pageRequest = function(){
        Ajax.pageRequest({
            url: config.ICityList,
            data: {
                hasLetter: true,
                cityName: encodeURIComponent($('#searchKey').val())
            }
        });
    };
    pageRequest();

    $('#searchKey').keyup(function(){
        pageRequest();
    });

    $('#searchKey').focusout(pageRequest);


    /**
     * 选择城市之后
     */
    $('#city').change(function () {
        if (!$('#city option:selected').val()) {
            alert('请选择城市!');
            return;
        }
        selectCity($('#city option:selected').text());
    });

    $('#data-list').on('click', 'li a', function (e) {
        e.preventDefault();
        selectCity($(this).text());
    });

    /**
     * 选中城市后操作
     * @param city
     */
    function selectCity(city) {
        Cookie.set(Cookie.CCITY, city, 1);
        location.href = config.PIndex;
    }
})();