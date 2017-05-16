package com.xhxkj.zhcs.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xhxkj.zhcs.base.BaseFgm;

import java.util.ArrayList;

/**
 * ViewPager适配器(4次调用)
 *
 * @author 王鑫
 *         Created by 王鑫 on 2015/9/22.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    ArrayList<BaseFgm> fragments;

    public MyPagerAdapter(FragmentManager fm, ArrayList<BaseFgm> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}