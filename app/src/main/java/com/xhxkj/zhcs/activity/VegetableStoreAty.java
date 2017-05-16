package com.xhxkj.zhcs.activity;

import android.os.AsyncTask;
import android.widget.Button;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.MyPagerAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.fragment.StoreFgm;
import com.xhxkj.zhcs.fragment.StoreListFgm;
import com.xhxkj.zhcs.fragment.StorePayFgm;
import com.xhxkj.zhcs.util.AppToast;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.view.NonSlidePager;
import com.xhxkj.zhcs.view.WaitDialog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-主页-蔬菜店
 *
 * @author 魏一凡
 */
public class VegetableStoreAty extends BaseAty {

    @Bind(R.id.pagerStore)
    NonSlidePager viewPager;
    @Bind(R.id.btnLeft)
    Button btnLeft;
    @Bind(R.id.btnRight)
    Button btnRight;


    @Override
    protected int layoutResId() {
        return R.layout.aty_vegetable_store;
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.title_activity_vegetable_store));
    }

    @Override
    protected void initViews() {
        ArrayList<BaseFgm> fragments = new ArrayList<>();
        fragments.add(new StoreListFgm());
        fragments.add(new StoreFgm());
        fragments.add(new StorePayFgm());
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.btnLeft)
    public void clickLeft() {
        if (viewPager.getCurrentItem() == 0) {
            finish();
        } else {
            selectPage(viewPager.getCurrentItem() - 1);
        }
    }

    WaitDialog waitDialog;

    @OnClick(R.id.btnRight)
    public void clickRight() {
        if (viewPager.getCurrentItem() == 2) {

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
        } else {
            selectPage(viewPager.getCurrentItem() + 1);
        }
    }

    private void selectPage(int pageIndex) {
        viewPager.setCurrentItem(pageIndex);
        switch (pageIndex) {
            case 0:
                btnLeft.setText("查看地图");
                break;
        }
    }

}
