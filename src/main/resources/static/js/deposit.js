String.prototype.format = function (args) {
    if (arguments.length > 0) {
        var result = this;
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        } else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] == undefined) {
                    return "";
                } else {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
        return result;
    } else {
        return this;
    }
}

function dateToString(date) {
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return (Y + M + D + h + m + s);
}

function showActivity() {
    $.ajax({
        type: "POST",
        url: "/stock/total",
        data: null,
        dataType: 'JSON',
        success: function (rdata) {
            stocks = rdata.data;
            for (i = 0; i < stocks.length; ++i) {
                stocks[i].rate *= 100;
                curt = new Date();
                if (curt > new Date(stocks[i].endTime)) {
                    stocks[i].buttonClass = "layui-btn layui-btn-disabled";
                    stocks[i].buttonName = "活动已结束";
                    stocks[i].dis = "disabled";
                } else if (curt > new Date(stocks[i].beginTime)) {
                    stocks[i].buttonClass = "layui-btn normal";
                    stocks[i].buttonName = "开始抢购";
                    stocks[i].dis = "";
                } else {
                    stocks[i].buttonClass = "layui-btn layui-btn-disabled";
                    stocks[i].buttonName = "活动未开始";
                    stocks[i].dis = "disabled";
                }
                ts = stocks[i].beginTime.split("T");
                stocks[i].beginTime = ts[0] + " " + ts[1].split(".")[0];
            }
            stocks.sort(function (a, b) {
                aid = a.buttonName == '开始抢购' ? 1 : a.buttonName == '活动未开始' ? 2 : 3;
                bid = b.buttonName == '开始抢购' ? 1 : b.buttonName == '活动未开始' ? 2 : 3;
                return aid - bid;
            });
            for (i = 0; i < stocks.length; ++i) {
                document.getElementById("stock").innerHTML += template.format(stocks[i]);
            }
            return rdata;
        },
        error: function (rdata) {
            console.log(rdata);
            return rdata;
        },
    });
}

template = '<div  class="col-lg-6 col-md-6">\n' +
    '                <div class="singleService ">\n' +
    '                    <i class="flaticon-money"></i>\n' +
    '                    <h1>{rate}%</h1>\n' +
    '                    <h4>{name}</h4>\n' +
    '<n>                 <p>{detail}</p>\n' +
    '                    <p>\n' +
    '                        每份限额{amount}元，年利率{rate}%，定存{years}年，共计{count}份。\n' +
    '                        每人限购一份，先购先得。\n' +
    '                    </p>\n' +
    '                    <p>\n' +
    '                        本活动将于{beginTime}于本页面开启，敬请期待。\n' +
    '                    </p>\n' +
    '                    <div class="layui-btn-container">\n' +
    '                        <button  type="button" style="background:#8180e0;text-align: center" class="{buttonClass}" onclick="buy({id})" {dis}>{buttonName}\n' +
    '                        </button>\n' +
    '                    </div>\n' +
    '\n' +
    '                </div>\n' +
    '            </div>'

showActivity();


function buy(id) {
    curUserStr = cookie("curUser");
    curUser = curUserStr == null || curUserStr == '' ? null : JSON.parse(curUserStr);
    if (curUser == null) {
        return;
    }
    $.ajax({
        type: "GET",
        url: "/order/createUserOrderWithMq",
        data: {userId: curUser.id, sid: id},
        dataType: 'JSON',
        success: function (rdata) {
            console.log(rdata);
            layer.open({
                type: 1
                , offset: "auto" //具体配置参考：https://www.layuiweb.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + "auto" //防止重复弹出
                , content: '<div style="padding: 20px 100px;">' + rdata.data + '</div>'
                , btn: '确认'
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        error: function (rdata) {
            console.log(rdata);
            layer.open({
                type: 1
                , offset: "auto" //具体配置参考：https://www.layuiweb.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + "auto" //防止重复弹出
                , content: '<div style="padding: 20px 100px;">' + rdata.data + '</div>'
                , btn: '确认'
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
    });
}

