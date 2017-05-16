package com.xhxkj.zhcs.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 主界面-首页-在线选菜-蔬菜-青菜
 *
 * @author 魏一凡
 */
public class FoodDetailsAty extends BaseAty {

    @Bind(R.id.lvFoodDetails)
    ListView lvFoodDetails;

    ArrayList<String> mData;

    @Override
    protected int layoutResId() {
        return R.layout.aty_food_details;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
    }

    @Override
    protected void initViews() {
        mData = TempData.getFoodDetails();
        FoodDetailAdapter adapter = new FoodDetailAdapter(this, mData);
        lvFoodDetails.setAdapter(adapter);
    }

    @OnItemClick(R.id.lvFoodDetails)
    public void buyIt(int position){
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.titleText),mData.get(position));
        goActivity(BuyFoodAty.class,bundle);
    }

    class FoodDetailAdapter extends BaseHolderAdapter<FoodDetailAdapter.ViewHolder, String> {

        public FoodDetailAdapter(BaseAty context, ArrayList<String> mData) {
            super(context, mData);
        }

        @Override
        protected void setItemData(ViewHolder holder, String data,int position) {
            holder.tvDishName.setText(data);
        }

        @Override
        protected ViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.lv_item_food_detail;
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
