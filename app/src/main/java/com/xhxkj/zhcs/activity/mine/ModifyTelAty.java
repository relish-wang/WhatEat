package com.xhxkj.zhcs.activity.mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.presenter.ModifyTelAtyPst;
import com.xhxkj.zhcs.util.StringUtil;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.ModifyTelAtyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-我的-我的账户-更改联系方式
 *
 * @author 吴定伟
 */
public class ModifyTelAty extends BaseAty implements ModifyTelAtyView {

    ModifyTelAtyPst modifyTelAtyPst;

    @Bind(R.id.tvOldPhone)
    TextView tvOldPhone;

    @Bind(R.id.etInputPsd)
    EditText etInputPsd;

    @Bind(R.id.etNewTel)
    EditText etNewTel;

    @Bind(R.id.btnGetPsw)
    Button btnGetPsw;

    @Override
    protected int layoutResId() {
        return R.layout.aty_modify_tel;
    }

    @Override
    protected void initPresenter() {
        modifyTelAtyPst = new ModifyTelAtyPst();
        modifyTelAtyPst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.modify_phone));
        appActionBar.setBtnBackOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initViews() {
        tvOldPhone.setText(StringUtil.formatPhone(UserEntity.getTel()));
    }

    @OnClick(R.id.btnSubmit)
    public void modifyTel() {
        modifyTelAtyPst.modifyTel(UserEntity.getSessionId(), etNewTel.getText().toString().trim());
    }


    @Override
    public void onModifySuccess() {
        setResult(MyAccountAty.MODIFY_TEL);
        finish();
    }
}
