<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/">
            <img src="${base}/static/dist/images/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav-user">
            <#if currentUserId??>
                <li class="layui-nav-item">
                    <a class="fly-nav-avatar" href="${base}/logout">
                        <cite>退出</cite>
                    </a>
                </li>
            <#else>
                <li class="layui-nav-item">
                    <a class="iconfont icon-touxiang layui-hide-xs" href="${base}/login"></a>
                </li>
                <li class="layui-nav-item">
                    <a href="${base}/login">登入</a>
                </li>
                <li class="layui-nav-item">
                    <a href="${base}/reg">注册</a>
                </li>
            </#if>
        </ul>
    </div>
</div>