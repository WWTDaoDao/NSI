package com.nsi.okexsay.ui.login;

import android.content.Intent;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.wiget.CountDownButton;
import com.nsi.okexsay.wiget.LoadingDialog;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class FindPasswordTwoActivity extends BaseActivity implements View.OnClickListener{

    private Button btn_next;
    private EditText et_password,et_password_confirm;
    private AppContext appContext;
    private SharedPreferences sp;
    private LoadingDialog dialog;
    private String sessionid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password_two);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        sessionid = bundle.getString("sessionid");
        btn_next = findViewById(R.id.btn_next_step);
        et_password=findViewById(R.id.edit_password);
        et_password_confirm=findViewById(R.id.edit_password_confirm);
        btn_next.setOnClickListener(this);
    }

    private void resetPassword() {
        String password =  et_password.getText().toString().trim();
        String passwordConfirm =  et_password_confirm.getText().toString().trim();
        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConfirm)){
            showShortToast("各项都不能为空！");
            return;
        } else if(!password.equals(passwordConfirm)){
            showShortToast("两次密码输入不同，请重新输入！");
            return;
        }
        dialog.show();
        RequestParams params =  new RequestParams();
        params.put("newPassword", password);
        params.put("newPassword2", passwordConfirm);
        params.put("sessionid", sessionid);
        HttpRequest.post(StaticField.RESET_PASSWORD, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if(jsonObject.getInteger("code") == 0){
                    showLongToast("密码修改成功！");
                    finish();
                } else {
                    showLongToast(jsonObject.getString("msg"));
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
        switch(view.getId()){
            case R.id.btn_next_step:
                resetPassword();
                break;
        }
    }
}
