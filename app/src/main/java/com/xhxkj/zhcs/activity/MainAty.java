package com.xhxkj.zhcs.activity;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhxkj.zhcs.AppContext;
import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.adapter.MyPagerAdapter;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.base.BaseFgm;
import com.xhxkj.zhcs.fragment.HomePageFgm;
import com.xhxkj.zhcs.fragment.MineFgm;
import com.xhxkj.zhcs.fragment.CartFgm;
import com.xhxkj.zhcs.presenter.MainAtyPst;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.MainAtyView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 主界面
 *
 * @author 王鑫
 */
public class MainAty extends BaseAty implements ViewPager.OnPageChangeListener, MainAtyView {


    private static final int HOME_PAGE = 0;
    private static final int SHOPPING_CART = 1;
    private static final int MINE = 2;


    @Bind(R.id.vp)
    ViewPager viewPager;

    ArrayList<BaseFgm> fragments;

    MainAtyPst pst;

    /**
     * 【首页】界面按钮
     */
    @Bind(R.id.tvHomePage)
    TextView tvHomePage;
    @Bind(R.id.ivHomePage)
    ImageView ivHomePage;
    /**
     * 【购物车】界面按钮
     */
    @Bind(R.id.tvShoppingCart)
    TextView tvShoppingCart;
    @Bind(R.id.ivShoppingCart)
    ImageView ivShoppingCart;
    /**
     * 【我的】界面按钮
     */
    @Bind(R.id.tvMine)
    TextView tvMine;
    @Bind(R.id.ivMine)
    ImageView ivMine;

    AppActionBar appActionBar;

    @Override
    protected boolean useParent(){
        return false;
    }

    @Override
    protected int layoutResId() {
        return R.layout.aty_main;
    }

    @Override
    protected void initPresenter() {
        pst = new MainAtyPst();
        pst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
//        this.appActionBar = appActionBar;
//        appActionBar.hideBtnBack();
//        appActionBar.hideBtnCustom();
    }

    @Override
    protected void initViews() {
        fragments = new ArrayList<>();
        BaseFgm homePageFragment = new HomePageFgm();
        BaseFgm shoppingCartFragment = new CartFgm();
        BaseFgm mineFragment = new MineFgm();
        fragments.add(homePageFragment);
        fragments.add(shoppingCartFragment);
        fragments.add(mineFragment);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(3);//保留3个界面

        selectPage(HOME_PAGE);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        selectPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.layoutHomePage)
    public void page2Home() {
        selectPage(HOME_PAGE);
    }

    @OnClick(R.id.layoutShoppingCart)
    public void page2ShoppingCart() {
        selectPage(SHOPPING_CART);
    }

    @OnClick(R.id.layoutMine)
    public void page2Mine() {
        selectPage(MINE);
    }

    private void selectPage(int pageIndex) {
        resetFragments();
        switch (pageIndex) {
            case HOME_PAGE:
                tvHomePage.setTextColor(ContextCompat.getColor(this, R.color.actionbar_color));
                ivHomePage.setImageResource(R.mipmap.home_page_pressed);
                viewPager.setCurrentItem(pageIndex);
//                appActionBar.setActionBarTitle(getString(R.string.home_page));
                break;
            case SHOPPING_CART:
                tvShoppingCart.setTextColor(ContextCompat.getColor(this, R.color.actionbar_color));
                ivShoppingCart.setImageResource(R.mipmap.cart_pressed);
                viewPager.setCurrentItem(pageIndex);
//                appActionBar.setActionBarTitle(getString(R.string.shopping_cart));
                break;
            case MINE:
                tvMine.setTextColor(ContextCompat.getColor(this, R.color.actionbar_color));
                ivMine.setImageResource(R.mipmap.mine_pressed);
                viewPager.setCurrentItem(pageIndex);
//                appActionBar.setActionBarTitle(getString(R.string.mine));
                break;
        }
    }

    /**
     * 重置三个fragment的颜色样式状态（都不被选中状态）
     */
    private void resetFragments() {
        // 文字颜色
        tvHomePage.setTextColor(getResources().getColor(R.color.gray));
        tvShoppingCart.setTextColor(getResources().getColor(R.color.gray));
        tvMine.setTextColor(getResources().getColor(R.color.gray));
        //图片
        ivHomePage.setImageResource(R.mipmap.home_page);
        ivShoppingCart.setImageResource(R.mipmap.cart);
        ivMine.setImageResource(R.mipmap.mine);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("退出？")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppContext.clearActivities();
                    }
                })
                .setNegativeButton("取消", null)
                .setMessage("是否退出软件？")
                .create()
                .show();
    }

    //    public void switchContent(Fragment from, Fragment to) {
//        if (mContent != to) {
//            mContent = to;
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction().setCustomAnimations(
//                    android.R.anim.fade_in, R.anim.slide_out);
//            if (!to.isAdded()) {    // 先判断是否被add过
//                transaction.hide(from).add(R.id.content_frame, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
//            } else {
//                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
//            }
//        }
//    }
}
