package com.xhxkj.zhcs.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.RecipeEntity;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 草稿
 * Created by r3lish on 2016/2/27.
 */
public class DraftBoxAty extends BaseAty {

    @Bind(R.id.lvDrafts)
    ListView lvDrafts;
    ArrayList<RecipeEntity> drafts;

    DraftAdapter adapter;

    @Override
    protected int layoutResId() {
        return R.layout.aty_draft_box;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setActionBarTitle("草稿");
    }

    @Override
    protected void initViews() {
        drafts = TempData.getDrafts();
        adapter = new DraftAdapter(this, drafts);
        lvDrafts.setAdapter(adapter);
    }

    @OnItemClick(R.id.lvDrafts)
    public void editDraft(int position){
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipe",drafts.get(position));//// TODO: 2016/2/29 菜谱
        goActivity(EditDraftAty.class, bundle);
    }

    class DraftAdapter extends BaseHolderAdapter<DraftAdapter.ViewHolder, RecipeEntity> {

        public DraftAdapter(BaseAty aty, ArrayList<RecipeEntity> mData) {
            super(aty, mData);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.lv_item_draft;
        }

        @Override
        protected void setItemData(DraftAdapter.ViewHolder holder, RecipeEntity draft,int position) {
            holder.ivDraft.setImageResource(draft.getIconResId());
            holder.tvDraft.setText(draft.getName());
            holder.tvSteps.setText(makeupSteps(draft.getSteps()));
        }

        @Override
        protected DraftAdapter.ViewHolder createViewHolder(View view) {
            return new ViewHolder(view);
        }

        private String makeupSteps(ArrayList<String> steps) {
            if (steps == null || steps.size() == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder(steps.get(0));
            if (steps.size() > 1) {
                for (int i = 0; i < steps.size(); i++) {
                    sb.append(" ");
                    sb.append(steps.get(i));
                }
            }
            return sb.toString();
        }

        class ViewHolder extends BaseViewHolder {

            @Bind(R.id.ivDraft)
            ImageView ivDraft;
            @Bind(R.id.tvDraft)
            TextView tvDraft;
            @Bind(R.id.tvSteps)
            TextView tvSteps;

            public ViewHolder(View view) {
                super(view);
            }
        }

    }
}
