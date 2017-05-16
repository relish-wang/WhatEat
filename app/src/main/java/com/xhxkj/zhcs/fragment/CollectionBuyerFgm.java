package com.xhxkj.zhcs.fragment;


import android.widget.ListView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.BuyerAdapter;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.presenter.CollectBuyerFgmPst;
import com.xhxkj.zhcs.temp.TempData;
import com.xhxkj.zhcs.vm.CollectBuyerFgmView;

import butterknife.Bind;

/**
 * 主界面(aty)-我的(fgm)-我的收藏(aty)-买手收藏(fgm)
 *
 * @author 王鑫
 */
public class CollectionBuyerFgm extends BaseFgm implements CollectBuyerFgmView {

    CollectBuyerFgmPst pst;

    @Bind(R.id.lvBuyers)
    ListView lvBuyers;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_buyer_collection;
    }

    @Override
    protected void initPresenter() {
        pst = new CollectBuyerFgmPst();
        pst.attachView(this);
    }

    @Override
    protected void initView() {
        BuyerAdapter adapter = new BuyerAdapter(getActivity(), TempData.getStaredBuyers());
        lvBuyers.setAdapter(adapter);
    }

}
