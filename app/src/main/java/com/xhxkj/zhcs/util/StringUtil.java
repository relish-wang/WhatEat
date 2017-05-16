package com.xhxkj.zhcs.util;

import com.xhxkj.zhcs.activity.RegisterAty;

/**
 * Created by 鑫 on 2015/12/2.
 */
public class StringUtil {

    public static String formatPhone(String tel) {
        if(RegisterAty.TEL.matcher(tel).matches()){
            return tel.substring(0, 3) + "****" + tel.substring(7);
        }
        return tel;
    }
}
