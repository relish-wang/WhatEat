package com.xhxkj.zhcs.activity.mine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.GoodEvaluationAty;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.OrderEntity;
import com.xhxkj.zhcs.entity.OrderListEntity;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.presenter.MyOrderAtyPst;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.view.FTextView;
import com.xhxkj.zhcs.vm.MyOrderAtyView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 主界面(aty)-我的(fgm)-我的订单(aty)
 *
 * @author 王鑫
 */
public class MyOrderAty extends BaseAty implements MyOrderAtyView {
    MyOrderAtyPst pst;
    @Bind(R.id.lvOrders)
    ListView lvOrders;

    @Bind(R.id.tv_no_data)
    TextView tv_no_data;

    @Override
    protected int layoutResId() {
        return R.layout.aty_my_order;
    }

    @Override
    protected void initPresenter() {
        pst = new MyOrderAtyPst();
        pst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.my_order));
    }

    private ArrayList<OrderEntity> orders;
    OrderAdapter adapter;

    @Override
    protected void initViews() {
        pst.update(UserEntity.getSessionId(), 0, 10);//当前第0页 一次加载10条
        orders = new ArrayList<>();
        adapter = new OrderAdapter();
        lvOrders.setAdapter(adapter);
    }

    @Override
    public void update(OrderListEntity orderList) {
        //TODO 更新ListView
        orders = TempData.getOrders();
        if (orders == null || orders.size() == 0) {
            tv_no_data.setVisibility(View.VISIBLE);
            lvOrders.setVisibility(View.GONE);
        } else {
            lvOrders.setVisibility(View.VISIBLE);
            tv_no_data.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 订单适配器
     */
    class OrderAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public OrderAdapter() {
            this.inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return orders.size();
        }

        @Override
        public Object getItem(int i) {
            return orders.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.lv_item_order, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            OrderEntity order = orders.get(i);
            holder.tvDate.setText(order.getDate());
            holder.tvHasEval.setText(order.getComment());
            holder.ivStore.setImageResource(R.mipmap.icon);//TODO 网络图片
            holder.tvStoreName.setText(order.getUser_id());//TODO 后台脑残 接口有误 假数据保平安
            holder.ftvGoods.setText(order.getStatus());//TODO 后台脑残 接口有误 假数据保平安
            holder.btnEval.setOnClickListener(view1 -> goActivity(GoodEvaluationAty.class));
            holder.btnBuyMore.setOnClickListener(v -> {
                goActivity(MarketNearbyAty.class);
            });
            return view;
        }

        class ViewHolder extends BaseViewHolder {
            @Bind(R.id.tvDate)
            TextView tvDate;
            @Bind(R.id.tvHasEval)
            TextView tvHasEval;
            @Bind(R.id.ivStore)
            ImageView ivStore;
            @Bind(R.id.tvStoreName)
            TextView tvStoreName;
            @Bind(R.id.ftvGoods)
            FTextView ftvGoods;
            @Bind(R.id.btnEval)
            Button btnEval;
            @Bind(R.id.btnBuyMore)
            Button btnBuyMore;

            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
