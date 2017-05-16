package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.UserEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 鑫 on 2015/12/9.
 */
public class AddAddressRequest extends BaseJsonRequest<Void> {

    String name;
    String tel;
    String address;


    public AddAddressRequest(String name, String tel, String address) {
        this.name = name;
        this.tel = tel;
        this.address = address;
    }


    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("tel", tel);
        jsonObject.put("address", address);
        jsonObject.put("sessionId", UserEntity.getSessionId());
        return jsonObject;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "addressAddServlet";
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
