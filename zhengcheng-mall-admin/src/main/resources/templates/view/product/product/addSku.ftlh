<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增页面</title>
    <link rel="stylesheet" href="../../../component/pear/css/pear.css"/>
</head>
<body>
<div class="layui-container" id="app">
    <form action="" class="layui-form fairy-form">
        <!--商品规格选项-->
        <div id="fairy-is-attribute"></div>
        <!--商品类型选择-->
        <div id="fairy-product-type"></div>
        <!--商品属性表-->
        <div id="fairy-attribute-table"></div>
        <!--商品规格表-->
        <div id="fairy-spec-table"></div>
        <!--商品库存表-->
        <div id="fairy-sku-table"></div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="../../../component/layui/layui.js"></script>
<script src="../../../component/pear/pear.js"></script>
<script>
    layui.use(['form', 'jquery', 'skuTable', 'common'], function () {
        let form = layui.form;
        let $ = layui.jquery;
        let skuTable = layui.skuTable;
        let common = layui.common;

        var skuTableObj = skuTable.render({
            isAttributeElemId: 'fairy-is-attribute',
            productTypeElemId: 'fairy-product-type',
            attributeTableElemId: 'fairy-attribute-table',
            specTableElemId: 'fairy-spec-table',
            skuTableElemId: 'fairy-sku-table',
            //商品规格模式 0单规格 1多规格
            mode: ${spu.specificationMode},
            //是否开启sku表行合并
            rowspan: true,
            //图片上传接口
            uploadUrl: '/admin/data/sku-table/upload.json',
            //获取商品类型接口
            productTypeUrl: '/admin/productCategory/data',
            //获取商品类型下的规格和属性接口
            attrSpecUrl: '/admin/specification/findAttrSpec?spuId=${spu.id}',
            //创建规格接口
            specCreateUrl: '/admin/specification/save',
            //删除规格接口
            specDeleteUrl: '/admin/specification/remove',
            //创建规格值接口
            specValueCreateUrl: '/admin/specificationValue/save',
            //删除规格值接口
            specValueDeleteUrl: '/admin/specificationValue/remove',
            //单规格SKU表配置
            singleSkuTableConfig: {
                thead: [
                    {title: '销售价(元)', icon: 'layui-icon-cols'},
                    {title: '市场价(元)', icon: 'layui-icon-cols'},
                    {title: '成本价(元)', icon: 'layui-icon-cols'},
                    {title: '库存', icon: 'layui-icon-cols'},
                    {title: '状态', icon: ''},
                ],
                tbody: [
                    {type: 'input', field: 'price', value: '0', verify: 'required|number', reqtext: '销售价不能为空'},
                    {type: 'input', field: 'marketPrice', value: '0', verify: 'required|number', reqtext: '市场价不能为空'},
                    {type: 'input', field: 'cost', value: '0', verify: 'required|number', reqtext: '成本价不能为空'},
                    {type: 'input', field: 'stock', value: '0', verify: 'required|number', reqtext: '库存不能为空'},
                    {
                        type: 'select',
                        field: 'enable',
                        option: [{key: '启用', value: '1'}, {key: '禁用', value: '0'}],
                        verify: 'required',
                        reqtext: '状态不能为空'
                    },
                ]
            },
            //多规格SKU表配置
            multipleSkuTableConfig: {
                thead: [
                    {title: '图片', icon: ''},
                    {title: '销售价(元)', icon: 'layui-icon-cols'},
                    {title: '市场价(元)', icon: 'layui-icon-cols'},
                    {title: '成本价(元)', icon: 'layui-icon-cols'},
                    {title: '库存', icon: 'layui-icon-cols'},
                    {title: '状态', icon: ''},
                ],
                tbody: [
                    {type: 'image', field: 'picture', value: '', verify: '', reqtext: ''},
                    {type: 'input', field: 'price', value: '0', verify: 'required|number', reqtext: '销售价不能为空'},
                    {type: 'input', field: 'marketPrice', value: '0', verify: 'required|number', reqtext: '市场价不能为空'},
                    {type: 'input', field: 'cost', value: '0', verify: 'required|number', reqtext: '成本价不能为空'},
                    {type: 'input', field: 'stock', value: '0', verify: 'required|number', reqtext: '库存不能为空'},
                    {
                        type: 'select',
                        field: 'enable',
                        option: [{key: '启用', value: '1'}, {key: '禁用', value: '0'}],
                        verify: '',
                        reqtext: ''
                    },
                ]
            },
            // ========================================== 回显时相关配置参数 ========================================== //
            //商品id
            productId: ${spu.id},
            //商品类型id
            productTypeId: ${spu.productCategoryId},
            //sku数据接口
            skuDataUrl: '/admin/product/skuData'
        });

        form.on('submit(submit)', function (data) {
            //获取表单数据

            common.submit(data.field, '/admin/product/saveSkuData/${spu.id}', '', null);

            return false;
        });
    })
</script>
</body>
</html>
