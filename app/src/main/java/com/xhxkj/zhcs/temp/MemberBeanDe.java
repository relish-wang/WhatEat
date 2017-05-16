package com.xhxkj.zhcs.temp;

/**
 * 家庭成员实体
 * @author 王鑫
 * Created by 鑫 on 2015/10/11.
 * @deprecated
 */
public class MemberBeanDe {
    private int ivResId;
    private String name;
    private String nickname;
    private String phone;

    public MemberBeanDe(int ivResId, String name) {
        this.ivResId = ivResId;
        this.name = name;
    }

    public MemberBeanDe(String nickname, String phone) {
        this.phone = phone;
        this.nickname = nickname;
    }

    public int getIvResId() {
        return ivResId;
    }

    public void setIvResId(int ivResId) {
        this.ivResId = ivResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }
    public String getPhone(){
        return phone;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
