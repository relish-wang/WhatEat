package com.xhxkj.zhcs.activity;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xhxkj.zhcs.App;
import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.entity.UserEntity;
import com.xhxkj.zhcs.presenter.RegisterAtyPst;
import com.xhxkj.zhcs.util.AppToast;
import com.xhxkj.zhcs.view.AppActionBar;
import com.xhxkj.zhcs.vm.RegisterAtyView;

import java.util.Random;
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

    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.btnCode)
    Button btnCode;

    String mobile;
    String verify_code;

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
        appActionBar.setBtnBackText("登录");
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

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(etTel.getText().toString().trim())) {
                    if (etTel.getText().toString().trim().length() == 11) {
                        mobile = etTel.getText().toString().trim();
                        if (mobile.matches(TEL.pattern())) {
                            verify_code = generateCode();
                            if (ContextCompat.checkSelfPermission(RegisterAty.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(RegisterAty.this, new String[]{Manifest.permission.SEND_SMS}, 2);
                            } else {
                                sendSms();
                            }
                        } else {
                            Toast.makeText(RegisterAty.this, "请输入完整的电话号码", Toast.LENGTH_LONG).show();
                            etTel.requestFocus();
                        }
                    } else {
                        Toast.makeText(RegisterAty.this, "请输入完整的电话号码", Toast.LENGTH_LONG).show();
                        etTel.requestFocus();
                    }
                } else {
                    Toast.makeText(RegisterAty.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
                    etTel.requestFocus();
                }
            }
        });
    }


    int second = 60;

    private void sendSms() {
        sendNotification("新消息", getString(R.string.send_verfiy_code_message, verify_code));
        etRepeatPwd.clearFocus();
        closeKeyboard();
        btnCode.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
        btnCode.setClickable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (second > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    second--;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnCode.setText("(" + second + ")");
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnCode.setEnabled(true);
                        btnCode.setClickable(true);
                        btnCode.setText("获取\n验证码");
                        btnCode.setBackgroundResource(R.drawable.btn_deep_selector);
                    }
                });
            }
        }).start();

    }
    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private static String generateCode() {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public void onRegisterSuccess() {
        Intent i = new Intent();
        i.putExtra(UserEntity.NAME, etName.getText().toString());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case 2:
                    sendSms();
                    break;
            }
        } else {
            showMessage("拒绝权限将无法使用该软件");
        }
    }


    protected void sendNotification(final String title, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showMessage("发送验证码成功！");
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(RegisterAty.this);
                builder.setSmallIcon(R.mipmap.icon_transparent)
                        .setContentText(message)
                        .setContentTitle(title)
                        .setTicker("新消息")
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setOnlyAlertOnce(true)
                        .setShowWhen(true)
                        .setWhen(System.currentTimeMillis());
                Intent intent = new Intent(App.CONTEXT, RegisterAty.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(RegisterAty.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pendingIntent);
                Notification notification = builder.build();
                NotificationManagerCompat manager = NotificationManagerCompat.from(RegisterAty.this);
                manager.notify(1, notification);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etCode.setText(verify_code);
                    }
                });
            }
        }).start();
    }
}
