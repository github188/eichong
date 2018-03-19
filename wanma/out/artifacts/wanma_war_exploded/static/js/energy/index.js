/**
 * Created by Aaron on 2015/3/24.
 */
$(document).ready(function () {
	//智能排序
     var  priceSort=1;//价格 1-默认 2-按最优排序
     var  soldQuantity=1;//销售量排序 1-默认 2-按最优排序
    /**
     * 新品,折扣区
     */
    $('#new-product,#discount-product').on('click', function (e) {
        e.preventDefault();
        location.href = config.PEnergyProductArea + '?type=' + ($(this).is($('#discount-product')) ? '2' : '1');
    });
    /**
     * 热门推荐
     */
    config.pageRequest = function(){
        Ajax.pageRequest({
            url: config.IGetHotRecommendDetail,
            data: {
                pageSize: config.pageSize,
                begin: config.begin,
                priceSort:priceSort,
                soldQuantity:soldQuantity
            }
        });
       // $("").removeClass("carTypeShow").addClass("carTypeHide");
    };
    config.pageRequest();
    /**
     * 智能排序
     */
    $(".condition .conditionDetail").click(function(){
        $(this).addClass("cur").siblings().removeClass("cur");
        
        if($(this).is("a.rel.conditionDetail.cur")){//充电模式
        	$(this).children("ul").removeClass("carTypeHide").addClass("carTypeShow");
        }else if($(this).is("a.cur.conditionDetail.noLimit")){//不限
        	priceSort=1;
        	soldQuantity=1;
        	 
        	$(this).siblings(".rel").children("ul").removeClass("carTypeShow").addClass("carTypeHide");
        	config.pageRequest();
        }else if($(this).is("a.conditionDetail.priceSort.cur")){//价格
           	priceSort=2;
        	soldQuantity=1;
        	 
        	$(this).siblings(".rel").children("ul").removeClass("carTypeShow").addClass("carTypeHide");
        	config.pageRequest();
        }else if($(this).is("a.conditionDetail.soldQuantity.cur")){//销量
           	priceSort=1;
        	soldQuantity=2;
        	 
        	$(this).siblings(".rel").children("ul").removeClass("carTypeShow").addClass("carTypeHide");
        	config.pageRequest();
        }
         
       
    });
    
    /**
     * 充电模式下拉框
     */
    $(".conditionDetailList").click(function(){ 
    	$(this).parent("ul").removeClass("carTypeShow").addClass("carTypeHide");
    	$(this).parent("ul").siblings().first("span").text($(this).text());
    	config.pageRequest();
    	
    });
    
    /**
     * 导航菜单，热门推荐-电动汽车-电动自行车-充电设备-配件
     */
    $(".menu_list li").click(function(){
    	 $(this).addClass("on").siblings().removeClass("on");
    	if($(this).is("li.hot.on")){
    		 config.pageRequest();
    	}else{
    		//location.href = config.Pbuilding;
    		alert("我们正在紧张的规划二期，产品暂时未上架");
    	}
       
    });
    /**
     * 详情查看
     */
    $('#data-list').on('click','li',function(){
        location.href = config.PEnergyProductDetail + '?id=' + $(this).attr('data-id')+'&sm='+ $(this).attr('stock-amount');
    });
});