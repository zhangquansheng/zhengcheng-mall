<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登入</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="即时聊天,socket.io,netty-socketio">
    <meta name="description" content="基于socket.io,使用netty-socketio">
    <link rel="stylesheet" href="${base}/static/dist/css/layui.css">
    <link rel="stylesheet" href="${base}/static/dist/css/global.css">
</head>
<body>

<#include "head.ftlh">

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title">
                <li class="layui-this">登入</li>
                <li><a href="${base}/reg">注册</a></li>
            </ul>
            <div class="layui-form layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form class="layui-form">
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_username" name="username" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="pass" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="login" lay-submit>立即登录</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${base}/static/dist/layui.js"></script>
<script src="${base}/static/js/jsencrypt.min.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;
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
                    "enPassword": enPassword
                }),
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200) {
                        location.href = "${redirectUrl}";
                    } else {
                        layer.msg('用户名或者密码不正确！');
                    }
                }
            });
            return false;
        });
    });
</script>

</body>
</html>