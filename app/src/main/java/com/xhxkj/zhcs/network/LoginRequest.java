package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.base.BaseRequest;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.util.MD5Utils;
import com.xhxkj.zhcs.util.OkHttpInvoker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 登录请求
 * Created by 鑫 on 2015/11/24.
 */
public class LoginRequest extends BaseJsonRequest<Void> {
    public static boolean isSessionValid = false;

    public static final String LOGIN_METHOD_NAME = "userLoginServlet";

    private String name;
    private String pwdMD5;

    public LoginRequest(String name, String pwd) {
        this.name = name;
        this.pwdMD5 = MD5Utils.toMd5(pwd);
    }

    @NonNull
    @Override
    protected String methodName() {
        return LOGIN_METHOD_NAME;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(UserEntity.NAME, name);
        jsonObject.put(UserEntity.PWD, pwdMD5);
        return jsonObject;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {

        JSONObject data = response.getJSONObject("data");
        UserEntity.setSessionId(data.getString(UserEntity.SESSION_ID));
        UserEntity.setId(data.getString(UserEntity.ID));
        UserEntity.setTel(data.getString(UserEntity.TEL));
        UserEntity.setName(data.getString(UserEntity.NAME));
        //UserEntity.setDefaultAddressId(data.getString(UserEntity.ADDRESS_ID));
        getAddresses();
        return null;
    }

    @Override
    protected String parseErrorMessage(int resultCode) {
        if (resultCode == 1) {
            return "用户名或密码错误";
        }
        return super.parseErrorMessage(resultCode);
    }

    /**
     * 重新登录。<br/>
     * 注意：该方法用来为网络请求的返回信息为“session过期”时重新登录以获取新的session，不建议直接使用此方法进行登录。
     * 因为其仅仅返回登录是否成功且在登录成功后对本地信息进行更新，并不会返回具体的登录失败原因，如：用户名或密码错误。
     * 登录应创建{@link LoginRequest}，并调用{@link #request()}进行登录，以及调用
     * {@link #setOnResponseListener(OnResponseListener)}监听登录结果<br/>
     *
     * @param username 用户名
     * @param password 密码（加密过的）
     * @return 返回重新登录的结果
     */
    public static synchronized boolean reLogin(String username, String password) {
        if (isSessionValid||true) {
            return true;
        }

        isSessionValid = false;

        try {
            JSONObject jsonLoginRequest = new JSONObject();
            jsonLoginRequest.put(UserEntity.NAME, username);
            jsonLoginRequest.put(UserEntity.PWD, password);

            JSONObject jsonLoginResponse = new JSONObject(OkHttpInvoker.postJson(
                    BASE_URL + LOGIN_METHOD_NAME, jsonLoginRequest.toString()));

            String sessionId = jsonLoginResponse.getInt("resultCode") == 0 ?
                    jsonLoginResponse.getString("sessionId") : null;

            if (sessionId == null) {
                return false;
            }

            //更新本地的sessionId
            UserEntity.setSessionId(sessionId);
            getAddresses();
            isSessionValid = true;
            return true;

        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得地址列表
     */
    private static void getAddresses() {
        GetAddressesRequest getAddressesRequest = new GetAddressesRequest(UserEntity.getSessionId());
        getAddressesRequest.setOnResponseListener(
                new BaseRequest.OnResponseListener<ArrayList<AddressEntity>>() {
                    @Override
                    public void onSuccess(ArrayList<AddressEntity> addressEntities) {
                        UserEntity.setAddresses(addressEntities);
                    }

                    @Override
                    public void onFail(String message) {
                        //do nothing.
                    }
                });
        getAddressesRequest.request();
    }
}
