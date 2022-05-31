<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户中心</title>
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
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title" id="LAY_mine">
                <li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖（<span>${member.jies?size}</span>）</li>
                <li data-type="collection" lay-id="collection">
                    我收藏的帖（<span>${member.favoriteJies?size}</span>）
                </li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <table class="layui-hide" id="member-jie-table" lay-filter="member-jie-table"></table>
                </div>
                <div class="layui-tab-item">
                    <table class="layui-hide" id="member-favorite-jie-table"
                           lay-filter="member-favorite-jie-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "${base}/include/footer.ftl"/>

<script type="text/html" id="jie-table-title-tpl">
    <a href="/jie/detail/{{d.id}}" target="_blank" class="layui-table-link">{{d.title}}</a>
</script>

<script type="text/html" id="jie-table-toolbar-tpl">
    <a class="layui-btn layui-btn-xs" href="/member/jie/edit/{{d.id}}" target="_blank">编辑</a>
</script>

<script type="text/html" id="jieAuditStatusTpl">
    {{#  if(d.auditStatus === 'handing'){ }}
    <span style="color: red;">审核中</span>
    {{#  } else if(d.auditStatus === 'adopt') { }}
    <span style="color: green;">正常</span>
    {{#  } else if(d.auditStatus === 'unAdopt') { }}
    <span style="color:gray;">未批准</span>
    {{#  } }}
</script>

<script type="text/html" id="jie-table-finished-tpl">
    {{#  if(d.finished){ }}
    <span>已结</span>
    {{# }else{ }}
    <span style="color: gray">未结</span>
    {{#  } }}
</script>

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
        , avatar: '${member.avatar}'
        , experience: ${member.kiss?c}
        , sex: sex
    };

    layui.config({
        version: "3.0.0"
        , base: '${base}/res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly', 'table'], function () {
        var table = layui.table;

        table.render({
            elem: '#member-jie-table'
            , url: '/member/jie/my/list'
            , response: {
                statusCode: 20000 //成功的状态码，默认：0
            }
            , page: true
            , cellMinWidth: 80
            , cols: [[
                {width: 300, title: '帖子标题', templet: '#jie-table-title-tpl'}
                , {field: 'auditStatus', title: '状态', templet: '#jieAuditStatusTpl'}
                , {field: 'finished', title: '结贴', templet: '#jie-table-finished-tpl'}
                , {
                    field: 'createDate'
                    , title: '发表时间'
                    , sort: true
                    , width: 160
                    , templet: '<div>{{ layui.util.timeAgo(d.createDate) }}</div>'
                }
                , {title: '数据', templet: '<div>{{d.commentNums}}答/{{d.hits}}阅</div>'}
                , {
                    title: '操作',
                    templet: '#jie-table-toolbar-tpl'
                }
            ]]
        });

        table.render({
            elem: '#member-favorite-jie-table'
            , url: '/member/jie/favorite/list'
            , response: {
                statusCode: 20000
            }
            , page: true
            , cols: [[
                {field: 'title', title: '帖子标题', templet: '#jie-table-title-tpl'}
            ]]
        });

    });
</script>

</body>
</html>