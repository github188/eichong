/**
 * Created by haner on 15/4/16.
 */
var Clock = function () {

    var _this = this;

    function init() {
        drawLines($('.line-min'), 60, 90);
        drawLines($('.line-hour'), 12, 85);
        drawNumbers($('.number'));
        if(arguments.length&&arguments.length==3)
            setDate(arguments[0],arguments[1],arguments[2]);
        else
            setDate();
    }

    /*
     * 画刻度线
     */
    function drawLines(wrap, total, translateX) {
        //角度
        var gap = 360 / total;
        var strHtml = '';
        for (var i = 0; i < total; i++) {
            strHtml += '<li style="transform:rotate(' + (i * gap) + 'deg) translate(' + translateX + 'px,-50%)"></li>';
        }
        wrap.html(strHtml);
    }


    /*
     * 绘制时钟数字
     */
    function drawNumbers(wrap) {
        //半径获取
        var radius = wrap.width() / 2;

        var strHtml = '';
        for (var i = 1; i <= 12; i++) {
            var myAngle = (i - 3) / 6 * Math.PI;

            var myX = radius + radius * Math.cos(myAngle), // x=r+rcos(θ)
                myY = radius + radius * Math.sin(myAngle); // y=r+rsin(θ)

            strHtml += '<li style="left:' + myX + 'px; top:' + myY + 'px">' + i + '</li>';
        }
        wrap.html(strHtml);
    }


    /*
     * 钟表走动，转动秒针、分针、时针
     */
    function move() {
        var domHour = $(".hour"), domMin = $(".min"), domSec = $(".sec");


        _this.timer = setInterval(function () {

            //init val
            h = (_this.hour);
            m = _this.min;
            s = _this.sec++;


            var secAngle = s * 6 - 90,  // 减去90度(translate 坐标轴X为横屏开始)
                minAngle = m * 6 + s * 0.1 - 90,  // m*6+s*0.1-90
                hourAngle = h * 30 + m * 0.5 - 90;  // h*30+m*0.5 - 90

            domSec.css('transform', 'rotate(' + secAngle + 'deg)');
            domMin.css('transform', 'rotate(' + minAngle + 'deg)');
            domHour.css('transform', 'rotate(' + hourAngle + 'deg)');

            //for
            if (s >= 60) {
                s = _this.sec = 1;
                _this.min++;
            }
            if (m >= 60) {
                _this.min = 1;
                _this.hour++;
            }

            if (h >= 24)
                _this.hour = 0;

        }, 1000);

    }

    /**
     * 设置时间
     * @param h
     * @param m
     * @param s
     */
    function setDate(h, m, s) {
        _this.hour = h ? (!!(h = parseInt(h)) ? (h > 24 ? 0 : h) : 0) : new Date().getHours();
        _this.min = m ? (!!(m = parseInt(m)) ? (m > 60 ? 0 : m) : 0) : (new Date().getMinutes());
        _this.sec = s ? (!!(s = parseInt(s)) ? (s > 60 ? 0 : s) : 0) : new Date().getSeconds();

        clearInterval(_this.timer);
        move();
    }

    return {
        init: init
    };
};

