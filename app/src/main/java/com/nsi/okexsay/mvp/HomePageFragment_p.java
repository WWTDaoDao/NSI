package com.nsi.okexsay.mvp;

import android.content.Context;

public class HomePageFragment_p implements HomePageFragment_in.Present {

    private Context context;
    private HomePageFragment_in.View view;
    private HomePageFragment_m model;

    public HomePageFragment_p(Context context, HomePageFragment_in.View view) {
        this.context = context;
        this.view = view;
        model = new HomePageFragment_m(context, this);
    }

    @Override
    public void load_home_data() {

    }

    @Override
    public void res_home_data(String dataString) {

    }
}
