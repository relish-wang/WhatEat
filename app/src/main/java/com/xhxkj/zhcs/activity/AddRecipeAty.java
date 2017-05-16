package com.xhxkj.zhcs.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.view.AppActionBar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-首页-私人菜单-添加菜单
 *
 * @author 魏一凡
 */
public class AddRecipeAty extends BaseAty {

    @Bind(R.id.etRecipeName)
    EditText etRecipeName;

    @Override
    protected int layoutResId() {
        return R.layout.aty_add_recipe;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setBtnBackText("取消");
        appActionBar.setActionBarTitle("菜谱名称");
        appActionBar.setBtnCustomText("确定");
        appActionBar.setBtnCustomOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipeName = etRecipeName.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.recipe_name), recipeName);
                goActivity(EditDraftAty.class, bundle);
            }
        });
    }

    @Override
    protected void initViews() {
    }


    @OnClick(R.id.btnDraft)
    public void goDraft() {
        goActivity(DraftBoxAty.class);
    }

}
