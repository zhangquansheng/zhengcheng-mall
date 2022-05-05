<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改页面</title>
    <link rel="stylesheet" href="../../../component/pear/css/pear.css"/>
</head>
<body>
<form class="layui-form" action="">
    <input type="hidden" name="id" value="${dictType.id}"/>
    <div class="mainBox">
        <div class="main-container">
            <div class="layui-form-item">
                <label class="layui-form-label">字典名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" value="${dictType.name}" lay-verify="required" autocomplete="off"
                           placeholder="请输入字典名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">字典编码</label>
                <div class="layui-input-block">
                    <input type="text" name="code" value="${dictType.code}" lay-verify="required" autocomplete="off"
                           placeholder="请输入字典编码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" value="${dictType.description}" autocomplete="off"
                           placeholder="请输入描述"
                           class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit=""
                    lay-filter="dictType-update">
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

        form.on('submit(dictType-update)', function (data) {
            common.submit(data.field, '/admin/dict/update/type', 'dict-type-table', null);
            return false;
        });
    })
</script>
</body>
</html>
