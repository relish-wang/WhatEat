package com.xhxkj.zhcs.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.activity.homepage.ChooseFoodAty;
import com.xhxkj.zhcs.activity.homepage.FindTableAty;
import com.xhxkj.zhcs.activity.homepage.Go2OrderAty;
import com.xhxkj.zhcs.activity.homepage.HealthyDietAty;
import com.xhxkj.zhcs.activity.homepage.MarketNearbyAty;
import com.xhxkj.zhcs.activity.homepage.MyFamilyGroupAty;
import com.xhxkj.zhcs.activity.homepage.PrivateMenuAty;
import com.xhxkj.zhcs.base.BaseFgm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import wang.relish.baseadapterhelper.BaseQuickAdapter;
import wang.relish.baseadapterhelper.holder.CommonHolder;

/**
 * @author 王鑫
 */


public class HomePageFgm extends BaseFgm {

    @Bind(R.id.rvCardMenus)
    RecyclerView rvCardMenus;

    private static final List<Integer> IMAGE_RES_ID = new ArrayList<Integer>() {
        {
            add(R.mipmap.hp_choose_food);
            add(R.mipmap.hp_market_nearby);
            add(R.mipmap.hp_family_group);
            add(R.mipmap.hp_private_menu);
            add(R.mipmap.hp_find_table);
            add(R.mipmap.hp_go_to_order);
            add(R.mipmap.hp_health_diet);
        }
    };


    private static final HashMap<Integer, Class> GO2OTHER_ATY = new HashMap<Integer, Class>() {
        {
            put(R.mipmap.hp_choose_food, ChooseFoodAty.class);//网上选菜
            put(R.mipmap.hp_market_nearby, MarketNearbyAty.class);//附近菜市
            put(R.mipmap.hp_family_group, MyFamilyGroupAty.class);//我的家庭组
            put(R.mipmap.hp_private_menu, PrivateMenuAty.class);//私房菜单
            put(R.mipmap.hp_find_table, FindTableAty.class);//发现餐桌
            put(R.mipmap.hp_go_to_order, Go2OrderAty.class);//我要点菜
            put(R.mipmap.hp_health_diet, HealthyDietAty.class);//健康饮食
        }
    };


    private static final HashMap<Integer, Integer> IMG2STR = new HashMap<Integer, Integer>() {
        {
            put(R.mipmap.hp_choose_food, R.string.choose_food);//网上选菜
            put(R.mipmap.hp_market_nearby, R.string.market_nearby);//附近菜市
            put(R.mipmap.hp_family_group, R.string.family_group);//我的家庭组
            put(R.mipmap.hp_private_menu, R.string.private_menu);//私房菜单
            put(R.mipmap.hp_find_table, R.string.find_table);//发现餐桌
            put(R.mipmap.hp_go_to_order, R.string.go_2_order);//我要点菜
            put(R.mipmap.hp_health_diet, R.string.healthy_diet);//健康饮食
        }
    };


    private static final HashMap<Integer, Integer> COLORS = new HashMap<Integer, Integer>() {
        {
            put(R.mipmap.hp_choose_food, R.color.choose_food_color);//网上选菜
            put(R.mipmap.hp_market_nearby, R.color.market_nearby_color);//附近菜市
            put(R.mipmap.hp_family_group, R.color.family_group_color);//我的家庭组
            put(R.mipmap.hp_private_menu, R.color.private_menu_color);//私房菜单
            put(R.mipmap.hp_find_table, R.color.find_table_color);//发现餐桌
            put(R.mipmap.hp_go_to_order, R.color.go_2_order_color);//我要点菜
            put(R.mipmap.hp_health_diet, R.color.healthy_diet_color);//健康饮食
        }
    };

    @Override
    protected int layoutResId() {
        return R.layout.fgm_home_page;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        rvCardMenus.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvCardMenus.setAdapter(new CardAdapter(IMAGE_RES_ID));
    }

    class CardAdapter extends BaseQuickAdapter<Integer> {

        public CardAdapter(List<Integer> data) {
            super(getActivity(), data, R.layout.rv_item_card_menu);

        }

        @Override
        public void convert(CommonHolder holder, final Integer data, int position, int itemType) {

            holder.setBackgroundColor(R.id.ll_background, ContextCompat.getColor(getActivity(), COLORS.get(data)));
            holder.setText(R.id.tv_card_name, IMG2STR.get(data));
            holder.setImageResource(R.id.iv_card, data);
            holder.setOnClickListener(R.id.iv_card, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goActivity(GO2OTHER_ATY.get(data));
                }
            });
        }
    }
}
