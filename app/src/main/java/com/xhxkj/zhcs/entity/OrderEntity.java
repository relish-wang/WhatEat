package com.xhxkj.zhcs.entity;

import com.xhxkj.zhcs.db.DataSupportCompat;

/**
 * OrderEntity
 * Created by 鑫 on 2015/12/1.
 */
public class OrderEntity extends DataSupportCompat<OrderEntity> {
    private String date;
    private String value;
    private String comment;
    private String buyer_id;
    private String user_id;
    private String status;

    public OrderEntity(){

    }

    public OrderEntity(String date, String value, String comment, String buyer_id, String user_id, String status) {
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.buyer_id = buyer_id;
        this.user_id = user_id;
        this.status = status;
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
        if (comment == null) {
            this.comment = "暂无评价";
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
