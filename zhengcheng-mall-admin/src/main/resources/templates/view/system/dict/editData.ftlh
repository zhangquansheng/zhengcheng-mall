<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改页面</title>
    <link rel="stylesheet" href="../../../component/pear/css/pear.css"/>
</head>
<body>
<form class="layui-form" action="">
    <input type="hidden" name="id" value="${dictData.id}"/>
    <div class="mainBox">
        <div class="main-container">
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-block">
                    <select name="typeCode" lay-verify="required" lay-search>
                        <option value=""></option>
                        <#list dictTypes as dictType>
                            <option value="${dictType.code}" <#if dictType.code == dictData.typeCode> selected </#if> >${dictType.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" value="${dictData.name}" lay-verify="required" autocomplete="off"
                           placeholder="请输入名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">值</label>
                <div class="layui-input-block">
                    <input type="text" name="value" value="${dictData.value}" lay-verify="required" autocomplete="off"
                           placeholder="请输入值"
                           class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit=""
                    lay-filter="dictData-update">
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

        form.on('submit(dictData-update)', function (data) {
            $.ajax({
                url: '/admin/dict/update/data',
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
                            parent.layui.table.reload("dict-data-table");
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
</body>
</html>
