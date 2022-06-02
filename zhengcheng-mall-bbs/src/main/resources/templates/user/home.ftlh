<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">
</head>
<body style="margin-top: 65px;">

<#include "${base}/include/header.ftl"/>

<div class="fly-home fly-panel" style="background-image: url();">
    <#if user.avatar??>
        <img src="${user.avatar}" alt="${user.nickname}">
    </#if>
    <#if user.renzheng??>
        <i class="iconfont icon-renzheng" title="${siteName}认证"></i>
    </#if>
    <h1>
        ${user.nickname}
        <#if user.gender??>
            <#if user.gender == 'male'>
                <i class="iconfont icon-nan"></i>
            <#elseif user.gender == 'female'>
                <i class="iconfont icon-nv"></i>
            </#if>
        </#if>
        <#if user.vip??>
            <i class="layui-badge fly-badge-vip">${user.vip}</i>
        </#if>
        <#if user.admin>
            <span style="color:#c00;">（管理员）</span>
        </#if>
        <#if user.light>
            <span style="color:#5FB878;">（社区之光）</span>
        </#if>
        <#if !user.enable>
            <span>（该号已被封）</span>
        </#if>
    </h1>
    <#if user.renzheng??>
        <p style="padding: 10px 0; color: #5FB878;">认证信息：${user.renzheng}</p>
    </#if>
    <p class="fly-home-info">
        <i class="iconfont icon-kiss" title="飞吻"></i><span style="color: #FF7200;">${user.kiss} 飞吻</span>
        <i class="iconfont icon-shijian"></i><span>${user.createDate?string("yyyy-MM-dd")} 加入</span>
        <i class="iconfont icon-chengshi"></i><span>来自${(user.city)!}</span>
    </p>

    <p class="fly-home-sign">（${(user.slogn)!"这个人懒得留下签名"}）</p>

</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6 fly-home-jie">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.nickname} 最近的发帖</h3>
                <ul class="jie-row">
                    <#if jiePage.content?has_content>
                        <#list jiePage.content as jie>
                            <li>
                                <#if jie.good>
                                    <span class="fly-jing">精</span>
                                </#if>
                                <a href="${base}/jie/detail/${jie.id}" class="jie-title">${jie.title}</a>
                                <i>${jie.createTime?string("yyyy/MM/dd HH:mm")}</i>
                                <em class="layui-hide-xs">${jie.hits}阅/${jie.commentNums}答</em>
                            </li>
                        </#list>
                    <#else >
                        <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;">
                            <i style="font-size:14px;">没有发表任何帖子</i>
                        </div>
                    </#if>
                </ul>
            </div>
        </div>

        <div class="layui-col-md6 fly-home-da">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${user.nickname} 最近的回帖</h3>
                <ul class="home-jieda">
                    <#if jiedaPage.content?has_content>
                        <#list jiedaPage.content as jieda>
                            <li>
                                <p>
                                    <span>${jieda.createDate?string("yyyy/MM/dd HH:mm")}</span>
                                    在<a href="${base}/jie/detail/${jieda.jie.id}" target="_blank">${jieda.jie.title}</a>中回答：
                                </p>
                                <div class="home-dacontent">
                                    ${jieda.content}
                                </div>
                            </li>
                        </#list>
                    <#else>
                        <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;">
                            <span>没有回答任何回帖</span>
                        </div>
                    </#if>
                </ul>
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
    }).use('fly');
</script>

</body>
</html>