package com.nsi.okexsay.mvp;

import android.content.Context;

import com.nsi.okexsay.http.StaticField;

import cn.finalteam.okhttpfinal.ApiResponse;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class BankCardReceipt_m implements BankCardReceipt_in.Model {

    private Context context;
    private BankCardReceipt_p present;

    public BankCardReceipt_m(Context context, BankCardReceipt_p present) {
        this.context = context;
        this.present = present;
    }

    @Override
    public void get_bank_list() {

    }

    @Override
    public void get_is_Merchant(RequestParams params) {
        HttpRequest.post(StaticField.SHOWFBLX, params, new BaseHttpRequestCallback<ApiResponse>() {
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

    @Override
    public void getSendMsg(RequestParams params) {
        HttpRequest.post(StaticField.GET_VERIFY_CODE, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);

                present.getSendMsg(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.getSendMsg(0, msg);
            }
        });
    }

    /**
     * 添加银行卡
     *
     * @param params
     */
    @Override
    public void req_submit(RequestParams params) {
        HttpRequest.post(StaticField.ADD_BANK, params, new BaseHttpRequestCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                super.onSuccess(apiResponse);

                present.res_submit_data(1, apiResponse.getResponse());
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                present.res_submit_data(0, msg);
            }
        });
    }
}
