package com.xhxkj.zhcs.activity.mine;

import android.widget.EditText;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.mine.MyAddressAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.presenter.AddAddressAtyPst;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.AddAddressAtyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-我的-我的地址-新增收货地址
 *
 * @author 王鑫
 */
public class AddAddressAty extends BaseAty implements AddAddressAtyView {

    AddAddressAtyPst pst;

    @Bind(R.id.etName)
    EditText etName;

    @Bind(R.id.etTel)
    EditText etTel;

    @Bind(R.id.etAddress)
    EditText etAddress;

    @Override
    protected int layoutResId() {
        return R.layout.aty_add_address;
    }

    @Override
    protected void initPresenter() {
        pst = new AddAddressAtyPst();
        pst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.add_address));
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btnSubmit)
    public void addAddress() {
        pst.addAddress(
                etName.getText().toString(),
                etTel.getText().toString(),
                etAddress.getText().toString());
    }


    @Override
    public void onAddAddressSuccess() {
        setResult(MyAddressAty.ADD_ADDRESS);
        finish();
    }
}