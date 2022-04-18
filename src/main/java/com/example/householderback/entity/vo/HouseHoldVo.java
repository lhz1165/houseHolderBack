package com.example.householderback.entity.vo;

import com.example.householderback.entity.UserInfo;

import java.util.List;

public class HouseHoldVo {
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
     * 户号
     */
    private Integer houseNo;

    /**
     * 总人数
     */
    private Integer peopleCount;

    /**
     * 备注
     */
    private String desc;


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

    public Integer getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(Integer houseNo) {
        this.houseNo = houseNo;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
