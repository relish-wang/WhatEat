package com.xhxkj.zhcs.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.MaterialEntity;
import com.xhxkj.zhcs.entity.RecipeEntity;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 菜谱适配器
 * <p/>
 * 草稿箱、收藏、私房菜都用到
 * Created by r3lish on 2016/2/29.
 */
public class RecipeAdapter extends BaseHolderAdapter<RecipeAdapter.ViewHolder, RecipeEntity> {


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