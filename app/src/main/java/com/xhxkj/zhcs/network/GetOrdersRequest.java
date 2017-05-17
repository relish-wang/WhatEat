package com.xhxkj.zhcs.network;

import android.support.annotation.NonNull;

import com.xhxkj.zhcs.base.BaseJsonRequest;
import com.xhxkj.zhcs.entity.OrderEntity;
import com.xhxkj.zhcs.entity.OrderListEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 获取订单列表
 * Created by 鑫 on 2015/11/24.
 */
public class GetOrdersRequest extends BaseJsonRequest<OrderListEntity> {

    public static final String GET_ORDERS_METHOD_NAME = "listOrderServlet";

    private String sessionId;
    private Integer currentPage;
    private Integer pageSize;

    public GetOrdersRequest(String sessionId, Integer currentPage, Integer pageSize) {
        this.sessionId = sessionId;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    @NonNull
    @Override
    protected String methodName() {
        return GET_ORDERS_METHOD_NAME;
    }

    @NonNull
    @Override
    protected JSONObject json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("pageSize", pageSize);
        return jsonObject;
    }

    @Override
    protected OrderListEntity parseResponse(@NonNull JSONObject response) throws JSONException {
        OrderListEntity orderList = new OrderListEntity();
        orderList.setPageNum(response.getInt("pageNum"));
        orderList.setTotalNum(response.getInt("totalNum"));
        orderList.setTotalPageNum(response.getInt("totalPageNum"));
        ArrayList<OrderEntity> orders = new ArrayList<>();
        JSONArray data = response.getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            JSONObject orderJSON = data.getJSONObject(i);
            OrderEntity order = new OrderEntity();
            order.setId(Long.parseLong(orderJSON.getString("id")));
            order.setDate(orderJSON.getString("Date"));
            order.setValue(orderJSON.getString("value"));
            order.setComment(orderJSON.getString("comment"));
            order.setBuyerId(orderJSON.getString("buyer_id"));
            order.setUserId(orderJSON.getString("user_id"));
            order.setStatus(orderJSON.getString("status"));
            orders.add(order);
        }
        orderList.setOrders(orders);
        return orderList;
    }

    @Override
    protected String parseErrorMessage(int resultCode) {
        if (resultCode == -1) {
            return "登录超时";
        } else if (resultCode == 1) {
            return "暂无数据";
        }
        return super.parseErrorMessage(resultCode);
    }
}
