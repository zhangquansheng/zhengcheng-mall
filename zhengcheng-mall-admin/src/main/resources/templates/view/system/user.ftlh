<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <link rel="stylesheet" href="../../component/pear/css/pear.css"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" placeholder="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" placeholder="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="mobile" placeholder="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-inline">
                    <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit lay-filter="user-query">
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
        <table id="user-table" lay-filter="user-table"></table>
    </div>
</div>

<script type="text/html" id="user-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
</script>

<script type="text/html" id="user-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>
    </button>
</script>

<script type="text/html" id="user-enable">
    <input type="checkbox" name="enable" value="{{d.id}}" lay-skin="switch" lay-text="启用|禁用" lay-filter="user-enable" {{
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
            [{
                type: 'checkbox'
            },
                {
                    title: '账号',
                    field: 'username',
                    align: 'center',
                    width: 100
                },
                {
                    title: '姓名',
                    field: 'name',
                    align: 'center'
                },
                {
                    title: '电话',
                    field: 'mobile',
                    align: 'center'
                },
                {
                    title: '启用',
                    field: 'enable',
                    align: 'center',
                    templet: '#user-enable'
                },
                {
                    title: '最后登录时间',
                    field: 'lastLogin',
                    align: 'center'
                },
                {
                    title: '操作',
                    toolbar: '#user-bar',
                    align: 'center',
                    width: 130
                }
            ]
        ]

        table.render({
            elem: '#user-table',
            url: '/admin/user/page',
            page: true,
            cols: cols,
            skin: 'line',
            toolbar: '#user-toolbar',
            method: 'post',
            contentType: 'application/json',
            defaultToolbar: [{
                title: '刷新',
                layEvent: 'refresh',
                icon: 'layui-icon-refresh',
            }, 'filter', 'print', 'exports']
        });

        table.on('tool(user-table)', function (obj) {
            if (obj.event === 'edit') {
                window.edit(obj);
            }
        });

        table.on('toolbar(user-table)', function (obj) {
            if (obj.event === 'add') {
                window.add();
            } else if (obj.event === 'refresh') {
                window.refresh();
            }
        });

        form.on('submit(user-query)', function (data) {
            table.reload('user-table', {
                where: data.field
            })
            return false;
        });

        form.on('switch(user-enable)', function (obj) {
            common.ajax.switchSubmit('enable', 'post', {
                id: this.value,
                enable: obj.elem.checked
            }, 'user-table');
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
                area: ['500px', '400px'],
                content: 'edit?id=' + +obj.data['id']
            });
        }

        window.refresh = function (param) {
            table.reload('user-table');
        }
    })
</script>
</body>
</html>
