window.rootPath = (function (src) {
    src = document.scripts[document.scripts.length - 1].src;
    return src.substring(0, src.lastIndexOf("/") + 1);
})();

layui.config({
    base: rootPath + "module/",
    version: "3.9.4"
}).extend({
    admin: "admin", 	// 框架布局组件
    menu: "menu",		// 数据菜单组件
    frame: "frame", 	// 内容页面组件
    tab: "tab",			// 多选项卡组件
    echarts: "echarts", // 数据图表组件
    echartsTheme: "echartsTheme", // 数据图表主题
    hash: "hash",		// 数据加密组件
    select: "select",	// 下拉多选组件
    drawer: "drawer",	// 抽屉弹层组件
    notice: "notice",	// 消息提示组件
    step: "step",		// 分布表单组件
    tag: "tag",			// 多标签页组件
    popup: "popup",      // 弹层封装
    treetable: "treetable",   // 树状表格
    dtree: "dtree",			// 树结构
    tinymce: "tinymce/tinymce", // 编辑器
    area: "area",			// 省市级联  
    count: "count",			// 数字滚动组件
    topBar: "topBar",		// 置顶组件
    button: "button",		// 加载按钮
    design: "design",		// 表单设计
    card: "card",			// 数据卡片组件
    loading: "loading",		// 加载组件
    cropper: "cropper",		// 裁剪组件
    convert: "convert",		// 数据转换
    yaml: "yaml",			// yaml 解析组件
    context: "context",		// 上下文组件
    http: "http",			// ajax请求组件
    theme: "theme",			// 主题转换
    message: "message",     // 通知组件
    toast: "toast"          // 消息通知
}).use(['layer', 'theme'], function () {
    layui.theme.changeTheme(window, false);
});

layui.use(['table'], function () {

    /* table全局设置 */
    var token = layui.data('zhengchengMallAdmin').satoken;
    if (token) {
        layui.table.set({
            headers: {'satoken': token},
            request: {
                pageName: 'pageNo' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            response: {
                statusCode: 200 //规定成功的状态码，默认：0
                , msgName: 'message' //规定状态信息的字段名称，默认：msg
            },
            parseData: function (res) { //res 即为原始返回的数据
                console.log(JSON.stringify(res));
                if (res.code != 200) {
                    layer.msg(res.message, {icon: 2});
                }
                return {
                    "code": res.code,
                    "msg": res.message, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.records //解析数据列表
                };
            },
        });
    }

});