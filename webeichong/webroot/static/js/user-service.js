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
        $.ajax({
	         type: 'POST',
	         url: basepath+"/web/user/logout.do" ,
	         dataType: "json",
	         success: function(data){
	        	 if(data.code=="OK"){
	        		 Cookie.set(Cookie.ACCOUNT,null);
	        		 Cookie.set(Cookie.IMG,null);
	        		 window.location.href=basepath+"/index.html";
	        	 }else{
	        		 showInfo(data.msg,"error",3000);
	        	 }
	         },
	         error: function(XMLHttpRequest, textStatus, errorThrown) {
	         }
        });   
    },
    getUserId: function () {
    	if(UserService.getUser()){
    		return UserService.getUser().userId;
    	}else{
    		return null;
    	}
    },
    getUserIMG: function () {
    	if(UserService.getUser()){
    		return JSON3.parse(Cookie.get(Cookie.IMG)).userImage;
    	}else{
    		return null;
    	}
    },
    login: function (form) {
        var _this = this;
        var data = formJson(form);
        if(!Cookie.get(Cookie.REMEMBER)){
            //MD5加密
            data.userPassword = $.md5(data.userPassword);
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
            if ($('input[name="rememberMe"]:checked')) {
                var unameApwd = {};
                unameApwd.uname = data.userAccount || '';
                unameApwd.pwd = data.userPassword || '';
                Cookie.set(Cookie.REMEMBER, JSON3.stringify(unameApwd));
            }
            //保存用户信息
            Cookie.set(Cookie.ACCOUNT, JSON3.stringify(res.data || {}));
            location.href = config.PIndex;
        });
    },
    login2: function (user) {
    	var cookUser={
    			normName:user.normName,
    			userAccount:user.userAccount,
    			userId:user.userId,
    			userPassword:user.userPassword
    	};
    	var userImg={
    			userImage:user.userImage?user.userImage:(basepath+"/static/images/user/2.png")
    	};
        if ($('input[name="rememberMe"]:checked').length>0) {
        	var exp = new Date(); 
        	exp.setTime(exp.getTime() + 365*24*60*60*1000);
        	Cookie.set(Cookie.ACCOUNT, JSON3.stringify(cookUser || {}),exp.toGMTString());
        	Cookie.set(Cookie.IMG, JSON3.stringify(userImg || {}),exp.toGMTString());
        }else{
        	Cookie.set(Cookie.ACCOUNT, JSON3.stringify(cookUser || {}));
        	Cookie.set(Cookie.IMG, JSON3.stringify(userImg || {}));
        }
    },
    register: function (form) {
        var _this = this;
        var data = formJson(form);
        //MD5加密
        data.userPassword = $.md5(data.userPassword || '');
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
        data.userPassword = $.md5(data.userPassword);
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
    },
    showUser:function(){
    	var user=UserService.getUser();
    	if(user){
			$(".offline").hide();
			$(".online").show();
			$("#userAccount").html(user.normName?user.normName:user.userAccount);
			$(".userImg").attr("src",UserService.getUserIMG()).show();
			$(".userName").html(user.normName?user.normName:user.userAccount);
		}else{
			$(".offline").show();
			$(".online").hide();
		}
    },
    saveUser:function(user){
    	Cookie.set(Cookie.ACCOUNT, JSON3.stringify(user || {}));
    }
};