package com.yy.bean;

import java.util.Date;
import java.util.List;

// 定义一个电影类Movie类，Movie类包含：片名、主演、评分、时长、票价、余票
public class Movie {
    private String name;
    private String star;
    private double rate;
    private double duration;
    private Date startTime;
    private double price;
    private int ticketNumber;
    private List<Double> scores; // 评分集合
    private double score; // 评分

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }

    public Movie() {
    }

    public Movie(String name, String star, double duration, Date startTime, double price, int ticketNumber) {
        this.name = name;
        this.star = star;
        this.duration = duration;
        this.startTime = startTime;
        this.price = price;
        this.ticketNumber = ticketNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
