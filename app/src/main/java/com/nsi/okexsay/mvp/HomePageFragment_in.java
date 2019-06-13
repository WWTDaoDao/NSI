package com.nsi.okexsay.mvp;

import com.nsi.okexsay.Beans.HomeDatasBean;

public interface HomePageFragment_in {

    interface View {
        void init_data(HomeDatasBean homeDatasBean);
    }

    interface Model {
        void get_home_data();
    }

    interface Present {
        void load_home_data();

        void res_home_data(String dataString);
    }

}
