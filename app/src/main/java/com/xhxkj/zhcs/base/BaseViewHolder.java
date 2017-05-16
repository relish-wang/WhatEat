package com.xhxkj.zhcs.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * viewHolder基础类
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/11/9.
 */
public abstract class BaseViewHolder {
    public BaseViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
