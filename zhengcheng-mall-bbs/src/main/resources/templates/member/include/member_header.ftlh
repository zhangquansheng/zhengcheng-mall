<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/">
            <img src="${base}/res/images/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <a href="/"><i class="iconfont icon-jiaoliu"></i>交流</a>
            </li>
        </ul>

        <ul class="layui-nav fly-nav-user">
            <!-- 登入后的状态 -->
            <li class="layui-nav-item">
                <a class="fly-nav-avatar" href="javascript:;">
                    <cite class="layui-hide-xs">${member.nickname}</cite>
                    <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：${member.certified}"></i>
                     <#if member.vip??>
                         <i class="layui-badge fly-badge-vip layui-hide-xs">${member.vip}</i>
                     </#if>
                    <#if member.avatar??>
                    <img src="${member.avatar}">
                    </#if>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="${base}/member/set"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                    <dd><a href="${base}/member/message/list"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a>
                    </dd>
                    <dd><a href="${base}/user/jump?nickname=${member.nickname}">
                        <i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a>
                    </dd>
                    <hr style="margin: 5px 0;">
                    <dd><a href="/logout" style="text-align: center;">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>