<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品管理</title>
    <link rel="stylesheet" href="../../component/pear/css/pear.css"/>
    <link rel="stylesheet" href="../../component/pear/css/soulTable.css" media="all"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">商品分类</label>
                    <div class="layui-input-inline">
                        <div id="productCategory"></div>
                    </div>
                </div>
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">商品名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" placeholder="" class="layui-input">
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
        <table id="product-table" lay-filter="product-table"></table>
    </div>
</div>

<script type="text/html" id="user-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add"></i>
        新增
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-md" lay-event="batchRemove">
        <i class="layui-icon layui-icon-delete"></i>
        删除
    </button>
</script>

<script type="text/html" id="user-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>
    </button>
    <button class="pear-btn pear-btn-warming pear-btn-sm" lay-event="sku"><i class="layui-icon layui-icon-list"></i>
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i>
    </button>
</script>

<script type="text/html" id="product-marketable">
    <input type="checkbox" name="marketable" value="{{d.id}}" lay-skin="switch" lay-text="上架|下架"
           lay-filter="product-marketable" {{
           d.marketable== true ? 'checked' : '' }} />
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script src="../../component/pear/xm-select.js"></script>
<script>
    layui.use(['table', 'form', 'jquery', 'common', 'soulTable'], function () {
        let table = layui.table;
        let form = layui.form;
        let $ = layui.jquery;
        let common = layui.common;
        let soulTable = layui.soulTable;

        xmSelect.render({
            el: '#productCategory',
            autoRow: true,
            cascader: {
                show: true,
                indent: 200,
            },
            height: '200px',
            data() {
                return [
                    {
                        name: '销售员', value: -1, disabled: true, children: [
                            {name: '张三1', value: 1, selected: true, children: []},
                            {name: '李四1', value: 2, selected: true},
                            {name: '王五1', value: 3, disabled: true},
                        ]
                    },
                    {
                        name: '奖品', value: -2, children: [
                            {
                                name: '奖品3', value: -3, children: [
                                    {name: '苹果3', value: 14, selected: true},
                                    {name: '香蕉3', value: 15},
                                    {name: '葡萄3', value: 16},
                                ]
                            },
                            {name: '苹果2', value: 4, selected: true, disabled: true},
                            {name: '香蕉2', value: 5},
                            {name: '葡萄2', value: 6},
                        ]
                    },
                ]
            }
        });

        let cols = [
            [{
                title: '#', width: 50, children: function (row) {
                    console.log(row)
                    return [
                        {
                            title: 'SKU'
                            , url: '/admin/product/sku/page'
                            , height: 300
                            , method: 'post' //如果无需自定义HTTP类型，可不加该参数
                            , contentType: 'application/json'
                            , where: {
                                spuId: row.id
                            }
                            , page: true
                            , cols: [[
                                {type: 'checkbox', fixed: 'left'},
                                {field: 'skuNo', title: 'SKU编号'},
                                {field: 'fullName', title: '全称'},
                                {field: 'price', title: '销售价(元)'},
                                {field: 'cost', title: '成本价(元)'},
                                {field: 'marketPrice', title: '市场价(元)'},
                                {field: 'stock', title: '库存'},
                                {field: 'allocatedStock', title: '已分配库存'},
                                {field: 'stockMemo', title: '库存备注'},
                                {field: 'enable', title: '是否启用'}
                            ]]
                            , done: function () {
                                soulTable.render(this);
                            }
                        }
                    ]
                }
            },
                {
                    type: 'checkbox'
                },
                {
                    title: '编码',
                    field: 'spuNo',
                    align: 'center'
                },
                {
                    title: '名称',
                    field: 'name',
                    align: 'center'
                },
                {
                    title: '商品分类',
                    field: 'productCategoryName',
                    align: 'center'
                },
                {
                    title: '是否上架',
                    field: 'marketable',
                    align: 'center',
                    templet: '#product-marketable'
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
            elem: '#product-table',
            url: '/admin/product/page',
            page: true,
            cols: cols,
            skin: 'line',
            toolbar: '#user-toolbar',
            method: 'post', //如果无需自定义HTTP类型，可不加该参数
            contentType: 'application/json',
            defaultToolbar: [{
                title: '刷新',
                layEvent: 'refresh',
                icon: 'layui-icon-refresh',
            }, 'filter', 'print', 'exports'],
            done: function () {
                soulTable.render(this);
            }
        });

        table.on('tool(product-table)', function (obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            } else if (obj.event === 'sku') {
                window.sku(obj);
            }
        });

        table.on('toolbar(product-table)', function (obj) {
            if (obj.event === 'add') {
                window.add();
            } else if (obj.event === 'refresh') {
                window.refresh();
            } else if (obj.event === 'batchRemove') {
                window.batchRemove(obj);
            }
        });

        form.on('submit(user-query)', function (data) {
            table.reload('product-table', {
                where: data.field
            })
            return false;
        });

        form.on('switch(product-marketable)', function (obj) {
            layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
        });

        window.add = function () {
            layer.open({
                type: 2,
                title: '新增',
                shade: 0.1,
                area: ['100%', '100%'],
                content: 'add'
            });
        }

        window.edit = function (obj) {
            layer.open({
                type: 2,
                title: '修改',
                shade: 0.1,
                area: ['500px', '400px'],
                content: 'edit'
            });
        }

        window.sku = function (obj) {
            layer.open({
                type: 2,
                title: '请设置商品属性规格',
                shade: 0.1,
                area: ['100%', '100%'],
                content: 'addSku?spuId=' + obj.data['id']
            });
        }

        window.remove = function (obj) {
            layer.confirm('确定要删除该用户', {
                icon: 3,
                title: '提示'
            }, function (index) {
                layer.close(index);
                let loading = layer.load();
                $.ajax({
                    url: MODULE_PATH + "remove/" + obj.data['userId'],
                    dataType: 'json',
                    type: 'delete',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layer.msg(result.msg, {
                                icon: 1,
                                time: 1000
                            }, function () {
                                obj.del();
                            });
                        } else {
                            layer.msg(result.msg, {
                                icon: 2,
                                time: 1000
                            });
                        }
                    }
                })
            });
        }

        window.batchRemove = function (obj) {

            var checkIds = common.checkField(obj, 'userId');

            if (checkIds === "") {
                layer.msg("未选中数据", {
                    icon: 3,
                    time: 1000
                });
                return false;
            }

            layer.confirm('确定要删除这些用户', {
                icon: 3,
                title: '提示'
            }, function (index) {
                layer.close(index);
                let loading = layer.load();
                $.ajax({
                    url: MODULE_PATH + "batchRemove/" + ids,
                    dataType: 'json',
                    type: 'delete',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layer.msg(result.msg, {
                                icon: 1,
                                time: 1000
                            }, function () {
                                table.reload('product-table');
                            });
                        } else {
                            layer.msg(result.msg, {
                                icon: 2,
                                time: 1000
                            });
                        }
                    }
                })
            });
        }

        window.refresh = function (param) {
            table.reload('product-table');
        }

    });
</script>
</body>
</html>
