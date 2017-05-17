package com.xhxkj.zhcs.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.presenter.WelcomeAtyPst;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.WelcomeAtyView;

/**
 * 欢迎界面
 *
 * @author 王鑫
 */
public class WelcomeAty extends BaseAty implements WelcomeAtyView {

    WelcomeAtyPst pst;

    @Override
    protected int layoutResId() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.aty_welcome;
    }

    @Override
    protected void initPresenter() {
        pst = new WelcomeAtyPst();
        pst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hide();
    }

    @Override
    protected void initViews() {
        // 自动登录
        pst.autoLogin();
    }

    @Override
    public void onAutoLoginSuccess() {
        goActivity(MainAty.class);
        finish();
    }

    @Override
    public void onAutoLoginFail(@Nullable String name) {
        Intent intent = new Intent(this, LoginAty.class);
        intent.putExtra(UserEntity.NAME, name);
        startActivity(intent);
        finish();
    }
}
