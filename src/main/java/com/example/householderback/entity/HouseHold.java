package com.example.householderback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhz
 * @since 2022-04-12
 */
@TableName("house_hold")
public class HouseHold implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 户主名
     */
    private String householder;

    /**
     * 组号	
     */
    private Integer groupNo;

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
    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
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

    @Override
    public String toString() {
        return "HouseHold{" +
            "id=" + id +
            ", householder=" + householder +
            ", groupNo=" + groupNo +
            ", address=" + address +
            ", houseNo=" + houseNo +
            ", peopleCount=" + peopleCount +
            ", desc=" + desc +
        "}";
    }
}
