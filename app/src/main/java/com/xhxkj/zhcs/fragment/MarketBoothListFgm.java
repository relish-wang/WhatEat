package com.xhxkj.zhcs.fragment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.FTextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * 菜市场摊位列表Fragment.
 */
public class MarketBoothListFgm extends BaseFgm {

    @Bind(R.id.lvBooths)
    ListView lvBooths;

    View view;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_market_booth_list;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    @Override
    protected void initView() {
        BoothsAdapter adapter = new BoothsAdapter(getActivity(), TempData.getBooths());
        lvBooths.setAdapter(adapter);
    }

    @OnItemClick(R.id.lvBooths)
    public void goBuyers(int position){
        ((MarketNearbyAty)getActivity()).selectPage(MarketNearbyAty.INDEX_DISH_LIST);
    }

    class BoothsAdapter extends BaseAdapter {

        private static final String BOOTH_NAME = "booth_name";
        private static final String PRICE = "price";
        private static final String STATISTICS = "statistics";


        Context context;
        LayoutInflater inflater;
        ArrayList<HashMap<String, Object>> mData;

        public BoothsAdapter(Context context, ArrayList<HashMap<String, Object>> mData) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return mData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            ViewHolder holder = null;
            if (view == null) {
                view = inflater.inflate(R.layout.lv_item_booths, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.ftvBoothName.setText(String.valueOf(mData.get(position).get(BOOTH_NAME)));
            holder.ftvPrice.setText(String.valueOf(mData.get(position).get(PRICE)));
            holder.ftvStatistics.setText(String.valueOf(mData.get(position).get(STATISTICS)));
            return view;
        }

        class ViewHolder {
            @Bind(R.id.ftvBoothName)
            FTextView ftvBoothName;
            @Bind(R.id.ftvPrice)
            FTextView ftvPrice;
            @Bind(R.id.ftvStatistics)
            FTextView ftvStatistics;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }

        }
    }
}
