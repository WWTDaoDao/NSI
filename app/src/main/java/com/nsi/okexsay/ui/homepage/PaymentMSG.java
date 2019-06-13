package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.login.LoginActivity;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

//确认付款
public class PaymentMSG extends BaseActivity implements View.OnClickListener {
    private TextView tv_title;
    private String orderFidstr = "";
    private String paymentstr = "";
    private AppContext appContext;
    private SharedPreferences sp;
    private TextView yhkh;
    private TextView khy;
    private TextView je;
    private TextView postMsg,cancel,khname;
private String transaction="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentmsg);
        appContext = (AppContext) getApplicationContext();
        sp = appContext.preferences;
        orderFidstr = getIntent().getStringExtra("orderFid");//
        paymentstr = getIntent().getStringExtra("payment");//交易类型(usdt,cpt)
        transaction = getIntent().getStringExtra("type");
        Shoudialog();
        initView();
        postData(orderFidstr);
    }

    private void initView() {
        je = findViewById(R.id.je);
        khy = findViewById(R.id.khy);
        yhkh = findViewById(R.id.yhkh);
        postMsg = findViewById(R.id.postMsg);
        cancel = findViewById(R.id.cancel);
        tv_title = findViewById(R.id.home_text_title);
        khname=findViewById(R.id.khname);
        tv_title.setText("确认付款");
        postMsg.setOnClickListener(this);
        cancel.setOnClickListener(this);
        if (transaction.equals("出售")){
            cancel.setVisibility(View.GONE);
        }
    }

    /**
     * 提交购买数据
     */
    private void postData(String orderFidString) {
        RequestParams params = new RequestParams();
        params.put("orderFid", Integer.valueOf(orderFidString));//订单id
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TO_ZHIFUS, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                //  Log.e("PaymentTwoMSGJSON", jsonObject.toJSONString());
                String scny = jsonObject.getString("c2c");
               // Log.e("PaymentTwoMSGJSON", jsonObject.toJSONString());
                yhkh.setText("对方银行卡号：" + jsonObject.getString("yhkh"));
                khy.setText(jsonObject.getString("yh") + ":" + jsonObject.getString("dz"));
                je.setText(jsonObject.getString("sjfy"));
                khname.setText("真实姓名："+jsonObject.getString("name"));
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(PaymentMSG.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }


            }
        });
    }

    /**
     * 取消订单
     */
    private void postcancel(String orderFidString) {
        RequestParams params = new RequestParams();
        params.put("orderFId", Integer.valueOf(orderFidString));//订单id
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.CANCEL, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                Log.e("Paymentpostcancel", jsonObject.toJSONString());
                OrderPageProgressivetenseFragment.typeindex=true;
                showLongToast("取消成功！");
                finish();
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(PaymentMSG.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }
    /**
     * 付款后确认订单
     */
    private void postConfirm(String orderFidString) {
        RequestParams params = new RequestParams();
        params.put("orderFId", Integer.valueOf(orderFidString));//订单id
        params.put("fuserId", Integer.valueOf(sp.getString(SharedPreferencesKeys.FID, "")));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.YFK, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                //  Log.e("PaymentTwoMSGJSON", jsonObject.toJSONString());
                //   String scny = jsonObject.getString("c2c");
                Log.e("PaymentpostConfirm", jsonObject.toJSONString());
                OrderPageProgressivetenseFragment.typeindex=true;
                showLongToast("确认成功！");
                finish();
                //    showLongToast();
//                yhkh.setText("对方银行卡号：" + jsonObject.getString("yhkh"));
//                khy.setText(jsonObject.getString("yh") + ":" + jsonObject.getString("dz"));
//                je.setText(jsonObject.getString("sjfy"));
//                setData();
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(PaymentMSG.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.postMsg:
                Shoudialog();
                postConfirm(orderFidstr);
                break;
            case R.id.cancel:
                Shoudialog();
                postcancel(orderFidstr);
                break;

        }
    }
}
