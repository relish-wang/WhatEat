package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.UserEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 鑫 on 2015/12/9.
 */
public class DeleteAddressRequest extends BaseJsonRequest<Void>{

    String address_id;

    public DeleteAddressRequest(String address_id) {
        this.address_id = address_id;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address_id",address_id);
        jsonObject.put("sessionId", UserEntity.getSessionId());
        return jsonObject;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "addressDeleteServlet";
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
