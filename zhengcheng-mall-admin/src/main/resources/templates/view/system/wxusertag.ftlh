<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>微信用户标签管理</title>
    <link href="../../component/pear/css/pear.css" rel="stylesheet"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-form-item layui-inline">
                    <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit lay-filter="role-query">
                        <i class="layui-icon layui-icon-search"></i>
                        查询
                    </button>
                    <button type="reset" class="pear-btn pear-btn-md">
                        <i class="layui-icon layui-icon-refresh"></i>
                        重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <table id="wxusertag-table" lay-filter="wxusertag-table"></table>
    </div>
</div>

<script type="text/html" id="wxusertag-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
</script>

<script type="text/html" id="role-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i>
    </button>
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['table', 'form', 'jquery', 'common'], function () {
        let table = layui.table;
        let form = layui.form;
        let $ = layui.jquery;
        let common = layui.common;

        let MODULE_PATH = "operate/";

        let cols = [
            [
                {title: '名称', field: 'name', align: 'center'},
                {title: '操作', toolbar: '#role-bar', align: 'center'}
            ]
        ]

        table.render({
            elem: '#wxusertag-table',
            url: '/admin/wxusertag/list',
            page: false,
            cols: cols,
            skin: 'line',
            toolbar: '#wxusertag-toolbar',
            method: 'post', //如果无需自定义HTTP类型，可不加该参数
            contentType: 'application/json',
            defaultToolbar: [{
                title: '刷新',
                layEvent: 'refresh',
                icon: 'layui-icon-refresh',
            }, 'filter', 'print', 'exports']
        });

        table.on('tool(wxusertag-table)', function (obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            }
        });

        table.on('toolbar(wxusertag-table)', function (obj) {
            if (obj.event === 'add') {
                window.add();
            } else if (obj.event === 'refresh') {
                window.refresh();
            }
        });

        form.on('submit(role-query)', function (data) {
            table.reload('wxusertag-table', {where: data.field})
            return false;
        });

        window.add = function () {
            layer.open({
                type: 2,
                title: '新增',
                shade: 0.1,
                area: ['500px', '400px'],
                content: 'add'
            });
        }

        window.edit = function (obj) {
            layer.open({
                type: 2,
                title: '修改',
                shade: 0.1,
                area: ['450px', '500px'],
                content: 'edit/' + obj.data['id'] + '?name=' + obj.data['name']
            });
        }

        window.remove = function (obj) {
            layer.confirm('确定要删除该用户标签吗？', {icon: 3, title: '提示'}, function (index) {
                layer.close(index);
                common.ajax({}, MODULE_PATH + "remove/" + obj.data['id'], 'delete', 'wxusertag-table');
            });
        }

        window.refresh = function () {
            table.reload('wxusertag-table');
        }
    })
</script>
</body>
</html>
