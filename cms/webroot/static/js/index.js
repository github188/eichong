var relId = "";
var oldUrl = "";
var newUrl = "";
function loadpage(url) {
	$.ajaxSetup({
		cache : false
	});
	oldUrl = newUrl;
	newUrl = url;
	$("#menuMain").load(basePath + url);
}

function goBack() {
	loadpage(oldUrl);
}

function refreshCurrent() {
	loadpage(newUrl);
}

// 登录框
$(".loginName").mouseover(function() {
	$(".loginBlock").show();
})
$(".loginName").mouseleave(function() {
	$(".loginBlock").hide();
})

$("#logout").click(function() {
	window.location.href = basePath + logoutUrl;
});
// 全选和反选样式
$("body").on('click', ".selAll", function() {
	$(".selectedBox").prop("checked", $(this).is(':checked'));
})
//处理顶部导航==========================
$("body").on("click",".myMenu>li>a",function(){
	$(this).addClass("headMenu").parent().siblings().find('a').removeClass("headMenu");
})
// 处理左边菜单栏逻辑
$("body").on(
		"click",
		".mainList>li>h2",
		function() {
			var $ul = $('.mainList>li>ul');
			var ulLength = $ul.size();
			var check = $(this).siblings();
			if (ulLength > 0) {
				$(this).parent().siblings().find('h2').addClass('down')
						.removeClass('up').siblings().slideUp();
				$(this).addClass('up').removeClass('down').next("ul")
						.slideDown();
			}
		})
$("body").off('click', '.mainList>li>ul>li>a').on(
		'click',
		'.mainList>li>ul>li>a',
		function() {
			$(this).addClass('activeStyle').parent().siblings().find('a')
					.removeClass('activeStyle');
			$(this).parent().parent().parent().siblings().find('ul').find('li')
					.find('a').removeClass('activeStyle');
			$(this).parent().parent().parent().find('h2').addClass(
					"h2ActiveStyle");
			$(this).parent().parent().parent().siblings().find('h2')
					.removeClass("h2ActiveStyle");
			loadpage($(this).attr("target"));

		})
// 加载菜单部分
loadMenu("1nTba6eQSmDUaYAuhZR");

function loadMenu(menuId) {
	$.ajax({
		type : "get",
		url : basePath + menuChildrenUrl + "?menuId=" + menuId,
		// url:"static/js/json.json",
		async : true,
		dataType : "json",
		success : function(req) {
			if (req.status == 100) {
				var data = req.data;
				var secondMenuLength = data.children.length;
				var html = "";
				var firstUrl = data.children[0].children[0].target;
				for (var i = 0; i < secondMenuLength; i++) {
					if (i == 0) {
						firstUrl = data.children[0].children[0].target;
						html += '<li><h2 class="up" id="' + data.children[i].id
								+ '">' + data.children[i].name + '</h2><ul>';
					} else {
						html += '<li><h2 class="down" id="'
								+ data.children[i].id + '">'
								+ data.children[i].name + '</h2><ul>';
					}
					for (var j = 0; j < data.children[i].children.length; j++) {
						html += '<li><a target="'
								+ data.children[i].children[j].target
								+ '" id="' + data.children[i].children[j].id
								+ '">' + data.children[i].children[j].name
								+ '</a></li>';
					}
					html += '</ul></li>';
				}
				$("#mainList").html(html);
				$(".mainList>li:first-child>ul>li:first-child>a").addClass(
						'activeStyle');
				$(".mainList>li:first-child>h2:first-child").addClass(
						'h2ActiveStyle');
				// loadpage($(".mainList>li:first-child>ul>li:first-child>a").attr("target"));
				loadpage(firstUrl);
			}
		}
	});
}

function initTable(formId, pageId, url, callback, pager) {
	if (!pager) {
		pager = {
			pageNum : 1
		};
	}
	var data = $("#" + formId).serializeObject();
	data.pageNum = pager.pageNum;
	$.ajax({
		url : basePath + url,
		type : "get",
		dataType : 'json',
		data : data,
		success : function(datas) {
			if (typeof callback == "function") {
				if (datas.status == 100) {
					callback(datas);
					if (datas.pager) {
						initPage(datas.pager, formId, pageId, url, callback);
					}
				} else if (datas.status == 5002) {
					location.href = basePath + toLoginUrl;
				} else {
					layer.alert("系统出错", {
						offset : '100px'
					});
				}
			}
		}
	});
}

function initPage(pager, formId, pageId, url, callback) {
	var options = {
		containerClass : "pagination",
		currentPage : pager.pageNum,
		numberOfPages : 5,
		totalPages : pager.pageTotal,
		totalCount : pager.total,
		pageUrl : function(type, page) {
			return null;
		},
		onPageClicked : function(event, originalEvent, type, page) {
			initTable(formId, pageId, url, callback, {
				pageNum : page
			});
		}

	};
	$('#' + pageId).bootstrapPaginator(options);
}

function initSelect(url, selectIds) {
	$.ajax({
		type : "get",
		url : basePath + url,
		dataType : "json",
		success : function(req) {
			var obj = req.data;
			var options = "";
			for (var i = 0; i < obj.length; i++) {
				options += '<option type="' + obj[i].t + '" value="' + obj[i].k
						+ '">' + obj[i].v + '</option>';
			}
			if (selectIds.indexOf(",") > 0) {
				selectIds = selectIds.split(",");
			} else {
				selectIds = new Array(selectIds);
			}
			for (var i = 0; i < selectIds.length; i++) {
				$("#" + selectIds[i]).append(options);
			}

		}
	});
}

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

// 省市区三级联动
var pcaIdPrefix = "";
function loadProvince(idPrefix, selectValue) {
	pcaIdPrefix = idPrefix;
	$.ajax({
		url : basePath + provinceListUrl,
		type : "get",
		dataType : 'json',
		// data : data,
		async : false,
		success : function(req) {
			if (req.status == 100) {
				var datas = req.data;
				ProvinceCallBack(datas, selectValue);
			} else if (datas.status = 5002) {
				location.href = basePath + toLoginUrl;
			} else {
				layer.alert("系统出错", {
					offset : '100px'
				});
			}

		}
	});

}
function ProvinceCallBack(datas, selectValue) {
	if (!selectValue) {
		var options = '<option disabled selected>请选择省份</option>';
	}
	var selected='';
	for (var i = 0; i < datas.length; i++) {
		if (selectValue == datas[i].provinceId) {
			selected=' selected="selected" ';
		} else {
			selected='';
		}
		options += '<option value="' + datas[i].provinceId
		+ '" '+selected+'>' + datas[i].provinceName
		+ '</option>';
	}
	$("#" + pcaIdPrefix + "Province").html(options);
}
function ProvinceChange(obj) {
	loadCity(obj.value, "")
}

function loadCity(value, selectValue) {
	var provinceId = value;
	if (provinceId) {
		$.ajax({
			url : basePath + cityListUrl,
			type : "get",
			dataType : 'json',
			data : {
				pId: provinceId
			},
			async : false,
			success : function(req) {
				if (req.status == 100) {
					var datas = req.data;
					cityCallBack(datas, selectValue);
				} else if (datas.status = 5002) {
					location.href = basePath + toLoginUrl;
				} else {
					layer.alert("系统出错", {
						offset : '100px'
					});
				}

			}
		});
	}
}
function cityCallBack(datas, selectValue) {
	if (!selectValue) {
		var options = '<option disabled selected>请选择市</option>';
	}
	var selected='';
	for (var i = 0; i < datas.length; i++) {
		if (selectValue == datas[i].cityId) {
			selected=' selected="selected" ';
		} else {
			selected='';
		}
		options += '<option value="' + datas[i].cityId
		+ '" '+selected+' >' + datas[i].cityName
		+ '</option>';
	}
	$("#" + pcaIdPrefix + "City").html(options);
	$("#" + pcaIdPrefix + "Area").html(
			"<option disabled selected>请选择区</option>");
}
function cityChange(obj) {
	loadAreaList(obj.value, "");
}
function loadAreaList(value, selectValue) {
	var cityId = value;
	if (cityId) {
		$.ajax({
			url : basePath + areaListUrl,
			type : "get",
			dataType : 'json',
			data : {
				cId : cityId
			},
			async : false,
			success : function(req) {
				if (req.status == 100) {
					var datas = req.data;
					areaListCallBack(datas, selectValue);
				} else if (datas.status = 5002) {
					location.href = basePath + toLoginUrl;
				} else {
					layer.alert("系统出错", {
						offset : '100px'
					});
				}

			}
		});
	}
}
function areaListCallBack(datas, selectValue) {
	if (!selectValue) {
		var options = '<option disabled selected>请选择区</option>';
	}
	var selected='';
	for (var i = 0; i < datas.length; i++) {
		if (selectValue == datas[i].areaId) {
			selected=' selected="selected" ';
		} else {
			selected='';
		}
		options += '<option value="' + datas[i].areaId
		+ '" '+selected+' >' + datas[i].areaName
		+ '</option>';
	}
	$("#" + pcaIdPrefix + "Area").html(options);
}

// 获取时间======================================================
Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

// 字符串转时间
String.prototype.toDate = function() {
	var temp = this.toString();
	temp = temp.replace(/-/g, "/");
	return new Date(Date.parse(temp));
}
// 字符串转时间parse方法
function parseDate(dateStr) {
	return dateStr.toDate();
}

// 获取复选框选中值
function getIds(name) {
	name = name ? name : "ids";
	var ids = [];
	$('input[name="' + name + '"]:checked').each(function() {
		ids.push($(this).val());
	});
	return ids.join(",");
}

(function($) {
	$("body").on("click", "a", function(e) {
		e.preventDefault();
	});
	function ajaxSubmitForm(formId) {
		var $form = $("#" + formId);
		function _doPost() {
			var url = ajaxTodoUrl ? ajaxTodoUrl : $form.attr('action');
			var type = ajaxTodoUrl ? "get" : "post";
			$.ajax({
				type : type,
				url : basePath + url,
				dataType : 'json',
				cache : false,
				data : $("#" + formId).serializeObject(),
				success : function(req) {
					ajaxCallback(req);
					var callback = $form.attr("callback");
					if (callback) {
						eval(callback);
					} else {
						goBack();
					}
					
				}
			});
		}
		_doPost();
	}
	
	function ajaxCallback(req){
		if (req.status == 100) {
			layer.alert(req.msg, {
				offset : '100px',
				time : 2000,
				closeBtn : 2
			});
		} else if (req.status == 0) {
			layer.alert(req.msg, {
				offset : '100px'
			});
		} else if (req.status == 5002) {
			location.href = basePath + toLoginUrl;
		} else {
			layer.alert("系统出错", {
				offset : '100px'
			});
		}
	}

	function iframeSubmitForm(form) {
		var $form = $(form), $iframe = $("#callbackframe");
		var callback = $form.attr("callback");
		// if(!$form.valid()) {return false;}

		if ($iframe.size() == 0) {
			$iframe = $(
					"<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>")
					.appendTo("body");
		}
		if (!form.ajax) {
			$form.append('<input type="hidden" name="ajax" value="1" />');
		}
		form.target = "callbackframe";
		form.submit();
		_iframeResponse($iframe[0], callback);
	}
	function _iframeResponse(iframe, callback) {
		var $iframe = $(iframe), $document = $(document);
		$iframe.load(function() {
			$iframe.unload();
			var res = $(this).contents().find('body').text();
			res=JSON.parse(res);
			ajaxCallback(res);
			if (callback) {
				eval(callback);
			} else {
				goBack();
			}
		});

	}
	$.fn.extend({
		iframeSubmitForm : function() {
			var formId = "";
			if ($(this).is("form")) {
				formId = $(this).attr("id");
			} else {
				formId = $(this).attr("rel");
			}
			if (!formId) {
				layer.alert('请指定表单元素', {
					offset : '100px',
					closeBtn : 2
				});
				return false;
			}
			iframeSubmitForm($("#" + formId)[0]);
		},
		ajaxSubmitForm : function() {
			var formId = "";
			if ($(this).is("form")) {
				formId = $(this).attr("id");
			} else {
				formId = $(this).attr("rel");
			}
			if (!formId) {
				layer.alert('请指定表单元素', {
					offset : '100px',
					closeBtn : 2
				});
				return false;
			}
			ajaxSubmitForm(formId);
		},
		fileExport: function(){
			function _doExport($this) {
				var formId=$this.attr("rel");
				var $form = $("#"+formId);
				var url = $this.attr("href");
				window.location = basePath+url+(url.indexOf('?') == -1 ? "?" : "&")+$form.serialize();
			}
			
			return this.each(function(){
				var $this = $(this);
				var title = $this.attr("title");
				if (title) {
					layer.confirm(title, {
						btn : [ '确定', '取消' ],
						offset : '100px',
						closeBtn : 2,
					}, function(index, layero) {
						_doExport($this);
						layer.closeAll();
					}, function(index) {
						layer.closeAll();
					});
				} else {_doExport($this);}
			});
		},
		selectedTodo : function() {
			function _getIds(selectedIds, targetType) {
				var ids = "";
				$("input:checked").filter("[name='" + selectedIds + "']").each(
						function(i) {
							var val = $(this).val();
							ids += i == 0 ? val : "," + val;
						});
				return ids;
			}
			var $this = $(this);
			var selectedIds = $this.attr("rel") || "ids";
			var targetType = $this.attr("targetType");
			var ids = _getIds(selectedIds, targetType);
			if (!ids) {
				layer.alert('请选择', {
					offset : '100px',
					closeBtn : 2
				});
				return false;
			}

			function _doPost() {
				var url = ajaxTodoUrl ? ajaxTodoUrl : $this.attr('href');
				var type = ajaxTodoUrl ? "get" : "post";
				$.ajax({
					type : type,
					url : basePath + url,
					dataType : 'json',
					cache : false,
					data : function() {
						var _data = {};
						_data[selectedIds] = ids;
						return _data;
					}(),
					success : function(req) {
						var data = req.data;
						if (req.status == 100) {
							refreshCurrent();
							layer.alert(req.msg, {
								offset : '100px',
								time : 2000,
								closeBtn : 2
							});
						} else if (req.status == 0) {
							layer.alert(req.msg, {
								offset : '100px'
							});
						} else if (req.status == 5002) {
							location.href = basePath + toLoginUrl;
						} else {
							layer.alert("系统出错", {
								offset : '100px'
							});
						}
					}
				});
			}
			var title = $this.attr("title");
			layer.confirm(title, {
				btn : [ '确定', '取消' ],
				offset : '100px',
				closeBtn : 2,
			}, function(index, layero) {
				// 按钮【按钮一】的回调
				_doPost();
				layer.closeAll();
			}, function(index) {
				// 按钮【按钮二】的回调
				layer.closeAll();
			});
			return false;
		}
	});
})(jQuery);
$("body").on("click", ".submitBtn", function() {
	$(this).ajaxSubmitForm();
});
$("body").on("click", ".fileExport", function() {
	$(this).fileExport();
});
$("body").on("click", "a[target=selectedTodo]", function() {
	$(this).selectedTodo();
});

$("body").on("click", ".menu", function() {
	$(this).siblings().removeClass("headMenu");
	$(this).addClass("headMenu");
	loadMenu($(this).attr("rel"));
});
// 实时监控test版
function toB(){
	$("#menuMain").load("index.html");
	$(".sidebar").show();
	$("#menuMain").addClass("col-md-offset-2 col-sm-offset-2 col-sm-10 col-md-10").removeClass("col-md-12");
	loadMenu("PEIO10uBCSkCIR2iG2n");
}
function toMonitor(){
	$(".sidebar").hide();
	$("#menuMain").removeClass("col-md-offset-2 col-sm-offset-2 col-sm-10 col-md-10").addClass("col-md-12");
//	loadpage();
loadMonitor();
}
function loadMonitor(){
	$("#menuMain").load("static/html/monitor.html");
}
