package com.xhxkj.zhcs.activity;

import android.view.View;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.view.AppActionBar;

/**
 * Created by r3lish on 2016/2/28.
 */
public class TextAty extends BaseAty {

    @Override
    protected int layoutResId() {
        return R.layout.aty_text;
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setBtnBackText("取消");
        appActionBar.setBtnCustomText("确定");
        appActionBar.setBtnCustomOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                finish();
            }
        });
    }

    @Override
    protected void initViews() {
    }
}
