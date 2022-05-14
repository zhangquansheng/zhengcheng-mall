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
                <label class="layui-form-label">字典名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入字典名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">字典编码</label>
                <div class="layui-input-block">
                    <input type="text" name="code" lay-verify="required" autocomplete="off" placeholder="请输入字典编码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" autocomplete="off" placeholder="请输入描述"
                           class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit=""
                    lay-filter="dictType-save">
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
    layui.use(['form', 'jquery', 'common'], function () {
        let form = layui.form;
        let $ = layui.jquery;
        let common = layui.common;

        form.on('submit(dictType-save)', function (data) {
            layui.common.ajax.formSubmit('/admin/dict/save/type', data.field, 'dict-type-table');
            return false;
        });
    })
</script>
<script>
</script>
</body>
</html>
