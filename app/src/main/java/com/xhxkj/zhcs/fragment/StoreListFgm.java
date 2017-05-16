package com.xhxkj.zhcs.fragment;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.CollectionAdapter;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.temp.ShopBeanDe;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.FTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 王鑫
 */
public class StoreListFgm extends BaseFgm {


    @Bind(R.id.lvStores)
    ListView lvStores;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_store_list;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    @Override
    protected void initView() {
        StoreAdapter adapter = new StoreAdapter(getActivity(), TempData.getStores());
        lvStores.setAdapter(adapter);
    }

    /**
     * 店铺适配器
     */
    class StoreAdapter extends CollectionAdapter {

        ArrayList<ShopBeanDe> mData;
        LayoutInflater inflater;

        public StoreAdapter(Activity aty, ArrayList<ShopBeanDe> mData) {
            this.mData = mData;
            inflater = aty.getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.lv_item_store, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            ShopBeanDe store = mData.get(position);
            holder.ftvMarketName.setText(store.getName());
            holder.ftvDistance.setText(distanceStatement(store.getDistance()));
            holder.ftvStatistics.setText(makeUpStatistics(store.getHireCount(), store.getScore()));
            return view;
        }

        class ViewHolder {
            @Bind(R.id.ftvMarketName)
            FTextView ftvMarketName;
            @Bind(R.id.tvDistance)
            FTextView ftvDistance;
            @Bind(R.id.ftvStatistics)
            FTextView ftvStatistics;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        private String distanceStatement(int distance) {
            return "距离我家" + distance + "米";
        }
    }

}