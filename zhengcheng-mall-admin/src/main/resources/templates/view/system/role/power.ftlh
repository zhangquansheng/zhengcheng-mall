<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>授权页面</title>
    <link rel="stylesheet" href="../../../component/pear/css/pear.css"/>
</head>
<body>
<form class="layui-form" action="">
    <input type="hidden" name="roleId" value="${roleId}"/>
    <div class="mainBox">
        <div class="main-container">
            <div class="main-container">
                <ul class="dtree" data-id="0" id="role-power"></ul>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit=""
                    lay-filter="role-power-save">
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
        let dtree = layui.dtree;
        let form = layui.form;
        let $ = layui.jquery;
        let common = layui.common;

        dtree.render({
            elem: "#role-power",
            method: "get",
            url: "/admin/authority/findByRoleId?roleId=${roleId}",
            dataStyle: 'layuiStyle',
            dataFormat: "list",  //配置data的风格为list
            checkbar: true,
            response: {
                statusName: "code",		//返回标识
                statusCode: 200,		//返回码
                message: "message",		//返回信息
                treeId: "id",
                parentId: "pid",
                title: "name"
            }
        });

        form.on('submit(role-power-save)', function (data) {
            let param = dtree.getCheckbarNodesParam("role-power");
            let ids = '';
            for (let i = 0; i < param.length; i++) {
                let id = param[i].nodeId;
                ids += id + ',';
            }
            ids = ids.substr(0, ids.length - 1);
            data.field.authorityIds = ids;

            layui.common.ajax.formSubmit('/admin/role/saveRoleAuthority', data.field, 'role-table');
            return false;
        });
    })
</script>
</body>
</html>
