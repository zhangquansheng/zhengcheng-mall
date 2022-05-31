package com.zhengcheng.mall.bbs.service;

import java.util.List;

import com.zhengcheng.mall.bbs.domain.entity.FriendLink;

/**
 * 友情链接
 *
 * @author zqs
 * @version 3.0
 */
public interface FriendLinkService extends BaseService<FriendLink, Long> {

    /**
     * 列表
     *
     * @param type     类型
     * @param position 位置
     * @return
     */
    List<FriendLink> findList(FriendLink.Type type, FriendLink.Position position);
}
