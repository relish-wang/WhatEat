package com.xhxkj.zhcs.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xhxkj.zhcs.R;

import java.util.regex.Pattern;

/**
 * @author 王鑫
 * Created by 王鑫 on 2015/8/10.
 */
public class FormatWatcher implements TextWatcher {


    private EditText et;
    private ImageView iv;
    private int minLength;
    private int maxLength;
    private Pattern pattern;
    private String error;

    public FormatWatcher(EditText et, ImageView iv, int minLength, int maxLength, Pattern pattern, String error) {
        this.et = et;
        this.iv = iv;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.pattern = pattern;
        this.error = error;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (null != iv) {
            if ("".equals(s.toString())) {
                iv.setVisibility(View.INVISIBLE);
            } else {
                iv.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        String str = s.toString();
        if (str.length() < minLength) {
            if (null != iv)
                iv.setImageResource(R.mipmap.wrong);
            et.setError("长度不得少于" + minLength + "位");
        } else if (str.length() > maxLength) {
            if (null != iv)
                iv.setImageResource(R.mipmap.wrong);
            et.setError("长度不得超过" + maxLength + "位");
        } else {
            if (pattern.matcher(str).matches()) {//开头不可以为数字的长度为5-10的字符串
                if (null != iv)
                    iv.setImageResource(R.mipmap.correct);
            } else {
                if (null != iv)
                    iv.setImageResource(R.mipmap.wrong);
                et.setError(error);
            }
        }
    }

}
