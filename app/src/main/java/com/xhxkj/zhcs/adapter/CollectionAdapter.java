package com.xhxkj.zhcs.adapter;

import android.widget.BaseAdapter;

/**
 * 收藏Adapter（3次调用）
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/11/9.
 */
public abstract class CollectionAdapter extends BaseAdapter {

    public static String makeUpStatistics(Object hireCount, Object score) {
        return "共购买过" + hireCount + "次，平均评分" + score + "分";
    }

    public static String makeUpDistance(Object distance) {
        return "距离我家" + distance + "米";
    }
}
