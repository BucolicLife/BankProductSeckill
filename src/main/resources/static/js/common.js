function init() {
    curUserStr = cookie("curUser");
    curUser = curUserStr == null ? null : JSON.parse(curUserStr);
    if (curUser != null) {
        showUser();
    } else {
        showLogin();
    }
}

init();

function exitsUser(tel) {
    $.ajax({
        type: "POST",
        url: "/user/exist",
        data: "tel=" + tel,
        dataType: 'JSON',
        success: function (rdata) {
            console.log(rdata);
            return rdata;
        },
        error: function (rdata) {
            console.log(rdata);
            return rdata;
        },
    });
}

function showLogin() {
    if (document.getElementById("login_stauts2") == null || document.getElementById("login_stauts1") == null) {
        return;
    }
    document.getElementById("login_stauts2").style.display = "None";
    document.getElementById("login_stauts1").style.display = "block";
}

function showUser() {
    if (document.getElementById("login_stauts2") == null || document.getElementById("login_stauts1") == null) {
        return;
    }
    document.getElementById("login_stauts2").style.display = "block";
    document.getElementById("login_stauts1").style.display = "None";
    document.getElementById("userName").innerHTML = "你好，" + curUser.name;
}

function login_success(user) {
    console.log(user);
    cookie.set("curUser", JSON.stringify(user));
    location.href = "/";
}
function gotoRegistry(){
    location.href = "/registry";
}
layui.use(function () {
    var layer = layui.layer
        , form = layui.form
        , laypage = layui.laypage
        , element = layui.element
        , laydate = layui.laydate
        , util = layui.util;

    //触发事件
    util.event('test-active', {
        'test-form': function () {
            layer.open({
                type: 1
                , resize: false
                , shadeClose: true
                , area: '20%'
                , title: '登录'
                , content: ['<ul class="layui-form layui-form-pane" style="margin: 15px;">'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label">手机号</label>'
                    , '<div class="layui-input-block">'
                    , '<input class="layui-input" lay-verify="required" name="tel">'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label">密码</label>'
                    , '<div class="layui-input-block">'
                    , '<input class="layui-input" lay-verify="required" name="password">'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item" style="text-align:center;">'
                    , '<button type="submit" style="background:#8180e0;" lay-submit lay-filter="*" class="layui-btn">提交</button>'
                    , '<button  style="background:#8180e0;" onclick="gotoRegistry()"  class="layui-btn">注册</button>'
                    , '</li>'
                    , '</ul>'].join('')
                , success: function (layero, index) {
                    layero.find('.layui-layer-content').css('overflow', 'visible');
                    form.render().on('submit(*)', function (data) {
                        $.ajax({
                            type: "POST",
                            url: "/user/login",
                            data: data.field,
                            dataType: 'JSON',
                            success: function (rdata) {
                                console.log(rdata);
                                layer.msg(JSON.stringify("登录成功"), {icon: 1});
                                layer.close(index); //关闭层
                                login_success(rdata);
                            },
                            error: function (rdata) {
                                console.log(rdata);
                                layer.msg(JSON.stringify("登录失败"), {icon: 0});
                            },
                        });

                    });
                }
            });
        }
    });
});
