package com.nsi.okexsay.ui.login;

import android.content.Context;
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

public class FindPasswordActivity extends BaseActivity implements View.OnClickListener{

    private Button btn_next;
    private EditText et_phone,et_verify_code;
    private Button tv_verification_code;
    private Spinner spinner_area;
    private Context mContext;
    private CountDownButton countDownButton;
    private AppContext appContext;
    private SharedPreferences sp;
    private LoadingDialog dialog;
    private String sessionidVerifyCode = "";
    private String sessionid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        initView();
        getAreaCode();
    }


    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        btn_next = findViewById(R.id.btn_next_step);
        et_phone = findViewById(R.id.edit_phone);
        et_verify_code= findViewById(R.id.edit_find_code);
        tv_verification_code = findViewById(R.id.tv_verification_code);
        spinner_area = findViewById(R.id.spinner_area_find);

        countDownButton = new CountDownButton(CountDownButton.TIME_COUNT_FUTURE, CountDownButton.TIME_COUNT_INTERVAL);
        mContext = this;
        countDownButton.init(mContext, tv_verification_code);
        appContext = (AppContext) getApplication();

        btn_next.setOnClickListener(this);
        tv_verification_code.setOnClickListener(this);

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


    private void getVerifyCode() {
        String nameText = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(nameText)) {
            return;
        }
        RequestParams params = new RequestParams();
        params.put("type", 9);
        params.put("areaCode", "86");
        params.put("phone", nameText);
        HttpRequest.post(StaticField.GET_VERIFY_CODE, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if(jsonObject.getInteger("code") == 0){
                    sessionidVerifyCode = jsonObject.getString("sessionid");
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

    private Boolean checkValue(){
        String nameText =  et_phone.getText().toString().trim();
        String verifyText =  et_verify_code.getText().toString().trim();
        if(TextUtils.isEmpty(nameText)|| TextUtils.isEmpty(verifyText)){
            showShortToast("各项都不能为空！");
            return false;
        }
        return true;
    }


    private void validatePhone() {
        final String nameText =  et_phone.getText().toString().trim();
        final String verifyText =  et_verify_code.getText().toString().trim();
        if(TextUtils.isEmpty(nameText)|| TextUtils.isEmpty(verifyText)){
            showShortToast("各项都不能为空！");
            return;
        }
        dialog.show();
        RequestParams params =  new RequestParams();
        params.put("phone", nameText);
        params.put("msgcode", verifyText);
        params.put("areaCode", "86");
        params.put("sessionid", sessionidVerifyCode);
        HttpRequest.post(StaticField.PHONE_VALIDATION, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                dialog.dismiss();
                if(jsonObject.getInteger("code") == 0){
                    sessionid = jsonObject.getString("sessionid");
                   Intent intent = new Intent(FindPasswordActivity.this, FindPasswordTwoActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("phone", nameText);
                    bundle1.putString("msgcode", verifyText);
                    bundle1.putString("sessionid", sessionid);
                    intent.putExtras(bundle1);
                    startActivity(intent);
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
              validatePhone();
                break;
            case R.id.tv_verification_code:
                getVerifyCode();
                break;
        }
    }
}