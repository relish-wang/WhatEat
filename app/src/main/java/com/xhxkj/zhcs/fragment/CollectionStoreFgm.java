package com.xhxkj.zhcs.fragment;


import android.widget.ListView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.temp.TempData;

import butterknife.Bind;

/**
 * 主界面(aty)-我的(fgm)-我的收藏(aty)-店铺收藏(fgm)
 *
 * @author 王鑫
 */
public class CollectionStoreFgm extends BaseFgm {


    @Bind(R.id.lvStores)
    ListView lvStores;


    @Override
    protected int layoutResId() {
        return R.layout.fgm_store_collection;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    @Override
    protected void initView() {
        MarketListFgm.MarketAdapter adapter = new MarketListFgm.MarketAdapter((BaseAty) getActivity(), TempData.getMarkets());
        lvStores.setAdapter(adapter);
    }


}
