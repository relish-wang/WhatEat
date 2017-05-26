package com.xhxkj.zhcs.activity.homepage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
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

import static com.xhxkj.zhcs.util.D.user_info.tel;

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

    String tel;

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
            holder.btnTel.setOnClickListener(v -> {
                tel = data.getPhone();
                if(ContextCompat.checkSelfPermission(MyFamilyGroupAty.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MyFamilyGroupAty.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                    call();
                }
            });
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
            @Bind(R.id.btnTel)
            Button btnTel;

            public ViewHolder(View view) {
                super(view);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    call();
                }else{
                    showMessage("拒绝权限将无法使用该程序");
                }
                break;
        }
    }

    public void call(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        startActivity(intent);
    }
}
