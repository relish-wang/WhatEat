package com.xhxkj.zhcs.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.mine.MyAccountAty;
import com.xhxkj.zhcs.activity.mine.MyAddressAty;
import com.xhxkj.zhcs.activity.mine.MyCollectionAty;
import com.xhxkj.zhcs.activity.mine.MyOrderAty;
import com.xhxkj.zhcs.activity.mine.MyWalletAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.presenter.MineFgmPst;
import com.xhxkj.zhcs.util.AppLog;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.view.FTextView;
import com.xhxkj.zhcs.vm.MineFgmView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的 fragment
 *
 * @author 王鑫
 */
public class MineFgm extends BaseFgm implements MineFgmView {

    public static final int REQUEST_CODE = 101;
    public static final int MODIFY_TEL = 102;
    public static final int MODIFY_DEFAULT_ADDRESS = 103;
    MineFgmPst pst;

    @Bind(R.id.tvUsername)
    TextView tvName;
    @Bind(R.id.tvTelBound)
    TextView tvTel;
    @Bind(R.id.ftvAddress)
    FTextView ftvAddress;
    @Bind(R.id.appActionBar)
    AppActionBar appActionBar;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_mine;
    }

    @Override
    protected void initPresenter() {
        pst = new MineFgmPst();
        pst.attachView(this);
    }

    @Override
    protected void initView() {
        appActionBar.setActionBarTitle("我的");
        appActionBar.hideBtnBack();
        appActionBar.hideBtnCustom();
        tvName.setText(UserEntity.getName());
        tvTel.setText(UserEntity.getTel());
        ftvAddress.setText(UserEntity.getDefaultAddress());
    }

    /**
     * 跳啊 跳啊 我的骄傲放纵~
     *
     * @param v 被点击的view
     */
    @OnClick({R.id.layoutMyAccount, R.id.layoutMyWallet, R.id.layoutAddress, R.id.layoutCollection, R.id.layoutTheOrder})
    public void goOtherAty(View v) {
        switch (v.getId()) {
            case R.id.layoutMyAccount:
                goActivityForResult(MyAccountAty.class, REQUEST_CODE);
                break;
            case R.id.layoutMyWallet:
                goActivity(MyWalletAty.class);
                break;
            case R.id.layoutAddress:
                goActivityForResult(MyAddressAty.class, REQUEST_CODE);
                break;
            case R.id.layoutCollection:
                goActivity(MyCollectionAty.class);
                break;
            case R.id.layoutTheOrder:
                goActivity(MyOrderAty.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        AppLog.d("MineFgm", "onActivityResult", "requestCode = " + requestCode);
        AppLog.d("MineFgm", "onActivityResult", "resultCode = " + resultCode);
        switch (resultCode) {
            case MODIFY_TEL:
                tvTel.setText(UserEntity.getTel());
                break;
            case MODIFY_DEFAULT_ADDRESS:
                ftvAddress.setText(UserEntity.getDefaultAddress());
                break;

        }
    }
}
