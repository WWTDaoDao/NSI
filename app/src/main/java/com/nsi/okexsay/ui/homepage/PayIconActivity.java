package com.nsi.okexsay.ui.homepage;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.PrePayBean;
import com.nsi.okexsay.Beans.VirtualtocptBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.adapter.PayExchangeTypeAdapter;
import com.nsi.okexsay.utils.JsonUtil;
import com.nsi.okexsay.wiget.LoadingDialog;
import com.google.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class PayIconActivity extends BaseActivity implements View.OnClickListener {

    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    private ImageView homeBtnBack;
    private TextView homeTextTitle, homeTextRight;
    private LinearLayout line_scan_pay;
    private RelativeLayout rela_select_other_icon;
    private Button btn_submit;
    private TextView text_ftp_total, text_shanghu;
    private EditText edit_pay_number, edit_exchange_password;
    private PopupWindow popupWindow;
    private View contentView;

    private PrePayBean prePayBean = new PrePayBean();
    private List<VirtualtocptBean> iconList = new ArrayList<>();
    private List<String> xnbTotal = new ArrayList<>();
    private PayExchangeTypeAdapter listAdapter;

    private int REQUEST_CODE = 0x01;
    private int RESULT_OK = 0xA1;
    private static final int REQUEST_CAMERA = 0;
    public static final int PATY_TO_EXCHANGE = 111;
    private String intentInChange = "";
    private String shId = "", shName = "", shAddress = "", arges = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_icon);
        intentInChange = getIntent().getStringExtra("scanResult");
        initView();
        prePay();
    }

    private void initView() {
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeTextRight = findViewById(R.id.home_text_right);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextRight.setVisibility(View.VISIBLE);
        homeTextTitle.setText("付钱");
        homeTextRight.setText("消费明细");
        line_scan_pay = findViewById(R.id.line_scan_pay);
        btn_submit = findViewById(R.id.btn_submit);
        text_ftp_total = findViewById(R.id.text_ftp_total);
        text_shanghu = findViewById(R.id.text_shanghu);
        edit_pay_number = findViewById(R.id.edit_pay_number);
        edit_exchange_password = findViewById(R.id.edit_exchange_password);
        rela_select_other_icon = findViewById(R.id.rela_select_other_icon);
        setTextMsg();
        rela_select_other_icon.setOnClickListener(this);
        line_scan_pay.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        homeTextRight.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);
    }

    //服务器返回金额数量
    private void setTextMsg() {
        if (intentInChange.contains(";")) {
            String[] arrayAll = intentInChange.split(";");
            if (arrayAll.length >= 2) {
                arges = arrayAll[1];
                String[] array = arrayAll[0].split(",");
                if (array.length == 3) {
                    shId = array[0];
                    shName = array[1];
                    shAddress = array[2];
                    text_shanghu.setText(shName);
                } else if (array.length > 3) {
                    shId = array[0];
                    shName = array[1];
                    shAddress = array[2];
                    text_shanghu.setText(shName);
                    edit_pay_number.setText(array[3]);
                    edit_pay_number.setEnabled(false);
                }
            }
        } else {
            String[] array = intentInChange.split(",");
            if (array.length >= 3) {
                shId = array[0];
                shName = array[1];
                shAddress = array[2];
                text_shanghu.setText(shName);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.home_text_right:
                Intent intentHistory = new Intent(this, PayAndCollectHistory.class);
                intentHistory.putExtra("iconId", prePayBean.getFvirtualcointype().getFid());
                startActivity(intentHistory);
                break;
            case R.id.line_scan_pay:

                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1000);
                } else {
                    Intent intent = new Intent(PayIconActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            case R.id.btn_submit:
                savePayIcon();
                break;
            case R.id.rela_select_other_icon:
                Intent intentType = new Intent(this, ExchangeActivity.class);
                intentType.putExtra("fromFlag", "PAY");
                startActivityForResult(intentType, PATY_TO_EXCHANGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            if (requestCode == PATY_TO_EXCHANGE) {
                prePay();
            }
//            Bundle bundle = data.getExtras();
//            String scanResult = bundle.getString("qr_scan_result");
         //   Log.d("scanResult======", scanResult);

        }
    }

    private void prePay() {
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.PRE_PAY, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (jsonObject.getInteger("code") == 0) {
                    prePayBean = JsonUtil.parseObject(jsonObject.toJSONString(), PrePayBean.class);
                    text_ftp_total.setText(prePayBean.getCptFtotal() + "CPT");
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

    private void savePayIcon() {
        String payNumber = edit_pay_number.getText().toString().trim();
        String payPassword = edit_exchange_password.getText().toString().trim();
        if (TextUtils.isEmpty(payNumber) || TextUtils.isEmpty(payPassword)) {
            showShortToast("数量和交易密码不能为空！");
            return;
        } else if (payPassword.length() < 6) {
            showShortToast("交易密码不能小于6位！");
            return;
        } else if (Double.parseDouble(payNumber) > Double.parseDouble(prePayBean.getCptFtotal())) {
            showShortToast("可用数量不足，请重新输入！");
            return;
        }
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        params.put("symbol", prePayBean.getFvirtualcointype().getFid());
        params.put("withdrawBtcPass", payPassword);
        params.put("withdrawAddr", shAddress);
        params.put("withdrawRemark", "123");
        params.put("withdrawAddr_id", shId);
        params.put("withdrawAmount", payNumber);
        params.put("level", prePayBean.getFlevel());
        params.put("arges", arges);
        HttpRequest.post(StaticField.PAY_ICON, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (jsonObject.getInteger("code") == 0) {
                    showShortToast("支付成功！");
                    finish();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                ToastUtils.showToast(ChooseRiceActivity.this, "相机权限已申请");
                Intent intent = new Intent(PayIconActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            } else {
                showShortToast("相机权限已被禁止,请在设置中打开");
            }
        }
    }

}
