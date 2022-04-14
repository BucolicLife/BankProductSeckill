layui.use('layer', function () { //独立版的layer无需执行这一句
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

    //触发事件
    var active = {
        offset: function (othis) {
            console.log(othis)
            var type = othis.data('type')
                , text = othis.mess;
            layer.open({
                type: 1
                , offset: type //具体配置参考：https://www.layuiweb.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + type //防止重复弹出
                , content: '<div style="padding: 20px 100px;">' + text + '</div>'
                , btn: '确认'
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        }
    };

    $('#applyButton').on('click', function () {
        var othis = $(this), method = othis.data('method');
        console.log(othis);
        curUserStr = cookie("curUser");
        if (curUserStr == null || curUserStr == "") {
            othis.mess = "您未登录!";
            active[method] ? active[method].call(this, othis) : '';
        } else {
            curUser = JSON.parse(curUserStr);
            $.ajax({
                type: "POST",
                url: "/loan/apply/" + curUser.idcardNum,
                dataType: 'JSON',
                success: function (rdata) {
                    console.log(rdata);
                    if (rdata.status == 999) {
                        othis.mess = "由于您的逾期记录，您的贷款资质审核不通过!";
                    } else {
                        othis.mess = "申请成功";
                    }
                    active[method] ? active[method].call(this, othis) : '';
                },
                error: function (rdata) {
                    console.log(rdata);
                    if (rdata.status == 999) {
                        othis.mess = "由于您的逾期记录，您的贷款资质审核不通过!";
                    } else {
                        othis.mess = "申请成功";
                    }
                    active[method] ? active[method].call(this, othis) : '';
                },
            });
        }
    });

});