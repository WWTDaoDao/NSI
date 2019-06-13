package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.library.OnPopupItemClickListener;
import com.library.PopupView;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.CurrencyTypeBean;
import com.nsi.okexsay.Beans.DistributeMSG;
import com.nsi.okexsay.Beans.PurchaseBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.ui.personcenter.AccountManagementActivity;
import com.nsi.okexsay.utils.JsonUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

//发布广告
public class ReleaseAdvertisement extends BaseActivity implements View.OnClickListener {
    private TextView tv_title;
    private PopupView popup_transaction;//货币归类
    private PopupView popup_Classification;//法币类型
    private PopupView popup_Company;//单位
    private List<CharSequence> ClassificationDatatransaction;
    private List<CharSequence> ClassificationClassification;
    private List<CharSequence> ClassificationCompany;
    private CurrencyTypeBean currencyTypeBean;
    private String tradeType = "0";//购买：1，出售：0
    private String LegalcurrencyID = "1";//法币类型
    private TextView TVPrice;
    private TextView TVMinimum;
    private TextView TVMaximum;
    private EditText ET_Price, ET_Minimum, ET_Number;
    private TextView TV_Maximum;
    private TextView TV_Company;
    private LinearLayout ll_yinhangka, ll_weixin, zhifubo;
    private String paymentY = "-1";//标记支付类型银行卡
    private String paymentW = "-1";//标记支付类型微信
    private String paymentZ = "-1";//标记支付类型支付宝
    private ImageView IV_yinhangka, IV_weixin, IV_zhifubao;
    private AppContext appContext;
    private SharedPreferences sp;
    private String cptOrUsdtstr = "1";
    private TextView postMsg;
    private String upzhifu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.releaseadvertisement);
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        initView();
        setOnLinseter();
    }


    private void initView() {
        postMsg = findViewById(R.id.postMsg);
        IV_yinhangka = findViewById(R.id.IV_yinhangka);
        IV_weixin = findViewById(R.id.IV_weixin);
        IV_zhifubao = findViewById(R.id.IV_zhifubao);
        ll_yinhangka = findViewById(R.id.ll_yinhangka);
        ll_weixin = findViewById(R.id.ll_weixin);
        zhifubo = findViewById(R.id.zhifubo);
        tv_title = findViewById(R.id.home_text_title);
        popup_transaction = findViewById(R.id.popup_transaction);
        popup_Classification = findViewById(R.id.popup_Classification);
        popup_Company = findViewById(R.id.popup_Company);
        TVPrice = findViewById(R.id.TVPrice);
        TVMinimum = findViewById(R.id.TVMinimum);
        TVMaximum = findViewById(R.id.TVMaximum);
        ET_Price = findViewById(R.id.ET_Price);
        ET_Number = findViewById(R.id.ET_Number);
        TV_Maximum = findViewById(R.id.TV_Maximum);
        ET_Minimum = findViewById(R.id.ET_Minimum);
        TV_Company = findViewById(R.id.TV_Company);
        popup_transaction.setBackgroundDrawable(null);
        popup_Classification.setBackgroundDrawable(null);
        popup_Company.setBackgroundDrawable(null);
        tv_title.setText("发布广告");
        ll_yinhangka.setOnClickListener(this);
        ll_weixin.setOnClickListener(this);
        zhifubo.setOnClickListener(this);
        postMsg.setOnClickListener(this);
        addData();
    }

    private void addData() {
        ClassificationDatatransaction = new ArrayList<>();
        ClassificationClassification = new ArrayList<>();
        ClassificationCompany = new ArrayList<>();
        ClassificationDatatransaction.add("购买");
        ClassificationDatatransaction.add("出售");
        popup_transaction.setItemsFromList(ClassificationDatatransaction);
        popup_transaction.setPostion(0);
        ClassificationCompany.add("CPT");
        ClassificationCompany.add("USDT");
        popup_Company.setItemsFromList(ClassificationCompany);
        popup_Company.setPostion(0);
        Shoudialog();
        getFBGG("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 获取广告实体
     */
    private void getFBGG(String fIdString) {
        RequestParams params = new RequestParams();
        params.put("cptOrUsdt", fIdString);//Cpt：0，usdt：1
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//用户ID
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.TO_FBGG, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                Log.e("TOFBGGType", jsonObject.toJSONString());
                currencyTypeBean = JsonUtil.parseObject(jsonObject.toJSONString(), CurrencyTypeBean.class);
                for (int i = 0; i < currencyTypeBean.getFbList().size(); i++) {
                    ClassificationClassification.add(currencyTypeBean.getFbList().get(i).getCurrencySymbol());
                }
                popup_Classification.setItemsFromList(ClassificationClassification);
                popup_Classification.setPostion(0);
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(ReleaseAdvertisement.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }


            }
        });
    }

    /**
     * 发布广告
     */
    private void CHECKCG() {
        if (LegalcurrencyID.equals("0")) {//如果法币类型为
            LegalcurrencyID = currencyTypeBean.getFbList().get(0).getId() + "";
        }
        if (!paymentY.equals("-1") && !paymentZ.equals("-1") && !paymentW.equals("-1")) {
            upzhifu = "0,1,2";
        } else if (paymentY.equals("-1") && !paymentZ.equals("-1") && !paymentW.equals("-1")) {
            upzhifu = "1,2";
        } else if (!paymentY.equals("-1") && !paymentZ.equals("-1") && paymentW.equals("-1")) {
            upzhifu = "0,1";
        } else if (!paymentY.equals("-1") && paymentZ.equals("-1") && !paymentW.equals("-1")) {
            upzhifu = "0,2";
        } else if (paymentY.equals("-1") && !paymentZ.equals("-1") && paymentW.equals("-1")) {
            upzhifu = "1";
        } else if (paymentY.equals("-1") && paymentZ.equals("-1") && !paymentW.equals("-1")) {
            upzhifu = "2";
        } else if (!paymentY.equals("-1") && paymentZ.equals("-1") & paymentW.equals("-1")) {
            upzhifu = "0";
        }
        RequestParams params = new RequestParams();
        params.put("tradeType", tradeType);//购买：1，出售：0
        params.put("fbTypeId", LegalcurrencyID);//法币类型
        params.put("cptOrUsdt", cptOrUsdtstr);//单位 Cpt：0，usdt：1
        params.put("price", ET_Price.getText().toString());//价格
        params.put("amount", ET_Number.getText().toString());//数量
        params.put("limitMin", ET_Minimum.getText().toString());//最小额
        params.put("limitMax", TV_Maximum.getText().toString());//最大额
        params.put("payment", upzhifu);//支付方式
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//用户ID
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.FBGG, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                // Log.e("CHECKCGType", jsonObject.toJSONString());
                if (jsonObject.getString("result").equals("00")) {
                    showLongToast("您没有上传银行卡支付");
                } else if (jsonObject.getString("result").equals("01")) {
                    showLongToast("您没有上传微信支付");
                } else if (jsonObject.getString("result").equals("02")) {
                    showLongToast("您没有上传支付宝支付");
                } else if (jsonObject.getString("result").equals("0")) {
                    showLongToast("您还不是商户！，请您申请商户");
                } else {
                    EventBus.getDefault().postSticky(new DistributeMSG("关闭"));
                    showLongToast("广告发布成功！");
                    finish();
                }
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(ReleaseAdvertisement.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_yinhangka:
                if (paymentY.equals("-1")) {
                    paymentY = "0";
                    IV_yinhangka.setImageDrawable(getResources().getDrawable(R.mipmap.selection));
                } else {
                    IV_yinhangka.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                    paymentY = "-1";
                }
                break;
            case R.id.ll_weixin:
                if (paymentW.equals("-1")) {
                    paymentW = "1";
                    IV_weixin.setImageDrawable(getResources().getDrawable(R.mipmap.selection));
                } else {
                    IV_weixin.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                    paymentW = "-1";
                }
                break;
            case R.id.zhifubo:
                if (paymentZ.equals("-1")) {
                    IV_zhifubao.setImageDrawable(getResources().getDrawable(R.mipmap.selection));
                    paymentZ = "2";
                } else {
                    IV_zhifubao.setImageDrawable(getResources().getDrawable(R.mipmap.defauflt));
                    paymentZ = "-1";
                }
                break;
            case R.id.postMsg:
                if (ET_Price.getText().toString().trim().equals(null) && ET_Price.getText().toString().length() > 0) {
                    showLongToast("请输入价格");
                } else if (ET_Number.getText().toString().trim().equals(null) && ET_Number.getText().toString().length() > 0) {
                    showLongToast("请输入数量");
                } else if (ET_Minimum.getText().toString().trim().equals(null) && ET_Minimum.getText().toString().length() > 0) {
                    showLongToast("请输入最小额");
                } else if (TV_Maximum.getText().toString().trim().equals(null) && TV_Maximum.getText().toString().length() > 0) {
                    showLongToast("请输入最大额");
                } else if (paymentY.equals("-1") && paymentW.equals("-1") && paymentZ.equals("-1")) {
                    showLongToast("请选择支付方式");
                } else {
                    if (tradeType.equals("0")) {
                        Shoudialog();
                        CHECKCG();
                    } else {
                        Shoudialog();
                        //CHECKCG();
                        getInspect(tradeType, ET_Number.getText().toString(), TV_Maximum.getText().toString(),ET_Price.getText().toString(),LegalcurrencyID);
                    }
                }
                break;
        }
    }

    private void setOnLinseter() {
        popup_transaction.setOnItemClickListener(new OnPopupItemClickListener() {
            @Override
            public void onItemClickListener(int i, int position, CharSequence charSequence) {
                if (position == 0) {
                    tradeType = "0";
                    postMsg.setText("购买");
                } else {
                    tradeType = "1";
                    postMsg.setText("出售");
                }
            }
        });
        popup_Classification.setOnItemClickListener(new OnPopupItemClickListener() {
            @Override
            public void onItemClickListener(int i, int position, CharSequence charSequence) {
                LegalcurrencyID = currencyTypeBean.getFbList().get(position).getId() + "";
                TVPrice.setText("(" + currencyTypeBean.getFbList().get(position).getCurrencySymbol() + ")");
                TVMinimum.setText("(" + currencyTypeBean.getFbList().get(position).getCurrencySymbol() + ")");
                TVMaximum.setText("(" + currencyTypeBean.getFbList().get(position).getCurrencySymbol() + ")");
            }
        });
        popup_Company.setOnItemClickListener(new OnPopupItemClickListener() {
            @Override
            public void onItemClickListener(int i, int pos, CharSequence charSequence) {
                if (pos == 0) {
                    cptOrUsdtstr = "0";
                } else {
                    cptOrUsdtstr = "1";
                }
                TV_Company.setText("(" + ClassificationCompany.get(pos) + ")");
            }
        });
        ET_Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")&&!ET_Number.getText().toString().equals("")) {
                    Double ET_Pricedouble = Double.valueOf(s.toString());
                    Double ET_Numberdouble = Double.valueOf(ET_Number.getText().toString());
                    TV_Maximum.setText((ET_Pricedouble * ET_Numberdouble) + "");
                } else {
                    TV_Maximum.setText("0");
                }

            }
        });
        ET_Number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    TV_Maximum.setText("0");
                } else {
                    Double ET_Pricedouble = Double.valueOf(ET_Price.getText().toString());
                    Double ET_Numberdouble = Double.valueOf(s.toString());
                    TV_Maximum.setText((ET_Pricedouble * ET_Numberdouble) + "");
                }

            }
        });
    }

    /**
     * 验证资产
     */
    private void getInspect(String cptOrUsdt, String amount, String limitMax,String pricestr,String fbTypeIdint) {
        RequestParams params = new RequestParams();
        params.put("cptOrUsdt", cptOrUsdt);//售出类型
        params.put("amount", amount);//数量
        params.put("limitMax", limitMax);//最大
        params.put("tradeType", "1");//卖
        params.put("price", pricestr);//单价
        params.put("fbTypeId", "1");//法币类型
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.CHECKCG, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                Log.e("getInspect", jsonObject.toJSONString());
                if (jsonObject.getString("result").equals("01")) {
                    showLongToast("已超过当前您拥有的币的数量");
                } else if (jsonObject.getString("result").equals("02")) {
                    showLongToast("已超过您所质押币的总价值");
                } else if (jsonObject.getString("result").equals("03")){
                    showLongToast("请管理员设置开盘价");
                } else if (jsonObject.getString("result").equals("04")){
                    showLongToast("商户不可用重新申请");
                } else if (jsonObject.getString("result").equals("05")){
                    showLongToast("钱包异常");
                }else {
                    CHECKCG();
                }

            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(ReleaseAdvertisement.this,LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }
}
