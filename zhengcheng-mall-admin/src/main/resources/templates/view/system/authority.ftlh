<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>权限管理</title>
    <link rel="stylesheet" href="../../component/pear/css/pear.css"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
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
        <table id="power-table" lay-filter="power-table"></table>
    </div>
</div>

<script type="text/html" id="power-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
    <button class="pear-btn pear-btn-success pear-btn-md" lay-event="expandAll">
        <i class="layui-icon layui-icon-spread-left"></i>
        展开
    </button>
    <button class="pear-btn pear-btn-success pear-btn-md" lay-event="foldAll">
        <i class="layui-icon layui-icon-shrink-right"></i>
        折叠
    </button>
</script>

<script type="text/html" id="power-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i>
    </button>
</script>

<script type="text/html" id="power-type">
    {{#if (d.type == '0') { }}
    <span>目录</span>
    {{# }else if(d.type == '1'){ }}
    <span>菜单</span>
    {{# }else if(d.type == '2'){ }}
    <span>按钮</span>
    {{# } }}
</script>

<script type="text/html" id="power-enable">
    <input type="checkbox" name="enable" value="{{d.id}}" lay-skin="switch" lay-text="启用|禁用" lay-filter="user-enable" {{
           d.enable== true ? 'checked' : '' }} />
</script>

<script type="text/html" id="icon">
    <i class="layui-icon {{d.icon}}"></i>
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['table', 'form', 'jquery', 'treetable'], function () {
        let table = layui.table;
        let form = layui.form;
        let $ = layui.jquery;
        let treetable = layui.treetable;

        let MODULE_PATH = "operate/";

        window.render = function () {
            treetable.render({
                treeColIndex: 1,
                treeSpid: 0,
                treeIdName: 'id',
                treePidName: 'pid',
                skin: 'line',
                treeDefaultClose: true,
                toolbar: '#power-toolbar',
                elem: '#power-table',
                url: '/admin/authority/data',
                response: {
                    statusCode: 200 //规定成功的状态码，默认：0
                    , msgName: 'message' //规定状态信息的字段名称，默认：msg
                },
                page: false,
                cols: [
                    [
                        {type: 'checkbox'},
                        {field: 'name', minWidth: 200, title: '权限名称'},
                        {field: 'code', minWidth: 200, title: '权限编码'},
                        {field: 'icon', title: '图标', templet: '#icon'},
                        {field: 'type', title: '权限类型', templet: '#power-type'},
                        {field: 'enable', title: '是否可用', templet: '#power-enable'},
                        {field: 'sort', title: '排序'},
                        {title: '操作', templet: '#power-bar', width: 150, align: 'center'}
                    ]
                ]
            });
        }

        render();

        table.on('tool(power-table)', function (obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            }
        })


        table.on('toolbar(power-table)', function (obj) {
            if (obj.event === 'add') {
                window.add();
            } else if (obj.event === 'refresh') {
                window.refresh();
            } else if (obj.event === 'expandAll') {
                treetable.expandAll("#power-table");
            } else if (obj.event === 'foldAll') {
                treetable.foldAll("#power-table");
            }
        });

        window.add = function () {
            layer.open({
                type: 2,
                title: '新增',
                shade: 0.1,
                area: ['450px', '500px'],
                content: MODULE_PATH + 'add'
            });
        }

        window.edit = function (obj) {
            layer.open({
                type: 2,
                title: '修改',
                shade: 0.1,
                area: ['450px', '500px'],
                content: MODULE_PATH + 'edit.html'
            });
        }

        window.remove = function (obj) {
            layer.confirm('确定要删除该权限', {icon: 3, title: '提示'}, function (index) {
                layer.close(index);
                let loading = layer.load();
                $.ajax({
                    url: MODULE_PATH + "remove/" + obj.data['id'],
                    headers: {'satoken': layui.data('zhengchengMallAdmin').satoken},
                    dataType: 'json',
                    type: 'delete',
                    success: function (result) {
                        layer.close(loading);
                        if (result.code == 200) {
                            layer.msg(result.message, {icon: 1, time: 1000}, function () {
                                obj.del();
                            });
                        } else {
                            layer.msg(result.message, {icon: 2, time: 1000});
                        }
                    }
                })
            });
        }
    })
</script>
</body>
</html>