package com.example.householderback.entity.param;

import java.time.LocalDateTime;

public class AddUserInfo {
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 民族
     */
    private String race;

    /**
     * 籍贯
     */
    private String place;

    /**
     * 户口类型 常住/外地
     */
    private String type;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 身份证
     */
    private String identity;

    /**
     * 密码
     */
    private String password;

    /**
     * 健康状况
     */
    private String healhty;

    /**
     * 单位
     */
    private String company;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 单位地址
     */
    private String address;

    /**
     * 户籍状态 迁出/迁出/注销
     */
    private String status;

    /**
     * 户籍号
     */
    private Integer householderId;

    /**
     * 性别
     */
    private String gender;
}
