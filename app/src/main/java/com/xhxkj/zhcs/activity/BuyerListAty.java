package com.xhxkj.zhcs.activity;


import android.view.View;
import android.widget.ListView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.adapter.BuyerAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 选择买手
 */
public class BuyerListAty extends BaseAty {

    @Bind(R.id.lvBuyers)
    ListView lvBuyers;

    View view;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_market_buyer_list;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setActionBarTitle("选择买手");
    }

    @Override
    protected void initViews() {
        BuyerAdapter adapter = new BuyerAdapter(this, TempData.getBuyers());
        lvBuyers.setAdapter(adapter);
    }

    @OnItemClick(R.id.lvBuyers)
    public void goPay(int position){
        //TODO 选择买手
        finish();
    }
}
