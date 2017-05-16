package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.AppContext;
import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.util.D;
import com.xhxkj.zhcs.util.OkHttpInvoker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 注册
 * Created by 鑫 on 2015/11/24.
 */
public class RegisterRequest extends BaseJsonRequest<Void> {
    public static final String REGISTER_METHOD_NAME = "userRegisterServlet";

    private String name;
    private String pwd;
    private String tel;

    public RegisterRequest(String username, String password, String tel) {
        this.name = username;
        this.pwd = password;
        this.tel = tel;
    }

    @NonNull
    @Override
    protected String methodName() {
        return REGISTER_METHOD_NAME;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(UserEntity.NAME, name);
        jsonObject.put(D.user_info.pwd, pwd);
        jsonObject.put(D.user_info.tel, tel);
        return jsonObject;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        UserEntity.setName(response.getString(UserEntity.NAME));
        return null;
    }

    @Override
    protected String parseErrorMessage(int resultCode) {
        if (resultCode == 1) {
            return "注册成功";
        }else if(resultCode == 0){
            return "用户名已存在";
        }
        return super.parseErrorMessage(resultCode);
    }
}
