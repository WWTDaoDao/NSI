package com.nsi.okexsay.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.UserBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.common.MainActivity;
import com.nsi.okexsay.utils.JsonUtil;
import com.nsi.okexsay.wiget.LoadingDialog;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_login;
    private EditText et_phone, et_password;
    private TextView tv_forget, tv_register;
    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        btn_login = findViewById(R.id.btn_login);
        et_phone = findViewById(R.id.edit_phone);
        et_password = findViewById(R.id.edit_passwd);
        tv_forget = findViewById(R.id.forget_pwd);
        tv_register = findViewById(R.id.register);

        btn_login.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    private void loginClick() {
        dialog.show();
        final String nameText = et_phone.getText().toString().trim();
        final String passwordText = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(nameText) || TextUtils.isEmpty(passwordText)) {
            showShortToast("用户名和密码不能为空！");
            return;
        }
        RequestParams params = new RequestParams();
        params.put("loginName", nameText);
        params.put("password", passwordText);
        HttpRequest.post(StaticField.LOGIN, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if(jsonObject.getInteger("code") == 0){
                    showShortToast("登录成功");
                   // Log.e("LoginActivityjjj",jsonObject.toJSONString());
                    UserBean userInfo = JsonUtil.parseObject(jsonObject.getString("fuser"), UserBean.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean(SharedPreferencesKeys.IS_LOGIN, true);
                    editor.putString(SharedPreferencesKeys.USER_INFO, jsonObject.getString("fuser"));
                    editor.putString(SharedPreferencesKeys.FID, userInfo.getFid());
                    editor.putString(SharedPreferencesKeys.ID_CARD, userInfo.getFidentityNo());
                    editor.putString(SharedPreferencesKeys.LOGIN_NAME, nameText);
                    editor.putString(SharedPreferencesKeys.PASSWORD, passwordText);
                    editor.putString(SharedPreferencesKeys.NICK_NAME, userInfo.getFnickName());
                    editor.putString(SharedPreferencesKeys.PHONE, userInfo.getFtelephone());
                    editor.putString(SharedPreferencesKeys.REAL_NAME, userInfo.getFrealName());
                    if (userInfo.getFpostRealValidate()){
                        editor.putString(SharedPreferencesKeys.POST_REAL_VALIDATE, "true");
                    }else {
                        editor.putString(SharedPreferencesKeys.POST_REAL_VALIDATE, "false");
                    }
                    if (userInfo.getFhasRealValidate()){
                        editor.putString(SharedPreferencesKeys.HAS_REAL_VALIDATE, "true");
                    }else {
                        editor.putString(SharedPreferencesKeys.HAS_REAL_VALIDATE, "false");
                    }
                    editor.putString(SharedPreferencesKeys.TOKEN, jsonObject.getString("token"));
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    showShortToast(jsonObject.getString("msg"));
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                dialog.dismiss();
                showShortToast(msg);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginClick();
                break;
            case R.id.forget_pwd:
                Intent intentFind = new Intent(this, FindPasswordActivity.class);
                startActivity(intentFind);
                break;
            case R.id.register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}
