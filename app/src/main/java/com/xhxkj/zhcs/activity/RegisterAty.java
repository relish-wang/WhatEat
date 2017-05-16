package com.xhxkj.zhcs.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.presenter.RegisterAtyPst;
import com.xhxkj.zhcs.util.AppToast;
import com.xhxkj.zhcs.util.FormatWatcher;
import com.xhxkj.zhcs.util.MD5Utils;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.RegisterAtyView;

import java.util.regex.Pattern;

import butterknife.Bind;

/**
 * @author 王鑫
 */
public class RegisterAty extends BaseAty implements RegisterAtyView, View.OnFocusChangeListener {

    /**
     * 正则表达式：用户名
     */
    private static final Pattern USER_NAME = Pattern.compile("^[_a-zA-Z0-9]{4,11}$");
    /**
     * 正则表达式：密码
     */
    private static final Pattern PWD = Pattern.compile("[/[!-~]/]{6,16}");
    /**
     * 正则表达式：手机号
     */
    public static final Pattern TEL = Pattern.compile("^1[0-9]{10}$");
    /**
     * 正则表达式：邮箱
     */
    private static final Pattern EMAIL = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
    public static final int REQUEST_CODE_SUCCESS = 0x123;
    public static final int REQUEST_CODE_BACK = 0x321;

    //用户名
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.iv_username_register)
    ImageView ivName;

    //密码
    @Bind(R.id.etPwd)
    EditText etPwd;
    @Bind(R.id.iv_pswd_register)
    ImageView ivPwd;

    //确认密码
    @Bind(R.id.etRepeatPwd)
    EditText etRepeatPwd;
    @Bind(R.id.iv_ensure_pswd_register)
    ImageView ivRepeatPwd;

    //电话
    @Bind(R.id.etTel)
    EditText etTel;
    @Bind(R.id.iv_tel_register)
    ImageView ivTel;

    RegisterAtyPst registerAtyPst;

    @Override
    protected int layoutResId() {
        return R.layout.aty_register;
    }

    @Override
    protected void initPresenter() {
        registerAtyPst = new RegisterAtyPst();
        registerAtyPst.attachView(this);
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setActionBarTitle(getString(R.string.register));
        appActionBar.setBtnBackOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(REQUEST_CODE_BACK);
                finish();
            }
        });
        appActionBar.setBtnCustomText(getString(R.string.ensure));
        appActionBar.setBtnCustomOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                String repeatPwd = etRepeatPwd.getText().toString().trim();
                String tel = etTel.getText().toString().trim();
                if ((USER_NAME.matcher(name).matches() ||
                        EMAIL.matcher(name).matches()) &&
                        pwd.length() >= 6 &&
                        pwd.length() <= 16 &&
                        pwd.equals(repeatPwd)) {
                    registerAtyPst.register(name, pwd, repeatPwd, tel);
                } else {
                    AppToast.showShort("格式错误！");
                }
            }
        });
    }

    @Override
    protected void initViews() {

        ivName.setVisibility(View.INVISIBLE);
        ivPwd.setVisibility(View.INVISIBLE);
        ivRepeatPwd.setVisibility(View.INVISIBLE);
        ivTel.setVisibility(View.INVISIBLE);

        etName.setOnFocusChangeListener(this);
        etPwd.setOnFocusChangeListener(this);
        etRepeatPwd.setOnFocusChangeListener(this);
        etTel.setOnFocusChangeListener(this);
    }

    @Override
    public void onRegisterSuccess() {
        Intent i = new Intent();
        i.putExtra(UserEntity.NAME, etName.getText());
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!v.isFocused()) {
            switch (v.getId()) {
                case R.id.etName:
                    if (!TextUtils.isEmpty(etName.getText().toString().trim())) {
                        ivName.setVisibility(View.VISIBLE);
                        if (USER_NAME.matcher(etName.getText().toString().trim()).matches() || EMAIL.matcher(etName.getText().toString().trim()).matches()) {
                            ivName.setImageResource(R.mipmap.correct);
                        } else {
                            etName.setError("手机号或邮箱格式不正确");
                            ivName.setImageResource(R.mipmap.wrong);
                        }
                    }
                    break;
                case R.id.etPwd:
                    if (!TextUtils.isEmpty(etPwd.getText().toString().trim())) {
                        ivPwd.setVisibility(View.VISIBLE);
                        if (PWD.matcher(etPwd.getText().toString().trim()).matches()) {
                            ivPwd.setImageResource(R.mipmap.correct);
                        } else {
                            etPwd.setError("由6-16位英文字符、数字、字母组成,区分大小写");
                            ivPwd.setImageResource(R.mipmap.wrong);
                        }
                    }
                    break;
                case R.id.etRepeatPwd:
                    ivRepeatPwd.setVisibility(View.VISIBLE);
                    if (TextUtils.equals(etPwd.getText().toString().trim(), etRepeatPwd.getText().toString().trim())) {
                        ivRepeatPwd.setImageResource(R.mipmap.correct);
                    } else {
                        etRepeatPwd.setError("两次密码输入不正确");
                        ivRepeatPwd.setImageResource(R.mipmap.wrong);
                    }
                    break;
                case R.id.etTel:
                    if (!TextUtils.isEmpty(etTel.getText().toString().trim())) {
                        ivTel.setVisibility(View.VISIBLE);
                        if (TEL.matcher(etTel.getText().toString().trim()).matches()) {
                            ivTel.setImageResource(R.mipmap.correct);
                        } else {
                            etTel.setError("手机号码输入不正确");
                            ivTel.setImageResource(R.mipmap.wrong);
                        }
                    }
                    break;
            }
        }
    }
}
