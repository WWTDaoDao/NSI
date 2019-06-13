package com.nsi.okexsay.mvp;

import cn.finalteam.okhttpfinal.RequestParams;

public interface BankCardReceipt_in {

    interface View {
        void set_bank_list();

        void set_submit_status(String status);

        void set_Merchant_code(String msg);
        void getSendMsg(String msg);
    }

    interface Model {
        void get_bank_list();

        void get_is_Merchant(RequestParams params);
        void getSendMsg(RequestParams params);
        void req_submit(RequestParams params);
    }

    interface Present {
        void load_bank_list();

        void req_submit(RequestParams params);

        void get_is_Merchant(RequestParams params);

        void get_is_Merchant(int status, String res_data);

        void res_bank_list();

        void getSendMsg(RequestParams params);

        void getSendMsg(int status, String res_data);

        void res_submit_data(int status, String res_data);
    }

}
