package com.yy.bean;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {
    // 定义一个属性存储购买记录
    private Map<String, Boolean> movieRated = new HashMap<>();

    public Map<String, Boolean> getMovieRated() {
        return movieRated;
    }

    public void setMovieRated(Map<String, Boolean> movieRated) {
        this.movieRated = movieRated;
    }

    public Customer() {
    }

    public Customer(String loginName, String password, String name, char gender, String phoneNumber, double money) {
        super(loginName, password, name, gender, phoneNumber, money);
    }
}
