package com.zhengcheng.mall.bbs.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zhengcheng.mall.bbs.domain.entity.Member;

/**
 * The Interface MemberService.
 *
 * @author zqs
 * @version 3.0
 */
public interface MemberService extends BaseService<Member, Long> {

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名(忽略大小写)
     * @return 用户名是否存在
     */
    boolean usernameExists(String username);

    /**
     * 判断昵称是否存在
     *
     * @param nickname 昵称
     * @return 昵称是否存在
     */
    boolean nicknameExists(String nickname);

    /**
     * 判断用户名是否禁用
     *
     * @param username 用户名(忽略大小写)
     * @return 用户名是否禁用
     */
    boolean usernameDisabled(String username);

    /**
     * 根据用户名查询会员
     *
     * @param username 用户名
     * @return 会员
     */
    Member findByUsername(String username);

    /**
     * 根据昵称查询会员
     *
     * @param nickname 昵称
     * @return 会员
     */
    Member findByNickname(String nickname);

    /**
     * 判断会员是否登录
     *
     * @return 会员是否登录
     */
    boolean isAuthenticated();

    /**
     * 获取当前登录会员
     *
     * @return 当前登录会员，若不存在则返回null
     */
    Member getCurrent();

    /**
     * 获取当前登录用户ID
     *
     * @return 当前登录用户ID，若不存在则返回null
     */
    Long getCurrentID();

    /**
     * 注册会员
     *
     * @param username 用户名
     * @param nickname 昵称
     * @param password 密码
     */
    Member register(String username, String nickname, String password);

    /**
     * 回贴周榜
     *
     * @param weekReplies 周回帖
     * @param enable      会员是否启用
     * @param pageable    分页
     * @return
     */
    Page<Member> findPage(Long weekReplies, Boolean enable, Pageable pageable);

    /**
     * 根据签到时间排序查询
     * <p>
     * 最新签到 TOP20 -今日最快 TOP20
     *
     * @return
     */
    Page<Member> findPageBySignTime(Pageable pageable);

    /**
     * 连续签到-总签到榜  TOP20
     */
    Page<Member> findPageBySigncount(Pageable pageable);

    /**
     * 判断是否今日是否签到
     *
     * @return
     */
    Boolean isSign(Member member);

    /**
     * 签到
     *
     * @param member 会员
     * @return
     */
    Map<String, Object> sign(Member member);

    /**
     * 更新会员属性
     *
     * @param currentMember 当前的会员
     * @param id            会员ID
     * @param field         属性
     * @param value         属性值
     * @return
     */
    boolean update(Member currentMember, Long id, String field, String value);

    /**
     * 更新头像
     *
     * @param id     会员ID
     * @param avatar 头像
     */
    void updateAvatar(Long id, String avatar);

}
