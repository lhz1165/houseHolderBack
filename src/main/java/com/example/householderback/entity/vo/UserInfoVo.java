package com.example.householderback.entity.vo;

import java.time.LocalDateTime;

public class UserInfoVo {
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 联系电话
     */
    private String phone;

    /**
     * 单位地址
     */
    private String address;

    /**
     * 户籍状态 1迁出/2迁出/3注销
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

    /**
     * 用户账号id
     */
    private Integer userId;


    /**
     * 户主名
     */
    private String householder;


    /**
     * 户籍地址
     */
    private String houseAddress;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getHouseholderId() {
        return householderId;
    }

    public void setHouseholderId(Integer householderId) {
        this.householderId = householderId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHouseholder() {
        return householder;
    }

    public void setHouseholder(String householder) {
        this.householder = householder;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
