package com.xhxkj.zhcs.adapter;

/**
 * Created by r3lish on 2016/2/29.
 */

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.TextAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 菜谱步骤适配器
 */
public class StepAdapter extends BaseHolderAdapter<StepAdapter.ViewHolder, String> {

    public StepAdapter(BaseAty aty, ArrayList<String> mData) {
        super(aty, mData);
    }

    @Override
    protected int itemLayoutId() {
        return R.layout.lv_item_step;
    }

    @Override
    protected void setItemData(StepAdapter.ViewHolder holder, String step, int position) {
        holder.tvStepNum.setText((position + 1) + "");
        if (TextUtils.isEmpty(step)) {
            holder.rlIvStep.setVisibility(View.VISIBLE);
            holder.llAddStep.setVisibility(View.VISIBLE);
            holder.llAddStep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    aty.goActivity(TextAty.class,bundle);
                }
            });
            holder.tvStep.setVisibility(View.GONE);
        } else {
            holder.rlIvStep.setVisibility(View.GONE);
            holder.llAddStep.setVisibility(View.GONE);
            holder.tvStep.setText(step);
            holder.tvStep.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected StepAdapter.ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseViewHolder {

        @Bind(R.id.tvStepNum)
        TextView tvStepNum;
        @Bind(R.id.tvStep)
        TextView tvStep;
        @Bind(R.id.ivStep)
        ImageView ivStep;
        @Bind(R.id.llAddStep)
        LinearLayout llAddStep;
        @Bind(R.id.rlIvStep)
        RelativeLayout rlIvStep;

        public ViewHolder(View view) {
            super(view);
        }
    }
}