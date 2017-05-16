package com.xhxkj.zhcs.view;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.xhxkj.zhcs.R;


/**
 * 家庭组二维码Dialog
 *
 * @author 王鑫
 */
public class QRCodeDialog extends DialogFragment {

    public QRCodeDialog() {
        this.setCancelable(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        getDialog().setCanceledOnTouchOutside(true);//点击Dialog外区域，就dismiss

        return inflater.inflate(R.layout.dialog_qrcode, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    getDialog().cancel();
                    getDialog().dismiss();
//                    getActivity().finish();
                }
                return false;
            }
        });
    }
}
