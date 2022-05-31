package com.zhengcheng.mall.bbs.common.constant;

/**
 * 极简论坛的常量
 *
 * @author zqs
 * @version 3.0
 */
public class BBSContant {

    /**
     * 网站名称
     */
    public static final String  SITE_NAME                 = "HIO社区";

    /**
     * 首页-帖子专栏-全部的
     */
    public static final Long    ALL_JIE_CATEGORY_ID       = 0L;

    /**
     * 帖子详情-没有选择的帖子专栏
     */
    public static final Long    NO_SELECT_JIE_CATEGORY_ID = -1L;

    /**
     * 飞吻列表
     */
    public static final Long    KISS_LIST[]               = { 20L, 30L, 50L, 60L, 80L };

    /**
     * 每页记录数
     */
    public static final Integer PAGE_SIZE                 = 10;

    /**
     * 最大用户名长度
     */
    public static int           MAX_USERNAME_LENGTH       = 14;

    /**
     * 最小用户名长度
     */
    public static int           MIN_USERNAME_LENGTH       = 2;

    /**
     * 人类验证码索引值
     */
    public static String        VERCODE_INDEX             = "verCodeIndex";

    /**
     * 首次上传头像赠送的经验值
     */
    public static Long          FIRST_GIVE_KISS           = 1000L;

}
