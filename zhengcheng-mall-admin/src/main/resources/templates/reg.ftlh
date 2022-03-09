<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
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
                <li><a href="${base}/login">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
            <div class="layui-form layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form class="layui-form">
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_username" name="username" required lay-verify="username"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_nickname" class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_nickname" name="nickname" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="pass" required lay-verify="pass"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_repass" name="repass" required lay-verify="repass"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="*" lay-submit>立即注册</button>
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
        form.verify({
            username: function (value, item) { //value：表单的值、item：表单的DOM对象
                var usernameReg = false;
                $.ajax({
                    url: "${base}/reg/check-username",
                    data: {
                        "username": value
                    },
                    async: false,
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 200) {
                            usernameReg = res.data;
                        }
                    }
                });
                if (!usernameReg) {
                    return '用户名被禁用或已存在';
                }
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if (/^\d+\d+\d$/.test(value)) {
                    return '用户名不能全为数字';
                }
            }
            , pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ],
            repass: function (value, item) {
                var pass = $("#L_pass").val();
                if (value != pass) {
                    return '两次密码不一致！';
                }
            }
        });
        form.on('submit(*)', function (data) {
            var encrypt = new JSEncrypt();
            encrypt.setPublicKey("${publicKeyStr}");
            var enPassword = encrypt.encrypt(data.field.pass);
            $.ajax({
                type: 'POST',
                url: '${base}/reg/submit',
                data: {
                    "username": data.field.username,
                    "enPassword": enPassword,
                    "nickname": data.field.nickname
                },
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200) {
                        layer.alert('注册成功，快去登录体验吧！', function (index) {
                            location.href = "${base}/login";
                            layer.close(index);
                        });
                    } else {
                        layer.msg(res.msg);
                    }
                }
            });
            return false;
        });
    });
</script>

</body>
</html>