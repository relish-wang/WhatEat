package com.xhxkj.zhcs.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.MaterialAdapter;
import com.xhxkj.zhcs.adapter.StepAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.MaterialEntity;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 主界面-首页-私房菜单-添加菜单-添加原料种类
 *
 * @author 魏一凡
 */
public class EditDraftAty extends BaseAty {


    @Bind(R.id.lvMaterials)
    ListView lvMaterials;
    @Bind(R.id.lvSteps)
    ListView lvSteps;

    @Override
    protected int layoutResId() {
        return R.layout.aty_edit_draft;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hide();
    }

    protected void initViews() {
        MaterialAdapter materialAdapter = new MaterialAdapter(this, TempData.getMaterials());
        lvMaterials.setAdapter(materialAdapter);
        StepAdapter stepAdapter = new StepAdapter(this, TempData.getRecipes().get(0).getSteps());
        lvSteps.setAdapter(stepAdapter);
    }


    @OnItemClick(R.id.lvMaterials)
    public void chooseKind(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("materials", new ArrayList<MaterialEntity>());
        goActivity(AddMaterialAty.class, bundle);
    }


}
