<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item">
        <a href="${base}/user/jump?nickname=${member.nickname}">
            <i class="layui-icon">&#xe609;</i>
            我的主页
        </a>
    </li>
    <#if member.admin>
    <li class="layui-nav-item <#if nav == 'admin'>layui-this</#if>">
        <a href="${base}/member/admin#jielist">
            <i class="layui-icon">&#xe612;</i>
            社区管理
        </a>
    </li>
    </#if>
    <li class="layui-nav-item <#if nav == 'index'>layui-this</#if>">
        <a href="${base}/member/index">
            <i class="layui-icon">&#xe612;</i>
            用户中心
        </a>
    </li>
    <li class="layui-nav-item <#if nav == 'set'>layui-this</#if>">
        <a href="${base}/member/set">
            <i class="layui-icon">&#xe620;</i>
            基本设置
        </a>
    </li>
    <li class="layui-nav-item <#if nav == 'message'>layui-this</#if>">
        <a href="${base}/member/message/list">
            <i class="layui-icon">&#xe611;</i>
            我的消息
        </a>
    </li>
</ul>