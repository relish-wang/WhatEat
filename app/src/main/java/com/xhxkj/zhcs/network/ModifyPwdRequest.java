package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.util.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 修改密码
 */
public class ModifyPwdRequest extends BaseJsonRequest<Void> {

    String oldPwd;
    String newPwd;

    public ModifyPwdRequest(String oldPwd, String newPwd) {
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", UserEntity.getSessionId());
        jsonObject.put("oldPwd", MD5Utils.toMd5(oldPwd));
        jsonObject.put("newPwd", MD5Utils.toMd5(newPwd));
        return jsonObject;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "userInfoUpdatePwdServlet";
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }

    @Override
    protected String parseErrorMessage(int resultCode) {
        switch(resultCode){
            case -1:
                return "登录超时";
            case 1:
                return "传入参数有误";
            case 2:
                return "旧密码错误";
            default:
                return super.parseErrorMessage(resultCode);
        }
    }
}
