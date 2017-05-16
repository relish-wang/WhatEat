package com.xhxkj.zhcs.util;

import android.util.Log;
import android.widget.Toast;

import com.xhxkj.zhcs.AppContext;

/**
 * Toast类
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/10/13.
 */
public class AppToast {
    /**
     * Make a toast and show.
     *
     * @param message If message is null, toast won't show.
     */
    public static void showShort(String message) {
        if (message != null) {
            Log.d("AppToast", (AppContext.APP_CONTEXT == null) + "");
            Toast.makeText(AppContext.APP_CONTEXT, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Make a toast and show.
     *
     * @param message If message is null, toast won't show.
     */
    public static void showLong(String message) {
        if (message != null)
            Toast.makeText(AppContext.APP_CONTEXT, message, Toast.LENGTH_LONG).show();
    }
}
