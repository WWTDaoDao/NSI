package com.nsi.okexsay.mvp;

import android.content.Context;
import android.util.Log;

import com.nsi.okexsay.http.StaticField;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class HomeNews_m implements HomeNews_in.Model {

    private Context context;
    private HomeNews_p present;

    public HomeNews_m(Context context, HomeNews_p present) {
        this.context = context;
        this.present = present;
    }

    @Override
    public void get_data(RequestParams params) {
        HttpRequest.post(StaticField.HOME_NEWS_LIST, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                Log.e("获取新闻列表：", apiResponse.getResponse());
                present.res_data(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.res_data(0, msg);
            }
        });
    }
}
