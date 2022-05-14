<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>分布表单</title>
    <link rel="stylesheet" href="../../component/pear/css/pear.css"/>
</head>
<body class="pear-container">
<div class="layui-row layui-col-space10">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header">
                支付宝支付
            </div>
            <div class="layui-card-body">IJPay 让支付触手可及</div>
        </div>
    </div>
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card">
                <div class="layui-card-body" style="padding-top: 40px;">
                    <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">
                        <div carousel-item>
                            <div>
                                <form class="layui-form" action="javascript:void(0);"
                                      style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">支付金额:</label>
                                        <div class="layui-input-block">
                                            <input type="number" placeholder="请填写入款金额" value="" class="layui-input"
                                                   lay-verify="number" required>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">支付类型:</label>
                                        <div class="layui-input-block">
                                            <select lay-verify="required">
                                                <option value="4" selected>电脑网站支付</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">备注说明:</label>
                                        <div class="layui-input-block">
                                            <textarea placeholder="支付备注" value="" class="layui-textarea"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button class="pear-btn pear-btn-success" lay-submit lay-filter="formStep">
                                                &emsp;下一步&emsp;
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <form class="layui-form" action="javascript:void(0);"
                                      style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">商户订单号:</label>
                                        <div class="layui-input-block">
                                            <div class="layui-form-mid layui-word-aux">639537</div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">支付金额:</label>
                                        <div class="layui-input-block">
                                            <div class="layui-form-mid layui-word-aux">
                                                <span style="font-size: 18px;color: #333;">1800 元</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">支付类型:</label>
                                        <div class="layui-input-block">
                                            <div class="layui-form-mid layui-word-aux">H5支付</div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">备注说明:</label>
                                        <div class="layui-input-block">
                                            <div class="layui-form-mid layui-word-aux">备注说明</div>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button type="button" class="pear-btn pear-btn-success pre">上一步</button>
                                            <button class="pear-btn pear-btn-success" lay-submit lay-filter="formStep2">
                                                &emsp;确认支付
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <div style="text-align: center;margin-top: 90px;">
                                    <i class="layui-icon layui-circle"
                                       style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
                                    <div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
                                        支付成功
                                    </div>
                                    <div style="font-size: 14px;color: #666;margin-top: 20px;">预计两小时到账</div>
                                </div>
                                <div style="text-align: center;margin-top: 50px;">
                                    <button class="pear-btn pear-btn-success next">再支付一笔</button>
                                    <button class="pear-btn pear-btn-success">查看账单</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['form', 'step', 'code', 'element', 'common', 'toast'], function () {
        var $ = layui.$,
            form = layui.form,
            step = layui.step;
        let common = layui.common;
        var toast = layui.toast;

        layui.code();

        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%',
            stepWidth: '600px',
            height: '500px',
            stepItems: [{
                title: '下单'
            }, {
                title: '确认'
            }, {
                title: '完成'
            }]
        });

        function callback(result) {
            console.log('result' + JSON.stringify(result));
        }

        form.on('submit(formStep)', function (data) {
            layui.common.ajax.post('/payment/submit', data.field, function (result) {
                if (result.code === 200) {
                    step.next('#stepForm');
                } else {
                    toast.error({message: result.message});
                }
            });
            return false;
        });

        form.on('submit(formStep2)', function (data) {

            common.ajax(data.field, '/aliPay/pcPay', 'post', '');

            step.next('#stepForm');
            return false;
        });

        $('.pre').click(function () {
            step.pre('#stepForm');
            return false;
        });

        $('.next').click(function () {
            step.next('#stepForm');
            return false;
        });
    })
</script>
</body>
</html>