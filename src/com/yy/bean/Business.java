package com.yy.bean;

// 定义Business类代表商家角色，属性：店铺名称、地址
public class Business extends User {
    private String shopName;
    private String address;

    public Business() {
    }

    public Business(String loginName, String password, String name, char gender, String phoneNumber, double money, String shopName, String address) {
        super(loginName, password, name, gender, phoneNumber, money);
        this.shopName = shopName;
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
