package com.xhxkj.zhcs.activity.mine;

import android.widget.EditText;

import com.xhxkj.zhcs.App;
import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.LoginAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.presenter.ModifyPwdAtyPst;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.ModifyPwdAtyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面(aty)-我的(fgm)-我的账户(aty)-修改密码(aty)
 *
 * @author 吴定伟
 */
public class ModifyPwdAty extends BaseAty implements ModifyPwdAtyView {
    @Bind(R.id.etOldPwd)
    EditText etOldPwd;

    @Bind(R.id.etNewPwd)
    EditText etNewPwd;

    @Bind(R.id.etRepeatPwd)
    EditText etRepeatPwd;

    ModifyPwdAtyPst pst;

    @Override
    protected int layoutResId() {
        return R.layout.aty_modify_pwd;
    }

    @Override
    protected void initPresenter() {
        pst = new ModifyPwdAtyPst();
        pst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.modify_pwd));
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btnSubmit)
    public void submit() {
        String oldPwd = etOldPwd.getText().toString();
        String newPwd = etNewPwd.getText().toString();
        String repeatPwd = etRepeatPwd.getText().toString();
        pst.modifyPwd(oldPwd, newPwd, repeatPwd);
    }


    @Override
    public void onModifyPwdSuccess() {
        App.clearActivities();
        goActivity(LoginAty.class);
    }
}
