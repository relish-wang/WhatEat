package com.xhxkj.zhcs.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.FoodEntity;
import com.xhxkj.zhcs.temp.TempData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 菜市场菜品列表Fragment
 */
public class MarketFoodListFgm extends BaseFgm {

    @Bind(R.id.lvRecipes)
    ListView lvFood;


    @Override
    protected int layoutResId() {
        return R.layout.fgm_market_food_list;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    @Override
    protected void initView() {
        FoodAdapter adapter = new FoodAdapter((BaseAty) getActivity(), TempData.getDishes());
        lvFood.setAdapter(adapter);
    }

    @OnItemClick(R.id.lvRecipes)
    public void goOptions(int position) {
        ((MarketNearbyAty) getActivity()).selectPage(MarketNearbyAty.INDEX_OPTION);
    }

    class FoodAdapter extends BaseHolderAdapter<FoodAdapter.ViewHolder, FoodEntity> {

        public FoodAdapter(BaseAty context, ArrayList<FoodEntity> mData) {
            super(context, mData);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.lv_item_table;
        }

        @Override
        protected void setItemData(ViewHolder holder, FoodEntity data, int position) {
            holder.ivDish.setImageResource(data.getIconResId());
            holder.tvDishName.setText(data.getFoodName());
        }

        @Override
        protected ViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }

        class ViewHolder extends BaseViewHolder {
            @Bind(R.id.ivDish)
            ImageView ivDish;
            @Bind(R.id.tvDishName)
            TextView tvDishName;

            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
