<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>HIO社区</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">
</head>
<body>

<#include "${base}/include/header.ftlh"/>

<#include "${base}/include/header_category.ftlh"/>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <#if categoryId == 0>
                <div class="fly-panel">
                    <div class="fly-panel-title fly-filter">
                        <a>置顶</a>
                        <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin"
                           style="color: #FF5722;">去签到</a>
                    </div>
                    <ul class="fly-list">
                        <#list topJieList as jie>
                            <li>
                                <a href="${base}/user/jump?nickname=${jie.member.nickname}" class="fly-avatar">
                                    <img src="${jie.member.avatar}"
                                         alt="${jie.member.nickname}">
                                </a>
                                <h2>
                                    <a class="layui-badge">${jie.category.name}</a>
                                    <a href="${base}/jie/detail/${jie.id}">${jie.title}</a>
                                </h2>
                                <div class="fly-list-info">
                                    <a href="${base}/user/jump?nickname=${jie.member.nickname}" link>
                                        <cite>${jie.member.nickname}</cite>
                                        <#if jie.member.renzheng??>
                                            <i class="iconfont icon-renzheng" title="认证信息：${jie.member.renzheng}"></i>
                                        </#if>
                                        <#if jie.member.vip??>
                                            <i class="layui-badge fly-badge-vip">${jie.member.vip}</i>
                                        </#if>
                                    </a>
                                    <span>${jie.createTime}</span>

                                    <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i
                                                class="iconfont icon-kiss"></i> ${jie.experience}</span>
                                    <#if jie.finished>
                                        <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                                    </#if>
                                    <span class="fly-list-nums">
                                <i class="iconfont icon-pinglun1" title="回答"></i> ${jie.commentNums}
                              </span>
                                </div>
                                <div class="fly-list-badge">
                                    <#if jie.good>
                                        <span class="layui-badge layui-bg-red">精贴</span>
                                    </#if>
                                </div>
                            </li>
                        </#list>
                    </ul>
                </div>
            </#if>

            <div class="fly-panel" style="margin-bottom: 0;">

                <div class="fly-panel-title fly-filter">
                    <a href="${base}/jie/${categoryId}/colligate/${jieOrderAttr}"
                       <#if jieType=='colligate'>class="layui-this"</#if>>综合</a>
                    <span class="fly-mid"></span>
                    <a href="${base}/jie/${categoryId}/unsolved/${jieOrderAttr}"
                       <#if jieType=='unsolved'>class="layui-this"</#if>>未结</a>
                    <span class="fly-mid"></span>
                    <a href="${base}/jie/${categoryId}/solved/${jieOrderAttr}"
                       <#if jieType=='solved'>class="layui-this"</#if>>已结</a>
                    <span class="fly-mid"></span>
                    <a href="${base}/jie/${categoryId}/wonderful/${jieOrderAttr}"
                       <#if jieType=='wonderful'>class="layui-this"</#if>>精华</a>
                    <span class="fly-filter-right layui-hide-xs">
            <a href="${base}/jie/${categoryId}/${jieType}/newest"
               <#if jieOrderAttr=='newest'>class="layui-this"</#if>>按最新</a>
            <span class="fly-mid"></span>
            <a href="${base}/jie/${categoryId}/${jieType}/commentmost"
               <#if jieOrderAttr=='commentmost'>class="layui-this"</#if>>按热议</a>
          </span>
                </div>

                <ul class="fly-list">
                    <#list page.content as jie>
                        <li>
                            <a href="${base}/user/jump?nickname=${jie.member.nickname}" class="fly-avatar">
                                <img src="${jie.member.avatar}"
                                     alt="${jie.member.nickname}">
                            </a>
                            <h2>
                                <#if categoryId == allJieCategoryId>
                                    <a class="layui-badge">${jie.category.name}</a>
                                </#if>
                                <a href="${base}/jie/detail/${jie.id}">${jie.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="${base}/user/jump?nickname=${jie.member.nickname}" link>
                                    <cite>${jie.member.nickname}</cite>
                                    <#if jie.member.renzheng??>
                                        <i class="iconfont icon-renzheng" title="认证信息：${jie.member.renzheng}"></i>
                                    </#if>
                                    <#if jie.member.vip??>
                                        <i class="layui-badge fly-badge-vip">${jie.member.vip}</i>
                                    </#if>
                                </a>
                                <span>${jie.createTime}</span>

                                <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i
                                            class="iconfont icon-kiss"></i> ${jie.experience}</span>
                                <#if jie.finished>
                                    <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                                </#if>
                                <span class="fly-list-nums">
                                <i class="iconfont icon-pinglun1" title="回答"></i> ${jie.commentNums}
                              </span>
                            </div>
                            <div class="fly-list-badge">
                                <#if jie.good>
                                    <span class="layui-badge layui-bg-red">精帖</span>
                                </#if>
                            </div>
                        </li>
                    </#list>
                </ul>

                <div style="text-align: center">
                    <#if hasNext && (pageNum == 1) >
                        <div class="laypage-main">
                            <a href="${base}/jie/${categoryId}/${jieType}/${jieOrderAttr}?pageNum=2"
                               class="laypage-next">更多求解</a>
                        </div>
                    <#elseif (pageNum > 1)>
                        <div id="L-page"></div>
                    </#if>
                </div>

            </div>
        </div>
        <div class="layui-col-md4">
            <#if listStaticFriendLinks?has_content>
                <div class="fly-panel">
                    <h3 class="fly-panel-title">温馨通道</h3>
                    <ul class="fly-panel-main fly-list-static">
                        <#list listStaticFriendLinks as friendLink>
                            <li>
                                <a href="${friendLink.url}" target="_blank">${friendLink.name}</a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </#if>

            <div class="fly-panel fly-signin">
                <div class="fly-panel-title">
                    签到
                    <i class="fly-mid"></i>
                    <a href="javascript:;" class="fly-link" id="LAY_signinHelp">说明</a>
                    <i class="fly-mid"></i>
                    <a href="javascript:;" class="fly-link" id="LAY_signinTop">活跃榜<span class="layui-badge-dot"></span></a>
                    <#if isAuthenticated && (member.signNonstopCount >0) >
                        <span class="fly-signin-days">已连续签到<cite>${member.signNonstopCount}</cite>天</span>
                    </#if>
                </div>
                <div class="fly-panel-main fly-signin-main">
                    <#if isSign>
                        <!-- 已签到状态 -->
                        <button class="layui-btn layui-btn-disabled">今日已签到</button>
                        <span>获得了<cite>${member.signLastKiss}</cite>飞吻</span>
                    <#else>
                        <button class="layui-btn layui-btn-danger" id="LAY_signin">今日签到</button>
                        <#if isAuthenticated>
                            <span>可获得<cite>${kiss}</cite>飞吻</span>
                        </#if>
                    </#if>
                </div>
            </div>
            <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank">
                <h3 class="fly-panel-title">回贴周榜</h3>
                <dl></dl>
            </div>

            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">社区热议</dt>
                <#if weekHotPage.content?has_content>
                    <#list weekHotPage.content as jie>
                        <dd>
                            <a href="${base}/jie/detail/${jie.id}">${jie.title}</a>
                            <span><i class="iconfont icon-pinglun1"></i> ${jie.commentNums}</span>
                        </dd>
                    </#list>
                <#else>
                    <div class="fly-none">没有相关数据</div>
                </#if>
            </dl>

            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">最热新闻</dt>
                <#list newsList as news>
                    <dd>
                        <a href="${news.url}">${news.title}</a>
                    </dd>
                </#list>
            </dl>

            <div class="fly-panel">
                <div class="fly-panel-title">
                    钻级赞助商
                </div>
                <div class="fly-panel-main">
                    <#if ads?has_content>
                        <#list ads as ad>
                            <a href="${ad.url}" target="_blank" class="fly-zanzhu"
                               time-limit="${ad.beginDate?string('yyyy.MM.dd')}-${ad.endDate?string('yyyy.MM.dd')}"
                               style="background-color: #5FB878;">${ad.title}</a>
                        </#list>
                    </#if>
                </div>
            </div>

            <#if textFriendLinks?has_content>
                <div class="fly-panel fly-link">
                    <h3 class="fly-panel-title">友情链接</h3>
                    <dl class="fly-panel-main">
                        <#list textFriendLinks as friendLink>
                        <dd><a href="${friendLink.url}" target="_blank">${friendLink.name}</a>
                        <dd>
                            </#list>
                    </dl>
                </div>
            </#if>
        </div>
    </div>
</div>

<#include "${base}/include/footer.ftlh"/>

<script src="${base}/res/layui/layui.js"></script>
<script>
    layui.cache.page = '';

    <#include "${base}/include/auth_cache_user.ftlh"/>

    layui.config({
        version: "3.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly', 'laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
        var pageNum = ${pageNum};
        if (pageNum > 1) {
            laypage.render({
                elem: 'L-page'
                , count: ${page.totalElements}
                , curr: pageNum
                , last: '尾页'
                , jump: function (obj, first) {
                    //首次不执行
                    if (!first) {
                        location.href = '${base}/jie/${categoryId}/${jieType}/${jieOrderAttr}?pageNum=' + obj.curr;
                    }
                }
            });
        }
    });
</script>

</body>
</html>