package com.xhxkj.zhcs.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.EditText;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.presenter.LoginAtyPst;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.LoginAtyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登录界面
 *
 * @author 王鑫
 */
public class LoginAty extends BaseAty implements LoginAtyView {
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etPwd)
    EditText etPwd;

    private LoginAtyPst loginAtyPst;

    @Override
    protected int layoutResId() {
        return R.layout.aty_login;
    }

    @Override
    protected void initPresenter() {
        loginAtyPst = new LoginAtyPst();
        loginAtyPst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hide();
    }

    @Override
    protected void initViews() {
        etName.setText(UserEntity.getName());

        Intent intent = getIntent();
        String name = intent.getStringExtra(UserEntity.NAME);
        if (!TextUtils.isEmpty(name)) {
            etName.setText(name);
        }

        Drawable daName = getResources().getDrawable(R.drawable.login_name_image);
        if (daName != null) {
            daName.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
            etName.setCompoundDrawables(daName, null, null, null);//只放左边
        }

        Drawable daPwd = getResources().getDrawable(R.drawable.login_pwd_image);
        if (daPwd != null) {
            daPwd.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
            etPwd.setCompoundDrawables(daPwd, null, null, null);//只放左边
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RegisterAty.REQUEST_CODE_SUCCESS) {
            String name = "";
            if (data != null) {
                name = data.getStringExtra(UserEntity.NAME);
            }
            etName.setText(name);
            //etPwd.setText(pwd);
        }
    }


    /**
     * 登录
     */
    @OnClick(R.id.btnLogin)
    public void login() {
        loginAtyPst.login(etName.getText().toString(), etPwd.getText().toString());
    }

    /**
     * 新用户
     */
    @OnClick(R.id.tvNewUser)
    public void newUser() {
        goActivityForResult(RegisterAty.class, RegisterAty.REQUEST_CODE_SUCCESS);
    }

    /**
     * 忘记密码
     */
    @OnClick(R.id.tvForget)
    public void forget() {

    }

    @Override
    public void onLoginSuccess() {
        goActivity(MainAty.class);
        finish();
    }
}