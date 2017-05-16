package com.xhxkj.zhcs.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.presenter.EditAddressAtyPst;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.EditAddressAtyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-我的-我的地址-编辑收货地址
 *
 * @author 王鑫
 */
public class ModifyAddressAty extends BaseAty implements EditAddressAtyView {

    EditAddressAtyPst pst;
    AddressEntity entity;

    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etTel)
    EditText etTel;
    @Bind(R.id.etAddress)
    EditText etAddress;

    @Override
    protected int layoutResId() {
        return R.layout.aty_modify_address;
    }

    @Override
    protected void initPresenter() {
        pst = new EditAddressAtyPst();
        pst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.edit_address));
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.entity = (AddressEntity) bundle.getSerializable("address");
        if (entity != null) {
            etName.setText(entity.getName());
            etName.setHint(entity.getName());
            etTel.setText(entity.getTel());
            etTel.setHint(entity.getTel());
            etAddress.setText(entity.getAddress());
            etAddress.setHint(entity.getAddress());
        }
    }


    @OnClick(R.id.btnSubmit)
    public void modifyAddress() {
        entity.setName(etName.getText().toString());
        entity.setTel(etTel.getText().toString());
        entity.setAddress(etAddress.getText().toString());
        pst.modifyAddress(entity);
    }

    @Override
    public void onModifyAddressSuccess() {
        setResult(MyAddressAty.MODIFY_ADDRESS);
        finish();
    }
}
