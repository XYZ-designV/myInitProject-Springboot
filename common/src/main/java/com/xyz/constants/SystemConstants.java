package com.xyz.constants;

/**
 * 系统自定义常量
 * @author xyz
 * 2023/5/30
 */
public class SystemConstants {

    /**
     * 后台系统用户ID
     */
    public static final int ADMIN = 1;
    /**
     * 系统管理员root
     */
    public static final Long ROOT_ID = 1L;

    /**
     * 文章草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**
     * 文章正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 分类正常发布状态
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    /**
     * 分类禁用状态
     */
    public static final int CATEGORY_STATUS_FORBID = 1;

    /**
     * 友联审核成功状态
     */
    public static final int LINK_STATUS_NORMAL = 0;

    /**
     * 评论表根目录id
     */
    public static final int COMMENT_ROOT_ID = -1;

    /**
     * 文章评论列表
     */
    public static final String ARTICLE_COMMENT_TYPE = "0";

    /**
     * 友联评论列表
     */
    public static final String LINK_COMMENT_TYPE = "1";

    /**
     * 角色正常状态
     */
    public static final String ROLE_STATUS_NORMAL = "0";
    /**
     * 角色未被禁止状态
     */
    public static final String ROLE_NO_FORBID = "0";

    /**
     * 权限正常状态
     */
    public static final String MENU_STATUS_NORMAL = "0";
}
