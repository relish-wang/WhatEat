package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.UserEntity;

import org.json.JSONException;
import org.json.JSONObject;

public class LogoutRequest extends BaseJsonRequest<Void> {

    String sessionId;

    public LogoutRequest(String sessionId) {
        this.sessionId = sessionId;
        //TODO 测试
        DEBUG = true;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("sessionId", sessionId);
        return json;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "userLogoutServlet";
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
