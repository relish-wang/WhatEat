package com.xhxkj.zhcs.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.xhxkj.zhcs.App;
import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.util.AppLog;
import com.xhxkj.zhcs.util.AppToast;
import com.xhxkj.zhcs.util.StatusBarCompat;
import com.xhxkj.zhcs.view.AppActionBar;

import butterknife.ButterKnife;

public abstract class BaseAty extends AppCompatActivity implements BaseView {
    private static final String TAG = "BaseAty";

    private ProgressDialog mProgressDialog;

    //    @Bind(R.id.appActionBar)
    AppActionBar mAppActionBar;

    protected boolean useParent() {
        return true;
    }

    protected abstract int layoutResId();

    protected abstract void initPresenter();

    protected abstract void initActionBar(AppActionBar appActionBar);

    protected void beforeSetContentView() {

    }

    protected void parseIntent(Intent intent){

    }

    protected abstract void initViews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent(getIntent());
        beforeSetContentView();
        int resId = layoutResId();
        if (resId != 0) {
            // 设置根布局
            LayoutInflater inflater = getLayoutInflater();

            @SuppressLint("InflateParams")
            LinearLayout llRoot = (LinearLayout) inflater.inflate(R.layout.aty_base, null);

            mAppActionBar = (AppActionBar) llRoot.findViewById(R.id.appActionBar);
            View contentView = inflater.inflate(resId, null);
            llRoot.addView(contentView, new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.market_nearby_color));
            if (useParent()) {
                setContentView(llRoot);
            } else {
                setContentView(resId);
                // 设置view注解
                ButterKnife.bind(this);
                initViews();
                return;
            }
            // 添加至自定义的activity堆栈
            App.addActivity(this);

            // 设置view注解
            ButterKnife.bind(this, contentView);

            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("正在加载");
            mProgressDialog.setCanceledOnTouchOutside(false);

            initPresenter();

            // 在android4.4以后将状态栏设置成透明，然后通过设置actionbar的padding将actionbar填充至状态栏，
            // 以此来实现状态栏的沉浸
            // actionbar的padding已在布局文件中设置过
            // 据说在代码里直接声明状态栏为透明比较有效（未测试）
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                        | localLayoutParams.flags);
//
//                // 设置actionbar的填充状态栏
//                mAppActionBar.setPadding(0, DensityUtil.getStatusBarHeight(this), 0, 0);
//
//                // 设置actionbar的背景颜色
//                // noinspection deprecation
//                mAppActionBar.setBackgroundColor(getResources().getColor(R.color.actionbar_color));
//            }

            // 初始化actionbar
            // 这里设置了actionbar的常用点击事件，例如点击返回键activity退栈
            Intent intent = getIntent();
            if (intent != null) {
                String btnBackText = intent.getStringExtra(getString(R.string.btn_back_text));
                if (null != btnBackText) {
                    mAppActionBar.setBtnBackText(btnBackText);
                } else {
                    mAppActionBar.setBtnBackText("返回");
                }
            } else {
                mAppActionBar.setBtnBackText("返回");
            }
            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    String titleText = (String) bundle.get(getString(R.string.titleText));
                    if (null != titleText) {
                        mAppActionBar.setActionBarTitle(titleText);
                    }
                }
            }
            mAppActionBar.setBtnBackOnClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            // 交由子类实现各自的actionbar显示内容及其点击事件监听
            initActionBar(mAppActionBar);
            // 初始化界面
            initViews();
        } else {
            AppLog.e(TAG, "onCreate", "Invalid resId.");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity被回收时同时将其从自定义堆栈中清除
        App.removeActivities(this.getClass().getSimpleName());
    }

    /**
     * 弹出弹框表示目前正在加载。此时界面不能操作任何元素
     *
     * @param shouldLoading true表示显示加载界面，false表示停止加载界面<br/>
     *                      若界面与请求的界面已经一致（如在加载界面显示的时候请求显示加载界面）则不会进行任何操作
     */
    @Override
    public void showLoading(boolean shouldLoading) {
        if (shouldLoading && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        } else if (!shouldLoading && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {
        AppToast.showShort(message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideInputSoftware();
    }

    public AppActionBar getAppActionBar() {
        return mAppActionBar;
    }

    @Override
    public void update() {
    }

    public void goActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(getString(R.string.btn_back_text), mAppActionBar.getActionBarTitle());
        startActivity(intent);
    }

    public void goActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(getString(R.string.btn_back_text), mAppActionBar.getActionBarTitle());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goActivityForResult(Class<?> cls, int resultCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(getString(R.string.btn_back_text), mAppActionBar.getActionBarTitle());
        startActivityForResult(intent, resultCode);
    }

    public void goActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(getString(R.string.btn_back_text), mAppActionBar.getActionBarTitle());
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 关闭输入法
     */
    protected void hideInputSoftware() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            View view = getCurrentFocus();
            if (view != null) {
                inputMethodManager.hideSoftInputFromWindow(
                        view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public AppActionBar getmAppActionBar() {
        return mAppActionBar;
    }
}
