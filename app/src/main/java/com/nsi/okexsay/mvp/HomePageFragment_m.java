package com.nsi.okexsay.mvp;

import android.content.Context;

public class HomePageFragment_m implements HomePageFragment_in.Model {

    private Context context;
    private HomePageFragment_p present;

    public HomePageFragment_m(Context context, HomePageFragment_p present) {
        this.context = context;
        this.present = present;
    }

    @Override
    public void get_home_data() {

    }
}
