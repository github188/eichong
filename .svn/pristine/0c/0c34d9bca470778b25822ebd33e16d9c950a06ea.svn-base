var cpyId = getUrlParam('cpyId');
//ztree电桩数据,需要修改参数
var setting = {
    check: {
        enable: true,
        chkboxType: {"Y": "ps", "N": "ps"}
    },
    data: {
        simpleData: {
            enable: true
        },
        key: {
            checked: "isSelected",
            children: "list",
            name: "name"
        }
    }
};
//点击取消返回公司主页
$('#goCancel').on('click', function () {
    window.location.href = 'company_home.html?cpyId=' + cpyId;
})
//页面加载进来，获取公司名称，加载等级列表
$(document).ready(function () {
    //获取公司名称,加载等级列表
    var setChargeInfo = window.localStorage;
    var companyName = setChargeInfo.getItem("remCompanyName");
    $('#companyNameInfo').html(companyName);
    getLevelInfo();
})
function getLevelInfo() {
    $.ajax({
        type: "post",
        url: basePath + getRateUniqueRelaGroupUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            if (req.success == true) {
                getLevelNameCallBack(req);
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

            }
        }
    });
}
function getLevelNameCallBack(req) {
    var data = req.dataObject;
    var json = '[';
    var arr = [];
    var id = '';
    var levelName = '';
    for (var i = 0; i < data.length; i++) {
        if (i == 0) {
            id = data[i].levelId;
            levelName = data[i].levelName;
            arr.push(data[i].rateinfoId);
        } else {
            if (data[i].levelId == id && i != data.length - 1) {
                arr.push(data[i].rateinfoId);
            } else if (data[i].levelId == id && i == data.length - 1) {
                arr.push(data[i].rateinfoId)
                json += "{\"levelId\":" + "\"" + id
                    + "\"" + ",\"arr\":" + "\"" + arr
                    + "\"" + ",\"levelName\":" + "\"" + levelName
                    + "\"}";
            } else if (data[i].levelId != id && i != data.length - 1) {
                var newArr = [];
                var newId = '';
                var newLevelName = '';
                newId = id;
                newLevelName = levelName;
                newArr = arr;
                json += "{\"levelId\":" + "\"" + newId
                    + "\"" + ",\"arr\":" + "\"" + newArr
                    + "\"" + ",\"levelName\":" + "\"" + newLevelName
                    + "\"},";
                arr = [];
                arr.push(data[i].rateinfoId);
                id = data[i].levelId;
                levelName = data[i].levelName;
            } else if (data[i].levelId != id && i == data.length - 1) {
                json += "{\"levelId\":" + "\"" + id
                    + "\"" + ",\"arr\":" + "\"" + arr
                    + "\"" + ",\"levelName\":" + "\"" + levelName
                    + "\"},";
                arr = [];
                arr.push(data[i].rateinfoId);
                json += "{\"levelId\":" + "\"" + data[i].levelId
                    + "\"" + ",\"arr\":" + "\"" + arr
                    + "\"" + ",\"levelName\":" + "\"" + data[i].levelName
                    + "\"}";
            }
        }
    }
    json += ']';
    getFinalDataObject(json);
    return json;
}
function getFinalDataObject(json) {
    var data = JSON.parse(json);
    console.log(data);
    var show = '';
    var listLi = '';
    var spArr = [];
    var addRateHtml = '添加费率';
    for (var j = 0; j < data.length; j++) {
        if (j == 0) {
            show = 'in';
            listLi += '<div class="panel panel-default"><div class="panel-heading" role="tab" id="headingOne' + j + '">'
                + '<h4 class="panel-title"><a  class="levelName" role="button" data-toggle="collapse" data-parent="#accordion"'
                + 'href="#collapseOne' + j + '" aria-expanded="true" aria-controls="collapseOne">' + data[j].levelName + '</a>'
                + '<span class="addRate" data-levelId="' + data[j].levelId + '">' + addRateHtml + '</span></h4>'
                + '</div><div id="collapseOne' + j + '" class="panel-collapse collapse ' + show + '" role="tabpanel" aria-labelledby="headingOne"><div class="panel-body" data-levelId="' + data[j].levelId + '">'
        } else {
            show = '';
            listLi += '<div class="panel panel-default"><div class="panel-heading" role="tab" id="headingOne' + j + '">'
                + '<h4 class="panel-title"><a  class="levelName" role="button" data-toggle="collapse" data-parent="#accordion"'
                + 'href="#collapseOne' + j + '" aria-expanded="true" aria-controls="collapseOne">' + data[j].levelName + '</a>'
                + '<span class="addRate" data-levelId="' + data[j].levelId + '">' + addRateHtml + '</span></h4>'
                + '</div><div id="collapseOne' + j + '" class="panel-collapse collapse ' + show + '" role="tabpanel" aria-labelledby="headingOne"><div class="panel-body" data-levelId="' + data[j].levelId + '">'
        }
        spArr = data[j].arr.split(',');
        for (var m = 0; m < spArr.length; m++) {
            listLi += '<div class="col-sm-12 rateList"><div class="col-sm-2 rateInfoId"><input type="text" value="' + spArr[m] + '" readonly="readonly">'
                + '</div><div class="col-sm-2 rateBtn changePile editRate">编辑费率</div>'
                + '<div class="col-sm-2 rateBtn changePile savePile" style="display: none;">保存费率</div></div>';
        }

        listLi += '</div></div></div>';
        $('#accordion').html(listLi);
    }

}
//编辑费率按钮
$('body').off('click', '.editRate').on('click', '.editRate', function () {
    var rateinfoId = $(this).prev().find('input').val();
    window.localStorage.setItem('oldRateinfoId', rateinfoId);
    var levelId = $(this).parent().parent().attr('data-levelId');
    $(this).prev().find('input').attr('readonly', false).focus();
    $(this).hide().next().show();
    window.localStorage.setItem('rateinfoId', rateinfoId);
    window.localStorage.setItem('levelId', levelId);
    //平行的按钮都变成编辑按钮，并且input禁止
    $(this).parent().siblings().find('.editRate').show();
    $(this).parent().siblings().find('.savePile').hide();
    $(this).parent().siblings().find('input').attr('readonly', true);

    getZNodes(cpyId, levelId, rateinfoId);

})
function getZNodes(cpyId, levelId, rateinfoId) {
    var zNodes = "";
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getRateStationAndPileUrl,
        async: true,
        data: {
            cpyId: cpyId,
            levelId: levelId,
            rateinfoId: rateinfoId
        },
        success: function (req) {
            layer.closeAll();
            if (req.success == true) {
                zNodes = req.dataObject;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
        }
    });
}
//点击保存
$('body').off('click', '.savePile').on('click', '.savePile', function () {
    var rateinfoId = $(this).prev().prev().find('input').val();
    var levelId = window.localStorage.getItem('levelId');
    var oldRateinfoId = window.localStorage.getItem('oldRateinfoId');
    var reg = /^[0-9][\d]*$/;
    if (!rateinfoId) {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "请输入费率",
            btn: ["确定"]
        });
        return false;
    } else if (!reg.test(rateinfoId)) {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "输入费率有误，请重新输入费率",
            btn: ["确定"]
        });
        return false;
    }
    setChargePiles(rateinfoId,levelId,oldRateinfoId);
    //兄弟显示出来，框框禁止操作
})
function setChargePiles(rateinfoId,levelId,oldRateinfoId) {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = treeObj.getCheckedNodes(true);
    var idsArr = [];
    var ids = '';
    for (var i = 0; i < nodes.length; i++) {
        var PileIds = nodes[i].id;
        idsArr.push(PileIds);
    }
    for (var j = 0; j < idsArr.length; j++) {
        if (idsArr[j]) {
            ids += idsArr[j] + ',';
        }
    }
    var obj = {
        cpyId: cpyId,
        rateinfoId: rateinfoId,
        oldRateinfoId: oldRateinfoId,
        levelId: levelId,
        electricpileIds: ids
    };
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + addRateUniqueRelaUrl,
        async: true,
        data: obj,
        success: function (req) {
            layer.closeAll();
            if (req.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '180px'],//宽高
                    content: "保存成功",
                    btn: ["确定"],
                    yes: function (index, layero) {
                        window.location.href = 'company_home.html?cpyId=' + cpyId;
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = 'company_home.html?cpyId=' + cpyId;
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
                    btn: ["确定"],
                    time:3000,
                    yes: function (index, layero) {
                        layer.closeAll();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                    }
                });
            }

        }
    });
}
//添加费率addRate
$('body').off('click','.addRate').on('click','.addRate',function(){
    var levelId=$(this).attr('data-levelId');
    var newRateStr='<div class="col-sm-12 rateList"><div class="col-sm-2 rateInfoId"><input type="text" value="0" readonly="readonly">'
        + '</div><div class="col-sm-2 rateBtn changePile editRate">编辑费率</div>'
        + '<div class="col-sm-2 rateBtn changePile savePile" style="display: none;">保存费率</div><span class="delBtn">删除</span></div>';
    $(this).parent().parent().next().find('.panel-body').append(newRateStr);
})
$('body').off('click','.delBtn').on('click','.delBtn',function(){
    $(this).parent().remove();
})