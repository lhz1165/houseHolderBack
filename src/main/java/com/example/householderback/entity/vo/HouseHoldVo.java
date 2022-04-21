package com.example.householderback.entity.vo;

import com.example.householderback.entity.UserInfo;

import java.util.List;

public class HouseHoldVo {
    /**
     * 户号
     */
    private Integer id;

    /**
     * 户主名
     */
    private String householder;
    /**
     * 户籍地址
     */
    private String address;


    /**
     * 总人数
     */
    private Integer peopleCount;

    /**
     * 备注
     */
    private String description;


    List<UserInfo> userInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseholder() {
        return householder;
    }

    public void setHouseholder(String householder) {
        this.householder = householder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
