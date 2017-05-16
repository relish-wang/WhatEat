package com.xhxkj.zhcs.activity.mine;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.view.AppActionBar;

public class MyWalletAty extends BaseAty {

    @Override
    protected int layoutResId() {
        return R.layout.aty_my_wallet;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.my_wallet));
    }

    @Override
    protected void initViews() {

    }
}
