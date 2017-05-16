package com.xhxkj.zhcs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 永远处于焦点状态的TextView
 * <p>
 * Created by 鑫 on 2015/8/9.
 */
public class FTextView extends TextView {
    public FTextView(Context context) {
        super(context);
    }


    public FTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FTextView(Context context, AttributeSet attrs,
                     int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
