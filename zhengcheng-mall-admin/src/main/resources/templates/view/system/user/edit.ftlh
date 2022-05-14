<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改页面</title>
    <link rel="stylesheet" href="../../../component/pear/css/pear.css"/>
</head>
<body>
<form class="layui-form" action="">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="hidden" name="source" value="${source}"/>
    <div class="mainBox">
        <div class="main-container">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" value="${user.name}" name="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入姓名"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" value="${user.email}" name="email" autocomplete="off" placeholder="请输入邮箱"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" autocomplete="off" placeholder="请输入密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input type="text" name="mobile" value="${user.mobile}" lay-verify="required" autocomplete="off"
                           placeholder="请输入电话"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <#list roles as role>
                        <input lay-skin="primary" value="${role.id}" title="${role.name}" name="roleIds"
                               type="checkbox" <#if user.roleCodes?seq_contains(role.code)> checked </#if>>
                    </#list>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit=""
                    lay-filter="user-update">
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

        form.on('submit(user-update)', function (data) {
            var roleIds = [];
            $('input[type=checkbox]:checked').each(function () {
                roleIds.push($(this).val());
            })
            data.field.roleIds = roleIds;

            layui.common.ajax.formSubmit('/admin/user/update', data.field, 'user-table');
            return false;
        });
    })
</script>
<script>
</script>
</body>
</html>
