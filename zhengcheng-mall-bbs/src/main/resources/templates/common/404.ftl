<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>404 - ${siteName}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">
</head>
<body>

<#include "${base}/include/header.ftl"/>

<#include "${base}/include/header_category.ftl"/>

<div class="layui-container fly-marginTop">
    <div class="fly-panel">
        <div class="fly-none">
            <h2><i class="iconfont icon-404"></i></h2>
            <p>页面或者数据被<a href="${base}/" target="_blank"> 纸飞机 </a>运到火星了，啥都看不到了…</p>
        </div>
    </div>
</div>

<#include "${base}/include/footer.ftl"/>

<script src="${base}/res/layui/layui.js"></script>
<script>
    layui.cache.page = '';

   <#include "${base}/include/auth_cache_user.ftl"/>

    layui.config({
        version: "3.0.0"
        , base: '../../res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>