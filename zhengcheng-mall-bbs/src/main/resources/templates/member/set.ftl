<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>帐号设置</title>
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

<#include "${base}/member/include/member_header.ftl"/>

<div class="layui-container fly-marginTop fly-user-main">

   <#include "${base}/member/include/member_left_tree.ftl"/>

    <div class="site-tree-mobile layui-hide">
        <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>

    <div class="site-tree-mobile layui-hide">
        <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>


    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title" id="LAY_mine">
                <li class="layui-this" lay-id="info">我的资料</li>
                <li lay-id="avatar">头像</li>
                <li lay-id="pass">密码</li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-form layui-form-pane layui-tab-item layui-show">
                    <form method="post">
                        <div class="layui-form-item">
                            <label for="L_username" class="layui-form-label">昵称</label>
                            <div class="layui-input-inline">
                                <input type="text" id="L_nickname" name="nickname" required lay-verify="required"
                                       autocomplete="off" class="layui-input" disabled value="${member.nickname}">
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input type="radio" name="gender" value="male"
                                           <#if member.gender?? && member.gender == 'male'>checked</#if> title="男">
                                    <input type="radio" name="gender" value="female"
                                           <#if member.gender?? && member.gender == 'female'>checked</#if> title="女">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label for="L_city" class="layui-form-label">城市</label>
                            <div class="layui-input-inline">
                                <input type="text" id="L_city" name="city" autocomplete="off"
                                       value="${(member.city)!}"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label for="L_sign" class="layui-form-label">签名</label>
                            <div class="layui-input-block">
                                <textarea placeholder="随便写些什么刷下存在感" id="L_sign" name="sign" autocomplete="off"
                                          class="layui-textarea" style="height: 80px;">${(member.sign)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button class="layui-btn" key="set-mine" lay-filter="user-set-mine-submit" lay-submit>确认修改
                            </button>
                        </div>
                </div>

                <div class="layui-form layui-form-pane layui-tab-item">
                    <div class="layui-form-item">
                        <div class="avatar-add">
                            <p>建议尺寸168*168，支持jpg、png、gif，最大不能超过50KB</p>
                            <button type="button" class="layui-btn upload-img">
                                <i class="layui-icon">&#xe67c;</i>上传头像
                            </button>
                            <#if member.avatar??>
                            <img src="${(member.avatar)!}">
                            </#if>
                            <span class="loading"></span>
                        </div>
                    </div>
                </div>

                <div class="layui-form layui-form-pane layui-tab-item">
                    <form>
                        <input type="hidden" name="modulus" value="${modulus}"/>
                        <input type="hidden" name="exponent" value="${exponent}"/>
                        <div class="layui-form-item">
                            <label for="L_nowpass" class="layui-form-label">当前密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="L_nowpass" name="nowpass" required
                                       lay-verify="required"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label for="L_pass" class="layui-form-label">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="L_pass" name="pass" required lay-verify="required|password"
                                       autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">4到20个字符</div>
                        </div>
                        <div class="layui-form-item">
                            <label for="L_repass" class="layui-form-label">确认密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="L_repass" name="repass" required lay-verify="required"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button class="layui-btn" key="set-mine" lay-filter="user-update-password-submit"
                                    lay-submit>确认修改
                            </button>
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

    var sex = '女';
    var gender = '${member.gender}';
    if (gender === 'male') {
        sex = '男';
    }
    layui.cache.user = {
        username: '${member.nickname}'
        , uid: ${member.id?c}
        , avatar: '${(member.avatar)!"${base}/res/images/avatar/00.jpg"}'
        , experience: ${member.kiss?c}
        , sex: sex
    };

    layui.config({
        version: "2.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly'], function () {
        var $ = layui.$
                , form = layui.form;

        form.render();

        //自定义验证规则
        form.verify({
            password: function (value) {
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

        //修改密碼
        form.on('submit(user-update-password-submit)', function (data) {
            var rsaKey = new RSAKey();
            var modulus = $("input[name='modulus']").val();
            var exponent = $("input[name='exponent']").val();
            rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
            var enNowpass = hex2b64(rsaKey.encrypt(data.field.nowpass));
            var enPass = hex2b64(rsaKey.encrypt(data.field.pass));
            $.ajax({
                url: '${base}/member/update_password'
                , type: "POST"
                , data: {
                    enNowpass: enNowpass
                    , enPass: enPass
                }
                , success: function (res) {
                    if (res.code == '20000') {
                        layer.alert('修改成功', {icon: 1}, function () {
                            location.reload();
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

        //修改个人资料
        form.on('submit(user-set-mine-submit)', function (data) {
            $.ajax({
                url: '${base}/member/update_profile'
                , type: "POST"
                , data: data.field
                , success: function (res) {
                    if (res.code == '20000') {
                        layer.msg('修改成功', {
                            icon: 1
                            , time: 1000
                            , shade: 0.1
                        }, function () {
                            location.reload();
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
    });
</script>

</body>
</html>