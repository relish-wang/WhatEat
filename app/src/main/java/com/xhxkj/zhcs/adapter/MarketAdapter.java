package com.xhxkj.zhcs.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.ViewMapAty;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.MarketEntity;
import com.xhxkj.zhcs.view.FTextView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * market列表适配器（调用3次）
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/11/9.
 * @deprecated 到时候复制过去吧
 */
public class MarketAdapter extends BaseHolderAdapter<MarketAdapter.ViewHolder, MarketEntity> {

    public MarketAdapter(BaseAty context, ArrayList<MarketEntity> mData) {
        super(context, mData);
    }

    @Override
    protected int itemLayoutId() {
        return R.layout.lv_item_market;
    }

    @Override
    protected void setItemData(ViewHolder holder, MarketEntity market, int position) {
        holder.ivMarketIcon.setImageResource(market.getIconResId());
        holder.ftvMarketName.setText(market.getName());
        holder.ftvDistance.setText(CollectionAdapter.makeUpDistance(market.getDistance()));
        holder.ftvStatistics.setText(CollectionAdapter.makeUpStatistics(market.getTimes(), market.getComment()));
        holder.btnViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aty.goActivity(ViewMapAty.class);
            }
        });
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    class ViewHolder extends BaseViewHolder {
        @Bind(R.id.ivMarketIcon)
        ImageView ivMarketIcon;
        @Bind(R.id.ftvMarketName)
        FTextView ftvMarketName;
        @Bind(R.id.tvDistance)
        FTextView ftvDistance;
        @Bind(R.id.ftvStatistics)
        FTextView ftvStatistics;
        @Bind(R.id.btnViewMap)
        Button btnViewMap;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
