package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.nsi.okexsay.utils.QrCodeCreateUtil;
import com.nsi.okexsay.wiget.LoadingDialog;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class CollectIconActivity extends BaseActivity implements View.OnClickListener {

    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    ImageView homeBtnBack;
    TextView homeTextTitle,homeTextRight;
    private ImageView img_collect_qrcode;
    private String realName = "", address = "",userId="";
    private String iconId = "12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_icon);
        initView();
        getCollectInfo();
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
        homeTextTitle.setText("收钱");
        homeTextRight.setText("消费明细");
        img_collect_qrcode = findViewById(R.id.img_collect_qrcode);

        homeTextRight.setOnClickListener(this);
        homeBtnBack.setOnClickListener(this);
    }

    private void getImage(String content){
        Bitmap qrCodeBitmap = QrCodeCreateUtil.createQRCodeBitmap(
                content, 550, 550);
        img_collect_qrcode.setImageBitmap(qrCodeBitmap);
    }

    private void getCollectInfo() {
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.RECHARGE_BTC, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (jsonObject.getInteger("code") == 0) {
                    realName = jsonObject.getString("fRealName");
                    address = jsonObject.getString("fadderess");
                    userId = jsonObject.getString("fuserId");
                    iconId = jsonObject.getString("fVi_fId");
                    if(TextUtils.isEmpty(iconId)){
                        iconId = "12";
                    }
                    getImage(userId+","+realName+","+address);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
            case R.id.home_text_right:
                Intent intentHistory = new Intent(this, PayAndCollectHistory.class);
                intentHistory.putExtra("iconId", iconId);
                startActivity(intentHistory);
                break;
        }
    }
}
