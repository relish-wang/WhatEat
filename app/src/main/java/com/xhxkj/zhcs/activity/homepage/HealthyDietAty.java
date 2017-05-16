package com.xhxkj.zhcs.activity.homepage;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.view.AppActionBar;

/**
 * 主界面-首页-健康饮食
 *
 * @author 魏一凡
 */
public class HealthyDietAty extends BaseAty {

    @Override
    protected int layoutResId() {
        return R.layout.aty_healthy_diet;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.healthy_diet));
    }

    @Override
    protected void initViews() {

    }

}
