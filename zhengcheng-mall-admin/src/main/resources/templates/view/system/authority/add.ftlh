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
                    <ul id="pid-tree" class="dtree" data-id="0"></ul>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入名称"
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
    layui.use(['dtree', 'form', 'jquery', 'common'], function () {
        let form = layui.form;
        let dtree = layui.dtree;
        let $ = layui.jquery;
        let common = layui.common;

        dtree.renderSelect({
            elem: "#pid-tree",
            method: "get",
            url: "/admin/authority/findByRoleId",
            dataStyle: 'layuiStyle',
            selectTips: '请选择上级',
            dataFormat: "list",  //配置data的风格为list
            response: {
                statusName: "code",		//返回标识
                statusCode: 200,		//返回码
                message: "message",		//返回信息
                treeId: "id",
                parentId: "pid",
                title: "name"
            },
            selectInputName: {
                nodeId: "pid",
                context: "pname"
            }
        });

        form.on('submit(authority-save)', function (data) {
            var param = dtree.selectVal("pid-tree");
            data.field.pid = param.pid;
            layui.common.ajax.formSubmit('/admin/authority/save', data.field, 'power-table');
            return false;
        });
    })
</script>
</body>
</html>
