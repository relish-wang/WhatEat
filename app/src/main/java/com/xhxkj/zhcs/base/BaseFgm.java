package com.xhxkj.zhcs.base;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.util.AppToast;

import butterknife.ButterKnife;

/**
 * @author 王鑫
 */

public abstract class BaseFgm extends Fragment implements BaseView {
    private static final String TAG = BaseFgm.class.getSimpleName();

    private boolean isLoading;
    private View loadingView;
    private View contentView;

    private ObjectAnimator contentViewAnimator;

    protected abstract int layoutResId();

    protected abstract void initPresenter();

    protected abstract void initView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resId = layoutResId();
        if (resId != 0) {
            FrameLayout flRoot = (FrameLayout) inflater.inflate(R.layout.fgm_base, container, false);
            loadingView = flRoot.findViewById(R.id.tvLoading);
            contentView = inflater.inflate(layoutResId(), container, false);
            contentViewAnimator = ObjectAnimator.ofFloat(contentView, "alpha", 0f, 1f);
            contentViewAnimator.setDuration(500);
            flRoot.addView(contentView);

            ButterKnife.bind(this, contentView);

            initPresenter();

            initView();

            return flRoot;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void showLoading(boolean shouldLoading) {
        if (shouldLoading && !isLoading) {
            // 显示加载界面
            isLoading = true;
            contentView.setVisibility(View.INVISIBLE);
            loadingView.setVisibility(View.VISIBLE);
        } else if (!shouldLoading && isLoading) {
            //　隐藏加载界面
            isLoading = false;
            loadingView.setVisibility(View.INVISIBLE);
            contentViewAnimator.start();
            contentView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        AppToast.showShort(message);
    }

    @Override
    public void update() {

    }

    public void goActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra(getString(R.string.btn_back_text),
                ((BaseAty)getActivity()).getmAppActionBar().getActionBarTitle());
        startActivity(intent);
    }

    public void goActivity(Class<?> cls, Bundle... bundles) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra(getString(R.string.btn_back_text),
                ((BaseAty)getActivity()).getmAppActionBar().getActionBarTitle());
        for (Bundle bundle : bundles) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra(getString(R.string.btn_back_text),
                ((BaseAty)getActivity()).getmAppActionBar().getActionBarTitle());
        startActivityForResult(intent, requestCode);
    }

}
