/**

 @Name: 用户模块

 */

layui.define(['laypage', 'fly', 'element', 'flow'], function (exports) {

    var $ = layui.jquery;
    var layer = layui.layer;
    var util = layui.util;
    var laytpl = layui.laytpl;
    var form = layui.form;
    var laypage = layui.laypage;
    var fly = layui.fly;
    var flow = layui.flow;
    var element = layui.element;
    var upload = layui.upload;

    var gather = {}, dom = {
        mine: $('#LAY_mine')
        , mineview: $('.mine-view')
        , minemsg: $('#LAY_minemsg')
        , infobtn: $('#LAY_btninfo')
    };

    //显示当前tab
    if (location.hash) {
        element.tabChange('user', location.hash.replace(/^#/, ''));
    }

    element.on('tab(user)', function () {
        var othis = $(this), layid = othis.attr('lay-id');
        if (layid) {
            location.hash = layid;
        }
    });

    //根据ip获取城市
    if ($('#L_city').val() === '') {
        $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function () {
            $('#L_city').val(remote_ip_info.city || '');
        });
    }

    //上传图片
    if ($('.upload-img')[0]) {
        layui.use('upload', function (upload) {
            var avatarAdd = $('.avatar-add');

            upload.render({
                elem: '.upload-img'
                , url: '/file/upload/'
                , size: 50
                , before: function () {
                    avatarAdd.find('.loading').show();
                }
                , done: function (res) {
                    if (res.code == 20000) {
                        $.post('/member/update_avatar', {
                            avatar: res.url
                        }, function () {
                            location.reload();
                        });
                    } else {
                        layer.msg(res.msg, {icon: 5});
                    }
                    avatarAdd.find('.loading').hide();
                }
                , error: function () {
                    avatarAdd.find('.loading').hide();
                }
            });
        });
    }

    //我的消息
    gather.minemsg = function () {
        var delAll = $('#LAY_delallmsg')
            , tpl = '{{# var len = d.rows.length;\
    if(len === 0){ }}\
      <div class="fly-none">您暂时没有最新消息</div>\
    {{# } else { }}\
      <ul class="mine-msg">\
      {{# for(var i = 0; i < len; i++){ }}\
        <li data-id="{{d.rows[i].id}}">\
          <blockquote class="layui-elem-quote">{{ d.rows[i].content}}</blockquote>\
          <p><span>{{d.rows[i].time}}</span><a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-danger fly-delete">删除</a></p>\
        </li>\
      {{# } }}\
      </ul>\
    {{# } }}'
            , delEnd = function (clear) {
            if (clear || dom.minemsg.find('.mine-msg li').length === 0) {
                dom.minemsg.html('<div class="fly-none">您暂时没有最新消息</div>');
            }
        }


        fly.json('/member/message/find', {}, function (res) {
            var html = laytpl(tpl).render(res);
            dom.minemsg.html(html);
            if (res.rows.length > 0) {
                delAll.removeClass('layui-hide');
            }
        });


        //阅读后删除
        dom.minemsg.on('click', '.mine-msg li .fly-delete', function () {
            var othis = $(this).parents('li'), id = othis.data('id');
            fly.json('/member/message/remove', {
                id: id
            }, function (res) {
                if (res.code === 20000) {
                    othis.remove();
                    delEnd();
                }
            });
        });

        //删除全部
        $('#LAY_delallmsg').on('click', function () {
            var othis = $(this);
            layer.confirm('确定清空吗？', function (index) {
                fly.json('/member/message/remove', {
                    all: true
                }, function (res) {
                    if (res.code === 20000) {
                        layer.close(index);
                        othis.addClass('layui-hide');
                        delEnd(true);
                    }
                });
            });
        });

    };

    dom.minemsg[0] && gather.minemsg();

    exports('user', null);

});