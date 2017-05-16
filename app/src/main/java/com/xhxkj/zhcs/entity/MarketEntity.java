package com.xhxkj.zhcs.entity;

import com.xhxkj.zhcs.base.BaseEntity;

/**
 * MarketEntity
 * Created by r3lish on 2016/2/20.
 */
public class MarketEntity extends BaseEntity implements Comparable<MarketEntity> {
    Integer id;
    Integer iconResId;
    String name;
    Double distance;
    Integer times;
    Double comment;

    public MarketEntity(Integer iconResId, String name, Double distance, Integer times, Double comment) {
        this.iconResId = iconResId;
        this.name = name;
        this.distance = distance;
        this.times = times;
        this.comment = comment;
    }

    @Override
    public int compareTo(MarketEntity another) {
        if (this.distance < another.distance) {
            return 1;
        } else if (this.distance > another.distance) {
            return -1;
        } else {
            if (this.times > another.times) {
                return 1;
            } else if (this.times < another.times) {
                return -1;
            } else {
                if (this.comment > another.comment) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Double getComment() {
        return comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }

    public Integer getIconResId() {
        return iconResId;
    }

    public void setIconResId(Integer iconResId) {
        this.iconResId = iconResId;
    }
}