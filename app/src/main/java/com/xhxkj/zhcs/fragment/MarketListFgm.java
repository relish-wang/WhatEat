package com.xhxkj.zhcs.fragment;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.ViewMapAty;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.adapter.CollectionAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.base.BaseHolderAdapter;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.entity.MarketEntity;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.FTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 菜市场列表Fragment.
 *
 * @author 王鑫
 */
public class MarketListFgm extends BaseFgm {


    @Bind(R.id.lvMarkets)
    ListView lvMarkets;


    @Override
    protected int layoutResId() {
        return R.layout.fgm_market_list;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    @OnItemClick(R.id.lvMarkets)
    public void goBooths(int position) {
        //TODO 传送摊位列表信息
        ((MarketNearbyAty) getActivity()).selectPage(MarketNearbyAty.INDEX_BOOTH_LIST);
    }

    @Override
    protected void initView() {
        MarketAdapter adapter = new MarketAdapter((BaseAty) getActivity(), TempData.getMarkets());
        lvMarkets.setAdapter(adapter);
    }

    public static class MarketAdapter extends BaseHolderAdapter<MarketAdapter.ViewHolder, MarketEntity> {

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
            holder.tvDistance.setText(CollectionAdapter.makeUpDistance(market.getDistance()));
            holder.ftvStatistics.setText(CollectionAdapter.makeUpStatistics(market.getTimes(), market.getComment()));
            holder.btnViewMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewMapAty.start(getContext());
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
            TextView tvDistance;
            @Bind(R.id.ftvStatistics)
            FTextView ftvStatistics;
            @Bind(R.id.btnViewMap)
            Button btnViewMap;

            public ViewHolder(View view) {
                super(view);
            }
        }
    }

}
