function getRootPath() {   
	 var pathName = window.location.pathname.substring(1);   
	 var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));   
	 return window.location.protocol + '//' + window.location.host + '/'+ webName + '/';   
} 

//采用正则表达式获取地址栏参数
function getUrlParam(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}


//扩展Date
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
//字符串转时间
String.prototype.toDate = function() { 
	var temp = this.toString(); 
	temp = temp.replace(/-/g, "/"); 
	return new Date(Date.parse(temp)); 
} 
//字符串转时间parse方法
function parseDate(dateStr){
	return dateStr.toDate();
}

