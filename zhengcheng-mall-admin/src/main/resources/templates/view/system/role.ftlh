<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色管理</title>
    <link href="../../component/pear/css/pear.css" rel="stylesheet"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" placeholder="" class="layui-input">
                    </div>
                </div>
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
        <table id="role-table" lay-filter="role-table"></table>
    </div>
</div>

<script type="text/html" id="role-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-md" lay-event="batchRemove">
        <i class="layui-icon layui-icon-delete"></i>
        删除
    </button>
</script>

<script type="text/html" id="role-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>
    </button>
    <button class="pear-btn pear-btn-warming pear-btn-sm" lay-event="power"><i
                class="layui-icon layui-icon-vercode"></i></button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i>
    </button>
</script>

<script type="text/html" id="role-enable">
    <input type="checkbox" name="enable" value="{{d.id}}" lay-skin="switch" lay-text="是|否" lay-filter="role-enable" {{
           d.enable== true ? 'checked' : '' }} />
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['table', 'form', 'jquery', 'common'], function () {
        let table = layui.table;
        let form = layui.form;
        let $ = layui.jquery;
        let common = layui.common;

        let cols = [
            [
                {type: 'checkbox'},
                {title: '角色名', field: 'name', align: 'center', width: 100},
                {title: '编码', field: 'code', align: 'center'},
                {title: '描述', field: 'description', align: 'center'},
                {title: '是否启用', field: 'enable', align: 'center', templet: '#role-enable'},
                {title: '创建时间', field: 'createTime', align: 'center'},
                {title: '更新时间', field: 'updateTime', align: 'center'},
                {title: '操作', toolbar: '#role-bar', align: 'center', width: 195}
            ]
        ]

        table.render({
            elem: '#role-table',
            url: '/admin/role/page',
            page: true,
            cols: cols,
            skin: 'line',
            toolbar: '#role-toolbar',
            method: 'post', //如果无需自定义HTTP类型，可不加该参数
            contentType: 'application/json',
            defaultToolbar: [{
                title: '刷新',
                layEvent: 'refresh',
                icon: 'layui-icon-refresh',
            }, 'filter', 'print', 'exports']
        });

        table.on('tool(role-table)', function (obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            } else if (obj.event === 'power') {
                window.power(obj);
            }
        });

        table.on('toolbar(role-table)', function (obj) {
            if (obj.event === 'add') {
                window.add();
            } else if (obj.event === 'refresh') {
                window.refresh();
            } else if (obj.event === 'batchRemove') {
                window.batchRemove(obj);
            }
        });

        form.on('submit(role-query)', function (data) {
            table.reload('role-table', {where: data.field})
            return false;
        });

        form.on('switch(role-enable)', function (obj) {
            common.ajax.switchSubmit('enable', 'post', {
                id: this.value,
                enable: obj.elem.checked
            }, 'role-table');
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

        window.power = function (obj) {
            layer.open({
                type: 2,
                title: '授权',
                shade: 0.1,
                area: ['320px', '400px'],
                content: 'power?roleId=' + obj.data['id']
            });
        }

        window.edit = function (obj) {
            layer.open({
                type: 2,
                title: '修改',
                shade: 0.1,
                area: ['500px', '400px'],
                content: 'edit?id=' + obj.data['id']
            });
        }

        window.remove = function (obj) {
            layer.confirm('确定要删除该角色', {icon: 3, title: '提示'}, function (index) {
                layer.close(index);
                common.ajax.tableRemove("remove/" + obj.data['id'], obj);
            });
        }

        window.batchRemove = function (obj) {
            var checkIds = common.checkField(obj, 'id');
            layer.confirm('确定要删除这些角色', {icon: 3, title: '提示'}, function (index) {
                layer.close(index);
                common.ajax.tableBatchRemove("batchRemove?ids=" + checkIds, 'role-table');
            });
        }

        window.refresh = function () {
            table.reload('role-table');
        }
    })
</script>
</body>
</html>
