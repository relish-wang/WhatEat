package com.xhxkj.zhcs.fragment;


import android.os.AsyncTask;
import android.view.View;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.util.AppToast;
import com.xhxkj.zhcs.view.WaitDialog;

/**
 * @author 王鑫
 */
public class MarketPayFgm extends BaseFgm {


    @Override
    protected int layoutResId() {
        return R.layout.fgm_market_pay;
    }

    @Override
    protected void initPresenter() {
        //TODO
    }

    WaitDialog waitDialog;
    @Override
    protected void initView() {
        ((MarketNearbyAty)getActivity()).getAppActionBar().setBtnCustomText("确认支付");
        ((MarketNearbyAty)getActivity()).getAppActionBar().setBtnCustomOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        waitDialog = new WaitDialog();
                        waitDialog.show(getActivity().getSupportFragmentManager(), "wait");
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        waitDialog.dismiss();
                        AppToast.showShort("支付成功");
                    }
                }.execute();
            }
        });
    }
}
