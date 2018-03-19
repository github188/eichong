/**
 * Created by Han on 2015/2/7.
 * 用户信息,用户操作
 */

var UserService = {
    callback: undefined,
    getUser: function () {
        return JSON3.parse(Cookie.get(Cookie.ACCOUNT));
    },
    removeUser: function () {
        Cookie.remove(Cookie.ACCOUNT);
    },
    getUserId: function () {
        return $('#userId').val();
    },
    login: function (form) {
        var _this = this;
        var data = formJson(form);
        if(!Cookie.get(Cookie.REMEMBER)){
            //MD5加密
            data.usInPassword = $.md5(data.usInPassword);
        }
        Ajax.submitForm({
            url: config.ILogin,
            data: data
        }, function (res) {
            if (res.code !== 'OK') {
                alert(res.msg);
                return;
            }

            //记住密码
            Cookie.remove(Cookie.REMEMBER);
            if ($('input[name="rememberMe"]').prop('checked')) {
                var unameApwd = {};
                unameApwd.uname = data.usinPhone || '';
                unameApwd.pwd = data.usInPassword || '';
                Cookie.set(Cookie.REMEMBER, JSON3.stringify(unameApwd));
            }

            //保存用户信息
            Cookie.set(Cookie.ACCOUNT, JSON3.stringify(res.data || {}));
            location.href = config.PIndex;
        });
    },
    register: function (form) {
        var _this = this;
        var data = formJson(form);
        //MD5加密
        data.usInPassword = $.md5(data.usinPassword || '');
        Ajax.submitForm({
            url: config.IRegist,
            data: data
        }, function (res) {
            if (res.code !== 'OK') {
                alert(res.msg);
                return;
            }
            //保存用户信息
            Cookie.set(Cookie.ACCOUNT, JSON3.stringify(res.data || {}));
            location.href = config.PModifyInfo;
        });
    },
    logout: function () {
        Ajax.custom({url: config.ILogout}, function (res) {
            if (res.code !== 'OK')return;
            location.href = config.PLogin;
        });
    },
    isRememberPwd: function () {
        return JSON3.parse(Cookie.get(Cookie.REMEMBER));
    },
    collect: function (id) {
        if (!id)return;
        var _this = this;
        Ajax.custom({
            url: config.ICollect,
            data: {
                favoProductid: id,
                favoUserid: _this.getUserId()
            }
        }, function (res) {
            if (typeof _this.callback == 'function') _this.callback();

        });
    },
    resetPwd: function (form) {
        var data = formJson(form);
        //MD5加密
        data.usInPassword = $.md5(data.usInPassword);
        Ajax.submitForm({
            url: config.IReset,
            data: data
        }, function (res) {
            if (res.code !== 'OK') {
                alert(res.msg);
                return
            }
            location.href = config.PLogin;
        });
    },
    /**
     * 获取用户当前城市 H5 support
     */
    getCity: function () {

        /**
         * case 1:浏览器定位,获取经纬度 (default)
         * case 2:百度api根据城市反编译获取经纬度(change)
         */
        var o = {
            currentCity: (Cookie.get(Cookie.CCITY) || '杭州'),
            coordinate: {
                longtitude: '', latitude: ''
            }
        };

        return o;
    }
};