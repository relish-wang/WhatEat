package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.entity.UserEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 获得地址列表
 *
 * @author 王鑫 on 2015/12/3.
 */
public class GetAddressesRequest extends BaseJsonRequest<ArrayList<AddressEntity>> {

    String sessionId;

    public GetAddressesRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", sessionId);
        return jsonObject;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "listAddressServlet";
    }

    @Override
    protected ArrayList<AddressEntity> parseResponse(@NonNull JSONObject response) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("data");
        ArrayList<AddressEntity> addresses = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            AddressEntity address = new AddressEntity();
            address.setId(Long.parseLong(jsonArray.getJSONObject(i).getString("id")));
            if (TextUtils.equals(address.getId() + "", UserEntity.getDefaultAddressId())) {
                address.setIsDefault(Boolean.TRUE);
            }
            address.setName(jsonArray.getJSONObject(i).getString("name"));
            address.setTel(jsonArray.getJSONObject(i).getString("tel"));
            address.setAddress(jsonArray.getJSONObject(i).getString("address"));
            addresses.add(address);
        }
        UserEntity.setAddresses(addresses);
        return addresses;
    }
}