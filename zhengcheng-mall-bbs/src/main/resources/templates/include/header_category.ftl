<div class="fly-panel fly-column">
    <div class="layui-container">
        <ul class="layui-clear">
            <li <#if allJieCategoryId == categoryId> class="layui-hide-xs layui-this"</#if> ><a href="/">首页</a></li>
            <#list jieCategories as category>
                 <li <#if category.id == categoryId>class="layui-hide-xs layui-this"</#if>>
                     <a href="${base}/jie/${category.id}/${(jieType)!"colligate"}/${(jieOrderAttr)!"newest"}">${category.name}</a>
                 </li>
            </#list>
            <#if isAuthenticated>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>
                <!-- 用户登入后显示 -->
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a
                        href="${base}/member/index">我发表的贴</a>
                </li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a
                        href="${base}/member/index#collection">我收藏的贴</a>
                </li>
            </#if>
        </ul>

        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search"><i class="layui-icon"></i></span>
            <a href="${base}/member/jie/add" class="layui-btn">发表新帖</a>
        </div>
        <div class="layui-hide-sm layui-show-xs-block"
             style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
            <a href="${base}/member/jie/add" class="layui-btn">发表新帖</a>
        </div>
    </div>
</div>