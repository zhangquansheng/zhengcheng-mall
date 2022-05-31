/**

 @Name: 求解板块

 */

layui.define('fly', function (exports) {

    var $ = layui.jquery;
    var layer = layui.layer;
    var util = layui.util;
    var laytpl = layui.laytpl;
    var form = layui.form;
    var fly = layui.fly;

    var gather = {}, dom = {
        jieda: $('#jieda')
        , content: $('#L_content')
        , jiedaCount: $('#jiedaCount')
    };

    //提交回答
    fly.form['/member/jie/reply'] = function (data, required) {
        var tpl = '<li>\
      <div class="detail-about detail-about-reply">\
        <a class="fly-avatar" href="/user/jump?nickname={{ layui.cache.user.username }}" target="_blank">\
          <img src="{{= d.user.avatar}}" alt="{{= d.user.username}}">\
        </a>\
        <div class="fly-detail-user">\
          <a href="/user/jump?nickname={{ layui.cache.user.username }}" target="_blank" class="fly-link">\
            <cite>{{d.user.username}}</cite>\
          </a>\
        </div>\
        <div class="detail-hits">\
          <span>刚刚</span>\
        </div>\
      </div>\
      <div class="detail-body jieda-body photos">\
        {{ d.content}}\
      </div>\
    </li>'
        data.content = fly.content(data.content);
        laytpl(tpl).render($.extend(data, {
            user: layui.cache.user
        }), function (html) {
            required[0].value = '';
            dom.jieda.find('.fly-none').remove();
            dom.jieda.append(html);

            var count = dom.jiedaCount.text() | 0;
            dom.jiedaCount.html(++count);
        });
    };

    //求解管理
    gather.jieAdmin = {
        //删求解
        del: function (div) {
            layer.confirm('确认删除该求解么？', function (index) {
                layer.close(index);
                fly.json('/member/jie/delete', {
                    id: div.data('id')
                }, function (res) {
                    if (res.code === 20000) {
                        location.href = '/';
                    } else {
                        layer.msg(res.errorMsg);
                    }
                });
            });
        }

        //设置置顶、状态
        , set: function (div) {
            var othis = $(this);
            fly.json('/member/jie/set', {
                id: div.data('id')
                , rank: othis.attr('rank')
                , field: othis.attr('field')
            }, function (res) {
                if (res.code === 20000) {
                    location.reload();
                }
            });
        }

        //收藏
        , collect: function (div) {
            var othis = $(this), type = othis.data('type');
            fly.json('/member/favorite/' + type, {
                cid: div.data('id')
            }, function (res) {
                if (type === 'add') {
                    othis.data('type', 'remove').html('取消收藏').addClass('layui-btn-danger');
                } else if (type === 'remove') {
                    othis.data('type', 'add').html('收藏').removeClass('layui-btn-danger');
                }
            });
        }
    };

    $('body').on('click', '.jie-admin', function () {
        var othis = $(this), type = othis.attr('type');
        gather.jieAdmin[type] && gather.jieAdmin[type].call(this, othis.parent());
    });

    //异步渲染
    var asyncRender = function () {
        var div = $('.fly-admin-box'), jieAdmin = $('#LAY_jieAdmin');
        //查询帖子是否收藏
        if (jieAdmin[0] && layui.cache.user.uid != -1) {
            fly.json('/member/favorite/find', {
                cid: div.data('id')
            }, function (res) {
                jieAdmin.append('<span class="layui-btn layui-btn-xs jie-admin ' + (res.data.collection ? 'layui-btn-danger' : '') + '" type="collect" data-type="' + (res.data.collection ? 'remove' : 'add') + '">' + (res.data.collection ? '取消收藏' : '收藏') + '</span>');
            });
        }
    }();

    //解答操作
    gather.jiedaActive = {
        zan: function (li) { //赞
            var othis = $(this), ok = othis.hasClass('zanok');
            fly.json('/member/jieda/zan', {
                ok: ok
                , id: li.data('id')
            }, function (res) {
                if (res.code === 20000) {
                    var zans = othis.find('em').html() | 0;
                    console.log(ok);
                    console.log(zans);
                    othis[ok ? 'removeClass' : 'addClass']('zanok');
                    othis.find('em').html(ok ? (--zans) : (++zans));
                } else {
                    layer.msg(res.msg, {shift: 6});
                }
            });
        }
        ,
        reply: function (li) { //回复
            var val = dom.content.val();
            var aite = '@' + li.find('.fly-detail-user cite').text().replace(/\s/g, '');
            dom.content.focus()
            if (val.indexOf(aite) !== -1) return;
            dom.content.val(aite + ' ' + val);
        }
        ,
        accept: function (li) { //采纳
            var othis = $(this);
            layer.confirm('是否采纳该回答为最佳答案？', function (index) {
                layer.close(index);
                fly.json('/member/jieda/accept', {
                    id: li.data('id')
                }, function (res) {
                    if (res.code === 20000) {
                        $('.jieda-accept').remove();
                        li.addClass('jieda-daan');
                        li.find('.detail-about').append('<i class="iconfont icon-caina" title="最佳答案"></i>');
                    } else {
                        layer.msg(res.errorMsg);
                    }
                });
            });
        }
        ,
        edit: function (li) { //编辑
            fly.json('/member/jieda/getDa', {
                id: li.data('id')
            }, function (res) {
                var data = res.data;
                layer.prompt({
                    formType: 2
                    , value: data.content
                    , maxlength: 100000
                    , title: '编辑回帖'
                    , area: ['728px', '300px']
                    , success: function (layero) {
                        fly.layEditor({
                            elem: layero.find('textarea')
                        });
                    }
                }, function (value, index) {
                    fly.json('/member/jieda/updateDa', {
                        id: li.data('id')
                        , content: value
                    }, function (res) {
                        layer.close(index);
                        li.find('.detail-body').html(fly.content(value));
                    });
                });
            });
        }
        ,
        del: function (li) { //删除
            layer.confirm('确认删除该回答么？', function (index) {
                layer.close(index);
                fly.json('/member/jieda/delete', {
                    id: li.data('id')
                }, function (res) {
                    if (res.code === 20000) {
                        var count = dom.jiedaCount.text() | 0;
                        dom.jiedaCount.html(--count);
                        li.remove();
                        //如果删除了最佳答案
                        if (li.hasClass('jieda-daan')) {
                            $('.jie-status').removeClass('jie-status-ok').text('求解中');
                        }
                    } else {
                        layer.msg(res.errorMsg);
                    }
                });
            });
        }
    };

    $('.jieda-reply span').on('click', function () {
        var othis = $(this), type = othis.attr('type');
        gather.jiedaActive[type].call(this, othis.parents('li'));
    });


    //定位分页
    if (/\/page\//.test(location.href) && !location.hash) {
        var replyTop = $('#flyReply').offset().top - 80;
        $('html,body').scrollTop(replyTop);
    }

    exports('jie', null);
});