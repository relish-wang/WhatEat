package com.xhxkj.zhcs.activity.homepage;

import android.os.AsyncTask;
import android.view.View;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.MyPagerAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.fragment.MarketBoothListFgm;
import com.xhxkj.zhcs.fragment.MarketFoodListFgm;
import com.xhxkj.zhcs.fragment.MarketListFgm;
import com.xhxkj.zhcs.fragment.MarketOptionFgm;
import com.xhxkj.zhcs.fragment.MarketPayFgm;
import com.xhxkj.zhcs.util.AppToast;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.view.NonSlidePager;
import com.xhxkj.zhcs.view.WaitDialog;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 主界面-附近菜场
 *
 * @author 魏一凡
 */
public class MarketNearbyAty extends BaseAty {

    public static final int INDEX_MARKET_LIST = 0;
    public static final int INDEX_BOOTH_LIST = 1;
    public static final int INDEX_DISH_LIST = 2;
    public static final int INDEX_OPTION = 3;
    public static final int INDEX_PAY = 4;

    @Bind(R.id.pagerMarket)
    NonSlidePager viewPager;
    ArrayList<BaseFgm> fragments;

    @Override
    protected int layoutResId() {
        return R.layout.aty_market_nearby;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.market));
        appActionBar.setBtnBackOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() == INDEX_MARKET_LIST) {
                    finish();
                } else {
                    selectPage(viewPager.getCurrentItem() - 1);
                }
            }
        });
    }

    @Override
    protected void initViews() {
        fragments = new ArrayList<>();
        fragments.add(new MarketListFgm());//0:INDEX_MARKET_LIST
        fragments.add(new MarketBoothListFgm());//1:INDEX_BOOTH_LIST
        fragments.add(new MarketFoodListFgm());//2:INDEX_DISH_LIST
        fragments.add(new MarketOptionFgm());//3:INDEX_OPTION
        fragments.add(new MarketPayFgm());//4:INDEX_PAY

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        selectPage(0);
    }
    WaitDialog waitDialog;
    public void selectPage(int pageIndex) {
        viewPager.setCurrentItem(pageIndex);
        switch (pageIndex) {
            case INDEX_MARKET_LIST://商铺列表
                getAppActionBar().setBtnBackText("首页");
                getAppActionBar().setActionBarTitle("附近菜市");
                getAppActionBar().hideBtnCustom();
                break;
            case INDEX_BOOTH_LIST://商铺名【摊位列表】
                getAppActionBar().setBtnBackText("商铺列表");
                getAppActionBar().setActionBarTitle("昇果园");
                getAppActionBar().hideBtnCustom();
                break;
            case INDEX_DISH_LIST://摊位名【菜品列表】
                getAppActionBar().setBtnBackText("菜品列表");
                getAppActionBar().setActionBarTitle("C10摊位");
                getAppActionBar().hideBtnCustom();
                break;
            case INDEX_OPTION://购买选项
                getAppActionBar().setBtnBackText("C10摊位");
                getAppActionBar().setActionBarTitle("购买选项");
                getAppActionBar().setBtnCustomText("确认支付");
                getAppActionBar().setBtnCustomOnClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectPage(INDEX_PAY);
                    }
                });
                break;
            case INDEX_PAY://确认支付
                getAppActionBar().setBtnBackText("购买选项");
                getAppActionBar().setActionBarTitle("确认支付");
                getAppActionBar().setBtnCustomText("提交");
                getAppActionBar().setBtnCustomOnClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                waitDialog = new WaitDialog();
                                waitDialog.show(getSupportFragmentManager(), "wait");
                            }

                            @Override
                            protected Void doInBackground(Void... params) {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                waitDialog.dismiss();
                                AppToast.showShort("支付成功");
                                finish();
                            }
                        }.execute();
                    }
                });
                break;
        }
    }

}
