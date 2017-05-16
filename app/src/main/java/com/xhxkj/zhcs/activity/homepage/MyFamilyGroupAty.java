package com.xhxkj.zhcs.activity.homepage;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.mine.AddFamilyMemberAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.view.QRCodeDialog;
import com.xhxkj.zhcs.temp.MemberBeanDe;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-我的-我的家庭组
 *
 * @author 吴定伟
 */
public class MyFamilyGroupAty extends BaseAty {
    @Bind(R.id.lvFamily)
    ListView lvFamily;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected int layoutResId() {
        return R.layout.aty_my_family_group;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.family_group));
    }

    @Override
    protected void initViews() {
        FamilyMemberAdapter adapter = new FamilyMemberAdapter(this, TempData.getFamilyMembers());
        lvFamily.setAdapter(adapter);
    }

    @OnClick(R.id.flQRCode)
    public void showQRCode(){
        QRCodeDialog dialog = new QRCodeDialog();
        dialog.show(getSupportFragmentManager(),"QRCode");
    }

    @OnClick(R.id.flAddFamily)
    public void addFamily() {
        goActivity(AddFamilyMemberAty.class);
    }

    class FamilyMemberAdapter extends BaseHolderAdapter<FamilyMemberAdapter.ViewHolder,MemberBeanDe> {

        public FamilyMemberAdapter(BaseAty context, ArrayList<MemberBeanDe> mData) {
            super(context, mData);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.lv_item_family;
        }

        @Override
        protected void setItemData(ViewHolder holder, MemberBeanDe data,int position) {
            holder.tvFamilyName.setText(data.getNickname());
            holder.tvFamilyUser.setText(data.getPhone());
        }

        @Override
        protected ViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }

        class ViewHolder extends BaseViewHolder{
            @Bind(R.id.tvTelBound)
            TextView tvFamilyUser;
            @Bind(R.id.tvNickname)
            TextView tvFamilyName;

            public ViewHolder(View view) {
                super(view);
            }
        }

    }
}
