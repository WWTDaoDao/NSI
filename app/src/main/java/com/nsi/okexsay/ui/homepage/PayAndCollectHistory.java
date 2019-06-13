package com.nsi.okexsay.ui.homepage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.PayAndColletionHistoryBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.adapter.PayAndCollectionHistoryAdapter;
import com.nsi.okexsay.utils.JsonUtil;
import com.nsi.okexsay.wiget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class PayAndCollectHistory extends BaseActivity implements View.OnClickListener {

    private LoadingDialog dialog;
    private AppContext appContext;
    private SharedPreferences sp;
    private ListView lv_history;
    private ImageView homeBtnBack;
    private TextView homeTextTitle;
    private List<PayAndColletionHistoryBean> dataList = new ArrayList<>();
    private PayAndCollectionHistoryAdapter listAdapter;
    private String iconId = "12";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_exchange_history);
        initView();
        getIconHistory();
    }

    private void initView(){
        iconId = getIntent().getStringExtra("iconId");
        if(TextUtils.isEmpty(iconId)){
            iconId = "12";
        }
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        dialog = new LoadingDialog(this);
        lv_history = findViewById(R.id.lv_history);
        homeBtnBack = findViewById(R.id.home_btn_back);
        homeTextTitle = findViewById(R.id.home_text_title);
        homeBtnBack.setVisibility(View.VISIBLE);
        homeTextTitle.setText("收支明细");

        homeBtnBack.setOnClickListener(this);

        listAdapter = new PayAndCollectionHistoryAdapter(this, dataList);
        lv_history.setAdapter(listAdapter);
    }

    private void getIconHistory() {
        dialog.show();
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        params.put("symbol", iconId);
        HttpRequest.post(StaticField.PAY_COLLETION_HISTORY, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                dialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse().toString());
                if (jsonObject.getInteger("code") == 0) {
                    dataList = JsonUtil.parseList(jsonObject.getString("fvirtualcaptualoperations"), PayAndColletionHistoryBean.class);
                    listAdapter.setList(dataList);
                    lv_history.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                } else {
                    showShortToast(jsonObject.getString("msg"));
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
        switch (view.getId()) {
            case R.id.home_btn_back:
                finish();
                break;
        }
    }
}
