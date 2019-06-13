package com.nsi.okexsay.mvp;

import android.content.Context;

import com.nsi.okexsay.http.StaticField;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class AddZhiFuBaoWeiXin_m implements AddZhiFuBaoWeiXin_in.Model {

    private Context context;
    private AddZhiFuBaoWeiXin_p present;

    public AddZhiFuBaoWeiXin_m(Context context, AddZhiFuBaoWeiXin_p present) {
        this.context = context;
        this.present = present;
    }

    @Override
    public void get_phone_code(RequestParams params) {
        HttpRequest.post(StaticField.GET_VERIFY_CODE, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);

                present.res_get_phone_code(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.res_get_phone_code(0, msg);
            }
        });
    }

    @Override
    public void req_submit(RequestParams params) {//保存支付
        HttpRequest.post(StaticField.UPDATEOUTALIPAY, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                present.res_submit(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.res_submit(0, msg);
            }
        });
    }

    @Override
    public void get_is_Merchant(RequestParams params) {//判断是否是商户
        HttpRequest.post(StaticField.FMERCHAT, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                present.get_is_Merchant(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.get_is_Merchant(0, msg);
            }
        });
    }
}
