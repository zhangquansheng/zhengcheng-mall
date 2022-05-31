<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登入 - ${siteName}</title>
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
                <li class="layui-this">登入</li>
                <li><a href="${base}/register/index">注册</a></li>
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
                                <button class="layui-btn" lay-filter="user-login-submit" lay-submit>立即登录</button>
                            <#--
                            <span style="padding-left:20px;">
                              <a href="forget.html">忘记密码？</a>
                            </span>
                             -->
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

        form.on('submit(user-login-submit)', function (data) {
            var rsaKey = new RSAKey();
            var modulus = $("input[name='modulus']").val();
            var exponent = $("input[name='exponent']").val();
            rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
            var enPassword = hex2b64(rsaKey.encrypt(data.field.pass));
            $.ajax({
                url: '${base}/login/submit'
                , type: "POST"
                , data: {
                    username: data.field.username
                    , enPassword: enPassword
                    , vercode: data.field.vercode
                    , verCodeIndex: data.field.verCodeIndex
                }
                , success: function (res) {
                    if (res.code == '20000') {
                        <#if redirectUrl??>
                            location.href = "${redirectUrl}";
                        <#else>
                            location.href = "${base}/";
                        </#if>
                    } else {
                        layer.alert(res.errorMsg, {icon: 2});
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
    });
</script>

</body>
</html>