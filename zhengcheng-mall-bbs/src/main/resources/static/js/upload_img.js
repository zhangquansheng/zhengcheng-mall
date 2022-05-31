/**
 * 图片上传js
 * 具体使用请看
 uploadimg name="images" required="true" multiple="true" maxNum="6"
 */
layui.use('jquery', function () {
    var $ = layui.$;

    window.initXWBZImg = function (options) {
        var fileHtml = '<input type="file" class="xwbzImgFileBox" accept="image/*" style="display:none">';
        var imgboxHtml = '<div class="xwbzImgParentBox"></div>';
        $(".xwbzImgUrlsBox").each(function () {
            var $this = $(this);
            var isMultiple = $this.data('multiple');
            var maxNum = $this.data('maxnum');
            var $file = $(fileHtml);
            var $imgbox = $(imgboxHtml);
            $this.parent().append($file);
            $this.parent().append($imgbox);

            console.log(isMultiple);
            console.log(maxNum);
            if (isMultiple) {
                $file.prop("multiple", true);
            }
            if (maxNum) {
                $file.attr("data-maxnum", maxNum);
            }
            showImgBox($(this), isMultiple);
        });

        $(".xwbzImgFileBox").off("change").on("change", function () {
            var $this = $(this);

            var maxNum = ~~($this.siblings(".xwbzImgUrlsBox").data('maxnum'));
            var num = ~~($this.siblings(".xwbzImgUrlsBox").attr('data-num'));
            var isEdit = window['imgEditFlag'];
            var fileObjs = $this[0].files; // js 获取文件对象
            if (!isEdit && maxNum && (fileObjs.length + num) > maxNum) {
                layui.use(['layer'], function () {
                    layer.msg('最多还能选' + (maxNum - num) + '张图片', {icon: 2});
                });
                $this.val('');
                return;
            }

            if (fileObjs.length) {
                var FileController = options.url;   // 接收上传文件的后台地址
                // FormData 对象
                var form = new FormData();
                //  form.append("author", "hooyes");      // 可以增加表单数据

                for (var i = 0; i < fileObjs.length; i++) {
                    //图片不限制
                    //if(fileObjs[i].size > 512000){
                    //    layui.use(['layer'], function(){
                    //		layer.msg(fileObjs[i].name + '文件大小超出限制, 最大500kb', {icon: 2});
                    //	});
                    //    $this.val('');
                    //    return;
                    //}
                    form.append("file" + i, fileObjs[i]);       // 文件对象
                }
                // XMLHttpRequest 对象
                var xhr = new XMLHttpRequest();
                xhr.open("post", FileController, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {   //表示和服务器端的交互已经完成
                        //服务器返回的http状态码
                        //200表示“成功”，404表示“未找到”。500表示“服务器内容部错误”
                        if (xhr.status == 200) {
                            //表示服务器端的响应代码是200，正确的返回了数据
                            //纯文本数据的接受方法
                            var msg = xhr.responseText;
                            if (msg) {
                                if (JSON.parse(msg).code === 20000) {
                                    msg = JSON.parse(msg).data;
                                    if (isEdit) {
                                        editImgUrl($this, msg);
                                        return;
                                    } else {
                                        addImgUrl($this, msg);
                                    }

                                    //扩展功能，把返回的图片设置对应的标签的背景
                                    if (options.background) {
                                        $("#" + options.background).css('background', 'url(' + msg + ")  0px  0px no-repeat");
                                    }

                                } else {
                                    layer.msg(JSON.parse(msg).message.content, {icon: 2});
                                }
                            } else {
                                alert("上传失败，图片格式为JPG,JPEG,PNG,500KB之内");
                            }
                            $this.val('');
                        }
                        if (isEdit) {
                            window['imgEditFlag'] = false;
                            $this.prop("multiple", true);
                        }
                    }
                };
                // ${name}xhr.onload = function () {};
                xhr.send(form);
            }
        });
    };

    function showImgBox($d, isMultiple) {
        var $imgParent = $d.siblings(".xwbzImgParentBox");
        $imgParent.html("");
        var urlArr = $d.val() ? $d.val().split(',') : [];
        for (var i = 0; i < urlArr.length; i++) if (!urlArr[i]) urlArr.splice(i--, 1);
        $d.attr('data-num', urlArr.length);
        var hasUpBtn = false;
        var imgbase64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFgAAABLCAIAAAB7tddWAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo1Q0VBNzA0MjEyMDUxMUUzODk2Q0JFM0Q1RjE4QkExQyIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo1Q0VBNzA0MzEyMDUxMUUzODk2Q0JFM0Q1RjE4QkExQyI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjAzNDA2MkY1MTIwMzExRTM4OTZDQkUzRDVGMThCQTFDIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjAzNDA2MkY2MTIwMzExRTM4OTZDQkUzRDVGMThCQTFDIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+K6izdgAAAvpJREFUeNrsnFmPqkAQhWmX667gEp9c/v+/MkSDG+4LrvdcSYgRbw/0ALZQ9WBUJOn+uqvqHGCG3e93hUJRUoSAQBAIAkEgCASBIBAE4neRicEcII51Xb/dbnjPGOt0OqlUKok7ApN3jIKwY6DUIBAEgkAQCALho/X47TeXy8U0TcuyrtdrZKPs9/v2m8FgINYgf9QX/gTV+Xw2DCNKBJKmxmKxsAVc0kEcDgfyGq8CNp/Pa5qWy+WiHG6v13v7/XPt6Ha7Al5D3HQ1Go1sNkvtU8lkMsmtESSoCASBSFqEW/DQ0tbr9W63O51OKK6FQkFV1XQ6nSwQ0OOTyQSvjknZbDaA0mq1QCQafSFFasxmM4eCE1Do0+lUQrcSFggsPhzq20NgsVqtkpIax+MxMs+C/aXruvMxaonND75J9W5hUWWxuYAVdRfTg8EplUphGJywQPAFuBd5Dlhw/aDwwgVtCDgCdzph1QisG+dosVjkn44WYxjGC4XnvBuNRtvt9gtA2Hv47SGsZKVS4ef8eDzG4vMVCrpSgEU3xPbZbDar1erLl1AQ7XabU8xAAXvB3XffBnIHwkR2QcUYq9fr5XIZOxkTg6BEkeNLKdQF7AWPFBy1AoUmu8RG/HmE91nxM+J/ORIr07VcLvf7feCt+stAQGIBRNJtOJolDAhdj/hXGj5+u+TzIKAF+MbkK00XFta2BhDRUE0/9gv8Elogbu4TBW8+nyPhHSeuaVqtVuNQC6TzyQUCXsg0TbfyAxfIKogr9ynP1GJyYQZ57qbg7AuIRfclKZwSlDqWBQSmxM9zFALYh+fFBwJJSkNgqeFxSrAPw+EQ9QJew7Is2Sj8FgSW2nu1gylYPkKRMsRTA+4IcjA2fxsnDkLOq/IfACFP54uP1yAQBIJAEIh4gWCPkHk+GJ7AjU/fICJ+qlIghEfoDwQMtRjvyLYDRih4rsDT+bBM9tP5kuhrzN++e6SqqvCdYUb/SIO6BoEgEASCQBAIAkEgCEQg8VeAAQAB1bbO2qoeewAAAABJRU5ErkJggg==";
        if (isMultiple) {
            var maxNum = ~~($d.data('maxnum'));
            if (maxNum) {
                if (urlArr.length < maxNum) {
                    urlArr.push(imgbase64);
                    hasUpBtn = true;
                }
            } else {
                hasUpBtn = true;
            }
        } else {
            if (!urlArr.length) {
                urlArr[0] = imgbase64;
            }
            hasUpBtn = true;
        }

        if (isMultiple) {
            imgHtml = '<div><img class="xwbzImgBox" ><div><a class="imgDelBtn"><i class="layui-icon">&#xe640;</i></a><a class="imgEditBtn"><i class="layui-icon">&#xe642;</i></a></div></div>';
        } else {
            imgHtml = '<div><img class="xwbzImgBox"></div>';
        }
        for (var i in urlArr) {
            if (urlArr[i]) {
                var $img = $(imgHtml);
                $img.find("img").attr("src", urlArr[i]);
                $img.attr('data-idx', i);
                $imgParent.append($img);
            }
        }
        if (hasUpBtn) {
            $imgParent.find(".xwbzImgBox:last").on("click", imgBoxClickFunc);
        }
        if (isMultiple) {
            if (hasUpBtn) {
                $imgParent.find(".xwbzImgBox:not(:last)").parent().on("mouseenter", imgBoxMouseenterFunc);
                $imgParent.find(".xwbzImgBox:not(:last)").parent().on("mouseleave", imgBoxMouseleaveFunc);
            } else {
                $imgParent.find(".xwbzImgBox").parent().on("mouseenter", imgBoxMouseenterFunc);
                $imgParent.find(".xwbzImgBox").parent().on("mouseleave", imgBoxMouseleaveFunc);
            }
            $imgParent.find(".imgDelBtn").on("click", imgBoxDivDeleteFunc);
            $imgParent.find(".imgEditBtn").on("click", imgBoxDivEditFunc);
        }
    }

    function imgBoxMouseenterFunc() {
        var $this = $(this);
        $this.children("div").slideDown();
    }

    function imgBoxMouseleaveFunc() {
        var $this = $(this);
        $this.children("div").stop().slideUp();
    }

    function imgBoxClickFunc() {
        $(this).closest(".xwbzImgParentBox").siblings(".xwbzImgFileBox").trigger("click");
    }

    function addImgUrl($file, url) {
        var $urlBox = $file.siblings(".xwbzImgUrlsBox");
        var isMultiple = $urlBox.data('multiple');
        if (isMultiple) {
            var urls = $urlBox.val();
            var urlArr = urls ? urls.split(',') : [];
            urlArr.push(url);
            $urlBox.val(urlArr.join(','));
        } else {
            $urlBox.val(url);
        }
        showImgBox($urlBox, isMultiple);
    }

    function editImgUrl($file, url) {
        var $urlBox = $file.siblings(".xwbzImgUrlsBox");
        var idx = ~~(window['imgEditFlag'].replace('idx_', ''));
        var urls = $urlBox.val();
        var urlArr = urls ? urls.split(',') : [];
        urlArr[idx] = url;

        $urlBox.val(urlArr.join(','));
        var isMultiple = $urlBox.data('multiple');
        if (isMultiple) {
            $file.prop("multiple", true);
            delete window['imgEditFlag'];
        }
        showImgBox($urlBox, isMultiple);
    }

    function imgBoxDivDeleteFunc() {
        var $this = $(this);
        var idx = $this.parent().parent().data('idx');
        var $urlBox = $this.closest(".xwbzImgParentBox").siblings(".xwbzImgUrlsBox");
        var urls = $urlBox.val();
        var urlArr = urls ? urls.split(',') : [];
        urlArr.splice(idx, 1);
        $urlBox.val(urlArr.join(','));
        var isMultiple = $urlBox.data('multiple');
        showImgBox($urlBox, isMultiple);
    }


    function imgBoxDivEditFunc() {
        var $this = $(this);
        window['imgEditFlag'] = "idx_" + $this.parent().parent().data('idx');
        $this.closest(".xwbzImgParentBox").siblings(".xwbzImgFileBox").prop("multiple", false);
        $this.closest(".xwbzImgParentBox").siblings(".xwbzImgFileBox").trigger("click");
    }
});

