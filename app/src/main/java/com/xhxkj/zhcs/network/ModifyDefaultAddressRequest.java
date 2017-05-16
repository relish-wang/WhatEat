package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 修改默认收货地址
 * Created by 鑫 on 2015/12/4.
 */
public class ModifyDefaultAddressRequest extends BaseJsonRequest<Void> {

    String addressId;
    String sessionId;

    public ModifyDefaultAddressRequest(String sessionId, String addressId) {
        this.addressId = addressId;
        this.sessionId = sessionId;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("addressId", addressId);

        return jsonObject;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "updateDefaultAddressServlet";
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
