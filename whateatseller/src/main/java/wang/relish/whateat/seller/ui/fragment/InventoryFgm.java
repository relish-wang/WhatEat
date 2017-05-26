package wang.relish.whateat.seller.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.relish.ugo.base.BaseFgm;
import wang.relish.ugo.base.adapter.BaseHolderAdapter;
import wang.relish.whateat.seller.R;
import wang.relish.whateat.seller.bean.Good;
import wang.relish.whateat.seller.task.EasyCallback;
import wang.relish.whateat.seller.task.GetGoodsTask;

/**
 * <pre>
 *     author : 王鑫
 *     e-mail : wangxin@souche.com
 *     time   : 2017/05/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class InventoryFgm extends BaseFgm {

    @Override
    protected int layoutId() {
        return R.layout.fgm_inventory;
    }

    @Bind(R.id.tv_no_data)
    TextView tv_no_data;
    @Bind(R.id.lv_goods)
    ListView lv_goods;

    GoodsAdapter mAdapter;

    List<Good> mGoods = new ArrayList<>();

    @Override
    protected void initViews(View v) {
        mAdapter = new GoodsAdapter(mGoods);
        lv_goods.setAdapter(mAdapter);

        new GetGoodsTask(new EasyCallback<List<Good>>() {
            @Override
            public void onBefore() {
                showLoading(true);
            }

            @Override
            public void onSuccess(List<Good> goods) {
                showLoading(false);
                mGoods = goods;
                mAdapter.notifyDataSetChanged();
                show(true);
            }

            @Override
            public void onFailed(String message) {
                showLoading(false);
                showMessage(message);
                show(false);
            }
        }).execute();
    }

    private void show(boolean hasData) {
        lv_goods.setVisibility(hasData ? View.VISIBLE : View.GONE);
        tv_no_data.setVisibility(!hasData ? View.VISIBLE : View.GONE);
    }


    class GoodsAdapter extends BaseHolderAdapter<GoodsAdapter.Holder, Good> {

        public GoodsAdapter(List<Good> mData) {
            super(mData);
        }

        @Override
        protected int itemLayoutId() {
            return R.layout.item_good;
        }

        @Override
        protected void setItemData(Holder holder, Good good, int position) {
            Bitmap bitmap = BitmapFactory.decodeFile(good.getPic());
            holder.iv_pic.setImageBitmap(bitmap);
            holder.tv_name.setText(good.getName());
            holder.tv_price.setText(String.valueOf(good.getPrice() + good.getpUnit()));
        }

        @Override
        protected Holder createViewHolder(View view) {
            return new Holder(view);
        }

        class Holder {

            @Bind(R.id.iv_pic)
            ImageView iv_pic;
            @Bind(R.id.tv_name)
            TextView tv_name;
            @Bind(R.id.tv_price)
            TextView tv_price;

            public Holder(View view) {
                ButterKnife.bind(this, view);
            }

        }
    }

}
