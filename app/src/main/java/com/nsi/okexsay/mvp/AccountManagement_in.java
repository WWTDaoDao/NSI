package com.nsi.okexsay.mvp;

import com.nsi.okexsay.Beans.AccountManagementBean;

import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;

public interface AccountManagement_in {

    interface View {
        void set_bank_datas(List<AccountManagementBean> list);

        void set_zhifubao_datas(List<AccountManagementBean> list);

        void set_weixin_datas(List<AccountManagementBean> list);

        void dell_back(int status, String res_data);
    }

    interface Model {
        void get_bank_data(RequestParams params);

        void get_weixin_data(RequestParams params);

        void get_zhifubao_data(RequestParams params);

        void delete_bank(RequestParams params);

        void delete_zhifubao_weixin(RequestParams params);
    }

    interface Present {
        void load_bank_data(String token, String fuserId);

        void load_zhifubao_data(String token, String fuserId);

        void load_weixin_data(String token, String fuserId);

        void req_delete_bank(String token, String fuserId, String fid);

        void req_delete_zhifubao_weixin(String token, String fuserId, String fid);

        void res_bank_data(int status, String res_data);

        void res_zhifubao_data(int status, String res_data);

        void res_weixin_data(int status, String res_data);

        void res_delete_bank(int status, String res_data);

        void res_delete_zhifubao_weixin(int status, String res_data);
    }

}
