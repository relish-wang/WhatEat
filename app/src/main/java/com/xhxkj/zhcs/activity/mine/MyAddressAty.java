package com.xhxkj.zhcs.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.AddressEntity;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.fragment.MineFgm;
import com.xhxkj.zhcs.presenter.MyAddressAtyPst;
import com.xhxkj.zhcs.util.AppLog;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.view.FTextView;
import com.xhxkj.zhcs.vm.MyAddressAtyView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 管理收货地址
 *
 * @author 王鑫
 */
public class MyAddressAty extends BaseAty implements MyAddressAtyView {

    public static final int REQUEST_CODE = 201;
    public static final int MODIFY_ADDRESS = 202;
    public static final int ADD_ADDRESS = 203;

    MyAddressAtyPst pst;
    AddressAdapter adapter;
    @Bind(R.id.lvAddresses)
    ListView lvAddresses;

    @Override
    protected int layoutResId() {
        return R.layout.aty_my_address;
    }

    @Override
    protected void initPresenter() {
        pst = new MyAddressAtyPst();
        pst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.my_address));
    }

    @Override
    protected void initViews() {
        adapter = new AddressAdapter(this, UserEntity.getAddresses());
        lvAddresses.setAdapter(adapter);
        if (UserEntity.getAddresses().isEmpty()) {
            pst.getAddressList(UserEntity.getSessionId());
        }
    }


    @OnClick(R.id.llAddAddress)
    public void addAddress() {
        goActivityForResult(AddAddressAty.class, REQUEST_CODE);
    }

    @Override
    public void finish() {
        setResult(MineFgm.MODIFY_DEFAULT_ADDRESS);
        super.finish();
    }

    @Override
    public void updateAddresses(ArrayList<AddressEntity> addresses) {
        if (addresses == null) return;
        Collections.sort(addresses);
        UserEntity.setAddresses(addresses);
        adapter.update(addresses);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        AppLog.d("MyAddressAty", "onActivityResult", "#resultCode = " + resultCode);
        switch (resultCode) {
            case ADD_ADDRESS:
            case MODIFY_ADDRESS:
                pst.getAddressList(UserEntity.getSessionId());
                break;
        }

    }

    /**
     * 地址列表适配器
     */
    class AddressAdapter extends BaseHolderAdapter<AddressAdapter.ViewHolder, AddressEntity> {

        public AddressAdapter(BaseAty context, ArrayList<AddressEntity> mData) {
            super(context, mData);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.lv_item_address;
        }

        @Override
        protected void setItemData(ViewHolder holder, final AddressEntity entity,int position) {
            holder.tvName.setText(entity.getName());
            holder.tvTel.setText(entity.getTel());
            holder.ftvAddress.setText(entity.getAddress());
            holder.rdBtnSetAsDefault.setChecked(entity.getIsDefault());
            holder.rdBtnSetAsDefault.setText(entity.getIsDefault() ? "默认地址" : "设为默认");
            holder.rdBtnSetAsDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pst.setAsDefault(UserEntity.getSessionId(), entity.getId());
                }
            });
            holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("address", entity);
                    goActivityForResult(ModifyAddressAty.class, bundle, REQUEST_CODE);
                }
            });
            holder.tvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pst.deleteAddress(entity);
                }
            });
        }

        @Override
        protected ViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }


        class ViewHolder extends BaseViewHolder {
            @Bind(R.id.tvName)
            TextView tvName;
            @Bind(R.id.tvTel)
            TextView tvTel;
            @Bind(R.id.ftvAddress)
            FTextView ftvAddress;

            @Bind(R.id.rdBtnSetAsDefault)
            RadioButton rdBtnSetAsDefault;
            @Bind(R.id.tvEdit)
            TextView tvEdit;
            @Bind(R.id.tvDel)
            TextView tvDel;

            public ViewHolder(View view) {
                super(view);
            }
        }


    }

}
