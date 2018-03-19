/**
 * Created by haner on 15/4/16.
 */
var TimerCountDown = function () {

    var option = {
        marginSecond: 0,
        year: undefined,
        month: undefined,
        day: undefined,
        hour: undefined,
        min: undefined,
        sec: undefined
    }, stop = false,endDate = undefined;

    /**
     * init param
     */
    function init() {
        if (arguments.length && typeof arguments[0] === 'object') {
            _extend(option, arguments[0]);
        }
       if(!!option.marginSecond)
        endDate = new Date(Date.UTC(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), new Date().getHours(),new Date().getMinutes(),new Date().getSeconds()+option.marginSecond,new Date().getMilliseconds()));
        updateUI();
    }

    /**
     * date calc
     */
    function calc() {
        var startDate = new Date(),res = {
            year: "0",
            month: "00",
            day: "00",
            hour: "00",
            min: "00",
            sec: "00"
        };
        if (!endDate){
            stop = true;
            return res;
        }
        /**
         * getTimezoneOffset ref:https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Date/getTimezoneOffset
         * @type {number}
         */
        var marginTime = Math.round((endDate.getTime() - startDate.getTime()) / 1000) + endDate.getTimezoneOffset() * 60;

        if (marginTime > 0) {
            res.sec = addZero(marginTime % 60);
            res.min = Math.floor((marginTime / 60)) > 0 ? addZero(Math.floor((marginTime / 60)) % 60) : "00";
            res.hour = Math.floor((marginTime / 3600)) > 0 ? addZero(Math.floor((marginTime / 3600)) % 24) : "00";
            res.day = Math.floor((marginTime / 86400)) > 0 ? addZero(Math.floor((marginTime / 86400)) % 30) : "00";
            res.month = Math.floor((marginTime / 2629744)) > 0 ? addZero(Math.floor((marginTime / 2629744)) % 12) : "00";
            res.year = Math.floor((marginTime / 31556926)) > 0 ? Math.floor((marginTime / 31556926)) : "0";
        } else {
            stop = true;
        }
        return res;
    }

    /**
     * update dom val
     */
    function updateUI() {
        var r = calc();
        for (var o in r) {
            if (option[o])
                option[o].innerHTML = r[o];
        }
        if (stop) return;
        setTimeout(updateUI, 1000);
    }

    /**
     * deal date style
     * @param v
     * @returns {*}
     */
    function addZero(v) {
        var val = parseInt(v, 10);
        if (val < 0)
            return "00";
        if (val <= 9)
            val = "0" + val;
        return String(val);
    }

    /**
     * easy copy obj
     * @param source
     * @param desc
     * @private
     */
    function _extend(source, desc) {
        for (var o in source) {
            source[o] = desc[o];
        }
    }

    /**
     * interface
     */
    return {
        init: init
    };

}();

