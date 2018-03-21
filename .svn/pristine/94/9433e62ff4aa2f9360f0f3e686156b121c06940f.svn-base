//var loadHtml = '';
//function onLoading(){
//    loadHtml = ' <div id="loading">'+
//            '<ol>'+
//                '<li id="a"></li>'+
//                '<li id="b"></li>'+
//                '<li id="c"></li>'+
//                '<li id="d"></li>'+
//                '<li id="e"></li>'+
//                '<li id="f"></li>'+
//                '<li id="g"></li>'+
//                '<li id="h"></li>'+
//                '<li id="i"></li>'+
//            '</ol>'+
//        '</div>'
//    $('body').append(loadHtml);
//    //console.log(loadHtml)
//}
//
//
//function removeLoading(){
//    //console.log(2);
//    setTimeout(function(){
//        $('#loading').remove();
//    },4000);
//}

function removeLoading(){
    setTimeout(function(){
        $('#loading').hide();
    },500)
}

//判断是否为数组
function isArray(o){
    return Object.prototype.toString.call(o)=='[object Array]';
}
function exceptionHandle(data){
    var data=data;
    if(isArray(data)){
        if(data.length == 0){
            layer.closeAll();
            layer.open({
                type: 1,
                offset: '100px',
                title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                content:"没有查询到结果!",
                time:3000,
                area: ['310px', '180px'],//宽高
                btn: ["确定"],
                yes: function (index, layero) {
                    layer.closeAll();
                },
                cancel: function (index, layero) {
                    layer.closeAll();
                }
            })
            return;
        }
    }else if(data == undefined){
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            content:"部分请求无响应!",
            time:3000,
            area: ['310px', '180px'],//宽高
            btn: ["确定"],
            yes: function (index, layero) {
                layer.closeAll();
            },
            cancel: function (index, layero) {
                layer.closeAll();
            }
        })
        return;
    }else if(JSON.stringify(data) == "{}"){//判断是否为对象
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            content:"没有查询到结果",
            time:3000,
            area: ['310px', '180px'],//宽高
            btn: ["确定"],
            yes: function (index, layero) {
                layer.closeAll();
            },
            cancel: function (index, layero) {
                layer.closeAll();
            }
        })
        return;
    }
}