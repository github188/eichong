/**
 * Created by Aaron on 2015/3/25.
 */
(function(){

    //模板帮助方法，根据键值匹配显示内容
    template.helper('$mateArray', function (content, type) {
    		var array = new Array();
            switch (type) {
                case 1:
                	array = {
                		'0' : '空闲中',
                		'3' : '预约中',
                		'6' : '充电中',
                		'9' : '停用'
                	};
                    break;
                case 2:
                	array = {
                		'0' : '男',
                		'1' : '女'
            		};
                    break;
                case 3:
                	array = {
                		'1' : '待支付',
                		'2' : '支付成功',
            		};
                    break;
                case 4:
                	array = {
                		'1' : '待安装',
                		'2' : '安装完成',
            		};
                    break;
                case 5:
                	array = {
                		'1' : '普通用户',
                		'2' : '商家用户',
            		};
                    break;
                case 6:
                	array = {
                		'1' : '充电消费',
                		'2' : '预约消费',
                		'3' : '购物消费',
                		'4' : '充值',
                		'5' : '收益'
            		};
                    break;
                case 7:
                	array = {
                		'0' : '未知',
                		'7' : '国标',
                		'19' : '美标',
                		'20' : '欧标'
                		
            		};
                    break;
                case 8:
                	array = {
                		'1' : '支付',
                		'2' : '确认完成'
            		};
                    break;
                case 9:
                	array = {
                		'10' : '普通',
                		'15' : '智能'
            		};
                    break;
                case 10:
                	array = {
                		'0' : '未知',
                		'5' : '直流',
                		'14' : '交流'
            		};
                    break;
                case 11:
                	array = {
                		'3' : '电动汽车充电设备',
                		'4' : '电动自行车充电设备'
            		};
                    break;
                case 12:
                	array = {
                		'6' : '3.5KW',
                		'15' : '7KW',
                		'16' : '20KW',
                		'17' : '50KW',
                		'18' : '75KW',
                		'43' : '120KW',
                		'44' : '10KW'
            		};
                    break;
                case 13:
                	array = {
                		'1' : '国家补贴',
                		'2' : '免税置购'
            		};
                    break;
                default:
                	array = !!type ? type : array;
                    break;
            }
            if (isNaN(content) || content == null) {
                return content;
            }
            return array[content];
    });
    
    //模板帮助方法，时间格式转换
    template.helper('$convertDate', function (content, type) {
    	 var pattern = "yyyy-MM-dd hh:mm:ss";
         switch (type) {
             case 1:
                 pattern = "yyyy-MM-dd";
                 break;
             default:
                 pattern = !!type ? type : pattern;
                 break;
         }
         if (isNaN(content) && content != null) {
        	 var date = new Date(content);
             return date.format(pattern);
         }
         return content;
    });
    
    //模板帮助方法，根据序列值添加属性
    template.helper('$attrDecide', function (i, v, attrCase, attrThen) {
        if (i == v) {
            return attrCase || '';
        }
        return attrThen || '';
    });
    
})();