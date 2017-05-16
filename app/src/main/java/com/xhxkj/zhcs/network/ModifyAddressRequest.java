package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.AddressEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 修改默认收货地址
 * Created by 鑫 on 2015/12/4.
 */
public class ModifyAddressRequest extends BaseJsonRequest<Void> {

    String sessionId;
    AddressEntity entity;

    public ModifyAddressRequest(String sessionId, AddressEntity entity) {
        this.entity = entity;
        this.sessionId = sessionId;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("address_id", entity.getId());
        jsonObject.put("name", entity.getName());
        jsonObject.put("tel", entity.getTel());
        jsonObject.put("address", entity.getAddress());

        return jsonObject;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "addressUpdateServlet";
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
