package com.nsi.okexsay.mvp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.Beans.AccountManagementBean;
import com.nsi.okexsay.Beans.BiBiBean;
import com.nsi.okexsay.Beans.WeixinZhiFuBean;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;

public class BiBi_p implements BiBi_in.Present {

    private Context context;
    private BiBi_in.View view;
    private BiBi_m model;

    public BiBi_p(Context context, BiBi_in.View view) {
        this.context = context;
        this.view = view;
        model = new BiBi_m(context, this);
    }

    @Override
    public void load_data(RequestParams params) {
        model.get_data(params);
    }

//    @Override
//    public void load_currency(RequestParams params) {
//
//    }

    @Override
    public void res_datas(int status, String res_data) {
        if (res_data == null) {
            return;
        }

        List<BiBiBean> listall;
      // Log.e("res_weixin_data", res_data);
        if (status == 1) {
            JSONArray jsonObject = JSON.parseArray(res_data);
            listall = JsonUtil.parseList(jsonObject.toString(), BiBiBean.class);
            view.set_data(listall);
//            if (jsonObject.getInteger("code")!=-2){
//                list_wz = JsonUtil.parseList(jsonObject.getString("wxlist"), WeixinZhiFuBean.class);
//                for (int i = 0; i < list_wz.size(); i++) {
//                    AccountManagementBean accountManagementBean=new AccountManagementBean();
//                    accountManagementBean.setType(125);
//                    accountManagementBean.setId(list_wz.get(i).getFId()+"");
//                    accountManagementBean.setNumber(list_wz.get(i).getAccountnumber());
//                    accountManagementBean.setTypefrom("微信");
//                    listall.add(accountManagementBean);
//                }
//            }
        } else {
            if (res_data.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, res_data, Toast.LENGTH_SHORT).show();
            }
        }
        //view.set_weixin_datas(listall);
    }

//    @Override
//    public void res_currency(int status, String dataString) {
//
//    }
}
