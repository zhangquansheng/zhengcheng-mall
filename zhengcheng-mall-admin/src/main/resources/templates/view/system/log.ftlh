<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>行为日志</title>
    <link rel="stylesheet" href="../../component/pear/css/pear.css"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-tab layui-tab-card">
            <ul class="layui-tab-title">
                <li class="layui-this">登录日志</li>
                <li>操作日志</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <table style="margin-top: 10px;" id="log-login-table" lay-filter="user-table"></table>
                </div>
                <div class="layui-tab-item">
                    <table style="margin-top: 10px;" id="log-operate-table" lay-filter="user-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/html" id="log-bar">
    {{#if (d.success == true) { }}
    <button class="pear-btn pear-btn-sm pear-btn-success" lay-event="edit"><i class="layui-icon layui-icon-ok"></i>
        成功
    </button>
    {{# }else if(d.success == false){ }}
    <button class="pear-btn pear-btn-sm pear-btn-danger" lay-event="error"><i class="layui-icon layui-icon-help"></i>
        异常
    </button>
    {{# } }}
</script>

<script type="text/html" id="log-createTime">
    {{layui.util.toDateString(d.createTime,  "yyyy-MM-dd HH:mm:ss")}}
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['table', 'form', 'jquery', 'element'], function () {
        let table = layui.table;
        let form = layui.form;
        let element = layui.element;

        let MODULE_PATH = "/system/logging/";

        let cols = [
            [
                {title: '类型', field: 'type', align: 'center'},
                {title: '子类型', field: 'subType', align: 'center'},
                {title: '业务标识', field: 'bizNo', align: 'center'},
                {title: '日志内容', field: 'action', align: 'center', width: 300},
                {title: '访问时间', field: 'createTime', templet: '#log-createTime', align: 'center'},
                {title: '操作人', field: 'operator', align: 'center'}
            ]
        ]

        table.render({
            elem: '#log-operate-table',
            url: '/admin/log/page',
            method: 'post',
            contentType: 'application/json',
            page: true,
            cols: cols,
            skin: 'line',
            toolbar: false
        });

        table.render({
            elem: '#log-login-table',
            url: '/admin/userLoginLog/page',
            method: 'post',
            contentType: 'application/json',
            page: true,
            cols: [[
                {title: '登录账号', field: 'username', align: 'center'},
                {title: '姓名', field: 'name', align: 'center'},
                {title: '操作类型', field: 'typeDesc', align: 'center'},
                {title: '服务器地址', field: 'serverIp', align: 'center'},
                {title: '登录结果', field: 'resultDesc', align: 'center'},
                {title: '登录IP地址', field: 'loginIp', align: 'center'},
                {title: '访问时间', field: 'createTime', templet: '#log-createTime', align: 'center'}
            ]],
            skin: 'line',
            toolbar: false
        });

        form.on('submit(dict-type-query)', function (data) {
            table.reload('dict-type-table', {where: data.field})
            return false;
        });

        window.error = function (obj) {
            layer.open({
                type: 1,
                title: '异常信息',
                shade: 0,
                area: ['450px', '350px'],
                content: '<div class="pear-container"><div class="layui-card"><div class="layui-card-body">' + obj.data['error'] + '</div></div></div>'
            });
        }
    })
</script>
</html>