/**
 * Created by chenjie on 2017/10/25 0025.
 */
//初始加载
$(document).ready(function() {
    $('.sureBtn').bind("click", foo);
    $.ajax({
        url: basePath + getIntegralActivityAndRulesListUrl,
        type: "post",
        dataType: 'json',
        data: {
            pkId: 8
        },
        success: function(data) {
            var list_td = '',
                arr = new Array();
            var len = (data.dataObject).length;
            //循环integralRulesId成一个数组
            for (var i = 0; i < len; i++) {
                arr[i] = data.dataObject[i].integralRulesId;
            }
            //获取编辑的数据的index
            var index = arr.indexOf(parseInt(getUrlParam('integralRulesId')));
            //状态
            if (data.dataObject[index].activityStatus == 0) {
                $('input[name="state"]:eq(0)').attr("checked", 'checked');
            } else if (data.dataObject[index].activityStatus == 1) {
                $('input[name="state"]:eq(1)').attr("checked", 'checked');
                $('input[name="add_FestivalName"]').attr("disabled", true); //活动名称
                $('input[name="score"]').attr("disabled", true); //分值
                $('#datePicker').attr("disabled", true); //时间选择器
            }
            //赋值
            $('input[name="add_FestivalName"]').attr('data-value',getUrlParam('integralRulesId')).val(data.dataObject[index].contents);
            var startday = new Date(data.dataObject[index].startDate.time).format("yyyy-MM-dd"),
                endday = new Date(data.dataObject[index].endDate.time).format("yyyy-MM-dd");
            $('#datePicker').val(startday + ' - ' + endday);
            $('input[name="score"]').val(data.dataObject[index].fixedIntegralValue)
            //加载表格
            if (len != '') {
                for (var i = 0; i < len; i++) {
                    if (data.dataObject[i].activityStatus == 0) {
                        list_td += '<tr data-value="' + data.dataObject[i].integralRulesId + '" target ="' + data.dataObject[i].activityStatus + '"><td>' + data.dataObject[i].contents + '</td>' + '<td>' + new Date(data.dataObject[i].startDate.time).format("yyyy-MM-dd") + ' - ' + new Date(data.dataObject[i].endDate.time).format("yyyy-MM-dd") + '</td>' + '<td>' + data.dataObject[i].fixedIntegralValue + '</td>' + '<td><a style="text-align:center;color:blue;padding: 0px 2px;cursor:pointer;" onclick="updateRow(this);">修改</a><a style="text-align:center;color:blue;padding: 0px 2px;cursor:pointer;" onclick="delRow(this);">关闭</a></td>'
                    } else if (data.dataObject[i].activityStatus == 1) {
                        list_td += '<tr data-value="' + data.dataObject[i].integralRulesId + '" target ="' + data.dataObject[i].activityStatus + '"><td>' + data.dataObject[i].contents + '</td>' + '<td>' + new Date(data.dataObject[i].startDate.time).format("yyyy-MM-dd") + ' - ' + new Date(data.dataObject[i].endDate.time).format("yyyy-MM-dd") + '</td>' + '<td>' + data.dataObject[i].fixedIntegralValue + '</td>' + '<td><a style="text-align:center;color:blue;padding: 0px 2px;cursor:pointer;" onclick="restartRow(this);">激活</a></td>'
                    }
                }
            } else {
                return true;
            }

            $('.already-festival').after(list_td);
        }
    })
});

//日期选择
laydate.render({
    elem: '#datePicker',
    range: true,
    theme: '#ff7d00'
    /* ,min: -90,
     max:0//0 代表今天 -1代表昨天，-2代表前天，以此类推*/
});

//返回按钮
$('body').off('click', '#toPowerStationList').on('click', '#toPowerStationList', function() {
    window.history.back();
});

//基本后台反馈交互
function CommonCaution(data) {
    var status = true;
    if (data.status == 1000) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: data.msg,
            btn: ["确定"],
            yes: function(index, layero) {
                layer.closeAll();
                window.location.reload();
            },
            cancel: function(index, layero) {
                layer.closeAll();
                window.location.reload();
            }
        });
    } else if (data.status == 9001) {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: '会话超时，请重新登陆！',
            btn: ["确定"],
            yes: function(index, layero) {
                layer.closeAll();
                window.top.location.href = basePath + toLoginUrl;
            },
            cancel: function(index, layero) {
                layer.closeAll();
                window.top.location.href = basePath + toLoginUrl;
            }
        });
        status = false;
    } else if (data.status == 2001) {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: data.msg,
            btn: ["确定"],
            yes: function(index, layero) {
                layer.closeAll();
            },
            cancel: function(index, layero) {
                layer.closeAll();
            }
        });
        status = false;
    } else {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: data.msg,
            btn: ["确定"],
            yes: function(index, layero) {
                window.location.reload();
                layer.closeAll();
            },
            cancel: function(index, layero) {
                window.location.reload();
                layer.closeAll();
            }
        });
        status = false;
    }
    return status;
}

//基本后台反馈交互

//-------------------------------------列表---------------------------------------------
var row = 0; //定义全局行数用于修改
//----获取行号-----

function getRow(r) {
    var i = r.parentNode.parentNode.rowIndex;
    return i;
}
//----获取行号-----

//----删除某一行-----

function delRow(r) {
    var pkId = r.parentNode.parentNode.getAttribute('data-value'),
        activityStatus = 1;
    $.ajax({
        url: basePath + modifyIntegralRulesUrl,
        type: "post",
        dataType: 'json',
        data: {
            pkId: pkId,
            activityStatus: activityStatus
        },
        success: function(data) {
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],
                //宽高
                content: data.msg,
                time: 0,
                btn: ["确定"],
                btn1: function(index, layero) {
                    window.location.reload();
                    layer.closeAll();
                },
                cancel: function(index, layero) {
                    window.location.reload();
                    layer.closeAll();
                }
            })
        }
    }); //数据库删除
}
//----删除某一行-----

//----激活按钮----

function restartRow(r) {
    var pkId = r.parentNode.parentNode.getAttribute('data-value'),
        activityStatus = 0;
    $.ajax({
        url: basePath + modifyIntegralRulesUrl,
        type: "post",
        dataType: 'json',
        data: {
            pkId: pkId,
            activityStatus: activityStatus
        },
        success: function(data) {
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],
                //宽高
                content: data.msg,
                time: 0,
                btn: ["确定"],
                btn1: function(index, layero) {
                    window.location.reload();
                    layer.closeAll();
                },
                cancel: function(index, layero) {
                    window.location.reload();
                    layer.closeAll();
                }
            })
        }
    }) //数据库删除
}


//----激活按钮----

//----清除添加信息框的内容-----

function cleanAddInput() {
    $('input[name="add_FestivalName"]').val('');
    $('#datePicker').val('');
    $('input[name="score').val('');
}
//----清除添加信息框的内容-----

//----显示添加信息框-----

function showAddInput() {
    $('.add-activity-botton').hide();
    $('.new-Fesitival-Activity').show();
    $('.saveBtn').show();
    $('#updateBtn').hide();
    cleanAddInput();
}
//----隐藏添加信息框-----

function hideAddInput() {
    $('.new-Fesitival-Activity').hide();
    $('.updateBtn').hide();
}
//----隐藏添加信息框-----

//----新增信息的插入方法-----

function insertInfo(obj) {
    //根据id获取表单信息
    var arr = new Array();
    arr[0] = $('input[name="add_FestivalName"]').val();
    arr[1] = $('#datePicker').val();
    arr[2] = $('input[name="score').val();
    if(obj == 1){
        arr[3] = "<a style='text-align:center;color:blue;padding: 0px 2px;cursor:pointer;' onclick='restartRow(this);'>激活</a>";
    }else{
        arr[3] = "<a style='text-align:center;color:blue;padding: 0px 2px;cursor:pointer;' onclick='updateRow(this);'>修改</a> <a style='text-align:center;color:blue;padding: 0px 2px;cursor:pointer;' onclick='delRow(this);'>关闭</a>";
    }

    var x = document.getElementById('table').insertRow(1); //获取第一行对象

    for (var i = 0; i < arr.length; i++) {
        x.insertCell(i).innerHTML = arr[i]; //用循环把每个数据插入第一行的每一列
    }

}
//----新增信息的插入方法-----

//----新增信息-----

function addInfo() {
    if(foo()){
        foo()
    }else{
        return false;
    }
    insertInfo(); //执行插入
    hideAddInput(); //隐藏添加信息框
    $('.add-activity-botton').show();
    $('#saveBtn').hide();
    $('#table').show();
    cleanAddInput(); //清除原有信息

}
//----新增信息-----

//----根据行号修改信息-----

function updateRow(r) {
    $('.add-activity-botton').hide();
    row = getRow(r); //把该行号赋值给全局变量
    showAddInput(); //显示修改表单
    insertInputFromQuery(queryInfoByRow(row));
    $('.saveBtn').hide();
    $('#updateBtn').show();
}
//----根据行号修改信息-----

//----根据行号查信息----

function queryInfoByRow(row) {
    var arr = new Array();
    for (var m = 0; m < 3; m++) {
        arr[m] = document.getElementById('table').rows[row].cells[m].innerText;
    }
    var str = $('#table tbody').find('tr:eq(' + row + ')').attr('data-value');
    arr.push(str)
    return arr; //返回该行数据

}
//----根据行号查信息----

//----把查询到的信息放入修改的表单里----

function insertInputFromQuery(arr) {
    $('input[name="add_FestivalName"]').val(arr[0]);
    $('#datePicker').val(arr[1]);
    $('input[name="score').val(arr[2]);
    $('input[name="add_FestivalName"]').attr('data-value', arr[3]);
}
//----把查询到的信息放入修改的表单里----

//错误提示
function layerCase(msg){
    layer.open({
        type: 1,
        offset: '100px',
        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '160px'],
        //宽高
        content: msg,
        time: 0,
        btn: ["确定"],
        btn1: function(index, layero) {
            layer.closeAll();
        },
        cancel: function(index, layero) {
            layer.closeAll();
        }
    });
}

//更新
function updateInfo(){
        var lenArr = new Array();
        for(var i=0;i<$('#table tr').length-1;i++){
            var n = i+1;
            lenArr[i] = $('#table tr:eq('+n+')').attr('data-value');
        }
        row = lenArr.indexOf(getUrlParam('integralRulesId')) + 1;
        var data_one = $('input:radio:checked').val(),
        //状态
            data_two = 8,
        //活动名称
            data_three = 0,
        //积分方法
            dayTime = $('#datePicker').val(),
        //活动时间
            data_four = $('input[name="add_FestivalName"]').val(),
        //活动名称
            data_five = $('input[name = "score"]').val();
        //分值
            data_six = $('input[name="add_FestivalName"]').attr('data-value');
        //固定分值
        var start_date = slice_date(dayTime)[0],
            end_day = slice_date(dayTime)[1];
        if (data_four == '') {
            layerCase('活动名称不能为空');
            return false;
        }else if(dayTime == ''){
            layerCase('活动时间不能为空');
            return false;
        }else if(data_five == ''){
            layerCase('分值不能为空');
            return false;
        }
        var data = {
            'pkId': data_two,
            'activityStatus': data_one,
            'activityName': '节假日',
            'direction': data_three,
            'fixedIntegralValue': data_five,
            'strStartDate': start_date,
            'strEndDate': end_day,
            'contents': data_four,
            'integralRulesId': data_six
        };

        $.ajax({
            url: basePath + modifyIntegralActivityUrl,
            type: "post",
            dataType: 'json',
            data: data,
            success: function(data) {
                CommonCaution(data);
                if(CommonCaution(data)){
                    document.getElementById('table').deleteRow(row); //删除原来那行
                    insertInfo(data_one); //插入修改后的值
                    hideAddInput(); //隐藏添加模块
                    $('#updateBtn').hide();
                    $('#saveBtn').hide();
                    $('.add-activity-botton').show();
                }

            }
        })
}
//-------------------------------------列表---------------------------------------------

//状态切换
$('.radio-inline').on('click', function() {
    var target = $(this).attr('state');
    if (target == '2') {
        $('input[name="add_FestivalName"]').attr("disabled", true); //活动名称
        $('input[name="score"]').attr("disabled", true); //分值
        $('#datePicker').attr("disabled", true); //时间选择器
    } else {
        $('.sureBtn').bind("click", foo);
        $('input[name="add_FestivalName"]').attr("disabled", false);
        $('input[name="score"]').attr("disabled", false);
        $('#datePicker').attr("disabled", false);
    }
});

//分值取整提醒
$('input[name="score"]').on({
    keyup: function() {
        if (this.value.length == 1) {
            this.value = this.value.replace(/[^1-9]/g, '')
        } else {
            this.value = this.value.replace(/\D/g, '')
        }
    },
    afterpaste: function() {
        if (this.value.length == 1) {
            this.value = this.value.replace(/[^1-9]/g, '0')
        } else {
            this.value = this.value.replace(/\D/g, '')
        }
    },
    blur: function() {
        if (this.value >= 100) {
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],
                //宽高
                content: '过高的分值可能会导致损失，请注意操作。',
                time: 0,
                btn: ["确定", "取消"],
                btn1: function(index, layero) {
                    layer.closeAll();
                },
                btn2: function(index, layero) {
                    $('input[name="score"]').val('');
                    layer.closeAll();
                },
                cancel: function(index, layero) {
                    $('input[name="score"]').val('');
                    layer.closeAll();
                }
            })
        } else if (this.value < 50 && this.value != '') {
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],
                //宽高
                content: '节假日分值请勿设置低于普通日期，会降低用户体验。',
                time: 0,
                btn: ["确定"],
                btn1: function(index, layero) {
                    layer.closeAll();
                },
                cancel: function(index, layero) {
                    layer.closeAll();
                }
            })
        } else if (this.value == '') {
            return true;
        }
    }
});

//数据提交
var foo = function() {
    var state = true;
    var data_one = $('input:radio:checked').val(),
    //状态
        data_two = 8,
    //活动名称
        data_three = 0,
    //积分方法
        day = $('#datePicker').val(),
    //时间
        data_four = $('input[name="add_FestivalName"]').val(),
    //新增活动名称
        data_five = $('input[name = "score"]').val();
    //固定分值
    var start_date = slice_date(day)[0],
        end_day = slice_date(day)[1];
    if (data_four == '' || day == '' || data_five == '') {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: '请确认信息是否填写完整',
            time: 0,
            btn: ["确定"],
            btn1: function(index, layero) {
                layer.closeAll();
            },
            cancel: function(index, layero) {
                layer.closeAll();
            }
        });
        state = false;
        return false;
    }
    var data = {
        'pkId': data_two,
        'activityStatus': data_one,
        'activityName': '节假日',
        'direction': data_three,
        'fixedIntegralValue': data_five,
        'strStartDate': start_date,
        'strEndDate': end_day,
        'contents': data_four
    };
    $.ajax({
        url: basePath + addIntegralActivityUrl,
        type: "post",
        dataType: 'json',
        data: data,
        success: function(data) {
            CommonCaution(data);
        }
    });
    return state;
};

//监听回车键
$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        foo();
    }
});

