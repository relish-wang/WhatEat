package com.xhxkj.zhcs.activity.homepage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.AddRecipeAty;
import com.xhxkj.zhcs.activity.DraftBoxAty;
import com.xhxkj.zhcs.activity.RecipeAty;
import com.xhxkj.zhcs.adapter.RecipeAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.RecipeEntity;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 主界面-首页-私房菜单
 *
 * @author 魏一凡
 */
public class PrivateMenuAty extends BaseAty {

    @Bind(R.id.lvRecipes)
    ListView lvRecipes;
    ArrayList<RecipeEntity> recipes;


    @Override
    protected int layoutResId() {
        return R.layout.aty_private_menu;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.private_menu));
        appActionBar.setBtnCustomText("草稿箱");
        appActionBar.setBtnCustomOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(DraftBoxAty.class);
            }
        });
    }

    @Override
    protected void initViews() {
        recipes = TempData.getPrivateMenus();
        RecipeAdapter adapter = new RecipeAdapter(this, recipes);
        lvRecipes.setAdapter(adapter);
    }


    @OnClick(R.id.btnAddMenu)
    public void AddMenu() {
        goActivity(AddRecipeAty.class);
    }

    @OnItemClick(R.id.lvRecipes)
    public void viewRecipe(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipe", recipes.get(position));//TODO 菜谱
        goActivity(RecipeAty.class, bundle);
    }


}
