<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增页面</title>
    <link rel="stylesheet" href="../../../component/pear/css/pear.css"/>
</head>
<body>
<form class="layui-form" action="">
    <div class="mainBox">
        <div class="main-container">
            <div class="layui-form-item">
                <label class="layui-form-label">上级</label>
                <div class="layui-input-block">
                    <input type="text" name="pid" autocomplete="off" placeholder="请输入名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" autocomplete="off" placeholder="请输入名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">编码</label>
                <div class="layui-input-block">
                    <input type="text" name="code" autocomplete="off" placeholder="请输入编码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图标</label>
                <div class="layui-input-block">
                    <input type="text" name="icon" autocomplete="off" placeholder="请输入图标"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">URL</label>
                <div class="layui-input-block">
                    <input type="text" name="url" autocomplete="off" placeholder="请输入URL"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-block">
                    <input type="radio" name="type" value="0" title="目录" checked>
                    <input type="radio" name="type" value="1" title="菜单">
                    <input type="radio" name="type" value="2" title="按钮">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-block">
                    <input type="text" name="sort" autocomplete="off" placeholder="请输入排序"
                           class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit=""
                    lay-filter="authority-save">
                <i class="layui-icon layui-icon-ok"></i>
                提交
            </button>
            <button type="reset" class="pear-btn pear-btn-sm">
                <i class="layui-icon layui-icon-refresh"></i>
                重置
            </button>
        </div>
    </div>
</form>
<script src="../../../component/layui/layui.js"></script>
<script src="../../../component/pear/pear.js"></script>
<script>
    layui.use(['form', 'jquery'], function () {
        let form = layui.form;
        let $ = layui.jquery;

        form.on('submit(authority-save)', function (data) {
            $.ajax({
                url: '/admin/authority/save',
                data: JSON.stringify(data.field),
                dataType: 'json',
                contentType: 'application/json',
                type: 'post',
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1000
                        }, function () {
                            parent.layer.close(parent.layer.getFrameIndex(window
                                .name)); //关闭当前页
                            parent.layui.table.reload("power-table");
                        });
                    } else {
                        layer.msg(result.message, {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            })
            return false;
        });
    })
</script>
<script>
</script>
</body>
</html>
