package com.xhxkj.zhcs.fragment;


import android.view.View;
import android.widget.TextView;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.BuyerListAty;
import com.xhxkj.zhcs.base.BaseFgm;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author 王鑫
 */
public class MarketOptionFgm extends BaseFgm {

    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.tvDeliverWay)
    TextView tvDeliverWay;
    @Bind(R.id.tvExtraService)
    TextView tvExtraService;
    @Bind(R.id.tvCreateTime)
    TextView tvTime;


    @Override
    protected int layoutResId() {
        return R.layout.fgm_option_market;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.tvAddress, R.id.tvDeliverWay,
            R.id.tvExtraService, R.id.tvCreateTime})
    public void temp(View v) {
        switch (v.getId()) {
            case R.id.tvAddress:
                tvAddress.setText("浙江省杭州市下沙高教园");
                break;
            case R.id.tvDeliverWay:
                tvDeliverWay.setText("送货上门(买手：张三)");
                goActivity(BuyerListAty.class);
                break;
            case R.id.tvExtraService:
                tvExtraService.setText("菜品初加工");
                break;
            case R.id.tvCreateTime:
                tvTime.setText("20:00-20:30");
                break;
        }
    }


}
