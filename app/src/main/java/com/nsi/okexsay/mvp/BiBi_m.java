package com.nsi.okexsay.mvp;

import android.content.Context;
import android.util.Log;

import com.nsi.okexsay.http.StaticField;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class BiBi_m implements BiBi_in.Model {

    private Context context;
    private BiBi_p present;

    public BiBi_m(Context context, BiBi_p present) {
        this.context = context;
        this.present = present;
    }

    @Override
    public void get_data(RequestParams params) {
        HttpRequest.post(StaticField.INDEXMARKET, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);

                Log.e("或许bb交易数据：", apiResponse.getResponse() + " ");

                present.res_datas(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.res_datas(1, msg);
            }
        });
    }
}
