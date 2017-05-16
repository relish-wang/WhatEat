package com.xhxkj.zhcs.activity.mine;

import android.support.v4.view.ViewPager;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.MyPagerAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.fragment.CollectionBuyerFgm;
import com.xhxkj.zhcs.fragment.CollectionStoreFgm;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;

public class MyCollectionAty extends BaseAty implements ViewPager.OnPageChangeListener {

    private static final int STORE = 0;
    private static final int BUYER = 1;

    private static final int[] TITLE_TEXT_RES_ID = {R.string.store_collection, R.string.buyer_collection};

    AppActionBar appActionBar;

    @Bind(R.id.vpCollection)
    ViewPager vpCollection;

    @Override
    protected int layoutResId() {
        return R.layout.aty_my_collection;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        this.appActionBar = appActionBar;
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.store_collection));
    }

    @Override
    protected void initViews() {
        ArrayList<BaseFgm> fragments = new ArrayList<>();
        BaseFgm storeCollectionFgm = new CollectionStoreFgm();
        BaseFgm buyerCollectionFgm = new CollectionBuyerFgm();
        fragments.add(storeCollectionFgm);
        fragments.add(buyerCollectionFgm);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        vpCollection.setAdapter(adapter);
        vpCollection.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        select(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void select(int pageIndex) {
        vpCollection.setCurrentItem(pageIndex);
        appActionBar.setActionBarTitle(getString(TITLE_TEXT_RES_ID[pageIndex]));
    }
}
