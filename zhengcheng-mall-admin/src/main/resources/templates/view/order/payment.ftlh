<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>收款流水</title>
    <link rel="stylesheet" href="../../component/pear/css/pear.css"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-form-item layui-inline">
                    <label class="layui-form-label">交易流水号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tradeNo" placeholder="" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item layui-inline">
                    <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit lay-filter="payment-query">
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
        <table id="payment-table" lay-filter="payment-table"></table>
    </div>
</div>

<script type="text/html" id="payment-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>
    </button>
</script>

<script type="text/html" id="expandAll">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">支付成功时间:</label>
            <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">
                    <span style="font-size: 18px;color: #333;">{{d.successTime||""}}</span>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">到期时间:</label>
            <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">{{d.expiredTime||""}}</div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注说明:</label>
            <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">{{d.memo||""}}</div>
            </div>
        </div>
    </div>
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['table', 'form', 'jquery', 'common', 'soulTable'], function () {
        let table = layui.table;
        let form = layui.form;
        let $ = layui.jquery;
        let common = layui.common;
        let soulTable = layui.soulTable;

        let cols = [
            [{title: '#', width: 50, collapse: true, children: '#expandAll', childWidth: 'full'},
                {
                    title: '交易流水号',
                    field: 'tradeNo',
                    align: 'center',
                    width: 100
                },
                {
                    title: '类型',
                    field: 'typeName',
                    align: 'center'
                },
                {
                    title: '方式',
                    field: 'methodName',
                    align: 'center'
                },
                {
                    title: '状态',
                    field: 'statusName',
                    align: 'center'
                },
                {
                    title: '支付方式',
                    field: 'paymentMethod',
                    align: 'center'
                },
                {
                    title: '付款金额（元）',
                    field: 'amount',
                    align: 'center',
                    templet: function (d) {
                        return '￥' + d.amount / 100;
                    }
                },
                {
                    title: '创建时间',
                    field: 'createTime',
                    align: 'center'
                },
                {
                    title: '操作',
                    toolbar: '#payment-bar',
                    align: 'center',
                    width: 130
                }
            ]
        ]

        table.render({
            elem: '#payment-table',
            url: '/admin/payment/page',
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
            , done: function () {
                soulTable.render(this)
            }
        });

        table.on('tool(payment-table)', function (obj) {
            if (obj.event === 'edit') {
                window.edit(obj);
            }
        });

        table.on('toolbar(payment-table)', function (obj) {
            if (obj.event === 'refresh') {
                window.refresh();
            }
        });

        form.on('submit(payment-query)', function (data) {
            table.reload('payment-table', {
                where: data.field
            })
            return false;
        });

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
            table.reload('payment-table');
        }
    })
</script>
</body>
</html>
