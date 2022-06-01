<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑问题</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${base}/res/layui/css/layui.css">
    <link rel="stylesheet" href="${base}/res/css/global.css">
</head>
<body>

<#include "${base}/member/include/member_header.ftl"/>

<div class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <#if member.jieAuth>
            <div class="layui-form layui-form-pane">
                <div class="layui-tab layui-tab-brief" lay-filter="user">
                    <ul class="layui-tab-title">
                        <li class="layui-this">编辑帖子</li>
                    </ul>
                    <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                        <div class="layui-tab-item layui-show">
                            <form action="${base}/member/jie/update" method="post">
                                <input type="hidden" name="id" value="${jie.id}">
                                <div class="layui-row layui-col-space15 layui-form-item">
                                    <div class="layui-col-md3">
                                        <label class="layui-form-label">所在专栏</label>
                                        <div class="layui-input-block">
                                            <select lay-verify="required" name="jieCategryId" lay-filter="column"
                                                    disabled>
                                            <#list jieCategories as category>
                                                <option value="${category.id}" <#if jie.category.id == category.id>
                                                    selected="selected" </#if> >${category.name}</option>
                                            </#list>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-col-md9">
                                        <label for="L_title" class="layui-form-label">标题</label>
                                        <div class="layui-input-block">
                                            <input type="text" id="L_title" name="title" value="${jie.title}" required
                                                   lay-verify="required"
                                                   autocomplete="off" class="layui-input" disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item layui-form-text">
                                    <div class="layui-input-block">
                                    <textarea id="L_content" name="content" required lay-verify="required"
                                              placeholder="详细描述" class="layui-textarea fly-editor"
                                              style="height: 260px;">${jie.content}</textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">悬赏飞吻</label>
                                        <div class="layui-input-inline" style="width: 190px;">
                                            <input type="text" id="L_experience" name="experience"
                                                   value="${jie.experience}" required
                                                   lay-verify="required"
                                                   autocomplete="off" class="layui-input" disabled>
                                        </div>
                                        <div class="layui-form-mid layui-word-aux">无法更改飞吻</div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <button class="layui-btn" lay-filter="*" lay-submit>立即发布</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="fly-none">没有权限</div>
        </#if>
    </div>
</div>

<#include "${base}/include/footer.ftl"/>

<script src="${base}/res/layui/layui.js"></script>
<script>

    layui.cache.page = 'jie';
    var sex = '女';
    var gender = '${member.gender}';
    if (gender === 'male') {
        sex = '男';
    }
    layui.cache.user = {
        username: '${member.nickname}'
        , uid: ${member.id?c}
        , avatar: '${member.avatar}'
        , experience: ${member.kiss?c}
        , sex: sex
    };

    layui.config({
        version: "3.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly'], function () {
        var $ = layui.$
                , fly = layui.fly
                , form = layui.form;

        form.render();

    });
</script>

</body>
</html>