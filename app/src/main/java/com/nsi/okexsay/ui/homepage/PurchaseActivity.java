package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.PurchaseBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.ui.personcenter.AccountManagementActivity;
import com.nsi.okexsay.utils.JsonUtil;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * 购买
 */
public class PurchaseActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_title;
    private String titlestr = "";
    private String transaction = "";
    private String fId = "";//广告ID
    private PurchaseBean areaList;
    private TextView price;//价格
    private EditText purchase;//数量
    private EditText et_Quota;//限额
    private LinearLayout ll_yinhangka, ll_weixin, zhifubo;
    private TextView pricetype;
    private TextView Amountofmoney;
    private TextView cptOrUsdt;
    private TextView postMsg;
    private TextView whole_Number, whole_Money;
    private ImageView IV_yinhangka, IV_weixin, IV_zhifubao;
    private boolean aBoolean = true;
    private boolean allBoolean = true;
    private AppContext appContext;
    private SharedPreferences sp;
    private String payment = "-1";
    private EditText et_password;
private LinearLayout RLPASS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchaseactivity);
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        titlestr = getIntent().getStringExtra("type");//交易类型(usdt,cpt)
        transaction = getIntent().getStringExtra("transaction");
        fId = getIntent().getStringExtra("fId");
        sp.getString(SharedPreferencesKeys.FID, "");
        initView();
        Shoudialog();
        getAdvertisement(fId);
        setOnListener();
    }


    private void initView() {
        RLPASS=findViewById(R.id.RLPASS);
        tv_title = findViewById(R.id.home_text_title);
        price = findViewById(R.id.price);
        et_Quota = findViewById(R.id.et_Quota);
        ll_yinhangka = findViewById(R.id.ll_yinhangka);
        ll_weixin = findViewById(R.id.ll_weixin);
        zhifubo = findViewById(R.id.zhifubo);
        purchase = findViewById(R.id.purchase);
        pricetype = findViewById(R.id.pricetype);
        Amountofmoney = findViewById(R.id.Amountofmoney);
        cptOrUsdt = findViewById(R.id.cptOrUsdt);
        postMsg = findViewById(R.id.postMsg);//下单
        whole_Number = findViewById(R.id.whole_Number);
        whole_Money = findViewById(R.id.whole_Money);
        IV_yinhangka = findViewById(R.id.IV_yinhangka);
        IV_weixin = findViewById(R.id.IV_weixin);
        IV_zhifubao = findViewById(R.id.IV_zhifubao);
        et_password = findViewById(R.id.et_password);
        tv_title.setText(transaction + titlestr);
        postMsg.setOnClickListener(this);
        whole_Number.setOnClickListener(this);
        whole_Money.setOnClickListener(this);
        ll_yinhangka.setOnClickListener(this);
        ll_weixin.setOnClickListener(this);
        zhifubo.setOnClickListener(this);
        ll_yinhangka.setOnClickListener(this);
        ll_weixin.setOnClickListener(this);
        zhifubo.setOnClickListener(this);
        if (transaction.equals("出售")) {
            postMsg.setText("出售");
            RLPASS.setVisibility(View.VISIBLE);
        }
    }

    private void setOnListener() {
        purchase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (allBoolean) {
                    if (aBoolean) {
                        if (s.toString().equals("") || s.toString().equals("0")) {
                            aBoolean = false;
                            et_Quota.setText("0");
                        } else {
                            aBoolean = false;
                            Double number = Double.valueOf(s.toString());
                            et_Quota.setText(number * areaList.getC2c().getPrice() + "");
                        }
                    } else {
                        aBoolean = true;
                        Log.e("", "");
                    }
                } else {
                    // allBoolean = true;
                }

            }
        });
        et_Quota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (allBoolean) {
                    if (aBoolean) {
                        if (s.toString().equals("") || s.toString().equals("0")) {
                            aBoolean = false;
                            purchase.setText("0");
                        } else {
                            aBoolean = false;
                            purchase.setText(Double.valueOf(et_Quota.getText().toString()) / areaList.getC2c().getPrice() + "");
                        }
                    } else {
                        aBoolean = true;
                        Log.e("", "");
                    }
                } else {
                    //allBoolean=true;
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.postMsg:
                if (purchase.getText().toString().trim().equals("") || et_Quota.getText().toString().trim().equals("")) {
                    showShortToast("请在区间范围内！");
                } else {
                    Double number = Double.valueOf(purchase.getText().toString());
                    if (number <= areaList.getC2c().getAmount() && number * areaList.getC2c().getPrice() >= Double.valueOf(areaList.getC2c().getLimitMin())) {
                        if (payment.equals("-1")) {
                            showLongToast("请选择支付方式！");
                        } else {
                            Shoudialog();
                            if (transaction.equals("出售")) {
                                if (!et_password.getText().toString().isEmpty() && et_password.getText().toString().length() >= 6) {
                                    postSelloutData(areaList.getC2c().getFId() + "", purchase.getText().toString(), payment, et_password.getText().toString());
                                } else {
                                    DissDialog();
                                    showLongToast("请正确输入密码");
                                }
                            } else {
                                postData(areaList.getC2c().getFId() + "", purchase.getText().toString(), payment);
                            }

                        }

                    } else {
                        showShortToast("请在区间范围内！");
                    }

                }

                break;
            case R.id.whole_Number:
                allBoolean = false;
                purchase.setText(areaList.getC2c().getAmount() + "");
                et_Quota.setText(areaList.getC2c().getLimitMax() + "");
                allBoolean = true;
                break;
            case R.id.whole_Money:
                allBoolean = false;
                purchase.setText(areaList.getC2c().getAmount() + "");
                et_Quota.setText(areaList.getC2c().getLimitMax() + "");
                allBoolean = true;
                break;
            case R.id.ll_yinhangka:
                payment = "0";
                IV_yinhangka.setImageDrawable(getResources().getDrawable(R.mipmap.selection));
                IV_weixin.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                IV_zhifubao.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                break;
            case R.id.ll_weixin:
                payment = "1";
                IV_weixin.setImageDrawable(getResources().getDrawable(R.mipmap.selection));
                IV_yinhangka.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                IV_zhifubao.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                break;
            case R.id.zhifubo:
                payment = "2";
                IV_zhifubao.setImageDrawable(getResources().getDrawable(R.mipmap.selection));
                IV_weixin.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                IV_yinhangka.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                break;
        }
    }

    /**
     * 获取广告实体
     */
    private void getAdvertisement(String fIdString) {
        RequestParams params = new RequestParams();
        params.put("fId", fIdString);//广告id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//用户ID);
        HttpRequest.post(StaticField.TO_XIADAN, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                Log.e("PurchaseActivity", jsonObject.toJSONString());
                areaList = JsonUtil.parseObject(jsonObject.toJSONString(), PurchaseBean.class);
                setData();
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(PurchaseActivity.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }

    /**
     * 提交购买数据
     */
    private void postData(String fIdString, String number, String paymentstr) {
        RequestParams params = new RequestParams();
        params.put("paymentType", paymentstr);//添加选择方式
        params.put("fId", fIdString);//广告id
        params.put("amountReal", number);//数量
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//用户ID
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TO_ZHIFU, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                if (jsonObject.getString("result").equals("0")) {
                    showLongToast("下单金额不在范围内");
                } else if (jsonObject.getString("result").equals("01")) {
                    showLongToast("账户余额不足！");
                } else if (jsonObject.getString("result").equals("02")) {
                    showLongToast("该广告已经被撤销！");
                } else if (jsonObject.getString("result").equals("05")) {
                    showLongToast("钱包异常！");
                }  else {
                    if (postMsg.getText().toString().equals("出售")) {
                        showLongToast("您已出售，对方会在20分钟内向您付款！");
                        finish();
                    } else {
                        if (payment.equals("0")) {//银行卡支付
                            Intent intent = new Intent(PurchaseActivity.this, PaymentMSG.class);
                            intent.putExtra("orderFid", jsonObject.getString("orderFid"));//订单ID
                            intent.putExtra("payment", payment);//选择的支付方式
                            intent.putExtra("type", transaction);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(PurchaseActivity.this, PaymentTwoMSG.class);
                            intent.putExtra("orderFid", jsonObject.getString("orderFid"));//订单ID
                            intent.putExtra("payment", payment);//选择的支付方式
                            intent.putExtra("type", transaction);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(PurchaseActivity.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }

    /**
     * 提交购买数据
     */
    private void postSelloutData(String fIdString, String number, String paymentstr, String withdrawBtcPassstr) {
        RequestParams params = new RequestParams();
        params.put("paymentType", paymentstr);//添加选择方式
        params.put("fId", fIdString);//广告id
        params.put("amountReal", number);//数量
        params.put("buyOrShell", "1");
        params.put("withdrawBtcPass", withdrawBtcPassstr);//交易密码
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//用户ID
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TO_ZHIFU, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                if (jsonObject.getString("result").equals("0")) {
                    showLongToast("下单金额不在范围内");
                } else if (jsonObject.getString("result").equals("01")) {
                    showLongToast("账户余额不足！");
                } else if (jsonObject.getString("result").equals("02")) {
                    showLongToast("该广告已经被撤销！");
                } else if (jsonObject.getString("result").equals("05")) {
                    showLongToast("钱包异常！");
                } else if (jsonObject.getString("result").equals("-2")) {
                    showLongToast("交易密码有误！");
                } else {
                    if (postMsg.getText().toString().equals("出售")) {
                        showLongToast("您已出售，对方会在20分钟内向您付款！");
                        finish();
                    } else {
                        if (payment.equals("0")) {//银行卡支付
                            Intent intent = new Intent(PurchaseActivity.this, PaymentMSG.class);
                            intent.putExtra("orderFid", jsonObject.getString("orderFid"));//订单ID
                            intent.putExtra("payment", payment);//选择的支付方式
                            intent.putExtra("type", transaction);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(PurchaseActivity.this, PaymentTwoMSG.class);
                            intent.putExtra("orderFid", jsonObject.getString("orderFid"));//订单ID
                            intent.putExtra("payment", payment);//选择的支付方式
                            intent.putExtra("type", transaction);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(PurchaseActivity.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }


            }
        });
    }

    private void setData() {
        price.setText(areaList.getC2c().getPrice() + "");
        SpannableString s = new SpannableString(areaList.getC2c().getAmount() + "");//这里输入自己想要的提示文字
        purchase.setHint(s);
       // double value=Double.valueOf(areaList.getC2c().getLimitMin() + "-" + areaList.getC2c().getLimitMax());
        if (areaList.getC2c().getLimitMin()<=0){
            showLongToast("该订单已经被抢");
            finish();
        }
        SpannableString msg = new SpannableString(areaList.getC2c().getLimitMin() + "-" + areaList.getC2c().getLimitMax());//这里输入自己想要的提示文字
        et_Quota.setHint(msg);
        pricetype.setText("(" + areaList.getC2c().getLegalCurrency().getCurrencySymbol() + ")");
        Amountofmoney.setText("(" + areaList.getC2c().getLegalCurrency().getCurrencySymbol() + ")");
        if (areaList.getC2c().getCptOrUsdt().equals("1")) {
            cptOrUsdt.setText("(USDT)");
        } else {
            cptOrUsdt.setText("(CPT)");
        }
        if (areaList.getC2c().getPayment().contains("0")) {
            ll_yinhangka.setVisibility(View.VISIBLE);
        }
        if (areaList.getC2c().getPayment().contains("1")) {
            ll_weixin.setVisibility(View.VISIBLE);
        }
        if (areaList.getC2c().getPayment().contains("2")) {
            zhifubo.setVisibility(View.VISIBLE);
        }
    }
}
