package com.xhxkj.zhcs.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * App的工具类
 *
 * @author 王鑫
 * Created by 王鑫 on 2015/7/19.
 */
public class AppUtil {

    /**
     * 修改电话号码格式为 xxx-xxxx-xxxx
     *
     * @param s
     * @return
     */
    public static String formatPhone(String s) {
        s = new StringBuilder(convertformatPhone(s)).reverse().toString();
        if (s.length() <= 8) {//x{3,8}
            return new StringBuilder(s).reverse().toString();
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                if (i % 4 == 3 && i != s.length() - 1) {
                    sb.append("-");
                }
            }
            return sb.reverse().toString();
        }
    }

    /**
     * 修改电话号码格式为 xxxxxxxxxxx
     *
     * @param s
     * @return
     */
    public static String convertformatPhone(String s) {
        return s.replace("-", "");
    }

    /**
     * 修改字符串格式为 xxxxx...
     *
     * @param s
     * @return
     */
    public static String cutTail(String s, int len) {
        try {
            int end = 0, count = 0;
            for (int i = 0; i < s.length(); i++) {
                count += ("" + s.charAt(i)).getBytes("UTF-8").length;
                if (count >= len) {
                    end = i;
                    break;
                }
            }
            if (s.length() - 1 == end) {
                return s;
            } else if (count >= len) {
                return s.substring(0, end) + "...";
            } else {
                return s;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("wx", "string cutTail failed");
            return s;
        }
    }

    /**
     * 计算字符串的字节数
     *
     * @param s
     * @return
     */
    public static int getStringBitLength(String s) {
        int count = 0;
        try {
            for (int i = 0; i < s.length(); i++) {
                count += ("" + s.charAt(i)).getBytes("UTF-8").length;
            }
        } catch (Exception e) {
            Log.e("wx", "string get bitLength failed");
            e.printStackTrace();
        }
        return count;
    }
}
