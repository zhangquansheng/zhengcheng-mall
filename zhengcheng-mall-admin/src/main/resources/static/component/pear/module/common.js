;
"use strict";
layui.define(["layer", "jquery", "table", 'toast'], function (exports) {
    var $ = layui.jquery;
    var table = layui.table;
    var toast = layui.toast;
    var obj = {
        checkField: function (obj, field) {
            let data = table.checkStatus(obj.config.id).data;
            if (data.length === 0) {
                return "";
            }
            let ids = "";
            for (let i = 0; i < data.length; i++) {
                ids += data[i][field] + ",";
            }
            ids = ids.substr(0, ids.length - 1);
            return ids;
        },
        resizeTable: function (tableId) {
            layui.table.resize(tableId);
        }
        , sprintf: function (str) {
            var args = arguments, flag = true, i = 1;
            str = str.replace(/%s/g, function () {
                var arg = args[i++];
                if (typeof arg === 'undefined') {
                    flag = false;
                    return '';
                }
                return arg;
            });
            return flag ? str : '';
        },
        equals: function (str, that) {
            return str == that;
        },
        equalsIgnoreCase: function (str, that) {
            return String(str).toUpperCase() === String(that).toUpperCase();
        },
        isEmpty: function (value) {
            if (typeof (value) === "undefined" || value == null || this.trim(value) == "") {
                return true;
            }
            return false;
        },
        formatNullStr: function (o) {
            if (this.isEmpty(o)) {
                return "";
            } else {
                return o;
            }
        },
        getJsonArrayValue: function (array, key, keyChecked, keyId) {
            var aa = [];
            for (var a in array) {
                var _item = array[a];
                if (_item[keyChecked]) {
                    aa.push(_item[keyId]);
                }
                if (typeof (_item[key]) != "undefined" && _item[key].length > 0) {
                    var _aa = this.getJsonArrayValue(_item[key], key, keyChecked, keyId);
                    if (_aa != null && _aa.length > 0) {
                        for (var _a in _aa) {
                            aa.push(_aa[_a]);
                        }
                    }
                }
            }
            return aa;
        },
        trim: function (value) {
            if (value == null) {
                return "";
            }
            return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
        },
        random: function (min, max) {
            return Math.floor((Math.random() * max) + min);
        },
        getCheckValues: function (name) {
            var _items = $('input:checkbox[name*="' + name + '"]:checked');
            var _itemsStr = "";
            layui.each(_items, function (i, n) {
                _itemsStr += "," + $(n).val();
            });
            if (_itemsStr.length > 0) {
                return _itemsStr.substr(1, _itemsStr.length);
            }
            return "";
        },
        joinArray: function (array, key) {
            var _itemsStr = "";
            layui.each(array, function (i, n) {
                _itemsStr += "," + n[key];
            });
            if (_itemsStr.length > 0) {
                return _itemsStr.substr(1, _itemsStr.length);
            }
            return "";
        },
        getDictLabel: function (array, value) {
            var actions = [];
            layui.each(array, function (i, n) {
                if (n.dictValue === value) {
                    actions.push(n.dictLabel);
                }
            });
            return actions.join('');
        },
        ajaxRemove: function (removeUrl, id, cb) {
            if (id == '' || id == undefined) {
                layui.layer.alert('请选择删除数据！');
                return;
            }
            var url = this.isEmpty(id) ? removeUrl : removeUrl.replace("{id}", id);
            var msg = (id.length > 0 && id.indexOf(",") > 0) ? "是否确认删除这些项？" : "是否确认删除该项？";
            layer.confirm(msg, function (index) {
                $.ajax({
                    type: "POST",
                    url: url,
                    async: true,
                    cache: false,
                    headers: {'satoken': layui.data('zhengchengMallAdmin').satoken},
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({"ids": id}),
                    success: function (res) {
                        if (typeof (cb) === "function") {
                            cb(res);
                        }
                        layer.close(index);
                    }
                });
            });
        },
        ajax: {
            /**
             * form 提交json数据并关闭当前页，刷新上一级的 table
             * @param url 提交接口
             * @param data 提交数据
             * @param table 刷新父级表
             * */
            formSubmit: function (url, data, table) {
                $.ajax({
                    url: url,
                    data: JSON.stringify(data),
                    headers: {'satoken': layui.data('zhengchengMallAdmin').satoken},
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    type: 'post',
                    success: function (result) {
                        if (result.code === 200) {
                            layer.msg(result.message, {icon: 1, time: 1000}, function () {
                                parent.layer.close(parent.layer.getFrameIndex(window.name));//关闭当前页
                                if (table !== '') {
                                    parent.layui.table.reload(table);
                                }
                            });
                        } else {
                            toast.error({message: result.message});
                        }
                    }
                });
            },
            /**
             * 提交表格的switch操作
             * @param url 提交接口
             * @param type 接口类型
             * @param data 提交数据
             * @param table 提交数据失败后刷新表
             */
            switchSubmit: function (url, type, data, table) {
                obj.ajax.submit(url, type, "json", data, function (result) {
                    if (result.code === 200) {
                        toast.info({message: result.message});
                    } else {
                        toast.error({message: result.message});
                        if (table !== '') {
                            layui.table.reload(table);
                        }
                    }
                });
            },
            /**
             * 表格数据删除
             * @param url 提交接口
             * @param data 删除的数据对象
             */
            tableRemove: function (url, data) {
                obj.ajax.submit(url, 'delete', "json", {}, function (result) {
                    if (result.code === 200) {
                        layer.msg(result.message, {icon: 1, time: 1000}, function () {
                            data.del();
                        });
                    } else {
                        toast.error({message: result.message});
                    }
                });
            },
            /**
             * 表格数据批量删除
             * @param url 提交接口
             * @param table 刷新表
             */
            tableBatchRemove: function (url, table) {
                obj.ajax.submit(url, 'delete', "json", {}, function (result) {
                    if (result.code === 200) {
                        layer.msg(result.message, {icon: 1, time: 1000}, function () {
                            layui.table.reload(table);
                        });
                    } else {
                        toast.error({message: result.message});
                    }
                });
            },
            submit: function (url, type, dataType, data, cb) {
                var config = {
                    url: url,
                    type: type,
                    dataType: dataType,
                    headers: {'satoken': layui.data('zhengchengMallAdmin').satoken},
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    beforeSend: function () {
                        //layer.loading("正在处理中，请稍后...");
                        layer.load(2);
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if (typeof (cb) === "function") {
                            cb(result);
                        }
                    }
                };
                $.ajax(config)
            },
            post: function (url, data, cb) {
                obj.ajax.submit(url, "post", "json", data, cb);
            },
            get: function (url, cb) {
                obj.ajax.submit(url, "get", "json", "", cb);
            }
        }
    };
    exports('common', obj);
});