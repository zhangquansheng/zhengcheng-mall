package com.zhengcheng.mall.bbs.common.apiresult;

import java.util.HashMap;

public class ApiResultMap<K, V> extends HashMap<K, V> {
    private static final long serialVersionUID = -4828826454128841348L;

    public ApiResultMap() {
    }

    public ApiResultMap<K, V> add(K key, V value) {
        super.put(key, value);
        return this;
    }
}
