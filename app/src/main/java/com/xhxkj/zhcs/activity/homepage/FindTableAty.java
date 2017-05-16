package com.xhxkj.zhcs.activity.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.MaterialEntity;
import com.xhxkj.zhcs.entity.RecipeEntity;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 主界面-首页-发现餐桌
 *
 * @author 魏一凡
 */
public class FindTableAty extends BaseAty {

    @Bind(R.id.lvRecipes)
    ListView lvDishes;

    @Override
    protected int layoutResId() {
        return R.layout.aty_find_table;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.find_table));
    }

    @Override
    protected void initViews() {
        RecipeAdapter adapter = new RecipeAdapter(this, TempData.getRecipes());
        lvDishes.setAdapter(adapter);
    }


    /**
     * 餐桌适配器
     */
    class RecipeAdapter extends BaseHolderAdapter<RecipeAdapter.ViewHolder, RecipeEntity> {


        public RecipeAdapter(BaseAty context, ArrayList<RecipeEntity> mData) {
            super(context, mData);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.lv_item_recipe;
        }

        @Override
        protected void setItemData(ViewHolder holder, RecipeEntity recipe, int position) {
            holder.ivDish.setImageResource(recipe.getIconResId());
            holder.tvDishName.setText(recipe.getName());
            holder.tvDishMaterial.setText(makeupMaterials(recipe.getMaterials()));
            holder.tvDishComment.setText(recipe.getComment() + "");
            holder.tvWeeklyFrequency.setText("（七天内" + recipe.getWeeklyFrequency() + "人做过");
            holder.tvAuthorName.setText(recipe.getAuthorName());
        }

        private String makeupMaterials(ArrayList<MaterialEntity> materials) {
            if (materials == null || materials.size() == 0) return "";
            StringBuffer sb = new StringBuffer(materials.get(0).getName() + materials.get(0).getWeight());
            if (materials.size() > 1) {
                for (int i = 0; i < materials.size(); i++) {
                    sb.append("、");
                    sb.append(materials.get(i).getName());
                    sb.append(materials.get(i).getWeight());
                }
            }
            return sb.toString();
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
            @Bind(R.id.tvDishMaterial)
            TextView tvDishMaterial;
            @Bind(R.id.tvDishComment)
            TextView tvDishComment;
            @Bind(R.id.tvWeeklyFrequency)
            TextView tvWeeklyFrequency;
            @Bind(R.id.tvAuthorName)
            TextView tvAuthorName;


            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
