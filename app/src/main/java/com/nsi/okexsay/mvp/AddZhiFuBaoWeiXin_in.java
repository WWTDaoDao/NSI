package com.nsi.okexsay.mvp;

import cn.finalteam.okhttpfinal.RequestParams;

public interface AddZhiFuBaoWeiXin_in {

    interface View {
        void set_get_phone_code_status(String status);

        void get_phone_code(RequestParams params);

        void set_Merchant_code(String msg);

        void set_submit_status(String status);
    }

    interface Model {
        void get_phone_code(RequestParams params);

        void req_submit(RequestParams params);

        void get_is_Merchant(RequestParams params);

    }

    interface Present {
        void get_phone_code(RequestParams params);

        void req_submit(RequestParams params);
        void get_is_Merchant(RequestParams params);
        void get_is_Merchant(int status, String res_data);

        void res_get_phone_code(int status, String res_data);

        void res_submit(int status, String res_data);

    }

}
