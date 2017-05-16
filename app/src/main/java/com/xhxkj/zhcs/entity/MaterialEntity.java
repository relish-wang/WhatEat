package com.xhxkj.zhcs.entity;

import com.xhxkj.zhcs.base.BaseEntity;

/**
 * Created by r3lish on 2016/2/28.
 */
public class MaterialEntity extends BaseEntity {
    String name;
    String weight;

    public MaterialEntity(String name, String weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
