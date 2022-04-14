function formatDate(dateStr) {
    var date = new Date(dateStr)
    const y = date.getFullYear()
    var m = date.getMonth() + 1
    m = m < 10 ? '0' + m : m
    var d = date.getDate()
    d = d < 10 ? ('0' + d) : d
    var h = date.getHours();
    var min = date.getMinutes();
    var s = date.getSeconds();
    h = h < 10 ? ('0' + h) : h
    min = min < 10 ? ('0' + min) : min
    s = s < 10 ? ('0' + s) : s
    return y + '-' + m + '-' + d + " " + h + ':' + min + ':' + s
}

function initOrderTable() {
    curUserStr = cookie("curUser");
    if (curUserStr == null || curUserStr == "") {
        location.href = "/";
        return;
    }
    curUser = JSON.parse(curUserStr);
    console.log(curUser);
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#test'
            , url: '/order/select/' + curUser.id
            , cols: [[
                {field: 'id', title: '订单编号', sort: true}
                , {field: 'sid', title: '活动编号'}
                , {field: 'userId', title: '用户编号'}
                , {field: 'name', title: '活动名'}
                , {field: 'createTime', title: '订单时间', sort: true}
            ]]
            , page: true
            , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                console.log(res);
                for (var i = 0; i < res.data.length; ++i) {
                    console.log(res.data[i])
                    res.data[i].createTime = formatDate(res.data[i].createTime);
                }
                return {
                    "code": 0, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.length, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
        });
    });
}

initOrderTable();
