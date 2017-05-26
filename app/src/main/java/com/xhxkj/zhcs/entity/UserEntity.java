package com.xhxkj.zhcs.entity;

import android.text.TextUtils;

import com.xhxkj.zhcs.util.AppPreference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 用户实体类
 * <p>
 * Created by 鑫 on 2015/11/30.
 */
public class UserEntity implements Serializable {
    public static final String NAME = "name";
    public static final String PWD = "pwd";
    public static final String SESSION_ID = "sessionId";
    public static final String ID = "id";
    public static final String TEL = "tel";
    public static final String ADDRESS_ID = "defaultAddress";


    //入参和出参
    private static String name;
    private static String pwd;

    private static String sessionId;
    //data 出参
    private static String id;
    private static String tel;
    private static String defaultAddressId;
    private static String defaultAddress;
    private static ArrayList<AddressEntity> addresses;

    public static String getName() {
        if (TextUtils.isEmpty(name)) {
            name = AppPreference.getString(NAME, "");
        }
        return name;
    }

    public static void setName(String name) {
        AppPreference.put(NAME, name);
        UserEntity.name = name;
    }

    public static String getPwd() {
        if (TextUtils.isEmpty(pwd)) {
            pwd = AppPreference.getString(PWD, "");
        }
        return pwd;
    }

    public static void setPwd(String pwd) {
        AppPreference.put(PWD, pwd);
        UserEntity.pwd = pwd;
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        UserEntity.sessionId = sessionId;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        UserEntity.id = id;
    }

    public static String getTel() {
        if (TextUtils.isEmpty(tel)) {
            return "未填写";
        }
        return tel;
    }

    public static void setTel(String tel) {
        UserEntity.tel = tel;
    }

    public static String getDefaultAddressId() {
        return defaultAddressId;
    }

    public static void setDefaultAddressId(String defaultAddressId) {
        UserEntity.defaultAddressId = defaultAddressId;
    }

    public static ArrayList<AddressEntity> getAddresses() {
        if (UserEntity.addresses == null) {
            return new ArrayList<AddressEntity>() {
                {
                    add(new AddressEntity("王鑫", "13588015024", "浙江省杭州市江干区学院街998号浙江传媒学院生活区", true));
                    add(new AddressEntity("易小强", "13500005024", "浙江省杭州市滨江区南环路3738号税友大厦", false));
                    add(new AddressEntity("Lucky", "13511115024", "浙江省杭州市余杭区五常大道175号大搜车", false));
                    add(new AddressEntity("一条狗", "13522225024", "浙江省杭州市余杭区五常大道175号大搜车门口的那个狗窝", false));
                }
            };
        }
        Collections.sort(UserEntity.addresses);
        return UserEntity.addresses;
    }

    /**
     * 设置地址列表的同时设置默认地址
     *
     * @param addresses 地址列表
     */
    public static void setAddresses(ArrayList<AddressEntity> addresses) {
        if (addresses == null || addresses.size() == 0) return;
        boolean hasSetDefault = false;
        for (AddressEntity address : addresses) {
            if (TextUtils.equals(address.getId() + "", defaultAddressId)) {
                setDefaultAddress(address.getAddress());
                setDefaultAddressId(address.getId() + "");
                hasSetDefault = true;
                break;
            }
        }
        //其实这么做是不合理的：
        //1.删除默认地址导致不存在默认地址
        //2.但是在【我的账户】页面需要显示一个地址，
        // 所以将这些地址里面的id最小的地址作为假的默认地址
        if (!hasSetDefault) {
            setDefaultAddress(addresses.get(0).getAddress());
            setDefaultAddressId(addresses.get(0).getId() + "");
        }
        UserEntity.addresses = addresses;
    }

    public static String getDefaultAddress() {
        if (TextUtils.isEmpty(defaultAddress)) {
            return "浙江省杭州市江干区学源街998号浙江传媒学院生活区";
        }
        return defaultAddress;
    }

    public static void setDefaultAddress(String defaultAddress) {
        UserEntity.defaultAddress = defaultAddress;
    }

    /**
     * 修改地址列表中的一个
     *
     * @param address 被更改的地址
     */
    public static void setAddress(AddressEntity address) {
        for (int i = 0; i < addresses.size(); i++) {
            if (TextUtils.equals(addresses.get(i).getId() + "", address.getId() + "")) {
                addresses.get(i).setAddress(address.getAddress());
                addresses.get(i).setName(address.getName());
                addresses.get(i).setTel(address.getTel());
                addresses.get(i).setIsDefault(address.getIsDefault());//TODO 不合理之处
            }
        }
    }
}
