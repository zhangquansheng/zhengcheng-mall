<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <!-- 样 式 文 件 -->
    <link rel="stylesheet" href="${base}/static/component/pear/css/pear.css"/>
    <link rel="stylesheet" href="${base}/static/admin/css/other/login.css"/>
</head>
<!-- 代 码 结 构 -->
<body background="${base}/static/admin/images/background.svg" style="background-size: cover;">
<form class="layui-form" action="javascript:void(0);">
    <div class="layui-form-item">
        <img class="logo" src="${base}/static/admin/images/logo.png"/>
        <div class="title">Pear Admin</div>
        <div class="desc">
            明 湖 区 最 具 影 响 力 的 设 计 规 范 之 一
        </div>
    </div>
    <div class="layui-form-item">
        <input placeholder="账 户 : admin " name="username" lay-verify="required" hover class="layui-input"/>
    </div>
    <div class="layui-form-item">
        <input placeholder="密 码 : admin " type="password" name="pass" lay-verify="required" hover class="layui-input"/>
    </div>
    <div class="layui-form-item">
        <input placeholder="验证码 : " hover lay-verify="required" name="captcha"
               class="code layui-input layui-input-inline"/>
        <img src="/admin/kaptcha/render" id="captchaImage" class="codeImage"/>
    </div>
    <div class="layui-form-item">
        <input type="checkbox" name="" title="记住密码" lay-skin="primary" checked>
    </div>
    <div class="layui-form-item">
        <button type="button" class="pear-btn pear-btn-success login" lay-submit lay-filter="login">
            登 入
        </button>
    </div>
</form>
<!-- 资 源 引 入 -->
<script src="${base}/static/component/layui/layui.js"></script>
<script src="${base}/static/component/pear/pear.js"></script>
<script src="${base}/static/admin/js/jsencrypt.min.js"></script>
<script>
    layui.use(['form', 'button', 'popup', 'jquery', 'layer'], function () {
        var form = layui.form;
        var button = layui.button;
        var popup = layui.popup;
        var layer = layui.layer;
        var $ = layui.jquery;
        let captchaPath = "/admin/kaptcha/render";

        // 登 录 提 交
        form.on('submit(login)', function (data) {
            var encrypt = new JSEncrypt();
            encrypt.setPublicKey("${publicKeyStr}");
            var enPassword = encrypt.encrypt(data.field.pass);
            $.ajax({
                type: 'POST',
                url: '${base}/login/submit',
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                data: JSON.stringify({
                    "username": data.field.username,
                    "enPassword": enPassword,
                    "captcha": data.field.captcha
                }),
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200) {
                        // 存储token
                        layui.data('zhengchengMallAdmin', {
                            key: res.data.tokenName,
                            value: res.data.tokenValue
                        });

                        button.load({
                            elem: '.login',
                            time: 1500,
                            done: function () {
                                popup.success("登录成功", function () {
                                    location.href = "${redirectUrl}";
                                });
                            }
                        });
                    } else {
                        popup.failure(res.message, function () {
                            document.getElementById("captchaImage").src = captchaPath + "?" + Math.random();
                        });
                    }
                }
            });
            return false;
        });

        $("#captchaImage").click(function () {
            document.getElementById("captchaImage").src = captchaPath + "?" + Math.random();
        })
        setInterval(function () {
            document.getElementById("captchaImage").src = captchaPath + "?" + Math.random();
        }, 900 * 1000);

        $('input').on('keydown', function (event) {
            if (event.keyCode == 13) {
                $(".login").trigger("click");
                return false
            }
        });

    })
</script>
</body>
</html>
