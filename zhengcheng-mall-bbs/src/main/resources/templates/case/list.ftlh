<!DOCTYPE html>
<html style="background-color: #e2e2e2;">
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="{{ lay.base.keywords }}">
    <meta name="description" content="{{ lay.base.description }}">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>案例</title>
    <link rel="stylesheet" href="../../res/layui/css/layui.css">
    <link rel="stylesheet" href="../../res/css/global.css">
    <style>
        .header {
            border-bottom: 1px solid #404553;
            border-right: 1px solid #404553;
        }
    </style>
</head>
<body class="fly-full">

<#include "${base}/include/header.ftl"/>

<div class="fly-main" style="overflow: hidden;">
    <ul class="fly-case-list">
        <li data-id="1">
            <h2><a href="http://developer.baidu.com/vcast/" target="_blank">百度语音</a></h2>
            <p class="fly-case-desc">在线文字转语音|免费生成语音-百度广播开放平台。</p>
        </li>
        <li data-id="2">
            <h2><a href="${base}/test/basics" target="_blank">JAVA基础</a></h2>
            <p class="fly-case-desc">Java中==号与equals()方法的区别。</p>
        </li>
    </ul>
</div>

<#include "${base}/include/footer.ftl"/>

<script src="${base}/res/layui/layui.js"></script>
<script>

    <#include "${base}/include/auth_cache_user.ftl"/>

    layui.config({
        version: "3.0.0"
        , base: '${base}/res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>
</body>
</html>