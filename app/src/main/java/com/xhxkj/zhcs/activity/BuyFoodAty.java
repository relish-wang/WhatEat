package com.xhxkj.zhcs.activity;

import android.view.View;
import android.widget.TextView;

import com.xhxkj.zhcs.App;
import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.view.AppActionBar;

import butterknife.OnClick;

/**
 * Created by r3lish on 2016/2/19.
 */
public class BuyFoodAty extends BaseAty{
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


    @Override
    protected void initViews() {
    }

    @OnClick(R.id.tvPutItIntoCart)
    public void addToCart() {
        showLoading(true);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(()->{
                showLoading(false);
                App.clearActivities();
                goActivity(MainAty.class);
                finish();
                showMessage("成功加入购物车");
            });
        }).start();

    }
}
