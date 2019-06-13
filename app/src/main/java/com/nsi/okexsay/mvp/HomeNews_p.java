package com.nsi.okexsay.mvp;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nsi.okexsay.Beans.HomeNewsBean;
import com.nsi.okexsay.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.finalteam.okhttpfinal.RequestParams;

public class HomeNews_p implements HomeNews_in.present {

    private Context context;
    private HomeNews_in.View view;
    private HomeNews_m model;

    public HomeNews_p(Context context, HomeNews_in.View view) {
        this.context = context;
        this.view = view;
        model = new HomeNews_m(context, this);
    }

    @Override
    public void load_data(RequestParams params) {
        model.get_data(params);
    }

    @Override
    public void res_data(int status, String dataString) {
        if (status == 1) {
            try {
                JSONObject jsonObject = new JSONObject(dataString);
                String code = jsonObject.getString("code");
                if (code.equals("0")) {
                    Gson gson = new Gson();
                    HomeNewsBean homeNewsBean = gson.fromJson(dataString, HomeNewsBean.class);
                    view.set_list_data(homeNewsBean);

                } else {
                    if (code.equals("-1")) {
                        //跳转登录页面
                        context.startActivity(new Intent(context, LoginActivity.class));
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            view.set_list_data(null);
            if (dataString.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, dataString, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
