package com.yy.bean;

// 定义User类作为父类，属性：登录名称、密码、真实名称、性别、电话、账户金额
public class User {
    private String loginName;
    private String password;
    private String name;
    private char gender;
    private String phoneNumber;
    private double money;

    public User() {
    }

    public User(String loginName, String password, String name, char gender, String phoneNumber, double money) {
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.money = money;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
