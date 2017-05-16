package com.xhxkj.zhcs.entity;

import com.xhxkj.zhcs.base.BaseEntity;

/**
 * OrderEntity
 * Created by 鑫 on 2015/12/1.
 */
public class OrderEntity extends BaseEntity {
    private String id;
    private String date;
    private String value;
    private String comment;
    private String buyer_id;
    private String user_id;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if(comment==null){
            this.comment="暂无评价";
            return;
        }
        this.comment = comment;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyerId(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
