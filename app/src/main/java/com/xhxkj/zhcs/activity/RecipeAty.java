package com.xhxkj.zhcs.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.MaterialAdapter;
import com.xhxkj.zhcs.adapter.StepAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.RecipeEntity;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.view.Lv4Scroll;

import butterknife.Bind;

/**
 * Created by r3lish on 2016/2/29.
 */
public class RecipeAty extends BaseAty {

    RecipeEntity recipe;

    @Bind(R.id.ivRecipe)
    ImageView ivRecipe;
    @Bind(R.id.tvRecipeName)
    TextView tvRecipeName;
    @Bind(R.id.tvComment)
    TextView tvComment;
    @Bind(R.id.tvWeeklyFrequency)
    TextView tvWeeklyFrequency;
    @Bind(R.id.tvIntroduce)
    TextView tvIntroduce;

    @Bind(R.id.tvAuthorName)
    TextView tvAuthorName;
    @Bind(R.id.tvCreateTime)
    TextView tvCreateTime;

    @Bind(R.id.btnCollect)
    Button btnCollect;

    @Bind(R.id.lvMaterials)
    Lv4Scroll lvMaterials;
    @Bind(R.id.lvSteps)
    Lv4Scroll lvSteps;
    @Bind(R.id.sv)
    ScrollView sv;

    @Override
    protected int layoutResId() {
        return R.layout.aty_recipe;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            recipe = (RecipeEntity) bundle.get("recipe");
        }
        if (recipe == null) return;
        appActionBar.setActionBarTitle(recipe.getName());
        appActionBar.hideBtnCustom();
    }

    @Override
    protected void initViews() {
        if (recipe != null) {
            ivRecipe.setImageResource(recipe.getIconResId());
            tvRecipeName.setText(recipe.getName());
            tvComment.setText(makeupComment(recipe.getComment()));
            tvWeeklyFrequency.setText(makeupWeeklyFrequency(recipe.getWeeklyFrequency()));
            tvIntroduce.setText(recipe.getIntroduce());
            tvAuthorName.setText(recipe.getAuthorName());
            tvCreateTime.setText(makeupCreateTime(recipe.getCreateTime()));

            MaterialAdapter materialAdapter = new MaterialAdapter(this, recipe.getMaterials());
            lvMaterials.setAdapter(materialAdapter);
            lvMaterials.setEnabled(false);

            StepAdapter stepAdapter = new StepAdapter(this, recipe.getSteps());
            lvSteps.setAdapter(stepAdapter);
            lvSteps.setEnabled(false);

            sv.smoothScrollTo(0, 0);
        }
    }


    private String makeupComment(Double comment) {
        return comment + "综合评分";
    }

    private String makeupWeeklyFrequency(Integer weeklyFrequency) {
        return weeklyFrequency + "人最近7天内做过";
    }

    private String makeupCreateTime(String createTime) {
        return "创建于" + createTime;
    }


}
