package com.nsi.okexsay.mvp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nsi.okexsay.Beans.AccountManagementBean;
import com.nsi.okexsay.Beans.AllPementBean;
import com.nsi.okexsay.Beans.PersionalBean;
import com.nsi.okexsay.Beans.WeixinZhiFuBean;
import com.nsi.okexsay.ui.login.LoginActivity;
import com.nsi.okexsay.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;

import static com.nsi.okexsay.utils.JsonUtil.parseObject;

public class AccountManagement_p implements AccountManagement_in.Present {

    private Context context;
    private AccountManagement_in.View view;
    private AccountManagement_m model;
    List<AllPementBean> list_data = null;
    List<WeixinZhiFuBean> list_wz= null;
    public AccountManagement_p(Context context, AccountManagement_in.View view) {
        this.context = context;
        this.view = view;
        model = new AccountManagement_m(context, this);
    }

    @Override
    public void load_bank_data(String token, String fuserId) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("fuserId", fuserId);//用户ID);
        model.get_bank_data(params);
    }

    @Override
    public void load_zhifubao_data(String token, String fuserId) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("fuserId", fuserId);//用户ID);
        model.get_zhifubao_data(params);
    }

    @Override
    public void load_weixin_data(String token, String fuserId) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("fuserId", fuserId);//用户ID);
        model.get_weixin_data(params);
    }

    @Override
    public void req_delete_bank(String token, String fuserId, String fid) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("fuserId", fuserId);//用户ID);
        params.put("fid", fid);
        model.delete_bank(params);
    }

    @Override
    public void req_delete_zhifubao_weixin(String token, String fuserId, String fid) {
        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("fuserId", fuserId);//用户ID);
        params.put("fid", fid);
        model.delete_zhifubao_weixin(params);
    }

    @Override
    public void res_bank_data(int status, String res_data) {
        if (res_data == null) {
            return;
        }

        List<AccountManagementBean> listall = new ArrayList<>();
        if (status == 1) {
            JSONObject jsonObject = JSON.parseObject(res_data);
            if (jsonObject.getInteger("code")!=-2){
                list_data = JsonUtil.parseList(jsonObject.getString("bankinfo"), AllPementBean.class);
                for (int i = 0; i < list_data.size(); i++) {
                    AccountManagementBean accountManagementBean=new AccountManagementBean();
                    accountManagementBean.setType(123);
                    accountManagementBean.setId(list_data.get(i).getFid()+"");
                    accountManagementBean.setNumber(list_data.get(i).getFbankNumber());
                    accountManagementBean.setTypefrom(list_data.get(i).getFname());
                    listall.add(accountManagementBean);
                }
            }

        } else {
            if (res_data.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, res_data, Toast.LENGTH_SHORT).show();
            }
        }

        view.set_bank_datas(listall);
    }

    @Override
    public void res_zhifubao_data(int status, String res_data) {
        if (res_data == null) {
            return;
        }
        List<AccountManagementBean> listall = new ArrayList<>();
        Log.e("res_zhifubao_data", res_data);
        if (status == 1) {
            JSONObject jsonObject = JSON.parseObject(res_data);
            if (jsonObject.getInteger("code")!=-2){
                list_wz = JsonUtil.parseList(jsonObject.getString("zfblist"), WeixinZhiFuBean.class);
                for (int i = 0; i < list_wz.size(); i++) {
                    AccountManagementBean accountManagementBean=new AccountManagementBean();
                    accountManagementBean.setType(124);
                    accountManagementBean.setId(list_wz.get(i).getFId()+"");
                    accountManagementBean.setNumber(list_wz.get(i).getAccountnumber());
                    accountManagementBean.setTypefrom("支付宝");
                    listall.add(accountManagementBean);
                }
            }

        } else {
            if (res_data.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, res_data, Toast.LENGTH_SHORT).show();
            }
        }

        view.set_zhifubao_datas(listall);
    }

    @Override
    public void res_weixin_data(int status, String res_data) {
        if (res_data == null) {
            return;
        }

        List<AccountManagementBean> listall = new ArrayList<>();
       // Log.e("res_weixin_data", res_data);
        if (status == 1) {
            JSONObject jsonObject = JSON.parseObject(res_data);
            if (jsonObject.getInteger("code")!=-2){
                list_wz = JsonUtil.parseList(jsonObject.getString("wxlist"), WeixinZhiFuBean.class);
                for (int i = 0; i < list_wz.size(); i++) {
                    AccountManagementBean accountManagementBean=new AccountManagementBean();
                    accountManagementBean.setType(125);
                    accountManagementBean.setId(list_wz.get(i).getFId()+"");
                    accountManagementBean.setNumber(list_wz.get(i).getAccountnumber());
                    accountManagementBean.setTypefrom("微信");
                    listall.add(accountManagementBean);
                }
            }
        } else {
            if (res_data.equals("CODE_REE")) {
                context.startActivity(new Intent(context, LoginActivity.class));
            } else {
                Toast.makeText(context, res_data, Toast.LENGTH_SHORT).show();
            }
        }
        view.set_weixin_datas(listall);
    }

    /**
     * 删除银行卡返回
     *
     * @param status
     * @param res_data
     */
    @Override
    public void res_delete_bank(int status, String res_data) {
        view.dell_back(status,res_data);
    }

    @Override
    public void res_delete_zhifubao_weixin(int status, String res_data) {

    }


}
