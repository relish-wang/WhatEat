package com.xhxkj.zhcs.entity;

import com.xhxkj.zhcs.base.BaseEntity;

/**
 * Created by r3lish on 2016/2/28.
 */
public class StepEntity extends BaseEntity{

    Integer ivResId;
    String text;

    public StepEntity(Integer ivResId, String text) {
        this.ivResId = ivResId;
        this.text = text;
    }

    public Integer getIvResId() {
        return ivResId;
    }

    public void setIvResId(Integer ivResId) {
        this.ivResId = ivResId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
