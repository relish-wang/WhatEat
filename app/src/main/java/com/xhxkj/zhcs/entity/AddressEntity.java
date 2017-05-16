package com.xhxkj.zhcs.entity;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseEntity;

/**
 * Created by 鑫 on 2015/12/3.
 */
public class AddressEntity extends BaseEntity implements Comparable<AddressEntity> {

    private String id;
    private String name;
    private String tel;
    private String addr;
    private Boolean isDefault = Boolean.FALSE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return addr;
    }

    public void setAddress(String addr) {
        this.addr = addr;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public int compareTo(@NonNull AddressEntity another) {
        if (this.isDefault) {
            return -1;
        } else if (!another.isDefault) {
            return another.id.compareTo(this.id);
        } else {
            return 1;
        }
    }
}
