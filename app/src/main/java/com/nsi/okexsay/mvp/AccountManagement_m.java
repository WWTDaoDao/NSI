package com.nsi.okexsay.mvp;

import android.content.Context;
import android.util.Log;

import com.nsi.okexsay.http.StaticField;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class AccountManagement_m implements AccountManagement_in.Model {

    private Context context;
    private AccountManagement_p present;

    public AccountManagement_m(Context context, AccountManagement_p present) {
        this.context = context;
        this.present = present;
    }

    @Override
    public void get_bank_data(RequestParams params) {
        HttpRequest.post(StaticField.SHOW_BANK, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);

                Log.e("获取所有银行卡：", apiResponse.getResponse() + " ");

                present.res_bank_data(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.res_bank_data(0, msg);
            }
        });

    }

    @Override
    public void get_weixin_data(RequestParams params) {
        HttpRequest.post(StaticField.SHOW_WX, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                Log.e("获取所有微信：", apiResponse.getResponse() + " ");
                present.res_weixin_data(1, apiResponse.getResponse());
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.res_weixin_data(0, msg);
            }
        });
    }

    @Override
    public void get_zhifubao_data(RequestParams params) {
        HttpRequest.post(StaticField.SHOW_ZHIFB, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                Log.e("获取所有支付宝：", apiResponse.getResponse() + " ");
                present.res_zhifubao_data(1, apiResponse.getResponse());
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                if (msg == null) {
                    Log.e("get_zhifubao_data", "msg is null");
                }

                Log.e("获取所有支付宝Failure：", msg + " ");
                super.onFailure(errorCode, msg);
                present.res_zhifubao_data(0, msg);
            }
        });
    }

    @Override
    public void delete_bank(RequestParams params) {
        HttpRequest.post(StaticField.DELETE_BANK, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                present.res_delete_bank(1, apiResponse.getResponse());
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                if (msg == null) {
                    Log.e("get_zhifubao_data", "msg is null");
                }
                super.onFailure(errorCode, msg);
                present.res_delete_bank(0, msg);
            }
        });
    }

    @Override
    public void delete_zhifubao_weixin(RequestParams params) {
        HttpRequest.post(StaticField.DELETE_ZHIFUBAO_WEIXIN, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);
                present.res_delete_zhifubao_weixin(1, apiResponse.getResponse());
            }


            @Override
            public void onFailure(int errorCode, String msg) {
                if (msg == null) {
                    Log.e("get_zhifubao_data", "msg is null");
                }
                super.onFailure(errorCode, msg);
                present.res_delete_zhifubao_weixin(0, msg);
            }
        });
    }
}
