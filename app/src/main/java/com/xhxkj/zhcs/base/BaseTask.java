package com.xhxkj.zhcs.base;

import android.os.AsyncTask;

import com.xhxkj.zhcs.view.WaitDialog;

/**
 * 所有异步加载类的基类
 *
 * @author 王鑫
 *         Created by 王鑫 on 2015/9/20.
 */
public abstract class BaseTask extends AsyncTask<Void, Void, Void> {

    private WaitDialog waitDialog;

    protected BaseAty context;

    public BaseTask(BaseAty context) {
        this.context = context;
        waitDialog = new WaitDialog();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        waitDialog.show(context.getSupportFragmentManager(), "wait");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        waitDialog.dismiss();
    }
}
