package com.xhxkj.zhcs.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseViewHolder;
import com.xhxkj.zhcs.temp.GoodBean;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 主界面(aty)-我的(fgm)-我的订单(aty)-订单评价(aty)
 *
 * @author 王鑫
 */
public class GoodEvaluationAty extends BaseAty {

    @Bind(R.id.lvGoods)
    ListView lvGoods;

    @Override
    protected int layoutResId() {
        return R.layout.aty_good_comment;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnCustom();
        appActionBar.setActionBarTitle(getString(R.string.good_evaluation));
    }

    @Override
    protected void initViews() {
        GoodAdapter adapter = new GoodAdapter(TempData.getGoods());
        lvGoods.setAdapter(adapter);
    }

    /**
     * 商品设配器
     */
    class GoodAdapter extends BaseAdapter {

        ArrayList<GoodBean> goods;
        LayoutInflater inflater;

        public GoodAdapter(ArrayList<GoodBean> goods) {
            this.inflater = getLayoutInflater();
            this.goods = goods;
        }

        @Override
        public int getCount() {
            return goods.size();
        }

        @Override
        public Object getItem(int i) {
            return goods.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.lv_item_good, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tvGoodName.setText(goods.get(i).getName());

            return view;
        }

        class ViewHolder extends BaseViewHolder {
            @Bind(R.id.tvGoodName)
            TextView tvGoodName;

            public ViewHolder(View view) {
                super(view);
            }
        }

    }

}
