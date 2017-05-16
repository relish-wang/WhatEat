package com.xhxkj.zhcs.activity.homepage;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.FoodDetailsAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.MaterialEntity;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 主界面-首页-网上选菜
 *
 * @author 魏一凡
 */
public class ChooseFoodAty extends BaseAty {

    @Bind(R.id.lvKinds)
    ListView lvKinds;

    ArrayList<MaterialEntity> mData;

    @Override
    protected int layoutResId() {
        return R.layout.aty_choose_food;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.choose_food));
    }

    @Override
    protected void initViews() {
        mData = TempData.getMaterials();
        MaterialAdapter adapter = new MaterialAdapter(this, mData);
        lvKinds.setAdapter(adapter);
    }

    @OnItemClick(R.id.lvKinds)
    public void chooseKind(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.btn_back_text), getString(R.string.choose_food));
        bundle.putString(getString(R.string.titleText), mData.get(position).getName());
        goActivity(FoodDetailsAty.class, bundle);
    }

    class MaterialAdapter extends BaseHolderAdapter<MaterialAdapter.ViewHolder, MaterialEntity> {


        public MaterialAdapter(BaseAty context, ArrayList<MaterialEntity> mData) {
            super(context, mData);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.lv_item_table;
        }

        @Override
        protected void setItemData(ViewHolder holder, MaterialEntity dishName, int position) {
            holder.tvDishName.setText(dishName.getName());
        }

        @Override
        protected ViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }

        class ViewHolder extends BaseViewHolder {
            @Bind(R.id.tvDishName)
            TextView tvDishName;

            public ViewHolder(View view) {
                super(view);
            }
        }
    }

}
