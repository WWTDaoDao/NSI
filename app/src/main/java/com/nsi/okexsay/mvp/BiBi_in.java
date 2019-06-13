package com.nsi.okexsay.mvp;

import com.nsi.okexsay.Beans.BiBiBean;

import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;

public interface BiBi_in {

    interface View {
        void set_data(List<BiBiBean> list);
      //  void set_currency(List<BiBiBean> list);
    }

    interface Model {
        void get_data(RequestParams params);
       // void get_currency(RequestParams params);
    }

    interface Present {
        void load_data(RequestParams params);
     //   void load_currency(RequestParams params);

        void res_datas(int status, String dataString);
      //  void res_currency(int status, String dataString);
    }
}
