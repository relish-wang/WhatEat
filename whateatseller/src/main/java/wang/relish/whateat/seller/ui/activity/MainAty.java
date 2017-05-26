package wang.relish.whateat.seller.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnPageChange;
import wang.relish.ugo.base.BaseAty;
import wang.relish.ugo.base.BaseFgm;
import wang.relish.whateat.seller.R;
import wang.relish.whateat.seller.ui.dialog.AddGoodDialog;
import wang.relish.whateat.seller.ui.fragment.HomepageFgm;
import wang.relish.whateat.seller.ui.fragment.InventoryFgm;
import wang.relish.whateat.seller.ui.fragment.MineFgm;

public class MainAty extends BaseAty {

    private static final int HOMEPAGE = 0;
    private static final int INVENTORY = 1;
    private static final int MINE = 2;

    @Override
    protected int layoutId() {
        return R.layout.aty_main;
    }

    @Override
    protected boolean isBtnBackEnable() {
        return false;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState, Toolbar mToolbar) {
    }

    List<BaseFgm> mFgms = new ArrayList<>();

    @Bind(R.id.vp_main)
    ViewPager vp;

    /**
     * 【首页】界面按钮
     */
    @Bind(R.id.tv_homepage)
    TextView tv_homepage;
    @Bind(R.id.iv_homepage)
    ImageView iv_homepage;
    /**
     * 【库存】界面按钮
     */
    @Bind(R.id.tv_inventory)
    TextView tv_inventory;
    @Bind(R.id.iv_inventory)
    ImageView iv_inventory;
    /**
     * 【我的】界面按钮
     */
    @Bind(R.id.tv_mine)
    TextView tv_mine;
    @Bind(R.id.iv_mine)
    ImageView iv_mine;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        BaseFgm homepageFgm = new HomepageFgm();
        BaseFgm inventoryFgm = new InventoryFgm();
        BaseFgm mineFgm = new MineFgm();
        mFgms.add(homepageFgm);
        mFgms.add(inventoryFgm);
        mFgms.add(mineFgm);

        vp.setAdapter(new HomePagerAdapter());
        vp.setOffscreenPageLimit(3);//保留3个界面

        selectPage(HOMEPAGE);
    }


    @OnPageChange(R.id.vp_main)
    public void onPageChange(int index) {
        selectPage(index);
    }

    @OnClick({R.id.layout_home_page,R.id.layout_inventory,R.id.layout_mine})
    public void changePage(View v){
        switch (v.getId()){
            case R.id.layout_home_page:
                selectPage(HOMEPAGE);
                break;
            case R.id.layout_inventory:
                selectPage(INVENTORY);
                break;
            case R.id.layout_mine:
                selectPage(MINE);
                break;
        }
    }

    private class HomePagerAdapter extends FragmentPagerAdapter {

        HomePagerAdapter() {
            super(MainAty.this.getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return mFgms.get(position);
        }

        @Override
        public int getCount() {
            return mFgms.size();
        }
    }


    private void selectPage(int pageIndex) {
        resetFragments();
        switch (pageIndex) {
            case HOMEPAGE:
                tv_homepage.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                iv_homepage.setImageResource(R.drawable.home_page_pressed);
                vp.setCurrentItem(pageIndex);
                mToolbar.setTitle(getString(R.string.home_page));
                break;
            case INVENTORY:
                tv_inventory.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                iv_inventory.setImageResource(R.drawable.cart_pressed);
                vp.setCurrentItem(pageIndex);
                mToolbar.setTitle(getString(R.string.inventory));
                break;
            case MINE:
                tv_mine.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                iv_mine.setImageResource(R.drawable.mine_pressed);
                vp.setCurrentItem(pageIndex);
                mToolbar.setTitle(getString(R.string.mine));
                break;
        }
    }

    /**
     * 重置三个fragment的颜色样式状态（都不被选中状态）
     */
    private void resetFragments() {
        // 文字颜色
        tv_homepage.setTextColor(getResources().getColor(R.color.gray));
        tv_inventory.setTextColor(getResources().getColor(R.color.gray));
        tv_mine.setTextColor(getResources().getColor(R.color.gray));
        //图片
        iv_homepage.setImageResource(R.drawable.home_page);
        iv_inventory.setImageResource(R.drawable.cart);
        iv_mine.setImageResource(R.drawable.mine);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inventory,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_good:
                addGood();
                break;
        }
        return true;
    }

    private void addGood() {
        AddGoodDialog dialog = AddGoodDialog.newInstance();
        dialog.show(getSupportFragmentManager(),"");
    }
}
