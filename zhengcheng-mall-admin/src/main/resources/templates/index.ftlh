<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>即时聊天演示</title>
    <meta name="keywords" content="即时聊天,socket.io,netty-socketio">
    <meta name="description" content="基于socket.io,使用netty-socketio">
    <link rel="stylesheet" href="${base}/static/dist/css/layui.css">
    <link rel="stylesheet" href="${base}/static/dist/css/global.css">
    <style>
        html {
            background-color: #333;
        }
    </style>
</head>
<body>

<#include "head.ftlh">

<script src="${base}/static/js/socket.io-2.2.0.js"></script>
<script src="${base}/static/js/moment-2.24.0.min.js"></script>
<script src="${base}/static/dist/layui.js"></script>
<script>

    layui.use('layim', function (layim) {

        var socket = io('${socketioUrl}', {
            transports: ['polling', 'websocket']
        });

        socket.on('connect', function () {

        });

        socket.on('disconnect', function () {
            layer.open({
                content: '服务器已经断开链接，5秒后自动重新登录！'
                , btn: ['确定']
                , icon: 5
                , time: 5000
                , closeBtn: 0
                , yes: function (index) {
                    layer.close(index);
                }
                , end: function () {
                    location.href = "${base}/login";
                }
            });
        });

        socket.on('${chatMessageEvent}', function (data) {
            layim.getMessage(data);
        });

        socket.on('${chatUserStatusEvent}', function (data) {
            layim.setFriendStatus(data.id, data.status);
            if ('offline' === data.status) {
                layim.getMessage({
                    system: true
                    , id: data.id
                    , type: "friend"
                    , content: '对方已掉线'
                });
            }
        });

        //基础配置
        layim.config({

            //初始化接口
            init: {
                url: '${base}/layim/board'
                , data: {}
            }

            //查看群员接口
            , members: {
                url: '${base}/layim/group-members'
                , data: {}
            }

            //上传图片接口
            , uploadImage: {
                url: '/upload/image' //（返回的数据格式见下文）
                , type: '' //默认post
            }

            //上传文件接口
            , uploadFile: {
                url: '/upload/file' //（返回的数据格式见下文）
                , type: '' //默认post
            }

            , isAudio: true //开启聊天工具栏音频
            , isVideo: true //开启聊天工具栏视频

            //扩展工具栏
            , tool: [{
                alias: 'code'
                , title: '代码'
                , icon: '&#xe64e;'
            }]

            //,brief: true //是否简约模式（若开启则不显示主面板）

            //,title: 'WebIM' //自定义主面板最小化时的标题
            //,right: '100px' //主面板相对浏览器右侧距离
            //,minRight: '90px' //聊天面板最小化时相对浏览器右侧距离
            , initSkin: '1.jpg' //1-5 设置初始背景
            //,skin: ['aaa.jpg'] //新增皮肤
            //,isfriend: false //是否开启好友
            //,isgroup: false //是否开启群组
            //,min: true //是否始终最小化主面板，默认false
            , notice: true //是否开启桌面消息提醒，默认false
            //,voice: false //声音提醒，默认开启，声音文件为：default.mp3

            //, msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
            //, find: layui.cache.dir + 'css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
            //, chatLog: layui.cache.dir + 'css/modules/layim/html/chatlog.html' //聊天记录页面地址，若不开启，剔除该项即可

        });

        //监听在线状态的切换事件
        layim.on('online', function (data) {
            //console.log(data);
        });

        //监听签名修改
        layim.on('sign', function (value) {
            //console.log(value);
        });

        //监听自定义工具栏点击，以添加代码为例
        layim.on('tool(code)', function (insert) {
            layer.prompt({
                title: '插入代码'
                , formType: 2
                , shade: 0
            }, function (text, index) {
                layer.close(index);
                insert('[pre class=layui-code]' + text + '[/pre]'); //将内容插入到编辑器
            });
        });

        //监听layim建立就绪
        layim.on('ready', function (res) {

            //console.log(res.mine);

        });

        //监听发送消息
        layim.on('sendMessage', function (data) {
            // var To = data.to;
            // console.log(data);

            socket.emit('${chatMessageEvent}', data);

            // if (To.type === 'friend') {
            //     layim.setChatStatus('<span style="color:#FF5722;">对方正在输入。。。</span>');
            // }

        });

        //监听查看群员
        layim.on('members', function (data) {
            //console.log(data);
        });

        //监听聊天窗口的切换
        // layim.on('chatChange', function (res) {
        //     var type = res.data.type;
        //     console.log(res.data.id)
        //     if (type === 'friend') {
        //         //模拟标注好友状态
        //         //layim.setChatStatus('<span style="color:#FF5722;">在线</span>');
        //     } else if (type === 'group') {
        //         //模拟系统消息
        //         layim.getMessage({
        //             system: true
        //             , id: res.data.id
        //             , type: "group"
        //             , content: '模拟群员' + (Math.random() * 100 | 0) + '加入群聊'
        //         });
        //     }
        // });

    });
</script>
</body>
</html>