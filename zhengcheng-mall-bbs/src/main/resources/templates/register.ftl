<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册 - ${siteName}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">
    <script src="${base}/js/jsbn.js"></script>
    <script src="${base}/js/prng4.js"></script>
    <script src="${base}/js/rng.js"></script>
    <script src="${base}/js/rsa.js"></script>
    <script src="${base}/js/base64.js"></script>
</head>
<body>

<#include "${base}/include/header.ftl"/>

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="${base}/login/index">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post">
                            <input type="hidden" name="modulus" value="${modulus}"/>
                            <input type="hidden" name="exponent" value="${exponent}"/>
                            <input type="hidden" name="verCodeIndex" value="${verCodeIndex}"/>
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_username" name="username" required
                                           lay-verify="username" maxlength="14" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">设置后不可更改，中英文均可，最长14个英文或7个汉字</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_nickname" name="nickname" required
                                           lay-verify="required|nickname"
                                           maxlength="30" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">设置后不可更改</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="pass" required
                                           lay-verify="required|password"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">4到20个字符</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_repass" name="repass" required
                                           lay-verify="required|password"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">人类验证</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_vercode" name="vercode" required lay-verify="required"
                                           placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">
                                    <span style="color: #c00;">${vercode}</span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="user-register-submit" lay-submit>立即注册</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<#include "${base}/include/footer.ftl"/>

<script src="${base}/res/layui/layui.js"></script>
<script>
    layui.cache.page = 'user';

    <#include "${base}/include/auth_cache_user.ftl"/>

    layui.config({
        version: "3.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly'], function () {
        var $ = layui.$
                , form = layui.form;

        form.render();

        //自定义验证规则
        form.verify({
            username: function (value) {
                if (value.length < 3) {
                    return '用户名最少2个字符';
                }
                if (value.length > 14) {
                    return '用户名最多20个字符';
                }
                var checkUsername = true;
                $.ajax({
                    url: "${base}/register/check_username",
                    data: {username: value},
                    async: false,
                    success: function (result) {
                        checkUsername = result;
                    },
                    error: function () {
                        checkUsername = false;
                        return;
                    }
                });

                if (!checkUsername) {
                    return '此账号已存在或者被禁用，请更换账号';
                }
            }
            , nickname: function (value) {
                var checkNickname = true;
                $.ajax({
                    url: "${base}/register/check_nickname",
                    data: {nickname: value},
                    async: false,
                    success: function (result) {
                        checkNickname = result;
                    },
                    error: function () {
                        checkNickname = false;
                        return;
                    }
                });
                if (!checkNickname) {
                    return '此昵称已被使用！';
                }
            }
            , password: function (value) {
                var reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
                if (!reg.test(value)) {
                    return '密码必须由字母+数字+特殊字符组成！';
                }
                if (value.length < 4) {
                    return '密码最少4个字符';
                }
                if (value.length > 20) {
                    return '密码最多20个字符';
                }
                var pass = $("#L_pass").val();
                if (value != pass) {
                    return '两次输入的密码不一致！';
                }
                var repass = $("#L_repass").val();
                if (value != repass) {
                    return '两次输入的密码不一致！';
                }
            }
        });

        form.on('submit(user-register-submit)', function (data) {
            var rsaKey = new RSAKey();
            var modulus = $("input[name='modulus']").val();
            var exponent = $("input[name='exponent']").val();
            rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
            var enPassword = hex2b64(rsaKey.encrypt(data.field.pass));
            console.log(enPassword);
            $.ajax({
                url: '${base}/register/submit'
                , type: "POST"
                , data: {
                    username: data.field.username
                    , enPassword: enPassword
                    , nickname: data.field.nickname
                    , vercode: data.field.vercode
                    , verCodeIndex: data.field.verCodeIndex
                }
                , success: function (res) {
                    if (res.code == '20000') {
                        //登入成功的提示与跳转
                        layer.msg('注册成功', {
                            offset: '15px'
                            , icon: 1
                            , time: 1000
                        }, function () {
                            window.location.href = "/";
                        });
                    } else {
                        layer.alert(res.errorMsg, {icon: 2}, function () {
                            location.reload();
                        });
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    // 状态码
                    console.log(XMLHttpRequest.status);
                    // 状态
                    console.log(XMLHttpRequest.readyState);
                    // 错误信息
                    console.log(textStatus);
                    layer.alert(textStatus + ':请稍后重试！', {icon: 2}, function () {
                        location.reload();
                    });
                }
            });
            return false;
        });
    })
</script>

</body>
</html>