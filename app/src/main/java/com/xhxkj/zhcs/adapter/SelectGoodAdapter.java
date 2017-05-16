package com.xhxkj.zhcs.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xhxkj.zhcs.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选择商品适配器
 * @author 王鑫
 */
public class SelectGoodAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    /**
     * 加载的数据
     */
    private ArrayList<HashMap<String, Object>> mData;
    /**
     * 选择的数据
     */
    private ArrayList<HashMap<String, Object>> selectData;


    public SelectGoodAdapter(Activity activity, ArrayList<HashMap<String, Object>> mData) {
        this.mInflater = activity.getLayoutInflater();
        this.mData = mData;
        selectData = new ArrayList<>();
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
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.lv_item_select_goods, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvGoodName.setText(mData.get(position).get("good_name") + "");
        viewHolder.tvGoodWeight.setText("" + mData.get(position).get("good_weight"));
        return view;
    }

    public void setData(ArrayList<HashMap<String,Object>> data) {
        mData = data;
        notifyDataSetChanged();
    }


    class ViewHolder {
        @Bind(R.id.tvGoodName)
        TextView tvGoodName;
        @Bind(R.id.tvGoodWeight)
        TextView tvGoodWeight;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ArrayList<HashMap<String, Object>> getSelectData() {
        return selectData;
    }

    public void setSelectData(ArrayList<HashMap<String, Object>> selectData) {
        this.selectData = selectData;
    }

    public ArrayList<HashMap<String, Object>> getData(){
        return mData;
    }

}
