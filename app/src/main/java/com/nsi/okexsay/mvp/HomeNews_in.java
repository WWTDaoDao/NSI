package com.nsi.okexsay.mvp;

import com.nsi.okexsay.Beans.HomeNewsBean;

import cn.finalteam.okhttpfinal.RequestParams;

public interface HomeNews_in {

    interface View {
        void set_list_data(HomeNewsBean homeNewsBean);
    }

    interface Model {
        void get_data(RequestParams params);
    }

    interface present {
        void load_data(RequestParams params);

        void res_data(int status, String dataString);

    }

}
