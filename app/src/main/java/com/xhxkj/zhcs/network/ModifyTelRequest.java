package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 修改联系方式
 *
 * Created by 鑫 on 2015/12/2.
 */
public class ModifyTelRequest extends BaseJsonRequest<Void>{
    private String sessionId;
    private String tel;

    public ModifyTelRequest(String sessionId,String tel) {
        this.sessionId = sessionId;
        this.tel = tel;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId",sessionId);
        jsonObject.put("tel",tel);
        return jsonObject;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "userInfoUpdateTelServlet";
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
