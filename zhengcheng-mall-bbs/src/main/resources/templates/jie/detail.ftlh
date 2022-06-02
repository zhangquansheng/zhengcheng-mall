<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${jie.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">
</head>
<body>

<#include "${base}/include/header.ftlh"/>

<div class="layui-hide-xs">
    <#include "${base}/include/header_category.ftlh"/>
</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${(jie.title)!}</h1>
                <div class="fly-detail-info">
                    <#if jie.auditStatus == "handing">
                        <span class="layui-badge">审核中</span>
                    <#elseif jie.auditStatus == "unAdopt">
                        <span class="layui-badge">未批准</span>
                    </#if>
                    <span class="layui-badge layui-bg-green fly-detail-column">${jie.category.name}</span>
                    <#if jie.finished>
                        <span class="layui-badge" style="background-color: #5FB878;">已结</span>
                    <#else>
                        <span class="layui-badge" style="background-color: #999;">未结</span>
                    </#if>
                    <#if jie.top>
                        <span class="layui-badge layui-bg-black">置顶</span>
                    </#if>
                    <#if jie.good>
                        <span class="layui-badge layui-bg-red">精帖</span>
                    </#if>
                    <#if isAuthenticated>
                        <div class="fly-admin-box" data-id="${jie.id}">
                            <#if member.admin>
                                <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>
                                <#if jie.top>
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="top"
                                          rank="0"
                                          style="background-color:#ccc;">取消置顶</span>
                                <#else>
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="top"
                                          rank="1">置顶</span>
                                </#if>

                                <#if jie.good>
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="good"
                                          rank="0"
                                          style="background-color:#ccc;">取消加精</span>
                                <#else>
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="good"
                                          rank="1">加精</span>
                                </#if>
                            </#if>
                        </div>
                    </#if>
                    <span class="fly-list-nums">
                        <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ${jie.commentNums}</a>
                        <i class="iconfont" title="人气">&#xe60b;</i> ${jie.hits}
                    </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="${base}/user/jump?nickname=${jie.member.nickname}">
                        <img src="${jie.member.avatar}" alt="${jie.member.nickname}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="${base}/user/jump?nickname=${jie.member.nickname}" class="fly-link">
                            <cite>${jie.member.nickname}</cite>
                            <i class="iconfont icon-renzheng" title="认证信息：${jie.member.certified}"></i>
                            <#if jie.member.vip??>
                                <i class="layui-badge fly-badge-vip">${jie.member.vip}</i>
                            </#if>
                        </a>
                        <span>${jie.member.createDate?string("yyyy-MM-dd")}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="${jie.id}">
                        <span style="padding-right: 10px; color: #FF7200">悬赏：${jie.experience}飞吻</span>
                        <#if isAuthenticated>
                            <#if member.equals(jie.member)>
                                <span class="layui-btn layui-btn-xs jie-admin" type="edit">
                                <a href="${base}/member/jie/edit/${jie.id}">编辑此贴</a>
                             </span>
                            </#if>
                        </#if>
                    </div>
                </div>
                <div class="detail-body photos">
                    ${jie.content}
                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                    <#if jie.jiedas?has_content>
                        <#list jie.jiedas as jieda>
                            <li data-id="${jieda.id}" class="jieda-daan">
                                <a name="item-${jieda.id}"></a>
                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="${base}/user/jump?nickname=${jieda.member.nickname}">
                                        <img src="${jieda.member.avatar}" alt="${jieda.member.nickname}">
                                    </a>
                                    <div class="fly-detail-user">
                                        <a href="${base}/user/jump?nickname=${jieda.member.nickname}" class="fly-link">
                                            <cite>${jieda.member.nickname}</cite>
                                            <i class="iconfont icon-renzheng"
                                               title="认证信息：${jieda.member.certified}"></i>
                                            <#if jieda.member.vip??>
                                                <i class="layui-badge fly-badge-vip">${jieda.member.vip}</i>
                                            </#if>
                                        </a>
                                        <#if jieda.member.equals(jie.member)>
                                            <span>(楼主)</span>
                                        </#if>
                                        <#if jieda.member.admin>
                                            <span style="color:#5FB878">(管理员)</span>
                                        </#if>
                                        <#if jieda.member.light>
                                            <span style="color:#FF9E3F">（社区之光）</span>
                                        </#if>
                                        <#if !jieda.member.enable>
                                            <span style="color:#999">（该号已被封）</span>
                                        </#if>
                                    </div>

                                    <div class="detail-hits">
                                        <span>${jieda.createDate?string('yyyy-MM-dd')}</span>
                                    </div>
                                    <#if jieda.caina>
                                        <i class="iconfont icon-caina" title="最佳答案"></i>
                                    </#if>
                                </div>
                                <div class="detail-body jieda-body photos">
                                    ${jieda.content}
                                </div>
                                <div class="jieda-reply">
                                <span class="jieda-zan <#if isAuthenticated && jieda.zanMembers?seq_contains(member)>zanok</#if>"
                                      type="zan">
                                    <i class="iconfont icon-zan"></i>
                                    <em>${jieda.zan}</em>
                                </span>
                                    <span type="reply">
                                    <i class="iconfont icon-svgmoban53"></i>回复
                                </span>
                                    <div class="jieda-admin">
                                        <#if isAuthenticated>
                                            <#if member.getAdmin()>
                                                <span type="edit">编辑</span>
                                                <span type="del">删除</span>
                                            </#if>
                                        </#if>
                                        <#if isAuthenticated>
                                            <#if !jieda.member.equals(jie.member) && !jie.finished && !jieda.caina && member.equals(jie.member)>
                                                <span class="jieda-accept" type="accept">采纳</span>
                                            </#if>
                                        </#if>
                                    </div>
                                </div>
                            </li>
                        </#list>
                    <#else >
                        <li class="fly-none">消灭零回复</li>
                    </#if>
                </ul>

                <div class="layui-form layui-form-pane">
                    <form action="/member/jie/reply" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required"
                                          placeholder="请输入内容" class="layui-textarea fly-editor"
                                          style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="jid" value="${jie.id}">
                            <button class="layui-btn" lay-filter="*" lay-submit>提交回复</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">

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
        </div>
    </div>
</div>

<#include "${base}/include/footer.ftlh"/>

<script src="${base}/res/layui/layui.js"></script>
<script>
    layui.cache.page = 'jie';

    <#include "${base}/include/auth_cache_user.ftlh"/>

    layui.config({
        version: "3.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly', 'face'], function () {
        var $ = layui.$
            , fly = layui.fly;

        $('.detail-body').each(function () {
            var othis = $(this), html = othis.html();
            othis.html(fly.content(html));
        });

    });
</script>

</body>
</html>