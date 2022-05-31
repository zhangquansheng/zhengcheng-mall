<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>发表新帖</title>
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
                        <li class="layui-this">发表新帖</li>
                    </ul>
                    <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                        <div class="layui-tab-item layui-show">
                            <form action="${base}/member/jie/save" method="post">
                                <div class="layui-row layui-col-space15 layui-form-item">
                                    <div class="layui-col-md3">
                                        <label class="layui-form-label">所在专栏</label>
                                        <div class="layui-input-block">
                                            <select lay-verify="required" name="jieCategryId" lay-filter="column">
                                                <option></option>
                                            <#list jieCategories as category>
                                            <option value="${category.id}">${category.name}</option>
                                            </#list>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-col-md9">
                                        <label for="L_title" class="layui-form-label">标题</label>
                                        <div class="layui-input-block">
                                            <input type="text" id="L_title" name="title" required lay-verify="required"
                                                   maxlength="300" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item layui-form-text">
                                    <div class="layui-input-block">
                                    <textarea id="L_content" name="content" required lay-verify="required"
                                              placeholder="详细描述" class="layui-textarea fly-editor"
                                              style="height: 260px;"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">悬赏飞吻</label>
                                        <div class="layui-input-inline" style="width: 190px;">
                                            <select name="experience">
                                                <#list kissList as kiss>
                                                    <option value="${kiss}">${kiss}</option>
                                                </#list>
                                            </select>
                                        </div>
                                        <div class="layui-form-mid layui-word-aux">发表后无法更改飞吻</div>
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
        , avatar: '${(member.avatar)!}'
        , experience: ${member.kiss?c}
        , sex: sex
    };

    layui.config({
        version: "3.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly', 'layer'], function () {
        var layer = layui.layer;
        <#if member.avatar??>
        <#else>
        layer.open({
            type: 1,
            title: false,
            closeBtn: false,
            area: '300px;',
            shade: 0.8,
            id: 'member-jie-add-notice',
            btn: ['去上传', '先看看'],
            btnAlign: 'c',
            moveType: 1,
            content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">发帖之前要求必须上传头像。<br><br>上传头像即可获得100经验值。<br><br>本社区所有的帖子都需要经过审核之后才能显示，审核时间在48小时之内。</div>',
            success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: '${base}/member/set'
                });
                btn.find('.layui-layer-btn1').attr({
                    href: '${base}/'
                });
            }
        });
        </#if>
    });


</script>

</body>
</html>