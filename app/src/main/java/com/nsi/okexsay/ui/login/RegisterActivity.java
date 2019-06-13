package com.nsi.okexsay.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.Internationals;
import com.nsi.okexsay.R;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.utils.JsonUtil;
import com.nsi.okexsay.wiget.CountDownButton;
import com.nsi.okexsay.wiget.LoadingDialog;

import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_register;
    private EditText et_phone, et_password, edit_password_confirm,et_verify_code, et_invitation_code;
    private Button btn_verify_code;
    private Spinner spinner_area;
    private Context mContext;
    private CountDownButton countDownButton;
    private AppContext appContext;
    private SharedPreferences sp;
    private LoadingDialog dialog;
    private boolean isCanUse = false;
    private String sessionid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        getAreaCode();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        btn_register = findViewById(R.id.btn_register);
        et_phone = findViewById(R.id.edit_phone);
        et_verify_code = findViewById(R.id.edit_verify_code);
        et_password = findViewById(R.id.et_password);
        edit_password_confirm = findViewById(R.id.edit_pwd_confirm);
        et_invitation_code = findViewById(R.id.edit_invitation_code);
        btn_verify_code = findViewById(R.id.tv_verification_code);
        spinner_area = findViewById(R.id.spinner_area);

        countDownButton = new CountDownButton(CountDownButton.TIME_COUNT_FUTURE, CountDownButton.TIME_COUNT_INTERVAL);
        mContext = RegisterActivity.this;
        countDownButton.init(mContext, btn_verify_code);
        appContext = (AppContext) getApplication();

        et_phone.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    checkRegName();
                }
            }
        });

        btn_register.setOnClickListener(this);
        btn_verify_code.setOnClickListener(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, R.layout.spinner_stytle);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_stytle);
        spinner_area.setAdapter(adapter);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void getAreaCode() {
        RequestParams params = new RequestParams();
        params.put("name", "name");
        HttpRequest.post(StaticField.GET_INTERNATIONALS, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                List<Internationals> areaList = JsonUtil.parseList(jsonObject.getString("internationals"), Internationals.class);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                showShortToast(msg);
            }
        });
    }

    private void checkRegName() {
        String nameText = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(nameText)) {
            return;
        }
        RequestParams params = new RequestParams();
        params.put("name", nameText);
        params.put("type", 0);
        HttpRequest.post(StaticField.CHECK_REGNAME, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                Log.d("checkRegName=====", apiResponse.toString());
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                Log.d("jsonObject=====", jsonObject.toJSONString());
                if (jsonObject.getInteger("code") == 0) {
                    isCanUse = true;
                } else {
                    isCanUse = false;
                    showShortToast(jsonObject.getString("msg"));
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                showShortToast(msg);
            }
        });
    }

    private void getVerifyCode() {
        String nameText = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(nameText)) {
            return;
        }
        RequestParams params = new RequestParams();
        params.put("type", 12);
        params.put("areaCode", "86");
        params.put("phone", nameText);
        HttpRequest.post(StaticField.GET_VERIFY_CODE, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if(jsonObject.getInteger("code") == 0){
                    sessionid = jsonObject.getString("sessionid");
                    showLongToast("验证码已发送，请注意查收");
                    countDownButton.start();
                } else {
                    showLongToast(jsonObject.getString("msg"));
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                showShortToast(msg);
            }
        });
    }

    private void registerSave() {
        String nameText = et_phone.getText().toString().trim();
        String verifyText = et_verify_code.getText().toString().trim();
        String passwordText = et_password.getText().toString().trim();
        String passwordConfirmText = edit_password_confirm.getText().toString().trim();
        String invitationCode = et_invitation_code.getText().toString().trim();
        if (TextUtils.isEmpty(nameText) || TextUtils.isEmpty(verifyText) || TextUtils.isEmpty(passwordText)) {
            showShortToast("各项都不能为空！");
            return;
        }
        if (!isCanUse) {
            showShortToast("该手机号码错误或已存在，请更换手机号！");
            return;
        }
        if(!passwordText.equals(passwordConfirmText)){
            showShortToast("两次密码输入不同，请重新输入！");
            return;
        }
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("sessionid", sessionid);
        params.put("regName", nameText);
        params.put("password", passwordText);
        params.put("regType", "0");
        params.put("phoneCode", verifyText);
        params.put("areaCode", "86");
        if(!TextUtils.isEmpty(invitationCode)){
            params.put("intro_user", invitationCode);
        }
        HttpRequest.post(StaticField.SAVE_REGISTER, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if(jsonObject.getInteger("code") == 0){
                    showShortToast("注册成功！");
                    finish();
                } else {
                    showShortToast(jsonObject.getString("msg"));
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                dialog.dismiss();
                showShortToast("请求失败，请检查网络连接！");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                registerSave();
                break;
            case R.id.tv_verification_code:
                getVerifyCode();
                break;
        }
    }
}
