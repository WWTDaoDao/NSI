package com.nsi.okexsay.mvp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.nsi.okexsay.ui.login.LoginActivity;

import cn.finalteam.okhttpfinal.RequestParams;

public class BankCardReceipt_p implements BankCardReceipt_in.Present {

    private Context context;
    private BankCardReceipt_in.View view;
    private BankCardReceipt_m model;

    public BankCardReceipt_p(Context context, BankCardReceipt_in.View view) {
        this.context = context;
        this.view = view;
        model = new BankCardReceipt_m(context, this);
    }

    @Override
    public void load_bank_list() {

    }

    @Override
    public void req_submit(RequestParams params) {
        model.req_submit(params);
    }

    @Override
    public void get_is_Merchant(RequestParams params) {
        model.get_is_Merchant(params);
    }

    @Override
    public void get_is_Merchant(int status, String res_data) {
        if (status == 1) {
            view.set_Merchant_code(res_data);
        } else {
            //view.set_get_phone_code_status("0");
            if (res_data.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, res_data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void res_bank_list() {

    }

    @Override
    public void getSendMsg(RequestParams params) {
        model.getSendMsg(params);
    }

    @Override
    public void getSendMsg(int status, String res_data) {
        view.getSendMsg(res_data);
    }

    @Override
    public void res_submit_data(int status, String res_data) {
        view.set_submit_status(res_data);

    }
}
