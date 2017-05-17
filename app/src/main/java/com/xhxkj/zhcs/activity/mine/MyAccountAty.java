package com.xhxkj.zhcs.activity.mine;

import android.content.Intent;
import android.widget.TextView;

import com.xhxkj.zhcs.App;
import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.LoginAty;
import com.xhxkj.zhcs.activity.homepage.MyFamilyGroupAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.fragment.MineFgm;
import com.xhxkj.zhcs.presenter.MyAccountAtyPst;
import com.xhxkj.zhcs.util.AppLog;
import com.xhxkj.zhcs.util.AppPreference;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.MyAccountAtyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-我的-我的账户
 *
 * @author 吴定伟
 */
public class MyAccountAty extends BaseAty implements MyAccountAtyView {

    public static final int REQUEST_CODE = 0x000;
    public static final int MODIFY_TEL = 0x001;
    public static final int MODIFY_DEFAULT_ADDRESS = 0x002;

    MyAccountAtyPst pst;
    @Bind(R.id.tvUsername)
    TextView tvName;

    @Bind(R.id.tvTelBound)
    TextView tvTel;

    @Override
    protected int layoutResId() {
        return R.layout.aty_my_account;
    }

    @Override
    protected void initPresenter() {
        pst = new MyAccountAtyPst();
        pst.attachView(this);
    }

    @Override

    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setBtnBackText("我的");
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.my_account));
    }

    @Override
    protected void initViews() {
        tvName.setText(UserEntity.getName());
        tvTel.setText(UserEntity.getTel());
    }

    @OnClick(R.id.flModifyPwd)
    public void goModifyPwd() {
        goActivity(ModifyPwdAty.class);
    }

    @OnClick(R.id.flTel)
    public void goTel() {
        goActivityForResult(ModifyTelAty.class, REQUEST_CODE);
    }

    @OnClick(R.id.flFamilyGroup)
    public void goFamily() {
        goActivity(MyFamilyGroupAty.class);
    }

    @OnClick(R.id.btnLogout)
    public void logout() {
        pst.logout(UserEntity.getSessionId());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        AppLog.d("MyAccountAty", "onActivityResult", "#resultCode = " + resultCode);
        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case MODIFY_TEL:
                    tvTel.setText(UserEntity.getTel());
                    break;
            }
        }
    }

    @Override
    public void onLogoutSuccess() {
        AppPreference.clear();
        App.clearActivities();
        AppPreference.put("autoLogin", false);
        Intent intent = new Intent(this,LoginAty.class);
        intent.putExtra(UserEntity.NAME,UserEntity.getName());
        startActivity(intent);
    }

    @Override
    public void finish() {
        setResult(MineFgm.MODIFY_TEL);
        super.finish();
    }
}
