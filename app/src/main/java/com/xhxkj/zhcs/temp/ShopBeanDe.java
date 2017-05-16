package com.xhxkj.zhcs.temp;

/**
 * 店铺
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/11/9.
 *         @deprecated
 */
public class ShopBeanDe {
    private String name;
    private int distance;
    private int hireCount;
    private double score;

    public ShopBeanDe(String name, int distance, int hireCount, double score) {
        this.name = name;
        this.distance = distance;
        this.hireCount = hireCount;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getHireCount() {
        return hireCount;
    }

    public void setHireCount(int hireCount) {
        this.hireCount = hireCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
