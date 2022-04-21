package com.example.householderback.entity.param;



public class HouseHoldUpdateParam {
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
     * 描述
     */
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


}
