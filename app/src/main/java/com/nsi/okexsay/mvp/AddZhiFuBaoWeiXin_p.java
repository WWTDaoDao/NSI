package com.nsi.okexsay.mvp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.nsi.okexsay.ui.login.LoginActivity;

import cn.finalteam.okhttpfinal.RequestParams;

public class AddZhiFuBaoWeiXin_p implements AddZhiFuBaoWeiXin_in.Present {

    private Context context;
    private AddZhiFuBaoWeiXin_in.View view;
    private AddZhiFuBaoWeiXin_m model;

    public AddZhiFuBaoWeiXin_p(Context context, AddZhiFuBaoWeiXin_in.View view) {
        this.context = context;
        this.view = view;
        model = new AddZhiFuBaoWeiXin_m(context, this);
    }

    @Override
    public void get_phone_code(RequestParams params) {
       model.get_phone_code(params);
        //view.get_phone_code(params);
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

    /**
     * 获取验证码返回
     *
     * @param status
     * @param res_data
     */
    @Override
    public void res_get_phone_code(int status, String res_data) {
        Log.e("res_get_phone_code", res_data);
        if (status == 1) {

            view.set_get_phone_code_status(res_data);
        } else {
            //view.set_get_phone_code_status("0");
            if (res_data.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, res_data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 提交返回
     *
     * @param status
     * @param res_data
     */
    @Override
    public void res_submit(int status, String res_data) {
        if (status == 1) {
            view.set_submit_status(res_data);
        } else {
            view.set_submit_status("0");
            if (res_data.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, res_data, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
