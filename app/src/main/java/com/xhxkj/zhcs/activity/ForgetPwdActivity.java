package com.xhxkj.zhcs.activity;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xhxkj.zhcs.R;
import com.xhxkj.zhcs.base.BaseAty;
import com.xhxkj.zhcs.util.PhoneUtils;
import com.xhxkj.zhcs.view.AppActionBar;

import java.util.Random;


/**
 * 忘记密码
 * Created by Relish on 2016/12/4.
 */
public class ForgetPwdActivity extends BaseAty implements View.OnClickListener {
    @Override
    protected int layoutResId() {
        return R.layout.activity_forget_pwd;
    }

    public int currentPage = 0;

    private String mMobile;

    @Override
    protected void parseIntent(Intent intent) {
        super.parseIntent(intent);
        mMobile = intent.getStringExtra("mobile");

    }


    protected void initToolbar(Bundle savedInstanceState, Toolbar mToolbar) {
        mToolbar.setTitle("忘记密码");
    }


    public static void open(Context context, String mobile) {
        Intent intent = new Intent(context, ForgetPwdActivity.class);
        intent.putExtra("mobile", mobile);
        context.startActivity(intent);
    }

    private String code = "";

    private ImageView iv_mobile_or_pwd;
    private EditText et_mobile_or_pwd;
    private EditText et_verify_code_or_repeat_pwd;
    private Button btn_get_verify_code;
    private Button btn_next_or_commit;

    @Override
    protected void initViews() {
        iv_mobile_or_pwd = (ImageView) findViewById(R.id.iv_mobile_or_pwd);
        et_mobile_or_pwd = (EditText) findViewById(R.id.et_mobile_or_pwd);
        et_verify_code_or_repeat_pwd = (EditText) findViewById(R.id.et_verify_code_or_repeat_pwd);
        btn_get_verify_code = (Button) findViewById(R.id.btn_get_verify_code);
        btn_next_or_commit = (Button) findViewById(R.id.btn_next_or_commit);

        if (!TextUtils.isEmpty(mMobile)) {
            et_mobile_or_pwd.setText(mMobile);
        }

        btn_get_verify_code.setOnClickListener(this);
        btn_next_or_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_verify_code:
                if (!TextUtils.isEmpty(et_mobile_or_pwd.getText().toString().trim())) {
                    if (et_mobile_or_pwd.getText().toString().trim().length() == 11) {
                        mMobile = et_mobile_or_pwd.getText().toString().trim();
                        if (mMobile.matches(PhoneUtils.MOBILE_PATTERN)) {
                            code = generateCode();
                            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 2);
                            } else {
                                sendSms();
                            }
                        } else {
                            Toast.makeText(this, R.string.phone_number_error, Toast.LENGTH_LONG).show();
                            et_mobile_or_pwd.requestFocus();
                        }
                    } else {
                        Toast.makeText(this, R.string.phone_number_error, Toast.LENGTH_LONG).show();
                        et_mobile_or_pwd.requestFocus();
                    }
                } else {
                    Toast.makeText(this, R.string.phone_number_empty, Toast.LENGTH_LONG).show();
                    et_mobile_or_pwd.requestFocus();
                }
                break;
            case R.id.btn_next_or_commit:
                if (currentPage == 0) {
                    String code = et_verify_code_or_repeat_pwd.getText().toString().trim();
                    if (TextUtils.isEmpty(code)) {
                        showMessage("验证码不得为空！");
                        return;
                    }
                    if (!code.matches("^[0-9]{4}$")) {
                        showMessage("验证码格式不正确！");
                        return;
                    }
                    if (!TextUtils.equals(this.code, code)) {
                        showMessage("验证码不正确！");
                        return;
                    }
                    turnPage();
                } else {
                    String pwd = et_mobile_or_pwd.getText().toString().trim();
                    String repeat_pwd = et_verify_code_or_repeat_pwd.getText().toString().trim();
                    if (TextUtils.isEmpty(pwd)) {
                        showMessage("密码不得为空");
                        return;
                    }
                    if (!TextUtils.equals(pwd, repeat_pwd)) {
                        showMessage("两次密码输入不一致");
                        return;
                    }
                    resetPwdSuccess(pwd);
                }
                break;
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

    private void sendSms() {
        new CheckUserExistTask().execute(mMobile);
    }


    protected void sendNotification(String title, String message) {
        new Thread(() -> {
            runOnUiThread(() -> showMessage("发送验证码成功！"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
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
            Notification notification = builder.build();
            NotificationManagerCompat manager = NotificationManagerCompat.from(ForgetPwdActivity.this);
            manager.notify(1, notification);
            runOnUiThread(() -> et_verify_code_or_repeat_pwd.setText(code));
        }).start();
    }

    private void turnPage() {
        currentPage = 1;
        iv_mobile_or_pwd.setImageResource(R.drawable.ic_password);
        et_mobile_or_pwd.setHint("新密码");
        et_mobile_or_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_mobile_or_pwd.setText("");
        et_mobile_or_pwd.invalidate();
        et_verify_code_or_repeat_pwd.setHint("重复密码");
        et_verify_code_or_repeat_pwd.setText("");
        et_verify_code_or_repeat_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_verify_code_or_repeat_pwd.invalidate();
        btn_get_verify_code.setVisibility(View.GONE);
        btn_next_or_commit.setText("提交");
    }

    private void resetPwdSuccess(String pwd) {
        new ResetPwdTask().execute(pwd);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setActionBarTitle("忘记密码");
        appActionBar.setBtnBackText("登录");
    }

    int second = 60;

    private class CheckUserExistTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                sendNotification("新消息", getString(R.string.send_verfiy_code_message, code));
                closeKeyboard();
                btn_get_verify_code.setBackgroundColor(ContextCompat.getColor(ForgetPwdActivity.this, R.color.gray));
                btn_get_verify_code.setClickable(false);
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
                                    btn_get_verify_code.setText("获取验证码(" + second + ")");
                                }
                            });
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btn_get_verify_code.setEnabled(true);
                                btn_get_verify_code.setClickable(true);
                                btn_get_verify_code.setText("获取\n验证码");
                                btn_get_verify_code.setBackgroundResource(R.drawable.btn_deep_selector);
                            }
                        });
                    }
                }).start();
            } else {
                showMessage("用户不存在！");
            }
        }
    }

    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private class ResetPwdTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String pwd = params[0];
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            showMessage("密码重置成功！");
//            Intent intent = new Intent(ForgetPwdActivity.this, LoginAty.class);
//            intent.putExtra("logout", true);
//            startActivity(intent);
            setResult(RESULT_OK);
            finish();
        }
    }

    private void backPage() {
        currentPage = 0;
        iv_mobile_or_pwd.setImageResource(R.drawable.ic_phone);
        et_mobile_or_pwd.setHint("手机");
        et_mobile_or_pwd.setText(mMobile);
        et_mobile_or_pwd.setInputType(InputType.TYPE_CLASS_NUMBER);
        et_mobile_or_pwd.invalidate();
        et_verify_code_or_repeat_pwd.setHint("验证码");
        et_verify_code_or_repeat_pwd.setText(code);
        et_verify_code_or_repeat_pwd.setInputType(InputType.TYPE_CLASS_NUMBER);
        et_verify_code_or_repeat_pwd.invalidate();
        btn_get_verify_code.setVisibility(View.VISIBLE);
        btn_next_or_commit.setText("下一步");
    }

    @Override
    public void onBackPressed() {
        if (currentPage == 1) {
            backPage();
        } else {
            super.onBackPressed();
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
            showMessage(getString(R.string.permission_denied_message));
        }
    }

    public static final int REQUEST_CODE_SUCCESS = 0x4567;
}
