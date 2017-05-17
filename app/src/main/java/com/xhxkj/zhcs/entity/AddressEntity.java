package com.xhxkj.zhcs.entity;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.db.DataSupportCompat;

/**
 * Created by 鑫 on 2015/12/3.
 */
public class AddressEntity extends DataSupportCompat<AddressEntity> implements Comparable<AddressEntity> {

    private String name;
    private String tel;
    private String addr;
    private Boolean isDefault = Boolean.FALSE;

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
            return another.id > this.id ? 1 : -1;
        } else {
            return 1;
        }
    }
}
