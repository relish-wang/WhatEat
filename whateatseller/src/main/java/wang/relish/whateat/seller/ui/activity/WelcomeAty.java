package wang.relish.whateat.seller.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import wang.relish.ugo.base.BaseAty;
import wang.relish.whateat.seller.R;

/**
 * <pre>
 *     author : 王鑫
 *     e-mail : wangxin@souche.com
 *     time   : 2017/05/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class WelcomeAty extends BaseAty {
    @Override
    protected int layoutId() {
        return R.layout.aty_welcome;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState, Toolbar mToolbar) {
        mToolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                finish();
            }
        }.execute();
    }
}
