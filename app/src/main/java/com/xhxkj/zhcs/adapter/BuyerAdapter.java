package com.xhxkj.zhcs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.temp.BuyerBean;
import com.xhxkj.zhcs.view.FTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 买手列表适配器（2次调用）
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/11/9.
 */
public class BuyerAdapter extends CollectionAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<BuyerBean> mData;

    public BuyerAdapter(Context context, ArrayList<BuyerBean> mData) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.lv_item_buyer, viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.ftvBuyerName.setText(String.valueOf(mData.get(i).getName()));
        holder.tvStar.setBackgroundResource(mData.get(i).isCollected()?R.color.app_theme_color:R.color.gray);
        holder.tvStar.setText(mData.get(i).isCollected()?"已收藏":"未收藏");
        holder.ftvStatistics.setText(makeUpStatistics(
                mData.get(i).getBuyCount(), //购买次数
                mData.get(i).getComment()));//平均评分
        return view;
    }

    class ViewHolder {
        @Bind(R.id.ftvBuyerName)
        FTextView ftvBuyerName;
        @Bind(R.id.tvStar)
        TextView tvStar;
        @Bind(R.id.ftvStatistics)
        FTextView ftvStatistics;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
