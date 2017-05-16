package com.xhxkj.zhcs.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.VegetableStoreAty;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.adapter.SelectGoodAdapter;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.presenter.CartFgmPst;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.CartFgmView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

/**
 * @author 王鑫
 */
public class CartFgm extends BaseFgm implements AdapterView.OnItemClickListener, CartFgmView {

    @Bind(R.id.lvGoods)
    ListView lvGoods;
    @Bind(R.id.btnEmptyCart)
    Button btnEmptyCart;

    @Bind(R.id.appActionBar)
    AppActionBar appActionBar;

    @Bind(R.id.tv_no_data)
    TextView tv_no_data;

    CartFgmPst pst;
    ArrayList<HashMap<String, Object>> mData;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_cart;
    }

    @Override
    protected void initPresenter() {
        pst = new CartFgmPst();
        pst.attachView(this);
    }

    @OnClick(R.id.btnEmptyCart)
    public void emptyCart() {
        pst.emptyCart();
    }

    SelectGoodAdapter adapter;

    @Override
    protected void initView() {
        appActionBar.setActionBarTitle("购物车");
        appActionBar.hideBtnBack();
        appActionBar.hideBtnCustom();
        mData = TempData.getShoppingCart();
        adapter = new SelectGoodAdapter(getActivity(), mData);
        lvGoods.setAdapter(adapter);
        update(mData);
    }

    public void update(ArrayList<HashMap<String, Object>> data) {
        if (data == null || data.size() == 0) {
            tv_no_data.setVisibility(View.VISIBLE);
            lvGoods.setVisibility(View.GONE);
            btnEmptyCart.setVisibility(View.GONE);
        } else {
            tv_no_data.setVisibility(View.GONE);
            lvGoods.setVisibility(View.VISIBLE);
            btnEmptyCart.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @OnItemClick(R.id.lvGoods)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        new AlertDialog.Builder(getActivity())
                .setTitle("选择购买来源")
                .setMessage("选择是从蔬菜店或是农贸市场购买蔬菜？")
                .setPositiveButton("取消", null)
                .setNeutralButton("蔬菜店", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), VegetableStoreAty.class));
                    }
                })
                .setNegativeButton("农贸市场", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), MarketNearbyAty.class));
                    }
                })
                .create()
                .show();
    }

    @OnItemLongClick(R.id.lvGoods)
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(getActivity())
                .setTitle("删除")
                .setMessage("是否删除【" + mData.get(position).get("good_name") + "】?")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mData.remove(position);
                        update(mData);
                    }
                })
                .setNegativeButton("取消", null)
                .show();

        return true;
    }


    @OnClick(R.id.btnEmptyCart)
    public void onClick(View v) {
        new AlertDialog.Builder(getActivity())
                .setTitle("清空购物车")
                .setMessage("是否清空购物车所有物品？")
                .setPositiveButton("清空", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mData == null) {
                            mData = new ArrayList<>();
                        } else {
                            mData.clear();
                        }
                        update(mData);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }


}
