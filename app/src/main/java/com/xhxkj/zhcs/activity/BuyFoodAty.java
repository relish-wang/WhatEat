package com.xhxkj.zhcs.activity;

import android.view.View;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.view.AppActionBar;

/**
 * Created by r3lish on 2016/2/19.
 */
public class BuyFoodAty extends BaseAty implements View.OnClickListener{
    @Override
    protected int layoutResId() {
        return R.layout.aty_buy_food;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
    }

    TextView tvBuyItNow;

    @Override
    protected void initViews() {
        tvBuyItNow = (TextView) findViewById(R.id.tvBuyItNow);
        tvBuyItNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvBuyItNow:



                break;
        }
    }
}
