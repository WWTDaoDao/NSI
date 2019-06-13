package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.utils.GlideUtil;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

//确认付款，微信支付宝
public class LawPaymentTwoMSG extends BaseActivity implements View.OnClickListener {
    private TextView tv_title;
    private String orderFidstr = "";
    private String paymentstr = "";
    private AppContext appContext;
    private SharedPreferences sp;
    private TextView Tv_number;
    private TextView Tv_order;//订单编号
    private TextView je;
    private TextView postMsg, cancel;
    private ImageView IV_zhifu;
    private String transaction="";
    private String jsonString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymenttwomsg);
        appContext = (AppContext) getApplicationContext();
        sp = appContext.preferences;
        orderFidstr = getIntent().getStringExtra("orderFid");//
        paymentstr = getIntent().getStringExtra("payment");//交易类型(usdt,cpt)
        transaction = getIntent().getStringExtra("transaction");
        jsonString=getIntent().getStringExtra("jsonStringjson");
        initView();    }

    private void initView() {
        je = findViewById(R.id.je);
        IV_zhifu = findViewById(R.id.IV_zhifu);
        Tv_number = findViewById(R.id.Tv_number);
        Tv_order = findViewById(R.id.Tv_order);
        tv_title = findViewById(R.id.home_text_title);
        postMsg = findViewById(R.id.postMsg);
        cancel = findViewById(R.id.cancel);
        tv_title.setText("确认付款");
        postMsg.setOnClickListener(this);
        cancel.setOnClickListener(this);
        if (transaction.equals("出售")){
            cancel.setVisibility(View.GONE);
        }
        JSONObject jsonObject = JSON.parseObject(jsonString);
        Log.e("TO_ZHIFUS",jsonObject.toString());
        //String typeString = jsonObject.getString("yh_zfb_wx");
        if (paymentstr.equals("1")) {
            if (jsonObject.getString("yh_zfb_wx").equals("1")) {
                Tv_number.setText("对方微信账号：" + jsonObject.getString("wxzh"));
                Tv_order.setText(jsonObject.getString("orderNo"));
                je.setText(jsonObject.getString("sjfy"));
                GlideUtil.diskCacheStrategy(StaticField.HOST_BASE+jsonObject.getString("wxPath"), LawPaymentTwoMSG.this, IV_zhifu, R.mipmap.img_building);
            } else {
                showLongToast("暂无支付方式");
            }
        } else {
            if (jsonObject.getString("yh_zfb_wx").equals("2")) {
                Tv_number.setText("对方支付宝账号：" + jsonObject.getString("zbfzh"));
                Tv_order.setText(jsonObject.getString("orderNo"));
                je.setText(jsonObject.getString("sjfy"));
                GlideUtil.diskCacheStrategy(StaticField.HOST_BASE+jsonObject.getString("zfbPath"), LawPaymentTwoMSG.this, IV_zhifu, R.mipmap.img_building);
            } else {
                showLongToast("暂无支付方式");
            }
        }
    }

    /**
     * 付款后确认订单
     */
    private void postConfirm(String orderFidString) {
        RequestParams params = new RequestParams();
        params.put("orderFId", Integer.valueOf(orderFidString));//订单id
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.YFK, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                //  Log.e("PaymentTwoMSGJSON", jsonObject.toJSONString());
                //   String scny = jsonObject.getString("c2c");
                showLongToast("确认成功！");
                OrderPageProgressivetenseFragment.typeindex=true;
                finish();             //    showLongToast();
//                yhkh.setText("对方银行卡号：" + jsonObject.getString("yhkh"));
//                khy.setText(jsonObject.getString("yh") + ":" + jsonObject.getString("dz"));
//                je.setText(jsonObject.getString("sjfy"));
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                showShortToast(msg);
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
                showLongToast("取消成功！");
                OrderPageProgressivetenseFragment.typeindex=true;
                finish();
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(LawPaymentTwoMSG.this,LoginActivity.class));
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
                Log.e("PaymentTwoMSGJSON", jsonObject.toJSONString());
                if (paymentstr.equals("1")) {
                    if (jsonObject.getString("yh_zfb_wx").equals("1")) {
                        Tv_number.setText("对方微信账号：" + jsonObject.getString("wxzh"));
                        Tv_order.setText(jsonObject.getString("orderNo"));
                        je.setText(jsonObject.getString("sjfy"));
                        GlideUtil.diskCacheStrategy(StaticField.HOST_BASE+jsonObject.getString("wxPath"), LawPaymentTwoMSG.this, IV_zhifu, R.mipmap.img_building);
                    } else {
                      showLongToast("暂无支付方式");
                    }
                } else {
                    if (jsonObject.getString("yh_zfb_wx").equals("2")) {
                        Tv_number.setText("对方支付宝账号：" + jsonObject.getString("zbfzh"));
                        Tv_order.setText(jsonObject.getString("orderNo"));
                        je.setText(jsonObject.getString("sjfy"));
                        GlideUtil.diskCacheStrategy(StaticField.HOST_BASE+jsonObject.getString("wxPath"), LawPaymentTwoMSG.this, IV_zhifu, R.mipmap.img_building);
                    } else {
                        showLongToast("暂无支付方式");
                    }
                }
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(LawPaymentTwoMSG.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }


            }
        });
    }

}
