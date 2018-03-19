var regUserName = /^([A-Z]|[a-z]|\d){6,16}$/;
var $userName = "";
var $password = "";
var $codeImg = "";
var $regTest = "";
var $codeValue = "";

function testUserName() {
    $userName = $("#userName").val();
    $codeImg = $("#codeImg");
    $regTest = $("#regTest");
    if (!regUserName.test($userName)) {
        $regTest.show();
        $regTest.html("请填写正确的账号!");
        $("#userName").focus();
        return false;
    } else {
        $regTest.hide();
        $regTest.html("");
        return true;
    }
    return true;
}

// 点击登录按钮正则验证,若账号和密码均未填写，则点击登录按钮提示“请输入账号”
function commonTest() {
    $userName = $("#userName").val();
    $password = $("#password").val();
    $regTest = $("#regTest");
    $codeValue = $("#codeValue").val();
    if ($userName == "" && $password == "") {
        $regTest.show();
        $regTest.html("请输入账号!");
        $("#userName").focus();
        return false;
    } else if (testUserName() && $password == "") {
        $regTest.show();
        $regTest.html("请输入密码!");
        $("#password").focus();
        return false;
    } else if (testUserName() && $codeValue == "") {
        $regTest.show();
        $("#codeValue").focus();
        $regTest.html("请输入验证码!");
        return false;
    }
    $regTest.hide();
    return true;
}
function reloadCode() {
    var time = new Date().getTime();
    var img = "common/getValidCode.do?t=" + time;
    $("#codeImg").attr("src", img);
}
function generateMixed(n) {
    var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
        'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    var res = "";
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 35);
        res += chars[id];
    }
    return res;
}
$(function () {
    reloadCode();
    $("#loginUser").click(function () {// 保存用户
        toLoginCtrl();
    });
});

function toLoginCtrl(){
    if (commonTest()) {
        toCtrLogin();
    }
}
function toCtrLogin() {
    var username = $('#userName').val();
    var codeValue = $('#codeValue').val();
    var password = $.md5($("#password").val());
    var password = $.md5(password + $("#userName").val()) + generateMixed(1);
    $.ajax({
        type: "post",
        url: basePath + loginUrl,
        async: true,
        data: {
            username: username,
            passwd: password,
            code: codeValue
        },
        success: function (req) {
            if (req.success == true) {
                window.location.href = basePath + loginUrl;
            } else {
                $('#regTest').show().html(req.msg);
            }
        }
    });
}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        toLoginCtrl();
    }
});