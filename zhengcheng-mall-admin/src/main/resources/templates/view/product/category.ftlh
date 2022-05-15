<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品分类</title>
    <link rel="stylesheet" href="../../component/pear/css/pear.css"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-form-item layui-inline">
                    <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit
                            lay-filter="product-category-query">
                        <i class="layui-icon layui-icon-search"></i>
                        查询
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <table id="product-category-table" lay-filter="product-category-table"></table>
    </div>
</div>

<script type="text/html" id="product-category-toolbar">
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

<script type="text/html" id="product-category-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i>
    </button>
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['table', 'form', 'jquery', 'treetable', 'common', 'toast'], function () {
        let table = layui.table;
        let form = layui.form;
        let $ = layui.jquery;
        let treetable = layui.treetable;
        let common = layui.common;
        var toast = layui.toast;

        window.render = function () {
            treetable.render({
                treeColIndex: 1,
                treeSpid: 0,
                treeIdName: 'id',
                treePidName: 'pid',
                skin: 'line',
                toolbar: '#product-category-toolbar',
                elem: '#product-category-table',
                url: '/admin/productCategory/data',
                response: {
                    statusCode: 200 //规定成功的状态码，默认：0
                    , msgName: 'message' //规定状态信息的字段名称，默认：msg
                },
                page: false,
                cols: [
                    [
                        {field: 'title', minWidth: 200, title: '名称'},
                        {field: 'sort', title: '排序'},
                        {title: '操作', templet: '#product-category-bar', width: 150, align: 'center'}
                    ]
                ]
            });
        }

        render();

        table.on('tool(product-category-table)', function (obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            }
        })


        table.on('toolbar(product-category-table)', function (obj) {
            if (obj.event === 'add') {
                window.add();
            } else if (obj.event === 'refresh') {
                window.refresh();
            } else if (obj.event === 'expandAll') {
                treetable.expandAll("#product-category-table");
            } else if (obj.event === 'foldAll') {
                treetable.foldAll("#product-category-table");
            }
        });

        window.add = function () {
            layer.open({
                type: 2,
                title: '新增',
                shade: 0.1,
                area: ['450px', '500px'],
                content: 'add'
            });
        }

        window.edit = function (obj) {
            layer.open({
                type: 2,
                title: '修改',
                shade: 0.1,
                area: ['450px', '500px'],
                content: 'edit?id=' + obj.data['id']
            });
        }

        window.remove = function (obj) {
            layer.confirm('确定要删除该分类及其下的所有商品吗？', {icon: 3, title: '提示'}, function (index) {
                layer.close(index);
                common.ajax.tableRemove("remove/" + obj.data['id'], obj);
            });
        }

    })
</script>
</body>
</html>