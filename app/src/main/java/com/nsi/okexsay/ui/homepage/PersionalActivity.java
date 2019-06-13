package com.nsi.okexsay.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.AppContext;
import com.nsi.okexsay.Beans.DevertisementListBean;
import com.nsi.okexsay.Beans.PersionalBean;
import com.nsi.okexsay.Beans.TypeIdBean;
import com.nsi.okexsay.R;
import com.nsi.okexsay.constants.SharedPreferencesKeys;
import com.nsi.okexsay.http.StaticField;
import com.nsi.okexsay.ui.base.BaseActivity;
import com.nsi.okexsay.ui.homepage.adapter.PersionalAdapter;
import com.nsi.okexsay.ui.homepage.adapter.PurchaseAdapter;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.utils.JsonUtil;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

import static com.nsi.okexsay.utils.JsonUtil.parseObject;

//个人资产
public class PersionalActivity extends BaseActivity {
    private TextView homeTextTitle;
    private AppContext appContext;
    private SharedPreferences sp;
    private TextView totalMSG;
    private PersionalAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persionalactivity);
        appContext = (AppContext) getApplication();
        sp = appContext.preferences;
        intn();
        adapter = new PersionalAdapter(R.layout.item_persion);
        recyclerView.setAdapter(adapter);
    }

    private void intn() {
        homeTextTitle = findViewById(R.id.home_text_title);
        totalMSG = findViewById(R.id.totalMSG);
        homeTextTitle.setText("个人资产");
        recyclerView = findViewById(R.id.refreshView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(PersionalActivity.this, R.drawable.shape_devider_red));
        recyclerView.addItemDecoration(dividerItemDecoration);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        getPersional();
    }

    /**
     * 获取个人资产
     */
    private void getPersional() {
        RequestParams params = new RequestParams();
        params.put("fuserId", sp.getString(SharedPreferencesKeys.FID, ""));//当前用户id
        params.put("token", sp.getString(SharedPreferencesKeys.TOKEN, ""));
        HttpRequest.post(StaticField.FASSETS, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                DissDialog();
                JSONObject jsonObject = JSON.parseObject(apiResponse.getResponse());
                PersionalBean persionalBean = parseObject(jsonObject.toString(), PersionalBean.class);
                totalMSG.setText(persionalBean.getTotalCapital() + "");
                //adapter.setNewData(persionalBean.getFvirtualwallets());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DissDialog();
                if (msg.equals("CODE_REE")) {
                    startActivity(new Intent(PersionalActivity.this, LoginActivity.class));
                } else {
                    showShortToast(msg);
                }

            }
        });
    }
}
