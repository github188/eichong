var historyData = [];
var dateValue;
/**
 * 查询参数
 */
headHistoryParamsData = function () {
    var headId = window.localStorage.getItem('monitor_headId');
    var epCode = window.localStorage.getItem('monitor_epCode');
    var epType = window.localStorage.getItem('monitor_epType');

    var dateValue=$('#hiddenDate').val();
    if(dateValue==''){
        var nowTime = new Date().getTime();
        var todayDate = new Date(nowTime).format("yyyy-MM");
        dateValue = todayDate.replace('-', '');
    }else{
        dateValue=dateValue;
    }
    return {
        pageNum: 1,
        pageSize: 10,
        date: dateValue,
        headId: headId,
        epCode: epCode,
        epType: epType
    };
}

function searchHistory(pageNum, pageSize) {
    var params = headHistoryParamsData();
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getHeadHistoryListUrl,
        //url: 'test.json',
        async: true,
        data: params,
        success: function (req) {
            layer.closeAll();
            if (req.success == true) {
                historyData = req.dataObject.data;
            } else if (req.status == 9001) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '会话超时，请重新登陆！',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });

            } else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: req.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
            setHistoryInfo(pageNum, pageSize);
        }
    });
}


//初始化月份标签
function initMonthTab() {
    var myDate = new Date();
    var dangqian = myDate.getMonth() + 1;
    var monthTabHtml = "";
    for (var i = 5; i >= 0; i--) {
        var historyMonth = dangqian - i;
        var yearVal = myDate.getFullYear();
        if (i == 0) {
            monthTabHtml = '<span class="dangqian monthTab" data-value='
            + historyMonth + ' data-year=' + yearVal + '>'
            + historyMonth + '月</span>' + monthTabHtml;
            if (historyMonth < 10) {
                historyMonth = "0" + historyMonth
            }
            $('#dateBlock').val(yearVal + historyMonth);
        } else {
            if (historyMonth <= 0) {
                historyMonth = historyMonth + 12;
                yearVal = yearVal - 1;
                if (historyMonth == 12) {
                    monthTabHtml = '<span class="nian monthTab" data-value='
                    + historyMonth + ' data-year=' + yearVal + '>'
                    + yearVal + '年12月</span>' + monthTabHtml;
                } else {
                    monthTabHtml = '<span class="yue monthTab" data-value='
                    + historyMonth + ' data-year=' + yearVal + '>'
                    + historyMonth + '月</span>' + monthTabHtml;
                }
            } else {
                monthTabHtml = '<span class="yue monthTab" data-value='
                + historyMonth + ' data-year=' + yearVal + '>'
                + historyMonth + '月</span>' + monthTabHtml;
            }
        }
    }
    $('#dateBlock').html(monthTabHtml);

    //*************tab切换事件**************
    $(".monthTab").click(function () {
        $this = $(this);
        var monthVal = $this.attr('data-value');
        var yearVal = $this.attr('data-year');
        if (monthVal < 10) {
            monthVal = "0" + monthVal;
        }
        $(".dangqian").addClass('yue');
        $(".dangqian").removeClass('dangqian');
        $this.addClass('dangqian');
        dateValue=yearVal + monthVal;
        $('#hiddenDate').val(dateValue);
        searchHistory(1,10);
    })
}
function setHistoryInfo(pageNum, pageSize) {
    var epCode = window.localStorage.getItem('monitor_epCode');
    var infoHtml = '';
    var historyInfoLi = '';
    var allCount = historyData.length;
    var lastIndex = allCount - pageNum * pageSize;
    var startIndex = lastIndex + pageSize;
    if (historyData != null && allCount > 0) {
        for(var i = startIndex-1; i >= 0; i--){
            if (startIndex-i <= pageSize){
                statusHtml = '';
                if (historyData[i].status == 3) {
                    statusHtml = '开始充电';
                } else if (historyData[i].status == 1 || historyData[i].status >= 30) {
                    statusHtml = '故障';
                }
                var time = new Date(historyData[i].ts).format("yyyy-MM-dd hh:mm");
                if (historyData[i].status == 3) {
                    historyInfoLi += '<li class="infoLi"><span class="infoType">充电</span>'
                    + '<p class="infoHtml">' + time + '，用户在' + epCode + '号桩开始充电</p></li>'
                } else {
                    historyInfoLi += '<li class="infoLi"><span class="faultType">故障</span>'
                    + '<p class="infoHtml">' + time + '，' + epCode + '号桩发生故障</p></li>'
                }
            }else{
                break;
            }
        }
    }
    $('#infoUl').html(historyInfoLi);
    historyData.totalPage = Math.ceil(allCount / pageSize);
    historyData.count = pageSize;
    historyData.currentPage = pageNum;
    initPage2(historyData, "setHistoryInfo", 'pageEle1');
}
initMonthTab();
searchHistory(1,10);