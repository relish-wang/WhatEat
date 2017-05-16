package com.xhxkj.zhcs.entity;

import com.xhxkj.zhcs.base.BaseEntity;

/**
 * Created by r3lish on 2016/2/27.
 */
public class FoodEntity extends BaseEntity {

    Integer iconResId;

    String foodName;

    public FoodEntity(Integer iconResId, String foodName) {
        this.iconResId = iconResId;
        this.foodName = foodName;
    }

    public Integer getIconResId() {
        return iconResId;
    }

    public void setIconResId(Integer iconResId) {
        this.iconResId = iconResId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
