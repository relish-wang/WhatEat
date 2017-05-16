package com.xhxkj.zhcs.activity.mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.view.AppActionBar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面-我的-我的家庭组
 *
 * @author 吴定伟
 */
public class AddFamilyMemberAty extends BaseAty {
    @Bind(R.id.btnSearch)
    Button btnSearch;

    @Bind(R.id.etInputName)
    EditText etInputName;

    @Bind(R.id.rlSearchResult)
    RelativeLayout rlSearchResult;

    @Override
    protected int layoutResId() {
        return R.layout.aty_add_family;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setActionBarTitle("搜索");
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btnSearch)
    public void search(){
        rlSearchResult.setVisibility(View.VISIBLE);
    }

}
