package com.xhxkj.zhcs.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xhxkj.zhcs.R;


public final class AppActionBar extends FrameLayout {
    private static final String TAG = AppActionBar.class.getSimpleName();

    public static final int ACTIONBAR_MODE_TITLE = 0;
    public static final int ACTIONBAR_MODE_SWITCH = 1;

    @IntDef({ACTIONBAR_MODE_TITLE, ACTIONBAR_MODE_SWITCH})
    public @interface ActionBarMode {
    }

    private OnBtnSwitchedListener mOnBtnSwitchedListener;

    private View contentView;

    private LinearLayout llBack;
    private TextView tvBack;
    private TextView tvTitle;
    private RelativeLayout rlSwitch;
    private View switcher;
    private TextView tvSwitchLeft;
    private TextView tvSwitchRight;
    private TextView tvCustom;

    private boolean isInRight;

    public PopupWindow mPopupWindow;

    public AppActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        contentView = LayoutInflater.from(context).inflate(R.layout.actionbar, this)
                .findViewById(R.id.rlActionBarContent);
        llBack = (LinearLayout) contentView.findViewById(R.id.llBack);
        tvBack = (TextView) contentView.findViewById(R.id.tvBack);
        tvTitle = (TextView) contentView.findViewById(R.id.tvTitle);
        rlSwitch = (RelativeLayout) contentView.findViewById(R.id.rlSwitch);
        switcher = contentView.findViewById(R.id.switcher);
        tvSwitchLeft = (TextView) contentView.findViewById(R.id.tvSwitchLeft);
        tvSwitchRight = (TextView) contentView.findViewById(R.id.tvSwitchRight);
        tvCustom = (TextView) contentView.findViewById(R.id.tvCustom);

        initActionBar();
    }

    private void initActionBar() {
        rlSwitch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInRight) {
                    if (mOnBtnSwitchedListener != null) {
                        mOnBtnSwitchedListener.onLeftSwitched();
                    }

                    tvSwitchLeft.setTextColor(Color.parseColor("#25bae9"));
                    tvSwitchRight.setTextColor(Color.parseColor("#ffffff"));

                    ObjectAnimator animator = ObjectAnimator.ofFloat(
                            switcher, "x", tvSwitchRight.getX(), tvSwitchLeft.getX());
                    animator.setDuration(100);
                    animator.start();
                } else {
                    if (mOnBtnSwitchedListener != null) {
                        mOnBtnSwitchedListener.onRightSwitched();
                    }

                    tvSwitchLeft.setTextColor(Color.parseColor("#ffffff"));
                    tvSwitchRight.setTextColor(Color.parseColor("#25bae9"));

                    ObjectAnimator animator = ObjectAnimator.ofFloat(
                            switcher, "x", tvSwitchLeft.getX(), tvSwitchRight.getX());
                    animator.setDuration(100);
                    animator.start();
                }
                isInRight = !isInRight;
            }
        });
    }

    public void setPopupViewContent(View contentView) {
        if (mPopupWindow != null) {
            mPopupWindow.setContentView(contentView);
        } else {
            mPopupWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        }
    }

    public void showPopupView() {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(tvCustom);
        }
    }

    public void dismissPopupView() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void hide() {
        contentView.setVisibility(View.GONE);
    }

    public void setActionBarMode(@ActionBarMode int mode) {
        switch (mode) {
            case ACTIONBAR_MODE_TITLE:
                tvTitle.setVisibility(View.VISIBLE);
                rlSwitch.setVisibility(View.GONE);
                break;
            case ACTIONBAR_MODE_SWITCH:
                tvTitle.setVisibility(View.GONE);
                rlSwitch.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 设置actionbar的标题文本
     * 即使当前actionbar的标题未显示，设置仍有效，并会在actionbar的标题文本可见时显示该文本
     *
     * @param title actionbar的标题
     */
    public void setActionBarTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 获得actionbar的标题文本
     * @return title actionbar的标题
     */
    public String getActionBarTitle() {
        return tvTitle.getText().toString();
    }
    /**
     * 设置actionbar的中部的切换按钮的文本
     * 即使当前actionbar的切换按钮未显示，设置仍有效，并会在actionbar的切换按钮可见时显示该文本
     *
     * @param left  切换按钮左侧的文本，若超过2个字会自动取前2个字
     * @param right 切换按钮右侧的文本，若超过2个字会自动取前2个字
     */
    public void setBtnSwitchText(String left, String right) {
        if (right != null && left.length() > 2) {
            left = left.substring(0, 1);
        }
        if (right != null && right.length() > 2) {
            right = right.substring(0, 1);
        }
        tvSwitchLeft.setText(left);
        tvSwitchRight.setText(right);
    }

    /**
     * 设置actionbar返回按钮的文本，若返回按钮不可见，将自动将其设置为可见状态，并显示文本
     *
     * @param backButtonText 返回按钮上的文本
     */
    public void setBtnBackText(String backButtonText) {
        llBack.setVisibility(View.VISIBLE);
        tvBack.setText(backButtonText);
    }

    /**
     * 设置actionbar自定义按钮的文本（自定义按钮位于actionbar右侧），若自定义按钮不可见，
     * 将自动将其设置为可见状态，并显示文本
     *
     * @param customButtonText 自定义按钮上的文本
     */
    public void setBtnCustomText(String customButtonText) {
        tvCustom.setVisibility(View.VISIBLE);
        tvCustom.setText(customButtonText);
    }

    public void hideBtnBack() {
        llBack.setVisibility(View.INVISIBLE);
    }

    public void hideBtnCustom() {
        tvCustom.setVisibility(View.INVISIBLE);
    }

    public void setBtnBackOnClick(OnClickListener listener) {
        llBack.setOnClickListener(listener);
    }

    public void setBtnCustomOnClick(OnClickListener listener) {
        tvCustom.setOnClickListener(listener);
    }

    public void setBtnOnSwitch(OnBtnSwitchedListener listener) {
        mOnBtnSwitchedListener = listener;
    }

    public interface OnBtnSwitchedListener {
        void onLeftSwitched();

        void onRightSwitched();
    }
}
