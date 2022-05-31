<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的消息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">
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
        <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg" style="margin-top: 15px;">
            <button class="layui-btn layui-btn-danger layui-hide" id="LAY_delallmsg">清空全部消息</button>
            <div id="LAY_minemsg" style="margin-top: 10px;">
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
        version: "3.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');

</script>

</body>
</html>