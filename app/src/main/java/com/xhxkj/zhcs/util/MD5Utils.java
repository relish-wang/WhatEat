package com.xhxkj.zhcs.util;

import java.security.MessageDigest;

/**
 * MD5工具类
 * @author 苟秋林
 *
 */
public class MD5Utils {

    /**
     * 将原数据字符串逆时针左移3位，再将移动后的字符串进行MD5加密。
     * @param paramStr 原字符串，长度必须大于等于3位。
     * @return 加密后的字符串，原字符串长度小于3位返回null。
     */
    public static String toMd5(String paramStr){
        //原字符串为空或者字符长度小宇3位，返回null
        if(paramStr.isEmpty() || paramStr.length() < 3){
            return null;
        }

        //逆时针左移3位
        String preStr = paramStr.substring(0, 3);
        String endStr = paramStr.substring(3);
        String newStr = endStr + preStr;

        //MD5加密
        String md5Str =  MD52(newStr);

        return md5Str;
    }

    /**
     * 执行MD5加密
     * @param str 原字符串
     * @return 加密后的字符串
     */
    private static String MD5(String str) {
        //密钥
        char hexDigits[]={'K','U','C','N','O','D','I','T','S','Q',
                '0','1','9','3','4','F'};

        try {
            byte[] btInput = str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得散列后的值
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char endStr[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];

                endStr[k++] = hexDigits[byte0 >>> 2 & 0xf];
                endStr[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(endStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 执行MD5加密
     * @param str 原字符串
     * @return 加密后的字符串
     */
    private static String MD52(String str) {
        try {
            byte[] btInput = str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得散列后的值
            byte[] md = mdInst.digest();

            //抛掉前2位和后3位
            String md5Str1 = getMD5Str(md);
            md5Str1 = md5Str1.substring(2, 29);

            //第二次散列
            byte[] btInput2 = md5Str1.getBytes();
            MessageDigest mdInst2 = MessageDigest.getInstance("MD5");
            mdInst2.update(btInput2);
            byte[] md2 = mdInst2.digest();

            String md5Str2 = getMD5Str(md2);

            return md5Str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将MD5散列后的值转化成字符串
     * @param paramByte MD5散列后的数组
     * @return 转化后的字符串
     */
    private static String getMD5Str(byte[] paramByte){
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < paramByte.length; i++) {
            if (Integer.toHexString(0xFF & paramByte[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & paramByte[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & paramByte[i]));

        }
        return md5StrBuff.toString();
    }

}
