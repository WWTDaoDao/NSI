package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.VirtualtocptBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.interfaces.OnIconClickListener;
import com.nsi.okexsay.utils.JsonUtil;
import com.nsi.okexsay.utils.SoftKeyboardStateHelper;
import com.nsi.okexsay.wiget.LoadingDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class ExchangeActivity extends BaseActivity implements View.OnClickListener, OnIconClickListener {

    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    private LinearLayout root_layout;
    private ImageView homeBtnBack;
    private TextView homeTextTitle,homeTextRight;
    private TextView tv_number_can_use, tv_exchange_rate, tv_number_cpt,tv_type;
    private EditText et_number_own;
    private Button btn_submit;
    private List<VirtualtocptBean> iconList = new ArrayList<>();
    private List<String> xnbTotal = new ArrayList<>();
    private int selectIndex = 0;
    private double currentExchageRate = 0;
    private String fromFlag = "HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        initView();
        getIconData();
    }

    private void initView() {
        fromFlag = getIntent().getStringExtra("fromFlag");
        if(TextUtils.isEmpty(fromFlag)){
            fromFlag = "HOME";
        }
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(ExchangeActivity.this);
        root_layout = findViewById(R.id.root_layout);
        tv_type = findViewById(R.id.text_type);
        tv_number_can_use = findViewById(R.id.text_number_can_use);
        tv_exchange_rate = findViewById(R.id.text_exchange_rate);
        et_number_own = findViewById(R.id.edit_number_own);
        tv_number_cpt = findViewById(R.id.text_number_cpt);
        btn_submit = findViewById(R.id.btn_submit);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeTextRight = findViewById(R.id.home_text_right);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextRight.setVisibility(View.VISIBLE);
        homeTextTitle.setText("CPT兑换");

        root_layout.setOnClickListener(this);
        tv_type.setOnClickListener(this);
        homeTextRight.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);

        et_number_own.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    String numberContent = et_number_own.getText().toString().trim();
                    if (!TextUtils.isEmpty(numberContent)) {
                        Double counts = currentExchageRate * Double.parseDouble(numberContent);
                        tv_number_cpt.setText(counts + "");
                    }
                }
            }
        });
        et_number_own.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //输入文字前触发
                showShortToast("输入文字前触发");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //text改变过程中，一般在此加入监听事件。
                showShortToast("改变过程中");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //输入后触发
                showShortToast("输入后触发");
            }
        });

        //监听软键盘的打开与收起
        SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(findViewById(R.id.root_layout));
        softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                //键盘打开
//                showShortToast("键盘弹出！");
            }

            @Override
            public void onSoftKeyboardClosed() {
                //键盘关闭
//                showShortToast("键盘关闭！");
                String numberContent = et_number_own.getText().toString().trim();
                if (!TextUtils.isEmpty(numberContent)) {
                    Double counts = currentExchageRate * Double.parseDouble(numberContent);
                    tv_number_cpt.setText(counts + "");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.root_layout:
                et_number_own.clearFocus();
                break;
            case R.id.text_type:
                Intent intentType = new Intent(this,SelectIconTypeActivity.class);
                intentType.putExtra("typeList", (Serializable)iconList);
                startActivityForResult(intentType,100);
                break;
            case R.id.btn_submit:
                saveExchange();
                break;
            case R.id.home_text_right:
                Intent intent = new Intent(this, IconExchangeHistoryActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null){
            selectIndex = data.getExtras().getInt("position");
            currentExchageRate = Double.parseDouble(iconList.get(selectIndex).getExchangerate());
            tv_type.setText(iconList.get(selectIndex).getShortname());
            tv_number_can_use.setText(xnbTotal.get(selectIndex));
            tv_exchange_rate.setText(iconList.get(selectIndex).getExchangerate());
        }
    }

    @Override
    public void onChangeData(int position) {
        selectIndex = position;
        currentExchageRate = Double.parseDouble(iconList.get(position).getExchangerate());
        tv_number_can_use.setText(xnbTotal.get(position));
        tv_exchange_rate.setText(iconList.get(position).getExchangerate());
    }

    private void getIconData() {
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TONG_YONG_INTEGRAL, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                xnbTotal = JsonUtil.parseList(jsonObject.getString("xnbTotal"), String.class);
                iconList = JsonUtil.parseList(jsonObject.getString("VirtualtocptList2"), VirtualtocptBean.class);
                if (xnbTotal.size() > 0 && iconList.size() > 0) {
                    for(int i = 0; i < iconList.size(); i++){
                        if(iconList.get(i).getShortname().equals("BOB")){
                            iconList.remove(i);
                            xnbTotal.remove(i);
                        }
                    }
                    selectIndex = 0;
                    currentExchageRate = Double.parseDouble(iconList.get(0).getExchangerate());
                    tv_number_can_use.setText(xnbTotal.get(0));
                    tv_type.setText(iconList.get(0).getShortname());
                    tv_exchange_rate.setText(iconList.get(0).getExchangerate());
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                dialog.dismiss();
                if(TextUtils.isEmpty(msg)){
                    msg = "网络请求失败，请检查网络连接！";
                }
                showShortToast(msg);
            }
        });
    }

    private void saveExchange() {
        String numberContent = et_number_own.getText().toString().trim();
        Double counts = 0.0;
        if (TextUtils.isEmpty(numberContent)) {
            showShortToast("请输入兑换数量！");
            return;
        } else {
            counts = currentExchageRate * Double.parseDouble(numberContent);
            tv_number_cpt.setText(counts + "");
            if(Double.parseDouble(numberContent) > Double.parseDouble(xnbTotal.get(selectIndex))){
                showShortToast("可用数量不足，请重新输入！");
                return;
            }
        }
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        params.put("fVi_fId", iconList.get(selectIndex).getFvirtualcointype().getFid());
        params.put("vrfName", iconList.get(selectIndex).getVirtualname());
        params.put("shortname", iconList.get(selectIndex).getShortname());
        params.put("exchangerate", iconList.get(selectIndex).getExchangerate());
        params.put("vrfexchangeNum", numberContent);
        params.put("fTotal", xnbTotal.get(selectIndex));
        HttpRequest.post(StaticField.SAVE_RECORD, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (jsonObject.getInteger("code") == 0) {
                    showShortToast("兑换成功！");
                    if(fromFlag.equals("PAY")){
                        Intent intent = new Intent();
                        ExchangeActivity.this.setResult(RESULT_OK, intent);
                        ExchangeActivity.this.finish();
                    } else {
                        finish();
                    }

                } else {
                    showShortToast(jsonObject.getString("msg"));
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                dialog.dismiss();
                if (!TextUtils.isEmpty(msg)) {
                    showShortToast(msg);
                } else {
                    showShortToast("请求失败，请检查网络！");
                }
            }
        });
    }

}
