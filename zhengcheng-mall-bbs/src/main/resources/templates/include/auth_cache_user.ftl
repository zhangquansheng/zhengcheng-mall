<#if isAuthenticated>
        var sex = '女';
        var gender = '${member.gender}';
        if (gender === 'male') {
            sex = '男';
        }
        layui.cache.user = {
            username: '${member.nickname}'
            , uid: ${member.id?c}
            , avatar: '${(member.avatar)!"${base}/res/images/avatar/00.jpg"}'
            , experience: ${member.kiss?c}
            , sex: sex
        };
<#else>
         layui.cache.user = {
             username: '游客'
             , uid: -1
             , avatar: '${base}/res/images/avatar/00.jpg'
             , experience: 83
             , sex: '男'
         };
</#if>