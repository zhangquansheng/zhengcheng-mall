<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>社区管理中心</title>
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
                <li lay-id="memberlist" class="layui-this">
                    社区用户
                </li>
                <li lay-id="jielist">
                    社区帖子
                </li>
                <li lay-id="gameList">
                    草花机
                </li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item">
                    <table class="layui-hide" id="member-table" lay-filter="member-table"></table>
                </div>
                <div class="layui-tab-item">
                    <table class="layui-hide" id="jie-table" lay-filter="jie-table"></table>
                </div>
                <div class="layui-tab-item">
                    <table class="layui-hide" id="game-table" lay-filter="game-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "${base}/include/footer.ftl"/>

<script src="${base}/res/layui/layui.js"></script>

<script type="text/html" id="jieAuditStatusTpl">
    {{#  if(d.auditStatus === 'handing'){ }}
    <span style="color: red;">审核中</span>
    {{#  } else if(d.auditStatus === 'adopt') { }}
    <span style="color: green;">批准</span>
    {{#  } else if(d.auditStatus === 'unAdopt') { }}
    <span style="color:gray;">未批准</span>
    {{#  } }}
</script>

<script type="text/html" id="jie-table-bar">
    {{# if(d.auditStatus === 'handing'){  }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="adopt">通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unAdopt">下架</a>
    {{#  } else if(d.auditStatus === 'adopt') { }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unAdopt">下架</a>
    {{#  } }}
</script>

<script type="text/html" id="member-table-enable-checkbox-tpl">
    <input type="checkbox" name="enable" value="{{d.id}}" title="启用" lay-filter="member-table-enable"
           {{ d.enable ? 'checked' : '' }}>
</script>

<script type="text/html" id="member-table-admin-switch-tpl">
    <input type="checkbox" name="admin" value="{{d.id}}" lay-skin="switch" lay-text="是|否"
           lay-filter="member-table-admin" {{ d.admin ? 'checked' : '' }}>
</script>

<script type="text/html" id="member-table-jie-auth-switch-tpl">
    <input type="checkbox" name="jieAuth" value="{{d.id}}" lay-skin="switch" lay-text="是|否"
           lay-filter="member-table-jie-auth" {{ d.jieAuth ? 'checked' : '' }}>
</script>

<script type="text/html" id="member-table-reply-auth-switch-tpl">
    <input type="checkbox" name="replyAuth" value="{{d.id}}" lay-skin="switch" lay-text="是|否"
           lay-filter="member-table-reply-auth" {{ d.replyAuth ? 'checked' : '' }}>
</script>

<script type="text/html" id="member-table-light-switch-tpl">
    <input type="checkbox" name="light" value="{{d.id}}" lay-skin="switch" lay-text="是|否"
           lay-filter="member-table-light" {{ d.light ? 'checked' : '' }}>
</script>

<script type="text/html" id="jie-table-title-tpl">
    <a href="/jie/detail/{{d.id}}" target="_blank" class="layui-table-link">{{d.title}}</a>
</script>

<script type="text/html" id="game-table-name-tpl">
    <a href="/game/index/{{d.id}}" target="_blank" class="layui-table-link">{{d.name}}</a>
</script>

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
        var $ = layui.$
                , form = layui.form
                , table = layui.table;

        table.render({
            elem: '#jie-table'
            , url: '/member/jie/list'
            , response: {
                statusCode: 20000 //成功的状态码，默认：0
            }
            , page: true
            , cellMinWidth: 80
            , cols: [[
                {field: 'title', width: 280, title: '标题', templet: '#jie-table-title-tpl'}
                , {field: 'experience', title: '悬赏飞吻'}
                , {field: 'auditStatus', title: '审核状态', templet: '#jieAuditStatusTpl'}
                , {field: 'commentNums', title: '评论数'}
                , {field: 'hits', title: '点击数'}
                , {
                    title: '操作',
                    align: 'center',
                    fixed: 'right',
                    width: 180,
                    toolbar: '#jie-table-bar'
                }
            ]]
        });

        //监听工具条
        table.on('tool(jie-table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                window.open("${base}/jie/detail/" + data.id);
            } else if (obj.event === 'adopt') {
                layer.confirm('通过此帖子，立即显示在社区中?', {title: '提示'}, function (index) {
                    layer.close(index);
                    $.post("${base}/member/jie/audit", {
                        id: data.id
                        , field: "auditStatus"
                        , auditStatus: "adopt"
                    }, function (res) {
                        if (res.code == 20000) {
                            layer.msg('通过');
                        } else {
                            layer.msg(res.errorMsg);
                        }
                        table.reload('jie-table');
                    });
                });
            } else if (obj.event === 'unAdopt') {
                layer.prompt({
                    formType: 2,
                    value: '发布内容不合法',
                    title: '请输入未通过原因',
                    area: ['350px', '100px']
                }, function (value, index) {
                    layer.close(index);
                    $.post("${base}/member/jie/audit", {
                        id: data.id
                        , field: "auditStatus"
                        , auditStatus: "unAdopt"
                        , msg: value
                    }, function (res) {
                        if (res.code == 20000) {
                            layer.msg('下架帖子成功');
                        } else {
                            layer.msg(res.errorMsg);
                        }
                        table.reload('jie-table');
                    });
                });
            }
        });

        table.render({
            elem: '#member-table'
            , url: '/member/list'
            , response: {
                statusCode: 20000
            }
            , page: true
            , cellMinWidth: 80
            , cols: [[
                {field: 'username', width: 150, title: '用户名'}
                , {field: 'nickname', title: '昵称'}
                , {field: 'phone', width: 120, title: '常用手机号'}
                , {
                    field: 'enable',
                    width: 120,
                    title: '是否启用',
                    templet: '#member-table-enable-checkbox-tpl',
                    unresize: true
                }
                , {field: 'kiss', title: '飞吻', sort: true, edit: 'text'}
                , {field: 'certified', width: 120, title: '认证信息', edit: 'text'}
                , {field: 'vip', title: 'VIP', edit: 'text'}
                , {field: 'admin', title: '管理员', templet: '#member-table-admin-switch-tpl', unresize: true}
                , {field: 'jieAuth', title: '可发帖', templet: '#member-table-jie-auth-switch-tpl', unresize: true}
                , {field: 'replyAuth', title: '可回帖', templet: '#member-table-reply-auth-switch-tpl', unresize: true}
                , {field: 'light', title: '社区之光', width: 120, templet: '#member-table-light-switch-tpl', unresize: true}
            ]]
        });

        form.on('switch(member-table-admin)', function (obj) {
            updateMemberAttr(this.value, this.name, obj.elem.checked);
        });

        form.on('switch(member-table-jie-auth)', function (obj) {
            updateMemberAttr(this.value, this.name, obj.elem.checked);
        });

        form.on('switch(member-table-reply-auth)', function (obj) {
            updateMemberAttr(this.value, this.name, obj.elem.checked);
        });

        form.on('switch(member-table-light)', function (obj) {
            updateMemberAttr(this.value, this.name, obj.elem.checked);
        });

        form.on('checkbox(member-table-enable)', function (obj) {
            updateMemberAttr(this.value, this.name, obj.elem.checked);
        });

        function updateMemberAttr(id, field, checked) {
            $.post("${base}/member/update_attr", {
                id: id
                , field: field
                , value: checked
            }, function (res) {
                if (res.code == 20000) {
                    layer.msg('更新成功');
                } else {
                    layer.msg(res.errorMsg);
                    table.reload('member-table');
                }
            });
        }

        table.on('edit(member-table)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
            $.post("${base}/member/update_attr", {
                id: data.id
                , field: field
                , value: value
            }, function (res) {
                if (res.code == 20000) {
                    layer.msg('更新成功');
                } else {
                    layer.msg(res.errorMsg);
                    table.reload('member-table');
                }
            });
        });

        table.render({
            elem: '#game-table'
            , url: '/game/list'
            , response: {
                statusCode: 20000
            }
            , page: true
            , cellMinWidth: 80
            , cols: [[
                {field: 'name', width: 300, title: '游戏名称', templet: '#game-table-name-tpl'},
                {field: 'spadeNum', width: 150, title: '黑桃X3.8', sort: true},
                {field: 'heartNum', width: 150, title: '红心X3.8', sort: true},
                {field: 'clubNum', width: 150, title: '草花X4', sort: true},
                {field: 'diamondNum', width: 150, title: '方块X4', sort: true},
                {field: 'jokerNum', width: 150, title: '王X20', sort: true}
            ]]
        });

        //监听工具条
        table.on('sort(game-table)', function (obj) {
            table.reload('game-table', { //testTable是表格容器id
                initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
                , where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                    sortField: obj.field //排序字段
                    , sortType: obj.type //排序方式
                }
            });
        });

    });
</script>

</body>
</html>